package pt.ist.socialsoftware.softwareknowledge.jena;

import org.apache.jena.ontology.OntModelSpec;
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
		logger.debug("tearDown");

	}

	@Test
	public void basicTest() {
		logger.debug("basicTest");

		Experiment ex = new Experiment();
		OntModelSpec s = OntModelSpec.OWL_MEM_RULE_INF;
		ex.initModel(s);
	}

}
