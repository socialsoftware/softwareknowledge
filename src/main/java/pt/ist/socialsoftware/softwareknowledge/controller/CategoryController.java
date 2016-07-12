package pt.ist.socialsoftware.softwareknowledge.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import pt.ist.socialsoftware.softwareknowledge.domain.Category;
import pt.ist.socialsoftware.softwareknowledge.service.ServiceInterface;
import pt.ist.socialsoftware.softwareknowledge.service.dto.CategoryDTO;

@RestController
@RequestMapping(value = "/category")
public class CategoryController {
	private static Logger logger = LoggerFactory.getLogger(CategoryController.class);

	@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
	public ResponseEntity<CategoryDTO[]> getCategories() {
		
		
		ServiceInterface serviceInterface = ServiceInterface.getInstance();
		logger.debug("getCategories values:{}", serviceInterface.getCategories());
		CategoryDTO[] categories = serviceInterface.getCategories().stream().map(c -> c.getDTO())
				.toArray(size -> new CategoryDTO[size]);

		return new ResponseEntity<CategoryDTO[]>(categories, HttpStatus.OK);
	}
	
	/*@RequestMapping(value = "/{id}/sub", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
	public ResponseEntity<CategoryDTO[]> getSubCategories(@PathVariable("id") int catId) {
		logger.debug("getSubCategoriescatId:{}", catId);

		ServiceInterface serviceInterface = ServiceInterface.getInstance();
		CategoryDTO[] categories = serviceInterface.getSubCategories(catId).stream().map(c -> c.getDTO())
				.toArray(size -> new CategoryDTO[size]);

		return new ResponseEntity<CategoryDTO[]>(categories, HttpStatus.OK);
	}*/

	@RequestMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
	public ResponseEntity<CategoryDTO> getCategory(@PathVariable("id") int catId) {
		logger.debug("getCategory catId:{}", catId);

		ServiceInterface serviceInterface = ServiceInterface.getInstance();
		CategoryDTO categoryDTO = serviceInterface.getCategory(catId).getDTO();

		return new ResponseEntity<CategoryDTO>(categoryDTO, HttpStatus.OK);
	}
	
	
	@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
	public ResponseEntity<CategoryDTO> createCategory(@RequestBody CategoryDTO categoryDTO) {
		logger.debug("createCategory name:{}, catId:{}, parentId:{}", categoryDTO.getName(), categoryDTO.getCatId(),categoryDTO.getParentId());
		
		ServiceInterface serviceInterface = ServiceInterface.getInstance();
		Category category = serviceInterface.createCategory(categoryDTO);

		return new ResponseEntity<CategoryDTO>(category.getDTO(), HttpStatus.CREATED);
	}
	
	@RequestMapping(value="/{id}", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.DELETE)
	public ResponseEntity<CategoryDTO[]> removeCategory(@PathVariable("id") int catId) {
		logger.debug("removeCategory catId:{}", catId);
		
		ServiceInterface serviceInterface = ServiceInterface.getInstance();
		CategoryDTO[] categories = serviceInterface.removeCategory(catId).stream().map(c -> c.getDTO())
				.toArray(size -> new CategoryDTO[size]);

		return new ResponseEntity<CategoryDTO[]>(categories, HttpStatus.OK);
	}
	
	
	
}