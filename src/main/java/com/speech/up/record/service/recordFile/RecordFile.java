package com.speech.up.record.service.recordFile;

import java.io.File;
import java.io.IOException;
import org.springframework.web.multipart.MultipartFile;

public class RecordFile {
	private final MultipartFile audioFile;
	// 파일 네이밍 형식 정해야됨
	public RecordFile(MultipartFile audioFile) {
		if (audioFile == null) {
			throw new IllegalArgumentException("file 이 존재하지 않습니다.");
		}
		this.audioFile = audioFile;
	}

	public String createFile(final String directoryPath) throws IOException {
		final String BASE_DIRECTORY = "uploads/data";
		final String fullDirectoryPath = directoryPath + File.separator + BASE_DIRECTORY;
		makeDirectory(fullDirectoryPath);
		File destFile = new File(fullDirectoryPath + File.separator + audioFile.getOriginalFilename());
		audioFile.transferTo(destFile);
		return fullDirectoryPath + File.separator + audioFile.getOriginalFilename();
	}

	private void makeDirectory(final String directoryPath) throws IOException {
		File directory = new File(directoryPath);
		if (directory.exists()) {
			return;
		}
		if(directory.mkdir()){
			throw new IOException("디렉토리를 생성할 수 없습니다: " + directoryPath);
		}
	}

}
