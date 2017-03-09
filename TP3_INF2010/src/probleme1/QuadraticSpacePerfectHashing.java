package probleme1;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Random;

public class QuadraticSpacePerfectHashing<AnyType> 
{
	static int p = 46337;

	int a, b;
	AnyType[] items;

	QuadraticSpacePerfectHashing()
	{
		a=b=0; items = null;
	}

	QuadraticSpacePerfectHashing(ArrayList<AnyType> array)
	{
		AllocateMemory(array);
	}

	public void SetArray(ArrayList<AnyType> array)
	{
		AllocateMemory(array);
	}

	public int Size()
	{
		if( items == null ) return 0;

		return items.length;
	}

	public boolean containsKey(int key)
	{
		// A completer
		return (items[key] == null ? false:true);
	}

	public boolean containsValue(AnyType x )
	{
		// A completer
		//System.out.println("Cle de x : " + getKey(x) + " // Item a la cle de x " + items[getKey(x)]);
		if(containsKey(getKey(x)))
			return ( items[getKey(x)].equals(x) );
		else 
			return false;
		
	}

	public void remove (AnyType x) {
		// A completer
		if(containsValue(x))
			items[getKey(x)] = null;
	}

	public int getKey (AnyType x) {
		// A completer
		return ((a*x.hashCode()+b)%p)%(Size());
	}

	@SuppressWarnings("unchecked")
	private void AllocateMemory(ArrayList<AnyType> array)
	{
		Random generator = new Random( System.nanoTime() );

		if(array == null || array.size() == 0)
		{
			// A completer
			items = null;
			return;
		}
		if(array.size() == 1)
		{
			a = b = 0;

			// A completer
			items = (AnyType[]) new Object[1];
			items[0] = array.get(0);
			return;
		}
		
		do
		{
			items = null;
			// A completer
			a=generator.nextInt( p ) + 1;
			b=generator.nextInt( p );
		
		}
		while( collisionExists( array ));
	}

	@SuppressWarnings("unchecked")
	private boolean collisionExists(ArrayList<AnyType> array)
	{
		// A completer
		//System.out.println("a : " + a + "// b : " + b);
		
		items = (AnyType[]) new Object[array.size()*array.size()];
		for(int i=0;i<array.size();i++){
			if(items[getKey(array.get(i))] == null)
				items[getKey(array.get(i))]=array.get(i);
			else
				return true;
		}
		return false;
	}
	
	public String toString () {
		String result = "";
		
		// A completer
		for(int i=0;i<Size();i++){
			if(items[i]!=null)
				result += "("+ i +","+items[i]+"), ";
		}
		result = result.substring(0, result.length()-2) + ".";
		return result; 
	}
}
