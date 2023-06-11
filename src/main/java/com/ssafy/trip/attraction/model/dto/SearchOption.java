package com.ssafy.trip.attraction.model.dto;

import io.swagger.annotations.ApiParam;

public class SearchOption {
	@ApiParam(defaultValue = "")
	String sido;
	@ApiParam(defaultValue = "")
	String gugun;
	@ApiParam(defaultValue = "")
	String contentTypeId;
	@ApiParam(defaultValue = "")
	String keyword;
	
	
	public SearchOption(String sido, String gugun, String contentTypeId, String keyword) {
		super();
		this.sido = sido;
		this.gugun = gugun;
		this.contentTypeId = contentTypeId;
		this.keyword = keyword;
	}
	
	public String getSido() {
		return sido;
	}
	public void setSido(String sido) {
		this.sido = sido;
	}
	public String getGugun() {
		return gugun;
	}
	public void setGugun(String gugun) {
		this.gugun = gugun;
	}
	public String getContentTypeId() {
		return contentTypeId;
	}
	public void setContentTypeId(String contentTypeId) {
		this.contentTypeId = contentTypeId;
	}
	public String getKeyword() {
		return keyword;
	}
	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("SearchOption [sido=").append(sido).append(", gugun=").append(gugun).append(", contentTypeId=")
				.append(contentTypeId).append(", keyword=").append(keyword).append("]");
		return builder.toString();
	}
	
	
}
