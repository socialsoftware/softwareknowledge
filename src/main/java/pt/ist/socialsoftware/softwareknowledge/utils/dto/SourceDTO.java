package pt.ist.socialsoftware.softwareknowledge.utils.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown =true)

public class SourceDTO {

	  private String author;
	  private int sourceId;
	  private String insertDate;
	  private String sourceName;
	  
	  public SourceDTO(){};
	  
	  public SourceDTO(String author, int sourceId, String insertDate, String sourceName){
		  this.author=author;
		  this.sourceId=sourceId;
		  this.insertDate=insertDate;
		  this.sourceName=sourceName;
	  }
	  public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public int getSourceId() {
		return sourceId;
	}
	public void setSourceId(int sourceId) {
		this.sourceId = sourceId;
	}
	public String getInsertDate() {
		return insertDate;
	}
	public void setInsertDate(String insertDate) {
		this.insertDate = insertDate;
	}
	public String getSourceName() {
		return sourceName;
	}
	public void setSourceName(String sourceName) {
		this.sourceName = sourceName;
	}
	
	public SourceDTO getSource(SourceDTO s){
		return s;
	}
	
}
