package pt.ist.socialsoftware.softwareknowledge.domain;

public class Source {
	
	private String author;
	private int sourceId;
	private String insertDate;
	private String sourceName;
	
	public Source(){}
	
	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public int getSourceId() {
		return sourceId;
	}

	public void setSourceId(int sourceId) {
		this.sourceId = sourceId;
	}

	public String getInsertDate() {
		return insertDate;
	}

	public void setInsertDate(String insertDate) {
		this.insertDate = insertDate;
	}

	public String getSourceName() {
		return sourceName;
	}

	public void setSourceName(String sourceName) {
		this.sourceName = sourceName;
	}

	public Source(Integer id, String sourceName, String insertDate,String author){
		this.setSourceId(id);
		this.setSourceName(sourceName);
		this.setInsertDate(insertDate);
		this.setAuthor(author);
		
	}
	
	public Source getSource(Source s){
		return s;
	}
	/*
	public void addCatToSource(Source s, String c){
		 Map<Integer, String> map = new HashMap<>();
		 Integer val = map.size() +1;
		 for(int i=0;i<map.size();i++)
		 {
			 if(map[i].getValue().equals(c)){
					s.addProperty("hasCategory",c);
				}
				else{
					map.put(val,c);
				}
		 }
		
		
		
	}*/
}
