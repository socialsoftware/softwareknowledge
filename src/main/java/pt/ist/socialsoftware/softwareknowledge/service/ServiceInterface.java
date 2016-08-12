package pt.ist.socialsoftware.softwareknowledge.service;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;

import pt.ist.socialsoftware.softwareknowledge.dao.CategoryDao;
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
	
	 @Autowired
	 private CategoryDao categoryDao;

	private ServiceInterface() {
	}

	public SoftwareKnowledge getSoftwareKnowledge() {
		return SoftwareKnowledge.getInstance();
	}

	public Category createCategory(CategoryDTO categoryDTO) {
		Category c = null;
		Category parent = getSoftwareKnowledge().getCategory(categoryDTO.getParentId());
		try{
			c = new Category(getSoftwareKnowledge(), categoryDTO.getName(), parent);
			categoryDao.save(c);
		}
		catch (Exception ex) {
			
		}
		return c;
		
	}
	
	

	public Source createSource(SourceDTO sourceDTO) {

		Set<Category> catList = new HashSet<Category>();

		for (CategoryDTO i : sourceDTO.getCatList()) {
			catList.add(getSoftwareKnowledge().getCategory(i.getCatId()));
		}

		return new Source(getSoftwareKnowledge(), sourceDTO.getSourceId(), sourceDTO.getName(), sourceDTO.getAuthor(),
				sourceDTO.getInsertDate(), sourceDTO.getLink());
	}

	public Set<Category> getSubCategories(int catId) {
		return getSoftwareKnowledge().getSubCategorySet(catId);
	}

	/*
	 * public Set<Category> getSubCategories(int catId){ return
	 * getSoftwareKnowledge().getSubCategorySet(catId); }
	 */

	public Set<Category> getCategories() {
		return getSoftwareKnowledge().getCategorySet();
		//return (Set<Category>) categoryDao.findAll();

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

	public Category getCatParent(int catId) {
		return getSoftwareKnowledge().getCategory(catId).getParent();

	}

	public Set<Category> removeCategory(int catId) {
//		Category c = getCategory(catId);
//		categoryDao.delete(c);
		return getSoftwareKnowledge().removeCategory(catId);
	}

	public Set<Source> removeSource(int sourceId) {
		return getSoftwareKnowledge().removeSource(sourceId);
	}

	public Category updateCategory(CategoryDTO categoryDTO) {
//		Category c = categoryDao.findOne((long) categoryDTO.getCatId());
//		c.setCatId(categoryDTO.getCatId());
//		c.setName(categoryDTO.getName());
//		categoryDao.save(c);
		return getSoftwareKnowledge().updateCategory(categoryDTO.getName(), categoryDTO.getCatId());
	}

	public Source updateSource(SourceDTO sourceDTO) {
		return getSoftwareKnowledge().updateSource(sourceDTO.getName(), sourceDTO.getSourceId());
	}


	
}
