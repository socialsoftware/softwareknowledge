package pt.ist.socialsoftware.softwareknowledge.domain;

public class RelatedSource {
	private SourceProperty sourceProperty;
	private Source s1;
	private Source s2;
	
	public RelatedSource(Source s1, Source s2, SourceProperty property){
		
		this.s1 = s1;
		this.s2 = s2;
		this.sourceProperty = property;
				
	}

	public SourceProperty getSourceProperty() {
		return sourceProperty;
	}

	public void setSourceProperty(SourceProperty sourceProperty) {
		this.sourceProperty = sourceProperty;
	}

	public Source getS1() {
		return s1;
	}

	public void setS1(Source s1) {
		this.s1 = s1;
	}

	public Source getS2() {
		return s2;
	}

	public void setS2(Source s2) {
		this.s2 = s2;
	}

	

}
