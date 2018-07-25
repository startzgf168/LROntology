package com.sky.lr.agent;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import com.hp.hpl.jena.ontology.DatatypeProperty;
import com.hp.hpl.jena.ontology.Individual;
import com.hp.hpl.jena.ontology.OntClass;
import com.hp.hpl.jena.ontology.OntModel;
import com.hp.hpl.jena.query.Query;
import com.hp.hpl.jena.query.QueryExecution;
import com.hp.hpl.jena.query.QueryExecutionFactory;
import com.hp.hpl.jena.query.QueryFactory;
import com.hp.hpl.jena.query.QuerySolution;
import com.hp.hpl.jena.query.ResultSet;
import com.hp.hpl.jena.rdf.model.InfModel;
import com.hp.hpl.jena.rdf.model.RDFNode;
import com.hp.hpl.jena.rdf.model.Resource;
import com.hp.hpl.jena.rdf.model.Statement;
import com.hp.hpl.jena.rdf.model.StmtIterator;
import com.sky.lr.agent.interf.SCOROperator;
import com.sky.lr.main.Goal;
import com.sky.lr.main.Strategy;
import com.sky.lr.ontology.OntologyMeta;
import com.sky.lr.utility.OntologyOperator;

public class SCORAgent implements SCOROperator {

	@Override
	public void SCORPlan() {
		// TODO Auto-generated method stub

	}

	@Override
	public void SCORSource() {
		// TODO Auto-generated method stub

	}

	@Override
	public void SCORMake() {
		// TODO Auto-generated method stub

	}

	@Override
	public void SCORDeliver() {
		// TODO Auto-generated method stub

	}

	@Override
	public void SCORReturn() {
		// TODO Auto-generated method stub

	}
	//Get the corresponding CPU Capability that meet atomicTask's requirement
	public List<Individual> getMemoryCapability(OntModel ontM, InfModel infM, int memSize){
		//SPARQL string 
		String queryStr =  "PREFIX res: <http://purl.obolibrary.org/obo/bfo.owl#>" +
                           "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>"+
        		           "SELECT ?agent ?res ?role ?cap "+ 
        		           " WHERE { ?res  res:OwnBy  ?agent." + 
        		           "         ?res  res:hasCapability ?cap."+
        		           "         ?role rdf:type res:asMemory."+
        		           "         ?role res:Provides ?cap."+
        		           "         ?cap res:hasMemSize ?memSize.FILTER(?memSize>="+memSize+").}";
		
        Query query = QueryFactory.create(queryStr);
        
        // Execute the query and obtain results
        QueryExecution qe = QueryExecutionFactory.create(query, infM);
        
        ResultSet results = qe.execSelect();
        
		List<Individual> indList = new ArrayList<Individual>();
		Individual agentInd = null;	
		
        QuerySolution qs = null;
        
        while(results.hasNext()){    
        	qs = results.next();
     //   	System.out.println(qs.get("?agent").toString());
        	agentInd = ontM.getIndividual(qs.get("?cap").toString());
            indList.add(agentInd);
     //   	System.out.println(agentInd.getLocalName());
        }
        qe.close();
        
		
		return indList;		
	}
	
	
	//Get the corresponding Memory Capability that meet atomicTask's requirement
	public List<Individual> getOSCapability(OntModel ontM, InfModel infM, String osType, String osVersion){
		//SPARQL string 
		String queryStr =  "PREFIX res: <http://purl.obolibrary.org/obo/bfo.owl#>" +
                           "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>"+
        		           "SELECT ?agent ?res ?role ?cap "+ 
        		           " WHERE { ?res  res:OwnBy  ?agent." + 
        		           "         ?res  res:hasCapability ?cap."+
        		           "         ?role rdf:type res:asMemory."+
        		           "         ?role res:Provides ?cap."+
        		           "         ?cap res:hasOSType ?osType.FILTER(?osType =\""+osType+"\")." +
        		           "         ?cap res:hasOSVersion ?osVer.FILTER(?osVer=\""+osVersion+"\").}";
		
        Query query = QueryFactory.create(queryStr);
        
        // Execute the query and obtain results
        QueryExecution qe = QueryExecutionFactory.create(query, infM);
        
        ResultSet results = qe.execSelect();
        
		List<Individual> indList = new ArrayList<Individual>();
		Individual agentInd = null;	
		
        QuerySolution qs = null;
        
        while(results.hasNext()){    
        	qs = results.next();
     //   	System.out.println(qs.get("?agent").toString());
        	agentInd = ontM.getIndividual(qs.get("?cap").toString());
            indList.add(agentInd);
     //   	System.out.println(agentInd.getLocalName());
        }
        qe.close();
        
		
		return indList;		
	}
	
	//Get the corresponding Storage Capability that meet atomicTask's requirement
	public List<Individual> getStorageCapability(OntModel ontM, InfModel infM, int capacity){
		//SPARQL string 
		String queryStr =  "PREFIX res: <http://purl.obolibrary.org/obo/bfo.owl#>" +
                           "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>"+
        		           "SELECT ?agent ?res ?role ?cap "+ 
        		           " WHERE { ?res  res:OwnBy  ?agent." + 
        		           "         ?res  res:hasCapability ?cap."+
        		           "         ?role rdf:type res:asStorage."+
        		           "         ?role res:Provides ?cap."+
        		           "         ?cap res:hasStorageCapacity ?capacity.FILTER(?capacity>="+capacity+").}";
		
        Query query = QueryFactory.create(queryStr);
        
        // Execute the query and obtain results
        QueryExecution qe = QueryExecutionFactory.create(query, infM);
        
        ResultSet results = qe.execSelect();
        
		List<Individual> indList = new ArrayList<Individual>();
		Individual agentInd = null;	
		
        QuerySolution qs = null;
        
        while(results.hasNext()){    
        	qs = results.next();
     //   	System.out.println(qs.get("?agent").toString());
        	agentInd = ontM.getIndividual(qs.get("?cap").toString());
            indList.add(agentInd);
     //   	System.out.println(agentInd.getLocalName());
        }
        qe.close();
        
		
		return indList;		
	}
	
	//Get the corresponding CPU Capability that meet atomicTask's requirement
	public List<Individual> getCPUCapability(OntModel ontM, InfModel infM, int cpuNum, int cpuFreq){
		//SPARQL string 
		String queryStr =  "PREFIX res: <http://purl.obolibrary.org/obo/bfo.owl#>" +
                           "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>"+
        		           "SELECT ?agent ?res ?role ?cap "+ 
        		           " WHERE { ?res  res:OwnBy  ?agent." + 
        		           "         ?res  res:hasCapability ?cap."+
        		           "         ?role rdf:type res:asCompute."+
        		           "         ?role res:Provides ?cap."+
        		           "         ?cap res:hasCPUCores ?cpuNum.FILTER(?cpuNum>="+cpuNum+")."+
        		           "         ?cap res:hasCPUFrequency ?cpuFre.FILTER(?cpuFre>="+cpuFreq+").}";
		
        Query query = QueryFactory.create(queryStr);
        
        // Execute the query and obtain results
        QueryExecution qe = QueryExecutionFactory.create(query, infM);
        
        ResultSet results = qe.execSelect();
        
		List<Individual> indList = new ArrayList<Individual>();
		Individual agentInd = null;	
		
        QuerySolution qs = null;
        
        while(results.hasNext()){    
        	qs = results.next();
     //   	System.out.println(qs.get("?agent").toString());
        	agentInd = ontM.getIndividual(qs.get("?cap").toString());
            indList.add(agentInd);
     //   	System.out.println(agentInd.getLocalName());
        }
        qe.close();
        
		
		return indList;		
	}
	
	//Currently, we only support the case that cpu and memory are located in the same host. So the cpu must depend on the memory currently.
	//Maybe in the future, the cpu and memory can be separated in different hosts.~_^
	public List<Individual> getLimitedCPUCapability(OntModel ontM, InfModel infM, int cpuNum, int cpuFreq, int memSize){
		//SPARQL string 
		String queryStr =  "PREFIX res: <http://purl.obolibrary.org/obo/bfo.owl#>" +
                           "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>"+
        		           "SELECT ?agent ?res ?role1 ?role2 ?cap1 ?cap2 "+ 
        		           " WHERE { ?res  res:OwnBy  ?agent." + 
        		           "         ?res  res:hasCapability ?cap1."+
        		           "         ?res  res:hasCapability ?cap2."+
        		           "         ?role1 rdf:type res:asCompute."+
        		           "         ?role2 rdf:type res:asMemory."+
        		           "         ?role1 res:Provides ?cap1."+
        		           "         ?role2 res:Provides ?cap2."+
        		           "         ?cap2 res:hasMemSize ?memSize.FILTER(?memSize>="+memSize+")."+
        		           "         ?cap1 res:hasCPUCores ?cpuNum.FILTER(?cpuNum>="+cpuNum+")."+
        		           "         ?cap1 res:hasCPUFrequency ?cpuFre.FILTER(?cpuFre>="+cpuFreq+").}";
		
        Query query = QueryFactory.create(queryStr);
        
        // Execute the query and obtain results
        QueryExecution qe = QueryExecutionFactory.create(query, infM);
        
        ResultSet results = qe.execSelect();
        
		List<Individual> indList = new ArrayList<Individual>();
		Individual agentInd = null;	
		
        QuerySolution qs = null;
        
        while(results.hasNext()){    
        	qs = results.next();
     //   	System.out.println(qs.get("?agent").toString());
        	agentInd = ontM.getIndividual(qs.get("?cap1").toString());
            indList.add(agentInd);
     //   	System.out.println(agentInd.getLocalName());
        }
        qe.close();
        
		
		return indList;		
	}
	
	//Currently, we only support the case that cpu and memory are located in the same host. So the cpu must depend on the memory currently.
	//Maybe in the future, the cpu and memory can be separated in different hosts.~_^
	public List<Individual> getLimitedMemCapability(OntModel ontM, InfModel infM, int cpuNum, int cpuFreq, int memSize){
		//SPARQL string 
		String queryStr =  "PREFIX res: <http://purl.obolibrary.org/obo/bfo.owl#>" +
                           "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>"+
        		           "SELECT ?agent ?res ?role1 ?role2 ?cap1 ?cap2 "+ 
        		           " WHERE { ?res  res:OwnBy  ?agent." + 
        		           "         ?res  res:hasCapability ?cap1."+
        		           "         ?res  res:hasCapability ?cap2."+
        		           "         ?role1 rdf:type res:asCompute."+
        		           "         ?role2 rdf:type res:asMemory."+
        		           "         ?role1 res:Provides ?cap1."+
        		           "         ?role2 res:Provides ?cap2."+
        		           "         ?cap2 res:hasMemSize ?memSize.FILTER(?memSize>="+memSize+")."+
        		           "         ?cap1 res:hasCPUCores ?cpuNum.FILTER(?cpuNum>="+cpuNum+")."+
        		           "         ?cap1 res:hasCPUFrequency ?cpuFre.FILTER(?cpuFre>="+cpuFreq+").}";
		
        Query query = QueryFactory.create(queryStr);
        
        // Execute the query and obtain results
        QueryExecution qe = QueryExecutionFactory.create(query, infM);
        
        ResultSet results = qe.execSelect();
        
		List<Individual> indList = new ArrayList<Individual>();
		Individual agentInd = null;	
		
        QuerySolution qs = null;
        
        while(results.hasNext()){    
        	qs = results.next();
     //   	System.out.println(qs.get("?agent").toString());
        	agentInd = ontM.getIndividual(qs.get("?cap2").toString());
            indList.add(agentInd);
     //   	System.out.println(agentInd.getLocalName());
        }
        qe.close();
        
		
		return indList;		
	}
	
