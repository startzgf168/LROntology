package com.sky.lr.agent.interf;

/* Author:ZGF
 * Description: Supply-chain operations reference-model (SCOR) is a process reference model 
 * for supply chain management. This reference model enables users to address, improve, and communicate 
 * supply chain management practices within and between all interested parties in the extended enterprise.
 * The model is based on 3 major "pillars":\
 *           1.Process modeling and re-engineering
 *           2.Performance measurements
 *           3.Best practices
 * 
 * Date:2015-7-14
 * */

public interface SCOROperator {
	
	/*SCOR Plan operator
	 * Explanation: Processes that balance aggregate demand and supply to develop 
	 * a course of action which best meets sourcing, production, and delivery requirements.
	 * */
	public void SCORPlan();
	
	/*SCOR Source operator
	 * Explanation: Processes that procure goods and services to meet planned or actual demand.
	 * */
	public void SCORSource();
	
	/*SCOR Make operator
	 * Explanation: Processes that transform product to a finished state to meet planned or actual demand.
	 * */
	public void SCORMake();
	
	/*SCOR Deliver operator
	 * Explanation: Processes that provide finished goods and services to meet planned or actual demand, 
	 * typically including order management, transportation management, and distribution management.
	 * */
	public void SCORDeliver();

	/*SCOR Return operator
	 * Explanation: Processes associated with returning or receiving returned products for any reason. 
	 * These processes extend into post-delivery customer support.
	 * */
	public void SCORReturn();
	
}