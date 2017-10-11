import java.util.ArrayList;
import java.util.Iterator;
public class BinomialHeapOperations {



	// MakeHeap
	public BHeap makeBinomialHeap()
	{
		BHeap heap = new BHeap();
		heap.roots = null;
		heap.setHead(heap.roots);
		return heap;
	}

	// Heap Insert
	public BHeap binomialHeapInsert(BHeap heap,int key)
	{
		System.out.println("insert ");
		BHeap newHeap = makeBinomialHeap();
		Node newNode = new Node(key);
		newHeap.setHead(newNode);
		heap = binomialHeapUnion(heap,newHeap);
		System.out.println("KEY "+heap.getHead().getKey());
		return heap;
	}

	// Heap Union
	public BHeap binomialHeapUnion(BHeap heap, BHeap newHeap)
	{
		BHeap h = makeBinomialHeap();
		h.setHead(binomialHeapMerge(heap,newHeap));
		Node prevX = null;
		Node x = h.getHead();
		Node nextX = x.getSibling();

		while (nextX != null) 
		{
			if ((x.getDegree() != nextX.getDegree()) || ((nextX.getSibling() != null) && (nextX.getSibling().getDegree() == x.getDegree()))) 
			{                
				prevX = x;
				x = nextX;
			} 
			else
			{
				if (x.getKey() <= nextX.getKey()) 
				{
					x.setSibling(nextX.getSibling());
					nextX.setParent(x); 
					nextX.setSibling(x.getChild());
					x.setChild(nextX); 
					x.setDegree(x.getDegree()+1);
				} 
				else 
				{
					if (prevX == null) 
					{
						h.setHead(nextX);
					}
					else 
					{
						prevX.setSibling(nextX);
					}
					x.setParent(nextX);
					x.setSibling(nextX.getChild());
					nextX.setChild(x);
					nextX.setDegree(nextX.getDegree()+1);
					x = nextX;
				}
			}
			nextX = x.getSibling();

		}
		return h;
	}

	// Heap Link
	public void binomialLink(Node y, Node z)
	{
		y.setParent(z);
		y.setSibling(z.getChild());
		z.setChild(y);
		z.setDegree(z.getDegree()+1);
		System.out.println("exit link");
	}

	// Heap Merge
	public Node binomialHeapMerge(BHeap heap, BHeap newHeap)
	{
		System.out.println("insert merge");
		if(heap.getHead()==null)
			return newHeap.getHead();
		else if(newHeap.getHead() == null)
			return heap.getHead();
		else
		{
			Node head;
			Node heapNext = heap.getHead();
			Node newHeapNext = newHeap.getHead();
			if(heap.getHead().getDegree() <= newHeap.getHead().getDegree())
			{
				head = heap.getHead();
				heapNext = heapNext.getSibling();
			}
			else
			{
				head = newHeap.getHead();
				newHeapNext = newHeapNext.getSibling();
			}

			Node tail = head;
			while(heapNext != null && newHeapNext != null)
			{
				if(heapNext.getDegree() <= newHeapNext.getDegree())
				{
					tail.setSibling(heapNext);
					heapNext = heapNext.getSibling();
				}
				else
				{
					tail.setSibling(newHeapNext);
					newHeapNext = newHeapNext.getSibling();
				}
				tail = tail.getSibling();
			}
			if(heapNext != null)
			{
				tail.setSibling(heapNext);
			}
			else
			{
				tail.setSibling(newHeapNext);
			}
			System.out.println("exit merge");
			return head;
		}

	}

	// Minimum
	public Node binomialHeapMinimum(BHeap heap)
	{
		Node y = null;
		Node x = heap.getHead();
		System.out.println(x.getKey());
		int min = x.getKey();
		while(x != null)
		{
			if(x.getKey() <= min)
			{
				min = x.getKey();
				y = x;
			}
			x = x.getSibling();
		}
		return y;
	}

