package com.example.oauth.member.controller;

import com.example.oauth.auth.JwtTokenProvider;
import com.example.oauth.member.domain.Member;
import com.example.oauth.member.domain.SocialType;
import com.example.oauth.member.dto.*;
import com.example.oauth.member.service.GoogleService;
import com.example.oauth.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/member")
@RequiredArgsConstructor
public class MemberController {

	private final MemberService memberService;
	private final JwtTokenProvider jwtTokenProvider;
	private final GoogleService googleService;

	@PostMapping("/create")
	public ResponseEntity<?> memberCreate(@RequestBody MemberCreateDto memberCreateDto) {
		Member member = memberService.createMember(memberCreateDto);
		return ResponseEntity.status(HttpStatus.CREATED).body(member);
	}

	@PostMapping("/doLogin")
	public ResponseEntity<?> doLogin(@RequestBody MemberLoginDto memberLoginDto) {
		Member member = memberService.login(memberLoginDto);
		String jwtToken = jwtTokenProvider.createToken(member.getEmail(), member.getRole().toString());
		Map<String, Object> loginInfo = new HashMap<>();
		loginInfo.put("id", member.getId());
		loginInfo.put("token", jwtToken);
		return new ResponseEntity<>(loginInfo, HttpStatus.OK);
	}

	@PostMapping("/google/doLogin")
	public ResponseEntity<?> googleLogin(@RequestBody RedirectDto redirectDto) {
		// 1. accessToken 발급
		AccessTokenDto accessTokenDto = googleService.getAccessToken(redirectDto.getCode());
		// 2. 사용자 정보 얻기
        GoogleProfileDto googleProfileDto = googleService.getGoogleProfile(accessTokenDto.getAccess_token());
		// 3. 회원이 아니라면 회원가입
		Member originalMember = memberService.getMemberBySocialId(googleProfileDto.getSub());
		if (originalMember == null) {
			originalMember  = memberService.createOauth(googleProfileDto.getSub(), googleProfileDto.getEmail(), SocialType.GOOGLE);
		}
		// 4. 이미 회원이라면 토큰 발급
		String jwtToken = jwtTokenProvider.createToken(originalMember.getEmail(), originalMember.getRole().toString());
		Map<String, Object> loginInfo = new HashMap<>();
		loginInfo.put("id", originalMember.getId());
		loginInfo.put("token", jwtToken);
		return new ResponseEntity<>(loginInfo, HttpStatus.OK);
	}
}
