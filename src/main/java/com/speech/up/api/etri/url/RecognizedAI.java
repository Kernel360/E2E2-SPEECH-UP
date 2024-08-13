package com.speech.up.api.etri.url;

import java.net.MalformedURLException;
import java.net.URL;


public class RecognizedAI implements ApiUrl{
	private final String aiApiUrl;

	public RecognizedAI(String aiApiUrl) {
		this.aiApiUrl = aiApiUrl;
	}

	@Override
	public URL getUrl() throws MalformedURLException {
		return new URL(aiApiUrl);
	}
}
