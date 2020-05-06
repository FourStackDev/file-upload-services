package org.fourstack.fileupload.payload;

/**
 * Class <b><i>UploadFileResponse</i></b> is used a Response class for the end
 * api's. The Response will contain the details of file that has been uploaded
 * 
 * @author Manjunath_HM
 *
 */
public class UploadFileResponse {
	private String fileName;
	private String fileDownloadUri;
	private String fileType;
	private long size;

	/**
	 * No argument Constructor
	 */
	public UploadFileResponse() {
	}

	/**
	 * Parameterized Constructor to initialize the UploadFileResponse.
	 * 
	 * @param fileName        Name of the file
	 * @param fileDownloadUri Download URI from where the uploaded file can be
	 *                        downloaded
	 * @param fileType        Type of the file
	 * @param size            Size of the uploaded file
	 */
	public UploadFileResponse(String fileName, String fileDownloadUri, String fileType, long size) {
		this.fileName = fileName;
		this.fileDownloadUri = fileDownloadUri;
		this.fileType = fileType;
		this.size = size;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getFileDownloadUri() {
		return fileDownloadUri;
	}

	public void setFileDownloadUri(String fileDownloadUri) {
		this.fileDownloadUri = fileDownloadUri;
	}

	public String getFileType() {
		return fileType;
	}

	public void setFileType(String fileType) {
		this.fileType = fileType;
	}

	public long getSize() {
		return size;
	}

	public void setSize(long size) {
		this.size = size;
	}
}
