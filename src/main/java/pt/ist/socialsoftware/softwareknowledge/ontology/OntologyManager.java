package pt.ist.socialsoftware.softwareknowledge.ontology;

import org.apache.jena.ontology.OntClass;
import org.apache.jena.ontology.OntModel;
import org.apache.jena.ontology.OntModelSpec;
import org.apache.jena.ontology.OntProperty;
import org.apache.jena.ontology.SymmetricProperty;
import org.apache.jena.query.DatasetAccessor;
import org.apache.jena.query.DatasetAccessorFactory;
import org.apache.jena.query.Query;
import org.apache.jena.query.QueryExecution;
import org.apache.jena.query.QueryExecutionFactory;
import org.apache.jena.query.QueryFactory;
import org.apache.jena.query.ResultSet;
import org.apache.jena.query.ResultSetFormatter;
import org.apache.jena.rdf.model.InfModel;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.reasoner.Reasoner;
import org.apache.jena.reasoner.ReasonerRegistry;
import org.apache.jena.vocabulary.XSD;
import org.apache.log4j.BasicConfigurator;

public class OntologyManager {
	static private OntModel ontModel = null;

	static public OntModel getModel() {
		return ontModel;
	}

	final private static String NS = "http://example/test#";

	static public String getNS() {
		return NS;
	}

	static public OntModel initModel(OntModelSpec type) {
		BasicConfigurator.configure();
		String serviceURI = "http://localhost:3030/ds/data";
		DatasetAccessor accessor;
		accessor = DatasetAccessorFactory.createHTTP(serviceURI);

		final String rule = OntModelSpec.OWL_MEM_RULE_INF.toString();
		final String rdf = OntModelSpec.OWL_MEM_RDFS_INF.toString();
		final String micro = OntModelSpec.OWL_MEM_MICRO_RULE_INF.toString();
		final String mini = OntModelSpec.OWL_MEM_MINI_RULE_INF.toString();
		final String trans = OntModelSpec.OWL_MEM_TRANS_INF.toString();
		final String rdfs = OntModelSpec.RDFS_MEM_RDFS_INF.toString();
		String s = type.toString();

		if (s.equals(rule)) {
			ontModel = ModelFactory.createOntologyModel(type);

		} else if (s.equals(rdf)) {
			ontModel = ModelFactory.createOntologyModel(type);
		} else if (s.equals(micro)) {
			ontModel = ModelFactory.createOntologyModel(type);
		} else if (s.equals(mini)) {
			ontModel = ModelFactory.createOntologyModel(type);
		} else if (s.equals(trans)) {
			ontModel = ModelFactory.createOntologyModel(type);
		} else if (s.equals(rdfs)) {
			ontModel = ModelFactory.createOntologyModel(type);
		} else {

			throw new IllegalArgumentException();

		}

		Reasoner reasoner = ReasonerRegistry.getOWLReasoner();
		InfModel infmodel = ModelFactory.createInfModel(reasoner, ontModel);
		final OntClass source = ontModel.createClass(NS + "source");
		final OntClass intern = ontModel.createClass(NS + "intern");
		final OntClass extern = ontModel.createClass(NS + "extern");
		final OntClass category = ontModel.createClass(NS + "category");
		final OntClass subCategory = ontModel.createClass(NS + "subcategory");

		category.addSubClass(subCategory);
		source.addSubClass(intern);
		source.addSubClass(extern);
		intern.addDisjointWith(extern);

		OntProperty author = ontModel.createOntProperty(NS + "author");
		OntProperty sourceId = ontModel.createOntProperty(NS + "sourceid");
		OntProperty insertDate = ontModel.createOntProperty(NS + "date");
		OntProperty sourceName = ontModel.createOntProperty(NS + "name");
		OntProperty cite = ontModel.createOntProperty(NS + "cite");
		OntProperty tools = ontModel.createOntProperty(NS + "tools");
		OntProperty implement = ontModel.createOntProperty(NS + "implement");
		OntProperty define = ontModel.createOntProperty(NS + "define");
		OntProperty example = ontModel.createOntProperty(NS + "example");
		OntProperty complement = ontModel.createOntProperty(NS + "complement");
		OntProperty summary = ontModel.createOntProperty(NS + "summary");
		OntProperty deny = ontModel.createOntProperty(NS + "deny");
		OntProperty useCat = ontModel.createOntProperty(NS + "usecat");
		OntProperty catId = ontModel.createOntProperty(NS + "catid");
		OntProperty catName = ontModel.createOntProperty(NS + "catname");
		OntProperty subCatid = ontModel.createOntProperty(NS + "subCid");
		OntProperty subCatName = ontModel.createOntProperty(NS + "subCname");

		OntProperty hasCategory = ontModel.createOntProperty(NS + "hascategory");
		hasCategory.convertToFunctionalProperty();
		OntProperty hasSubCategory = ontModel.createOntProperty(NS + "hassubcategory");

		SymmetricProperty relatedSource = ontModel.createSymmetricProperty(NS + "relatedsource");
		SymmetricProperty relationCat = ontModel.createSymmetricProperty(NS + "relationcat");

		author.setDomain(source);
		author.setRange(XSD.xstring);
		sourceId.setDomain(source);
		sourceId.setRange(XSD.integer);
		insertDate.setDomain(source);
		insertDate.setRange(XSD.xstring);
		sourceName.setDomain(source);
		sourceName.setRange(XSD.xstring);
		catId.setDomain(category);
		catId.setRange(XSD.integer);
		catName.setDomain(category);
		catName.setRange(XSD.xstring);
		subCatid.setDomain(subCategory);
		subCatid.setRange(XSD.integer);
		subCatName.setDomain(category);
		subCatName.setRange(XSD.xstring);
		hasCategory.setDomain(source);
		hasCategory.setRange(category);
		relatedSource.setDomain(source);
		relatedSource.setRange(source);
		relatedSource.addSubProperty(cite);
		relatedSource.addSubProperty(tools);
		relatedSource.addSubProperty(implement);
		relatedSource.addSubProperty(define);
		relatedSource.addSubProperty(example);
		relatedSource.addSubProperty(complement);
		relatedSource.addSubProperty(summary);
		relatedSource.addSubProperty(deny);
		hasSubCategory.setDomain(category);
		hasSubCategory.setRange(subCategory);
		relationCat.setDomain(category);
		relationCat.setRange(category);
		relationCat.addSubProperty(useCat);

		accessor.putModel(ontModel);
		return ontModel;
		
	}

