
public class Node {
	private int value;
	private String key;
	private Node next;
	private PositionNode nextPosition;
	public int getValue() {
		return value;
	}
	public void setValue(int value) {
		this.value = value;
	}
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public Node getNext() {
		return next;
	}
	public void setNext(Node next) {
		this.next = next;
	}
	public PositionNode getNextPosition() {
		return nextPosition;
	}
	public void setNextPosition(PositionNode nextPosition) {
		this.nextPosition = nextPosition;
	}
	public PositionNode getLastPositionNode(Node node)
	{
		PositionNode pHead = node.getNextPosition();
		while(pHead!= null)
		{
			if(pHead.getNext() == null)
			{
				return pHead;
			}
			pHead = pHead.getNext();
		}
		return null;
	}
}
