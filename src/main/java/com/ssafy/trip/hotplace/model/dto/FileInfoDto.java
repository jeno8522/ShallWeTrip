package com.ssafy.trip.hotplace.model.dto;

public class FileInfoDto {

	private String saveFolder;
	private String fileId;
	private String originalFile;
	private String saveFile;

	public String getFileId() {
		return fileId;
	}

	public void setFileId(String fileId) {
		this.fileId = fileId;
	}

	public String getSaveFolder() {
		return saveFolder;
	}
	
	public void setSaveFolder(String saveFolder) {
		this.saveFolder = saveFolder;
	}

	public String getOriginalFile() {
		return originalFile;
	}

	public void setOriginalFile(String originalFile) {
		this.originalFile = originalFile;
	}

	public String getSaveFile() {
		return saveFile;
	}

	public void setSaveFile(String saveFile) {
		this.saveFile = saveFile;
	}

}
