import java.util.Scanner;

public class BinomialHeapMain {
	public static void main(String[] args)
	{
		BinomialHeapOperations HeapOp = new BinomialHeapOperations();
		BHeap heap = HeapOp.makeBinomialHeap();
		String answer ="";
		Scanner input = new Scanner(System.in);
		do{
			System.out.println("Enter the command");
			System.out.println("1. insert ");
			System.out.println("2. find minimum");
			System.out.println("3. extract minimum");
			System.out.println("4. union");
			System.out.println("5. decrease key");
			System.out.println("6. delete");
			System.out.println("7. display");
			System.out.println("Enter the number corresponding to command");
			int num = input.nextInt();
			
			int key = 0;
			switch(num)
			{
			case 1:
				System.out.println("Enter the key to be inserted in a binomial heap");
				heap = HeapOp.binomialHeapInsert(heap, input.nextInt());
				//1System.out.println("Entry in node"+heap.getHead().getKey());
				break;
			case 2:

				Node minimum = HeapOp.binomialHeapMinimum(heap);
				System.out.println("The minimum key in the binomail heap is "+minimum.getKey());
				break;

			case 3:
				heap = HeapOp.binomialHeapExtractMin(heap);
				if(heap.getHead()!= null)
				{
			//	System.out.println("The extracted minimum node in the binomial heap has key "+extractMin.getKey());
				System.out.println("The binomial heap after extraction is:");
				}
				else
				{
					System.out.println("empty heap");
				}
				break;

			case 4:
				System.out.println("Enter the number of keys to be inserted in new binomial heap for union operation");
				int rootListSize = input.nextInt();
				BHeap newHeapInput = HeapOp.makeBinomialHeap();
				for(int i=1;i<=rootListSize;i++)
				{
					System.out.println("Enter the key"+i);
					//Node root = new Node(input.nextInt());
					newHeapInput = HeapOp.binomialHeapInsert(newHeapInput, input.nextInt());
				}
				System.out.println("You entered heap:");
				HeapOp.displayBinomialHeap(newHeapInput);
				heap = HeapOp.binomialHeapUnion(heap,newHeapInput);
				System.out.println("The heap after union:");
				break;
				
				
				
			case 5:
				System.out.println("Enter the key of a node that you want to decrease");
				key = input.nextInt();
				if(heap.getHead()!= null)
				{
				Node toBeDecreased = heap.getHead().findNode(key);
				if(toBeDecreased!= null)
				{
				System.out.println("Enter new key");
				key = input.nextInt();
				HeapOp.binomialHeapDecreaseKey(heap, toBeDecreased, key);
				System.out.println("Binomial heap after decrease key operation is :");
				}
				else{
					System.out.println("Node deos not exist");
				}
				}
				else
				{
					System.out.println("Empty Heap");
				}
				break;

			case 6:
				System.out.println("enter the key of the node to be deleted");
				key = input.nextInt();
				heap = HeapOp.binomialHeapDelete(heap, key);
				break;
				
			case 7:
				System.out.println("Heap is:");
				HeapOp.displayBinomialHeap(heap);
				break;
				
			}
			HeapOp.displayBinomialHeap(heap);
			//HeapOp.printBinomialHeap(heap);
			System.out.println("");
			
			System.out.println("Do you wish to continue? Y/N");
			answer = input.next();
			
		}while(answer.equalsIgnoreCase("Y"));
		input.close();
	}
}
