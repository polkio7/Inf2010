package probleme2;

public class MyHashMap<KeyType, ValueType> {
	private DoubleHashingTable<Entry<KeyType, ValueType>> items;

	public MyHashMap() {
		items = new DoubleHashingTable<Entry<KeyType, ValueType>>();
	}

	public void put(KeyType key, ValueType val) {
		items.insert(new Entry<KeyType, ValueType>(key, val));
	}

	public ValueType get(KeyType key) {
		return (ValueType) (items.get(new Entry<KeyType, ValueType>(key, null))).value;
	}

	public boolean isEmpty() {
		return (items.getSize() == 0);
	}

	private static class Entry<KeyType, ValueType> {
		public KeyType key;
		public ValueType value;

		public Entry(KeyType key, ValueType value) {
			this.key = key;
			this.value = value;
		}

		public boolean equals(Object cmp) {
			return this.hashCode() == cmp.hashCode();
		}

		public int hashCode() {
			return key.hashCode();
		}
	}

	private class DoubleHashingTable <TableEntry>{

		private int TABLE_SIZE;

		private int size;

		private Entry<KeyType, ValueType>[] table;

		private int primeSize;

		/* Constructor */

		@SuppressWarnings("unchecked")
		public DoubleHashingTable(){

			size = 0;

			TABLE_SIZE = 10;

			table = new Entry[TABLE_SIZE];

			for (int i = 0; i < TABLE_SIZE; i++)

				table[i] = null;

			primeSize = getPrime();

		}

		/*
		 * Function to get prime number less than table size for myhash2
		 * function
		 */

		public int getPrime(){

			for (int i = TABLE_SIZE - 1; i >= 1; i--)

			{

				int fact = 0;

				for (int j = 2; j*j <= i; j++)

					if (i % j == 0)

						fact++;

				if (fact == 0)
					return i;

			}

			/* Return a prime number */

			return 3;

		}

		/* Function to get number of key-value pairs */

		public int getSize(){

			return size;

		}

		/* Function to get value of a key */

		public Entry<KeyType, ValueType> get(Entry<KeyType, ValueType> entry){

			int hash1 = myhash1(entry.key);

			int hash2 = myhash2(entry.key);

			while (table[hash1] != null && !table[hash1].key.equals(entry.key))

			{

				hash1 += hash2;

				hash1 %= TABLE_SIZE;

			}

			return table[hash1];

		}

		/* Function to insert a key value pair */

		@SuppressWarnings("unchecked")
		public void insert(Entry<KeyType, ValueType> entry){

			if (size >= TABLE_SIZE*0.75){ //rehash

				Entry<KeyType, ValueType>[] tempTable = table;
				TABLE_SIZE = TABLE_SIZE*2 + 1;
				table = new Entry[TABLE_SIZE];
				primeSize = getPrime();
				for(int i = 0; i < tempTable.length; i++){
					if(tempTable[i] != null){
						int hash1 = myhash1(tempTable[i].key);
						int hash2 = myhash2(tempTable[i].key);
						
						while (table[hash1] != null){

							hash1 += hash2;
							hash1 %= TABLE_SIZE;

						}
						table[hash1] = tempTable[i];
					}
				}

			}

			int hash1 = myhash1(entry.key);
			int hash2 = myhash2(entry.key);

			while (table[hash1] != null){

				hash1 += hash2;
				hash1 %= TABLE_SIZE;

			}

			table[hash1] = entry;

			size++;

		}

		/* Function to remove a key */

		public void remove(Entry<KeyType, ValueType> entry){

			int hash1 = myhash1(entry.key);

			int hash2 = myhash2(entry.key);

			while (table[hash1] != null && !table[hash1].key.equals(entry.key))

			{

				hash1 += hash2;

				hash1 %= TABLE_SIZE;

			}

			table[hash1] = null;

			size--;

		}

		/* H1(x) = x % N */

		private int myhash1(KeyType x){

			int hashVal = x.hashCode();

			hashVal %= TABLE_SIZE;

			if (hashVal < 0)

				hashVal += TABLE_SIZE;

			return hashVal;

		}

		/* H2(x) = R - (x%R) */

		private int myhash2(KeyType x){

			int hashVal = x.hashCode();

			if (hashVal < 0)

				hashVal += TABLE_SIZE;

			return primeSize - hashVal % primeSize;

		}

	}
}