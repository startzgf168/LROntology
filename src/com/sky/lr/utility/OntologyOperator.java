package com.sky.lr.utility;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashSet;
import java.util.Iterator;






import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import org.mindswap.pellet.jena.PelletReasonerFactory;
import org.semanticweb.owlapi.apibinding.OWLManager;
import org.semanticweb.owlapi.model.OWLEntity;
import org.semanticweb.owlapi.model.OWLOntology;
import org.semanticweb.owlapi.model.OWLOntologyCreationException;
import org.semanticweb.owlapi.model.OWLOntologyManager;

import com.hp.hpl.jena.datatypes.xsd.XSDDatatype;
import com.hp.hpl.jena.ontology.DatatypeProperty;
import com.hp.hpl.jena.ontology.Individual;
import com.hp.hpl.jena.ontology.ObjectProperty;
import com.hp.hpl.jena.ontology.OntClass;
import com.hp.hpl.jena.ontology.OntModel;
import com.hp.hpl.jena.ontology.OntModelSpec;
import com.hp.hpl.jena.ontology.OntResource;
import com.hp.hpl.jena.query.Query;
import com.hp.hpl.jena.query.QueryExecution;
import com.hp.hpl.jena.query.QueryExecutionFactory;
import com.hp.hpl.jena.query.QueryFactory;
import com.hp.hpl.jena.query.QuerySolution;
import com.hp.hpl.jena.query.ResultSet;
import com.hp.hpl.jena.query.ResultSetFormatter;
import com.hp.hpl.jena.rdf.model.InfModel;
import com.hp.hpl.jena.rdf.model.Literal;
import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.rdf.model.Property;
import com.hp.hpl.jena.rdf.model.RDFNode;
import com.hp.hpl.jena.rdf.model.Resource;
import com.hp.hpl.jena.rdf.model.Statement;
import com.hp.hpl.jena.reasoner.Reasoner;
import com.hp.hpl.jena.reasoner.ReasonerRegistry;
import com.hp.hpl.jena.reasoner.rulesys.GenericRuleReasoner;
import com.sky.lr.agent.SCORAgent;
import com.sky.lr.ontology.OntologyMeta;

public class OntologyOperator {
	
