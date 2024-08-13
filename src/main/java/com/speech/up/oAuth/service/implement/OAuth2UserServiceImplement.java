package com.speech.up.oAuth.service.implement;

import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.speech.up.oAuth.entity.CustomOAuth2User;
import com.speech.up.oAuth.provider.Provider;
import com.speech.up.oAuth.service.servicetype.ProviderType;
import com.speech.up.user.entity.UserEntity;
import com.speech.up.user.repository.UserRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class OAuth2UserServiceImplement extends DefaultOAuth2UserService {
	private final UserRepository userRepository;

	@SuppressWarnings("null")
	@Override
	public OAuth2User loadUser(OAuth2UserRequest request) throws OAuth2AuthenticationException {
		OAuth2User oAuth2User = super.loadUser(request);
		String oauthClientName = request.getClientRegistration().getClientName();

		Provider provider = new Provider(oAuth2User);
		UserEntity userEntity = provider.getUser(ProviderType.valueOf(oauthClientName.toUpperCase()));

		assert userEntity != null;
		if(!userRepository.existsBySocialId(userEntity.getSocialId())){
			userRepository.save(userEntity);
		}
		return new CustomOAuth2User(userEntity.getSocialId());
	}

}
