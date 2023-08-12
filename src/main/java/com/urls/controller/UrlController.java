package com.urls.controller;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.urls.model.UrlRequest;
import com.urls.service.IUrlService;

@RestController
public class UrlController {

	@Autowired
	private IUrlService iUrlService;

	@GetMapping("/{hashedUrl}")
	public ResponseEntity<Void> findByFullUrl(@PathVariable String hashedUrl) {
		String redirectUrl =  iUrlService.findByHashedUrl(hashedUrl);
        return ResponseEntity.status(HttpStatus.FOUND).location(URI.create(redirectUrl)).build();

		
	}

	@GetMapping("/")
	public String home() {
		return "Hello World!";
	}
	
	@PostMapping("/")
	public ResponseEntity<String> saveHashed(@RequestBody UrlRequest urlRequest) {
	    String hashed = iUrlService.saveHashed(urlRequest.getFullUrl());
	    if (hashed == null) {
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error processing the URL");
	    }
	    return ResponseEntity.ok(hashed);
	}
	
	

}
