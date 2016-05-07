package pt.ist.socialsoftware.softwareknowledge.domain;

import org.junit.After;
import org.junit.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AddPropertyToSourceTest {
	private static Logger logger = LoggerFactory.getLogger(AddPropertyToSourceTest.class);

	private SoftwareKnowledge softwareKnowledge;

	@Before
	public void setUp() {
		logger.debug("setup");
		softwareKnowledge = SoftwareKnowledge.getInstance();
	}

	@After
	public void tearDown() {
		logger.debug("tearDown");
		softwareKnowledge.clean();
	}

/*	@Test
	public void addPropertyToSourceSuccessTest() {
		Source source = new Source(softwareKnowledge, 1, "TestingSource","Rodrigo","29-04-2016");
		Integer i = softwareKnowledge.getPropertySet().size();
		softwareKnowledge.addPropertyToSource(source, "author", "Jose");
		
		assertEquals(2, model.listStatements(source1, p , (RDFNode) null).toSet().size());
	}*/

	

}
