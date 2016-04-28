package pt.ist.socialsoftware.softwareknowledge.ontology;

import org.apache.jena.ontology.Individual;
import org.apache.jena.ontology.OntModel;
import org.apache.jena.ontology.OntModelSpec;

import pt.ist.socialsoftware.softwareknowledge.domain.Category;
import pt.ist.socialsoftware.softwareknowledge.service.dto.CategoryDTO;
import pt.ist.socialsoftware.softwareknowledge.service.dto.SourceDTO;

public class OntologyInterface {

	static private OntologyInterface instance = null;

	static public OntologyInterface getInstance() {
		if (instance == null) {
			instance = new OntologyInterface();
		}
		return instance;
	}

	private OntologyInterface() {
		OntologyManager.initModel(OntModelSpec.OWL_MEM_RULE_INF);
	}

	public void addSource(String name, CategoryDTO cat) {

	}

	public void addCatToSource(SourceDTO source, CategoryDTO cat) {

	}

	public void removeSource(SourceDTO source) {

	}
	/*
	 * Falta conseguir aceder as propriedades do modelo public void
	 * addRelatedCat(SourceDTO d){ Map<String, String> map = new HashMap<>();
	 * 
	 * 
	 * if(d.hasProperty(relatedSource)){ for(StmtIterator i =
	 * d.listProperties(relatedSource);i.hasNext();){ Statement s3 = i.next();
	 * Resource sourceR = s3.getResource();
	 * 
	 * 
	 * for(StmtIterator k = sourceR.listProperties(hasCategory);k.hasNext();){
	 * Statement s2 = k.next(); String catR7 = s2.getResource().getLocalName();
	 * 
	 * for(StmtIterator j = d.listProperties(hasCategory);j.hasNext();){
	 * Statement s1 = j.next(); String catR8 = s1.getResource().getLocalName();
	 * map.put(catR7, catR8); }
	 * 
	 * } }
	 * 
	 * for (Map.Entry<String, String> entry : map.entrySet()) { String key =
	 * entry.getKey(); String value = entry.getValue();
	 * 
	 * d.addProperty(relationCat, key, value);
	 * 
	 * }
	 * 
	 * } }
	 */

	public void test() {
		OntologyManager ex = new OntologyManager();
		ex.test();
	}

	public void addCategory(Category category) {
		OntModel model = OntologyManager.getModel();
		final Individual cat1 = model.createIndividual(OntologyManager.getNS() + category.getName(),
				model.getOntClass("category"));
		cat1.addLiteral(model.getProperty("catId"), category.getCatId());
		cat1.addProperty(model.getProperty("catName"), category.getName());

	}

	/*
	 * public void addPropertytoSource(SourceDTO source,OntProperty p , String
	 * value){ source.addProperty(p,value); }
	 */

}
