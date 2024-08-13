package com.speech.up.api.converter;

import javax.sound.sampled.*;
import java.io.*;
import java.util.Arrays;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class WavToRaw {

	private static final Logger log = LoggerFactory.getLogger(WavToRaw.class);
	private static final AudioFormat FORMAT = new AudioFormat(16000, 16, 1, true, false);
	private static final int HEADER_SIZE = 44; // WAV 파일 헤더 크기

	public WavToRaw() {
		super();
	}

	// WAV 파일을 읽어 PCM 데이터를 바이트 배열로 반환
	public byte[] convertToRawPcm(File file) throws UnsupportedAudioFileException, IOException {
		try (AudioInputStream sourceStream = AudioSystem.getAudioInputStream(file)) {
			AudioFormat sourceFormat = sourceStream.getFormat();
			AudioFormat targetFormat = new AudioFormat(
				AudioFormat.Encoding.PCM_SIGNED,
				16000,
				16,
				1,
				2,
				16000,
				false
			);

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

				// WAV 헤더를 제거하고 PCM 데이터만 반환
				byte[] rawPcmData = byteArrayOutputStream.toByteArray();
				return formatWav2Raw(rawPcmData);
			}
		}
	}

	// WAV 헤더를 제거하고 PCM 데이터만 반환
	public byte[] formatWav2Raw(@NotNull final byte[] audioFileContent) {
		return Arrays.copyOfRange(audioFileContent, HEADER_SIZE, audioFileContent.length);
	}

	// 오디오 데이터를 바이트 배열로 읽어들임
	public byte[] AudioToByte(File file) {
		byte[] audioBytes = new byte[0];

		try (FileInputStream fileStream = new FileInputStream(file);
			 BufferedInputStream in = new BufferedInputStream(fileStream);
			 ByteArrayOutputStream out = new ByteArrayOutputStream()) {

			byte[] buffer = new byte[1024];
			int read;

			while ((read = in.read(buffer)) > 0) {
				out.write(buffer, 0, read);
			}

			audioBytes = out.toByteArray();
		} catch (FileNotFoundException ignored) {
			log.info("File not found: {}", ignored.getMessage());
		} catch (IOException ioe) {
			log.info("IO error occurred: {}", ioe.getMessage());
		}

		return audioBytes;
	}
}