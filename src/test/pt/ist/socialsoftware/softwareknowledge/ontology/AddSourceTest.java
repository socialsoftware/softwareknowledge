package pt.ist.socialsoftware.softwareknowledge.ontology;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import pt.ist.socialsoftware.softwareknowledge.domain.SoftwareKnowledge;
import pt.ist.socialsoftware.softwareknowledge.domain.Source;

public class AddSourceTest {
	private static Logger logger = LoggerFactory.getLogger(AddCategoryTest.class);

	private SoftwareKnowledge softwareKnowledge;
	private OntologyInterface ontologyInterface;

	@Before
	public void setUp() {
		logger.debug("setup");
		softwareKnowledge = SoftwareKnowledge.getInstance();
		ontologyInterface = OntologyInterface.getInstance();
	}

	@After
	public void tearDown() {
		logger.debug("tearDown");
		softwareKnowledge.clean();
		ontologyInterface.cleanResources();
	}

	@Test
	public void addSourceSuccessTest() {
		ontologyInterface.addSource(new Source(softwareKnowledge, 1, "TestingSource","Rodrigo","29-04-2016","http"));
		assertEquals("TestingSource", OntologyManager.getModel().getResource("/TestingSource").getLocalName());
	}


}
