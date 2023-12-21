package com.blog.application.Bean;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Lob;

@Embeddable
public class EXNFileProcessingServiceBean {
	private String fileName;
	@Lob
	@Column(columnDefinition = "LONGBLOB")
	private byte[] fileContent;
	private String filteType;
	private long fileSize;
	
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public byte[] getFileContent() {
		return fileContent;
	}
	public void setFileContent(byte[] fileContent) {
		this.fileContent = fileContent;
	}
	public String getFilteType() {
		return filteType;
	}
	public void setFilteType(String filteType) {
		this.filteType = filteType;
	}
	public long getFileSize() {
		return fileSize;
	}
	public void setFileSize(long fileSize) {
		this.fileSize = fileSize;
	}
	
	
	
}
