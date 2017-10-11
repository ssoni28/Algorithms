import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class RedBlackTreeOperations {
	TreeNode root;
	TreeNode nil;
	
	RedBlackTreeOperations()
	{
		nil = new TreeNode(null,0);
		root = nil;
	}
	// Insert node z in RB Tree
		public void RBInsert(TreeNode z)
		{
			TreeNode y = nil;
			TreeNode x = root;
			
			while(!nil.equals(x))
			{
				y = x;
				if(z.key < x.key)
				{
					x = x.left;
				}
				else
				{
					x = x.right;
				}
			}
			z.parent = y;
			if(nil.equals(y))
			{  
				root = z;
			}
			else if(z.key < y.key)
			{
				y.left = z;
			}
			else
			{
				y.right = z;
			}
			
			z.left = nil;
			z.right = nil;
			z.isRed = true;
			RBInsertFixup(z);
		}
		
		// RB Insert Fix after inserting every new node
		public void RBInsertFixup(TreeNode z)
		{
			while(z.parent.isRed)
			{
				if(z.parent.equals(z.parent.parent.left))
				{
					TreeNode y = z.parent.parent.right;
					if(y.isRed)
					{
						z.parent.isRed = false;
						y.isRed = false;
						z.parent.parent.isRed = true;
						z = z.parent.parent;
					}
					else 
					{
						if(z.equals(z.parent.right))
						{
							z = z.parent;
							leftRotate(z);
						}
						z.parent.isRed = false;
						z.parent.parent.isRed = true;
						rightRotate(z.parent.parent);
					}
				}
				else
				{
					TreeNode y = z.parent.parent.left;
					if(y.isRed)
					{
						z.parent.isRed = false;
						y.isRed = false;
						z.parent.parent.isRed = true;
						z = z.parent.parent;
					}
					else 
					{
						if(z.equals(z.parent.left))
						{
							z = z.parent;
							rightRotate(z);
						}
						z.parent.isRed = false;
						z.parent.parent.isRed = true;
						leftRotate(z.parent.parent);
					}
				}
			}
			root.isRed = false;
		}
		
		
	// Find node with minimum value
	public TreeNode min(TreeNode node)
	{
		while(node.left != nil)
		{
			node = node.left;
		}
		return node;
	}
	
	// Find node with maximum value
	public TreeNode max(TreeNode node)
	{
		while(node.right != nil)
		{
			node = node.right;
		}
		return node;
	}
	
	//Find the node having given value
	public TreeNode search(TreeNode node, int key)
	{
		while(node != nil && key != node.key)
		{
			if(key < node.key)
			{
				node = node.left;
			}
			else
			{
				node = node.right;
			}
		}
		return node;
	}
	
	public void sort(TreeNode node)
	{
		if(nil != node)
		{
			sort(node.left);
			System.out.print(node.key + "  ");
			sort(node.right);
		}
	}
	
	// Find successor node
	public TreeNode successor(TreeNode node)
	{
		TreeNode successorNode = null;
		
		if(node.right != nil)
		{
			successorNode = min(node.right);
		}
		else
		{
			successorNode = node.parent;
			
			while(successorNode != nil && node.equals(successorNode.right))
			{
				node = successorNode;
				successorNode = successorNode.parent;
			}
		}	
		return successorNode;
	}
	
	// Find predecessor
	public TreeNode predecessor(TreeNode node)
	{
		TreeNode predecessorNode = null;
		
		if( node.left !=nil)
		{
			predecessorNode = max(node.left);
		}
		else
		{
			predecessorNode = node.parent;
			
			while(predecessorNode  != nil && node.equals(predecessorNode.left))
			{
				node = predecessorNode;
				predecessorNode = predecessorNode.parent;
			}
		}	
		return predecessorNode;
	}
	
	// Left Rotation
	public void leftRotate(TreeNode x)
	{
		TreeNode y = x.right;
		x.right = y.left;
		
		if(!nil.equals(y.left))
		{
			y.left.parent = x;
		}
		y.parent = x.parent;
		if(x.parent.equals(nil))
		{
			root = y;
		}
		else if(x.equals(x.parent.left))
		{
			x.parent.left = y;
		}
		else
		{
			x.parent.right = y;
		}
		y.left = x;
		x.parent = y;
	}
	
	// Right Rotation
	public void rightRotate(TreeNode x)
	{
		TreeNode y = x.left;
		x.left = y.right;
		
		if(!nil.equals(y.right))
		{
			y.right.parent = x;
		}
		
		y.parent = x.parent;
		
		if(x.parent.equals(nil))
		{
			root = y;
		}
		else if(x.equals(x.parent.right))
		{
			x.parent.right = y;
		}
		else
		{
			x.parent.left = y;
		}
		y.right = x;
		x.parent = y;
	}
	
	// RB Transplant
    public void RBTransplant(TreeNode u, TreeNode v) 
    {
    	if(u.parent.equals(nil))
    	{
    		root = v;
    	}
    	else if(u.equals(u.parent.left))
    	{
    		u.parent.left = v;
    	}
    	else
    	{
    		u.parent.right = v;
    	}
    	v.parent = u.parent;
    }
    
    // RB Delete
    public void RBDelete(TreeNode z) 
    {
    	TreeNode y = z; 
    	TreeNode x;   
    	boolean isYOriginalRed = y.isRed;  	
    	
    	if (z.left.equals(nil)) 
    	{    	
    		x = z.right;
    		RBTransplant(z, z.right);             	
    	}
    	else if (z.right.equals(nil)) 
    	{ 	
    		x = z.left;
    		RBTransplant(z, z.left);              	
    	}
    	else 
    	{
    		y = min(z.right);
    		isYOriginalRed = y.isRed;
    		x = y.right;
			  
    		if (y.parent.equals(z))
    		{
    			x.parent = y;
    		}
    		else 
    		{
    			RBTransplant(y, y.right);
    			y.right = z.right;
    			y.right.parent = y;
    		}
			
    		RBTransplant(z, y);
    		y.left = z.left;
    		y.left.parent = y;
    		y.isRed = z.isRed;
    	}	
    	if (!isYOriginalRed)	
    	{
    		RBDeleteFixup(x);
    	}
    }

    // RB Delete Fix
    protected void RBDeleteFixup(TreeNode x) 
    {
    	TreeNode w = null;

    	while(!x.equals(root) && !x.isRed) 
    	{
    		if(x.equals(x.parent.left)) 
    		{
    			w = x.parent.right;
    			if(w.isRed) 
    			{
    				w.isRed = false;
    				x.parent.isRed = true;
    				leftRotate(x.parent);
    				w = x.parent.right;
    			}
    			if (!w.left.isRed &&  !w.right.isRed) 
    			{
    				w.isRed = true;
    				x = x.parent;
    			}
    			else 
    			{
    				if(!w.right.isRed) 
    				{
    					w.left.isRed = false;
    					w.isRed = true;
    					rightRotate(w);
    					w = x.parent.right;
    				}
    				
    				w.isRed = x.parent.isRed;
    				x.parent.isRed = false;
    				w.right.isRed = false;
    				leftRotate(x.parent);
    				x = root;
    			}
    		}
    		else 
    		{
    			w = x.parent.left;
    			if (w.isRed) 
    			{
    				w.isRed = false;
    				x.parent.isRed = true;
    				rightRotate(x.parent);
    				w = x.parent.left;
    			}
    			if (!w.right.isRed &&  !w.left.isRed) 
    			{
    				w.isRed = true;
    				x = x.parent;
    			}
    			else 
    			{
    				if(!w.left.isRed) 
    				{
    					w.right.isRed = false;
    					w.isRed = true;
    					leftRotate(w);
    					w = x.parent.left;
    				}
    				
    				w.isRed = x.parent.isRed;
    				x.parent.isRed = false;
    				w.left.isRed = false;
    				rightRotate(x.parent);
    				x = root;
    			}
    		}
    	}

    	x.isRed = false;
    }

    public void printRBTree() 
    {
        int maximumLevel = treeHeight(root);
        printNodes(Collections.singletonList(root), 1, maximumLevel);
    }

    public int treeHeight(TreeNode node) 
    {
        if (node == null)
        {
        	return 0;
        }
        return Math.max(treeHeight(node.left), treeHeight(node.right)) + 1;
    }
    
    private void printNodes(List<TreeNode> nodes, int level, int maxLevel) 
    {
        if (nodes.isEmpty() || NullElements(nodes))
        {
        	return;
        }

        List<TreeNode> newList = new ArrayList<TreeNode>();
        System.out.print("Level "+ level+"  ");
        for (TreeNode node : nodes)
        {
            if (node != null) 
            {
            	if(node.isRed)
            	{
            		System.out.print(node.key+",red  ");
            	}
            	else
            	{
            		System.out.print(node.key+", black  ");
            	}
            	
                newList.add(node.left);
                newList.add(node.right);
            } 
            else 
            {
            	newList.add(null);
            	newList.add(null);
                System.out.print(" ");
            }
            
        }
        System.out.println("");
        printNodes(newList, level + 1, maxLevel);
    }
 
    private boolean NullElements(List<TreeNode> list) 
    {
        for (Object object : list) 
        {
            if (object != null)
            {
            	return false;
            }
        }
        return true;
    }
}


