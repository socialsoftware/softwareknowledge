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

	public void addSource(Integer id, String name, CategoryDTO cat) {

	}
	
	public void addCatToSource(SourceDTO source, CategoryDTO cat){
		
	}
	
	/*public void addPropertytoSource(SourceDTO source,OntProperty p , String value){
		source.addProperty(p,value);
	}*/

}
