package pt.ist.socialsoftware.softwareknowledge.ontology;

import static org.junit.Assert.assertEquals;

import org.apache.jena.ontology.Individual;
import org.apache.jena.ontology.OntModel;
import org.apache.jena.ontology.OntProperty;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import pt.ist.socialsoftware.softwareknowledge.domain.SoftwareKnowledge;
import pt.ist.socialsoftware.softwareknowledge.domain.Source;

public class AddPropertyToSourceTest {

	private static Logger logger = LoggerFactory.getLogger(AddPropertyToSourceTest.class);

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
	public void addPropertyToSourceSuccessTest() {
		OntModel model = OntologyManager.getModel();
		Source source = new Source(softwareKnowledge, 1, "TestingSource","Rodrigo","29-04-2016");
		final Individual source1 = model.createIndividual(OntologyManager.getNS() + source.getName(), 
				model.getOntClass("source"));
		OntProperty p = model.createOntProperty(OntologyManager.getNS() + "author");
		ontologyInterface.addSource(source);
		Integer i = source.getPropertySet().size();
		ontologyInterface.addPropertyToSource(source, "author", "Jose");
		
		assertEquals(i+1,source.getPropertySet().size());
		
	}
}