	//Get the corresponding CPU Capability that meet atomicTask's requirement
	public List<Individual> getResourceofCPUCapability(OntModel ontM, InfModel infM, int cpuNum, int cpuFreq){
		//SPARQL string 
		String queryStr =  "PREFIX res: <http://purl.obolibrary.org/obo/bfo.owl#>" +
                           "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>"+
        		           "SELECT ?agent ?res ?role ?cap "+ 
        		           " WHERE { ?res  res:OwnBy  ?agent." + 
        		           "         ?res  res:hasCapability ?cap."+
        		           "         ?role rdf:type res:asCompute."+
        		           "         ?role res:Provides ?cap."+
        		           "         ?cap res:hasCPUCores ?cpuNum.FILTER(?cpuNum>="+cpuNum+")."+
        		           "         ?cap res:hasCPUFrequency ?cpuFre.FILTER(?cpuFre>="+cpuFreq+").}";
		
        Query query = QueryFactory.create(queryStr);
        
        // Execute the query and obtain results
        QueryExecution qe = QueryExecutionFactory.create(query, infM);
        
        ResultSet results = qe.execSelect();
        
		List<Individual> indList = new ArrayList<Individual>();
		Individual agentInd = null;	
		
        QuerySolution qs = null;
        
        while(results.hasNext()){    
        	qs = results.next();
     //   	System.out.println(qs.get("?agent").toString());
        	agentInd = ontM.getIndividual(qs.get("?res").toString());
            indList.add(agentInd);
     //   	System.out.println(agentInd.getLocalName());
        }
        qe.close();
        
		
		return indList;		
	}
	
	//Get the corresponding CPU Capability that meet atomicTask's requirement
	public List<Individual> getActorofCPUCapability(OntModel ontM, InfModel infM, int cpuNum, int cpuFreq){
		//SPARQL string 
		String queryStr =  "PREFIX res: <http://purl.obolibrary.org/obo/bfo.owl#>" +
                           "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>"+
        		           "SELECT ?agent ?res ?role ?cap "+ 
        		           " WHERE { ?res  res:OwnBy  ?agent." + 
        		           "         ?res  res:hasCapability ?cap."+
        		           "         ?role rdf:type res:asCompute."+
        		           "         ?role res:Provides ?cap."+
        		           "         ?cap res:hasCPUCores ?cpuNum.FILTER(?cpuNum>="+cpuNum+")."+
        		           "         ?cap res:hasCPUFrequency ?cpuFre.FILTER(?cpuFre>="+cpuFreq+").}";
		
        Query query = QueryFactory.create(queryStr);
        
        // Execute the query and obtain results
        QueryExecution qe = QueryExecutionFactory.create(query, infM);
        
        ResultSet results = qe.execSelect();
        
		List<Individual> indList = new ArrayList<Individual>();
		Individual agentInd = null;	
		
        QuerySolution qs = null;
        
        while(results.hasNext()){    
        	qs = results.next();
     //   	System.out.println(qs.get("?agent").toString());
        	agentInd = ontM.getIndividual(qs.get("?agent").toString());
            indList.add(agentInd);
     //   	System.out.println(agentInd.getLocalName());
        }
        qe.close();
        
		
		return indList;		
	}
	
	public String getStringFieldValue(String literal){
		int beginIndex =0, endIndex =0;
		endIndex = literal.indexOf("^^");
		return literal.substring(beginIndex, endIndex);
	}
	
	//Update the status of an atomicTask individual
	public void setStatusofAtomicTask(OntModel ontM, Individual aTask, String status){
		DatatypeProperty statusProperty = ontM.getDatatypeProperty(OntologyMeta.DefaultNS+"status");
		RDFNode rdf = ontM.createTypedLiteral(status);
		aTask.setPropertyValue(statusProperty, rdf);
	}
	
	//Update the hasStatus of an system event individual
	public void setStatusofSystemEvent(OntModel ontM, Individual sEvent, String status){
		DatatypeProperty statusProperty = ontM.getDatatypeProperty(OntologyMeta.DefaultNS+"hasStatus");
		RDFNode rdf = ontM.createTypedLiteral(status);
		sEvent.setPropertyValue(statusProperty, rdf);
	}
	
	//The relationship between Context and other individual maybe n:m, that is, one context individual can contain many other related individuals;
	//One individual can also contain many context individuals
	public List<Individual> getRelativedContextIndividual(OntModel ontM, InfModel infM, Individual Ind){
		String indName = Ind.getLocalName();
		//SPARQL string 
		String queryStr =  "PREFIX res: <http://purl.obolibrary.org/obo/bfo.owl#>"+
        		           "SELECT ?context "+ 
        		           " WHERE {?context res:Contains res:"+indName+"}";
		
        Query query = QueryFactory.create(queryStr);
        
        // Execute the query and obtain results
        QueryExecution qe = QueryExecutionFactory.create(query, infM);
        
        ResultSet results = qe.execSelect();
        
		List<Individual> indList = new ArrayList<Individual>();
		Individual contextInd = null;	
		
        QuerySolution qs = null;
        
        while(results.hasNext()){    
        	qs = results.next();
        	//goalInd can be Null or NonNull
        	contextInd = ontM.getIndividual(qs.get("?context").toString());
            indList.add(contextInd);
//        	System.out.println(contextInd.getLocalName());
        }
        qe.close();
        
		return indList;			
	}
	
	
	//The relationship between Goal and SystemEvent should 1:1
	public Individual getRelativedGoalIndividual(OntModel ontM, InfModel infM, Individual systemEventInd){
		String indName = systemEventInd.getLocalName();
		//SPARQL string 
		String queryStr =  "PREFIX res: <http://purl.obolibrary.org/obo/bfo.owl#>"+
        		           "SELECT ?goal "+ 
        		           " WHERE {?goal res:RepresentBy res:"+indName+"}";
		
        Query query = QueryFactory.create(queryStr);
        
        // Execute the query and obtain results
        QueryExecution qe = QueryExecutionFactory.create(query, infM);
        
        ResultSet results = qe.execSelect();
        
		Individual goalInd = null;
			
        QuerySolution qs = null;
        
        while(results.hasNext()){    
        	qs = results.next();
        	//goalInd can be Null or NonNull
        	goalInd = ontM.getIndividual(qs.get("?goal").toString());

        }
        qe.close();
        
		return goalInd;			
	}
	
	public List<Individual> getCurrentVNACapabilityofAtomicTask(OntModel ontM, InfModel infM, Individual ataskInd){
		List<Individual> indList = new LinkedList<Individual>();
		
		String indName = ataskInd.getLocalName();
		//SPARQL string 
		String queryStr =  "PREFIX res: <http://purl.obolibrary.org/obo/bfo.owl#>"+
        		           "SELECT ?cap "+ 
        		           " WHERE { res:"+indName+" res:EnableBy ?cap}";
		
        Query query = QueryFactory.create(queryStr);
        
        // Execute the query and obtain results
        QueryExecution qe = QueryExecutionFactory.create(query, infM);
        
        ResultSet results = qe.execSelect();
        			
        QuerySolution qs = null;
        
        while(results.hasNext()){    
        	qs = results.next();
        	//goalInd can be Null or NonNull
    		Individual capInd = ontM.getIndividual(qs.get("?cap").toString());
            indList.add(capInd);
//         	System.out.println(aTaskInd.getLocalName());
        }
        qe.close();
        
		return indList;	
	}
	
	//The relationship between AtomicTask and SystemEvent should n:1
	//In addition, we should filter the Atomic Task whose status is not undecided
	public List<Individual> getRelativedAtomicTaskIndividual(OntModel ontM, InfModel infM, Individual sEventInd){
		List<Individual> indList = new LinkedList<Individual>();
		
		String indName = sEventInd.getLocalName();
		//SPARQL string 
		String queryStr =  "PREFIX res: <http://purl.obolibrary.org/obo/bfo.owl#>"+
        		           "SELECT ?aTask "+ 
        		           " WHERE { res:"+indName+" res:ComposedBy ?aTask}";
		
//		PREFIX res: <http://purl.obolibrary.org/obo/bfo.owl#>
//			SELECT  ?aTask ?status
//				WHERE { res:sEvent_compute_2015 res:ComposedBy ?aTask .
//			                                ?aTask res:status ?status.FILTER(?
//
//			status="Undecided")
        Query query = QueryFactory.create(queryStr);
        
        // Execute the query and obtain results
        QueryExecution qe = QueryExecutionFactory.create(query, infM);
        
        ResultSet results = qe.execSelect();
        
			
        QuerySolution qs = null;
        
        while(results.hasNext()){    
        	qs = results.next();
        	//goalInd can be Null or NonNull
    		Individual aTaskInd = ontM.getIndividual(qs.get("?aTask").toString());
            indList.add(aTaskInd);
//         	System.out.println(aTaskInd.getLocalName());
        }
        qe.close();
        
		return indList;			
	}
	
	public List<Individual> getAllValueIndividual(OntModel ontM, InfModel infM, Individual ataskInd){
		List<Individual> indList = new LinkedList<Individual>();
		
		//SPARQL string 
		String queryStr =  "PREFIX res: <http://purl.obolibrary.org/obo/bfo.owl#>"+
		                   "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>"+
        		           "SELECT ?value "+ 
        		           " WHERE { ?value rdf:type res:Value." + 
        		           "         ?value res:PerformOn res:"+ ataskInd.getLocalName() +
        		           "}";
		
        Query query = QueryFactory.create(queryStr);
        
        // Execute the query and obtain results
        QueryExecution qe = QueryExecutionFactory.create(query, infM);
        
        ResultSet results = qe.execSelect();
        
			
        QuerySolution qs = null;
        
        while(results.hasNext()){    
        	qs = results.next();
        	//goalInd can be Null or NonNull
    		Individual valueInd = ontM.getIndividual(qs.get("?value").toString());
            indList.add(valueInd);
//         	System.out.println(valueInd.getLocalName());
        }
        qe.close();
        
		return indList;	
	}
	
	//Get all corresponding failed/discard atomic tasks, we will replace these atomic tasks with other values
	public List<Individual> getAllFailedorDiscardAtomicTaskIndividual(OntModel ontM, InfModel infM){
		List<Individual> indList = new LinkedList<Individual>();
		
		//SPARQL string 
		String queryStr =  "PREFIX res: <http://purl.obolibrary.org/obo/bfo.owl#>"+
		                   "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>"+
        		           "SELECT ?aTask "+ 
        		           " WHERE { ?aTask rdf:type res:AtomicTask." + 
        		           "         ?aTask res:status ?status.FILTER(?status=\"Discard\"||?status=\"Error\")"+
        		           "}";
		
        Query query = QueryFactory.create(queryStr);
        
        // Execute the query and obtain results
        QueryExecution qe = QueryExecutionFactory.create(query, infM);
        
        ResultSet results = qe.execSelect();
        
			
        QuerySolution qs = null;
        
        while(results.hasNext()){    
        	qs = results.next();
        	//goalInd can be Null or NonNull
    		Individual aTaskInd = ontM.getIndividual(qs.get("?aTask").toString());
            indList.add(aTaskInd);
//         	System.out.println(aTaskInd.getLocalName());
        }
        qe.close();
        
		return indList;			
	}
	
