package study.querydsl;

import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import study.querydsl.entity.Member;
import study.querydsl.entity.Team;

import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;
import static study.querydsl.entity.QMember.member;

@SpringBootTest
@Transactional
public class QuerydslBasicTest {

    @Autowired
    EntityManager em;

    JPAQueryFactory queryFactory;

    @BeforeEach
    void before() {
        queryFactory = new JPAQueryFactory(em);
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
    }

    @Test
    @DisplayName("start jpql")
    void test1() {
        // given
        // when
        Member foundMember = em.createQuery("select m from Member m where m.username = :username", Member.class)
                               .setParameter("username", "member1")
                               .getSingleResult();
        // then
        assertThat(foundMember.getUsername()).isEqualTo("member1");
    }

    @Test
    @DisplayName("start querydsl")
    void test2() {
        // given
//        QMember m = new QMember("m");
//        QMember m = QMember.member;
        // when
        Member foundMember = queryFactory.select(member)
                                         .from(member)
                                         .where(member.username.eq("member1"))
                                         .fetchOne();
        // then
        assertThat(foundMember.getUsername()).isEqualTo("member1");
    }

    @Test
    @DisplayName("search")
    void test3() {
        // given
        // when
        Member foundMember = queryFactory.selectFrom(member)
                                         .where(member.username.eq("member1")
                                                               .and(member.age.eq(10)))
                                         .fetchOne();
        // then
        assertThat(foundMember.getUsername()).isEqualTo("member1");
        assertThat(foundMember.getAge()).isEqualTo(10);
    }

    @Test
    @DisplayName("search with params")
    void test4() {
        // given
        // when
        Member foundMember = queryFactory.selectFrom(member)
                                         .where(member.username.eq("member1"), member.age.eq(10))
                                         .fetchOne();
        // where 에 and() 안 쓰고 파라미터로 넘김
        // then
        assertThat(foundMember.getUsername()).isEqualTo("member1");
        assertThat(foundMember.getAge()).isEqualTo(10);
    }

    @Test
    @DisplayName("resultFetch")
    void test5() {
        // given
        // when
//        List<Member> fetch = queryFactory.selectFrom(member)
//                                         .fetch();
//
//        Member fetchOne = queryFactory.selectFrom(member)
//                                      .fetchOne();
//
//        Member fetchFirst = queryFactory.selectFrom(member)
//                                        .fetchFirst();
//
//        int size = queryFactory.selectFrom(member)
//                               .fetch()
//                               .size();
//        // then
//        assertThat(fetch).hasSize(4);
//        assertThat(fetchOne).h
    }
}
