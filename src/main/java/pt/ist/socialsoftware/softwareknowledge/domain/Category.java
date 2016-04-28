package pt.ist.socialsoftware.softwareknowledge.domain;

import pt.ist.socialsoftware.softwareknowledge.ontology.OntologyInterface;
import pt.ist.socialsoftware.softwareknowledge.service.dto.CategoryDTO;

public class Category {
	private SoftwareKnowledge softwareKnowledge;
	private int catId;
	private String name;

	public Category(SoftwareKnowledge softwareKnowledge, int catId, String name) {
		setSoftwareKnowledge(softwareKnowledge);
		setCatId(catId);
		setName(name);
		softwareKnowledge.addCategory(this);
		OntologyInterface.getInstance().addCategory(this);
	}

	public SoftwareKnowledge getSoftwareKnowledge() {
		return softwareKnowledge;
	}

	public void setSoftwareKnowledge(SoftwareKnowledge softwareKnowledge) {
		this.softwareKnowledge = softwareKnowledge;
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

	public CategoryDTO getDTO() {
		return new CategoryDTO(getCatId(), getName());
	}
}
