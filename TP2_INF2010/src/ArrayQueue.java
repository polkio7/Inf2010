import java.lang.reflect.Array;

public class ArrayQueue<AnyType> implements Queue<AnyType> {
	private int size = 0; // Nombre d'elements dans la file.
	private int startindex = 0; // Index du premier element de la file
	private AnyType[] table;

	@SuppressWarnings("unchecked")
	public ArrayQueue() {
		table = (AnyType[]) new Object[size];
		
	}

	// Indique si la file est vide
	public boolean empty() {
		return size == 0;
	}

	// A completer
	// Retourne la taille de la file
	public int size() {
		return size;
	}

	// Retourne l'element en tete de file
	// Retourne null si la file est vide
	// complexit� asymptotique: O(1)
	public AnyType peek() {
		if(empty())
			return null;
		else
			return table[startindex];
	}

	// Retire l'element en tete de file
	// complexit� asymptotique: O(1)
	public void pop() throws EmptyQueueException {
		if(empty())
			throw new EmptyQueueException();
		else{
			startindex++;
			size--;
		}
			
	}

	// Ajoute un element a la fin de la file
	// Double la taille de la file si necessaire
	// complexit� asymptotique: O(1) ( O(N) lorsqu'un redimensionnement est
	// necessaire )
	public void push(AnyType item) {
		
		if(size+startindex>=table.length)
			resize(2);
		
		table[size+startindex] = item;
		size++;
	}

	// Redimensionne la file. La capacite est multipliee par un facteur de
	// resizeFactor.
	// Replace les elements de la file au debut du tableau
	// complexit� asymptotique: O(N)
	@SuppressWarnings("unchecked")
	private void resize(int resizeFactor) {
		
		AnyType[] tableTemp = (AnyType[]) new Object[(resizeFactor*size)+1];
		
		for (int i=startindex,j = 0; i < (size+startindex); i++,j++) {
			tableTemp[j] = table[i];
		}
		table=tableTemp; 
		startindex=0;
		
	}
}
