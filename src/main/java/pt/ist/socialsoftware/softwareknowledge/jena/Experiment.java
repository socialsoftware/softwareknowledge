package pt.ist.socialsoftware.softwareknowledge.jena;








import java.util.HashMap;
import java.util.Map;

import org.apache.jena.ontology.Individual;
import org.apache.jena.ontology.OntClass;
import org.apache.jena.ontology.OntModel;
import org.apache.jena.ontology.OntModelSpec;
import org.apache.jena.ontology.OntProperty;
import org.apache.jena.ontology.SymmetricProperty;
import org.apache.jena.query.DatasetAccessor;
import org.apache.jena.query.DatasetAccessorFactory;
import org.apache.jena.rdf.model.InfModel;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.rdf.model.RDFNode;
import org.apache.jena.rdf.model.Resource;
import org.apache.jena.rdf.model.Statement;
import org.apache.jena.rdf.model.StmtIterator;
import org.apache.jena.reasoner.Reasoner;
import org.apache.jena.reasoner.ReasonerRegistry;
import org.apache.jena.vocabulary.XSD;
import org.apache.log4j.BasicConfigurator;

public class Experiment {

	final private static String NS = "http://example/test#";
	
	
	public OntModel initModel(OntModelSpec type){
		
		BasicConfigurator.configure();
		String serviceURI = "http://localhost:3030/ds/data";
		DatasetAccessorFactory factory = null;
		DatasetAccessor accessor;
		accessor = factory.createHTTP(serviceURI);
		OntModel ontModel= null;
		final String rule = OntModelSpec.OWL_MEM_RULE_INF.toString();
		final String rdf = OntModelSpec.OWL_MEM_RDFS_INF.toString();
		final String micro = OntModelSpec.OWL_MEM_MICRO_RULE_INF.toString();
		final String mini = OntModelSpec.OWL_MEM_MINI_RULE_INF.toString();
		final String trans = OntModelSpec.OWL_MEM_TRANS_INF.toString();
		final String rdfs = OntModelSpec.RDFS_MEM_RDFS_INF.toString();
		String s = type.toString();
		
		
	
		
		if(s.equals(rule)){
			ontModel = ModelFactory.createOntologyModel(type);
			
		}
		else if(s.equals(rdf)){
			ontModel = ModelFactory.createOntologyModel(type);
		}
		else if(s.equals(micro)){
			ontModel = ModelFactory.createOntologyModel(type);
		}
		else if(s.equals(mini)){
			ontModel = ModelFactory.createOntologyModel(type);
		}
		else if(s.equals(trans)){
			ontModel = ModelFactory.createOntologyModel(type);
		}
		else if(s.equals(rdfs)){
			ontModel = ModelFactory.createOntologyModel(type);
		}
		else{
			
			throw new IllegalArgumentException();
			
		}
		
		Reasoner reasoner = ReasonerRegistry.getOWLReasoner();
		
		 final OntClass source = ontModel.createClass( NS + "source" );
		 final OntClass intern = ontModel.createClass( NS + "intern" );
		 final OntClass extern = ontModel.createClass( NS + "extern" );
		 final OntClass category = ontModel.createClass( NS + "category");
		 final OntClass subCategory = ontModel.createClass( NS + "subcategory");
		    
		    
		 category.addSubClass(subCategory);
		 source.addSubClass( intern );
		 source.addSubClass( extern );
		 intern.addDisjointWith(extern);
		    
		   
		 OntProperty author = ontModel.createOntProperty(NS + "author");
		 OntProperty sourceId = ontModel.createOntProperty( NS + "sourceid");
		 OntProperty insertDate = ontModel.createOntProperty( NS + "date");
		 OntProperty sourceName = ontModel.createOntProperty( NS + "name");
		 OntProperty cite = ontModel.createOntProperty( NS + "cite");
		 OntProperty tools = ontModel.createOntProperty( NS + "tools");
		 OntProperty implement = ontModel.createOntProperty( NS + "implement");
		 OntProperty define = ontModel.createOntProperty( NS + "define");
		 OntProperty example = ontModel.createOntProperty( NS + "example");
		 OntProperty complement = ontModel.createOntProperty( NS + "complement");
		 OntProperty summary = ontModel.createOntProperty( NS + "summary");
		 OntProperty deny = ontModel.createOntProperty( NS + "deny");
		 OntProperty useCat = ontModel.createOntProperty( NS + "usecat");
		 OntProperty catId = ontModel.createOntProperty( NS + "catid");
		 OntProperty catName = ontModel.createOntProperty( NS + "catname");
		 OntProperty subCatid = ontModel.createOntProperty( NS + "subCid");
		 OntProperty subCatName = ontModel.createOntProperty(NS + "subCname");
		    
		 OntProperty hasCategory = ontModel.createOntProperty( NS + "hascategory");
		 hasCategory.convertToFunctionalProperty();
		 OntProperty hasSubCategory = ontModel.createOntProperty( NS + "hassubcategory");
		  
		 SymmetricProperty relatedSource =  ontModel.createSymmetricProperty( NS + "relatedsource");
		 SymmetricProperty relationCat = ontModel.createSymmetricProperty( NS + "relationcat");
		    
		    
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
		
		 return ontModel;
	 
		
	}
	
