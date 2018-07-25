package com.sky.lr.test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Iterator;

import com.hp.hpl.jena.ontology.ComplementClass;
import com.hp.hpl.jena.ontology.DatatypeProperty;
import com.hp.hpl.jena.ontology.Individual;
import com.hp.hpl.jena.ontology.IntersectionClass;
import com.hp.hpl.jena.ontology.ObjectProperty;
import com.hp.hpl.jena.ontology.OntClass;
import com.hp.hpl.jena.ontology.OntModel;
import com.hp.hpl.jena.ontology.OntModelSpec;
import com.hp.hpl.jena.ontology.UnionClass;
import com.hp.hpl.jena.query.Query;
import com.hp.hpl.jena.query.QueryExecution;
import com.hp.hpl.jena.query.QueryExecutionFactory;
import com.hp.hpl.jena.query.QueryFactory;
import com.hp.hpl.jena.query.QuerySolution;
import com.hp.hpl.jena.query.ResultSet;
import com.hp.hpl.jena.query.ResultSetFormatter;
import com.hp.hpl.jena.rdf.model.InfModel;
import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.rdf.model.RDFList;
import com.hp.hpl.jena.rdf.model.RDFNode;
import com.hp.hpl.jena.rdf.model.Resource;
import com.hp.hpl.jena.reasoner.Reasoner;
import com.hp.hpl.jena.reasoner.ReasonerRegistry;
import com.hp.hpl.jena.reasoner.ValidityReport;
import com.sky.lr.ontology.OntologyMeta;

public class TestSCORAgent {
//	static String filename = "f:/bfoexample/BFO_LR_0709.owl";
	static String filename = "d:/LROntology/BFO_LR_0805_SAM.owl";
	static String URI = "http://purl.obolibrary.org/obo/bfo.owl";
	static String NS = URI + "#";
	
	public void parseOWLFile(OntModel OM){
		//Output content
		for (Iterator i = OM.listClasses(); i.hasNext();) {
			
			OntClass c = (OntClass) i.next();
			
			if (!c.isAnon()) {
				
				System.out.println("Class:" + c.getLocalName()+" URI: "+c.getURI() +" " + c.getModel().getGraph().getPrefixMapping().shortForm(c.getURI()));
			}
							
		}
		// list OntClass
		OntClass actorC = OM.getOntClass(NS + "Actor");
		for (Iterator<OntClass> i = actorC.listSuperClasses(); i.hasNext(); ) {
			  OntClass c = i.next();
			  System.out.println( c.getURI() );
		}
		
		//list the inferred types
		Individual actor = OM.getIndividual(NS+"cAgent_1");
		for (Iterator<Resource> i = actor.listRDFTypes(true); i.hasNext(); ) {
		    System.out.println( actor.getURI() + " is inferred to be in class " + i.next() );
		}
	}
	
	public void createClass(OntModel OM){
//		//Create a new Class
//		OntClass c0 = OM.createClass(NS + "c0");
//		OntClass c1 = OM.createClass(NS + "c1");
//		OntClass c2 = OM.createClass(NS + "c2");
//		RDFList cs = OM.createList( new RDFNode[] {c0, c1, c2} );
//		
//		IntersectionClass insC = OM.createIntersectionClass(NS + "InterstClass", cs);
//		
//		UnionClass uniC = OM.createUnionClass(NS + "UniClass", cs);
//		
//		ComplementClass nC = OM.createComplementClass(NS + "ComClass", c0);
		OntClass cc = OM.createClass(NS + "asSwitch");
		
		cc.addSuperClass(OM.getOntClass("http://purl.obolibrary.org/obo/BFO_0000023"));
		cc.setLabel("asNetworkSwitch", "en");  // Label is the name of the class
	}
	
	public void createIndividual(OntModel OM, String ClassName, String individualName){
		
		OntClass contentAgent = OM.getOntClass(NS+ ClassName);
		
		OM.createIndividual(NS+individualName, contentAgent);
	}
	
	public void createObjectProperty(OntModel OM){
		
		ObjectProperty hasSpec = OM.createObjectProperty( NS + "hasSpec" );
		
		hasSpec.addDomain(OM.getOntClass(NS + "SystemEvent"));
		
		hasSpec.addLabel("has specification", "en");
		
		hasSpec.addSuperProperty(OM.getProperty(NS + "LRProperty"));
		
		hasSpec.convertToFunctionalProperty();
		
		System.out.println("hasSpec is funtional ?" +hasSpec.isFunctionalProperty());
	}
	
	public void createDatatypeProperty(OntModel OM){
		
		DatatypeProperty hasSize = OM.createDatatypeProperty( NS + "hasSize" );
		
		hasSize.addDomain(OM.getOntClass(NS+"Storage"));
		
        hasSize.addRange(OM.getResource("http://www.w3.org/2001/XMLSchema#long"));
        
        hasSize.addSuperProperty(OM.getDatatypeProperty(NS + "LRDataProperty"));
	}
	
