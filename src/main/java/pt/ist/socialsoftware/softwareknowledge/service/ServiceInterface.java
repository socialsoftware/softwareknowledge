package pt.ist.socialsoftware.softwareknowledge.service;

import java.util.Set;

import pt.ist.socialsoftware.softwareknowledge.domain.Category;
import pt.ist.socialsoftware.softwareknowledge.domain.SoftwareKnowledge;
import pt.ist.socialsoftware.softwareknowledge.domain.Source;
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

	public Category createCategory(CategoryDTO categoryDTO) {
		Category parent = getSoftwareKnowledge().getCategory(categoryDTO.getParentId());
		return new Category(getSoftwareKnowledge(), categoryDTO.getName(), parent);
	}

	public Source createSource(SourceDTO sourceDTO) {
		return new Source(getSoftwareKnowledge(), sourceDTO.getSourceId(), sourceDTO.getName(), sourceDTO.getAuthor(),
				sourceDTO.getInsertDate(), sourceDTO.getLink());
	}

	public Set<Category> getSubCategories(int catId) {
		return getSoftwareKnowledge().getSubCategorySet(catId);
	}

	public Set<Category> getCategories() {
		return getSoftwareKnowledge().getCategorySet();

	}

	public Category getCategory(int catId) {
		return getSoftwareKnowledge().getCategory(catId);
	}

	public Category getCategory(String name) {
		return getSoftwareKnowledge().getCategory(name);
	}

	public Set<Source> getSources() {
		return getSoftwareKnowledge().getSourceSet();
	}

	public Source getSource(int sourceId) {
		return getSoftwareKnowledge().getSource(sourceId);
	}

	public Source getSource(String name) {
		return getSoftwareKnowledge().getSource(name);
	}
}
