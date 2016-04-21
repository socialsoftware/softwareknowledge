package pt.ist.socialsoftware.softwareknowledge.jena;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BasicTest {
	private static Logger logger = LoggerFactory.getLogger(BasicTest.class);

	@Before
	public void setUp() {
		logger.debug("setup");
	}

	@After
	public void tearDown() {
		logger.debug("setup");

	}

	@Test
	public void basicTest() {
		logger.debug("tearDown");

		Experiment ex = new Experiment();
		ex.teste();
	}

}
