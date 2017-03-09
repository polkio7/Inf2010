package probleme1;

import java.util.Random;
import java.util.ArrayList;

public class LinearSpacePerfectHashing<AnyType>
{
	static int p = 46337;

	QuadraticSpacePerfectHashing<AnyType>[] data;
	int a, b;

	LinearSpacePerfectHashing()
	{
		a=b=0; data = null;
	}

	LinearSpacePerfectHashing(ArrayList<AnyType> array)
	{
		AllocateMemory(array);
	}

	public void SetArray(ArrayList<AnyType> array)
	{
		AllocateMemory(array);
	}

	@SuppressWarnings("unchecked")
	private void AllocateMemory(ArrayList<AnyType> array)
	{
		Random generator = new Random( System.nanoTime() );

		if(array == null || array.size() == 0)
		{
			// A completer
			return;
		}
		if(array.size() == 1)
		{
			a = b = 0;

			// A completer
			data = (QuadraticSpacePerfectHashing<AnyType>[]) new Object[1];
			data[0] = new QuadraticSpacePerfectHashing<AnyType>(array);
			return;
		}

		// A completer

		data = null;
		// A completer
		a=generator.nextInt( p ) + 1;
		b=generator.nextInt( p );
		
		data = (QuadraticSpacePerfectHashing<AnyType>[]) new QuadraticSpacePerfectHashing[array.size()];
		for(int g=0;g<data.length;g++){
			data[g]=new QuadraticSpacePerfectHashing<AnyType>();
		}
		
		for(int i=0;i<array.size();i++){
			ArrayList<AnyType> aTemp = new ArrayList<AnyType>();
			
			//copie le contenu de la case à la clé si plus de un item
			if(data[getKey(array.get(i))]!=null)
				
			
					for(int k=0;k<data[getKey(array.get(i))].Size();k++){
						if(data[getKey(array.get(i))].items[k] != null){
							aTemp.add(data[getKey(array.get(i))].items[k]);
						}
					}
			
			//ajoute l'élement actif
			aTemp.add(array.get(i));
			/*
			System.out.println("Clé : " + getKey(array.get(i)));
			System.out.println("Ajout de : " + array.get(i));
			System.out.println("État tableau Temp : " + aTemp.toString() );
			*/
			//rehash de la case
			data[getKey(array.get(i))].SetArray(aTemp); 


		}


	}
	public int Size()
	{
		if( data == null ) return 0;

		int size = 0;
		for(int i=0; i<data.length; ++i)
		{
			size += (data[i] == null ? 1 : data[i].Size());
		}
		return size;
	}

	public boolean containsKey(int key)
	{
		// A completer
		return (data[key].Size()>0 ? true:false );
	}

	public int getKey (AnyType x) {
		// A completer
		return ((a*x.hashCode()+b)%p)%(data.length);
	}

	public boolean containsValue (AnyType x) {
		// A completer
		if(containsKey(getKey(x)))
			return (data[getKey(x)].containsValue(x));
		else
			return false;
	}

	public void remove (AnyType x) {
		// A completer
		if(containsValue(x))
			data[getKey(x)].remove(x);
	}

	public String toString () {
		String result = "";

		// A completer
		for(int i=0;i<data.length;i++){
			if(data[i] != null && data[i].Size()>0){
				result += "[clé_"+i+"] -> ";
				result += data[i].toString();
				result += "\n";
			}
		}

		return result; 
	}

}
