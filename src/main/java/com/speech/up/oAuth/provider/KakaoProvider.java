package com.speech.up.oAuth.provider;

import java.util.HashMap;
import java.util.Map;

import org.springframework.security.oauth2.core.user.OAuth2User;

import com.speech.up.oAuth.service.servicetype.LevelType;
import com.speech.up.oAuth.service.servicetype.ProviderType;
import com.speech.up.oAuth.service.implement.UserAuthorizationType;
import com.speech.up.user.entity.UserEntity;

public class KakaoProvider implements ProviderOAuth {
	final UserEntity userEntity;
	final String socialId;
	final String email;
	final String name;
	final String providerType;
	final String authorization ;
	final String level;


	public KakaoProvider(OAuth2User user) {
		Map<String, String> responseMap = (Map<String, String>) user.getAttributes().get("properties");
		if(responseMap == null) {
			responseMap = new HashMap<String, String>();
		}
		this.socialId = user.getAttributes().get("id")+"";
		this.providerType = ProviderType.KAKAO.name();
		this.email = "none";
		this.name = responseMap.get("nickname");
		this.authorization = UserAuthorizationType.GENERAL_USER.name();
		this.level = LevelType.BRONZE.name();
		this.userEntity = new UserEntity(socialId, email, level, name, authorization, providerType);
	}

	@Override
	public UserEntity getUser() {
		return userEntity;
	}

}