package com.sky.lr.agent;

import java.util.ArrayList;
import java.util.List;

import com.hp.hpl.jena.ontology.Individual;
import com.hp.hpl.jena.ontology.OntModel;
import com.hp.hpl.jena.query.Query;
import com.hp.hpl.jena.query.QueryExecution;
import com.hp.hpl.jena.query.QueryExecutionFactory;
import com.hp.hpl.jena.query.QueryFactory;
import com.hp.hpl.jena.query.QuerySolution;
import com.hp.hpl.jena.query.ResultSet;
import com.hp.hpl.jena.query.ResultSetFormatter;
import com.hp.hpl.jena.rdf.model.InfModel;
import com.sky.lr.agent.interf.SCOROperator;
import com.sky.lr.utility.OntologyOperator;

public class HostAgent implements SCOROperator {

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
	
	public void printGoal(OntModel ontM, InfModel infM){
		//SPARQL string 
		String queryStr =  "PREFIX res: <http://purl.obolibrary.org/obo/bfo.owl#>" +
                           "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>"+
        		           "SELECT ?goal ?goalspec ?stra "+ 
        		           " WHERE { ?goal  rdf:type  res:Goal." + 
        		           "         ?goal  res:hasResourceSpec ?goalspec."+
        		           "         ?goal  res:hasStrategySpec ?stra.}";
		
        Query query = QueryFactory.create(queryStr);
        
        // Execute the query and obtain results
        QueryExecution qe = QueryExecutionFactory.create(query, infM);
        
        ResultSet results = qe.execSelect();
        
		//Normal printout
	    ResultSetFormatter.out(System.out, results, query);

        qe.close();
        	
	}
	
	public void printSystemEvent(OntModel ontM, InfModel infM){
		//SPARQL string 
		String queryStr =  "PREFIX res: <http://purl.obolibrary.org/obo/bfo.owl#>" +
                           "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>"+
        		           "SELECT ?goal ?sEvent "+ 
        		           " WHERE { ?goal  rdf:type  res:Goal." + 
        		           "         ?goal  res:RepresentBy ?sEvent.}";
		
        Query query = QueryFactory.create(queryStr);
        
        // Execute the query and obtain results
        QueryExecution qe = QueryExecutionFactory.create(query, infM);
        
        ResultSet results = qe.execSelect();
    
		//Normal printout
	    ResultSetFormatter.out(System.out, results, query);

        qe.close();
        	
	}
	
	public void printPlan(OntModel ontM, InfModel infM){
		//SPARQL string 
		String queryStr =  "PREFIX res: <http://purl.obolibrary.org/obo/bfo.owl#>" +
                           "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>"+
        		           "SELECT ?goal ?sEvent ?plan "+ 
        		           " WHERE { ?goal  rdf:type  res:Goal." + 
        		           "         ?goal  res:RepresentBy ?sEvent."+
        		           "         ?plan  res:ComposedBy  ?sEvent.}";
		
        Query query = QueryFactory.create(queryStr);
        
        // Execute the query and obtain results
        QueryExecution qe = QueryExecutionFactory.create(query, infM);
        
        ResultSet results = qe.execSelect();
    
		//Normal printout
	    ResultSetFormatter.out(System.out, results, query);

        qe.close();
        	
	}
	
	public void printStrategy(OntModel ontM, InfModel infM){
		//SPARQL string 
		String queryStr =  "PREFIX res: <http://purl.obolibrary.org/obo/bfo.owl#>" +
                           "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>"+
        		           "SELECT ?goal ?plan ?strategy"+ 
        		           " WHERE { ?goal  rdf:type  res:Goal." + 
        		           "         ?goal  res:RepresentBy ?sEvent."+
        		           "         ?plan  res:ComposedBy  ?sEvent."+
        		           "         ?goal  res:Follow      ?strategy.}";
		
        Query query = QueryFactory.create(queryStr);
        
        // Execute the query and obtain results
        QueryExecution qe = QueryExecutionFactory.create(query, infM);
        
        ResultSet results = qe.execSelect();
    
		//Normal printout
	    ResultSetFormatter.out(System.out, results, query);

        qe.close();
        	
	}
	
