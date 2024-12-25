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

}