	/*Falta conseguir aceder as propriedades do modelo
	public void addRelatedCat(SourceDTO d){
		 Map<String, String> map = new HashMap<>(); 
		    
		   
		   if(d.hasProperty(relatedSource)){
			   for(StmtIterator i = d.listProperties(relatedSource);i.hasNext();){
				   Statement s3 = i.next();
				   Resource sourceR = s3.getResource();
			   		
			   		
			   		for(StmtIterator k = sourceR.listProperties(hasCategory);k.hasNext();){
			   			Statement s2 = k.next();
			   			String catR7 = s2.getResource().getLocalName();
					
			   			for(StmtIterator j = d.listProperties(hasCategory);j.hasNext();){
							Statement s1 = j.next();
							String catR8 = s1.getResource().getLocalName();
							map.put(catR7, catR8);
						}
						
			   		}
			   }
			   
			   for (Map.Entry<String, String> entry : map.entrySet()) {
		    	    String key = entry.getKey();
		    	    String value = entry.getValue();
		    	    
		    	    d.addProperty(relationCat, key, value);
		    	    
		    	}
			 
		   }
	}
	
	*/
	
	
	public void teste() {
		BasicConfigurator.configure();
		String serviceURI = "http://localhost:3030/ds/data";
		DatasetAccessorFactory factory = null;
		DatasetAccessor accessor;
		accessor = factory.createHTTP(serviceURI);
		
		
		
		final OntModel ontModel = ModelFactory.createOntologyModel( OntModelSpec.OWL_MEM_RULE_INF );	
	    Reasoner reasoner = ReasonerRegistry.getOWLReasoner();
	    InfModel infmodel = ModelFactory.createInfModel(reasoner,ontModel);
	  
	    
	    final OntClass source = ontModel.createClass( NS + "source" );
	    final OntClass intern = ontModel.createClass( NS + "intern" );
	    final OntClass extern = ontModel.createClass( NS + "extern" );
	    final OntClass category = ontModel.createClass( NS + "category");
	    final OntClass subCategory = ontModel.createClass( NS + "subcategory");
	    
	    
	    category.addSubClass(subCategory);
	    source.addSubClass( intern );
	    source.addSubClass( extern );
	    intern.addDisjointWith(extern);
	    
	   
	    OntProperty author = ontModel.createOntProperty(NS + "author");
	    OntProperty sourceId = ontModel.createOntProperty( NS + "sourceid");
	    OntProperty insertDate = ontModel.createOntProperty( NS + "date");
	    OntProperty sourceName = ontModel.createOntProperty( NS + "name");
	    OntProperty cite = ontModel.createOntProperty( NS + "cite");
	    OntProperty tools = ontModel.createOntProperty( NS + "tools");
	    OntProperty implement = ontModel.createOntProperty( NS + "implement");
	    OntProperty define = ontModel.createOntProperty( NS + "define");
	    OntProperty example = ontModel.createOntProperty( NS + "example");
	    OntProperty complement = ontModel.createOntProperty( NS + "complement");
	    OntProperty summary = ontModel.createOntProperty( NS + "summary");
	    OntProperty deny = ontModel.createOntProperty( NS + "deny");
	    OntProperty useCat = ontModel.createOntProperty( NS + "usecat");
	    OntProperty catId = ontModel.createOntProperty( NS + "catid");
	    OntProperty catName = ontModel.createOntProperty( NS + "catname");
	    OntProperty subCatid = ontModel.createOntProperty( NS + "subCid");
	    OntProperty subCatName = ontModel.createOntProperty(NS + "subCname");
	    
	    OntProperty hasCategory = ontModel.createOntProperty( NS + "hascategory");
	    hasCategory.convertToFunctionalProperty();
	    OntProperty hasSubCategory = ontModel.createOntProperty( NS + "hassubcategory");
	  
	    SymmetricProperty relatedSource =  ontModel.createSymmetricProperty( NS + "relatedsource");
	    SymmetricProperty relationCat = ontModel.createSymmetricProperty( NS + "relationcat");
	    
	    
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
	    
	    
	 /*   
	    Iterator subclass = source.listSubClasses(false);
	    
	    while(subclass.hasNext()){
	    	OntClass sub = (OntClass) subclass.next();
	    	System.out.println(sub);
	    }
	    */
	    
	    
	    /* ----------- Sub -Categorias ----------- */
	    
	    final Individual sub1 = ontModel.createIndividual(NS + "sub1", subCategory);
	    sub1.addLiteral(subCatid,1);
	    sub1.addProperty(subCatName,"Analise Requisitos");
	    
	    final Individual sub2 = ontModel.createIndividual(NS + "sub2", subCategory);
	    sub2.addLiteral(subCatid,2);
	    sub2.addProperty(subCatName,"Elicitacao Requisitos");
	    
	    final Individual sub3 = ontModel.createIndividual(NS + "sub3", subCategory);
	    sub3.addLiteral(subCatid,3);
	    sub3.addProperty(subCatName,"Especificacao Requisitos");
	    
	    final Individual sub4 = ontModel.createIndividual(NS + "sub4", subCategory);
	    sub4.addLiteral(subCatid,4);
	    sub4.addProperty(subCatName,"Validacao Requisitos");
	    
	    final Individual sub5 = ontModel.createIndividual(NS + "sub5", subCategory);
	    sub5.addLiteral(subCatid,5);
	    sub5.addProperty(subCatName,"Arquitectura");
	    
	    final Individual sub6 = ontModel.createIndividual(NS + "sub6", subCategory);
	    sub6.addLiteral(subCatid,6);
	    sub6.addProperty(subCatName,"Interface Utilizador");
	    
	    final Individual sub7 = ontModel.createIndividual(NS + "sub7", subCategory);
	    sub7.addLiteral(subCatid,7);
	    sub7.addProperty(subCatName,"Estrategias e Metodos de Design");
	    
	    final Individual sub8 = ontModel.createIndividual(NS + "sub8", subCategory);
	    sub8.addLiteral(subCatid,8);
	    sub8.addProperty(subCatName,"Segurança");
	    
	    final Individual sub9 = ontModel.createIndividual(NS + "sub9", subCategory);
	    sub9.addLiteral(subCatid,9);
	    sub9.addProperty(subCatName,"Niveis de Testes");
	    
	    final Individual sub10 = ontModel.createIndividual(NS + "sub10", subCategory);
	    sub10.addLiteral(subCatid,10);
	    sub10.addProperty(subCatName,"Tecnicas de Testes");
	    
	    final Individual sub11 = ontModel.createIndividual(NS + "sub11", subCategory);
	    sub11.addLiteral(subCatid,11);
	    sub11.addProperty(subCatName,"Medidas de Testes");
	    
	    
	    final Individual sub12 = ontModel.createIndividual(NS + "sub12", subCategory);
	    sub12.addLiteral(subCatid,12);
	    sub12.addProperty(subCatName,"Processo de Teste");
	    
	    final Individual sub13 = ontModel.createIndividual(NS + "sub13", subCategory);
	    sub13.addLiteral(subCatid,13);
	    sub13.addProperty(subCatName,"Ferramentas para Teste Software");
	    
	    final Individual sub14 = ontModel.createIndividual(NS + "sub14", subCategory);
	    sub14.addLiteral(subCatid,14);
	    sub14.addProperty(subCatName,"Processo Manutencao");
	    
	    final Individual sub15 = ontModel.createIndividual(NS + "sub15", subCategory);
	    sub15.addLiteral(subCatid,15);
	    sub15.addProperty(subCatName,"Tecnicas para Manutencao");
	    
	    final Individual sub16 = ontModel.createIndividual(NS + "sub16", subCategory);
	    sub16.addLiteral(subCatid,16);
	    sub16.addProperty(subCatName,"Ferramentas para Manutencao");
	    
	    final Individual sub17 = ontModel.createIndividual(NS + "sub17", subCategory);
	    sub17.addLiteral(subCatid,17);
	    sub17.addProperty(subCatName,"Definicao Ambito");
	    
	    final Individual sub18 = ontModel.createIndividual(NS + "sub18", subCategory);
	    sub18.addLiteral(subCatid,18);
	    sub18.addProperty(subCatName,"Planeamento");
	    
	    final Individual sub19 = ontModel.createIndividual(NS + "sub19", subCategory);
	    sub19.addLiteral(subCatid,19);
	    sub19.addProperty(subCatName,"Revisao e Avaliacao");
	    
	    final Individual sub20 = ontModel.createIndividual(NS + "sub20", subCategory);
	    sub20.addLiteral(subCatid,20);
	    sub20.addProperty(subCatName,"Ferramentas de Gestao");
	    
	    final Individual sub21 = ontModel.createIndividual(NS + "sub21", subCategory);
	    sub21.addLiteral(subCatid,21);
	    sub21.addProperty(subCatName,"Qualidade Gestao Projectos");
	    
	    final Individual sub22 = ontModel.createIndividual(NS + "sub22", subCategory);
	    sub22.addLiteral(subCatid,22);
	    sub22.addProperty(subCatName,"Ferramentas Qualidade Software");
	    
	    final Individual sub23 = ontModel.createIndividual(NS + "sub23", subCategory);
	    sub23.addLiteral(subCatid,23);
	    sub23.addProperty(subCatName,"Tipos de Modelos");
	    
	    final Individual sub24 = ontModel.createIndividual(NS + "sub24", subCategory);
	    sub24.addLiteral(subCatid,24);
	    sub24.addProperty(subCatName,"Analise de Modelos");
	    
	    final Individual sub25 = ontModel.createIndividual(NS + "sub25", subCategory);
	    sub25.addLiteral(subCatid,25);
	    sub25.addProperty(subCatName,"Metodos ES");
	    
	    
	    /* ----------- Categorias ----------- */
	    final Individual cat1 = ontModel.createIndividual(NS + "cat1", category);
	    cat1.addLiteral(catId,1);
	    cat1.addProperty(catName,"Testes");
	    cat1.addProperty(hasSubCategory,sub9);
	    cat1.addProperty(hasSubCategory,sub10);
	    cat1.addProperty(hasSubCategory,sub11);
	    cat1.addProperty(hasSubCategory,sub12);
	    cat1.addProperty(hasSubCategory,sub13);
	  
	   
	    final Individual cat2 = ontModel.createIndividual(NS + "cat2", category);
	    cat2.addLiteral(catId,2);
	    cat2.addProperty(catName,"Requisitos");
	    cat2.addProperty(hasSubCategory,sub1);
	    cat2.addProperty(hasSubCategory,sub2);
	    cat2.addProperty(hasSubCategory,sub3);
	    cat2.addProperty(hasSubCategory,sub4);
	    
	    final Individual cat3 = ontModel.createIndividual(NS + "cat3", category);
	    cat3.addLiteral(catId,3);
	    cat3.addProperty(catName,"Design");
	    cat3.addProperty(hasSubCategory,sub5);
	    cat3.addProperty(hasSubCategory,sub6);
	    cat3.addProperty(hasSubCategory,sub7);
	    cat3.addProperty(hasSubCategory,sub8);
	    
	    
	    final Individual cat4 = ontModel.createIndividual(NS + "cat4", category);
	    cat4.addLiteral(catId,4);
	    cat4.addProperty(catName,"Manutencao");
	    cat4.addProperty(hasSubCategory,sub14);
	    cat4.addProperty(hasSubCategory,sub15);
	    cat4.addProperty(hasSubCategory,sub16);
	    cat4.addProperty(relationCat, cat1);
	   
	           
	    
	    final Individual cat5 = ontModel.createIndividual(NS + "cat5", category);
	    cat5.addLiteral(catId,5);
	    cat5.addProperty(catName,"Gestao ES");
	    cat5.addProperty(hasSubCategory,sub17);
	    cat5.addProperty(hasSubCategory,sub18);
	    cat5.addProperty(hasSubCategory,sub19);
	    cat5.addProperty(hasSubCategory,sub20);
	    
	    final Individual cat6 = ontModel.createIndividual(NS + "cat6", category);
	    cat6.addLiteral(catId,6);
	    cat6.addProperty(catName,"Qualidade Software");
	    cat6.addProperty(hasSubCategory,sub21);
	    cat6.addProperty(hasSubCategory,sub22);
	    
	    final Individual cat7 = ontModel.createIndividual(NS + "cat7", category);
	    cat7.addLiteral(catId,7);
	    cat7.addProperty(catName,"Metodos e Modelos");
	    cat7.addProperty(hasSubCategory,sub23);
	    cat7.addProperty(hasSubCategory,sub24);
	    cat7.addProperty(hasSubCategory,sub25);
	    ////////
	    cat7.addProperty(relationCat,cat4);
	    
	 
	    Resource source7Teste = ontModel.createResource(NS + "source7Teste", intern);
	    source7Teste.addLiteral(sourceId,7);
	    source7Teste.addProperty(sourceName,"Metodos e ModelosTeste");
	    source7Teste.addProperty(hasCategory,cat7);
	    source7Teste.addProperty(hasCategory,cat3);
	    source7Teste.addProperty(hasSubCategory,sub23);
	    source7Teste.addProperty(hasSubCategory,sub24);
	    source7Teste.addProperty(hasSubCategory,sub25);
	    
	    Resource source9Teste = ontModel.createResource(NS + "source9Teste", intern);
	    source9Teste.addLiteral(sourceId,9);
	    source9Teste.addProperty(sourceName,"Metodos e ModelosTeste");
	    source9Teste.addProperty(hasCategory,cat1);
	    source9Teste.addProperty(hasCategory,cat2);
	   
	    
	    Resource source8Teste = ontModel.createResource(NS + "source8Teste", intern);
	    source8Teste.addLiteral(sourceId,8);
	    source8Teste.addProperty(sourceName,"Metodos e Modelos2");
	    source8Teste.addProperty(hasCategory,cat4);
	   
	    source8Teste.addProperty(hasSubCategory,sub14);
	    source8Teste.addProperty(hasSubCategory,sub25);
	    source8Teste.addProperty(relatedSource,source7Teste);
	    source8Teste.addProperty(relatedSource,source9Teste);
	    
	    Map<String, String> map = new HashMap<>(); 
	    
		   
		   if(source8Teste.hasProperty(relatedSource)){
			   for(StmtIterator i = source8Teste.listProperties(relatedSource);i.hasNext();){
				   Statement s3 = i.next();
				   Resource sourceR = s3.getResource();
			   		
			   		//System.out.println(sourceR);
			   		for(StmtIterator k = sourceR.listProperties(hasCategory);k.hasNext();){
			   			Statement s2 = k.next();
			   			String catR7 = s2.getResource().getLocalName();
					
			   			for(StmtIterator j = source8Teste.listProperties(hasCategory);j.hasNext();){
							Statement s1 = j.next();
							String catR8 = s1.getResource().getLocalName();
							map.put(catR7, catR8);
						}
						
			   		}
			   }
			   
			   for (Map.Entry<String, String> entry : map.entrySet()) {
		    	    String key = entry.getKey();
		    	    String value = entry.getValue();
		    	    
		    	    source8Teste.addProperty(relationCat, key, value);
		    	    
		    	}
			 
		   }
	/////////
	   
	    	
	    
	   
	   	infmodel.prepare();
	    
	    	
	  
	  
	   
	    for(StmtIterator i = infmodel.listStatements(source8Teste, null  , (RDFNode) null); i.hasNext();){
			  Statement s = i.nextStatement();
			  System.out.println(s);
	    }
	    
	    
	    	
	 
	   
	   
	   //-------- Query devolve nome de todas as fontes
	    String queryTest = 
	    		"PREFIX j.0: <http://example/test#> " +
	    		"PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>" +
	    		"SELECT ?x " +
	    		"WHERE { " +
	    		"      ?x j.0:name ?sourceName .} ";
	    
	   
	    
	  
	    // ---------- Query1  Sub-categorias de uma categoria (Substituir cat3 pelo nome da categoria necessaria)--------------------- 
	    
	    String query1 = 
	    		"PREFIX j.0: <http://example/test#> " +
	    		"SELECT ?subCName  " +
	    		"WHERE { " +
	    		"		j.0:cat3 j.0:hassubcategory ?subCName .}  ";
	    
	    
	    // -------- Query2 -------- Query devolve todas categorias 
	    
	    String query2 =
	    		"PREFIX j.0: <http://example/test#> " +
	    		"SELECT ?catName " +
	    		"WHERE {" +
	    		"       ?source j.0:catname ?catName   .}  ";	
	    		
	    
	    
	    
	    
	    // -------- Query3 -------- Query devolve todas sub-categorias 
	    
	    String query3 =
	    		"PREFIX j.0: <http://example/test#> " +
	    		"SELECT ?subCatName " +
	    		"WHERE {" +
	    		"       ?source j.0:hassubcategory ?subCatName   .}  ";	
	    
	 // -------- Query4 -------- Query devolve sub-categorias de uma fonte(relacao source->sub?)
	    		
	    String query4 =
	    		"PREFIX j.0: <http://example/test#> " +
	    		"SELECT ?subCatName " +
	    		"WHERE {" +
	    		"       j.0:source8Teste j.0:hassubcategory ?subCatName   .}  ";

	    
	    
	 //------- Query5 --------- Query devolve categorias associadas a uma fonte
	    
	    String query5 =
	    		"PREFIX j.0: <http://example/test#> " +
	    		"SELECT ?catName " +
	    		"WHERE {" +
	    		"       j.0:source8Teste j.0:hascategory ?catName   .}  ";
	    
	//------- Query6 ---------  Query recebe uma fonte e devolve as fontes relacionadas
	    
	    String query6 =
	    		"PREFIX j.0: <http://example/test#> " +
	    		"SELECT ?sourceName " +
	    		"WHERE {" +
	    		"       j.0:source8Teste j.0:relatedsource ?sourceName   .}  ";
	    
	    
//----------Query7 --------- Query para devolver fontes com determinada categoria (problema das symmetric properties)
	    
	    String query7 =
	    		"PREFIX j.0: <http://example/test#> " +
	    		"SELECT ?source " +
	    		"WHERE {" +
	    		"       ?source j.0:hascategory ?cat ."
	    		+ "		?cat j.0:catname ?cat1."
	    		+ "FILTER(?cat1 ='Qualidade Software')"
	    		+ ".}  ";
	    
	    
//------------  Query8 ----------   Query para devolver fontes internas
	    
	    String query8 =
	    		"PREFIX j.0: <http://example/test#> " +
	    		"PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>" +
	    		"SELECT ?x " +
	    		"WHERE {" +
	    		"       ?x rdf:type j.0:intern"
	    		+ ".}  ";
	    
	    
	    
// --------- Query9 ------------  Query para devolver fontes externas
	    
	    String query9 =
	    		"PREFIX j.0: <http://example/test#> " +
	    		"PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>" +
	    		"SELECT ?x " +
	    		"WHERE {" +
	    		"       ?x rdf:type j.0:extern"
	    		+ ".}  ";
	    
	    /*
	    	Query query = QueryFactory.create(query7);

	    	// Execute the query and obtain results
	    	QueryExecution qe = QueryExecutionFactory.create(query, ontModel);
	    	ResultSet results = qe.execSelect();
	    	
	    	// Output query results	
	    	ResultSetFormatter.out(System.out, results, query);
	    	

	    	// Important - free up resources used running the query
	    	qe.close();
		*/
	    
	    //ontModel.write( System.out, "RDF/XML" );
	    //accessor.putModel(ontModel);
	}
}

