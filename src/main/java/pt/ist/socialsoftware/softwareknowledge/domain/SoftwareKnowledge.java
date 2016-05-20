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
	private Set<RelatedSource> relatedSourceSet = new HashSet<RelatedSource>();
	private Set<RelatedCategory> relatedCategorySet = new HashSet<RelatedCategory>();
	
	public void clean() {
		categorySet.clear();
		sourceSet.clear();
		relatedSourceSet.clear();
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
	
	public void addRelatedSource(Source s1, Source s2, SourceProperty p){
		for (RelatedSource r : getRelatedSourceSet()){
			if(r.getS1().getName().equals(s1.getName()) && r.getS2().getName().equals(s2.getName()) && r.getSourceProperty() == p){
				throw new SKException(SKErrorType.DUPLICATE_RELATEDSOURCE, s1.getName() + "," + s1.getName() + "," + p);
			}
			RelatedSource rs = new RelatedSource(s1, s2, p);
			getRelatedSourceSet().add(rs);
		}
	}
	
	
	public void addRelatedCategory(Category c1, Category c2, CategoryProperty p){
		for(RelatedCategory r : getRelatedCategorySet()){
			if(r.getC1().getName().equals(c1.getName()) && r.getC2().getName().equals(c2.getName()) && r.getCategoryProperty() == p){
				throw new SKException(SKErrorType.DUPLICATE_RELATEDCATEGORY, c1.getName() + "," + c1.getName() + "," + p);
			}
			RelatedCategory rc = new RelatedCategory(c1, c2, p);
			getRelatedCategorySet().add(rc);
		}
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
	

	

	public Set<RelatedCategory> getRelatedCategorySet() {
		return relatedCategorySet;
	}

	public void setRelatedCategorySet(Set<RelatedCategory> relatedCategorySet) {
		this.relatedCategorySet = relatedCategorySet;
	}

	public Set<RelatedSource> getRelatedSourceSet() {
		return relatedSourceSet;
	}

	public void setRelatedSourceSet(Set<RelatedSource> relatedSourceSet) {
		this.relatedSourceSet = relatedSourceSet;
	}

	public void setCategorySet(Set<Category> categorySet) {
		this.categorySet = categorySet;
	}

	public void setSourceSet(Set<Source> sourceSet) {
		this.sourceSet = sourceSet;
	}

	public Set<Category> getCategorySet() {
		return categorySet;
	}
	
	public Set<Source> getSourceSet(){
		return sourceSet;
	}
	
	
	
	
	

}
