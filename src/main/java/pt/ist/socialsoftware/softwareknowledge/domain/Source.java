package pt.ist.socialsoftware.softwareknowledge.domain;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import pt.ist.socialsoftware.softwareknowledge.ontology.OntologyInterface;
import pt.ist.socialsoftware.softwareknowledge.service.dto.CategoryDTO;
import pt.ist.socialsoftware.softwareknowledge.service.dto.SourceDTO;


//@Entity
//@Table(name="sources")
public class Source {
	
//	@Id
//	@GeneratedValue(strategy = GenerationType.AUTO)
//	private long id;
	
	private SoftwareKnowledge softwareknowledge;
	
//	@NotNull
//	@Column(name="author")
	private String author;
	
//	@NotNull
//	@Column(name="sourceId")
	private int sourceId;
	
//	@NotNull
//	@Column(name="insertDate")
	private String insertDate;
	
//	@NotNull
//	@Column(name="sourceName")
	private String name;
	
	//@ManyToMany(mappedBy="sourceWithCatSet",fetch=FetchType.EAGER)
	private Set<Category> catInSourceSet;
	private Set<RelatedSource> relatedSourceSet;
	
//	@NotNull
//	@Column(name="link")
	private String link;
	
	private static int sourceIdCounter=0;

	public Source() {
	}

	public Source(SoftwareKnowledge softwareKnowledge, int sourceId, String name, String author, String insertDate,
			String link) {
		setSoftwareKnowledge(softwareKnowledge);
		setSourceId(Source.sourceIdCounter++);
		setName(name);
		setInsertDate(insertDate);
		setAuthor(author);
		setLink(link);
		catInSourceSet = new HashSet<Category>();
		relatedSourceSet = new HashSet<RelatedSource>();
		//softwareKnowledge.addSource(this);
		OntologyInterface.getInstance().addSource(this);
	}

	public Source(SoftwareKnowledge softwareKnowledge, int sourceId, String name, String author, String link,Set<Category> catList) {
		setSoftwareKnowledge(softwareKnowledge);
		setSourceId(Source.sourceIdCounter++);
		setName(name);
		setAuthor(author);
		setLink(link);
		setCatInSourceSet(catList);
		relatedSourceSet = new HashSet<RelatedSource>();
	//	softwareKnowledge.addSource(this);
		OntologyInterface.getInstance().addSource(this);
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public SoftwareKnowledge getSoftwareKnowledge() {
		return softwareknowledge;
	}

	public void setSoftwareKnowledge(SoftwareKnowledge softwareKnowledge) {
		this.softwareknowledge = softwareKnowledge;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public int getSourceId() {
		return sourceId;
	}

	public void setSourceId(Integer sourceId) {
		this.sourceId = sourceId;
	}

	public String getInsertDate() {
		return insertDate;
	}

	public void setInsertDate(String insertDate) {
		this.insertDate = insertDate;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public SoftwareKnowledge getSoftwareknowledge() {
		return softwareknowledge;
	}

	public void setSoftwareknowledge(SoftwareKnowledge softwareknowledge) {
		this.softwareknowledge = softwareknowledge;
	}

	public Set<Category> getCatInSourceSet() {
		return catInSourceSet;
	}

	public void setCatInSourceSet(Set<Category> catInSourceSet) {
		this.catInSourceSet = catInSourceSet;
	}

	public Set<RelatedSource> getRelatedSourceSet() {
		return relatedSourceSet;
	}

	public void setRelatedSourceSet(Set<RelatedSource> relatedSourceSet) {
		this.relatedSourceSet = relatedSourceSet;
	}

	public void addRelatedSource(Source source, SourceProperty property) {
		RelatedSource rs = new RelatedSource(this, source, property);
		this.getRelatedSourceSet().add(rs);
	}

	public SourceDTO getDTO() {
	     Set<CategoryDTO> categories = getCatInSourceSet().stream().map(c->c.getDTO()).collect(Collectors.toSet());
	     return new SourceDTO(getAuthor(), getSourceId(), getName(), getLink(), categories);
	}

}
