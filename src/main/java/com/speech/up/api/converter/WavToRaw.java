package com.speech.up.api.converter;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Optional;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.UnsupportedAudioFileException;

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

	// WAV 파일을 읽어 PCM 데이터를 Optional<byte[]>로 반환
	public Optional<byte[]> convertToRawPcm(MultipartFile multipartFile) {
		Optional<File> tempFile = createTempFile(multipartFile);
		if (tempFile.isEmpty()) {
			return Optional.empty(); // 파일 생성 실패 시 빈 Optional 반환
		}

		Optional<byte[]> pcmData = processAudioFile(tempFile.get());
		deleteTempFile(tempFile.get());

		return pcmData;
	}

	// MultipartFile을 임시 파일로 변환
	private Optional<File> createTempFile(MultipartFile multipartFile) {
		try {
			File tempFile = File.createTempFile("uploaded-", ".wav");
			multipartFile.transferTo(tempFile); // MultipartFile을 임시 파일로 저장
			return Optional.of(tempFile);
		} catch (IOException e) {
			log.error("임시 파일 생성 실패: {}", e.getMessage());
			return Optional.empty(); // 파일 생성 실패 시 빈 Optional 반환
		}
	}

	// 임시 파일 삭제
	private void deleteTempFile(File tempFile) {
		if (!fileExists(tempFile)) {
			return; // 파일이 존재하지 않으면 조기 리턴
		}

		if (!tempFile.delete()) {
			log.warn("임시 파일 삭제 실패: {}", tempFile.getPath());
		}
	}

	// 파일 존재 여부 확인
	private boolean fileExists(File file) {
		return file != null && file.exists();
	}

	// 파일을 처리하여 PCM 데이터로 변환
	private Optional<byte[]> processAudioFile(File file) {
		Optional<AudioInputStream> sourceStream = getAudioInputStream(file);
		if (sourceStream.isEmpty()) {
			return Optional.empty(); // AudioInputStream 생성 실패 시 빈 Optional 반환
		}

		Optional<AudioInputStream> convertedStream = convertAudioStream(sourceStream.get());
		if (convertedStream.isEmpty()) {
			return Optional.empty(); // AudioStream 변환 실패 시 빈 Optional 반환
		}

		return readPcmData(convertedStream.get());
	}

	// 오디오 파일을 AudioInputStream으로 가져오기
	private Optional<AudioInputStream> getAudioInputStream(File file) {
		try {
			return Optional.of(AudioSystem.getAudioInputStream(file));
		} catch (UnsupportedAudioFileException | IOException e) {
			log.error("오디오 스트림 생성 실패: {}", e.getMessage());
			return Optional.empty(); // AudioInputStream 생성 실패 시 빈 Optional 반환
		}
	}

	// 소스 포맷을 변환 가능 여부 확인 및 변환
	private Optional<AudioInputStream> convertAudioStream(AudioInputStream sourceStream) {
		AudioFormat sourceFormat = sourceStream.getFormat();

		if (!AudioSystem.isConversionSupported(FORMAT, sourceFormat)) {
			log.error("변환할 수 없는 오디오 포맷: {}", sourceFormat);
			return Optional.empty(); // 변환 불가 시 빈 Optional 반환
		}

		return Optional.of(AudioSystem.getAudioInputStream(FORMAT, sourceStream));
	}

	// PCM 데이터를 읽고 반환
	private Optional<byte[]> readPcmData(AudioInputStream convertedStream) {
		try (ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream()) {
			byte[] buffer = new byte[1024];
			int bytesRead;
			while ((bytesRead = convertedStream.read(buffer)) != -1) {
				byteArrayOutputStream.write(buffer, 0, bytesRead);
			}

			byte[] rawPcmData = byteArrayOutputStream.toByteArray();
			return Optional.of(formatWav2Raw(rawPcmData)); // PCM 데이터로 변환
		} catch (IOException e) {
			log.error("PCM 데이터 읽기 실패: {}", e.getMessage());
			return Optional.empty(); // PCM 데이터 읽기 실패 시 빈 Optional 반환
		}
	}

	// WAV 헤더를 제거하고 PCM 데이터만 반환
	private byte[] formatWav2Raw(@NotNull final byte[] audioFileContent) {
		return Arrays.copyOfRange(audioFileContent, HEADER_SIZE, audioFileContent.length);
	}
}