	public void removeStatement(OntModel OM){
		Individual taskInd = OM.getIndividual(OntologyMeta.DefaultNS+"task1");
		Individual conInd  = OM.getIndividual(OntologyMeta.DefaultNS+"context1");
		//Remove (s,p,x), x is the DatatypeProperty of s
		OM.remove(taskInd, OM.getObjectProperty(OntologyMeta.DefaultNS+"RelevantTo"),conInd);
		//Remove (s,p,o), o is the ObjectProperty of s
//		OM.remove(OM.createStatement(taskInd, OM.getObjectProperty(OntologyMeta.DefaultNS+"RelevantTo"),conInd));
	}
	
	public void sparqlQuery(OntModel OM){
		//Test query with SPARQL 
        String queryString = 
        		"PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>"+
        		"SELECT ?subject ?object" +
        		"WHERE { ?subject rdfs:subClassOf ?object }";
        
        String queryString1 = 
        		"PREFIX res: <http://purl.obolibrary.org/obo/bfo.owl#>"+
        		"SELECT ?instance "+ 
        		"WHERE {?instance res:hasSize ?size.FILTER(?size >= 1073741824)}";
        
        String queryString2 = 
        		"PREFIX res: <http://purl.obolibrary.org/obo/bfo.owl#>" +
                "SELECT ?subject ?object " +
	            "WHERE { ?subject res:RelevantTo ?object }" +
                "ORDER BY DESC(?object)" +
                "LIMIT 18";
        
        Query query = QueryFactory.create(queryString2);
        
        // Execute the query and obtain results
        QueryExecution qe = QueryExecutionFactory.create(query, OM);
        ResultSet results = qe.execSelect();
        
        /* Output the query result with the specified format
        while(results.hasNext()){
        	QuerySolution qs = results.next();
        	System.out.println(qs.get("instance"));
        }
        */
        // Output query results	with the default format
        
        ResultSetFormatter.out(System.out, results, query);
        
        qe.close();
        
	}
	
	public void testReasoner(OntModel OM){
		//Create a reasoner\
		Reasoner reasoner = ReasonerRegistry.getRDFSReasoner();		
		
		//Add reasoner into the OntModel
		InfModel infm = ModelFactory.createInfModel(reasoner, OM);
		//Test the reasoner
		ValidityReport validity = infm.validate();
		if (validity.isValid()) {
		    System.out.println("OK");
		} else {
		    System.out.println("Conflicts");
		    for (Iterator i = validity.getReports(); i.hasNext(); ) {
		        ValidityReport.Report report = (ValidityReport.Report)i.next();
		        System.out.println(" - " + report);
		    }
		}
	}
	
	public static void main(String args[]) throws FileNotFoundException{
		System.err.println("Unknow resource type required by user! Please check the goal spec!");
		TestSCORAgent scorAgent = new TestSCORAgent();
		// create a default Model with the specified "RDF/XML"
		Model initM = ModelFactory.createDefaultModel();
		
		//Create a base model
		OntModel baseM = ModelFactory.createOntologyModel();
		
		// Create an ontology Model with a specified reasoner
		// We can create different OntModels depends on our need from the baseM
		OntModel infM = ModelFactory.createOntologyModel(OntModelSpec.OWL_DL_MEM_RDFS_INF);
		
		// Read the owl file from disk
		File file = new File(filename);
		FileInputStream fis = null;
		
		try{
			
			fis = new FileInputStream(file);

			infM.read(fis, null);	
			
			OntClass oc = infM.getOntClass(OntologyMeta.DefaultNS+ "AtomicTask");
			
			Iterator<Individual> it = infM.listIndividuals(oc);
			
			Individual ind = null;
			while(it.hasNext()) {
			    ind = it.next();
			    System.out.println(ind.getLocalName());
			}
//			scorAgent.createClass(infM);
			
//			//Test 1: Paring the OWL file
//			scorAgent.parseOWLFile(infM);
//			//Test 2: Create a new class
//			scorAgent.createClass(infM);
//			//Test 3: Create a new individual
//			scorAgent.createIndividual(infM, "ContentAgent", "ind1");
//			//Test 4: Create a new ObjectProperty
//			scorAgent.createObjectProperty(infM);
//			//Test 5: Create a new DatatypeProperty
//			scorAgent.createDatatypeProperty(infM);
			//Test 6: SPARQL query test
//			scorAgent.sparqlQuery(infM);
//			//Test 7: Reasoner test
//			scorAgent.testReasoner(infM);
			//Test 8: Remove statement test
			scorAgent.removeStatement(infM);
			
		}catch(IOException ioe){
			ioe.printStackTrace();
		}finally{
			try {
				fis.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
		//Flush out the cache in memory
		FileOutputStream fos = null;
		try{
			fos = new FileOutputStream(file);
			infM.write(fos, null);

		}catch(IOException ioe){
			ioe.printStackTrace();
		}finally{
			try {
				fos.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}

}
