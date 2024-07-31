package com.speech.up.api.voiceToText;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Profile;

@SpringBootTest
@Profile("test")
public class VoiceToTextTest {

	@Value(value = "${api.voice.accessKey}")
	private String accessKey;

	@Test
	public void testVoiceToText() {
		final String accessKey = "${api.voice.accessKey}";
		assertThat(accessKey).isEqualTo(this.accessKey);
	}
}
