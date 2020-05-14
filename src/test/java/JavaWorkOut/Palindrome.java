package JavaWorkOut;

public class Palindrome {
	
	public static void main(String[] args) {
		String s = "testleaf";
		boolean palindrome = false;
		char[] ch = s.toCharArray();
		for (int i = 0; i < ch.length; i++)
		{
			if(ch[i] == ch[ch.length-(i+1)])
			{
				palindrome = true;
			}
			else
			{
				palindrome = false;
			}
		}
		if(palindrome == true)
		{
			System.out.println("The given string "+s+" is a palindrome");
		}
		else
		{
			System.out.println("The given string "+s+" is not a palindrome");

		}
	}
	}


