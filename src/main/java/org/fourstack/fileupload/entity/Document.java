package org.fourstack.fileupload.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
@Table(name = "document")
public class Document implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5170964664203779304L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "document_name", unique = true)
	private String fileName;

	@Column(name = "document_content")
	@Lob
	private byte[] fileContent;
	
	@Column(name = "file_type")
	private String fileType;
	
	@CreationTimestamp
	@Column(name = "created_date", nullable = false, updatable = false)
	private LocalDateTime createdDate;
	
	@UpdateTimestamp
	@Column(name = "upadted_date", nullable = false, updatable = true)
	private LocalDateTime updatedDate;

	/**
	 * No argument Constructor
	 */
	public Document() {
	}

	/**
	 * Parameterized Constructor to initialize the Document Object without file type
	 * 
	 * @param fileName
	 * @param fileContent
	 */
	public Document(String fileName, byte[] fileContent) {
		this.fileName = fileName;
		this.fileContent = fileContent;
	}
	
	/**
	 * Parameterized Constructor to initialize the Document Object with file type
	 * 
	 * @param fileName
	 * @param fileContent
	 * @param fileType
	 */
	public Document(String fileName, byte[] fileContent, String fileType) {
		this.fileName = fileName;
		this.fileContent = fileContent;
		this.fileType = fileType;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public byte[] getFileContent() {
		return fileContent;
	}

	public void setFileContent(byte[] fileContent) {
		this.fileContent = fileContent;
	}

	public String getFileType() {
		return fileType;
	}

	public void setFileType(String fileType) {
		this.fileType = fileType;
	}
}
