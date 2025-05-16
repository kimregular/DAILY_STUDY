package com.example.oauth.member.service;

import com.example.oauth.member.dto.AccessTokenDto;
import com.example.oauth.member.dto.GoogleProfileDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.web.client.RestClient;

@Service

public class GoogleService {

	@Value("${oauth.google.client-di}")
	private String googleClientId;

	@Value("${oauth.google.client-secret}")
	private String googleClientSecret;

	@Value("${oauth.google.redirect-uri}")
	private String googleRedirectUri;


	public AccessTokenDto getAccessToken(String code) {
		// 인가코드, clientId, client-secret, redirect_url, grant_type
		// spring6 부터 restTemplate은 비추천이다. 대신 RestClient 사용
		RestClient restClient = RestClient.create();

		// MultiValueMap을 통해 자동으로 form-data 형식으로 body 조립 가능

		LinkedMultiValueMap<String, String> params = new LinkedMultiValueMap<>();
		params.add("code", code);
		params.add("client_id", googleClientId);
		params.add("client_secret", googleClientSecret);
		params.add("redirect_uri", googleRedirectUri);
		params.add("grant_type", "authorization_code");

		ResponseEntity<AccessTokenDto> response = restClient.post()
				.uri("https://oauth2.googleapis.com/token") // json:token uri
				.header("Content-Type", "application/x-www-form-urlencoded")
				.body(params)
				.retrieve() // 응답 body값을 추출
				.toEntity(AccessTokenDto.class);

		System.out.println(response.getBody());
		return response.getBody();
	}

	public GoogleProfileDto getGoogleProfile(String token) {
		RestClient restClient = RestClient.create();
		ResponseEntity<GoogleProfileDto> response = restClient.get()
				.uri("https://openidconnect.googleapis.com/v1/userinfo")
				.header("Authorization", "Bearer " + token)
				.retrieve()
				.toEntity(GoogleProfileDto.class);
		System.out.println(response.getBody());
		return response.getBody();
	}
}
