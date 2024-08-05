package com.speech.up.api.speechFlow.service;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.speech.up.customException.ResponseContentIsNullException;
import com.speech.up.customException.TaskIdIsNullException;


@Service
public class VoiceToTextSpeechFlowService {
	private static final String ENCODING = "UTF-8";
	private static final String BOUNDARY = "-----------------------------" + System.currentTimeMillis();
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

		requestHeader.put("keyId", apiKeyId);
		requestHeader.put("keySecret", apiKeySecret);

		return create(requestHeader, filePath);

	}

	HttpURLConnection connection() throws IOException {
		String createUrl = "https://api.speechflow.io/asr/file/v1/create";
		URL url = new URL(createUrl);
		int timeoutDuration = 5 * 60 * 1000;

		HttpURLConnection connection = (HttpURLConnection)url.openConnection();
		connection.setRequestProperty("accept", "text/html, application/xhtml+xml, image/jxr, */*");
		connection.setRequestProperty("user-agent",
			"Mozilla/5.0 (Windows NT 10.0; WOW64; rv:53.0) Gecko/20100101 Firefox/53.0");
		connection.setDoOutput(true);
		connection.setDoInput(true);
		connection.setRequestMethod("POST");
		connection.setConnectTimeout(timeoutDuration);
		connection.setReadTimeout(timeoutDuration);

		return connection;
	}

	String create(Map<String, String> requestHeader, String filePath) throws Exception {
		Map<String, String> params = new HashMap<>();
		Map<String, String> files = new HashMap<>();
		OutputStream out;

		params.put("lang", lang);
		files.put("file", filePath);

		HttpURLConnection connection = connection();
		for (Map.Entry<String, String> entry : requestHeader.entrySet()) {
			connection.setRequestProperty(entry.getKey(), entry.getValue());
		}

		if (files.isEmpty()) {
			throw new IllegalArgumentException("files cannot be null or empty");

		}

		connection.setRequestProperty("content-type", "multipart/form-data; boundary=" + BOUNDARY);
		out = new DataOutputStream(connection.getOutputStream());
		if (!params.isEmpty()) {
			haveFiles(params, out);
		}
		for (Map.Entry<String, String> entry : files.entrySet()) {
			String fileName = entry.getKey();
			filePath = entry.getValue();
			checkFile(out, filePath, fileName);
		}
		out.write(("--" + BOUNDARY + "--\r\n").getBytes(ENCODING));

		return getString(connection, out);
	}

	private void checkFile(OutputStream out, String filePath, String fileName) throws IOException {

		if (fileName == null || fileName.isEmpty() || filePath == null || filePath.isEmpty()) {
			return;
		}
		File file = new File(filePath);
		if (!file.exists()) {
			return;
		}
		out.write(("--" + BOUNDARY + "\r\n").getBytes(ENCODING));
		out.write(("Content-Disposition: form-data; name=\"" + fileName + "\"; filename=\"" + file.getName()
			+ "\"\r\n").getBytes(
			ENCODING));
		out.write(("Content-Type: application/x-msdownload\r\n\r\n").getBytes(ENCODING));
		try (DataInputStream in = new DataInputStream(new FileInputStream(file))) {
			int bytes;
			byte[] bufferOut = new byte[1024];
			while ((bytes = in.read(bufferOut)) != -1) {
				out.write(bufferOut, 0, bytes);
			}
		}
		out.write(("\r\n").getBytes(ENCODING));
	}

	private String getString(HttpURLConnection connection, OutputStream out) throws IOException {
		out.flush();
		BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream(), ENCODING));
		StringBuilder stringBuilder = new StringBuilder();
		String line;
		while ((line = reader.readLine()) != null) {
			stringBuilder.append(line);
		}
		out.close();
		reader.close();
		String responseContent = stringBuilder.toString();
		if (responseContent.isEmpty()) {
			throw new ResponseContentIsNullException("response content is null");
		}
		JSONObject jsonObject = new JSONObject(responseContent);
		int code = jsonObject.getInt("code");
		if (code == 10000) {
			return jsonObject.getString("taskId");
		}
		throw new ResponseContentIsNullException("response content is null");
	}

	private void haveFiles(Map<String, String> params, OutputStream out) throws IOException {
		StringBuilder sbFormData = new StringBuilder();
		for (Map.Entry<String, String> entry : params.entrySet()) {
			sbFormData.append("--").append(BOUNDARY).append("\r\n");
			sbFormData.append("Content-Disposition: form-data; name=\"").append(entry.getKey()).append("\"\r\n\r\n");
			sbFormData.append(entry.getValue()).append("\r\n");
		}
		out.write(sbFormData.toString().getBytes(ENCODING));
	}


	String queryTask(String taskId) throws Exception {
		if (taskId == null || taskId.isEmpty()) {
			throw new IllegalArgumentException("taskId cannot be null or empty");
		}
		while(true){
			String queryUrl = "https://api.speechflow.io/asr/file/v1/query";
			String responseContent = getString(taskId, queryUrl);
			if (responseContent.isEmpty()) {
				throw new ResponseContentIsNullException("response content is null");
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
				return "";
			}
		}
	}

	private String getString(String taskId, String queryUrl) throws IOException {
		String queryData = "?taskId=" + taskId + "&resultType=" + resultType;
		HttpURLConnection conn;
		BufferedReader in;

		URL url = new URL(queryUrl + queryData);
		conn = (HttpURLConnection)url.openConnection();
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

		return stringBuilder.toString();
	}

	public String getResult(String filepath) throws Exception {
		String taskId = createTask(filepath);
		if (taskId == null) {
			throw new TaskIdIsNullException("taskId is null");
		}
		return queryTask(taskId);
	}
}
