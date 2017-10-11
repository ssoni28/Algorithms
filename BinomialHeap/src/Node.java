
public class Node {
	private Node parent;
	private int key;
	private int degree;
	private Node child;
	private Node sibling;
	public Node getParent() {
		return parent;
	}
	public void setParent(Node parent) {
		this.parent = parent;
	}
	public int getKey() {
		return key;
	}
	public void setKey(int key) {
		this.key = key;
	}
	public int getDegree() {
		return degree;
	}
	public void setDegree(int degree) {
		this.degree = degree;
	}
	public Node getChild() {
		return child;
	}
	public void setChild(Node child) {
		this.child = child;
	}
	public Node getSibling() {
		return sibling;
	}
	public void setSibling(Node sibling) {
		this.sibling = sibling;
	}
	
	Node(int key)
	
	{
		this.parent = null;
		this.key = key;
		this.degree = 0;
		this.child = null;
		this.sibling = null;
	}
	
	 public Node findNode(int value) 
	    {
	            Node temp = this;
	            Node node = null;
	 
	            while (temp != null) 
	            {
	                if (temp.getKey() == value) 
	                {
	                    node = temp;
	                    break;
	                }
	                if (temp.getChild() == null)
	                    temp = temp.getSibling();
	                else 
	                {
	                    node = temp.getChild().findNode(value);
	                    if (node == null)
	                        temp = temp.getSibling();
	                    else
	                        break;
	                }
	            }
	 
	            return node;
	    }

}
