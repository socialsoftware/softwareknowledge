package pt.ist.socialsoftware.softwareknowledge.domain;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import pt.ist.socialsoftware.softwareknowledge.ontology.OntologyInterface;
import pt.ist.socialsoftware.softwareknowledge.service.dto.CategoryDTO;



@Entity
@Table(name = "categories")
public class Category {
	
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "softId", referencedColumnName="id")
	private SoftwareKnowledge softwareKnowledge;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	

	@NotNull
	@Column(name="catId")
	private int catId;
	
	@NotNull
	@Column(name="Name")
	private String name;
	
	@OneToMany(mappedBy = "parent",targetEntity=Category.class)
	private Set<Category> subCategorySet;
	
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "parentId", referencedColumnName="id")
	private Category parent;
	
//	@ManyToMany(fetch=FetchType.EAGER)
//	@JoinTable(name="SOURCE_CATS",
//            joinColumns=
//            @JoinColumn(name="catId", referencedColumnName="catId"),
//            inverseJoinColumns=
//            @JoinColumn(name="sourceId", referencedColumnName="sourceId")
//    )
	//private Set<Source> sourceWithCatSet;
	
	
	private static int catIdCounter = 1;

	
	public Category(){}
	
	public Category(SoftwareKnowledge softwareKnowledge, String name, Category parent) {

		setSoftwareKnowledge(softwareKnowledge);
		setCatId(Category.catIdCounter++);
		setName(name);
		softwareKnowledge.addCategory(this);
		subCategorySet = new HashSet<Category>();
//		sourceWithCatSet = new HashSet<Source>();
		OntologyInterface.getInstance().addCategory(this);
		if (parent != null) {
			parent.addSub(this);
		} else {
			this.parent = null;
		}

	}
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
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
		return null;//sourceWithCatSet;
	}

	public void setSourceWithCatSet(Set<Source> sourceWithCatSet) {
		//this.sourceWithCatSet = sourceWithCatSet;
	}

	public CategoryDTO getDTO() {
		CategoryDTO categoryDTO = new CategoryDTO();
		categoryDTO.setCatId(getCatId());
		categoryDTO.setName(getName());
		categoryDTO.setFullName(getFullName());

		if(getParent() != null){
			categoryDTO.setParentId(getParent().getCatId());
			categoryDTO.setParent(getParent().getName());
		}
		else{
			categoryDTO.setParentId(0);
			categoryDTO.setParent("");
		}
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
