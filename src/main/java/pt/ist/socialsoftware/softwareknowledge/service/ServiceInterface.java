package pt.ist.socialsoftware.softwareknowledge.service;

import pt.ist.socialsoftware.softwareknowledge.domain.Category;
import pt.ist.socialsoftware.softwareknowledge.domain.SoftwareKnowledge;
import pt.ist.socialsoftware.softwareknowledge.ontology.OntologyInterface;
import pt.ist.socialsoftware.softwareknowledge.service.dto.CategoryDTO;

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

	public SoftwareKnowledge getSoftwareKnowldge() {
		return SoftwareKnowledge.getInstance();
	}

	public void test() {
		OntologyInterface ontologyInterface = OntologyInterface.getInstance();
		ontologyInterface.test();
	}

	public Category createCategory(CategoryDTO categoryDTO) {
		return new Category(getSoftwareKnowldge(), categoryDTO.getCatId(), categoryDTO.getName());
	}

}
