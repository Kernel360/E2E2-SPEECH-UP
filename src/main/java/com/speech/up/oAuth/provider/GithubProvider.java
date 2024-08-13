package com.speech.up.oAuth.provider;

import org.springframework.security.oauth2.core.user.OAuth2User;

import com.speech.up.oAuth.service.servicetype.LevelType;
import com.speech.up.oAuth.service.servicetype.ProviderType;
import com.speech.up.oAuth.service.implement.UserAuthorizationType;
import com.speech.up.user.entity.UserEntity;

public class GithubProvider implements ProviderOAuth {
	final UserEntity userEntity;
	final String socialId;
	final String email;
	final String name;
	final String providerType;
	final String authorization ;
	final String level;

	public GithubProvider(OAuth2User user) {
		this.socialId = user.getAttributes().get("id")+"";
		this.name = user.getAttributes().get("name")+"";
		this.email = "none";
		this.providerType = ProviderType.GITHUB.name();
		this.authorization = UserAuthorizationType.GENERAL_USER.name();
		this.level = LevelType.BRONZE.name();
		this.userEntity = new UserEntity(socialId, email, level, name, authorization, providerType);
	}
	@Override
	public UserEntity getUser() {
		return userEntity;
	}
}
