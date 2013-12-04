import java.util.ArrayList;
import java.util.Iterator;


public class KTAIL {
	
	ArrayList<Integer> alphabeta = new ArrayList<Integer>();
	String inputString = new String();
	
	ArrayList<Node> FSM = new ArrayList<KTAIL.Node>();
	
	int K = 2;
	
	public KTAIL(String inputs)
	{
		inputString = inputs;
	}
	
	public static void main(String[] args)
	{
		KTAIL ktail = new KTAIL("RCERCECRECRERCECRECRERCECRERCERCECRECR");
		ktail.algorithm();
	}
	
	public void algorithm()
	{
		getAlphabeta(inputString);
		constructFSM();
	}
	
	
	public void getAlphabeta(String inputS)
	{
		char[] c = inputS.toCharArray();
		for(int i = 0;i< c.length;i++)
		{
			Integer c1 = new Integer(c[i]);
			if(!alphabeta.contains(c1))
			{
				alphabeta.add(new Integer(c1));
			}
		}
		
		System.out.println("alphabeta:");
		for(Integer i:alphabeta)
		{
			System.out.printf("%c",i);
		}
		System.out.println();
		
	}
	
	public String getKtailString(int ind)
	{
		if(ind <= inputString.length() - K - 1)
			return inputString.substring(ind, ind + K);
		else
			return inputString.substring(ind);
	}	
	
	public void constructFSM()
	{
		boolean b = false;
		int currentIndex = 0;
		int currentNodeIndex = 0;
		int nextNodeIndex = 0;
		int existNextNodeIndex = 0;
		int NodeNumber = 0;
		Node currentNode = new Node(0);
		Node nextNode = new Node(0);
		Node existNextNode = new Node(0);
		
		Iterator<KTAIL.Node> i;
		
		for(currentIndex = 0;currentIndex<= inputString.length();currentIndex++)
		{
			b = false;
			if(currentIndex == 0)
			{
				Node node = new Node(NodeNumber);
				node.setTail(getKtailString(0));
				FSM.add(node);
				
				System.out.println("+++"+node.getTail());
				
				currentNodeIndex = 0;
				currentNode = node;
				
				NodeNumber++;
			}
			if(currentIndex == inputString.length())
			{
				Node node = new Node(NodeNumber);
				node.setTail("");
				FSM.add(node);
				
				System.out.println("+++"+node.getTail());
				
				nextNode = node;
				nextNodeIndex = node.getNumber();
				
				
				
				
				
				nextNode.addInEdge(currentNode);
				currentNode.addOutEdge(nextNode);
				
				currentNode = nextNode;
				currentNodeIndex = nextNode.getNumber();
				NodeNumber++;
			}
			else
			{
				String s = getKtailString(currentIndex);
				i = FSM.iterator();
				while(i.hasNext())
				{
					Node n = i.next();
					
					
					if(n.getTail().equals(s))
					{
						System.out.println("***"+n.getTail());
						b = true;
						existNextNode = n;
						existNextNodeIndex = n.getNumber();
					}
				}
				
				if(b)
				{
					
					
					existNextNode.addInEdge(currentNode);
					currentNode.addOutEdge(existNextNode);
					
				}
				
				else
				{
					Node node = new Node(NodeNumber);
					node.setTail(s);
					FSM.add(node);
					System.out.println("+++"+node.getTail());
					
					
					
					NodeNumber++;
					
					nextNode = node;
					nextNodeIndex = node.getNumber();
					
					nextNode.addInEdge(currentNode);
					currentNode.addOutEdge(nextNode);
					
				}
				
				currentNode = nextNode;
				currentNodeIndex = nextNode.getNumber();
			}	
		
		}
		
	}
	

	
	
	
	
	//A node is a state and also an equivalent class
	class Node
	{
		Integer number;
		String tail = new String();
		ArrayList<Integer> inEdge = new ArrayList<Integer>();
		ArrayList<Integer> outEdge = new ArrayList<Integer>();
		
		public Node(int n)
		{
			number = n;
		}
		
		public void addInEdge(Node node)
		{
			if(!inEdge.contains(node.getNumber()))
			{
				inEdge.add(node.getNumber());
			}
		}
		
		public void addOutEdge(Node node)
		{
			if(outEdge.contains(node.getNumber()))
			{
				outEdge.add(node.getNumber());
			}
		}
		
		public void removeInEdge(Node node)
		{
			if(inEdge.contains(node.getNumber()))
			{
				inEdge.remove(node.getNumber());
			}
		}
		
		public void removeOutEdge(Node node)
		{
			if(outEdge.contains(node.getNumber()))
			{
				outEdge.remove(node.getNumber());
			}
		}
		
		public ArrayList<Integer> getInEdge()
		{
			return inEdge;
		}
		
		public ArrayList<Integer> getOutEdge()
		{
			return outEdge;
		}
		
		public boolean hasEdgeFrom(Node node)
		{
			if(inEdge.contains(node.getNumber()))
				return true;
			else
				return false;
		}
		
		public boolean hasEdgeTo(Node node)
		{
			if(outEdge.contains(node.getNumber()))
				return true;
			else
				return false;
		}

		public Integer getNumber() {
			return number;
		}

		public void setNumber(Integer number) {
			this.number = number;
		}

		public String getTail() {
			return tail;
		}

		public void setTail(String tail) {
			this.tail = tail;
		}
		
	}
	
}
