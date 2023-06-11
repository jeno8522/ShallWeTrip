package com.ssafy.trip.attraction.model.dto;

import java.io.Serializable;

public class Attraction implements Serializable, Comparable<Attraction>{
	private final double MULTI_CAMPUS_LATITUDE = 37.500613; 
	private final double MULTI_CAMPUS_LONGITUDE = 127.036431; 
	private String contentId;
	private String contentTypeId;
	private String title;
	private String addr1;
	private String addr2;
	private String tel;
	private String image;
	private String latitude;
	private String longitude;
	private String homepage;
	private String overview;
	private double distanceFromMC;
	
	public Attraction() {
		super();
	}
	
	public Attraction(String contentId, String contentTypeId, String title, String addr1, String addr2, String tel,
			String image, String latitude, String longitude, String homepage, String overview) {
		super();
		this.contentId = contentId;
		this.contentTypeId = contentTypeId;
		this.title = title;
		this.addr1 = addr1;
		this.addr2 = addr2;
		this.tel = tel;
		this.image = image;
		this.latitude = latitude;
		this.longitude = longitude;
		this.homepage = homepage;
		this.overview = overview;
		this.distanceFromMC = getManhattanDistance(MULTI_CAMPUS_LATITUDE, MULTI_CAMPUS_LONGITUDE, 
				Double.parseDouble(latitude), Double.parseDouble(longitude));
	}
	public String getContentId() {
		return contentId;
	}
	public void setContentId(String contentId) {
		this.contentId = contentId;
	}
	public String getContentTypeId() {
		return contentTypeId;
	}
	public void setContentTypeId(String contentTypeId) {
		this.contentTypeId = contentTypeId;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getAddr1() {
		return addr1;
	}
	public void setAddr1(String addr1) {
		this.addr1 = addr1;
	}
	public String getAddr2() {
		return addr2;
	}
	public void setAddr2(String addr2) {
		this.addr2 = addr2;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public String getLatitude() {
		return latitude;
	}
	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}
	public String getLongitude() {
		return longitude;
	}
	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}
	public String getHomepage() {
		return homepage;
	}
	public void setHomepage(String homepage) {
		this.homepage = homepage;
	}
	public String getOverview() {
		return overview;
	}
	public void setOverview(String overview) {
		this.overview = overview;
	}
	
	@Override
	public int compareTo(Attraction o) {
		return Double.compare(distanceFromMC, o.distanceFromMC);
	}
	
	private double getManhattanDistance(double lati1, double long1, double lati2, double long2) {
		return Math.abs(lati1-lati2) + Math.abs(long1 - long2);
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Attraction [contentId=").append(contentId).append(", contentTypeId=").append(contentTypeId)
				.append(", title=").append(title).append(", addr1=").append(addr1).append(", addr2=").append(addr2)
				.append(", tel=").append(tel).append(", image=").append(image).append(", latitude=").append(latitude)
				.append(", longitude=").append(longitude).append(", homepage=").append(homepage).append(", overview=")
				.append(overview).append("]");
		return builder.toString();
	}




}