	//The relationship between AtomicTask and SystemEvent should n:1
	//In addition, we should filter the Atomic Task whose status is not undecided
	//Please note: the OntModel and InfModel are different since the change within the OntModel won't appear in the InfModel immediately
	//SO if you want to get the content you just write, you should use the same OntModel since the InfModel can't change the owl
	public List<Individual> getRelativedUndecidedAtomicTaskIndividual(OntModel ontM, Individual sEventInd){
		List<Individual> indList = new LinkedList<Individual>();
		
		String indName = sEventInd.getLocalName();
		//SPARQL string 
		String queryStr =  "PREFIX res: <http://purl.obolibrary.org/obo/bfo.owl#>" +
                           "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>"+
        		           "SELECT ?aTask "+ 
        		           " WHERE { res:"+indName+" res:ComposedBy ?aTask."+
        		           "          ?aTask res:status ?status.FILTER(?status=\"Undecided\").}";
		

        Query query = QueryFactory.create(queryStr);
        
        // Execute the query and obtain results
        QueryExecution qe = QueryExecutionFactory.create(query, ontM);
        
        ResultSet results = qe.execSelect();
        
			
        QuerySolution qs = null;
        
        while(results.hasNext()){    
        	qs = results.next();
        	//goalInd can be Null or NonNull
    		Individual aTaskInd = ontM.getIndividual(qs.get("?aTask").toString());
            indList.add(aTaskInd);
//         	System.out.println(aTaskInd.getLocalName());
        }
        qe.close();
        
		return indList;			
	}
	
	//The relationship between AtomicTask and SystemEvent should n:1
	//In addition, we should filter the Atomic Task whose status is not undecided
	public List<Individual> getRelativedDecidedAtomicTaskIndividual(OntModel ontM, InfModel infM, Individual sEventInd){
		List<Individual> indList = new LinkedList<Individual>();
		
		String indName = sEventInd.getLocalName();
		//SPARQL string 
		String queryStr =  "PREFIX res: <http://purl.obolibrary.org/obo/bfo.owl#>" +
                           "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>"+
        		           "SELECT ?aTask "+ 
        		           " WHERE { res:"+indName+" res:ComposedBy ?aTask."+
        		           "          ?aTask res:status ?status.FILTER(?status=\"Decided\").}";
		

        Query query = QueryFactory.create(queryStr);
        
        // Execute the query and obtain results
        QueryExecution qe = QueryExecutionFactory.create(query, ontM);
        
        ResultSet results = qe.execSelect();
        
			
        QuerySolution qs = null;
        
        while(results.hasNext()){    
        	qs = results.next();
        	//goalInd can be Null or NonNull
    		Individual aTaskInd = ontM.getIndividual(qs.get("?aTask").toString());
            indList.add(aTaskInd);
//         	System.out.println(aTaskInd.getLocalName());
        }
        qe.close();
        
		return indList;			
	}
	
	//The relationship between GoalSpec and Goal should 1:1
	public Individual getRelativedGoalResourceSpecIndividual(OntModel ontM, InfModel infM, Individual goalInd){
		String indName = goalInd.getLocalName();
		//SPARQL string 
		String queryStr =  "PREFIX res: <http://purl.obolibrary.org/obo/bfo.owl#>"+
        		           "SELECT ?goalSpec "+ 
        		           " WHERE { res:"+indName+" res:hasResourceSpec ?goalSpec}";
		
        Query query = QueryFactory.create(queryStr);
        
        // Execute the query and obtain results
        QueryExecution qe = QueryExecutionFactory.create(query, infM);
        
        ResultSet results = qe.execSelect();
        
		Individual goalSpecInd = null;
			
        QuerySolution qs = null;
        
        while(results.hasNext()){    
        	qs = results.next();

        	goalSpecInd = ontM.getIndividual(qs.get("?goalSpec").toString());

        }
        qe.close();
        
		return goalSpecInd;			
	}
	
	//Get the relative change(install/modify/delete/migrate,etc.) individual
	public Individual getRelativeChangeIndividual(OntModel ontM, InfModel infM, Individual goalInd){
		String indName = goalInd.getLocalName();
		//SPARQL string 
		String queryStr =  "PREFIX res: <http://purl.obolibrary.org/obo/bfo.owl#>"+
        		           "SELECT ?change "+ 
        		           " WHERE { res:"+indName+" res:hasFunction ?change}";
		
        Query query = QueryFactory.create(queryStr);
        
        // Execute the query and obtain results
        QueryExecution qe = QueryExecutionFactory.create(query, infM);
        
        ResultSet results = qe.execSelect();
        
		Individual changeInd = null;
			
        QuerySolution qs = null;
        
        while(results.hasNext()){    
        	qs = results.next();

        	changeInd = ontM.getIndividual(qs.get("?change").toString());

        }
        qe.close();
        
		return changeInd;	
	}
	
	//The relationship between StrategySpec and Goal should n:1
	public List<Individual> getRelativedGoalStrategySpecIndividual(OntModel ontM, InfModel infM, Individual goalInd){
		List<Individual> indList = new LinkedList<Individual>();
		String indName = goalInd.getLocalName();
		//SPARQL string 
		String queryStr =  "PREFIX res: <http://purl.obolibrary.org/obo/bfo.owl#>"+
        		           "SELECT ?strategySpec "+ 
        		           " WHERE { res:"+indName+" res:hasStrategySpec ?strategySpec}";
		
        Query query = QueryFactory.create(queryStr);
        
        // Execute the query and obtain results
        QueryExecution qe = QueryExecutionFactory.create(query, infM);
        
        ResultSet results = qe.execSelect();
        
		Individual strategySpecInd = null;
			
        QuerySolution qs = null;
        
        while(results.hasNext()){    
        	qs = results.next();
        	//goalInd can be Null or NonNull
        	strategySpecInd = ontM.getIndividual(qs.get("?strategySpec").toString());
            indList.add(strategySpecInd);
//        	System.out.println(strategySpecInd.getLocalName());
        }
        qe.close();
        
		return indList;			
	}
	//Parse the strategy spec and extract its data into Strategy
	public List<Strategy> extractStrategySpec(InfModel infM, List<Individual> strategySpecIndList){
		List<Strategy> strategyList = new ArrayList<Strategy>();
		
		Strategy strategy = null;
		
		Iterator<Individual> it = strategySpecIndList.iterator();
		
		Individual sSpecInd = null;
		
		String indName=null, queryStr=null, tmpStr = null;
		int endIndex = 0;
		
        Query query = null;
        
        // Execute the query and obtain results
        QueryExecution qe = null;
        
        ResultSet results = null;
        			
        QuerySolution qs = null;
		
		while(it.hasNext()){			
	        sSpecInd = it.next();
	         
	 		indName = sSpecInd.getLocalName();
			//SPARQL string 
			queryStr =  "PREFIX res: <http://purl.obolibrary.org/obo/bfo.owl#>"+
	        		           "SELECT ?availabilityLevel ?scalabilityLevel ?reliabilityLevel"+ 
	        		           " WHERE { res:"+indName+" res:hasAvailabilityLevel  ?availabilityLevel." +
	        		           "         res:"+indName+" res:hasScalabilityLevel   ?scalabilityLevel." +
	        		           "         res:"+indName+" res:hasReliabilityLevel   ?reliabilityLevel.}";
			
			query = QueryFactory.create(queryStr);
			
			qe = QueryExecutionFactory.create(query, infM);
			
			results = qe.execSelect();
			
			
			while(results.hasNext()){
				qs = results.next();
				
				strategy = new Strategy();
				
				tmpStr = qs.get("?availabilityLevel").toString();
				endIndex = tmpStr.indexOf("^^");
				strategy.setAvaiablityLevel(tmpStr.substring(0, endIndex));
				
				tmpStr = qs.get("?scalabilityLevel").toString();
				endIndex = tmpStr.indexOf("^^");
				strategy.setScalabilityLevel(tmpStr.substring(0, endIndex));
				
				tmpStr = qs.get("?reliabilityLevel").toString();
				endIndex = tmpStr.indexOf("^^");
				strategy.setReliabilityLevel(tmpStr.substring(0, endIndex));
				
				strategyList.add(strategy);
				
//				System.out.println(qs.get("?availabilityLevel").toString() + " " +qs.get("?scalabilityLevel").toString() + " "+qs.get("?reliabilityLevel").toString());
			}
			qe.close();
			
		}
		
		return strategyList;
		
	}
	//Create a workload individual based on the strategy and goalspec
	public Individual createWorkloadIndividual(OntModel ontM, Individual goalSpec, Individual planInd, List<Strategy> strategy){
		//Currently, the relationship of goal and strategy should be 1:1
		//But in the future, their relationship maybe n:1
		//In addition, we only need to create a workload individual with the Red/Yellow/Green now.
		//The indicator of workload should be perfected in the future
		UUID uid = null;
		String newWorkloadIndName = null;
		Individual newWorkloadInd = null; 
		Statement  s1=null, s2=null, s3=null, s4=null;
		//Create the Plan Individual according to the system event individual
        uid = UUID.randomUUID();
        newWorkloadIndName = "workload_" + uid.toString();
        newWorkloadInd = OntologyOperator.createIndividual(ontM, "Workload", newWorkloadIndName);	
        //The value has capability and PerformOn the atomic task, therefore if one atomic task failed, then we can switch the other one
        s1 = ontM.createStatement(newWorkloadInd, ontM.getDatatypeProperty(OntologyMeta.DefaultNS+"hasIndicator1"),ontM.createTypedLiteral("Red"));
        //The value will PerformOn an atomicTask
        s2 = ontM.createStatement(newWorkloadInd, ontM.getDatatypeProperty(OntologyMeta.DefaultNS+"hasIndicator2"), ontM.createTypedLiteral("Yellow"));
        //Link the value to the plan
        s3 = ontM.createStatement(newWorkloadInd, ontM.getDatatypeProperty(OntologyMeta.DefaultNS+"hasIndicator3"), ontM.createTypedLiteral("Green"));
        //The plan ComposedBy the workload individual
        s4 = ontM.createStatement(planInd, ontM.getObjectProperty(OntologyMeta.DefaultNS+"ComposedBy"), newWorkloadInd);
        ontM.add(s1);
        ontM.add(s2);
        ontM.add(s3);
        ontM.add(s4);
        
        return newWorkloadInd;
	}
	
	//Create the corresponding value w.r.t the atomic task and the plan
	public void CreateValueofSystemEvent(OntModel ontM, Individual resInd, Individual planInd, Individual aTaskInd){
		UUID uid = null;
		String newValueIndName = null;
		Individual newValueInd = null; 
		Statement  s1=null, s2=null, s3=null;
		//Create the Plan Individual according to the system event individual
        uid = UUID.randomUUID();
        newValueIndName = "value_" + uid.toString();
        newValueInd = OntologyOperator.createIndividual(ontM, "Value", newValueIndName);	
        //The value has capability and PerformOn the atomic task, therefore if one atomic task failed, then we can switch the other one
        s1 = ontM.createStatement(newValueInd, ontM.getObjectProperty(OntologyMeta.DefaultNS+"hasValueCapability"),resInd);
        //The value will PerformOn an atomicTask
        s2 = ontM.createStatement(newValueInd, ontM.getObjectProperty(OntologyMeta.DefaultNS+"PerformOn"), aTaskInd);
        //Link the value to the plan
        s3 = ontM.createStatement(planInd, ontM.getObjectProperty(OntologyMeta.DefaultNS+"ComposedBy"), newValueInd);
        ontM.add(s1);
        ontM.add(s2);
        ontM.add(s3);
	}
	//Create the relationship between plan and the corresponding atomic tasks
	public void linkPlanToAtomicTask(OntModel ontM, Individual planInd, Individual aTaskInd){
		Statement ss = ontM.createStatement(planInd, ontM.getObjectProperty(OntologyMeta.DefaultNS+"ComposedBy"),aTaskInd);
        ontM.add(ss);
	}
	
