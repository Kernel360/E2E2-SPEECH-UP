package com.speech.up.auth.provider;

import org.springframework.security.oauth2.core.user.OAuth2User;

import com.speech.up.auth.service.servicetype.LevelType;
import com.speech.up.auth.service.servicetype.ProviderType;
import com.speech.up.auth.service.implement.UserAuthorizationType;
import com.speech.up.user.entity.UserEntity;

public class GithubProvider implements ProviderOAuth {
	final String socialId;
	final String email;
	final String name;
	final String providerType;
	final String authorization ;
	final String level;
	final boolean isUse;

	public GithubProvider(OAuth2User user) {
		this.socialId = user.getAttributes().get("id")+"";
		this.name = user.getAttributes().get("name")+"";
		this.email = "none";
		this.providerType = ProviderType.GITHUB.name();
		this.authorization = UserAuthorizationType.ROLE_GENERAL_USER.name();
		this.level = LevelType.BRONZE.name();
		this.isUse = true;
	}
	@Override
	public UserEntity getUser() {
		return new UserEntity(socialId, email, level, name, authorization, providerType);
	}
}
