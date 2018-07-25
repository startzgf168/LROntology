package com.sky.lr.test;

import java.io.IOException;
import java.nio.ByteOrder;


public class ProgramSkillTest {
	
	public static void printConsecutiveNum(int x){
		int num = x/2;
		String result = "";
		for(int i =1; i<num+1; i++){
			for(int k=1; k<num+1; k++){
				if((i+i+k)*(k+1)/2 == x){
					for(int m=i; m<i+k+1; m++){
						result += (Integer.toString(m) +" ");
					}
					result += "\n";
				}
				else{
					continue;
				}
			}
		}
		System.out.println(result);
	}

	public static void main(String args[]){
		
		System.out.println("Please input an integer:");
		int x = 0;
		try {
			 x= (int)System.in.read();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		printConsecutiveNum(15);
		
		int i = 1;
		if(1 == i){
			System.out.println(i);
		}
		
		byte a = 'a';
		byte b = 'b';
		
		//a+b is different with a+=b
		b = (byte) (a + b);
		
		a+=b;
		
		System.out.println(b);
		
		System.out.println("a="+ (a+=b));
		
		//Check what's the type of ENDIAN for Java
		if(ByteOrder.nativeOrder() == ByteOrder.BIG_ENDIAN){
			System.out.println("Big endian");
		}else{
			System.out.println("Little endian");
		}
		 
		//Self coding to check what's the type of ENDIAN for Java
		long ll = 0x0102030405060708l;
		byte bb = (byte) ll;
		
		System.out.println(bb);
		//Try to check the memory of jvm
		System.out.println(Runtime.getRuntime().freeMemory());
		System.out.println(Runtime.getRuntime().maxMemory());
		System.out.println(Runtime.getRuntime().totalMemory());
		
		//Try to check the cpu status
		System.out.println(Runtime.getRuntime().availableProcessors());
		
		//Exchange without new variable
		int value1 = 100, value2=200;
		
		value1 = value1+value2;
		
		value2 = value1 - value2;
		
		value1 = value1 - value2;
		
		System.out.println( value1);
		System.out.println(value2);
		
	}
}
