
import java.util.HashSet;
import java.util.Set;

import org.apache.jena.query.Dataset;
import org.apache.jena.query.ReadWrite;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.Property;
import org.apache.jena.rdf.model.RDFNode;
import org.apache.jena.rdf.model.Resource;
import org.apache.jena.rdf.model.Selector;
import org.apache.jena.rdf.model.SimpleSelector;
import org.apache.jena.rdf.model.Statement;
import org.apache.jena.rdf.model.StmtIterator;
import org.apache.jena.tdb.TDBFactory;
import org.apache.jena.util.FileManager;

public class TDBConnection {
	
private Dataset ds;
	
//path caminho para a pasta onde vai ser armazenada
	public TDBConnection( String path )
	{
		ds = TDBFactory.createDataset( path );
	}
	

// Carrega model/ Ontologia para TDB
	public void loadModel( String modelName, String path ){
		Model model = null;

		ds.begin( ReadWrite.WRITE );
		try
		{
			model = ds.getNamedModel( modelName );
			FileManager.get().readModel( model, path );
			ds.commit();
		}
		finally
		{
			ds.end();
		}
	}
	
	
// Adiciona triplos ao TDB
	public void addStatement( String modelName, String subject, String property, String object )
	{
		Model model = null;
		
		ds.begin( ReadWrite.WRITE );
		try
		{
			model = ds.getNamedModel( modelName );
			
			Statement stmt = model.createStatement
							 ( 	
								model.createResource( subject ), 
								model.createProperty( property ), 
								model.createResource( object ) 
							 );
			
			model.add( stmt );
			ds.commit();
		}
		finally
		{
			if( model != null ) model.close();
			ds.end();
		}
	}
	
	//Read List of triples from the TDB storeJava

	public Set<Statement> getStatements( String modelName, String subject, String property, String object )
	{
		Set<Statement> results = new HashSet<Statement>();
			
		Model model = null;
			
		ds.begin( ReadWrite.READ );
		try
		{
			model = ds.getNamedModel( modelName );
				
			Selector selector = new SimpleSelector(
						( subject != null ) ? model.createResource( subject ) : (Resource) null, 
						( property != null ) ? model.createProperty( property ) : (Property) null,
						( object != null ) ? model.createResource( object ) : (RDFNode) null
						);
				
			StmtIterator it = model.listStatements( selector );
			{
				while( it.hasNext() )
				{
					Statement stmt = it.next(); 
					results.add( stmt );
				}
			}
				
			ds.commit();
		}
		finally
		{
			if( model != null ) model.close();
			ds.end();
		}
			
		return results;
	}

	
	//Remove  triples from the TDB storeJava

	public void removeStatement( String modelName, String subject, String property, String object )
	{
		Model model = null;
		
		ds.begin( ReadWrite.WRITE );
		try
		{
			model = ds.getNamedModel( modelName );
			
			Statement stmt = model.createStatement
							 ( 	
								model.createResource( subject ), 
								model.createProperty( property ), 
								model.createResource( object ) 
							 );
					
			model.remove( stmt );
			ds.commit();
		}
		finally
		{
			if( model != null ) model.close();
			ds.end();
		}
	}
	


}
