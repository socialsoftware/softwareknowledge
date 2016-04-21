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

import pt.ist.socialsoftware.softwareknowledge.jena.exemplo;
import pt.ist.socialsoftware.softwareknowledge.utils.dto.*;

@RestController
//@RequestMapping(value = "/home")
public class HomeController {
	private static Logger log = LoggerFactory.getLogger(HomeController.class);
	
	@RequestMapping(value = "/home",produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
	public ResponseEntity<SourceDTO> getHome() {
		log.debug("getHome");
		SourceDTO sourceDTO = new SourceDTO();
		sourceDTO.setAuthor("Antonio");
		sourceDTO.setSourceId(1);
		sourceDTO.setSourceName("Software Engineering");
		sourceDTO.setInsertDate("14-04-2016");
		

		exemplo ex = new exemplo();
		//ex.teste();
		return new ResponseEntity<SourceDTO>(sourceDTO, HttpStatus.OK);
	}
	
	

}
