package pt.ist.socialsoftware.softwareknowledge.service.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)

public class CategoryDTO {
	private int catId;
	private String name;

	public CategoryDTO() {

	}

	public CategoryDTO(int catId, String name) {
		this.catId = catId;
		this.name = name;
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
