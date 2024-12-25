package study.querydsl.repository;

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

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
class MemberRepositoryTest {

    @Autowired
    EntityManager em;

    @Autowired
    MemberRepository memberRepository;

    @Test
    @DisplayName("basicTest")
    void test1() {
        // given
        Member member = new Member("member1", 10);
        memberRepository.save(member);
        // when
        Optional<Member> foundMember = memberRepository.findById(member.getId());
        List<Member> members = memberRepository.findAll();
        List<Member> foundMemberWithName = memberRepository.findByUsername("member1");

        // then
        assertThat(foundMember).isPresent()
                               .contains(member);

        assertThat(members).contains(member);

        assertThat(foundMemberWithName.get(0)
                                      .getUsername()).isEqualTo("member1");
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

        List<MemberTeamDto> result = memberRepository.search(condition);
        // then
        assertThat(result).extracting("username")
                          .containsExactly("member4");
    }

}