	//Generate the corresponding plan based on the system event
	public Individual createPlanofSystemEvent(OntModel ontM, Individual sEventInd){
		UUID uid = null;
		String newPlanIndName = null;
		Individual newPlanInd = null; 
		Statement  ss=null;
		//Create the Plan Individual according to the system event individual
        uid = UUID.randomUUID();
        newPlanIndName = "plan_" + uid.toString();
        newPlanInd = OntologyOperator.createIndividual(ontM, "Plan", newPlanIndName);	
        //Follow by the relative goal
        ss = ontM.createStatement(newPlanInd, ontM.getObjectProperty(OntologyMeta.DefaultNS+"ComposedBy"),sEventInd);
        ontM.add(ss);
        
        return newPlanInd;
	}
	//Generate the corresponding strategy according to the strategy spec
	public void initialiseStrategyFromStrategySpec(OntModel ontM, InfModel infM, Individual relativedGoalInd, Individual planInd, List<Strategy> strategyList){
		int size =strategyList.size(), index =0;
		
		Individual ind = null;
		Strategy strategy = null;
		
		UUID uid = null;
		String newStrategyIndName = null;
		Individual newStrategyInd = null; 
		Statement  s1=null, s2 = null;
		
		while(index < size){
			//In the future, we can generate multiple strategies according to the strategy spec
			//But currently, we have no resource and no time to do this job in the POC
			strategy = strategyList.get(index);
			/*
			 * ......TBD......
			 */
			
			//Create the Strategy Individual according to the strategy spec of goal
            uid = UUID.randomUUID();
            newStrategyIndName = "strategy_" + uid.toString();
            newStrategyInd = OntologyOperator.createIndividual(ontM, "Strategy", newStrategyIndName);	
            //Follow by the relative goal
            s1 = ontM.createStatement(newStrategyInd, ontM.getObjectProperty(OntologyMeta.DefaultNS+"FollowBy"),relativedGoalInd);
            //Compose the relative plan
            s2 = ontM.createStatement(planInd, ontM.getObjectProperty(OntologyMeta.DefaultNS+"ComposedBy"), newStrategyInd);
            
            ontM.add(s1);
            ontM.add(s2);
            
			index ++;
		}		
        		
	}
	
	//Extract the property of Goal individual
	public Goal extractGoalSpecIndividualProperty(OntModel ontM, InfModel infM, Individual goalSpecInd){
		String indName = goalSpecInd.getLocalName();
		//SPARQL string 
		String queryStr =  "PREFIX res: <http://purl.obolibrary.org/obo/bfo.owl#>"+
        		           "SELECT ?owner ?osType ?osVersion  ?CPUCores ?StorageCapacity ?MemSize ?VMName ?VMLevel "+ 
        		           " WHERE { res:"+indName+" res:hasOwner  ?owner." +
        		           "         res:"+indName+" res:hasOSType ?osType." +
        		           "         res:"+indName+" res:hasOSVersion ?osVersion."+
        		           "         res:"+indName+" res:hasCPUCores ?CPUCores."+
        		           "         res:"+indName+" res:hasStorageCapacity ?StorageCapacity."+
        		           "         res:"+indName+" res:hasMemSize ?MemSize."+
        		           "         res:"+indName+" res:hasVMName  ?VMName."+
        		           "         res:"+indName+" res:hasVMLevel ?VMLevel.}";
		
        Query query = QueryFactory.create(queryStr);
        
        // Execute the query and obtain results
        QueryExecution qe = QueryExecutionFactory.create(query, infM);
        
        ResultSet results = qe.execSelect();
        			
        QuerySolution qs = null;
        Goal goal = new Goal();
        
        int endIndex = 0;
        String tmpStr = null; 
        
        while(results.hasNext()){    
        	qs = results.next();
        	if(null != qs){
            	//the owner of goal
            	tmpStr = qs.get("?owner").toString();
            	endIndex = tmpStr.indexOf("^^");
            	goal.setGoalOwner(tmpStr.substring(0, endIndex));
//            	goal.setGoalOwner(getStringFieldValue(tmpStr));
            	//osType
            	tmpStr = qs.get("?osType").toString();
            	endIndex =  tmpStr.indexOf("^^");
            	goal.setOSType(tmpStr.substring(0, endIndex));
//            	goal.setOSType(getStringFieldValue(tmpStr));
            	//osVersion
            	tmpStr = qs.get("?osVersion").toString();
            	endIndex =  tmpStr.indexOf("^^");
            	goal.setOSVersion(tmpStr.substring(0, endIndex));
//            	goal.setOSVersion(getStringFieldValue(tmpStr));
            	//CPU cores
            	tmpStr = qs.get("?CPUCores").toString();
            	endIndex =  tmpStr.indexOf("^^");
            	goal.setCPUCores(Short.parseShort(tmpStr.substring(0, endIndex)));   
//            	goal.setCPUCores(getStringFieldValue(tmpStr));
            	//Storage capacity
            	tmpStr = qs.get("?StorageCapacity").toString();
            	endIndex =  tmpStr.indexOf("^^");
            	goal.setStorageSize(Integer.parseInt(tmpStr.substring(0, endIndex)));
//            	goal.setStorageSize(getStringFieldValue(tmpStr));
            	//Memory size
            	tmpStr = qs.get("?MemSize").toString();
            	endIndex =  tmpStr.indexOf("^^");
            	goal.setMemSize(Integer.parseInt(tmpStr.substring(0, endIndex)));
//            	goal.setMemSize(getStringFieldValue(tmpStr));
            	//VM Name
            	tmpStr = qs.get("?VMName").toString();
            	endIndex =  tmpStr.indexOf("^^");
            	goal.setVMName(tmpStr.substring(0, endIndex));
//            	goal.setVMName(getStringFieldValue(tmpStr));
            	//VM Level
            	tmpStr = qs.get("?VMLevel").toString();
            	endIndex =  tmpStr.indexOf("^^");
            	goal.setVMLevel(Short.parseShort(tmpStr.substring(0, endIndex)));
//            	goal.setVMLevel(getStringFieldValue(tmpStr));
            	//goalInd can be Null or NonNull
        	}

        }
        qe.close();
        
		return goal;			
	}
	
	public Iterator<Individual> getAllIndividual(OntModel ontM, String className){
		//Get the Goal class
		OntClass oc = ontM.getOntClass(OntologyMeta.DefaultNS+ className);
		
		//List all the corresponding individuals of SystemEvent
		Iterator<Individual> it = ontM.listIndividuals(oc);
		
		return it;
	}
	
	public Iterator<Individual> getAllGoalIndividual(OntModel ontM){
		//Get the Goal class
		OntClass oc = ontM.getOntClass(OntologyMeta.DefaultNS+ "Goal");
		
		//List all the corresponding individuals of SystemEvent
		Iterator<Individual> it = ontM.listIndividuals(oc);
		
		return it;
	}
	//We can't modify the individual since iterator don't allow concurrent modification, so we use the list to store the individual 
	public List<Individual> getAllSystemEventIndividual(OntModel ontM){
		//Get the SystemEvent class
		OntClass oc = ontM.getOntClass(OntologyMeta.DefaultNS+ "SystemEvent");
		
		//List all the corresponding individuals of SystemEvent
		Iterator<Individual> it = ontM.listIndividuals(oc);
		
		List<Individual> allInd = new LinkedList<Individual>();
		
		
		while(it.hasNext()){
			Individual ind = it.next();	
			if(null != ind){
				allInd.add(ind);
			}
		}
		
		return allInd;
		
	}
	
	//We can't modify the individual since iterator don't allow concurrent modification, so we use the list to store the individual 
	public List<Individual> getAllUnresolvedSystemEventIndividual(OntModel ontM, InfModel infM){
		List<Individual> indList = new LinkedList<Individual>();
		//SPARQL string, get the system event whose status is Pending
		String queryStr =  "PREFIX res: <http://purl.obolibrary.org/obo/bfo.owl#>"+ 
				           "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>"+
        		           "SELECT ?systemEvent "+ 
        		           "WHERE { ?systemEvent rdf:type res:SystemEvent."+
        		           "        ?systemEvent res:hasStatus ?status.FILTER(?status=\"Pending\")}";
		
        Query query = QueryFactory.create(queryStr);
        
        // Execute the query and obtain results
        QueryExecution qe = QueryExecutionFactory.create(query, infM);
        
        ResultSet results = qe.execSelect();
        
		Individual strategySpecInd = null;
			
        QuerySolution qs = null;
        
        while(results.hasNext()){    
        	qs = results.next();
        	//goalInd can be Null or NonNull
        	strategySpecInd = ontM.getIndividual(qs.get("?systemEvent").toString());
            indList.add(strategySpecInd);
 //       	System.out.println(strategySpecInd.getLocalName());
        }
        qe.close();
        
		return indList;				
		
	}
	
