package com.speech.up.auth.provider;

import org.springframework.security.oauth2.core.user.OAuth2User;

import com.speech.up.auth.service.servicetype.LevelType;
import com.speech.up.auth.service.servicetype.ProviderType;
import com.speech.up.common.enums.UserStatusCode;
import com.speech.up.user.entity.UserEntity;

public class GoogleProvider implements ProviderOAuth {
	final String socialId;
	final String email;
	final String name;
	final String providerType;
	final String authorization;
	final String level;
	final boolean isUse;

	public GoogleProvider(OAuth2User user) {
		this.socialId = user.getAttributes().get("sub") + "";
		this.providerType = ProviderType.GOOGLE.name();
		this.email = user.getAttributes().get("email") + "";
		this.name = user.getAttributes().get("name") + "";
		this.authorization = UserStatusCode.ROLE_GENERAL_USER.name();
		this.level = LevelType.BRONZE.name();
		this.isUse = true;
	}

	@Override
	public UserEntity getUser() {
		return UserEntity.providerOf(socialId, email, level, name, authorization, providerType);
	}
}
