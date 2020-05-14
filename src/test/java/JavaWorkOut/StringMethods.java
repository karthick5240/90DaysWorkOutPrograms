package JavaWorkOut;

public class StringMethods {

	public static void main(String[] args) {

		
		
       String input = " It is Work from Home not Work for Home";
       int uppercase =0, lowercase=0,number=0, space =0;
        char[] charArray = input.toCharArray();
      for (int i = 0; i < input.length(); i++) {
    	  if(charArray[i] == ' ')
			{
				space = space+1;
			}
			else if(Character.isUpperCase(charArray[i]))
			{
				uppercase = uppercase+1;
			}
			else if(Character.isLowerCase(charArray[i]))
			{
				lowercase=lowercase+1;
			}
			else if(Character.isDigit(charArray[i]))
			{
				number = number+1;
			}
		}
		
	System.out.println("UpperCase : "+uppercase);
	System.out.println("LowerCase : "+lowercase);
	System.out.println("Number : "+number);
	System.out.println("space : "+space);
	
	int totamt = uppercase+lowercase+number+space;
	
	System.out.println("Total number of letters:" +totamt);


}
}

