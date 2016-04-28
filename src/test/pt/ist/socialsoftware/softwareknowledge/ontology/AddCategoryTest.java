package pt.ist.socialsoftware.softwareknowledge.ontology;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import pt.ist.socialsoftware.softwareknowledge.domain.Category;
import pt.ist.socialsoftware.softwareknowledge.domain.SoftwareKnowledge;

public class AddCategoryTest {
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
		// TODO: clean softwareKnowledge and ontologyInterface
		softwareKnowledge.clean();
	}

	@Test
	public void addCategorySuccessTest() {
		ontologyInterface.addCategory(new Category(softwareKnowledge, 1, "Programming"));

		assertEquals(null, OntologyManager.getModel().getIndividual("Programming"));
		assertEquals("Programming", OntologyManager.getModel().getIndividual("Programming").getLocalName());
	}

}
