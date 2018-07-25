package com.sky.lr.main;

public class Strategy {
	//There are 4 different value to indicate different level
	public static enum Availability{  
		  Default, Low, middle, High  
		} 
	
	public Availability AvailabilityLevel = Availability.Default;
	
	//There are 4 different value to indicate different level
	public static enum Scalability {  
		  Default, Low, middle, High  
		} 
	
	public Scalability ScalabilityLevel = Scalability.Default;
	
	//There are 4 different value to indicate different level
	public static enum Reliability {  
		  Default, Low, middle, High  
		} 
	
	public Reliability ReliabilityLevel = Reliability.Default;
	
	public Strategy(){}
	
	public Strategy(Availability a, Scalability s, Reliability r){
		this.AvailabilityLevel = a;
		this.ScalabilityLevel = s;
		this.ReliabilityLevel = r;
	}
	
	public void setAvaiablityLevel(String level){
		if(level.equals("Low")){
			this.AvailabilityLevel = Availability.Low;
		}else if(level .equals( "Medium")){
			this.AvailabilityLevel = Availability.middle;
		}else if(level .equals( "High")){
			this.AvailabilityLevel = Availability.High;
		}else{
			//do nothing, just in case
			System.err.println("Invalid value of Availablity Level!");;
		}
	}
	
	public Availability getAvailabilityLevel(){
		return this.AvailabilityLevel;
	}
	
	public void setScalabilityLevel(String level){
		if(level.equals("Low")){
			this.ScalabilityLevel = Scalability.Low;
		}else if(level .equals( "Medium")){
			this.ScalabilityLevel = Scalability.middle;
		}else if(level .equals( "High")){
			this.ScalabilityLevel = Scalability.High;
		}else{
			//do nothing, just in case
			System.err.println("Invalid value of Scalability Level!");;
		}
	}
	
	public Scalability getScalabilityLevel(){
		return this.ScalabilityLevel;
	}
	
	public void setReliabilityLevel(String level){
		if(level.equals("Low")){
			this.ReliabilityLevel = Reliability.Low;
		}else if(level .equals( "Medium")){
			this.ReliabilityLevel = Reliability.middle;
		}else if(level .equals( "High")){
			this.ReliabilityLevel = Reliability.High;
		}else{
			//do nothing, just in case
			System.err.println("Invalid value of Reliability Level!");;
		}
	}
	
	public Reliability getReliabilityLevel(){
		return this.ReliabilityLevel;
	}
	
}