	// Extract Min
	public BHeap binomialHeapExtractMin(BHeap heap)
	{
		if(heap.getHead() == null)
			return null;
		Node minPrev = null;
		Node min = heap.getHead();
		Node next = min.getSibling();
		Node nextPrev = min;

		while(next != null)
		{
			if(next.getKey() < min.getKey())
			{
				min = next;
				minPrev = nextPrev;
			}
			nextPrev = next;
			next = next.getSibling();
		}
		// remove minimum node
		if(heap.getHead()==min)
		{
			heap.setHead(min.getSibling());
		}
		else
		{
			minPrev.setSibling(min.getSibling()); 
		}

		// reverse the order of children linked list
		BHeap newHeap = makeBinomialHeap();
		newHeap.setHead(null);
		Node firstChild = min.getChild();  
		while(firstChild!=null)
		{
			Node nextChild = firstChild.getSibling();
			firstChild.setParent(null);
			firstChild.setSibling(newHeap.getHead());
			newHeap.setHead(firstChild);
			firstChild = nextChild;
		}

		// call union
		heap = binomialHeapUnion(heap, newHeap);
		if(min!=null)
		{
		System.out.println("The extracted minimum node in the binomial heap has key"+min.getKey());
		}
		return heap;
	}

	// Decrease Key
	public void binomialHeapDecreaseKey(BHeap heap, Node x, int key)
	{
		if(key > x.getKey())
		{
			System.out.println("Error: new key is greater than current key");
			return;
		}
		x.setKey(key);
		Node y = x;
		Node z = x.getParent();
		while(z != null && (y.getKey() < z.getKey()))
		{
			int temp = y.getKey();
			y.setKey(z.getKey());
			z.setKey(temp);
			y = z;
			z = y.getParent();
		}
	}

	// Heap Delete
	public BHeap binomialHeapDelete(BHeap heap,int value) 
	{
		BHeap newHeap = makeBinomialHeap();
		if ((heap.getHead() != null) && (heap.getHead().findNode(value) != null)) 
		{
			Node x = heap.getHead().findNode(value);
			Node minimum = binomialHeapMinimum(heap);
			int new_value = minimum.getKey()-1;
			binomialHeapDecreaseKey(heap, x , new_value);
			newHeap = binomialHeapExtractMin(heap);
		}
		return newHeap;
	}


	// Display
	public void displayBinomialHeap(BHeap heap)
	{
		System.out.print("\nBinomial Heap : ");
		p1(heap.getHead());
		//displayBinomialHeap(heap.getHead());
		System.out.println("\n");
	}

	private void p1(Node n)
	{
		if(n != null)
		{
			ArrayList<Node> rootList = new ArrayList<Node>();
			System.out.println("   ");
			while(n != null)
			{
				System.out.print("---");
				rootList.add(n);
				System.out.print(n.getKey()+"\t");
				n = n.getSibling();
			}
			System.out.println("   ");
			p2(rootList);
		}
	}
	
	private void p2(ArrayList<Node> rootList)
	{
			Iterator<Node> iterator = rootList.iterator();
			ArrayList<Node> newList = new ArrayList<Node>();
			if(rootList.isEmpty())
			{
				return;
			}
			while(iterator.hasNext())
			{
				Node root = iterator.next();
				if(root.getChild()!= null)
				{
					newList.add(root.getChild());
					Node child = root.getChild();
					System.out.print("/");
					System.out.print(child.getKey());
					//System.out.print("   ");
					while(child.getSibling()!=null)
					{
						newList.add(child.getSibling());
						System.out.print("---");
						System.out.print(child.getSibling().getKey());
						//System.out.print("   ");
						child = child.getSibling();
					}
					System.out.print("   |");
				}
				else
				{
					System.out.print("     ");
				}
				//p3(newList);	
			}
			System.out.println("\t\t");
			p2(newList);	
	}
	
	private void p3(ArrayList<Node> newList)
	{
		System.out.println("");
		Iterator<Node> iterator = newList.iterator();
		while(iterator.hasNext())
		{
			Node root = iterator.next();
			System.out.print(root.getKey()+"   ");
		}
	}

	private void printHeap(Node n)
	{
		if(n != null)
		{
			// traverse child
			printHeap(n.getChild());
			System.out.print(n.getKey() +"\t");
			// traverse sibling
			printHeap(n.getSibling());
		}
	}


	private void displayBinomialHeap(Node r)
	{

		if (r != null)
		{

			if(r.getChild()!=null)
				displayBinomialHeap(r.getChild());
			System.out.print(r.getKey() +" ");
			if(r.getSibling()!=null)
				displayBinomialHeap(r.getSibling());
		}
	}    




