
public class LinkedListQueue<AnyType> implements Queue<AnyType>
{	
	// Un noeud de la file
	@SuppressWarnings("hiding")
	private class Node<AnyType> 
	{
		private AnyType data;
		private Node next;
		
		public Node(AnyType data, Node next) 
		{
			this.data = data;
			this.next = next;
		}

		public void setNext(Node next) 
		{
			this.next = next;
		}
		
		public Node<AnyType> getNext() 
		{
			return next;
		}
		
		public AnyType getData() 
		{
			return data;
		}
	}//end class Node
   
	private int size = 0;		//Nombre d'elements dans la file.
	private Node<AnyType> last;	//Dernier element de la liste
	private Node<AnyType> first;
	
	public LinkedListQueue(){
		
		first = new Node<AnyType>(null,null);
		last = first;
	}
	//Indique si la file est vide
	public boolean empty() 
	{ 
		return size == 0; 
	}
	
	//Retourne la taille de la file
	public int size() 
	{ 
		return size; 
	}
	
	//Retourne l'element en tete de file
	//Retourne null si la file est vide
	//complexit� asymptotique: O(1)
	public AnyType peek()
	{
		if(empty())
			return null;	
		else
			return this.first.getData();
	}
	
	//Retire l'element en tete de file
	//complexit� asymptotique: O(1)
	public void pop() throws EmptyQueueException
	{
		if(empty())
			throw new EmptyQueueException();
		else{
			this.first = this.first.getNext();
			size--;
		}
		
	}
	
	//Ajoute un element a la fin de la file
	//complexit� asymptotique: O(1)
	public void push(AnyType item)
	{	
		if(empty()){
			this.first = new Node<AnyType>(item,null);
			this.first = this.last;
		}
		else{
			Node<AnyType> nodeTemp = new Node<AnyType>(item,null);
			this.last.setNext(nodeTemp);
			this.last = nodeTemp;
		}
		this.size++;
	}  
}
