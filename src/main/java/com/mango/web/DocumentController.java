package com.mango.web;

import java.io.IOException;

import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.mango.service.DocumentService;
import com.mongo.document.Document;

@RestController
@RequestMapping("/")
public class DocumentController {

	private DocumentService documentService;
	public DocumentController(final DocumentService documentService) {
		this.documentService = documentService;
	}

	@PostMapping("document/add")
	public ResponseEntity<?> addDocument(@RequestParam("file") final MultipartFile file) throws IOException {
		 return new ResponseEntity<>(documentService.addDocument(file), HttpStatus.OK);
	}

	@GetMapping("document/{id}")
	public ResponseEntity<ByteArrayResource> dowloadDocument(@PathVariable("id") String id) throws IOException {
        Document document = documentService.downloadDocument(id);

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(document.getFileType() ))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + document.getFilename() + "\"")
                .body(new ByteArrayResource(document.getFile()));
	}
}
