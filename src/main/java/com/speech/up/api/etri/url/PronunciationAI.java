package com.speech.up.api.etri.url;

import java.net.MalformedURLException;
import java.net.URL;

public class PronunciationAI implements ApiUrl{
	private final String aiApiScore;

	public PronunciationAI(String aiApiScore) {
		this.aiApiScore = aiApiScore;
	}

	@Override
	public URL getUrl() throws MalformedURLException {
		return new URL(aiApiScore);
	}
}
