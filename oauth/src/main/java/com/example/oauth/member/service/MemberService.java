package com.example.oauth.member.service;

import com.example.oauth.member.domain.Member;
import com.example.oauth.member.domain.SocialType;
import com.example.oauth.member.dto.MemberCreateDto;
import com.example.oauth.member.dto.MemberLoginDto;
import com.example.oauth.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class MemberService {

	private final MemberRepository memberRepository;
	private final PasswordEncoder passwordEncoder;

	public Member createMember(MemberCreateDto memberCreateDto) {
		Member member = Member.builder()
				.email(memberCreateDto.getEmail())
				.password(passwordEncoder.encode(memberCreateDto.getPassword()))
				.build();
		memberRepository.save(member);
		return member;
	}

	public Member login(MemberLoginDto memberLoginDto) {
		Optional<Member> optMember = memberRepository.findByEmail(memberLoginDto.getEmail());
		if(optMember.isEmpty()) throw new IllegalArgumentException("email이 존재하지 않습니다.");

		Member member = optMember.get();

		if (!passwordEncoder.matches(memberLoginDto.getPassword(), member.getPassword())) {
			throw new IllegalArgumentException("password가 일치하지 않습니다.");
		}

		return member;
	}

	public Member getMemberBySocialId(String socialId) {
		Member optMember = memberRepository.findBySocialId(socialId).orElse(null);
		return optMember;
	}

	public Member createOauth(String socialId, String email, SocialType socialType) {
		Member member = Member.builder()
				.email(email)
				.socialId(socialId)
				.socialType(socialType)
				.build();

		memberRepository.save(member);
		return member;
	}
}