	// Currently, we manual check the goal in the first phase.
	// There are some rules to filter the system event
	public void CheckGoal(OntModel ontM){
		//Get all the individual of SystemEvent
		Iterator<Individual> it = getAllGoalIndividual(ontM);
		Individual goalInd = null;
		//Just in case to avoid the duplicate name
		while(it.hasNext()) {						
			goalInd = it.next();
			//Do something to check the Goal spec and then wrap it with the SystemEvent
			goalInd.getLocalName();
			
		}
		
	}
	//The structure of AtomicTask <ExpectedTarget, Capability, Action, fromState, toState, Status>
	/*The enum of status:
	 * 0 - Undecided
	 * 1 - Deciding
	 * 2 - Redeciding - if the atomictask is failed or discard, then we will use another value to replace it, so the current status is redeciding
	 * 2 - Decided
	 * 3 - Running
	 * 4 - Success
	 * 5 - Discard - The corresponding atomic task is discarded for the relative failed task
	 * 6 - Error
	 * 7 - RollBack - The corresponding atomic task is rolled back caused by some failed atomic task
	 * 8 - null
	 * The Value of Action:
	 * Source
	 * Make
	 * Deliver
	 * Return
	 * The value of Capability, especially refer to VNA capability
	 * CPU of VNA
	 * MEM of VNA
	 * IO Link of VNA
	 * Storage of VNA
	 */
	public List<Statement> ParseToAtomicTask(OntModel ontM, InfModel infM, Individual systemEventInd, Goal goal){
		
		List<Statement> listStatm = new LinkedList<Statement>();
		//Context individual, which will context 
		List<Individual> contextIndList = getRelativedContextIndividual(ontM,infM,systemEventInd);
		Individual context = null;
		int size = contextIndList.size(), index =0;
		UUID sessionID = UUID.randomUUID();
		
		if(null != goal){
			UUID uid = null;
			String newATaskIndName = null;
			Individual newATaskInd = null; 
			Statement contextss=null, ss=null,s1=null,s2=null,s3=null, s4=null, s5=null;
			
			//Get CPU capability and create a corresponding atomictask individual to wrap the consume request
			if(0 != goal.getCPUCores()){
				uid = UUID.randomUUID();
				newATaskIndName = "Require_CPU_Cap_" + uid.toString();
				newATaskInd = OntologyOperator.createIndividual(ontM, "AtomicTask", newATaskIndName);	
				//First of all, we should build the connection between the AtomicTask and the SystemEvent
				ss = ontM.createStatement(systemEventInd, ontM.getObjectProperty(OntologyMeta.DefaultNS+"ComposedBy"),newATaskInd);
				listStatm.add(ss);
				//The AtomicTask should relevant to the context
				while(index < size){
					context = contextIndList.get(index);
					contextss = ontM.createStatement(newATaskInd, ontM.getObjectProperty(OntologyMeta.DefaultNS + "RelevantTo"), context);
					listStatm.add(contextss);
					index++;
				}

				//Method 1 to set the datatypeproperty value of an individual
				//Add the statement of toState 
				s1 = ontM.createLiteralStatement(newATaskInd, ontM.getDatatypeProperty(OntologyMeta.DefaultNS+"toState"), goal.getCPUCores());
				listStatm.add(s1);
				s2 = ontM.createStatement(newATaskInd, ontM.getDatatypeProperty(OntologyMeta.DefaultNS +"status"), ontM.createTypedLiteral("Undecided"));
				listStatm.add(s2);
				s3 = ontM.createStatement(newATaskInd, ontM.getDatatypeProperty(OntologyMeta.DefaultNS +"resType"), ontM.createTypedLiteral("CPU"));	
				listStatm.add(s3);
				s4 = ontM.createStatement(newATaskInd, ontM.getDatatypeProperty(OntologyMeta.DefaultNS +"scorAction"), ontM.createTypedLiteral("SOURCE"));	
				listStatm.add(s4);
				s5 = ontM.createStatement(newATaskInd, ontM.getDatatypeProperty(OntologyMeta.DefaultNS +"sessionID"), ontM.createTypedLiteral(sessionID.toString()));	
				listStatm.add(s5);
				
				ontM.add(listStatm);
			}
			//Get Mem capability and create a corresponding atomictask individual
			if(0 != goal.getMemSize()){
				uid = UUID.randomUUID();
				newATaskIndName = "Require_Mem_Cap_"+uid.toString();
				newATaskInd = OntologyOperator.createIndividual(ontM, "AtomicTask", newATaskIndName);	
				//First of all, we should build the connection between the AtomicTask and the SystemEvent
				ss = ontM.createStatement(systemEventInd, ontM.getObjectProperty(OntologyMeta.DefaultNS+"ComposedBy"),newATaskInd);
				listStatm.add(ss);
				//The AtomicTask should relevant to the context
				index = 0;
				while(index < size){
					context = contextIndList.get(index);
					contextss = ontM.createStatement(newATaskInd, ontM.getObjectProperty(OntologyMeta.DefaultNS + "RelevantTo"), context);
					listStatm.add(contextss);
					index++;
				}
				//Method 1 to set the datatypeproperty value of an individual
				//Add the statement of toState 
				s1 = ontM.createLiteralStatement(newATaskInd, ontM.getDatatypeProperty(OntologyMeta.DefaultNS+"toState"), goal.getMemSize());
				listStatm.add(s1);
				s2 = ontM.createStatement(newATaskInd, ontM.getDatatypeProperty(OntologyMeta.DefaultNS +"status"), ontM.createTypedLiteral("Undecided"));
				listStatm.add(s2);
				s3 = ontM.createStatement(newATaskInd, ontM.getDatatypeProperty(OntologyMeta.DefaultNS +"resType"), ontM.createTypedLiteral("MEMORY"));	
				listStatm.add(s3);
				s4 = ontM.createStatement(newATaskInd, ontM.getDatatypeProperty(OntologyMeta.DefaultNS +"scorAction"), ontM.createTypedLiteral("SOURCE"));	
				listStatm.add(s4);
				s5 = ontM.createStatement(newATaskInd, ontM.getDatatypeProperty(OntologyMeta.DefaultNS +"sessionID"), ontM.createTypedLiteral(sessionID.toString()));	
				listStatm.add(s5);
				
				ontM.add(listStatm);				
			}
			
			//Get Storage capability and create a corresponding atomictask individual
			if(0 != goal.getStorageSize()){
				uid = UUID.randomUUID();
				newATaskIndName = "Require_Storage_Cap_"+uid.toString();
				newATaskInd = OntologyOperator.createIndividual(ontM, "AtomicTask", newATaskIndName);	
				//First of all, we should build the connection between the AtomicTask and the SystemEvent
				ss = ontM.createStatement(systemEventInd, ontM.getObjectProperty(OntologyMeta.DefaultNS+"ComposedBy"),newATaskInd);
				listStatm.add(ss);
				//The AtomicTask should relevant to the context
				index = 0;
				while(index < size){
					context = contextIndList.get(index);
					contextss = ontM.createStatement(newATaskInd, ontM.getObjectProperty(OntologyMeta.DefaultNS + "RelevantTo"), context);
					listStatm.add(contextss);
					index++;
				}
				//Method 1 to set the datatypeproperty value of an individual
				//Add the statement of toState 
				s1 = ontM.createLiteralStatement(newATaskInd, ontM.getDatatypeProperty(OntologyMeta.DefaultNS+"toState"), goal.getStorageSize());
				listStatm.add(s1);
				s2 = ontM.createStatement(newATaskInd, ontM.getDatatypeProperty(OntologyMeta.DefaultNS +"status"), ontM.createTypedLiteral("Undecided"));
				listStatm.add(s2);
				s3 = ontM.createStatement(newATaskInd, ontM.getDatatypeProperty(OntologyMeta.DefaultNS +"resType"), ontM.createTypedLiteral("STORAGE"));	
				listStatm.add(s3);
				s4 = ontM.createStatement(newATaskInd, ontM.getDatatypeProperty(OntologyMeta.DefaultNS +"scorAction"), ontM.createTypedLiteral("SOURCE"));	
				listStatm.add(s4);
				s5 = ontM.createStatement(newATaskInd, ontM.getDatatypeProperty(OntologyMeta.DefaultNS +"sessionID"), ontM.createTypedLiteral(sessionID.toString()));	
				listStatm.add(s5);
				
				ontM.add(listStatm);
			}
			
			//Get OS capability and create a corresponding atomictask individual
			if(null != goal.getOSType() || null != goal.getOSVersion()){
				uid = UUID.randomUUID();
				newATaskIndName = "Require_OS_Cap_"+uid.toString();
				newATaskInd = OntologyOperator.createIndividual(ontM, "AtomicTask", newATaskIndName);	
				//First of all, we should build the connection between the AtomicTask and the SystemEvent
				ss = ontM.createStatement(systemEventInd, ontM.getObjectProperty(OntologyMeta.DefaultNS+"ComposedBy"),newATaskInd);
				listStatm.add(ss);
				//The AtomicTask should relevant to the context
				index = 0;
				while(index < size){
					context = contextIndList.get(index);
					contextss = ontM.createStatement(newATaskInd, ontM.getObjectProperty(OntologyMeta.DefaultNS + "RelevantTo"), context);
					listStatm.add(contextss);
					index++;
				}
				//Method 1 to set the datatypeproperty value of an individual
				//Add the statement of toState 
				s1 = ontM.createLiteralStatement(newATaskInd, ontM.getDatatypeProperty(OntologyMeta.DefaultNS+"toState"), goal.osType+" "+goal.osVersion);
				listStatm.add(s1);
				s2 = ontM.createStatement(newATaskInd, ontM.getDatatypeProperty(OntologyMeta.DefaultNS +"status"), ontM.createTypedLiteral("Undecided"));
				listStatm.add(s2);
				s3 = ontM.createStatement(newATaskInd, ontM.getDatatypeProperty(OntologyMeta.DefaultNS +"resType"), ontM.createTypedLiteral("OS"));	
				listStatm.add(s3);
				s4 = ontM.createStatement(newATaskInd, ontM.getDatatypeProperty(OntologyMeta.DefaultNS +"scorAction"), ontM.createTypedLiteral("SOURCE"));	
				listStatm.add(s4);
				s5 = ontM.createStatement(newATaskInd, ontM.getDatatypeProperty(OntologyMeta.DefaultNS +"sessionID"), ontM.createTypedLiteral(sessionID.toString()));	
				listStatm.add(s5);
				
				ontM.add(listStatm);
			}
			

		}else{
			//do nothing, just ignore it
		}
		
		return listStatm;
	}
	
	public void setAtomicTask(OntModel ontM, InfModel infM, Individual resInd, Individual aTaskInd, List<Individual> contextIndList){
		//SPARQL string 
		String queryStr =  "PREFIX res: <http://purl.obolibrary.org/obo/bfo.owl#>" +
                           "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>"+
        		           "SELECT ?agent "+ 
        		           " WHERE { ?res  res:OwnBy  ?agent." + 
        		           "         ?res  res:hasCapability res:"+resInd.getLocalName()+".}";
		
        Query query = QueryFactory.create(queryStr);
        
        // Execute the query and obtain results
        QueryExecution qe = QueryExecutionFactory.create(query, infM);
        
        ResultSet results = qe.execSelect();
        
		Individual agentInd = null;	
		
        QuerySolution qs = null;

        while(results.hasNext()){    
        	qs = results.next();
        	agentInd = ontM.getIndividual(qs.get("?agent").toString());       	
        }
        qe.close();
        //Set the expectedTarget property of aTomicTask
		DatatypeProperty targetProperty = ontM.getDatatypeProperty(OntologyMeta.DefaultNS+"expectedTarget");
		RDFNode rdf = ontM.createTypedLiteral(agentInd.getLocalName());
        ontM.add(ontM.createStatement(aTaskInd, targetProperty, rdf)); 
        //Link the capability to the atomic task
        ontM.add(ontM.createStatement(aTaskInd, ontM.getObjectProperty(OntologyMeta.DefaultNS + "EnableBy"), resInd));
        
        int size = contextIndList.size();
        Individual contextInd = null;
        for(int i =0; i<size; i++){
        	contextInd = contextIndList.get(i);
        	//the actor is Relevant to the context 
        	ontM.add(ontM.createStatement(agentInd, ontM.getObjectProperty(OntologyMeta.DefaultNS+"RelevantTo"), contextInd));
        }
	}
	