	//Intialise the ontology
	public  OntModel initialiseOntology(){		
//		// create a default Model with the specified "RDF/XML"
//		Model initM = ModelFactory.createDefaultModel();
//		
//		//Create a base model
//		OntModel baseM = ModelFactory.createOntologyModel();
		
		// Create an ontology Model with a specified reasoner
		// We can create different OntModels depends on our need from the baseM
		OntModel infM = ModelFactory.createOntologyModel(OntModelSpec.OWL_DL_MEM_RDFS_INF);
		
		File file = null;		
		FileInputStream fis = null;
		
		try{
			// Read the owl file from disk
			file = new File(OntologyMeta.filepath);
			fis = new FileInputStream(file);
         
			//Read the owl file into the OntModel
			infM.read(fis, null);	
						
		}catch(FileNotFoundException nfe){
			nfe.printStackTrace();			
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
		return infM;		
	}
	
	//Intialise the ontology
	//Internal reasoner configuration can only guarantee sound but not 
	//complete, so we need to use the external reasoner, such as Pellet, FaCt,etc 
	public InfModel initialiseOntologyWithInternalReasoner(){	
		
		OntModel schemaM = ModelFactory.createOntologyModel(OntModelSpec.OWL_DL_MEM_TRANS_INF);
		
		InfModel owlInfM = null;
		//Create a reasoner
		Reasoner owlreasoner = ReasonerRegistry.getOWLReasoner();
		
		//External reasoner
	//	PelletReasoner pelletReasoner = PelletReasonerFactory.getInstance().createReasoner();
		
		
		File file = null;		
		FileInputStream fis = null;
		
		try{
			// Read the owl file from disk
			file = new File(OntologyMeta.filepath);
			fis = new FileInputStream(file);
           
			// Create an inference model with internal owl reasoner
			owlInfM = ModelFactory.createInfModel(owlreasoner, schemaM);
			
			//Read the owl file into the InfModel
			owlInfM.read(fis, null);	
						
		}catch(FileNotFoundException nfe){
			nfe.printStackTrace();			
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
		return owlInfM;		
	}
	
	//Intialise the ontology
	//Internal reasoner configuration can only guarantee sound but not 
	//complete, so we need to use the external reasoner, such as Pellet, FaCt,etc 
	public InfModel initialiseOntologyWithPelletReasoner(){	
		
		// create an ontology model	with external reasoner
		Model dataM = ModelFactory.createDefaultModel();
		
		Reasoner pelletReasoner = PelletReasonerFactory.theInstance().create();
		
		InfModel owlInfM = null;	
		
		File file = null;		
		FileInputStream fis = null;
		
		try{
			// Read the owl file from disk
			file = new File(OntologyMeta.filepath);
			fis = new FileInputStream(file);
			
			// Create an inference model with the external reasoner		
			owlInfM = ModelFactory.createInfModel(pelletReasoner, dataM);
			
			//Read the owl file into the InfModel
			owlInfM.read(fis, null);	
						
						
		}catch(FileNotFoundException nfe){
			nfe.printStackTrace();			
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
		return owlInfM;		
	}
	
	//Intialise the ontology
	//Internal reasoner configuration can only guarantee sound but not 
	//complete, so we need to use the external reasoner, such as Pellet, FaCt,etc 
	public InfModel initialiseOntologyWithPelletReasoner(OntModel ontM){	
		
		// create an ontology model	with external reasoner		
		Reasoner pelletReasoner = PelletReasonerFactory.theInstance().create();

		InfModel owlInfM = ModelFactory.createInfModel(pelletReasoner, ontM);
		
		return owlInfM;		
	}
	
	//Intialise the ontology
	//Internal reasoner configuration can only guarantee sound but not 
	//complete, so we need to use the external reasoner, such as Pellet, FaCt,etc 
	public InfModel initialiseOntologyWithHermitReasoner(){	
		//Create a default model
		Model dataM = ModelFactory.createDefaultModel();
		//Create a manage
		OWLOntologyManager manager = OWLManager.createOWLOntologyManager();  

		OWLOntology localAcademic = null;
		
		org.semanticweb.HermiT.Reasoner hermitReasoner = null;
		
		InfModel owlInfM = null;	
		
		File file = null;		
		

		// Read the owl file from disk
		file = new File(OntologyMeta.filepath);
		// Now load the local copy 
		try {
			localAcademic = manager.loadOntologyFromOntologyDocument(file);
		} catch (OWLOntologyCreationException e) {
				// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		// Create an inference model with the external reasoner		
		hermitReasoner = new org.semanticweb.HermiT.Reasoner(localAcademic);
			
		System.out.println(hermitReasoner.isConsistent());
		
			
		owlInfM = ModelFactory.createInfModel((Reasoner)hermitReasoner, dataM);									
						
		return owlInfM;		
	}
	
	//Flush out the cache in the memory, that is, write the changes of Ontolgoy back to the original disk file
	public void closeOntology(Model ontM){
		File file = null;
		//Flush out the cache in memory
		FileOutputStream fos = null;
		try{
			file = new File(OntologyMeta.filepath);
			fos = new FileOutputStream(file);
			ontM.write(fos, null);

		}catch(FileNotFoundException nfe){
			nfe.printStackTrace();			
		}catch(IOException ioe){
			ioe.printStackTrace();
		}finally{
			try {
				fos.close();
				ontM.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	//Flush out the cache in the memory, that is, write the changes of Ontolgoy back to a disk file
	public static void closeOntology(OntModel ontM, String filepath){
		File file = null;
		FileOutputStream fos = null;
		try{
		    file= new File(filepath);
			fos = new FileOutputStream(file);
			ontM.write(fos, null);

		}catch(FileNotFoundException nfe){
			nfe.printStackTrace();			
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
	
	//Output content in the OWL file
	public void parseOWLFile(OntModel ontM){
		//Output content
		for (Iterator i = ontM.listClasses(); i.hasNext();) {
			
			OntClass c = (OntClass) i.next();
			
			if (!c.isAnon()) {
				
				System.out.println("Class:" + c.getLocalName()+" URI: "+c.getURI() +" " + c.getModel().getGraph().getPrefixMapping().shortForm(c.getURI()));
			}
							
		}
		// list OntClass
		OntClass actorC = ontM.getOntClass(OntologyMeta.DefaultNS + "Actor");
		for (Iterator<OntClass> i = actorC.listSuperClasses(); i.hasNext(); ) {
			  OntClass c = i.next();
			  System.out.println( c.getURI() );
		}
		
		//list the inferred types
		Individual actor = ontM.getIndividual(OntologyMeta.DefaultNS+"cAgent_1");
		for (Iterator<Resource> i = actor.listRDFTypes(true); i.hasNext(); ) {
		    System.out.println( actor.getURI() + " is inferred to be in class " + i.next() );
		}
	}
	
	//Create a class in the Ontology
	public OntClass createClass(OntModel ontM, String className, String superClassURI){
		
		OntClass cc = ontM.createClass(OntologyMeta.DefaultNS + className);
		cc.setLabel(className, "en");  // Label is the name of the class
		
		//Set up its superclass, which is just the place it's put
		cc.addSuperClass(ontM.getOntClass(superClassURI));
		
		return cc;
	}
	
	//Delete a class in the ontolgoy
	public void removeClass(OntModel ontM, String className){
		OntClass cc = ontM.getOntClass(OntologyMeta.DefaultNS + className);
		ontM.remove(ontM.listStatements(cc, (Property)null, (RDFNode)null));
	}
	
	//Create a individual in the ontology
	//Firstly, check if there is a same individual, if yes, rename the name; otherwise, create it.
	public Individual createIndividual(OntModel ontM, String ClassName, String individualName, String str){
		
		OntClass oc = ontM.getOntClass(OntologyMeta.DefaultNS+ ClassName);
		Individual newInd = null;
		
		Iterator<Individual> it = ontM.listIndividuals(oc);
		
		Individual ind = null;
		
		//Just in case to avoid the duplicate name
		while(it.hasNext()) {
		    ind = it.next();
		    if(individualName.equals(ind.getLocalName()))
		    {
		    	return null;
		    }
		}
		
		newInd = ontM.createIndividual(OntologyMeta.DefaultNS+individualName, oc);
		
		newInd.setLabel(individualName, "en");
		//Method 1 to set the datatypeproperty value of an individual
//		Literal date = ontM.createTypedLiteral("2015-7-21", XSDDatatype.XSDdateTime);
//		Statement ss = ontM.createStatement(newInd, ontM.getDatatypeProperty(OntologyMeta.DefaultNS+"hasDate"), date);
//		ontM.add(ss);
	    //Method 2 to set the datatype property value of an individual
//		double dd = 100.0;
//		RDFNode rdf = ontM.createTypedLiteral(dd);
//		newInd.setPropertyValue(ontM.getDatatypeProperty(OntologyMeta.DefaultNS+"hasSize"), rdf);
				
		//We can also set its property here
		//newInd.setPropertyValue(property, value);
		return newInd;
	}
	//Create a individual in the ontology
	//Firstly, check if there is a same individual, if yes, rename the name; otherwise, create it.
	public static Individual createIndividual(OntModel ontM, String ClassName, String individualName){
		
		OntClass oc = ontM.getOntClass(OntologyMeta.DefaultNS+ ClassName);
		Individual newInd = null;
		
		Iterator<Individual> it = ontM.listIndividuals(oc);
		
		Individual ind = null;
		
		//Just in case to avoid the duplicate name
		while(it.hasNext()) {
		    ind = it.next();
		    if(individualName.equals(ind.getLocalName()))
		    {
		    	return null;
		    }
		}
		
		newInd = ontM.createIndividual(OntologyMeta.DefaultNS+individualName, oc);
		
		return newInd;
	}
	//Remove a individual
	public void removeIndividual(OntModel ontM, String individualName, String individualURI){
		
	}
	
	//Create a new object property
	public void createObjectProperty(OntModel ontM, String objPropName, String superObjPropURI){
		
		ObjectProperty objProp = ontM.createObjectProperty(OntologyMeta.DefaultNS + objPropName );
		
		objProp.addSuperProperty(ontM.getProperty(superObjPropURI));
		
		/*
		 * We can also set its property here
		objProp.addDomain(OM.getOntClass(OntologyMeta.NS + "SystemEvent"));
		
		objProp.addLabel("has Specification", "en");
		
		objProp.addSuperProperty(OM.getProperty(OntologyMeta.NS + "LRProperty"));
		
		objProp.convertToFunctionalProperty();
		*/
	}
	
	//Create a new Datatype property
	public void createDatatypeProperty(OntModel ontM, String dtName, String superPropertyURI){
		
		DatatypeProperty dtP = ontM.createDatatypeProperty( OntologyMeta.DefaultNS  + dtName );
		
        dtP.addSuperProperty(ontM.getDatatypeProperty(superPropertyURI));
        
		/*
		 * We can also set its property here
		dtP.addDomain(ontM.getOntClass(NS+"Storage"));
		
        dtP.addRange(OM.getResource("http://www.w3.org/2001/XMLSchema#long"));
        */

	}
	
	
	//SPARQL query
	public void sparqlQuery(Model ontM, String queryString){
        
        Query query = QueryFactory.create(queryString);
        
        // Execute the query and obtain results
        QueryExecution qe = QueryExecutionFactory.create(query, ontM);
        ResultSet results = qe.execSelect();
        
        // Output query results	with the default format      
        ResultSetFormatter.out(System.out, results, query);
        
        qe.close();       
	}
	
	public static ResultSet sparqlInferenceQuery(InfModel infM, String queryString){
        Query query = QueryFactory.create(queryString);
        
        // Execute the query and obtain results
        QueryExecution qe = QueryExecutionFactory.create(query, infM);
        
        ResultSet results = qe.execSelect();
        
        QuerySolution qs = null;
        
        while(results.hasNext()){
        	qs = results.next();
        	System.out.println(qs.get("?goal"));
        }
		return results;		
	}
	
	public List<OntClass> listAllNamedClasses(OntModel ontM){
		List<OntClass> ontClsList = new LinkedList<OntClass>();
		Iterator<OntClass> it = ontM.listNamedClasses();
		OntClass ontCls = null;
		
		while(it.hasNext()){
			ontCls = it.next();
			ontClsList.add(ontCls);
		}
		return ontClsList;		
	}
	
	public List<Individual> listAllIndividualofClass(OntModel ontM, OntClass ontCls){
		List<Individual> ontIndList = new LinkedList<Individual>();
		Iterator<Individual> it = ontM.listIndividuals(ontCls);
		Individual ind = null;
		
		while(it.hasNext()){
			ind = it.next();
			ontIndList.add(ind);
		}
		return ontIndList;		
	}
	
	public void removeAllIndividuals(OntModel ontM, InfModel infM){
		List<OntClass> clsList = listAllNamedClasses(ontM);
		Iterator<OntClass> itCls =  clsList.iterator();
		List<Individual> indList = null;
		Iterator<Individual> itInd = null;
		OntClass ontCls = null;
		Individual ind = null;
		while(itCls.hasNext()){
			ontCls = itCls.next();			
			System.out.println("Clear all individuals of ClassName:->"+ontCls.getLocalName());
			if(!ontCls.getLocalName().equals("SupplyChain")&&!ontCls.getLocalName().equals("BFO_0000002")
					&&!ontCls.getLocalName().equals("BFO_0000004")&&!ontCls.getLocalName().equals("BFO_0000040")
					&&!ontCls.getLocalName().equals("BFO_0000030")&&!ontCls.getLocalName().equals("BFO_0000020")
					&&!ontCls.getLocalName().equals("Resource")&&!ontCls.getLocalName().equals("Actor")
					&&!ontCls.getLocalName().equals("ContentAgent")&&!ontCls.getLocalName().equals("InfrastructureAgent")
					&&!ontCls.getLocalName().equals("HostAgent")&&!ontCls.getLocalName().equals("NetworkAgent")&&!ontCls.getLocalName().equals("StorageAgent")
					&&!ontCls.getLocalName().equals("ProcedureAgent")&&!ontCls.getLocalName().equals("SCORAgent")
					&&!ontCls.getLocalName().equals("WorkloadAgent")){
				indList = listAllIndividualofClass(ontM, ontCls);
				itInd = indList.iterator();
				while(itInd.hasNext()){
					ind = (Individual) itInd.next();
					ind.remove();
//					System.out.println(" IndividualName:->"+ind.getLocalName());
				}
			}
		}
	}
	
	//Test main
	public static void main(String args[]){
		OntologyOperator ontOpt = new OntologyOperator();
		OntModel ontM = ontOpt.initialiseOntology();
		InfModel infM = ontOpt.initialiseOntologyWithPelletReasoner(ontM);
		//scorA.getAllUnresolvedSystemEventIndividual(ontM, infM); //Just for test
		
        ontOpt.removeAllIndividuals(ontM, infM);
	//	scorA.getCPUCapability(ontM, infM, 2, 1500);
        
        ontOpt.closeOntology(ontM);
	}
}
