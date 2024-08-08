package com.speech.up.script.service.recordFile;

import com.dropbox.core.DbxRequestConfig;
// import com.dropbox.core.DbxClientV2;
import com.dropbox.core.DbxException;
import com.dropbox.core.v2.DbxClientV2;
import com.dropbox.core.v2.files.FileMetadata;
import com.dropbox.core.v2.files.UploadErrorException;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class DropboxUploadService {

	private final DbxClientV2 client;

	public DropboxUploadService(String accessToken) {
		DbxRequestConfig config = DbxRequestConfig.newBuilder("dropbox/java-tutorial").build();
		this.client = new DbxClientV2(config, accessToken);
	}

	public String uploadFile(String localFilePath, String dropboxPath) throws IOException, DbxException {
		try (InputStream in = new FileInputStream(localFilePath)) {
			FileMetadata metadata = client.files().uploadBuilder(dropboxPath)
				.uploadAndFinish(in);
			return metadata.getPathLower();
		} catch (UploadErrorException e) {
			System.err.println("Upload failed: " + e.getMessage());
			throw e;
		}
	}
}
