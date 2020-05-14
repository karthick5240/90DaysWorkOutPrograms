package JavaWorkOut;


public class OccurenceChar {

	public static void main(String[] args) {
		
		String in = "You have no choice other than following me!";
		int count = 0;
		char[] inarr = in.toCharArray();
		for (int i = 0; i < inarr.length; i++) 
		{
			if(inarr[i] == 'o')
			{
				count++;
			}
		}
		System.out.println("Occourance of o is: "+count);
		
	}


}
	
