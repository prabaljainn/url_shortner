package com.urls.service;

public interface IUrlService {
	String findByHashedUrl(String hashedUrl);

	String findByFullUrl(String fullUrl);

	String saveHashed(String fullUrl);

}