	public void printAtomicTask(OntModel ontM, InfModel infM){
		//SPARQL string 
		String queryStr =  "PREFIX res: <http://purl.obolibrary.org/obo/bfo.owl#>" +
                           "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>"+
        		           "SELECT ?plan ?atask ?sessionID ?Target ?Cap ?toState ?SCORAction"+ 
        		           " WHERE { ?goal  rdf:type  res:Goal." + 
        		           "         ?goal  res:RepresentBy ?sEvent."+
        		           "         ?plan  res:ComposedBy  ?sEvent."+
        		           "         ?atask rdf:type res:AtomicTask."+
        		           "         ?atask res:sessionID      ?sessionID."+
        		           "         ?atask res:expectedTarget ?Target."+
        		           "         ?atask res:resType        ?Cap."+
        		           "         ?atask res:toState        ?toState."+
        		           "         ?atask res:scorAction     ?SCORAction.}";
		
        Query query = QueryFactory.create(queryStr);
        
        // Execute the query and obtain results
        QueryExecution qe = QueryExecutionFactory.create(query, infM);
        
        ResultSet results = qe.execSelect();
    
		//Normal printout
	    ResultSetFormatter.out(System.out, results, query);

        qe.close();
        	
	}
	
	public void printConciseAtomicTask(OntModel ontM, InfModel infM){
		//SPARQL string 
		String queryStr =  "PREFIX res: <http://purl.obolibrary.org/obo/bfo.owl#>" +
                           "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>"+
        		           "SELECT ?atask  ?Target ?Cap  ?status"+ 
        		           " WHERE { ?goal  rdf:type  res:Goal." + 
        		           "         ?goal  res:RepresentBy ?sEvent."+
        		           "         ?plan  res:ComposedBy  ?sEvent."+
        		           "         ?atask rdf:type res:AtomicTask."+
        		           "         ?atask res:sessionID      ?sessionID."+
        		           "         ?atask res:expectedTarget ?Target."+
        		           "         ?atask res:resType        ?Cap."+
        		           "         ?atask res:toState        ?toState."+
        		           "         ?atask res:status         ?status."+
        		           "         ?atask res:scorAction     ?SCORAction.}";
		
        Query query = QueryFactory.create(queryStr);
        
        // Execute the query and obtain results
        QueryExecution qe = QueryExecutionFactory.create(query, infM);
        
        ResultSet results = qe.execSelect();
    
		//Normal printout
	    ResultSetFormatter.out(System.out, results, query);

        qe.close();
        	
	}
	
	public void printValue(OntModel ontM, InfModel infM){
		//SPARQL string 
		String queryStr =  "PREFIX res: <http://purl.obolibrary.org/obo/bfo.owl#>" +
                           "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>"+
        		           "SELECT ?atask ?value"+ 
        		           " WHERE { ?goal  rdf:type  res:Goal." + 
        		           "         ?goal  res:RepresentBy ?sEvent."+
        		           "         ?plan  res:ComposedBy  ?sEvent."+
        		           "         ?plan  res:ComposedBy  ?atask."+
        		           "         ?atask rdf:type res:AtomicTask."+
        		           "         ?value rdf:type res:Value."+
        		           "         ?value res:PerformOn    ?atask.} ORDER BY(?atask)";
		
        Query query = QueryFactory.create(queryStr);
        
        // Execute the query and obtain results
        QueryExecution qe = QueryExecutionFactory.create(query, infM);
        
        ResultSet results = qe.execSelect();
    
		//Normal printout
	    ResultSetFormatter.out(System.out, results, query);

        qe.close();
        	
	}
	
	public void printWVH(OntModel ontM, InfModel infM){
		//SPARQL string 
		String queryStr =  "PREFIX res: <http://purl.obolibrary.org/obo/bfo.owl#>" +
                           "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>"+
        		           "SELECT ?plan ?wload ?hardw"+ 
        		           " WHERE { ?goal  rdf:type  res:Goal." + 
        		           "         ?goal  res:RepresentBy ?sEvent."+
        		           "         ?plan  res:ComposedBy  ?sEvent."+
        		           "         ?plan  res:LocatedIn   ?vm."+
        		           "         ?wload rdf:type        res:Workload."+
        		           "         ?plan  res:ComposedBy  ?wload."+
        		           "         ?hardw rdf:type        res:Hardware."+
        		           "         ?plan  res:SupportBy  ?hardw.}";
		
        Query query = QueryFactory.create(queryStr);
        
        // Execute the query and obtain results
        QueryExecution qe = QueryExecutionFactory.create(query, infM);
        
        ResultSet results = qe.execSelect();
    
		//Normal printout
	    ResultSetFormatter.out(System.out, results, query);

        qe.close();
        	
	}
	
