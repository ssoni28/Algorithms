import java.util.ArrayList;

public class sam {
	public static void main(String[]args)
	{
		String str= "aabaa";
		ArrayList<String> list = new ArrayList<String>();
	    
	    for(int i=0; i<= str.length(); i++)
	        {
	    	for(int j=0; j<=str.length()-i; j++)
	    	{
	        list.add(str.substring(i,i+j));
	    	}
	        }
	  for(int k=1; k<=list.size(); k++)
	  {
		  System.out.println(list.get(k));
	  }	        
	}
}
