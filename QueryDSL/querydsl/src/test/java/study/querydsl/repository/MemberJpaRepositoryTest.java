package study.querydsl.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import study.querydsl.dto.MemberSearchCondition;
import study.querydsl.dto.MemberTeamDto;
import study.querydsl.entity.Member;
import study.querydsl.entity.Team;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
class MemberJpaRepositoryTest {

    @Autowired
    EntityManager em;

    @Autowired
    MemberJpaRepository memberJpaRepository;

    @Test
    @DisplayName("basicTest")
    void test1() {
        // given
        Member member = new Member("member1", 10);
        memberJpaRepository.save(member);
        // when
        Optional<Member> foundMember = memberJpaRepository.findById(member.getId());
        List<Member> members = memberJpaRepository.findAll();
        List<Member> foundMemberWithName = memberJpaRepository.findByUsername("member1");

        // then
        assertThat(foundMember).isPresent()
                               .contains(member);

        assertThat(members).contains(member);

        assertThat(foundMemberWithName.get(0)
                                      .getUsername()).isEqualTo("member1");
    }

    @Test
    @DisplayName("basicQuerydslTest")
    void test2(){
        // given
        Member member = new Member("member1", 10);
        memberJpaRepository.save(member);
        // when
        List<Member> members = memberJpaRepository.findAll_dsl();
        List<Member> foundMember = memberJpaRepository.findByUsername_dsl("member1");
        // then
        assertThat(members).containsExactly(member);
        assertThat(foundMember).containsExactly(member);
    }

    @Test
    @DisplayName("search test")
    void test3(){
        // given
        Team teamA = new Team("teamA");
        Team teamB = new Team("teamB");
        em.persist(teamA);
        em.persist(teamB);

        Member member1 = new Member("member1", 10, teamA);
        Member member2 = new Member("member2", 20, teamA);
        Member member3 = new Member("member3", 30, teamB);
        Member member4 = new Member("member4", 40, teamB);
        em.persist(member1);
        em.persist(member2);
        em.persist(member3);
        em.persist(member4);
        // when
        MemberSearchCondition condition = new MemberSearchCondition();
        condition.setAgeGoe(35);
        condition.setAgeLoe(40);
        condition.setTeamName("teamB");

        List<MemberTeamDto> result = memberJpaRepository.searchByBuilder(condition);
        // then
        assertThat(result).extracting("username")
                          .containsExactly("member4");
    }
}