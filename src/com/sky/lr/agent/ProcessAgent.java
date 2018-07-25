package com.sky.lr.agent;

import com.hp.hpl.jena.ontology.Individual;
import com.hp.hpl.jena.ontology.OntClass;
import com.hp.hpl.jena.ontology.OntModel;
import com.sky.lr.agent.interf.SCOROperator;
import com.sky.lr.main.Goal;
import com.sky.lr.main.SystemEvent;
import com.sky.lr.utility.OntologyOperator;

public class ProcessAgent implements SCOROperator {

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
	
	//Demo: Produce a goal to simulate the input from UI
	public void issueGoal(String goalName, Goal goal){
		OntologyOperator oOpt = new OntologyOperator();
		
		OntModel oM = oOpt.initialiseOntology();
        
		//Create a Goal individual 
		Individual goal_1= oOpt.createIndividual(oM, "Goal", goalName);
		
		if(goal_1 != null){
			System.out.println(goal_1.getLocalName());
		}else{
			System.out.println("There already exist a same object!");
		}

		oOpt.closeOntology(oM);
	}
	
	//Goal produced by user, the goal will be transferred from UI
	public Goal getGoal(OntModel ontM, String goalName){
		Goal goal = new Goal();
		
		//Parsing the goal structure from the user's input profile
		
		return goal;
	}
   
	
	//Goal -> SystemEvent
	// A system event will be generated and issued to ontology
	// In addition, both the Goal and SystemEvent will be recorded in the ontology
	public void issueSystemEvent(Object obj1, Object obj2){
		
	}
	
	//Get the system event, 
	// It seems we don't need to get the system event since it's only used in the ontology
	public SystemEvent getSystemEvent(OntModel ontM, String sEventName){
		return null;		
	}
	
	public static void main(String args[]){
	
//		ProcessAgent pAgent = new ProcessAgent();
//		Goal goal = new Goal();
//		//Test case 1
//        pAgent.issueGoal("goal", goal);
		 String arch = System.getProperty("sun.arch.data.model");


		 System.out.println(arch + "-bit");
		

	}
}