	public void printWorkload(OntModel ontM, InfModel infM){
		//SPARQL string 
		String queryStr =  "PREFIX res: <http://purl.obolibrary.org/obo/bfo.owl#>" +
                           "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>"+
        		           "SELECT ?plan ?indicator1 ?indicator2 ?indicator3"+ 
        		           " WHERE { ?goal  rdf:type  res:Goal." + 
        		           "         ?goal  res:RepresentBy ?sEvent."+
        		           "         ?plan  res:ComposedBy  ?sEvent."+
        		           "         ?wload rdf:type        res:Workload."+
        		           "         ?plan  res:ComposedBy  ?wload."+
        		           "         ?wload res:hasIndicator1  ?indicator1."+
        		           "         ?wload res:hasIndicator2  ?indicator2."+
        		           "         ?wload res:hasIndicator3  ?indicator3.}";
		
        Query query = QueryFactory.create(queryStr);
        
        // Execute the query and obtain results
        QueryExecution qe = QueryExecutionFactory.create(query, infM);
        
        ResultSet results = qe.execSelect();
    
		//Normal printout
	    ResultSetFormatter.out(System.out, results, query);

        qe.close();
        	
	}
	
	public void printHardware(OntModel ontM, InfModel infM){
		//SPARQL string 
		String queryStr =  "PREFIX res: <http://purl.obolibrary.org/obo/bfo.owl#>" +
                           "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>"+
        		           "SELECT ?plan ?hardw ?cpuCores ?memSize ?stgCapacity"+ 
        		           " WHERE { ?goal  rdf:type  res:Goal." + 
        		           "         ?goal  res:RepresentBy ?sEvent."+
        		           "         ?plan  res:ComposedBy  ?sEvent."+
        		           "         ?hardw res:hasCPUCores ?cpuCores."+
        		           "         ?hardw res:hasMemSize  ?memSize."+
        		           "         ?hardw res:hasStorageCapacity  ?stgCapacity."+
        		           "         ?hardw rdf:type        res:Hardware."+
        		           "         ?plan  res:SupportBy  ?hardw.}";
		
        Query query = QueryFactory.create(queryStr);
        
        // Execute the query and obtain results
        QueryExecution qe = QueryExecutionFactory.create(query, infM);
        
        ResultSet results = qe.execSelect();
    
		//Normal printout
	    ResultSetFormatter.out(System.out, results, query);

        qe.close();
        	
	}
	
	public void printChange(OntModel ontM, InfModel infM){
		//SPARQL string 
		String queryStr =  "PREFIX res: <http://purl.obolibrary.org/obo/bfo.owl#>" +
                           "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>"+
        		           "SELECT ?change ?atask"+ 
        		           " WHERE { ?goal  rdf:type  res:Goal." + 
        		           "         ?goal  res:RepresentBy ?sEvent."+
        		           "         ?goal  res:hasFunction ?change."+
        		           "         ?change  res:WrapBy    ?atask.} ORDER BY(?change)";
		
        Query query = QueryFactory.create(queryStr);
        
        // Execute the query and obtain results
        QueryExecution qe = QueryExecutionFactory.create(query, infM);
        
        ResultSet results = qe.execSelect();
    
		//Normal printout
	    ResultSetFormatter.out(System.out, results, query);

        qe.close();
        	
	}
	
	public void printContext(OntModel ontM, InfModel infM){
		//SPARQL string 
		String queryStr =  "PREFIX res: <http://purl.obolibrary.org/obo/bfo.owl#>" +
                           "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>"+
        		           "SELECT ?context ?object"+ 
        		           " WHERE { ?context  rdf:type  res:Context." + 
        		           "         ?context  res:Contains ?object.} ORDER BY(?context)";
		
        Query query = QueryFactory.create(queryStr);
        
        // Execute the query and obtain results
        QueryExecution qe = QueryExecutionFactory.create(query, infM);
        
        ResultSet results = qe.execSelect();
    
		//Normal printout
	    ResultSetFormatter.out(System.out, results, query);

        qe.close();
        	
	}
	
	
	public static void main(String args[]){
		//It's used to demo the plan structure currently
		HostAgent hostA = new HostAgent();
		OntologyOperator ontOpt = new OntologyOperator();
		OntModel ontM = ontOpt.initialiseOntology();
		InfModel infM = ontOpt.initialiseOntologyWithPelletReasoner(ontM);
		
		for(int i =0;i<args.length; i++){
			if(args[i].equalsIgnoreCase("print")){
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
				System.out.println("Please input your cmd with the specified format.");
			}
		}



		
	}

}
