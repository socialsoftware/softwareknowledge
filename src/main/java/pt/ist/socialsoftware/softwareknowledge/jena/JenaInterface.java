package pt.ist.socialsoftware.softwareknowledge.jena;

import org.apache.jena.ontology.OntProperty;

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

	public void addSource() {

	}
	
	public void addCatToSource(){
		
	}
	
	public void addPropertytoSource(SourceDTO source,OntProperty p , String value){
		source.addProperty(p,value);
	}

}
