package com.ssafy.trip.infoBoard.model.dto;

import io.swagger.annotations.ApiParam;

public class InfoBoard {
	@ApiParam(value = "infoId", required = true)
	private int infoId;
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

	public int getInfoId() {
		return infoId;
	}

	public void setInfoId(int infoId) {
		this.infoId = infoId;
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

	@Override
	public String toString() {
		return "BoardDto [articleNo=" + infoId + ", userId=" + userId + ", title=" + title + ", content="
				+ content + ", hit=" + hit + ", registerTime=" + createDate + "]";
	}

}
