package pt.ist.socialsoftware.softwareknowledge.utils.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown =true)

public class CategoryDTO {
	
	private int catId;
	private String catName;
	
	
	
	public CategoryDTO(){}


	public int getCatId() {
		return catId;
	}


	public void setCatId(int catId) {
		this.catId = catId;
	}


	public String getCatName() {
		return catName;
	}


	public void setCatName(String catName) {
		this.catName = catName;
	}



	
	

}
