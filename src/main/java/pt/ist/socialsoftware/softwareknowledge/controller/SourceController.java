package pt.ist.socialsoftware.softwareknowledge.controller;




import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import pt.ist.socialsoftware.softwareknowledge.domain.Source;
import pt.ist.socialsoftware.softwareknowledge.service.ServiceInterface;
import pt.ist.socialsoftware.softwareknowledge.service.dto.SourceDTO;

@RestController
@RequestMapping(value = "/category")
public class SourceController {
	private static Logger logger = LoggerFactory.getLogger(CategoryController.class);

	@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
	public ResponseEntity<SourceDTO> createCategory() {
		logger.debug("createCategory");
		// public ResponseEntity<CategoryDTO> createCategory(@RequestBody
		// CategoryDTO categoryDTO) {

		SourceDTO sourceDTO = new SourceDTO("Rodrigo", 2,"3-5-2016" , "Fonte3");

		ServiceInterface serviceInterface = ServiceInterface.getInstance();
		Source source = serviceInterface.createSource(sourceDTO);

		return new ResponseEntity<SourceDTO>(source.getDTO(), HttpStatus.OK);
	}
}
