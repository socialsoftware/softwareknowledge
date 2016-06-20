package pt.ist.socialsoftware.softwareknowledge.domain;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import pt.ist.socialsoftware.softwareknowledge.utils.exception.SKErrorType;
import pt.ist.socialsoftware.softwareknowledge.utils.exception.SKException;

public class AddSourceTest {
	private static Logger logger = LoggerFactory.getLogger(AddCategoryTest.class);

	private SoftwareKnowledge softwareKnowledge;

	@Before
	public void setUp() {
		logger.debug("setup");
		softwareKnowledge = SoftwareKnowledge.getInstance();
	}

	@After
	public void tearDown() {
		logger.debug("tearDown");
		// TODO: clean softwareKnowledge and ontologyInterface
		softwareKnowledge.clean();
		
	}

	@Test
	public void addSourceSuccessTest() {
		Source source = new Source(softwareKnowledge, 1, "TestingSource","Rodrigo","29-04-2016","http");

		assertEquals(1, source.getSourceId());
		assertEquals("TestingSource", source.getName());
		assertEquals("Rodrigo", source.getAuthor());
		assertEquals(1, softwareKnowledge.getSourceSet().size());
	}

	@Test
	public void addSourceDuplicateErrorTest() {
		new Source(softwareKnowledge, 1, "TestingSource","Rodrigo","29-04-2016","http");
		try {
			new Source(softwareKnowledge, 1, "TestingSource","Rodrigo","29-04-2016","http");
			fail();
		} catch (SKException ske) {
			assertEquals(SKErrorType.DUPLICATE_SOURCE, ske.getError());
		}
	}

}
