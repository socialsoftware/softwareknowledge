package pt.ist.socialsoftware.softwareknowledge.domain;

import java.util.HashSet;
import java.util.Set;

import pt.ist.socialsoftware.softwareknowledge.ontology.OntologyInterface;
import pt.ist.socialsoftware.softwareknowledge.service.dto.CategoryDTO;

public class Category {
	private SoftwareKnowledge softwareKnowledge;
	private int catId;
	private String name;
	private Set<Category> subCategorySet;
	private Category parent;
	private Set<Source> sourceWithCatSet;
	private static int catIdCounter = 1;

	public Category(SoftwareKnowledge softwareKnowledge, String name, Category parent) {

		setSoftwareKnowledge(softwareKnowledge);
		setCatId(Category.catIdCounter++);
		setName(name);
		softwareKnowledge.addCategory(this);
		subCategorySet = new HashSet<Category>();
		sourceWithCatSet = new HashSet<Source>();
		OntologyInterface.getInstance().addCategory(this);
		if (parent != null) {
			parent.addSub(this);
		} else {
			this.parent = null;
		}

	}

	public Category getParent() {
		return parent;
	}

	public void setParent(Category parent) {
		this.parent = parent;
	}

	public Set<Category> getSubCategorySet() {
		return subCategorySet;
	}

	public void setSubCategorySet(Set<Category> subCategorySet) {
		this.subCategorySet = subCategorySet;
	}

	public Category(SoftwareKnowledge softwareknowledge, int catId) {
		setSoftwareKnowledge(softwareKnowledge);
		setCatId(catId);
		softwareKnowledge.addCategory(this);
		OntologyInterface.getInstance().addCategory(this);
	}

	public Category(SoftwareKnowledge softwareknowledge) {
		setSoftwareKnowledge(softwareKnowledge);
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

	public Set<Source> getSourceWithCatSet() {
		return sourceWithCatSet;
	}

	public void setSourceWithCatSet(Set<Source> sourceWithCatSet) {
		this.sourceWithCatSet = sourceWithCatSet;
	}

	public CategoryDTO getDTO() {
		CategoryDTO categoryDTO = new CategoryDTO();
		categoryDTO.setCatId(getCatId());
		categoryDTO.setName(getName());
		categoryDTO.setFullName(getFullName());
		categoryDTO.setParentId(getParent() != null ? getParent().getCatId() : 0);
		categoryDTO.setParent(getParent() != null ? getParent().getName() : "");

		return categoryDTO;
	}

	public void addSub(Category category) {
		category.setParent(this);
		subCategorySet.add(category);
		OntologyInterface.getInstance().addSubToCat(category);
	}

	private String getFullName() {
		return getParent() != null ? getParent().getFullName() + "." + getName() : getName();
	}
}
