package com.sky.lr.main;

public class test {
	public static void main(String args[]){
		Task t1 = null, t2 =null, t3=null;
		
		t1 = new Task();
		t2 = t1;
		System.out.println(t1.toString());
		System.out.println(t2.toString());
		
		t1 = new Task();
		t3 = t1;
		System.out.println(t1.toString());
		System.out.println(t2.toString());
		System.out.println(t3.toString());
	}
}
