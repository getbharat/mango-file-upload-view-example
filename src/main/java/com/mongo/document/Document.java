package com.mongo.document;

import org.springframework.data.annotation.Id;

@org.springframework.data.mongodb.core.mapping.Document
public class Document {

	@Id
	private String id;
	private String filename;
	private String fileType;
	private String fileSize;
	private byte[] file;
	
	public Document() {}

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	public String getFileType() {
		return fileType;
	}

	public void setFileType(String fileType) {
		this.fileType = fileType;
	}

	public String getFileSize() {
		return fileSize;
	}

	public void setFileSize(String fileSize) {
		this.fileSize = fileSize;
	}

	public byte[] getFile() {
		return file;
	}

	public void setFile(byte[] file) {
		this.file = file;
	}

	public String getId() {
		return id;
	}
    
	
}
