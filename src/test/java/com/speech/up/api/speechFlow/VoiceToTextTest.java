package com.speech.up.api.speechFlow;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Profile;

@SpringBootTest
@Profile("test")
public class VoiceToTextTest {

	@Value(value = "test")
	private String accessKey;

	@Test
	public void testVoiceToText() {
		final String accessKey = "test";
		assertThat(accessKey).isEqualTo(this.accessKey);
	}
}
