package com.speech.up.auth.provider;

import java.util.HashMap;
import java.util.Map;

import org.springframework.security.oauth2.core.user.OAuth2User;

import com.speech.up.auth.service.implement.UserAuthorizationType;
import com.speech.up.auth.service.servicetype.LevelType;
import com.speech.up.auth.service.servicetype.ProviderType;
import com.speech.up.user.entity.UserEntity;

public class KakaoProvider implements ProviderOAuth {
	final String socialId;
	final String email;
	final String name;
	final String providerType;
	final String authorization;
	final String level;
	final boolean isUse;

	public KakaoProvider(OAuth2User user) {
		Object properties = user.getAttributes().get("properties");
		Map<String, String> responseMap = new HashMap<>();
		if (properties instanceof Map<?, ?> tempMap) {
			response(responseMap, tempMap);
		}

		this.socialId = user.getAttributes().get("id") + "";
		this.providerType = ProviderType.KAKAO.name();
		this.email = "none";
		this.name = responseMap.get("nickname");
		this.authorization = UserAuthorizationType.ROLE_GENERAL_USER.name();
		this.level = LevelType.BRONZE.name();
		this.isUse = true;
	}

	private void response(Map<String, String> response, Map<?, ?> tempMap) {
		for (Map.Entry<?, ?> entry : tempMap.entrySet()) {
			if (entry.getKey() instanceof String && entry.getValue() instanceof String) {
				response.put((String)entry.getKey(), (String)entry.getValue());
			}
		}
	}

	@Override
	public UserEntity getUser() {
		return UserEntity.providerOf(socialId, email, level, name, authorization, providerType);
	}

}
