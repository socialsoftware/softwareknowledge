package pt.ist.socialsoftware.softwareknowledge.domain;

import java.util.HashSet;
import java.util.Set;

import pt.ist.socialsoftware.softwareknowledge.utils.exception.SKErrorType;
import pt.ist.socialsoftware.softwareknowledge.utils.exception.SKException;

public class SoftwareKnowledge {
	static private SoftwareKnowledge instance = null;

	static public SoftwareKnowledge getInstance() {
		if (instance == null) {
			instance = new SoftwareKnowledge();
		}
		return instance;
	}

	private SoftwareKnowledge() {
	}

	private Set<Category> categorySet = new HashSet<Category>();

	public void clean() {
		categorySet.clear();
	}

	public void addCategory(Category category) {
		if (categorySet.stream()
				.filter(c -> c.getCatId() == category.getCatId() || c.getName().equals(category.getName())).findFirst()
				.isPresent()) {
			throw new SKException(SKErrorType.DUPLICATE_CATEGORY, category.getCatId() + ":" + category.getName());
		}

		categorySet.add(category);
	}

	public Set<Category> getCategorySet() {
		return categorySet;
	}

}
