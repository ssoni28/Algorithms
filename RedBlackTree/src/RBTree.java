import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class RBTree {

	public static void main(String[] args) {
		RedBlackTreeOperations rb = new RedBlackTreeOperations();
		TreeNode node;
		try
		{
			FileReader file = new FileReader("//Users//swativish//Documents//RB");
			Scanner scanner = new Scanner(file);
			while (scanner.hasNext()) 
			{
				if (scanner.hasNextInt()) 
				{
					node = new TreeNode(rb.nil, scanner.nextInt());
					rb.RBInsert(node);
				} 
				else 
				{
					scanner.next();
				}
			}
			scanner.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}

		System.out.println("Red Black Tree is:");
		rb.printRBTree();

		System.out.println("Please enter one of the following specified operations:");
		System.out.println("sort, search x, min, max, leftrotate x, rightrotate x, successor x, predecessor x, insert x, delete x and exit.");

		Scanner input = new Scanner(System.in);
		System.out.print("Please enter the operation: ");
		String operation = input.nextLine();

		while(!operation.equalsIgnoreCase("exit"))
		{
			Boolean flag = true;
			try
			{
				if(operation.equalsIgnoreCase("sort"))
				{
					System.out.println("The RB tree in Sorted Order : ");
					rb.sort(rb.root);
					System.out.println();
					flag = false;
				}

				else if(operation.equalsIgnoreCase("min"))
				{
					if(rb.nil == rb.root || rb.root == null)
					{
						System.out.println("Emtpty Tree");
					}
					else
					{
						System.out.println("Minimum node value is: " + rb.min(rb.root).key);
					}
					flag = false;
				}
				else if(operation.equalsIgnoreCase("max"))
				{
					if(rb.nil == rb.root || rb.root == null)
					{
						System.out.println("Empty Tree");
					}
					else
					{
						System.out.println("Maximum node value is: " + rb.max(rb.root).key);
					}
					flag = false;
				}
				else if(operation.contains("search"))
				{
					String[] op = operation.split(" ");
					TreeNode T = rb.search(rb.root, Integer.parseInt(op[1]));
					if(rb.nil == T ||  T == null)
					{
						System.out.println("Node having value "+ op[1]+" does not exists in this RB Tree");
					}
					else
					{
						System.out.println("Node having value "+ op[1]+" exists in this RB Tree");
					}
					flag = false;
				}
				else if(operation.contains("successor"))
				{
					String[] op = operation.split(" ");
					TreeNode T = rb.search(rb.root, Integer.parseInt(op[1]));
					if(rb.nil == T ||  T == null)
					{
						System.out.println("Node having value "+ op[1]+" does not exists in this RB Tree");
					}
					else
					{
						T = rb.successor(T);
						if(rb.nil == T ||  T == null)
						{
							System.out.println("No successor node exists");
						}
						else
						{
							System.out.println("Successor of " +op[1]+" is "+ T.key);
						}
					}
					flag = false;
				}
				else if(operation.contains("predecessor"))
				{
					String[] op = operation.split(" ");
					TreeNode T = rb.search(rb.root, Integer.parseInt(op[1]));
					if(rb.nil == T ||  T == null)
					{
						System.out.println("Node having value "+ op[1]+" does not exists in this RB Tree");
					}
					else
					{
						T = rb.predecessor(T);
						if(rb.nil == T ||  T == null)
						{
							System.out.println("No predecessor node exists.");
						}
						else
						{
							System.out.println("Predecessor of " +op[1]+" is "+ T.key);
						}
					}
					flag = false;
				}
				else if(operation.contains("leftrotation"))
				{	
					String[] op = operation.split(" ");
					TreeNode T = rb.search(rb.root, Integer.parseInt(op[1]));
					if(rb.nil == T ||  T == null)
					{
						System.out.println("Node having value "+ op[1]+" does not exists in this RB Tree");
					}
					else
					{
						rb.leftRotate(T);
						System.out.println("Left Rotation successfull.");
						flag = true;
					}

				}
				else if(operation.contains("rightrotation"))
				{
					String[] op = operation.split(" ");
					TreeNode T = rb.search(rb.root, Integer.parseInt(op[1]));
					if(rb.nil == T ||  T == null)
					{
						System.out.println("Node having value "+ op[1]+" does not exists in this RB Tree");
					}
					else
					{
						rb.rightRotate(T);
						System.out.println("Right Rotation successfull.");
						flag = true;
					}
				}	
				else if(operation.contains("insert"))
				{
					String[] op = operation.split(" ");
					TreeNode T = new TreeNode(rb.nil, Integer.parseInt(op[1]));
					rb.RBInsert(T);
					System.out.println("Insertion successfull.");
					flag = true;
				}
				else if(operation.contains("delete"))
				{
					String[] op = operation.split(" ");
					TreeNode T = rb.search(rb.root, Integer.parseInt(op[1]));
					if(rb.nil == T || T == null)
					{
						System.out.println("No such node exists in the tree.");
					}
					else
					{
						rb.RBDelete(T);
						System.out.println("Deletion successfull.");
					}
					flag = true;
				}
			}
			catch(Exception e)
			{
				System.err.println("Invalid command:" + e);
			}

			if(flag)
			{
				System.out.println("Red Black Tree after the " + operation + " operation is: ");
				rb.printRBTree();
				int height  = rb.treeHeight(rb.root) - 2;
				if(height < 0) 
				{
					height = 0;
				}
				System.out.println("Height of the Red Black tree after the " + operation + " operation is: " + height);
			}
			System.out.println("Please enter one of the following specified operations:");
			System.out.println("sort, search x, min, max, leftrotate x, rightrotate x, successor x, predecessor x, insert x, delete x and exit.");
			System.out.print("Please enter the operation : ");
			operation = input.nextLine();
		}
		input.close();
		System.out.println("Program end");
	}
}


