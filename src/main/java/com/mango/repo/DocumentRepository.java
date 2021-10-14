package com.mango.repo;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.mongo.document.Document;

public interface DocumentRepository  extends MongoRepository<Document, String>{

}