	public void queryExecution(OntModel m, String search) {
		Query query = QueryFactory.create(search);

		QueryExecution qe = QueryExecutionFactory.create(query, m);
		ResultSet results = qe.execSelect();

		// Output query results
		ResultSetFormatter.out(System.out, results, query);

		qe.close();
	}

	

		// -------- Query devolve nome de todas as fontes
		String queryTest = "PREFIX j.0: <http://example/test#> "
				+ "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>" + "SELECT ?x " + "WHERE { "
				+ "      ?x j.0:name ?sourceName .} ";

		// ---------- Query1 Sub-categorias de uma categoria (Substituir cat3
		// pelo nome da categoria necessaria)---------------------

		String query1 = "PREFIX j.0: <http://example/test#> " + "SELECT ?subCName  " + "WHERE { "
				+ "		j.0:cat3 j.0:hassubcategory ?subCName .}  ";

		// -------- Query2 -------- Query devolve todas categorias

		String query2 = "PREFIX j.0: <http://example/test#> " + "SELECT ?catName " + "WHERE {"
				+ "       ?source j.0:catname ?catName   .}  ";

		// -------- Query3 -------- Query devolve todas sub-categorias

		String query3 = "PREFIX j.0: <http://example/test#> " + "SELECT ?subCatName " + "WHERE {"
				+ "       ?source j.0:hassubcategory ?subCatName   .}  ";

		// -------- Query4 -------- Query devolve sub-categorias de uma
		// fonte(relacao source->sub?)

		String query4 = "PREFIX j.0: <http://example/test#> " + "SELECT ?subCatName " + "WHERE {"
				+ "       j.0:source8Teste j.0:hassubcategory ?subCatName   .}  ";

		// ------- Query5 --------- Query devolve categorias associadas a uma
		// fonte

		String query5 = "PREFIX j.0: <http://example/test#> " + "SELECT ?catName " + "WHERE {"
				+ "       j.0:source8Teste j.0:hascategory ?catName   .}  ";

		// ------- Query6 --------- Query recebe uma fonte e devolve as fontes
		// relacionadas

		String query6 = "PREFIX j.0: <http://example/test#> " + "SELECT ?sourceName " + "WHERE {"
				+ "       j.0:source8Teste j.0:relatedsource ?sourceName   .}  ";

		// ----------Query7 --------- Query para devolver fontes com determinada
		// categoria (problema das symmetric properties)

		String query7 = "PREFIX j.0: <http://example/test#> " + "SELECT ?source " + "WHERE {"
				+ "       ?source j.0:hascategory ?cat ." + "		?cat j.0:catname ?cat1."
				+ "FILTER(?cat1 ='Qualidade Software')" + ".}  ";

		// ------------ Query8 ---------- Query para devolver fontes internas

		String query8 = "PREFIX j.0: <http://example/test#> "
				+ "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>" + "SELECT ?x " + "WHERE {"
				+ "       ?x rdf:type j.0:intern" + ".}  ";

		// --------- Query9 ------------ Query para devolver fontes externas

		String query9 = "PREFIX j.0: <http://example/test#> "
				+ "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>" + "SELECT ?x " + "WHERE {"
				+ "       ?x rdf:type j.0:extern" + ".}  ";

		

		// ontModel.write( System.out, "RDF/XML" );
		
	
}
