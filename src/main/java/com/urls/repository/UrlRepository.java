package com.urls.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.urls.model.UrlModel;
import java.util.List;

@Repository
public interface UrlRepository extends MongoRepository<UrlModel, String> {

	List<UrlModel> findByFullUrl(String fullUrl);

}
