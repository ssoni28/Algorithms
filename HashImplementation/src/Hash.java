import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

public class Hash {
	
		public static void main(String [] args)
		{
			
			String fileName = "//Users//swativish//Documents//Alice_in_wonderland.txt";
			String word = null;
			String[] words = null;
			HashOperations hashOp = new HashOperations();
			int position =1;
			try
			{
			FileReader file = new FileReader(fileName);
			BufferedReader bReader = new BufferedReader(file);
			while((word = bReader.readLine()) != null)
			{
				word = word.replaceAll("[^A-Za-z]", " ");
				words = word.split(" ");
				for(int k=0;k<words.length;k++)
				{
					if(words[k] !=null && !words[k].isEmpty())
					{
						hashOp.increase(words[k]);
						hashOp.insertPosition(words[k],position);
						position++;
					}
						
				}
				
				System.out.println(word);
				
			}
			bReader.close();
			}
			catch(FileNotFoundException f)
			{
				f.printStackTrace();
			}
			catch(IOException e)
			{
				e.printStackTrace();
			}
			System.out.println(position);
			
			hashOp.printKeyValPair();
			PrintWriter pw = null;
			try 
			{
				pw = new PrintWriter("output.txt");
				int count = 0;
				pw.println("Words            "+"          Count"+"                     Positions");
				for(String k: hashOp.listAllKeys())
				{
					pw.print("    "+k + "                  " + hashOp.find(k));
					for(Integer p: hashOp.listAllPositions(k))
					{
						pw.print("                "+p+"   ");
					}
					pw.println("  ");
					count++;
				}
				System.out.println("TotalKeys = "+count);
			} 
			catch (FileNotFoundException e) 
			{
				System.out.println("File not found");
				e.printStackTrace();
			}
			finally
			{
				pw.close();
			}
		}
	}
