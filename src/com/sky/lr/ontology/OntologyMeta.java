package com.sky.lr.ontology;

public final class OntologyMeta {
	//The file path of LR ontology
//	public static String filepath = "f:/bfoexample/BFO_LR_0805_SAM.owl";
	public static String filepath = "D:/LROntology/BFO_LR_0805_SAM.owl";
	
	//File name of LR ontology
	public static String filename = "BFO_LR_0709.owl";
	
	//URI of concepts in the LR ontology. It inherit from BFO version 2.0.
	public static String DefaultURI = "http://purl.obolibrary.org/obo/bfo.owl";
	
	//The Namespace of concepts, which is used to identify the concept. 
	public static String DefaultNS = DefaultURI + "#";
	
	//NS of XSD, used to identify data type
	public static String XSDNS = "http://www.w3.org/2001/XMLSchema#";
	
	//NS of SWRLB
	public static String SWRLBNS = "http://www.w3.org/2003/11/swrlb#";
	
	//NS of OBO, specific domain 
	public static String OBONS = "http://purl.obolibrary.org/obo/";
	
	//NS of OWL
	public static String OWLNS = "http://www.w3.org/2002/07/owl#";
	
	//NS of SWRL
	public static String SWRLNS = "http://www.w3.org/2003/11/swrl#";
	
	//NS of RDFS
	public static String RDFS = "http://www.w3.org/2003/11/swrl#";
	
	//The number of thread
	public static int threadNum = 10;
	
	// TBD
}
