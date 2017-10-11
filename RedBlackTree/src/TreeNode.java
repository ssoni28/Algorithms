
public class TreeNode {
	public TreeNode left;
	public TreeNode right;
	public TreeNode parent;
	public int key;
	public boolean isRed;
	
	TreeNode(TreeNode nil, int key)
	{
		this.left = nil;
		this.right = nil;
		this.parent = nil;
		this.key = key;
		this.isRed = false;
	}
}