	//Context individual don't change but the content of context is changed since we may use other actors
	public boolean reSetAtomicTask(OntModel ontM, InfModel infM, Individual resInd, Individual aTaskInd){
		//SPARQL string 
		String queryStr =  "PREFIX res: <http://purl.obolibrary.org/obo/bfo.owl#>" +
                           "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>"+
        		           "SELECT ?agent "+ 
        		           " WHERE { ?res  res:OwnBy  ?agent." + 
        		           "         ?res  res:hasCapability res:"+resInd.getLocalName()+".}";
		
        Query query = QueryFactory.create(queryStr);
        
        // Execute the query and obtain results
        QueryExecution qe = QueryExecutionFactory.create(query, infM);
        
        ResultSet results = qe.execSelect();
        
		Individual agentInd = null;	
		
        QuerySolution qs = null;
        boolean isOK = false;

        while(results.hasNext()){    
        	qs = results.next();
        	agentInd = ontM.getIndividual(qs.get("?agent").toString());   
        	if(null != agentInd){
        		isOK = true;
        	}
        }
        qe.close();
        //Set the expectedTarget property of aTomicTask
		DatatypeProperty targetProperty = ontM.getDatatypeProperty(OntologyMeta.DefaultNS+"expectedTarget");
		RDFNode rdf = ontM.createTypedLiteral(agentInd.getLocalName());
		
		//Select another Agent to provide the capability
		aTaskInd.setPropertyValue(targetProperty, rdf);		
		
		//Remove the previous ObjectProperty EnableBy 
		aTaskInd.removeAll(ontM.getObjectProperty(OntologyMeta.DefaultNS + "EnableBy"));

        //Link the new capability to the atomic task
        ontM.add(ontM.createStatement(aTaskInd, ontM.getObjectProperty(OntologyMeta.DefaultNS + "EnableBy"), resInd));
  
        //Clear the relative context of the previous actor, do nothing currently
        clearContextofAgentBindToAtomicTask(ontM, infM, aTaskInd);

        //Get the context from the corresponding context
        List<Individual> contextIndList = getRelativedContextIndividual(ontM, infM, aTaskInd);
        int size = contextIndList.size();
        Individual contextInd = null;
        for(int i =0; i<size; i++){
        	contextInd = contextIndList.get(i);
        	//the actor is Relevant to the context 
        	ontM.add(ontM.createStatement(agentInd, ontM.getObjectProperty(OntologyMeta.DefaultNS+"RelevantTo"), contextInd));
        }
        
        return isOK;
	}
	
	public void allocateVNACapability(OntModel ontM, Individual capInd, String vnaType, int consumeSize){
		DatatypeProperty dp = null;
		Statement ss = null;
		RDFNode rdf = null;
		int leftSize = 0;
		if(vnaType.equals("CPU")){
			dp = ontM.getDatatypeProperty(OntologyMeta.DefaultNS+"hasCPUCores");
		}else if(vnaType.equals("MEMORY")){
			dp = ontM.getDatatypeProperty(OntologyMeta.DefaultNS+"hasMemSize");
		}else if(vnaType.equals("STORAGE")){
			dp = ontM.getDatatypeProperty(OntologyMeta.DefaultNS+"hasStorageCapacity");
		}else{
			//do nothing
		}
		
		rdf = capInd.getPropertyValue(dp);
//		System.out.println("allocateVNACapability->"+rdf.asLiteral().toString());
		leftSize = Integer.parseInt(getStringFieldValue(rdf.asLiteral().toString()))-consumeSize;
		
//		System.out.println(leftSize);
		capInd.setPropertyValue(dp, ontM.createTypedLiteral(leftSize));
	}
	
	//Release the capability consumed by atomic task, and return the corresponding capInd
	public Individual releaseVNACapability(OntModel ontM, InfModel infM, Individual ataskInd){
		
		RDFNode rdf1 = ataskInd.getPropertyValue(ontM.getDatatypeProperty(OntologyMeta.DefaultNS+"resType"));
		
		String vnaType = getStringFieldValue(rdf1.asLiteral().toString());
		
        DatatypeProperty dp = null;

		if(vnaType.equals("CPU")){
			dp = ontM.getDatatypeProperty(OntologyMeta.DefaultNS+"hasCPUCores");
		}else if(vnaType.equals("MEMORY")){
			dp = ontM.getDatatypeProperty(OntologyMeta.DefaultNS+"hasMemSize");
		}else if(vnaType.equals("STORAGE")){
			dp = ontM.getDatatypeProperty(OntologyMeta.DefaultNS+"hasStorageCapacity");
		}else{
			//do nothing
		}
		
		RDFNode rdf2 = ataskInd.getPropertyValue(ontM.getDatatypeProperty(OntologyMeta.DefaultNS+"toState"));

		int allocatedSize = Integer.parseInt(getStringFieldValue(rdf2.asLiteral().toString()));
		int currentSize = 0;
		
		List<Individual> capIndList = getCurrentVNACapabilityofAtomicTask(ontM, infM, ataskInd);
		Individual capInd = null;
		//Release all the corresponding capability, only 1 capability individual is allocated, so we only need to recycle once.
		int index =0, size = capIndList.size();
		while(index < size){
			capInd = capIndList.get(index);
			
			currentSize = Integer.parseInt(getStringFieldValue(capInd.getPropertyValue(dp).asLiteral().toString()));
			//recycle the capability, but it will affect the subsequent requirement in the future
			capInd.setPropertyValue(dp, ontM.createTypedLiteral(currentSize + allocatedSize));
			
			index++;
		}
		
		return capInd;
	}
	
	//Relevant to the context
	public void relevantToContext(OntModel ontM, Individual ind, List<Individual> contextIndList){
        int size = contextIndList.size();
        Individual contextInd = null;
        for(int i =0; i<size; i++){
        	contextInd = contextIndList.get(i);
        	//the actor is Relevant to the context 
        	ontM.add(ontM.createStatement(ind, ontM.getObjectProperty(OntologyMeta.DefaultNS+"RelevantTo"), contextInd));
        }
	}
	
	//Clear the context of the agent if it no longer participate the session
	//To be perfect in the future
	/*
	 * We need to clear the corresponding context relevant to the actor which participate in the atomic task in the same session
	 * How to do?
	 * 1. Find the context relevant to this atomictask
	 * 2. Find the actor which just provide the capability to enable the atomic task
	 * 3. Get all the atomictasks that provided by the above actor
	 * 4. If there more than 1 atomictasks, do nothing, otherwise, we need to clear the actor's current context
	 */
	public void clearContextofAgentBindToAtomicTask(OntModel ontM, InfModel infM, Individual ataskInd){		
		//SPARQL string 
		String queryStr =  "PREFIX res: <http://purl.obolibrary.org/obo/bfo.owl#>" +
                           "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>"+
        		           "SELECT ?atask ?agent ?context "+ 
        		           " WHERE { res:"+ataskInd.getLocalName()+"  res:RelevantTo  ?context."+ 
        		           "         ?agent res:RelevantTo ?context." + 
        		           "         ?atask rdf:type   res:AtomicTask." + 
        		           "         ?context res:Contains   ?atask." + 
        		           "         res:"+ataskInd.getLocalName()+" res:EnableBy   ?cap." + 
        		           "         ?res   res:OwnBy      ?agent." + 
        		           "         ?res   res:hasCapability ?cap."+
        		           "}";
		
        Query query = QueryFactory.create(queryStr);
        
        // Execute the query and obtain results
        QueryExecution qe = QueryExecutionFactory.create(query, infM);
        
        ResultSet results = qe.execSelect();
        
		Individual contextInd = null, agentInd = null;	
		
        QuerySolution qs = null;
        
        int size = 0;
        while(results.hasNext()){    
        	qs = results.next();
        	contextInd = ontM.getIndividual(qs.get("?context").toString()); 
        	agentInd   = ontM.getIndividual(qs.get("?agent").toString());
//        	System.out.println("context: "+contextInd.getLocalName());
            size ++;
        }
        qe.close();
        
        //If there are no more than 1 atomictask relevant to this actor, then just clear its context 
        if(size<2 && null != contextInd && null != agentInd){
        	//This is used to remove one statement like (s,p,o)-(Bob, name, BobName) or (Bob, wife, Angela)
        	ontM.remove(agentInd, ontM.getObjectProperty(OntologyMeta.DefaultNS+"RelevantTo"), contextInd);
        	
        }else{
        	// do nothing
        }
		
	}
	
	public boolean reEnableAtomicTask(OntModel ontM, InfModel infM, Individual ataskInd, Individual capInd){
		boolean result = false;
		//Redeciding which capability to enable the atomic task based on the value
		setStatusofAtomicTask(ontM, ataskInd, "Redeciding");
		
		result = reSetAtomicTask(ontM, infM, capInd, ataskInd);
		
		if(result){
			setStatusofAtomicTask(ontM, ataskInd, "Decided");
		}
		
		return result;		
	}
	
