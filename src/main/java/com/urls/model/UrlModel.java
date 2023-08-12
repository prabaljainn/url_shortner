package com.urls.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class UrlModel {

	@Id
	private String hashedUrl;
	private String fullUrl;

	public UrlModel() {
		super();
	}

	public UrlModel(String fullUrl, String hashedUrl) {
		super();
		this.fullUrl = fullUrl;
		this.hashedUrl = hashedUrl;
	}

	public String getFullUrl() {
		return fullUrl;
	}

	public void setFullUrl(String fullUrl) {
		this.fullUrl = fullUrl;
	}

	public String getHashedUrl() {
		return hashedUrl;
	}

	public void setHashedUrl(String hashedUrl) {
		this.hashedUrl = hashedUrl;
	}

	@Override
	public String toString() {
		return "UrlModel [fullUrl=" + fullUrl + ", hashedUrl=" + hashedUrl + "]";
	}

}
