import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Main {
	static int[] PC1 = {57 , 49 , 41 , 33 , 25 , 17 , 9 ,
		       1 , 58 , 50 , 42 , 34 , 26 , 18 ,
		      10 ,  2 , 59 , 51 , 43 , 35 , 27 ,
		      19 , 11 ,  3 , 60 , 52 , 44 , 36 ,
		      63 , 55 , 47 , 39 , 31 , 23 , 15 ,
		       7 , 62 , 54 , 46 , 38 , 30 , 22 ,
		      14 ,  6 , 61 , 53 , 45 , 37 , 29 ,
		      21 , 13 ,  5 , 28 , 20 , 12 , 4 };
	static int[] PC2 = {14 , 17 , 11 , 24 , 1 , 5 ,
		          3 , 28 , 15 , 6 , 21 , 10 ,
		         23 , 19 , 12 , 4 , 26 ,  8 , 
		         16 ,  7 , 27 , 20 , 13 , 2 ,
		         41 , 52 , 31 , 37 , 47 , 55 ,
		         30 , 40 , 51 , 45 , 33 , 48 ,
		         44 , 49 , 39 , 56 , 34 , 53 ,
		         46 , 42 , 50 , 36 , 29 , 32};
	static int[] IP = {58 , 50 , 42 , 34 , 26 , 18 , 10 , 2 ,
			60 , 52 , 44 , 36 , 28 , 20 , 12 , 4 ,
			62 , 54 , 46 , 38 , 30 , 22 , 14 , 6 ,
			64 , 56 , 48 , 40 , 32 , 24 , 16 , 8 ,
			57 , 49 , 41 , 33 , 25 , 17 , 9 , 1 ,
			59 , 51 , 43 , 35 , 27 , 19 , 11 , 3 ,
			61 , 53 , 45 , 37 , 29 , 21 , 13 , 5 ,
			63 , 55 , 47 , 39 , 31 , 23 , 15 , 7};
	
	static int[] EXPAND_BITS = {32 , 1 , 2 , 3 , 4 , 5 ,
		  	4 , 5 , 6 , 7 , 8 , 9 ,
			8 , 9 , 10 , 11 , 12 , 13 ,
			12 , 13 , 14 , 15 , 16 , 17 ,
			16 , 17 , 18 , 19 , 20 , 21 ,
			20 , 21 , 22 , 23 , 24 , 25 ,
			24 , 25 , 26 , 27 , 28 , 29 ,
			28 , 29 , 30 , 31 , 32 , 1 };
	
	static int[][] S1= { {14 , 4 , 13 , 1 , 2 , 15 , 11 , 8 , 3 , 10 , 6 , 12 , 5 , 9 , 0 , 7 },
			{ 0 , 15 , 7 , 4 , 14 , 2 , 13 , 1 , 10 , 6 , 12 , 11 , 9 , 5 , 3 , 8 },
		    { 4 , 1 , 14 , 8 , 13 , 6 , 2 , 11 , 15 , 12 , 9 ,7 , 3 , 10 , 5 , 0 },
			{15 , 12 , 8 , 2 , 4 , 9 , 1 , 7 , 5 , 11 , 3 , 14 , 10 , 0 , 6 , 13}};
	
	static int[][] S2= { {15 , 1 , 8 , 14 , 6 , 11 , 3 , 4 ,9, 7, 2, 13, 12, 0 ,5 ,10},
            { 3 ,13 ,4 ,7 ,15, 2, 8 ,14 ,12 ,0 ,1 ,10 ,6 ,9 ,11, 5},
            {0 ,14, 7 ,11 ,10 ,4 ,13, 1 ,5 ,8 ,12, 6, 9, 3 ,2 ,15 },
            {13 ,8 ,10 ,1 ,3, 15, 4 ,2 ,11 ,6 ,7, 12, 0, 5 ,14 ,9}};
	
	static int[][] S3= { {10 ,0 ,9 ,14, 6 ,3 ,15, 5 ,1 ,13 ,12 ,7 ,11, 4 ,2 ,8},
            { 13 ,7 ,0 ,9 ,3 ,4 ,6, 10, 2, 8, 5, 14, 12, 11, 15, 1 },
            {13 ,6 ,4, 9 ,8 ,15 ,3, 0 ,11 ,1 ,2 ,12 ,5 ,10 ,14 ,7},
            {1 ,10 ,13 ,0, 6,9 ,8 ,7 ,4 ,15, 14, 3 ,11 ,5, 2, 12}};
	
	static int[][] S4= { {7 ,13, 14, 3 ,0 ,6 ,9 ,10, 1 ,2 ,8, 5 ,11 ,12 ,4 ,15 },
            { 13 ,8 ,11 ,5 ,6 ,15 ,0 ,3 ,4 ,7, 2, 12, 1 ,10 ,14, 9 },
            {10, 6 ,9, 0 ,12, 11, 7 ,13 ,15 ,1 ,3, 14, 5 ,2 ,8 ,4 },
            {3 ,15, 0 ,6 ,10, 1, 13, 8, 9, 4 ,5 ,11 ,12 ,7 ,2 ,14}};
	
	static int[][] S5= { {2 ,12 ,4 ,1 ,7 ,10, 11, 6 ,8 ,5 ,3, 15 ,13, 0, 14, 9 },
            {14 ,11 ,2, 12 ,4 ,7, 13 ,1 ,5 ,0 ,15 ,10 ,3 ,9 ,8 ,6},
            {4, 2, 1, 11, 10, 13, 7 ,8 ,15, 9 ,12 ,5 ,6 ,3, 0, 14 },
            {11 ,8 ,12 ,7, 1 ,14 ,2 ,13, 6, 15, 0 ,9 ,10, 4, 5 ,3}};
	
	static int[][] S6= { {12, 1, 10, 15, 9, 2 ,6 ,8, 0 ,13,3, 4, 14, 7 ,5 ,11 },
            {10 ,15 ,4 ,2 ,7 ,12 ,9 ,5 ,6, 1, 13, 14, 0, 11, 3, 8},
            { 9 ,14 ,15, 5 ,2 ,8 ,12, 3, 7, 0, 4 ,10 ,1 ,13 ,11, 6},
            {4, 3 ,2, 12, 9 ,5 ,15 ,10 ,11, 14, 1, 7, 6 ,0, 8, 13}};
	
	static int[][] S7= { {4 ,11 ,2 ,14 ,15 ,0 ,8 ,13 ,3 ,12, 9, 7, 5 ,10 ,6, 1 },
            {13 ,0 ,11 ,7 ,4, 9, 1 ,10, 14, 3 ,5 ,12 ,2 ,15 ,8 ,6},
            { 1, 4 ,11 ,13 ,12, 3, 7 ,14 ,10 ,15 ,6, 8 ,0 ,5 ,9 ,2 },
            {6 ,11, 13, 8 ,1 ,4 ,10, 7, 9 ,5 ,0 ,15 ,14, 2, 3 ,12}};
	
	static int[][] S8= { {13, 2 ,8 ,4 ,6 ,15 ,11 ,1 ,10, 9, 3 ,14 ,5 ,0 ,12 ,7},
            { 1 ,15, 13, 8, 10 ,3 ,7, 4 ,12, 5 ,6, 11 ,0, 14, 9 ,2},
            { 7, 11 ,4, 1 ,9 ,12 ,14 ,2, 0 ,6 ,10 ,13, 15 ,3 ,5 ,8 },
            {2, 1 ,14, 7 ,4 ,10 ,8 ,13 ,15 ,12 ,9 ,0 ,3 ,5 ,6, 11}};
	
	static int[] P= {16, 7, 20, 21,
			29 ,12 ,28 ,17 ,
			1 ,15 ,23 ,26 ,
			5 ,18 ,31 ,10 ,
			2 ,8 ,24, 14 ,
			32 ,27 ,3, 9 ,
			19 ,13 ,30 ,6 ,
			22 ,11 ,4 ,25};
	static int[] inverseIP = {40, 8 ,48, 16, 56, 24, 64, 32,
			39, 7 ,47 ,15 ,55, 23 ,63 ,31,
			38 ,6 ,46 ,14 ,54 ,22 ,62 ,30,
			37 ,5 ,45 ,13 ,53 ,21, 61, 29,
			36 ,4 ,44 ,12 ,52 ,20 ,60 ,28,
			35 ,3 ,43, 11 ,51 ,19 ,59 ,27,
			34 ,2 ,42, 10 ,50 ,18 ,58 ,26,
			33, 1 ,41 ,9 ,49, 17, 57 ,25};
	static int[] leftShifts = {1,1,2,2,2,2,2,2,1,2,2,2,2,2,2,1};
	static ArrayList<String> Kp = new ArrayList<String>();
	static ArrayList<ArrayList<String>> C =   new ArrayList<ArrayList<String>>();
	static ArrayList<ArrayList<String>> D =   new ArrayList<ArrayList<String>>();
	static ArrayList<String> concatenatedKeys= new ArrayList<String>();
	static ArrayList<String> keys= new ArrayList<String>();
	static String x;
	static String y;
	static ArrayList<String> Mp = new ArrayList<String>();
	static ArrayList<ArrayList<String>> L =   new ArrayList<ArrayList<String>>();
	static ArrayList<ArrayList<String>> R =   new ArrayList<ArrayList<String>>();
	static ArrayList<String> inverseConcatenation= new ArrayList<String>();
	static ArrayList<String> finalBinaryResult= new ArrayList<String>();
	
	public static String hexToBinary(String x) {
		x=x.toUpperCase();
	//	System.out.println(x);
		ArrayList<String> Binary = new  ArrayList<String>();
		String zero="0000";
		String one="0001";
		String two="0010";
		String three="0011";
		String four="0100";
		String five="0101";
		String six="0110";
		String seven="0111";
		String eight="1000";
		String nine="1001";
		String A="1010";
		String B="1011";
		String C="1100";
		String D="1101";
		String E="1110";
		String F="1111";		
		for(int i=0; i<x.length(); i++) {
			switch(x.charAt(i)) {
			case '0':
				Binary.add(zero);
				break;
			case '1':
				Binary.add(one);
				break;
			case '2':
				Binary.add(two);
				break;
			case '3':
				Binary.add(three);
				break;
			case '4':
				Binary.add(four);
				break;
			case '5':
				Binary.add(five);
				break;
			case '6':
				Binary.add(six);
				break;
			case '7':
				Binary.add(seven);
				break;
			case '8':
				Binary.add(eight);
				break;
			case '9':
				Binary.add(nine);
				break;
			case 'A':
				Binary.add(A);
				break;
			case 'B':
				Binary.add(B);
				break;
			case 'C':
				Binary.add(C);
				break;
			case 'D':
				Binary.add(D);
				break;
			case 'E':
				Binary.add(E);
				break;
			case 'F':
				Binary.add(F);
				break;
				
			}
			
		}
	for(int i=0; i<Binary.size(); i++) {
		System.out.print(Binary.get(i));
		System.out.print(" ");			
	}
	
	StringBuffer sb = new StringBuffer(); 
    for (String s : Binary) {
       sb.append(s);
    }
    System.out.println();
    String str = sb.toString();
  //  System.out.println("String " + str);
	
		return str;
		
	}
	
	public static void permutation1(String binary) {
		int index;
		String toPlace;
		for(int i=0; i<PC1.length; i++) {
			index= PC1[i]; //57
			index=index-1;
			toPlace =Character.toString(binary.charAt(index));
			Kp.add(toPlace);
			
		}
		System.out.print("K+ = ");
		for(int i=0; i<Kp.size(); i++) {
			System.out.print(Kp.get(i)+" " );
		}
		System.out.println();
		
	}
	
	public static void splitToHalf1() {
		List<String> Left1= new ArrayList<String>();
		List<String> Right1 = new ArrayList<String>();
		int length = Kp.size();
		Left1=  Kp.subList(0, length/2);
		Right1 =  Kp.subList(length/2, length);
		StringBuilder strbul1=new StringBuilder();
		StringBuilder strbul2=new StringBuilder();
	    for(String str1 : Left1){
	        strbul1.append(str1);       
	    }
	    for(String str2 : Right1){
	        strbul2.append(str2);       
	    }
	  
	     String str1=strbul1.toString();
	     String str2=strbul2.toString();
	     ArrayList<String> Left2 = new ArrayList<>(Arrays.asList(str1.split("")));      
	     ArrayList<String> Right2 = new ArrayList<>(Arrays.asList(str2.split("")));      
		 C.add(Left2);
		 D.add(Right2);
			/*
			 * System.out.println("Left ="+L); System.out.println("Right ="+R);
			 */		
	
	}
	

	public static void shiftLeftD() {
		 ArrayList<String> R0=D.get(0);
		 ArrayList<String> tempR= new ArrayList<String>();
	//	 System.out.println("R0 = " +R0);
		 StringBuffer sb = new StringBuffer();	      
	     for (String s : R0) {
	           sb.append(s);
	      }
	     String string0 = sb.toString();
	     tempR.add(string0);
	     char firstIndex0 =string0.charAt(0);
	     StringBuilder str0 = new StringBuilder(string0);
         StringBuilder afterRemoval0 = str0.deleteCharAt(0);
         StringBuilder finalString0= afterRemoval0.append(firstIndex0);
         String finalStr0= finalString0.toString();
         y=finalStr0;
         tempR.add(y);
         int noShifts;
         for(int i=1; i<16; i++) {
   		 noShifts=leftShifts[i];
   		 if(noShifts==1) {	
   		     char firstIndex1 =y.charAt(0);
   		     StringBuilder str1 = new StringBuilder(y);
   	         StringBuilder afterRemoval1 = str1.deleteCharAt(0);
   	         StringBuilder finalString1= afterRemoval1.append(firstIndex1);
   	         String finalStr1= finalString1.toString();
   	         y=finalStr1;
   	         tempR.add(y);
   		   }
   		 else {
   		
   	         char firstIndex2=y.charAt(0);
   	         char firstIndex3=y.charAt(1);
   	         StringBuilder str2 = new StringBuilder(y);
   	         StringBuilder afterRemoval2 = str2.deleteCharAt(0);
   	         afterRemoval2=afterRemoval2.deleteCharAt(0);
   	         StringBuilder finalString2= afterRemoval2.append(firstIndex2);
   	         finalString2.append(firstIndex3);
   	         String finalStr2= finalString2.toString();
   	         y=finalStr2;
   	         tempR.add(y);	         
   	         
   		 }
   		

         }
			/*
			 * for(int i=0; i<tempL.size(); i++) { System.out.println(tempL.get(i)); }
			 */
        D.clear();
        D.add(tempR);
		/*
		 * System.out.println("Rights"); for(int i=0; i<R.size(); i++) {
		 * System.out.println(R.get(i)); System.out.println(); }
		 */
	}
	
	
	public static void shiftLeftC() {
		  ArrayList<String> L0=C.get(0);
		  ArrayList<String> tempL= new ArrayList<String>();
	//	  System.out.println("L0 = " +L0);
		  StringBuffer sb = new StringBuffer();	      
	      for (String s : L0) {
	           sb.append(s);
	      }
	      String string0 = sb.toString();
	      tempL.add(string0);
	      char firstIndex0 =string0.charAt(0);
	      StringBuilder str0 = new StringBuilder(string0);
          StringBuilder afterRemoval0 = str0.deleteCharAt(0);
          StringBuilder finalString0= afterRemoval0.append(firstIndex0);
          String finalStr0= finalString0.toString();
          x=finalStr0;
          tempL.add(x);
          int noShifts;
          for(int i=1; i<16; i++) {
    		 noShifts=leftShifts[i];
    		 if(noShifts==1) {	
    		     char firstIndex1 =x.charAt(0);
    		     StringBuilder str1 = new StringBuilder(x);
    	         StringBuilder afterRemoval1 = str1.deleteCharAt(0);
    	         StringBuilder finalString1= afterRemoval1.append(firstIndex1);
    	         String finalStr1= finalString1.toString();
    	         x=finalStr1;
    	         tempL.add(x);
    		   }
    		 else {
    		
    	         char firstIndex2=x.charAt(0);
    	         char firstIndex3=x.charAt(1);
    	         StringBuilder str2 = new StringBuilder(x);
    	         StringBuilder afterRemoval2 = str2.deleteCharAt(0);
    	         afterRemoval2=afterRemoval2.deleteCharAt(0);
    	         StringBuilder finalString2= afterRemoval2.append(firstIndex2);
    	         finalString2.append(firstIndex3);
    	         String finalStr2= finalString2.toString();
    	         x=finalStr2;
    	         tempL.add(x);	         
    	         
    		 }
    		

          }
			
         C.clear();
         C.add(tempL);
			/*
			 * System.out.println("Lefts"); for(int i=0; i<L.size(); i++) {
			 * System.out.println(L.get(i)); }
			 */
	}
	
	
	public static void concatenate1() {
		String Final = "";
		for(int i=0; i<17; i++) {
			String indexL=C.get(0).get(i);
			String indexR=D.get(0).get(i);
		    Final= indexL+indexR;
			concatenatedKeys.add(Final);		
		}
		concatenatedKeys.remove(0);
	//	for(int i=0; i<concatenatedKeys.size(); i++) {
		//System.out.println(concatenatedKeys.get(i));
	//	}
	}
	
	public static void permutation2() {
		int index;
		for(int i=0; i<concatenatedKeys.size(); i++) {
			String iterator=concatenatedKeys.get(i);
			String toPlace = "";
		for(int j=0; j<PC2.length; j++) {
			index= PC2[j]; //14
			index=index-1;
			toPlace +=Character.toString(iterator.charAt(index));			
		}
		keys.add(toPlace);
		}
		System.out.println();
		System.out.println("Final Keys : ");
		for(int i=0; i<keys.size(); i++) {
			System.out.println("K"+ (i+1) + " = " +keys.get(i));
	
		}
		System.out.println();
		
	}
	
	public static void initialPermutation(String string) {
		int index;
		String toPlace;
		for(int i=0; i<IP.length; i++) {
			index= IP[i]; //57
			index=index-1;
			toPlace =Character.toString(string.charAt(index));
			Mp.add(toPlace);
			
		}
		/*
		 * System.out.print("Message after Initial Permutation = "); for(int i=0;
		 * i<Mp.size(); i++) { System.out.print(Mp.get(i) ); }
		 */
		System.out.println();
	}
	
	public static void splitToHalf2() {
		List<String> Left1= new ArrayList<String>();
		List<String> Right1 = new ArrayList<String>();
		int length = Mp.size();
		Left1=  Mp.subList(0, length/2);
		Right1 =  Mp.subList(length/2, length);
		StringBuilder strbul1=new StringBuilder();
		StringBuilder strbul2=new StringBuilder();
	    for(String str1 : Left1){
	        strbul1.append(str1);       
	    }
	    for(String str2 : Right1){
	        strbul2.append(str2);       
	    }
	  
	     String str1=strbul1.toString();
	     String str2=strbul2.toString();
	     ArrayList<String> Left2 = new ArrayList<>(Arrays.asList(str1.split("")));      
	     ArrayList<String> Right2 = new ArrayList<>(Arrays.asList(str2.split("")));      
		 L.add(Left2);
		 R.add(Right2);
			
			 // System.out.println("Left ="+L); 
			//  System.out.println("Right ="+R);
		}
	
	public static void functionF() {
		ArrayList<String> L0= L.get(0);
		ArrayList<String> R0= R.get(0);		
		ArrayList<ArrayList<String>> tempL = new ArrayList<ArrayList<String>>();
		ArrayList<ArrayList<String>> tempR = new ArrayList<ArrayList<String>>();
		
		tempL.add(R0);//L1=R0
		ArrayList<String> expandedR0=Expand(R0); //expand R0 to XOR with KEY //CHANGE
		ArrayList<String> afterXOR1=XOR(expandedR0, keys.get(0));
		ArrayList<String> afterSbox=Sbox(afterXOR1);
		ArrayList<String> permuted=P(afterSbox);
		StringBuilder sb = new StringBuilder();
		for (String s : permuted){
		    sb.append(s);
		}
		String stringPermuted=sb.toString();
		ArrayList<String> afterXOR2= XOR(L0,stringPermuted); //CHANGE
		tempR.add(afterXOR2);
		//System.out.println("dd"+afterXOR2);
		for(int i=0; i<15; i++) {	
			tempL.add(afterXOR2);//L2=R1
			R0=afterXOR2;
			expandedR0=Expand(R0); //expand R0 to XOR with KEY //CHANGE
			afterXOR1=XOR(expandedR0, keys.get(i+1));
			afterSbox=Sbox(afterXOR1);
		    permuted=P(afterSbox);
			sb = new StringBuilder();
			for (String s : permuted){
			    sb.append(s);
			}
			stringPermuted=sb.toString();
	        afterXOR2= XOR(tempL.get(i),stringPermuted); //CHANGE
	        tempR.add(afterXOR2);
			
		}
		
		/*
		 * for(int i=0; i<tempR.size(); i++) { System.out.println("R"
		 * +(i+1)+tempR.get(i)); } System.out.println(); for(int i=0; i<tempL.size();
		 * i++) { System.out.println("L" +(i+1)+tempL.get(i)); }
		 */
		concatenate2(tempL,tempR);
	}
	
	public static void concatenate2(ArrayList<ArrayList<String>> tempL, ArrayList<ArrayList<String>> tempR) {
	     	ArrayList<String> indexL=tempL.get(tempL.size()-1);
			ArrayList<String> indexR=tempR.get(tempR.size()-1);
			indexR.addAll(indexL);
			inverseConcatenation.addAll(indexR);		

	//	System.out.println("inverse " +inverseConcatenation);
		
	}

	public static ArrayList<String> P(ArrayList<String> afterSbox) {
		ArrayList<String> p= new ArrayList<String>();
		StringBuilder sb = new StringBuilder();
		for (String s : afterSbox)
		{
		    sb.append(s);
		  
		}

		String x=sb.toString();
		int index;
		String toPlace;
		for(int i=0; i<P.length; i++) {
			index= P[i]; //57
			index=index-1;
			toPlace =Character.toString(x.charAt(index));
			p.add(toPlace);
			
		}
		/*
		 * System.out.print("p = "); for(int i=0; i<p.size(); i++) {
		 * System.out.print(p.get(i)+" " ); }
		 */
		return p;
		
	}

	public static ArrayList<String> Sbox(ArrayList<String> afterXOR) {
		
		ArrayList<String> result = new ArrayList<String>();
		List<List<String>> partitions = IntStream.range(0, afterXOR.size())
			    .filter(i -> i % 6 == 0)
			    .mapToObj(i -> afterXOR.subList(i, Math.min(i + 6, afterXOR.size() )))
			    .collect(Collectors.toList()); 
		
		/*
		 * for(int i=0; i<partitions.size(); i++) { for(int j=0;
		 * j<partitions.get(i).size(); j++) { System.out.print(partitions.get(i).get(j)
		 * + " "); } System.out.println(); }
		 */
		for(int i=0; i<partitions.size(); i++) {           
		        String x=partitions.get(i).get(0) ;    
		        String y= partitions.get(i).get(partitions.get(i).size()-1);
		        String z=x+y;
		        int decimal1=Integer.parseInt(z,2);        
		        String index2=partitions.get(i).get(1); 
		        String index3=partitions.get(i).get(2); 
		        String index4=partitions.get(i).get(3); 
		        String index5=partitions.get(i).get(4); 
	            String w=index2+index3+index4+index5; 
	            int decimal2=Integer.parseInt(w,2);             
	            if(i==0) {
	            	int inSBOX=S1[decimal1][decimal2];     	
	            	String binary;
	            	binary=String.format("%4s", Integer.toBinaryString(inSBOX)).replace(' ', '0');
	            	result.add(binary);
	            }
	            else if(i==1) {	      
	            	int inSBOX=S2[decimal1][decimal2];     	
	            	String binary;
	            	binary=String.format("%4s", Integer.toBinaryString(inSBOX)).replace(' ', '0');
	            	result.add(binary);	 	            
	            }
	            else if(i==2){
	            	int inSBOX=S3[decimal1][decimal2];     	
	            	String binary;
	            	binary=String.format("%4s", Integer.toBinaryString(inSBOX)).replace(' ', '0');
	            	result.add(binary);
	            	
	            } else if(i==3){
	            	int inSBOX=S4[decimal1][decimal2];     	
	            	String binary;
	            	binary=String.format("%4s", Integer.toBinaryString(inSBOX)).replace(' ', '0');
	            	result.add(binary);         	
	            }else if(i==4){
	            	int inSBOX=S5[decimal1][decimal2];     	
	            	String binary;
	            	binary=String.format("%4s", Integer.toBinaryString(inSBOX)).replace(' ', '0');
	            	result.add(binary);
	            }
	            else if(i==5){
	            	int inSBOX=S6[decimal1][decimal2];     	
	            	String binary;
	            	binary=String.format("%4s", Integer.toBinaryString(inSBOX)).replace(' ', '0');
	            	result.add(binary);
	            	
	            }else if(i==6){
	            	int inSBOX=S7[decimal1][decimal2];     	
	            	String binary;
	            	binary=String.format("%4s", Integer.toBinaryString(inSBOX)).replace(' ', '0');
	            	result.add(binary);
	            	
	            }else{
	            	int inSBOX=S8[decimal1][decimal2];     	
	            	String binary;
	            	binary=String.format("%4s", Integer.toBinaryString(inSBOX)).replace(' ', '0');
	            	result.add(binary);
	            	
	            }
		       
		} 
	//	System.out.println(result);
		return result;
	}

	public static ArrayList<String> XOR(ArrayList<String> list, String string) {
	   ArrayList<String> key = new ArrayList<>(Arrays.asList(string.split("")));
	   ArrayList<String> result = new ArrayList<String>();
	//   System.out.println(key);
	//   System.out.println(expandedR0);
	   for(int i=0; i<key.size(); i++) {
		   if(key.get(i).equals(list.get(i))) {
			   result.add("0");
		   }else {
			   result.add("1"); 
		   }
	   }
		/*
		 * System.out.println(); System.out.println("XOR ="+result);
		 */
		return result;
	}

	public static ArrayList<String> Expand(ArrayList<String> r0) {
		int index;
		ArrayList<String> result = new ArrayList<String>();
		String toPlace = "";
		for(int j=0; j<EXPAND_BITS.length; j++) {
			index= EXPAND_BITS[j]; //14
			index=index-1;
			toPlace =r0.get(index);				
	 	    result.add(toPlace);
		}
		/*
		 * System.out.println(); System.out.println("After expansion : "); for(int i=0;
		 * i<result.size(); i++) { System.out.print(result.get(i));
		 * 
		 * }
		 */
	//	System.out.println();
		return result;
	}

	public static void finalPermutaion() {
		//ArrayList<String> p= new ArrayList<String>();
		StringBuilder sb = new StringBuilder();
		for (String s : inverseConcatenation)
		{
		    sb.append(s);
		  
		}

		String x=sb.toString();
		int index;
		String toPlace;
		for(int i=0; i<inverseIP.length; i++) {
			index= inverseIP[i]; //57
			index=index-1;
			toPlace =Character.toString(x.charAt(index));
			finalBinaryResult.add(toPlace);
			
		}
	//	System.out.println("IP inverse = "+finalBinaryResult);
	}
	
	public static void BinaryToHex() {	
		StringBuilder sb = new StringBuilder();
		for (String s : finalBinaryResult){
		    sb.append(s);	  
		}
		String x=sb.toString();
		String hexString = new BigInteger(x, 2).toString(16);
		hexString=hexString.toUpperCase();
		System.out.println("Encrypted Hexadecimal Result = "+hexString);
		
	}
	
	public static void main(String[] args)  {
	//	String M = "0123456789ABCDEF";
	//	String K = "133457799BBCDFF1";	
		String M;
		String K;
		Scanner s= new Scanner(System.in);
		System.out.println("Enter the Message :");
		M=s.nextLine();
	    System.out.println("Enter the Key :");
		K=s.nextLine();
		String BinaryMessage;
		String BinaryKey;
		System.out.print("Hexadecimal Key to Binary : ");
		BinaryKey = hexToBinary(K);
        permutation1(BinaryKey);
        splitToHalf1();
        shiftLeftC();
        shiftLeftD();
        concatenate1();
        permutation2();
    	System.out.print("Hexadecimal Message to Binary : ");
        BinaryMessage=hexToBinary(M);
        initialPermutation(BinaryMessage);
        splitToHalf2();
        functionF();
        finalPermutaion();
        BinaryToHex();
        
		
	}

}