	//Provide corresponding capability to enale the atomic task
	public boolean EnableAtomicTask(OntModel ontM, InfModel infM, Individual sEventInd, Individual planInd){
		List<Individual> indList = getRelativedUndecidedAtomicTaskIndividual(ontM, sEventInd);
		int size=indList.size(), index =0;
		Individual aTaskInd = null, tmpCPUaTaskInd=null, tmpMemaTaskInd=null, resInd = null, hardwareInd = null;
		List<Individual> contextIndList = getRelativedContextIndividual(ontM, infM, sEventInd);
		//vnaType is used to distinguish different VNA resource requirement
		String vnaResType = null;
		int cpuNum = 0, cpuFreq = 0, storageCapacity = 0, memSize = 0;
		boolean cpuIndicator = false, memIndicator=false, allEnable = true;
		String osType = null, osVersion = null;
		String tmpStr[] = null;
		int resSize = 0, resIndex =0;
		RDFNode rdf = null;
		List<Individual> resList = new LinkedList<Individual>();
		List<Individual> resList1 = new LinkedList<Individual>();
		/* 1st-Phase
		 * Firstly, query/enable capability based on atomicTask's requirement. Meanwhile, we should filter 
		 *          the unsatisfactory capability based on strategy spec
		 * Secondly, determine the Value for the AtomicTask. We define at most 3 values for an AtomicTask
		 * Thirdly, update the status of atomicTask and link them to the corresponding plan.
		 */
		for(;index < size;){
			aTaskInd = indList.get(index);
			//Deciding the AtomicTask, so initialize it in the first
			setStatusofAtomicTask(ontM, aTaskInd, "Deciding");
			
			//Link the corresponding atomic tasks to the specified plan
			linkPlanToAtomicTask(ontM, planInd, aTaskInd);
			
			rdf = aTaskInd.getPropertyValue(ontM.getProperty(OntologyMeta.DefaultNS+"resType"));
			if(null != rdf){
				//The Data Type of property is RANDOM if you don't specify it.
//				System.out.println("asLiteral->"+rdf.asLiteral().toString());
				vnaResType = getStringFieldValue(rdf.asLiteral().toString());

				if(vnaResType.equals("CPU") && cpuIndicator==false){
					//record the cpu atomic task
					tmpCPUaTaskInd = aTaskInd;
					//AtomicTask require CPU capability
					cpuNum = Integer.parseInt(getStringFieldValue(aTaskInd.getPropertyValue(ontM.getProperty(OntologyMeta.DefaultNS+"toState")).asLiteral().toString()));
					cpuIndicator = true;
					
				}
				
				if(vnaResType.equals("MEMORY") && memIndicator == false){
					//record the memory atomic task
					tmpMemaTaskInd = aTaskInd;
					//AtomicTask require MEMORY capability
					memSize = Integer.parseInt(getStringFieldValue(aTaskInd.getPropertyValue(ontM.getProperty(OntologyMeta.DefaultNS+"toState")).asLiteral().toString()));
					memIndicator = true;
					
				}
				//CPU and Memory can't be separated, so we must guarantee they are located in the same host 
				if(cpuIndicator && memIndicator){
					//Get the corresponding capability that can meet atomictask's requirement
					//We only care cpu number currently
					resList = getLimitedCPUCapability(ontM, infM, cpuNum, cpuFreq, memSize);
					//Get the corresponding capability that can meet atomictask's requirement
					//We need to query at the same time Since mem and cpu must be located in the same host currently
					resList1 = getLimitedMemCapability(ontM, infM, cpuNum, cpuFreq, memSize);
					
					if(null != resList){
						resSize = resList.size();
						//There are more than 1 ways to meet user's requirement, looks to me, we should only select at most 3 ways
						while(resIndex < resSize){
							resInd = resList.get(resIndex);
							//Create value individual to link to the corresponding plan
							CreateValueofSystemEvent(ontM, resInd, planInd, tmpCPUaTaskInd);
							
							//Enable the atomic task based on the value. Currently, the first value is selected to enable the atomic task.
							//In the future, we will select it based on some strategy
							if(resIndex == 0){
								setAtomicTask(ontM, infM, resInd, tmpCPUaTaskInd,contextIndList);
								/*
								 * 
								 * 
								 * 
								 * 
								 */
								//There will be a hardware individual to link the capability
								//Aollocate the capability
								allocateVNACapability(ontM, resInd,"CPU", cpuNum);	
							}
							resIndex++;
						}
					}else{
						//There are no capability can meet user's requirement
						System.err.println("CPU Resource shortage!");
						allEnable = false;
					}
					//Avoid next computation
					cpuIndicator = false;
					resIndex = 0;
					

					if(null != resList1){
						resSize = resList1.size();
						//There are more than 1 ways to meet user's requirement, looks to me, we should only select at most 3 ways
						while(resIndex < resSize){
							resInd = resList1.get(resIndex);
							//Create value individual to link to the corresponding plan
							if(null != resInd && null != tmpMemaTaskInd){
								CreateValueofSystemEvent(ontM, resInd, planInd, tmpMemaTaskInd);
							}

							
							//Enable the atomic task based on the value. Currently, the first value is selected to enable the atomic task.
							//In the future, we will select it based on some strategy
							if(resIndex == 0){
								setAtomicTask(ontM, infM, resInd, tmpMemaTaskInd,contextIndList);
								/*
								 * 
								 * 
								 * 
								 * 
								 */
								//There will be a hardware individual to link the capability
								allocateVNACapability(ontM, resInd,"MEMORY", memSize);	
							}
							
							resIndex++;
						}
					}else{
						//There are no capability can meet user's requirement
						System.err.println("Memory Resource shortage!");
						allEnable = false;
					}
					//Avoid next computation
					memIndicator = false;
					resIndex = 0;
					
				}
				
				if(vnaResType.equals("STORAGE")){
					//AtomicTask require STORAGE capability
					storageCapacity = Integer.parseInt(getStringFieldValue(aTaskInd.getPropertyValue(ontM.getProperty(OntologyMeta.DefaultNS+"toState")).asLiteral().toString()));
					//Get the corresponding capability that can meet atomictask's requirement
					//We only care cpu number currently
					resList = getStorageCapability(ontM, infM, storageCapacity);
					if(null != resList){
						resSize = resList.size();
						//There are more than 1 ways to meet user's requirement, looks to me, we should only select at most 3 ways
						while(resIndex < resSize){
							resInd = resList.get(resIndex);
							//Create value individual to link to the corresponding plan
							CreateValueofSystemEvent(ontM, resInd, planInd, aTaskInd);
							
							//Enable the atomic task based on the value. Currently, the first value is selected to enable the atomic task.
							//In the future, we will select it based on some strategy
							if(resIndex == 0){
								setAtomicTask(ontM, infM, resInd, aTaskInd, contextIndList);
								/*
								 * 
								 * 
								 * 
								 * 
								 */
								//There will be a hardware individual to link the capability
								allocateVNACapability(ontM, resInd,vnaResType, storageCapacity);
							}
							
							resIndex++;
						}
					}else{
						//There are no capability can meet user's requirement
						System.err.println("Storage Resource shortage!");
						allEnable = false;
					}
					
					resIndex = 0;
										
				}else if(vnaResType.equals("OS")){
					//AtomicTask require OS capability
					//TBD
					tmpStr = getStringFieldValue(aTaskInd.getPropertyValue(ontM.getProperty(OntologyMeta.DefaultNS+"toState")).asLiteral().toString()).split(" ");
					int i =0;
					while(i < tmpStr.length){
						if(i == 0){
							osType = tmpStr[i];
						}else if(i == 1){
							osVersion = tmpStr[i];
						}
						i++;
					}
					//Get the corresponding capability that can meet atomictask's requirement
					//We only care cpu number currently
					resList = getOSCapability(ontM, infM, osType, osVersion);
					if(null != resList){
						resSize = resList.size();
						//There are more than 1 ways to meet user's requirement, looks to me, we should only select at most 3 ways
						while(resIndex < resSize){
							resInd = resList.get(resIndex);
							//Create value individual to link to the corresponding plan
							CreateValueofSystemEvent(ontM, resInd, planInd, aTaskInd);
							
							//Enable the atomic task based on the value. Currently, the first value is selected to enable the atomic task.
							//In the future, we will select it based on some strategy
							if(resIndex == 0){
								setAtomicTask(ontM, infM, resInd, aTaskInd, contextIndList);
								/*
								 * 
								 * 
								 * 
								 * 
								 */
							}
							
							resIndex++;
						}
					}
					
					resIndex = 0;
				}else if(vnaResType.equals("NETWORK")){
					//TBD
					resIndex = 0;
				}else if(vnaResType.equals("APP")){
					//TBD
					resIndex = 0;
				}else if(vnaResType.equals("MIDDLEWARE")){
					//TBD
					resIndex = 0;
				}
				
//				else if(vnaResType.equals("CPU")||vnaResType.equals("MEMORY")){
//					//Do nothing
//				}else{
//					//Just for debug currently, this should be perfected in the future.
//					System.err.println("Unknow resource type required by user! Please check the goal spec!");
//				}
//				System.out.println(vnaResType);
			}
			
			//Deciding the AtomicTask, so initialize it in the first
			setStatusofAtomicTask(ontM, aTaskInd, "Decided");
			
			index ++;
		}
		return allEnable;		
	}
	
	/* 2nd-Phase
	 * Firstly, create hardware individual and allocate the corresponding resource  
	 *          
	 * Secondly, Create a corresponding vm individual to present to user
	 * Thirdly,  link the vm individual to the corresponding plan.
	 */
	public void testPlan(OntModel ontM, InfModel infM, Individual sEventInd, Individual planInd, Individual wloadInd){
		List<Individual> contextIndList = getRelativedContextIndividual(ontM, infM,sEventInd);
		//1st-Create a new hardware individual
		UUID uid = UUID.randomUUID();
		String newIndName = "hardware_" + uid.toString();
		Individual newHardwareInd = OntologyOperator.createIndividual(ontM, "Hardware", newIndName);
		//the planInd is SupportBy newHardwareInd
		Statement s1 = ontM.createStatement(planInd, ontM.getObjectProperty(OntologyMeta.DefaultNS+"SupportBy"),newHardwareInd);
		ontM.add(s1);
		System.out.println("Allocating Hardware: "+newHardwareInd.getLocalName());
		//Relevant to the context
		relevantToContext(ontM, newHardwareInd, contextIndList);

		//2nd-Create a new VM individual
		uid = UUID.randomUUID();
		newIndName = "vm_" + uid.toString();
		Individual newVMInd = OntologyOperator.createIndividual(ontM, "VirtualMachine", newIndName);
		//The planInd is LocatedIn the new vm individual
		Statement s2 = ontM.createStatement(planInd, ontM.getObjectProperty(OntologyMeta.DefaultNS+"LocatedIn"), newVMInd);
		ontM.add(s2);
		//Initial the vm currentStatus since it is in the fly
		Statement s22 = ontM.createStatement(newVMInd, ontM.getDatatypeProperty(OntologyMeta.DefaultNS+"currentStatus"), ontM.createTypedLiteral("Flying"));
		ontM.add(s22);
		//Relevant to the context
		relevantToContext(ontM, newVMInd, contextIndList);
		
		//The vm is ProvidedBy the above hardware
		Statement s3 = ontM.createStatement(newVMInd, ontM.getObjectProperty(OntologyMeta.DefaultNS+"ProvidedBy"), newHardwareInd);
		ontM.add(s3);
		//workload consumes the vm individual
		Statement s4 = ontM.createStatement(wloadInd, ontM.getObjectProperty(OntologyMeta.DefaultNS+"Consumes"), newVMInd);
		ontM.add(s4);
		
		System.out.println("Creating VM: "+newVMInd.getLocalName());
		
		List<Individual> indList = getRelativedDecidedAtomicTaskIndividual(ontM, infM, sEventInd);
		int size=indList.size(), index =0;
		Individual tmpInd = null;
		RDFNode rdf = null;
		String vnaResType = null;
		int capSize = 0;
		Statement ss = null;
		DatatypeProperty dp = null;
		
		for(;index < size;){
			tmpInd = indList.get(index);
			
			rdf = tmpInd.getPropertyValue(ontM.getProperty(OntologyMeta.DefaultNS+"resType"));
			if(null != rdf){
				//The Data Type of property is RANDOM if you don't specify it.
//				System.out.println("asLiteral->"+rdf.asLiteral().toString());
				vnaResType = getStringFieldValue(rdf.asLiteral().toString());
			}
					
			if(vnaResType.equals("CPU")){				
				dp = ontM.getDatatypeProperty(OntologyMeta.DefaultNS + "hasCPUCores");
//				ss = ontM.createStatement(newHardwareInd, ontM.getDatatypeProperty(OntologyMeta.DefaultNS + "hasCPUCores"), ontM.createTypedLiteral(capSize));
				capSize = Integer.parseInt(getStringFieldValue(tmpInd.getPropertyValue(ontM.getProperty(OntologyMeta.DefaultNS+"toState")).asLiteral().toString()));
				
				ss = ontM.createStatement(newHardwareInd, dp, ontM.createTypedLiteral(capSize));
				ontM.add(ss);
			}else if(vnaResType.equals("MEMORY")){
				dp = ontM.getDatatypeProperty(OntologyMeta.DefaultNS + "hasMemSize");
				capSize = Integer.parseInt(getStringFieldValue(tmpInd.getPropertyValue(ontM.getProperty(OntologyMeta.DefaultNS+"toState")).asLiteral().toString()));
				
				ss = ontM.createStatement(newHardwareInd, dp, ontM.createTypedLiteral(capSize));
				ontM.add(ss);
			}else if(vnaResType.equals("STORAGE")){
				dp = ontM.getDatatypeProperty(OntologyMeta.DefaultNS + "hasStorageCapacity");
				capSize = Integer.parseInt(getStringFieldValue(tmpInd.getPropertyValue(ontM.getProperty(OntologyMeta.DefaultNS+"toState")).asLiteral().toString()));
				
				ss = ontM.createStatement(newHardwareInd, dp, ontM.createTypedLiteral(capSize));
				ontM.add(ss);
			}else if(vnaResType.equals("OS")){
				//Do nothing currently
				
			}else{
				//Do nothing
			}			
//			ss = ontM.createStatement(newHardwareInd, dp, ontM.createTypedLiteral(capSize));
//			ontM.add(ss);
			
			index ++;
		}
	}
	
	//Define the change process by wrapping the atomic task
	public void defineChangeProcess(OntModel ontM, InfModel infM, Individual sEventInd, Individual changeInd){
		List<Individual> indList = getRelativedDecidedAtomicTaskIndividual(ontM, infM, sEventInd);
		int size=indList.size(), index =0;
		Individual tmpAtaskInd = null;
		Statement ss = null;
		for(;index<size;){
			tmpAtaskInd = indList.get(index);
			
			ss = ontM.createStatement(changeInd, ontM.getObjectProperty(OntologyMeta.DefaultNS+"WrapBy"), tmpAtaskInd);
			ontM.add(ss);
			
			index++;
		}
	}
	
