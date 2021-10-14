package com.mango.service;

import java.io.IOException;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.gridfs.GridFsOperations;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.mango.repo.DocumentRepository;
import com.mongo.document.Document;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.mongodb.client.gridfs.model.GridFSFile;

@Service
public class DocumentService {

	@Autowired
	DocumentRepository documentRepository;

	@Autowired
	private GridFsTemplate template;

	@Autowired
	private GridFsOperations operations;

	public String addDocument(final MultipartFile file) throws IOException {
		
        DBObject metadata = new BasicDBObject();
        metadata.put("fileSize", file.getSize());
        Object fileID = template.store(file.getInputStream(), file.getOriginalFilename(), file.getContentType(), metadata);
        return fileID.toString();

	}

	public Document downloadDocument(final String id) throws IllegalStateException, IOException {
		GridFSFile gridFSFile = template.findOne(new Query(Criteria.where("_id").is(id)));

		Document document = new Document();

		if (gridFSFile != null && gridFSFile.getMetadata() != null) {
			document.setFilename(gridFSFile.getFilename());
			document.setFileType(gridFSFile.getMetadata().getString("_contentType").toString());
			document.setFileSize(gridFSFile.getMetadata().get("fileSize").toString());
			document.setFile(IOUtils.toByteArray(operations.getResource(gridFSFile).getInputStream()));
		}
		return document;
	}
}