	/*public void printBinomialHeap (BHeap heap)
		{

			LinkedList <Node> l = new LinkedList<Node>();
			Deque<Node> s = new ArrayDeque<Node>(); 


				if(min != null)
				{

					Node ptr = min;

					do{
						s.push(ptr);
						ptr = ptr.sibling;
					} while(ptr != min);


					Node t;
					int i=1;

					while(!s.isEmpty())
					{
		  				System.out.print("Level "+i+": [");

						while(!s.isEmpty())
							l.add(s.pop());


						while(!l.isEmpty())
						{
							t = l.removeLast();

							if(!l.isEmpty())
								System.out.print(t.data+",");
							else
								System.out.print(t.data);

							if(t.child != null)
							{
								Node p = t.child;
								do{
									s.push(p);
									p = p.sibling;
								} while(p != t.child);
							}

						}

						System.out.println("]");
						i++;

					}
				}
		}// end of print
	 */		

	public void printBinomialHeap(BHeap heap)
	{
		int level = 0;
		if(heap.getHead() == null)
		{
			level = 0;
		}
		else
		{
			Node root = heap.getHead();
			while(root.getSibling() != null)
			{
				root = root.getSibling();
			}
			Node child = root.getChild();
			level++;
			while(child != null)
			{
				level++;
				child = child.getChild();
			}
		}
		System.out.println("Level is"+level);
		ArrayList<Node> nodes = new ArrayList<Node>();
		Node head = heap.getHead();
		if(head != null)
		{
			while(head != null)
			{
				System.out.print("     ");
				nodes.add(head);
				//printNodes(head, level);
				head = head.getSibling();

			}
		}
		else
		{
			System.out.print("   0  ");
		}




		for(Node n: nodes)
		{
			System.out.print("         "+n.getKey());
		}
		System.out.println("");
		ArrayList<Node> childList = null;
		for(int i=1;i<level;i++)
		{
			System.out.println("");
			childList = printNodes(nodes);
			nodes = childList;
		}
	}

	public ArrayList<Node> printNodes(ArrayList<Node> nodes)
	{
		ArrayList<Node> childList = new ArrayList<Node>();
		for(Node n: nodes)
		{			
			System.out.print("***");
			if(n.getChild()!=null)
			{
				Node child = n.getChild();
				childList.add(child);
				System.out.print(child.getKey());
				if(child.getSibling()!=null)
				{
					Node sibling = child.getSibling();
					while(sibling!= null)
					{
						childList.add(sibling);
						System.out.print("   "+sibling.getKey());
					}
				}

			}
		}
		return childList;
	}
	/********
	 * public void printHeap(){
        System.out.print("\n Heap is -> : ");
        printHeap(root);
        System.out.println("\n");
    }
    private void printHeap(BinomialNode n) //DFS print
    {
        if (n != null){
        //traverse child
            printHeap(n.child);
            System.out.print(n.key +"\t");
            //traverse siblings
            printHeap(n.sibling);
        }
    }
	 */


	/*public void printNodes(Node node, int level)
	{

		System.out.print(node.getKey());
		System.out.printl
		if(node.getChild()!=null)
		{
			while(node.getChild()!=null)
			{
				System
			}
		}





		for(int i = 1; i<=level; i++)
		{
			if((node != null) && (node.getChild()!=null))
			{
				Node child = node.getChild();
				System.out.println("/");
				System.out.println(child.getKey());
				Node sibling = child.getSibling();
				while(sibling != null)
				{
					System.out.print("     ");
					System.out.print(sibling.getKey());
					sibling = sibling.getSibling();
				}
			}


		}
	}

	 */
	/*	
				System.out.print(head.getKey());
				for(int i = 1; i<=level; i++)
				{
					if(head.getChild() != null)
					{
						Node child = head.getChild();
						System.out.println("/");
						System.out.println(child.getKey());

					}
				}
			}
			System.out.print();

	}


	public void printNodes(Node node, int level)
	{
		Node head = heap.getHead();
		while(head.getSibling() != null)
		{
			System.out.print("     ");
			System.out.print(head.getKey());
			for(int i = 1; i<=level; i++)
			{
				if(head.getChild() != null)
				{
					Node child = head.getChild();
					System.out.println("/");
					System.out.println(child.getKey());

				}
			}
		}
	}*/
	/*BINOMIAL-HEAP-DELETE(H, x)
1 BINOMIAL-HEAP-DECREASE-KEY(H, x, −∞)
2 BINOMIAL-HEAP-EXTRACT-MIN(H)*/


