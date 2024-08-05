package com.speech.up.script.service.recordFile;

import java.io.File;
import java.io.IOException;
import org.springframework.web.multipart.MultipartFile;

import com.speech.up.customException.CustomIllegalAccessException;
import com.speech.up.customException.CustomIoException;

public class RecordFile {
	private final MultipartFile audioFile;

	// 파일 네이밍 형식 정해야됨
	public RecordFile(MultipartFile audioFile) {
		if (audioFile == null) {
			throw new CustomIllegalAccessException("file 이 존재하지 않습니다.");
		}
		this.audioFile = audioFile;
	}

	public void createFile(final String directoryPath) throws CustomIoException {
		final String BASE_DIRECTORY = "uploads/data";
		final String fullDirectoryPath = directoryPath + File.separator + BASE_DIRECTORY;
		makeDirectory(fullDirectoryPath);
		File destFile = new File(fullDirectoryPath + File.separator + audioFile.getOriginalFilename());
		try{
			audioFile.transferTo(destFile);
		}catch (IOException ioException){
			throw new CustomIoException(ioException.getMessage());
		}

	}

	private void makeDirectory(final String directoryPath) throws CustomIoException {
		File directory = new File(directoryPath);
		if (directory.exists()) {
			return;
		}
		if(directory.mkdir()){
			throw new CustomIoException("디렉토리를 생성할 수 없습니다: " + directoryPath);
		}
	}

}
