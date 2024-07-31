package com.speech.up.api.voiceToText.speechFlow.service;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

@Service
public class VoiceToTextSpeechFlowService {

	@Value(value = "${speech-flow.api.key.id}")
	private String apiKeyId;

	@Value(value = "${speech-flow.api.key.secret}")
	private String apiKeySecret;

	@Value(value = "${speech-flow.lang}")
	private String lang;

	@Value(value = "${speech-flow.result.type}")
	private int resultType;

	String createTask(String filePath) throws Exception {
		if (filePath == null || filePath.isEmpty()) {
			throw new IllegalArgumentException("filePath cannot be null or empty");
		}

		Map<String, String> requestHeader = new HashMap<>();
		boolean isRemote = filePath.startsWith("http");
		requestHeader.put("keyId", apiKeyId);
		requestHeader.put("keySecret", apiKeySecret);

		Map<String, String> params = new HashMap<>();
		params.put("lang", lang);
		Map<String, String> files = new HashMap<>();
		if (isRemote) {
			params.put("remotePath", filePath);
		} else {
			files.put("file", filePath);
		}
		return create(requestHeader, params, files);
	}

	String create(Map<String, String> requestHeader, Map<String, String> params, Map<String, String> files) throws Exception {
		String createUrl = "https://api.speechflow.io/asr/file/v1/create";
		URL url = new URL(createUrl);
		HttpURLConnection connection = (HttpURLConnection) url.openConnection();
		connection.setRequestProperty("accept", "text/html, application/xhtml+xml, image/jxr, */*");
		connection.setRequestProperty("user-agent", "Mozilla/5.0 (Windows NT 10.0; WOW64; rv:53.0) Gecko/20100101 Firefox/53.0");
		connection.setDoOutput(true);
		connection.setDoInput(true);
		connection.setRequestMethod("POST");
		int timeoutDuration = 5 * 60 * 1000;
		connection.setConnectTimeout(timeoutDuration);
		connection.setReadTimeout(timeoutDuration);
		String requestEncoding = "UTF-8";
		String responseEncoding = "UTF-8";

		for (Map.Entry<String, String> entry : requestHeader.entrySet()) {
			connection.setRequestProperty(entry.getKey(), entry.getValue());
		}

		OutputStream out;
		if (files == null || files.isEmpty()) {
			connection.setRequestProperty("content-type", "application/x-www-form-urlencoded");
			out = new DataOutputStream(connection.getOutputStream());
			if (params != null && !params.isEmpty()) {
				StringBuilder formData = new StringBuilder();
				for (Map.Entry<String, String> entry : params.entrySet()) {
					formData.append(entry.getKey()).append("=").append(entry.getValue()).append("&");
				}
				formData.delete(formData.length() - 1, formData.length());
				out.write(formData.toString().getBytes(requestEncoding));
			}
		} else {
			String boundary = "-----------------------------" + System.currentTimeMillis();
			connection.setRequestProperty("content-type", "multipart/form-data; boundary=" + boundary);
			out = new DataOutputStream(connection.getOutputStream());
			if (params != null && !params.isEmpty()) {
				StringBuilder sbFormData = new StringBuilder();
				for (Map.Entry<String, String> entry : params.entrySet()) {
					sbFormData.append("--").append(boundary).append("\r\n");
					sbFormData.append("Content-Disposition: form-data; name=\"").append(entry.getKey()).append("\"\r\n\r\n");
					sbFormData.append(entry.getValue()).append("\r\n");
				}
				out.write(sbFormData.toString().getBytes(requestEncoding));
			}
			for (Map.Entry<String, String> entry : files.entrySet()) {
				String fileName = entry.getKey();
				String filePath = entry.getValue();
				if (fileName == null || fileName.isEmpty() || filePath == null || filePath.isEmpty()) {
					continue;
				}
				File file = new File(filePath);
				if (!file.exists()) {
					continue;
				}
				out.write(("--" + boundary + "\r\n").getBytes(requestEncoding));
				out.write(("Content-Disposition: form-data; name=\"" + fileName + "\"; filename=\"" + file.getName() + "\"\r\n").getBytes(requestEncoding));
				out.write(("Content-Type: application/x-msdownload\r\n\r\n").getBytes(requestEncoding));
				try (DataInputStream in = new DataInputStream(new FileInputStream(file))) {
					int bytes;
					byte[] bufferOut = new byte[1024];
					while ((bytes = in.read(bufferOut)) != -1) {
						out.write(bufferOut, 0, bytes);
					}
				}
				out.write(("\r\n").getBytes(requestEncoding));
			}
			out.write(("--" + boundary + "--\r\n").getBytes(requestEncoding));
		}
		out.flush();
		BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream(), responseEncoding));
		StringBuilder stringBuilder = new StringBuilder();
		String line;
		while ((line = reader.readLine()) != null) {
			stringBuilder.append(line);
		}
		out.close();
		reader.close();
		String responseContent = stringBuilder.toString();
		if (responseContent.isEmpty()) {
			throw new RuntimeException("Response content is empty or null");
		}
		JSONObject jsonObject = new JSONObject(responseContent);
		int code = jsonObject.getInt("code");
		if (code == 10000) {
			return jsonObject.getString("taskId");
		} else {
			System.out.println("create error: " + jsonObject.getString("msg"));
			return null;
		}
	}

	String queryTask(String taskId) throws Exception {
		if (taskId == null || taskId.isEmpty()) {
			throw new IllegalArgumentException("taskId cannot be null or empty");
		}

		System.out.println("querying transcription result");
		String queryUrl = "https://api.speechflow.io/asr/file/v1/query";
		String queryData = "?taskId=" + taskId + "&resultType=" + resultType;
		HttpURLConnection conn;
		BufferedReader in;
		while (true) {
			URL url = new URL(queryUrl + queryData);
			conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.setRequestProperty("keyId", apiKeyId);
			conn.setRequestProperty("keySecret", apiKeySecret);
			conn.setDoOutput(false);
			in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			StringBuilder stringBuilder = new StringBuilder();
			String inputLine;
			while ((inputLine = in.readLine()) != null) {
				stringBuilder.append(inputLine);
			}
			in.close();

			String responseContent = stringBuilder.toString();
			if (responseContent.isEmpty()) {
				throw new RuntimeException("Response content is empty or null");
			}
			JSONObject jsonObject = new JSONObject(responseContent);

			int code = jsonObject.getInt("code");
			if (code == 11000) {
				return jsonObject.getString("result");
			} else if (code == 11001) {
				System.out.println("waiting");
				Thread.sleep(3000);
			} else {
				System.out.println(responseContent);
				System.out.println("transcription error: " + jsonObject.getString("msg"));
				return null;
			}
		}
	}

	public String getResult(String filepath) throws Exception {

		String taskId = createTask(filepath);
		if (taskId == null) {
			throw new RuntimeException("taskId is null");
		}
		return queryTask(taskId);
	}
}
