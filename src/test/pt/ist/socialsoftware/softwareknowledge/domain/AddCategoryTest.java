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

public class AddCategoryTest {
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
	public void addCategorySuccessTest() {
		Category category = new Category(softwareKnowledge, 1, "Programming");

		assertEquals(1, category.getCatId());
		assertEquals("Programming", category.getName());
		assertEquals(1, softwareKnowledge.getCategorySet().size());
	}

	@Test
	public void addCategoryDuplicateErroeTest() {
		new Category(softwareKnowledge, 1, "Programming");
		try {
			new Category(softwareKnowledge, 1, "Programming");
			fail();
		} catch (SKException ske) {
			assertEquals(SKErrorType.DUPLICATE_CATEGORY, ske.getError());
		}
	}

}
