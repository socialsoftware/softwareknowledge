package pt.ist.socialsoftware.softwareknowledge.domain;

public class RelatedCategory {
	
	private Category c1;
	private Category c2;
	private CategoryProperty property;
	
	public RelatedCategory(){}
	
	public RelatedCategory(Category c1, Category c2, CategoryProperty property){
		this.c1 = c1;
		this.c2 = c2;
		this.property = property;
	}

	public Category getC1() {
		return c1;
	}

	public void setC1(Category c1) {
		this.c1 = c1;
	}

	public Category getC2() {
		return c2;
	}

	public void setC2(Category c2) {
		this.c2 = c2;
	}

	public CategoryProperty getCategoryProperty() {
		return property;
	}

	public void setCategoryProperty(CategoryProperty property) {
		this.property = property;
	}
	
	

}
