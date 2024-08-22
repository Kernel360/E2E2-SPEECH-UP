package com.speech.up;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import io.github.cdimascio.dotenv.Dotenv;

@EnableJpaAuditing
@SpringBootApplication
public class UpApplication {

	public static void main(String[] args) {
		// Load .env file
		Dotenv dotenv = Dotenv.load();

		// Set system properties from .env file
		System.setProperty("api.voice.url", dotenv.get("API_VOICE_TO_TEXT_URL"));
		System.setProperty("api.voice.accessKey", dotenv.get("API_VOICE_TO_TEXT_ACCESS_KEY"));
		System.setProperty("api.voice.language-code", dotenv.get("API_VOICE_TO_LANGUAGE_CODE"));
		System.setProperty("api.voice.score", dotenv.get("API_VOICE_TO_SCORE"));

		System.setProperty("speech-flow.api.key.id", dotenv.get("SPEECH_FLOW_API_KEY_ID"));
		System.setProperty("speech-flow.api.key.secret", dotenv.get("SPEECH_FLOW_API_KEY_SECRET"));
		System.setProperty("speech-flow.lang", dotenv.get("SPEECH_FLOW_LANG"));
		System.setProperty("speech-flow.result.type", dotenv.get("SPEECH_FLOW_RESULT_TYPE"));

		System.setProperty("jwt.secret.key", dotenv.get("JWT_SECRET_KEY"));

		System.setProperty("spring.security.oauth2.client.registration.google.client-id", dotenv.get("GOOGLE_CLIENT_ID"));
		System.setProperty("spring.security.oauth2.client.registration.google.client-secret", dotenv.get("GOOGLE_CLIENT_SECRET"));

		System.setProperty("spring.security.oauth2.client.registration.kakao.client-id", dotenv.get("KAKAO_CLIENT_ID"));
		System.setProperty("spring.security.oauth2.client.registration.kakao.client-secret", dotenv.get("KAKAO_CLIENT_SECRET"));

		System.setProperty("spring.security.oauth2.client.registration.github.client-id", dotenv.get("GITHUB_CLIENT_ID"));
		System.setProperty("spring.security.oauth2.client.registration.github.client-secret", dotenv.get("GITHUB_CLIENT_SECRET"));

		System.setProperty("spring.datasource.url",dotenv.get("GOOGLE_CLOUD_DATABASE"));
		System.setProperty("google.cloud.url",dotenv.get("GOOGLE_CLOUD_URL"));

		System.setProperty("kakao.map.app.key", dotenv.get("KAKAO_MAP_APP_KEY"));

		SpringApplication.run(UpApplication.class, args);
	}

}
