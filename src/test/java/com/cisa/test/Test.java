package com.cisa.test;

public class Test {

//	public static void main(String[] args){
//
//	}
	
	public static String generateInitials ( String original ){
	       String initial = "";
	       String[] split = original.split(" ");

	       for(String value : split){
	            initial += value.substring(0,1);
	        }

	        return initial;
	    }

	    public static void main(String[] arg){
	        System.out.println( generateInitials("This is a sample code on getting the first letter of every word in a String using Java. This may be helpful if you want") );
	    }
	
}
