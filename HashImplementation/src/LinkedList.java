import java.util.ArrayList;

public class LinkedList
{
	private Node head;

	public Node getHead() 
	{
		return head;
	}

	public void setHead(Node head) 
	{
		this.head = head;
	}
	
	private PositionNode pHead;

	public PositionNode getpHead() {
		return pHead;
	}

	public void setpHead(PositionNode pHead) {
		this.pHead = pHead;
	}

	//Find
	public Node find(String key)
	{
		Node node = getHead();
		while(node!=null)
		{
			if(node.getKey().equals(key))
			{
				return node;
			}
			node=node.getNext();
		}
		return null;
	}
	
	
	// Insert
	public Node insert(String key, int value)
	{
		Node node = find(key);
		if(node != null)
		{
			node.setValue(value);
		}
		else
		{
			Node newNode = new Node();
			newNode.setKey(key);
			newNode.setValue(value);
			//newNode.setNextPosition(null);
			if(getHead() == null)
			{
				setHead(newNode);
			}
			else
			{
				getLastNode().setNext(newNode);
			}
		}
		return head;
	}
	
	/*public PositionNode insertPosition(String key, int position)
	{
		Node node = find(key);

		PositionNode pNode = new PositionNode();
		pNode.setValue(position);
		if(getpHead() == null)
		{
			setpHead(pNode);
			node.setNextPosition(pHead);
		}
		else
		{
			getLastPositionNode().setNext(pNode);
		}
		return pHead;
		
		Node node = find(key);
		PositionNode newPositionNode = new PositionNode();
		newPositionNode.setValue(position);
		
		
	}*/
	
	// Increase
	public Node increase(String key, int value)
	{
		Node node = find(key);
		if(node != null)
		{
			node.setValue(node.getValue() + value);
		}
		else
		{
			Node newNode = new Node();
			newNode.setKey(key);
			newNode.setValue(value);
			//newNode.setNextPosition(null);
			if(getHead() == null)
			{
				setHead(newNode);
			}
			else
			{
				getLastNode().setNext(newNode);
			}
		}
		return head;
	}
	
	/*public Node increasePosition(String key, int position)
	{
		Node node = find(key);
		if(node != null)
		{
			node.setValue(node.getValue() + value);
		}
		else
		{
			Node newNode = new Node();
			newNode.setKey(key);
			newNode.setValue(value);
			//newNode.setNextPosition(null);
			if(getHead() == null)
			{
				setHead(newNode);
			}
			else
			{
				getLastNode().setNext(newNode);
			}
		}
		return head;
	}*/
	// Delete
	public void delete(String key)
	{
		Node node = getHead();
		Node prevNode = null;
		while(node!= null)
		{
			if(node.getKey().equals(key))
			{
				break;
			}
			prevNode = node;
			node = node.getNext();
		}
		if(node != null)
		{
			if(prevNode == null)
			{
				setHead(node.getNext());
			}
			else
			{
				prevNode.setNext(node.getNext());
			}
		}
	}
	
	// Last Node
	public Node getLastNode()
	{
		Node node = getHead();
		while(node!= null)
		{
			if(node.getNext() == null)
			{
				return node;
			}
			node = node.getNext();
		}
		return null;
	}
	
	public PositionNode getLastPositionNode()
	{
		PositionNode node = getpHead();
		while(node!= null)
		{
			if(node.getNext() == null)
			{
				return node;
			}
			node = node.getNext();
		}
		return null;
	}
	
	
	public int size()
	{
		int size = 0;
		Node node = getHead();
		while(node!=null)
		{
			node = node.getNext();
			size++;
		}
		return size;
	}
	
	public void printKeyValPair()
	{
		Node node = getHead();
		while(node!=null)
		{
			System.out.print("(key= " + node.getKey() + ", value= " + node.getValue()+") ");
			
			if(node.getNextPosition()!=null)
			{
				PositionNode pNode = node.getNextPosition();
				while(pNode!=null)
				{
					System.out.println("");
					System.out.print("Position= "+pNode.getValue()+" key = " + node.getKey());
					pNode = pNode.getNext();
				}
			}
			node = node.getNext();
			System.out.println();
		}
		
	}
	
	public ArrayList<String> getKeys()
	{
		ArrayList<String> keys = new ArrayList<String>();
		Node node = getHead();
		while(node!=null)
		{
			keys.add(node.getKey());
			node = node.getNext();
		}
		return keys;
	}
	
	
	
}