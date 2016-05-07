package pt.ist.socialsoftware.softwareknowledge.domain;

import org.apache.jena.ontology.OntProperty;

public class Properties {
	
	private OntProperty property;
	private String value;
	
	public Properties(){}
	
	public Properties(OntProperty property, String value){
		setProperty(property);
		setValue(value);
	}
	
	public OntProperty getProperty() {
		return property;
	}
	public void setProperty(OntProperty property) {
		this.property = property;
	}
	
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	
	

}
