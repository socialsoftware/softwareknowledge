package pt.ist.socialsoftware.softwareknowledge.jena;

import pt.ist.socialsoftware.softwareknowledge.utils.dto.CategoryDTO;
import pt.ist.socialsoftware.softwareknowledge.utils.dto.SourceDTO;

public class JenaInterface {

	static private JenaInterface instance = null;

	static public JenaInterface getInstance() {
		if (instance == null) {
			instance = new JenaInterface();
		}
		return instance;
	}

	private void JenaInterface() {

	}

	public void addSource(String name, CategoryDTO cat) {

	}
	
	public void addCatToSource(SourceDTO source, CategoryDTO cat){
		
	}
	
	public void removeSource(SourceDTO source){
		
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
	
	
	/*public void addPropertytoSource(SourceDTO source,OntProperty p , String value){
		source.addProperty(p,value);
	}*/

}
