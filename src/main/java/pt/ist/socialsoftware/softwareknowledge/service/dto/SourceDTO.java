package pt.ist.socialsoftware.softwareknowledge.service.dto;

import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)

public class SourceDTO {

	private String author;
	private int sourceId;
	private String insertDate;
	private String name;
	private String link;
	private Set<CategoryDTO> catList;

	public SourceDTO() {
	};

	public SourceDTO(String author, int sourceId, String insertDate, String name, String link) {
		this.author = author;
		this.sourceId = sourceId;
		this.insertDate = insertDate;
		this.name = name;
		this.link = link;
	}
	
	public SourceDTO(String author, int sourceId, String name, String link, Set<CategoryDTO> catList){
		this.author = author;
		this.sourceId = sourceId;
		this.name = name;
		this.link = link;
		this.catList = catList;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public SourceDTO getSource(SourceDTO s) {
		return s;
	}

	public Set<CategoryDTO> getCatList() {
		return catList;
	}

	public void setCatList(Set<CategoryDTO> catList) {
		this.catList = catList;
	}
}