	// Currently, we manual check the system event in the first phase.
	// There are some rules to filter the system event
	public void checkSystemEvent(OntModel ontM, InfModel infM){
		//Get all the individual of SystemEvent
//		List<Individual> tempIndList = getAllSystemEventIndividual(ontM);
		List<Individual> tempIndList = getAllUnresolvedSystemEventIndividual(ontM,infM);
		Iterator<Individual> it = tempIndList.iterator();
		Individual systemEventInd = null, planInd = null, workloadInd=null;
        Individual relativedGoalInd = null;
        Individual goalResourceSpecInd = null;
        Individual changeInd = null;
        StmtIterator sIt = null;
        Statement statem = null;
        //Temporal variable to store the Goal structure
        Goal goal = null;
        List<Individual> sSpecindList = null;
        List<Strategy> strategyList = null;
		//Context individual, which will context 
		List<Individual> contextIndList = null;
        boolean allEnable = false;
        boolean planOK = false;
        //The iterator can't change anything while iterating, how to solve this problem?
		while(it.hasNext()) {						
			systemEventInd = it.next();
			System.out.println("Handling System Event:"+systemEventInd.getLocalName());
			//Check the SystemEvent and then split it with AtomicTask
			//1st！！first off！！update the status of system event
			setStatusofSystemEvent(ontM, systemEventInd, "Running");
			
			//2nd！！Create a new corresponding Plan to handle the system event
			planInd = createPlanofSystemEvent(ontM,systemEventInd);
			System.out.println("Generating a corresponding Plan:"+planInd.getLocalName());
			
			//3rd！！Get the corresponding goal w.r.t the system event individual
			relativedGoalInd = getRelativedGoalIndividual(ontM, infM, systemEventInd);
			
			//In case there are some systemEvents without any goal to represent them
			if(relativedGoalInd!=null){
				System.out.println("Extracting the Goal:"+relativedGoalInd.getLocalName());
				//4th！！Get the goalspec(resouce spec and strategy spec) w.r.t the Goal one by one
				goalResourceSpecInd = getRelativedGoalResourceSpecIndividual(ontM, infM, relativedGoalInd);
				
				//5th！！Get the corresponding change individual and then wrap it with the atomic task
				changeInd = getRelativeChangeIndividual(ontM, infM, relativedGoalInd);
				
				if(null != goalResourceSpecInd){
					//6th！！Produce some AtomicTasks according to the goalspec linked by the SystemEvent
					goal = extractGoalSpecIndividualProperty(ontM, infM, goalResourceSpecInd);
					//The next method is better than SPARQL since SPARQL return nothing if the field is null
					//But need to perfect it after
//                    sIt = goalSpecInd.listProperties();
//                    while(sIt.hasNext()){
//                    	statem = sIt.next();
//                    	System.out.println(statem.toString());
//                    	System.out.println(statem.getSubject().toString()+" "+statem.getPredicate().toString()+" "+statem.getObject().toString());
//                    	System.out.println(statem.getObject());
//                    }
					//7th！！Parse the strategy spec and create a corresponding strategy individual
					sSpecindList = getRelativedGoalStrategySpecIndividual(ontM, infM, relativedGoalInd);
					if(null != sSpecindList){
						strategyList = extractStrategySpec(infM,sSpecindList);
						//Generate strategy individual
						initialiseStrategyFromStrategySpec(ontM, infM, relativedGoalInd, planInd, strategyList);
					}													
					
					//8th！！Create a workload individual based on the goal and the strategy
					workloadInd=createWorkloadIndividual(ontM, goalResourceSpecInd, planInd, strategyList);
					//Relevant to the context
					contextIndList = getRelativedContextIndividual(ontM,infM,systemEventInd);
					relevantToContext(ontM, workloadInd, contextIndList);
					
					//9th！！Create some atomicTask w.r.t the goal ...TBD...
					/* AtomicTask structure <Capablity, Action, FromState, ToState, Status>
					 * E.g: ATask_require_VNA_CPU_Capability <CPU, Source, 0, 2, undecided>
					 *      ATask_require_VNA_Mem_Capability <Memory, Source, 0, 1024, decided>
					 *      ATask_require_VNA_Storage_Capability <Storage, Source, 0, 20480, running>
					 *      ATask_require_VNA_OS_Capability <OS, Source, null, (Ubuntu,11.04), ran>
					 *      ATask_require_VNA_Link_Capability <Link, Source, null, (LocatedIn host1), undecided>
					 */
//					System.out.println(goal.getGoalOwner()+" "+goal.getVMName()+" "+goal.getGoalId() + " "+goal.getCPUCores() +" "+goal.getMemSize() +" "+goal.getStorageSize()+" "+goal.getOSVersion() +" "+goal.getOSType()+" "+goal.getVMLevel());					
					ParseToAtomicTask(ontM, infM, systemEventInd, goal);					
					//10th！！Entail to build the relationship between AtomicTask and Capability
					// AtomicTask is consumer, while capability is provider, we should make sure the consistency between the consumer and provider
					allEnable = EnableAtomicTask(ontM, infM, systemEventInd, planInd);
					
					//11th！！Test the plan if it can deliver to user
					if(allEnable){
						//11.1！！Define the changeInd by Wrapping the atomictask
						System.out.println("The Change is wrapped by AtomicTask!");
						defineChangeProcess(ontM, infM, systemEventInd,changeInd);
						
						testPlan(ontM, infM, systemEventInd, planInd,workloadInd);
						
						System.out.println("Plan "+ planInd.getLocalName() +" of "+systemEventInd.getLocalName()+" is completed!");
						
						planOK = true;
					}
				}

			}
			else{
				//Do nothing
			}
			if(planOK){
				//Finally, we set 
				setStatusofSystemEvent(ontM, systemEventInd, "Success");
			}else{
				setStatusofSystemEvent(ontM, systemEventInd, "Failed");
			}
		}
//		//Test
//        Individual ataskInd = OntologyOperator.createIndividual(ontM, "AtomicTask", "require_CPU");	
//		Statement ss = ontM.createStatement(ontM.getIndividual(OntologyMeta.DefaultNS+"sEvent_compute_2015"), ontM.getObjectProperty(OntologyMeta.DefaultNS+"ComposedBy"), ataskInd);
//		ontM.add(ss);
	}
	
	public void checkAtomicTask(OntModel ontM, InfModel infM){
		//Get all failed or discard atomic tasks
		List<Individual> fdAtaskList = getAllFailedorDiscardAtomicTaskIndividual(ontM,infM);
		List<Individual> valueIndList = null;
		int taskindex =0, tasksize =fdAtaskList.size();
		int valuesize = 0;
		boolean indicator = true;
		Individual ataskInd = null, valueInd = null, capInd = null;
		
		if(tasksize == 0){
			System.out.println("All AtomicTask run OK!");
		}else{
			while(taskindex < tasksize){
				ataskInd = fdAtaskList.get(taskindex);
				
				System.out.println("Handling AtomicTask: "+ataskInd.getLocalName());
				
				//Release the allocated capability, but we don't change the atomic task's properties.
				capInd = releaseVNACapability(ontM, infM, ataskInd);
				
				//Get all the relative value individual against the atomic task
				valueIndList = getAllValueIndividual(ontM, infM, ataskInd);
				
				//Start to handle the failed/discard atomic task based on the value
				valuesize = valueIndList.size();
				
				indicator = true;
				
				for(int valueIndex =0; valueIndex < valuesize; valueIndex++){
					//we will use one value to replace the failed atomictask
					//But how to select the best value? It depends on some strategies and mechanism
					//Currently, we select the first value if have
					valueInd = valueIndList.get(valueIndex);
					
					Resource res = valueInd.getPropertyResourceValue(ontM.getProperty(OntologyMeta.DefaultNS+"hasValueCapability"));
					//Find the first value that has value capability is not same with the current task's capability
					if(indicator && !res.getLocalName().equals(capInd.getLocalName())){
						//We need to consider the rules here that CPU and Memory must be in the same host
						//In the future, there maybe more detailed rules we should consider
						
						/* TBD
						 * Need to based on the vnaType as well as the sessionID to decide if there is a same Actor to provide CPU and Memory
						 */
						
						Individual newCapInd = ontM.getIndividual(OntologyMeta.DefaultNS + res.getLocalName());
						//It should follow the previous rule to decide which capability 
						reEnableAtomicTask(ontM, infM, ataskInd, newCapInd);
						
						indicator = false;
						break;
					}
					
				}
				
				taskindex++;
			}
		}		

	}

	//To entail the ontology, it will produce a plan(containing strategy, value, task as well as other things)
	//for the goal
	public void OntolgoyEntail(){
		
	}

	public static void main(String args[]){
		SCORAgent scorA = new SCORAgent();
		HostAgent hostA = new HostAgent();
		
		OntologyOperator ontOpt = new OntologyOperator();
		OntModel ontM = ontOpt.initialiseOntology();
		InfModel infM = ontOpt.initialiseOntologyWithPelletReasoner(ontM);
		String tmpStr = new String();
		for(int i =0;i<args.length; i++){
			if(args[i].equalsIgnoreCase("check")){
				System.out.println("Check/check");
				scorA.checkAtomicTask(ontM, infM);
				System.out.println("|->Concise view of AtomicTask<-|");
				hostA.printConciseAtomicTask(ontM, infM);	
		        ontOpt.closeOntology(ontM);
			}else if(args[i].equalsIgnoreCase("entail")){
				System.out.println("Entail/entail");
				scorA.checkSystemEvent(ontM, infM);
		        ontOpt.closeOntology(ontM);
			}else if(args[i].equalsIgnoreCase("clear")){				
		        ontOpt.removeAllIndividuals(ontM, infM);
		        ontOpt.closeOntology(ontM);
		        
			}else if(args[i].equalsIgnoreCase("print")){
				
				System.out.println("|->User issue a Goal with goal spec and strategy spec<-|");
				hostA.printGoal(ontM, infM);
				
				System.out.println("|->Goal RepresentBy SystemEvent<-|");
				hostA.printSystemEvent(ontM, infM);
				
				System.out.println("|->Plan to acheive the goal<-|");
				hostA.printPlan(ontM, infM);
				
				System.out.println("|->Strategy FollowBy the goal and Compose the plan<-|");
				hostA.printStrategy(ontM, infM);
				
				System.out.println("|->AtomicTask compose the Plan and issued to the corresponding Agents<-|");
				hostA.printAtomicTask(ontM, infM);						
				
				System.out.println("|->Concise view of AtomicTask<-|");
				hostA.printConciseAtomicTask(ontM, infM);	
				
				System.out.println("|->Value in correspond to the AtomicTask<-|");
				hostA.printValue(ontM, infM);
				
				System.out.println("|->W/V/H in the plan: Workload consume VM, Hardware provide the VM<-|");
				hostA.printWVH(ontM, infM);
				
				System.out.println("|->Workload monitor the vm and user's goal<-|");
				hostA.printWorkload(ontM, infM);
				
				System.out.println("|->Hardware locate the plan<-|");
				hostA.printHardware(ontM, infM);
				
				System.out.println("|->Change is WrapBy AtomicTask<-|");
				hostA.printChange(ontM, infM);
				
				System.out.println("|->Context<-|");
				hostA.printContext(ontM, infM);
			}else{
				System.out.println("Please input your cmd with the following options:\n(1)Entail-handle the system event;\n(2)Check-Check the result of atomic task;\n(3)Print-Output the result;\n(4)Clear-remove all individuals.");
			}
		}
	}
}
