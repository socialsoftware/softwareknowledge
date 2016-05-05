package pt.ist.socialsoftware.softwareknowledge.ontology;

import org.apache.jena.ontology.OntModelSpec;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class InitModelTest {
	private static Logger logger = LoggerFactory.getLogger(InitModelTest.class);

	@Before
	public void setUp() {
		logger.debug("setup");
	}

	@After
	public void tearDown() {
		logger.debug("tearDown");

	}

	@Test
	public void initModelFromStaticTest() {
		logger.debug("initModelTest");

		OntologyManager.initModel(OntModelSpec.OWL_MEM_RULE_INF);
	}

	@Test
	public void initModelFromOntologInterfaceTest() {
		logger.debug("initModelTest");

		OntologyInterface.getInstance();
	}
	
	

}
