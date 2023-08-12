package com.urls.service;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.urls.model.UrlModel;
import com.urls.repository.UrlRepository;

@Service
public class UrlServiceImpl implements IUrlService {

	@Autowired
	private UrlRepository urlRepository;

	@Override
	public String findByHashedUrl(String hashedUrl) {

		var x = urlRepository.findById(hashedUrl);
		if (x.isEmpty())
			return "www.youtube.com/";
		else
			return x.get().getFullUrl();
	}

	@Override
	public String findByFullUrl(String fullUrl) {

		var x = urlRepository.findByFullUrl(fullUrl);

		if (x.isEmpty())
			return "www.youtube.com/";
		else
			return x.get(0).getHashedUrl();
	}

	@Override
	public String saveHashed(String fullUrl) {
		try {
			MessageDigest md = MessageDigest.getInstance("SHA-256");
			byte[] hashInBytes = md.digest(fullUrl.getBytes(StandardCharsets.UTF_8));

			StringBuilder sb = new StringBuilder();
			for (byte b : hashInBytes) {
				sb.append(String.format("%02x", b));
			}
			String hashed = sb.toString().substring(0, 8);

			// Check if URL is already in the database
			List<UrlModel> existingUrl = urlRepository.findByFullUrl(fullUrl);
			if (!existingUrl.isEmpty()) {
				return existingUrl.get(0).getHashedUrl();
			}

			UrlModel url = new UrlModel(fullUrl, hashed);
			urlRepository.save(url);
			return hashed;

		} catch (NoSuchAlgorithmException e) {
			// Logging and other error handling can go here
			return null;
		}
	}

}
