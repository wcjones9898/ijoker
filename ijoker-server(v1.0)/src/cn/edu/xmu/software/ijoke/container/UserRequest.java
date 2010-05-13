package cn.edu.xmu.software.ijoke.container;

public class UserRequest {

	private String synchronizationTicket;
	private String fileName;
	private String fileExtensition;
	public String getSynchronizationTicket() {
		return synchronizationTicket;
	}
	public void setSynchronizationTicket(String synchronizationTicket) {
		this.synchronizationTicket = synchronizationTicket;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public String getFileExtensition() {
		return fileExtensition;
	}
	public void setFileExtensition(String fileExtensition) {
		this.fileExtensition = fileExtensition;
	}

	
	
}
