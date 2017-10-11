import java.util.Scanner;

public class Intersection {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Scanner s = new Scanner(System.in);
		System.out.print("Enter the size of first list");
		int size1 = s.nextInt();
		System.out.println("Size of first list is "+size1);
		System.out.print("Enter the size of second list");
		int size2 = s.nextInt();
		System.out.println("Size of second list is "+size2);
		Node[] list1 = new Node[size1+1];
		Node headA = new Node();
		list1[0] = headA;
		Node[] list2 = new Node[size2+1];
		Node headB = new Node();
		list2[0] = headA;
		for(int i= 1; i<=size1; i++)
		{
			list1[i] = new Node();
			list1[i-1].next= list1[i];
			System.out.print("Enter "+i+" position element of first list");
			list1[i].data = s.nextInt();
		}
		list1[size1].next= null;
		for(int i= 1; i<=size2; i++)
		{
			list2[i] = new Node();
			list2[i-1].next= list2[i];
			System.out.print("Enter "+i+" position element of second list");
			list2[i].data = s.nextInt();
		}
		list2[size2].next= null;
		
		int common = findCommonElement(list1,list2);
		System.out.println("The common element of two linked lists is " + common);
	}
	
	public static int findCommonElement(Node[] list1, Node[] list2)
	{
		int size1 = list1.length;
		int size2 = list2.length;
		//System.out.println(size1);
		//System.out.println(size2);
		int size =0;
		int common =0;
		int offset = size1 - size2;
		//System.out.println(offset);
		if(offset>0)
		{
			size = size2;
		}
			
		else
		{
			size = size1;
			offset = size2 -size1;
		}
		for(int i= 1; i<size; i++)
		{
			if(size==size2)
			{
				if(list2[i].data==list1[i+offset].data)
				{
					common = list2[i].data;
					break;
				}
			}
			else
			{
				if(list1[i].data==list2[i+offset].data)
				{
					common = list1[i].data;
					break;
				}
			}
			
		}
		return common;
	}

}
