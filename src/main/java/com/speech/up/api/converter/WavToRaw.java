package com.speech.up.api.converter;

import javax.sound.sampled.*;
import java.io.*;
import java.util.Arrays;
import org.springframework.web.multipart.MultipartFile;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Getter
public class WavToRaw {

	private static final AudioFormat FORMAT = new AudioFormat(16000, 16, 1, true, false);
	private static final int HEADER_SIZE = 44; // WAV 파일 헤더 크기

	public WavToRaw() {
		super();
	}

	// WAV 파일을 읽어 PCM 데이터를 바이트 배열로 반환
	public byte[] convertToRawPcm(MultipartFile multipartFile) throws UnsupportedAudioFileException, IOException {
		File file = File.createTempFile("uploaded-", ".wav");
		// MultipartFile 데이터를 임시 파일로 저장
		multipartFile.transferTo(file);
		try (AudioInputStream sourceStream = AudioSystem.getAudioInputStream(file)) {
			AudioFormat sourceFormat = sourceStream.getFormat();
			AudioFormat targetFormat = FORMAT;

			if (!AudioSystem.isConversionSupported(targetFormat, sourceFormat)) {
				throw new UnsupportedAudioFileException("The source format is not supported.");
			}

			try (AudioInputStream convertedStream = AudioSystem.getAudioInputStream(targetFormat, sourceStream);
				 ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream()) {

				byte[] buffer = new byte[1024];
				int bytesRead;
				while ((bytesRead = convertedStream.read(buffer)) != -1) {
					byteArrayOutputStream.write(buffer, 0, bytesRead);
				}

				byte[] rawPcmData = byteArrayOutputStream.toByteArray();
				return formatWav2Raw(rawPcmData);
			}
		}
	}

	// WAV 헤더를 제거하고 PCM 데이터만 반환
	private byte[] formatWav2Raw(@NotNull final byte[] audioFileContent) {
		return Arrays.copyOfRange(audioFileContent, HEADER_SIZE, audioFileContent.length);
	}
}