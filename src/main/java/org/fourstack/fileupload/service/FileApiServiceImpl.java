package org.fourstack.fileupload.service;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Optional;

import org.fourstack.fileupload.entity.Document;
import org.fourstack.fileupload.exceptionhandling.FileStorageException;
import org.fourstack.fileupload.exceptionhandling.ResourceFileNotFoundException;
import org.fourstack.fileupload.property.FileStorageProperties;
import org.fourstack.fileupload.repository.DocumentRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FileApiServiceImpl implements FileApiService {
	private static final Logger logger = LoggerFactory.getLogger(FileApiServiceImpl.class);
	
	private final Path fileStorageLocation;
	
	public FileApiServiceImpl(FileStorageProperties properties) {
		this.fileStorageLocation = Paths.get(properties.getUploadDir()).toAbsolutePath().normalize();

		try {
			Files.createDirectories(this.fileStorageLocation);
		} catch (IOException e) {
			logger.error("Could not create the directory where the uploaded files will be stored", e);
		}
	}

	@Autowired
	private DocumentRepository documentRepository;

	@Override
	public String uploadFileToDatabase(MultipartFile file) {
		logger.debug("FileApiServiceImpl: Start of uploadFileToDatabase() method"); 

		// fetch the file name
		String fileName = StringUtils.cleanPath(file.getOriginalFilename());
		try {
			if (fileName.contains("..")) {
				throw new FileStorageException("Sorry, File name contains invalid path sequence");
			}

			Document document = new Document();
			document.setFileName(fileName);
			document.setFileContent(file.getBytes());

			documentRepository.save(document);
			logger.debug("File saved successfully..");
		} catch (IOException e) {
			logger.error("IOException occurred while uploading the file to database : " + fileName, e);
		}
		return fileName;
	}

	@Override
	public Document downloadFileFromDatabase(String fileName) {
		Optional<Document> optionalDocument = documentRepository.findByFileName(fileName);
		Document document = optionalDocument.isPresent() ? optionalDocument.get() : null;
		
		if (document == null)
			throw new ResourceFileNotFoundException("Requested file not present in the database: "+fileName);
		return document;
	}

	@Override
	public String uploadFileToLocalFileSystem(MultipartFile file) {
		String fileName = StringUtils.cleanPath(file.getOriginalFilename());
		try {
			if (fileName.contains("..")) {
				throw new FileStorageException("Sorry, File name contains invalid path sequence");
			}

			Path targetLocation = this.fileStorageLocation.resolve(fileName);
			Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);
			return fileName;
		} catch (IOException e) {
			logger.error("Not able to create the File in the target location", e);
			throw new FileStorageException("Could not store file", e);
		}
	}

	@Override
	public Resource downloadFileFromLocalSystem(String fileName) {
		try {
			Path filePath = this.fileStorageLocation.resolve(fileName).normalize();
			Resource resource = new UrlResource(filePath.toUri());
			
			if (resource.exists()) {
				return resource;
			} else {
				throw new ResourceFileNotFoundException("File Not Found: "+fileName);
			}
		} catch (MalformedURLException e) {
			throw new ResourceFileNotFoundException("File Not Found: "+fileName, e);
		}
	}

}
