package com.ssafy.trip.hotplace.model.dto;

import java.util.Arrays;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import io.swagger.annotations.ApiParam;

public class HotPlace {
	@ApiParam(value = "hotId", required = true)
	private int hotId;
	@ApiParam(value = "userId", required = true)
	private String userId;
	@ApiParam(value = "title", required = true)
	private String title;
	@ApiParam(value = "content", required = true)
	private String content;
	@ApiParam(value = "hit", required = true)
	private int hit;
	@ApiParam(value = "createDate", required = true)
	private String createDate;
	
	private List<FileInfoDto> fileInfos;
	private MultipartFile[] fileup;
	
	
	public int getHotId() {
		return hotId;
	}
	public void setHotId(int hotId) {
		this.hotId = hotId;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public int getHit() {
		return hit;
	}
	public void setHit(int hit) {
		this.hit = hit;
	}
	public String getCreateDate() {
		return createDate;
	}
	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}
	
	
	public MultipartFile[] getFileup() {
		return fileup;
	}
	public void setFileup(MultipartFile[] fileup) {
		this.fileup = fileup;
	}
	public List<FileInfoDto> getFileInfos() {
		return fileInfos;
	}
	public void setFileInfos(List<FileInfoDto> fileInfos) {
		this.fileInfos = fileInfos;
	}
	@Override
	public String toString() {
		return "HotPlace [hotId=" + hotId + ", userId=" + userId + ", title=" + title + ", content=" + content
				+ ", hit=" + hit + ", createDate=" + createDate + ", fileInfos=" + fileInfos + ", fileup="
				+ Arrays.toString(fileup) + "]";
	}



}
