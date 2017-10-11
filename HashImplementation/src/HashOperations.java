
import java.util.ArrayList;

public class HashOperations {

	public static final int M = 701;

	private LinkedList[] table = new LinkedList[M];

	
	int sascii(String x) {
		char ch[];
		ch = x.toCharArray();
		int i, sum;
		for (sum=0, i=0; i < x.length(); i++)
			sum += ch[i];
		return sum % M;
	}

	public void insert(String key, int value)
	{
		int hashIndex = sascii(key) % M;
		if(table[hashIndex] == null)
		{
			table[hashIndex] = new LinkedList();
		}
		table[hashIndex].insert(key, value);
	}

	public void insertPosition(String key, int position)
	{
		int hashIndex = sascii(key) % M;
		Node node = table[hashIndex].find(key);
		if(node != null)
		{
			if(node.getNextPosition() == null)
			{
				PositionNode posNode = new PositionNode();
				posNode.setValue(position);
				node.setNextPosition(posNode);
				posNode.setNext(null);	
			}
			else
			{
				PositionNode lastNode = node.getLastPositionNode(node);
				PositionNode posNode = new PositionNode();
				posNode.setValue(position);
				lastNode.setNext(posNode);
				posNode.setNext(null);
			}
		}
		
		/*if(table[hashIndex].getLastNode().getNextPosition() == null)
		{		
			//LinkedList newPositionLinkedList = new LinkedList();
			PositionNode newPositionNode = new PositionNode();
			newPositionNode.setValue(position);
			table[hashIndex].getLastNode().setNextPosition(newPositionNode);
			table[hashIndex].getLastNode().getNextPosition().setNext(null);
		}
		else
		{
			
		}
		table[hashIndex].insertPosition(key, position);*/
	}
	
	
	public int find(String key)
	{
		int hashIndex = sascii(key) % M;
		if(table[hashIndex] != null && table[hashIndex].find(key) != null)
		{
			return table[hashIndex].find(key).getValue();
		}
		else
		{
			return 0;
		}
	}

	public void increase(String key, int value)
	{
		int hashIndex = sascii(key) % M;
		if(table[hashIndex] == null)
		{
			insert(key, 1);
		}
		else
		{
			table[hashIndex].increase(key, 1);
		}
	}
	
	/*public void increasePosition(String key, int position)
	{
		int hashIndex = sascii(key) % M;
		table[hashIndex].insertPosition(key, position);
	
	}*/

	public void increase(String key)
	{
		increase(key, 1);
	}

	public void delete(String key)
	{
		int hashIndex = sascii(key) % M;
		if(table[hashIndex] == null || table[hashIndex].find(key) == null)
		{
			System.out.println("No such key exists in the hash table");
		}
		else
		{
			table[hashIndex].delete(key);
		}
	}

	public ArrayList<String> listAllKeys()
	{	
		ArrayList<String> keys = new ArrayList<String>();

		for(int i =0; i< table.length; i++)
		{
			if(table[i] != null)
			{
				keys.addAll(table[i].getKeys());
				System.out.println("Size  "+table[i].size());
			}
		}
		return keys;
	}

	/*while(table[i]!=null)
	{
		Node node = table[i].getHead();
		if(node.getNextPosition()!=null)
		{
			positions.add(node.getValue());

		}
		node = node.getNext();
	}
	 */
	public ArrayList<Integer> listAllPositions(String key)
	{
		ArrayList<Integer> positions = new ArrayList<Integer>();
		int hashIndex = sascii(key) % M;
		Node node = table[hashIndex].find(key);
		PositionNode pNode = node.getNextPosition();
		if(pNode != null)
		{
			while(pNode!=null)
			{
				positions.add(pNode.getValue());
				pNode = pNode.getNext();
			}
		}
		return positions;
	}

	public void printKeyValPair()
	{		
		for(int i =0; i< table.length; i++)
		{
			if(table[i] != null)
			{
				table[i].printKeyValPair();
			}
		}
	}
}

