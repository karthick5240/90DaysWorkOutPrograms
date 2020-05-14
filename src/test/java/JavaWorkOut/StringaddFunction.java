package JavaWorkOut;

public class StringaddFunction {
	
	public static void main(String[] args) {
		
		String input = "asdf1qwer9as8d7";
		int rem = 0;
		int inputNo = Integer.parseInt(input.replaceAll("[^0-9]", ""));
		while(inputNo>0)
		{
			rem = rem+inputNo%10;
			inputNo = inputNo/10;
			
		}
		
		System.out.println(rem);
	}

	}


