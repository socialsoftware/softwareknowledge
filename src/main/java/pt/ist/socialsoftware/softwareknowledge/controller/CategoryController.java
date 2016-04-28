package pt.ist.socialsoftware.softwareknowledge.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
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
	public ResponseEntity<CategoryDTO> createCategory() {
		logger.debug("createCategory");
		// public ResponseEntity<CategoryDTO> createCategory(@RequestBody
		// CategoryDTO categoryDTO) {

		CategoryDTO categoryDTO = new CategoryDTO(2, "tabem");

		ServiceInterface serviceInterface = ServiceInterface.getInstance();
		Category category = serviceInterface.createCategory(categoryDTO);

		return new ResponseEntity<CategoryDTO>(category.getDTO(), HttpStatus.OK);
	}
}