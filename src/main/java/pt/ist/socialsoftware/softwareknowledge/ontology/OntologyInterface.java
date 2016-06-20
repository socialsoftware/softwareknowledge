package pt.ist.socialsoftware.softwareknowledge.ontology;

import java.util.HashMap;
import java.util.Map;

import org.apache.jena.ontology.Individual;
import org.apache.jena.ontology.OntModel;
import org.apache.jena.ontology.OntModelSpec;
import org.apache.jena.ontology.OntProperty;
import org.apache.jena.ontology.SymmetricProperty;
import org.apache.jena.rdf.model.Resource;
import org.apache.jena.rdf.model.Statement;
import org.apache.jena.rdf.model.StmtIterator;

import pt.ist.socialsoftware.softwareknowledge.domain.Category;
import pt.ist.socialsoftware.softwareknowledge.domain.SoftwareKnowledge;
import pt.ist.socialsoftware.softwareknowledge.domain.Source;

public class OntologyInterface {

	static private OntologyInterface instance = null;
	public SoftwareKnowledge softwareknowledge;
	static public OntologyInterface getInstance() {
		if (instance == null) {
			instance = new OntologyInterface();
		}
		return instance;
	}

	private OntologyInterface() {
		OntologyManager.initModel(OntModelSpec.OWL_MEM_RULE_INF);
	}


	
	public void cleanResources(){
		OntModel model = OntologyManager.getModel();
		model.close();
	}

	public void addCategory(Category category) {
		OntModel model = OntologyManager.getModel();
		final Individual cat1 = model.createIndividual(OntologyManager.getNS() + category.getName(),
				model.getOntClass("category"));
		cat1.addLiteral(model.getProperty("catId"), category.getCatId());
		cat1.addProperty(model.getProperty("catName"), category.getName());
		
	}

	public void addSource(Source source) {
		OntModel model = OntologyManager.getModel();
		
		final Individual source1 = model.createIndividual(OntologyManager.getNS() + source.getName(), 
				model.getOntClass("source"));
		OntProperty id = model.createOntProperty(OntologyManager.getNS() + "sourceid");
		OntProperty name = model.createOntProperty(OntologyManager.getNS() + "sourceName");
		OntProperty author = model.createOntProperty(OntologyManager.getNS() + "author");
		OntProperty date = model.createOntProperty(OntologyManager.getNS() + "date");
		source1.addLiteral(id, source.getSourceId());
		source1.addProperty(name, source.getName());
		source1.addProperty(author, source.getAuthor());
		source1.addProperty(date, source.getInsertDate());
		
		
	}

	
	 
	public void addSubToCat(Category category){
		OntModel model = OntologyManager.getModel();
		
		final Individual cat1 = model.createIndividual(OntologyManager.getNS() + category.getParent().getName(),
				model.getOntClass("category"));
		final Individual sub1 = model.createIndividual(OntologyManager.getNS() + category.getName(), 
				model.getOntClass("subCategory"));
		
		cat1.addProperty(model.getProperty("hasSubCategory"),sub1);		
		}
		
		
		
	
	public void addCatToSource(Source source, Category category) {
		OntModel model = OntologyManager.getModel();
		
		final Individual cat1 = model.createIndividual(OntologyManager.getNS() + category.getName(),
				model.getOntClass("category"));
		Resource source1 = model.createResource(OntologyManager.getNS() + source.getName(), 
				model.getOntClass("source"));
		
		source1.addProperty(model.getProperty("hasCategory"),cat1);
	
		
					
	}
	
	public void addRelatedCat(Source source){
		OntModel model = OntologyManager.getModel();
		Map<String, String> map = new HashMap<>();

		final Individual source1 = model.createIndividual(OntologyManager.getNS() + source.getName(), 
				model.getOntClass("source"));
		
		if(source1.hasProperty(model.getProperty("relatedSource"))){
			
			for(StmtIterator i = source1.listProperties(model.getProperty("relatedSource"));i.hasNext();){ 
				Statement s3 = i.next();
				Resource sourceR = s3.getResource();

				for(StmtIterator k = sourceR.listProperties(model.getProperty("hasCategory"));k.hasNext();){
					Statement s2 = k.next(); 
					String catR7 = s2.getResource().getLocalName();

					for(StmtIterator j = source1.listProperties(model.getProperty("hasCategory"));j.hasNext();){
						Statement s1 = j.next(); 
						String catR8 = s1.getResource().getLocalName();
						map.put(catR7, catR8); 
					}

				} 
			}

			for(Map.Entry<String, String> entry : map.entrySet()) { 
				String key = entry.getKey(); 
				String value = entry.getValue();
				source1.addProperty(model.getProperty("relationCat"), key, value);

			}

		} 
	}
	/*
	public void printStatement(){
		OntModel model = OntologyManager.getModel(); 
		
		model.prepare();

		for (StmtIterator i = model.listStatements(null , null, (RDFNode) null); i.hasNext();) {
			Statement s = i.nextStatement();
			s.toString();
		}
	}*/

	public void addRelatedCategory(Category c1, Category c2){
		OntModel model = OntologyManager.getModel();
		final Individual cat1 = model.createIndividual(OntologyManager.getNS() + c1.getName(), 
				model.getOntClass("category"));
		final Individual cat2 = model.createIndividual(OntologyManager.getNS() + c2.getName(),
				model.getOntClass("category"));
		
		SymmetricProperty sp = model.getSymmetricProperty(OntologyManager.getNS() + "relatedcat");
		cat1.addProperty(sp,cat2);
	}
	
	
	public void addRelatedSource(Source s1, Source s2){
		OntModel model = OntologyManager.getModel();
		final Individual source1 = model.createIndividual(OntologyManager.getNS() + s1.getName(), 
				model.getOntClass("source"));
		final Individual source2 = model.createIndividual(OntologyManager.getNS() + s2.getName(),
				model.getOntClass("source"));
		SymmetricProperty sp = model.getSymmetricProperty(OntologyManager.getNS() + "relatedsource");
		source1.addProperty(sp, source2);
	}
	
	public void removeSource(Source source) {
		OntModel model = OntologyManager.getModel(); 
		final Individual source1 = model.createIndividual(OntologyManager.getNS() + source.getName(), 
				model.getOntClass("source"));
		//Object
		model.removeAll(null, null, source1);
		//Subject
		model.removeAll(source1, null, null);
	}
	
	
	//remove na ontologia não esta a funcionar
	public void removeCat(Category category) {
		OntModel model = OntologyManager.getModel();
		Resource cat1 = model.createResource(OntologyManager.getNS() + category.getName(), 
						model.getOntClass("category"));
		
		model.removeAll(null, null, cat1);
			
	}
	

}
