package com.speech.up.oAuth.provider;

import org.springframework.security.oauth2.core.user.OAuth2User;

import com.speech.up.oAuth.service.servicetype.LevelType;
import com.speech.up.oAuth.service.servicetype.ProviderType;
import com.speech.up.oAuth.service.implement.UserAuthorizationType;
import com.speech.up.user.entity.UserEntity;

public class GoogleProvider implements ProviderOAuth {
	final String socialId;
	final String email;
	final String name;
	final String providerType;
	final String authorization ;
	final String level;

	public GoogleProvider(OAuth2User user) {
		this.socialId = user.getAttributes().get("sub")+"";
		this.providerType = ProviderType.GOOGLE.name();
		this.email = user.getAttributes().get("email")+"";
		this.name = user.getAttributes().get("name")+"";
		this.authorization = UserAuthorizationType.GENERAL_USER.name();
		this.level = LevelType.BRONZE.name();
	}
	@Override
	public UserEntity getUser() {
		return new UserEntity(socialId, email, level, name, authorization, providerType);
	}
}
