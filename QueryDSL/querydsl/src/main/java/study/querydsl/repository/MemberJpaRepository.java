package study.querydsl.repository;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;
import study.querydsl.dto.MemberSearchCondition;
import study.querydsl.dto.MemberTeamDto;
import study.querydsl.dto.QMemberTeamDto;
import study.querydsl.entity.Member;
import study.querydsl.entity.QMember;
import study.querydsl.entity.QTeam;

import java.util.List;
import java.util.Optional;

import static org.springframework.util.StringUtils.*;
import static study.querydsl.entity.QMember.*;
import static study.querydsl.entity.QTeam.*;

@Repository
public class MemberJpaRepository {

    private final EntityManager em;
    private final JPAQueryFactory queryFactory;

    public MemberJpaRepository(EntityManager em) {
        this.em = em;
        this.queryFactory = new JPAQueryFactory(em);
    }

    public void save(Member member) {
        em.persist(member);
    }

    public Optional<Member> findById(Long id) {
        Member foundMember = em.find(Member.class, id);
        return Optional.ofNullable(foundMember);
    }

    public List<Member> findAll() {
        return em.createQuery("select m from Member m", Member.class)
                 .getResultList();
    }

    public List<Member> findAll_dsl() {
        return queryFactory.selectFrom(member)
                           .fetch();
    }

    public List<Member> findByUsername(String username) {
        return em.createQuery("select m from Member m where m.username =: username", Member.class)
                 .setParameter("username", username)
                 .getResultList();
    }

    public List<Member> findByUsername_dsl(String username) {
        return queryFactory.selectFrom(member)
                           .where(member.username.eq(username))
                           .fetch();
    }

    public List<MemberTeamDto> searchByBuilder(MemberSearchCondition condition) {
        BooleanBuilder builder = new BooleanBuilder();
        if (hasText(condition.getUsername())) {
            builder.and(member.username.eq(condition.getUsername()));
        }
        if (hasText(condition.getTeamName())) {
            builder.and(team.name.eq(condition.getTeamName()));
        }
        if (condition.getAgeGoe() != null) {
            builder.and(member.age.goe(condition.getAgeGoe()));
        }
        if (condition.getAgeLoe() != null) {
            builder.and(member.age.loe(condition.getAgeLoe()));
        }


        return queryFactory.select(new QMemberTeamDto(member.id.as("memberId"),
                                                      member.username,
                                                      member.age,
                                                      team.id.as("teamId"),
                                                      team.name.as("teamName")))
                           .from(member)
                           .leftJoin(member.team, team)
                           .where(builder)
                           .fetch();
    }

    public List<MemberTeamDto> search(MemberSearchCondition condition) {
        return queryFactory.select(new QMemberTeamDto(member.id.as("memberId"),
                                                      member.username,
                                                      member.age,
                                                      team.id.as("teamId"),
                                                      team.name.as("teamName")))
                           .from(member)
                           .leftJoin(member.team, team)
                           .where(usernameEq(condition.getUsername()),
                                  teamNameEq(condition.getTeamName()),
                                  ageGoe(condition.getAgeGoe()),
                                  ageLoe(condition.getAgeLoe()))
                           .fetch();
    }

    private Predicate usernameEq(String username) {
        return StringUtils.isEmpty()
    }

    private Predicate teamNameEq(String teamName) {

    }

    private Predicate ageGoe(Integer ageGoe) {
    }

    private Predicate ageLoe(Integer ageLoe) {

    }

}
