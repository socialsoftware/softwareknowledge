package pt.ist.socialsoftware.softwareknowledge.ontology;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.apache.jena.ontology.Individual;
import org.apache.jena.ontology.OntModel;
import org.apache.jena.ontology.OntModelSpec;
import org.apache.jena.ontology.OntProperty;
import org.apache.jena.rdf.model.Resource;
import org.apache.jena.rdf.model.Statement;
import org.apache.jena.rdf.model.StmtIterator;

import pt.ist.socialsoftware.softwareknowledge.domain.Category;
import pt.ist.socialsoftware.softwareknowledge.domain.Properties;
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

 

	public void test() {
		OntologyManager ex = new OntologyManager();
		ex.test();
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
		Set<Properties> propertiesSet =source.getPropertySet(); 
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
		Properties idS = new Properties(id, String.valueOf(source.getSourceId()));
		Properties nameS = new Properties(name,source.getName());
		Properties authorS = new Properties(author, source.getAuthor());
		Properties dateS = new Properties(date, source.getInsertDate());
		
		propertiesSet.add(idS);
		propertiesSet.add(nameS);
		propertiesSet.add(authorS);
		propertiesSet.add(dateS);
		
		
		
	}

	
	public void addPropertyToSource(Source source,String property , String value){ 
		OntModel model = OntologyManager.getModel();
		Set<Properties> propertiesSet =source.getPropertySet();
		final Individual source1 = model.createIndividual(OntologyManager.getNS() + source.getName(), 
				model.getOntClass("source"));
		
		OntProperty p = model.createOntProperty(OntologyManager.getNS() + property);
		source1.addProperty(p,value); 
		Properties prop = new Properties(p,value);
		propertiesSet.add(prop);
	}
	 
	public void addSubToCat(Category category){
		OntModel model = OntologyManager.getModel();
		Set<Category> subCatSet = category.getSubCategorySet();
		
		final Individual cat1 = model.createIndividual(OntologyManager.getNS() + category.getParent().getName(),
				model.getOntClass("category"));
		final Individual sub1 = model.createIndividual(OntologyManager.getNS() + category.getName(), 
				model.getOntClass("subCategory"));
		
		cat1.addProperty(model.getProperty("hasSubCategory"),sub1);
		subCatSet.add(category);			
		}
		
		
		
	
	public void addCatToSource(Source source, Category category) {
		OntModel model = OntologyManager.getModel();
		Set<Category> catInSourceSet = source.getCatInSourceSet();
		Set<Source> sourceWithCatSet = category.getSourceWithCatSet();
		
		final Individual cat1 = model.createIndividual(OntologyManager.getNS() + category.getName(),
				model.getOntClass("category"));
		Resource source1 = model.createResource(OntologyManager.getNS() + source.getName(), 
				model.getOntClass("source"));
		
		source1.addProperty(model.getProperty("hasCategory"),cat1);
		catInSourceSet.add(category);
		sourceWithCatSet.add(source);
		
					
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

	public void removeSource(Source source) {
		OntModel model = OntologyManager.getModel(); 
		final Individual source1 = model.createIndividual(OntologyManager.getNS() + source.getName(), 
				model.getOntClass("source"));
		
		source1.removeProperties();
	}
	
	
	//remove na ontologia não esta a funcionar
	public void removeCat(Category category) {
		OntModel model = OntologyManager.getModel();
		Resource cat1 = model.createResource(OntologyManager.getNS() + category.getName(), 
						model.getOntClass("category"));
		
		 cat1.removeProperties();
			
	}
	

}
