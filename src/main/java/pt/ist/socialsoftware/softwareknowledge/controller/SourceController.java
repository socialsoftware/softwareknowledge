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

import pt.ist.socialsoftware.softwareknowledge.domain.Source;
import pt.ist.socialsoftware.softwareknowledge.service.ServiceInterface;
import pt.ist.socialsoftware.softwareknowledge.service.dto.SourceDTO;

@RestController
@RequestMapping(value = "/source")
public class SourceController {
	private static Logger logger = LoggerFactory.getLogger(SourceController.class);

	@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
	public ResponseEntity<SourceDTO[]> getSources() {
		logger.debug("getSources");

		ServiceInterface serviceInterface = ServiceInterface.getInstance();
		SourceDTO[] sources = serviceInterface.getSources().stream().map(c -> c.getDTO())
				.toArray(size -> new SourceDTO[size]);

		return new ResponseEntity<SourceDTO[]>(sources, HttpStatus.OK);
	}

	@RequestMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
	public ResponseEntity<SourceDTO> getSource(@PathVariable("id") int sourceId) {
		logger.debug("getSource sourceId:{}", sourceId);

		ServiceInterface serviceInterface = ServiceInterface.getInstance();
		SourceDTO sourceDTO = serviceInterface.getSource(sourceId).getDTO();

		return new ResponseEntity<SourceDTO>(sourceDTO, HttpStatus.OK);
	}

	
	@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
	public ResponseEntity<SourceDTO> createSource(@RequestBody SourceDTO sourceDTO) {
		logger.debug("createSource name:{}, link:{}, author:{}, catList:{}", sourceDTO.getName(), sourceDTO.getLink(),sourceDTO.getAuthor(), sourceDTO.getCatList());
		
		ServiceInterface serviceInterface = ServiceInterface.getInstance();
		Source source = serviceInterface.createSource(sourceDTO);

		return new ResponseEntity<SourceDTO>(source.getDTO(), HttpStatus.CREATED);
	}
	
	
}
