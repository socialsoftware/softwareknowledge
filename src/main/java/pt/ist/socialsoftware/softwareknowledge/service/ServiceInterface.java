package pt.ist.socialsoftware.softwareknowledge.service;

import pt.ist.socialsoftware.softwareknowledge.domain.Category;
import pt.ist.socialsoftware.softwareknowledge.domain.SoftwareKnowledge;
import pt.ist.socialsoftware.softwareknowledge.domain.Source;
import pt.ist.socialsoftware.softwareknowledge.ontology.OntologyInterface;
import pt.ist.socialsoftware.softwareknowledge.service.dto.CategoryDTO;
import pt.ist.socialsoftware.softwareknowledge.service.dto.SourceDTO;

public class ServiceInterface {
	static private ServiceInterface instance = null;

	static public ServiceInterface getInstance() {
		if (instance == null) {
			instance = new ServiceInterface();
		}
		return instance;
	}

	private ServiceInterface() {
	}

	public SoftwareKnowledge getSoftwareKnowledge() {
		return SoftwareKnowledge.getInstance();
	}

	public void test() {
		OntologyInterface ontologyInterface = OntologyInterface.getInstance();
		ontologyInterface.test();
	}

	public Category createCategory(CategoryDTO categoryDTO) {
		Category parent = getSoftwareKnowledge().getCategory(categoryDTO.getParent());
		return new Category(getSoftwareKnowledge(), categoryDTO.getCatId(), categoryDTO.getName(), parent);
	}
	
	public Source createSource(SourceDTO sourceDTO){
		return new Source(getSoftwareKnowledge(), sourceDTO.getSourceId(),sourceDTO.getName(), sourceDTO.getAuthor(),sourceDTO.getInsertDate());
	}
	
	

}
