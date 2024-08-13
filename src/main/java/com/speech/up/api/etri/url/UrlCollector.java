package com.speech.up.api.etri.url;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import com.speech.up.api.etri.type.ApiType;

public class UrlCollector {

	final Map<ApiType, URL> aiApiUrl = new HashMap<>();
	public UrlCollector(String recognized, String pronunciation) throws MalformedURLException {
		PronunciationAI pronunciationAI = new PronunciationAI(pronunciation);
		RecognizedAI recognizedAI = new RecognizedAI(recognized);
		this.aiApiUrl.put(ApiType.PRONUNCIATION, pronunciationAI.getUrl());
		this.aiApiUrl.put(ApiType.RECOGNIZED, recognizedAI.getUrl());
	}
	public URL getApiUrl(ApiType apiType){
		return aiApiUrl.get(apiType);
	}
}

