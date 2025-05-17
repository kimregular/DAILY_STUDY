package com.example.oauth.member.service;

import com.example.oauth.member.dto.AccessTokenDto;
import com.example.oauth.member.dto.KakaoProfileDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.web.client.RestClient;

@Service
public class KakaoService {


	@Value("${oauth.kakao.client-id}")
	private String kakaoClientId;

	@Value("${oauth.kakao.redirect-uri}")
	private String kakaoRedirectUri;


	public AccessTokenDto getAccessToken(String code) {
		// 인가코드, clientId, client-secret, redirect_url, grant_type
		// spring6 부터 restTemplate은 비추천이다. 대신 RestClient 사용
		RestClient restClient = RestClient.create();

		// MultiValueMap을 통해 자동으로 form-data 형식으로 body 조립 가능

		LinkedMultiValueMap<String, String> params = new LinkedMultiValueMap<>();
		params.add("code", code);
		params.add("client_id", kakaoClientId);
		params.add("redirect_uri", kakaoRedirectUri);
		params.add("grant_type", "authorization_code");

		ResponseEntity<AccessTokenDto> response = restClient.post()
				.uri("https://kauth.kakao.com/oauth/token") // json:token uri
				.header("Content-Type", "application/x-www-form-urlencoded")
				.body(params)
				.retrieve() // 응답 body값을 추출
				.toEntity(AccessTokenDto.class);

		System.out.println(response.getBody());
		return response.getBody();
	}

	public KakaoProfileDto getKakaoProfile(String token) {
		RestClient restClient = RestClient.create();
		ResponseEntity<KakaoProfileDto> response = restClient.get()
				.uri("https://kapi.kakao.com/v2/user/me")
				.header("Authorization", "Bearer " + token)
				.retrieve()
				.toEntity(KakaoProfileDto.class);
		System.out.println(response.getBody());
		return response.getBody();
	}
}
