package com.speech.up.api.etri.service;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class UrlCollector {

	final Map<ApiType, URL> aiApiUrl = new HashMap<>();
	UrlCollector(String recognized, String pronunciation) throws MalformedURLException {
		PronunciationAI pronunciationAI = new PronunciationAI(pronunciation);
		RecognizedAI recognizedAI = new RecognizedAI(recognized);
		this.aiApiUrl.put(ApiType.pronunciation, pronunciationAI.getUrl());
		this.aiApiUrl.put(ApiType.recognized, recognizedAI.getUrl());
	}
	URL getApiUrl(ApiType apiType){
		return aiApiUrl.get(apiType);
	}
}

