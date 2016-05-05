package pt.ist.socialsoftware.softwareknowledge.domain;

import java.util.Collections;
import java.util.Set;

import pt.ist.socialsoftware.softwareknowledge.ontology.OntologyInterface;
import pt.ist.socialsoftware.softwareknowledge.service.dto.SourceDTO;

public class Source {
	private SoftwareKnowledge softwareknowledge;
	private String author;
	private int sourceId;
	private String insertDate;
	private String name;
	private Set<Category> catInSourceSet = Collections.<Category>emptySet();
	
	
	public Source(){}
	
	
	public Source(SoftwareKnowledge softwareKnowledge, int sourceId, String name, String author, String insertDate) {
		setSoftwareKnowledge(softwareKnowledge);
		setSourceId(sourceId);
		setName(name);
		setInsertDate(insertDate);
		setAuthor(author);
		softwareKnowledge.addSource(this);
		OntologyInterface.getInstance().addSource(this);
	}
	
	public SoftwareKnowledge getSoftwareKnowledge() {
		return softwareknowledge;
	}

	public void setSoftwareKnowledge(SoftwareKnowledge softwareKnowledge) {
		this.softwareknowledge = softwareKnowledge;
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

	public SoftwareKnowledge getSoftwareknowledge() {
		return softwareknowledge;
	}


	public void setSoftwareknowledge(SoftwareKnowledge softwareknowledge) {
		this.softwareknowledge = softwareknowledge;
	}


	public Set<Category> getCatInSourceSet() {
		return catInSourceSet;
	}


	public void setCatInSourceSet(Set<Category> catInSourceSet) {
		this.catInSourceSet = catInSourceSet;
	}

	public SourceDTO getDTO() {
	
		return new SourceDTO(getAuthor(),getSourceId(), getInsertDate(), getName());
		
	}

	
	
}
