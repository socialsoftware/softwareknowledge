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
	private Set<Source> sourceSet = new HashSet<Source>();
	

	public void clean() {
		categorySet.clear();
		sourceSet.clear();
	}

	public void addCategory(Category category) {
		if (categorySet.stream()
				.filter(c -> c.getCatId() == category.getCatId() || c.getName().equals(category.getName())).findFirst()
				.isPresent()) {
			throw new SKException(SKErrorType.DUPLICATE_CATEGORY, category.getCatId() + ":" + category.getName());
		}

		categorySet.add(category);
	}
	
	public void addSource(Source source){
		if(sourceSet.stream().filter(s -> s.getSourceId() == source.getSourceId() || s.getName().equals(source.getName())).findFirst()
				.isPresent()){
			throw new SKException(SKErrorType.DUPLICATE_SOURCE, source.getSourceId() + ":" + source.getName());
		}
		
		sourceSet.add(source);
	}
	
	
	
	public Category getCategory(String name){
		for(Category c : getCategorySet()){
			if(c.getName().equals(name)){
				return c;
			}
		}
		return null;
	}
	
	public Source getSource(String name){
		return	getSourceSet().stream().filter(s -> s.getName().equals(name)).findFirst().orElse(null);
		
	}
	

	public Set<Category> getCategorySet() {
		return categorySet;
	}
	
	public Set<Source> getSourceSet(){
		return sourceSet;
	}
	
	
	
	
	

}
