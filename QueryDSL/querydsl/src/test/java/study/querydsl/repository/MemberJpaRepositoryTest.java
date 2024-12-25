package study.querydsl.repository;

import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import study.querydsl.entity.Member;

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
}