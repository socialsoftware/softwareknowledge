package pt.ist.socialsoftware.softwareknowledge.dao;


import javax.transaction.Transactional;

import org.springframework.data.repository.CrudRepository;

import pt.ist.socialsoftware.softwareknowledge.domain.Category;

@Transactional
public interface CategoryDao extends CrudRepository<Category, Long>{
	
	//Set<Category> findAllCategories(); findAll() e findOne(id)
	
	
	
	
	

}
