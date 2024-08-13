package com.speech.up.oAuth.provider;

import java.util.HashMap;
import java.util.Map;

import org.springframework.security.oauth2.core.user.OAuth2User;

import com.speech.up.oAuth.service.servicetype.ProviderType;
import com.speech.up.user.entity.UserEntity;

public class Provider {
	final Map<ProviderType, UserEntity> user  = new HashMap<>();
	public Provider(OAuth2User oAuth2User){
		KakaoProvider kakaoProvider = new KakaoProvider(oAuth2User);
		GithubProvider githubProvider = new GithubProvider(oAuth2User);
		GoogleProvider googleProvider = new GoogleProvider(oAuth2User);

		this.user.put(ProviderType.KAKAO, kakaoProvider.getUser());
		this.user.put(ProviderType.GITHUB, githubProvider.getUser());
		this.user.put(ProviderType.GOOGLE, googleProvider.getUser());
	}


	public UserEntity getUser(ProviderType providerType){
		return user.get(providerType);
	}
}
