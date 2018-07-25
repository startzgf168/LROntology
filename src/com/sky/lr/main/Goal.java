package com.sky.lr.main;

public class Goal {
	//The URI of a Goal
	public  String goalId = null;
	
	//The owner of this goal, which Actor owns this goal
	public String goalOwner = null;
	
	//The context of this goal
	public String context = null;
	
	//The project name within the goal
	public  String projectName = null;
	
	//Resource spec
	public  String resourceSPEC = null;
	
	//CPU cores
	public short cpuCores = 0;
	
	//Memory size, unit:MB
	public int memSize = 0;
	
	//Storage capacity, unit:MB
	public int storageSize = 0;
	
	//OS version
	public String osVersion = null;
	
	//OS type
	public String osType = null;
	
	//VM name
	public String vmName = null;
	
	//VM level, enum: 0-5
	public short vmLevel = 0;
	
	public Goal(){};
	
	public Goal(String goalId, String projectName, String spec){
		this.goalId = goalId;
		this.projectName = projectName;
		this.resourceSPEC = spec;
	}
	
	public Goal(String goalId, short vmLevel, String vmName, short cpuCores, int memSize, int storageSize, String osType, String osVersion){
		this.goalId = goalId;
		this.vmLevel = vmLevel;
		this.vmName = vmName;
		this.cpuCores = cpuCores;
		this.memSize = memSize;
		this.storageSize = storageSize;
		this.osType = osType;
		this.osVersion = osVersion;
	}
	
	public void setGoalOwner(String owner){
		this.goalOwner = owner;
	}
	
	public String getGoalOwner(){
		return this.goalOwner;
	}
	
	public String getGoalId(){
		return this.goalId;
	}
	
	public void setGoalId(String goalId){
		this.goalId = goalId;
	}
	
	public String getProjectName(){
		return this.projectName;
	}
	
	public void setProjectName(String proName){
		this.projectName = proName;
	}
	
	public short getCPUCores(){
		return this.cpuCores;
	}
	
	public void setCPUCores(short cpuCores){
		this.cpuCores = cpuCores;
	}
	
	public int getMemSize(){
		return this.memSize;
	}
	
	public void setMemSize(int memSize){
		this.memSize = memSize;
	}
	
	public int getStorageSize(){
		return this.storageSize;
	}
	
	public void setStorageSize(int storageSize){
		this.storageSize = storageSize;
	}
	
	public String getOSType(){
		return this.osType;
	}
	
	public void setOSType(String osType){
		this.osType = osType;
	}
	
	public String getOSVersion(){
		return this.osVersion;
	}
	
	public void setOSVersion(String osVersion){
		this.osVersion = osVersion;
	}
	
	public String getVMName(){
		return this.vmName;
	}
	
	public void setVMName(String vmName){
		this.vmName = vmName;
	}
	
	public short getVMLevel(){
		return this.vmLevel;
	}
	
	public void setVMLevel(short vmLevel){
		this.vmLevel = vmLevel;
	}
	
	//TBD
	
}
