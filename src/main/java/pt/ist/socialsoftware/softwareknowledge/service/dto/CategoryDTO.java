package pt.ist.socialsoftware.softwareknowledge.service.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)

public class CategoryDTO {
	private int catId;
	private String name;
	private String parent;
	private int parentId;

	
	public CategoryDTO(){}
	
	public CategoryDTO(int catId, String name, int parentId) {
		this.catId = catId;
		this.name = name;
		this.parentId = parentId;
	}
	
	
	public int getParentId() {
		return parentId;
	}

	public void setParentId(int parentId) {
		this.parentId = parentId;
	}

	public String getParent() {
		return parent;
	}

	public void setParent(String parent) {
		this.parent = parent;
	}


	public int getCatId() {
		return catId;
	}

	public void setCatId(int catId) {
		this.catId = catId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