	/*BINOMIAL-HEAP-DECREASE-KEY(H, x, k)
1 if k > key[x]
2 then error “new key is greater than current key”
3 key[x] ← k
4 y ← x
5 z ← p[y]
6 while z 6= NIL and key[y] < key[z]
7 do exchange key[y] ↔ key[z]
8 ✄ If y and z have satellite fields, exchange them, too.
9 y ← z
10 z ← p[y]*/



	/*BINOMIAL-HEAP-EXTRACT-MIN(H)
	1 find the root x with the minimum key in the root list of H,
	and remove x from the root list of H
	2 H
	′ ← MAKE-BINOMIAL-HEAP()
	3 reverse the order of the linked list of x’s children, setting the p field of each
	child to NIL, and set head[H
	′
	] to point to the head of the resulting list
	4 H ← BINOMIAL-HEAP-UNION(H, H
	′
	)
	5 return x*/








	/*Node<T> min = head;
        Node<T> minPrev = null;
        Node<T> next = min.sibling;
        Node<T> nextPrev = min;

        while (next != null) {
            if (next.compareTo(min) < 0) {
                min = next;
                minPrev = nextPrev;
            }
            nextPrev = next;
            next = next.sibling;
        }

        removeTreeRoot(min, minPrev);
        return min.key;*/




	/*BINOMIAL-HEAP-MINIMUM(H)
	1 y ← NIL
	2 x ← head[H]
	3 min ← ∞
	4 while x 6= NIL
	5 do if key[x] < min
	6 then min ← key[x]
	7 y ← x
	8 x ← sibling[x]
	9 return y*/
	/*Binomial-HeapMerge(H1,H2)
a = head[H1]
b = head[H2]
head[H1] = Min-Degree(a, b)
if head[H1] = NIL
    return
if head[H1] = b
   then b = a
a = head[H1]
while b <> NIL
    do if sibling[a] = NIL
          then sibling[a] = b
               return
          else if degree[sibling[a]] < degree[b]
                  then a = sibling[a]
                  else c = sibling[b]
                       sibling[b] = sibling[a]
                       sibling[a] = b
                       a = sibling[a]
                       b = c
	 */




	/*BINOMIAL-HEAP-INSERT(H, x)
1 H
′ ← MAKE-BINOMIAL-HEAP()
2 p[x] ← NIL
3 child[x] ← NIL
4 sibling[x] ← NIL
5 degree[x] ← 0
6 head[H
′
] ← x
7 H ← BINOMIAL-HEAP-UNION(H, H
′
)*/

	/*public BHeap binomialHeapUnion(BHeap heap, BHeap newHeap)
		{
			System.out.println("insert union");
			BHeap h = makeBinomialHeap();
			h.setHead(binomialHeapMerge(heap,newHeap));
			System.out.println("after merge");
			if(h.getHead() == null)
			{
				System.out.println("enter after merge");
				return h;
			}
			Node prevX = null;
			Node x = h.getHead();
			Node nextX = x.getSibling();
			while(nextX != null)
			{
				System.out.println("enter loop");
				if((x.getDegree() != nextX.getDegree()) || (nextX.getSibling() != null) && (nextX.getSibling().getDegree() == x.getDegree()))
				{
					prevX = x;
					x = nextX;
				}
				else 
				{
					if(x.getKey() <= nextX.getKey())

					{
						x.setSibling(nextX.getSibling());
						binomialLink(nextX,x);
					}
					else 
					{
						if(prevX == null)
						{
							h.setHead(nextX);
						}
						else
						{
							prevX.setSibling(nextX);
						}
						binomialLink(x,nextX);
						x=nextX;
					}
				}
			}
				nextX = x.getSibling();
				System.out.println("exit union");
				return h;
			}
	 */

	/*public Node binomialHeapMerge(BHeap heap, BHeap newHeap)
		{

			Node a = heap.getHead();
			Node b = newHeap.getHead();
			Node minDegree = minimumDegree(a,b); 
			heap.setHead(minDegree);
			if(heap.getHead() == null)
				return newHeap.getHead();
			if(heap.getHead()==b)
				b=a;
			a= heap.getHead();
			while(b!=null)
			{
				if(a.getSibling()==null)
				{
					a.setSibling(b);
					return heap.getHead();
				}
				else if(a.getSibling().getDegree()<b.getDegree())
				{
					a=a.getSibling();
				}
				else
				{
					Node c=b.getSibling();
					b.setSibling(a.getSibling());
					a.setSibling(b);
					a=a.getSibling();
					b=c;
				}	
			}
		}*/
}

