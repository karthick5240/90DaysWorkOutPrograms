package JavaWorkOut;

public class StringReverse {

	public static void main(String[] args) {
String str = "When the world realise its own mistakes, corona will dissolve automatically" ;
		

	    String[] substr = str.split("\\s+");
	    
	   
	 for (int i=0; i<substr.length; i++)
	 {
		 StringBuilder str1=new StringBuilder(substr[i]);
		 		 
		 if(i%2!=0) {
			
			 str1=str1.reverse();
		 }
		 System.out.print(" "+str1);
			 
	 }
	    
	 
		
	}

	}


