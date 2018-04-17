//*********************************************************************************
//*********************************************************************************
//Last edited:  2/16/2016
//Edited by:    Jack Kawell 
public class DynamicArrayList implements ListInterface {
	private Object[] entry;
	private int length;
	private static final int INITIAL_SIZE = 50;
        //I added this variable to keep track of the doubling so I can know the 
        //length of the list
        private int amountOfTimesDoubled = 0;
	//*****************************************************************************
	public DynamicArrayList() {
		length = 0;
		entry = new Object[INITIAL_SIZE];
	}
	//*****************************************************************************
   	public DynamicArrayList(int initialSize) {
		length = 0;
		entry = new Object[initialSize];
	}
	//*****************************************************************************
	public boolean add(Object newEntry) {
		if (isArrayFull()) 
			doubleArray();
		entry[length] = newEntry;
		length++;
		return true;
	}
	//*****************************************************************************
	public boolean add(int newPosition, Object newEntry) {
		boolean isSuccessful = true;
		if ((newPosition >= 1) && (newPosition <= length+1)) {	
			if (isArrayFull()) 
				doubleArray();
			makeRoom(newPosition);				
			entry[newPosition-1] = newEntry;
			length++;
		}
		else
			isSuccessful = false;
			
		return isSuccessful;
	}
	//*****************************************************************************
	public Object remove(int givenPosition) {
		Object result = null;
		if ((givenPosition >= 1) && (givenPosition <= length)) {
			result = entry[givenPosition-1];
			if (givenPosition < length)
				removeGap(givenPosition);
			length--;
		}
		return result;
	}
	//*****************************************************************************
	public void clear()	{
		length = 0;
	}
	//*****************************************************************************
	public boolean replace(int givenPosition, Object newEntry) {
		boolean isSuccessful = true;
		if ((givenPosition >= 1) && (givenPosition <= length))
			entry[givenPosition-1] = newEntry;
		else
			isSuccessful = false;

		return isSuccessful;
	}
	//*****************************************************************************
	public Object getEntry(int givenPosition) {
		Object result = null;
		if ((givenPosition >= 1) && (givenPosition <= length))
			result = entry[givenPosition-1];

		return result;
	}
	//*****************************************************************************
	public boolean contains(Object anEntry) {
		boolean found = false;
		for (int index = 0; !found && (index < length); index++) {
			if (anEntry.equals(entry[index]))
				found = true;
		}
		return found;
	}
	//*****************************************************************************
	public int getLength() {
		return length;
	}
        //*****************************************************************************
        //This method allows me to get the number of times the array has been doubled 
        //in size.
        public int getHowManyDoubles() {
                return amountOfTimesDoubled;
        }
	//*****************************************************************************
	public boolean isEmpty() {
		return length == 0;
	}
	//*****************************************************************************
	public boolean isFull()	{
		return false;
	}
	//*****************************************************************************
	public void display() {
		for (int index = 0; index < length; index++)
			System.out.println(entry[index]);
	}
	//*****************************************************************************
	private boolean isArrayFull() {
	  return length == entry.length;
	}
	//*****************************************************************************
	private void doubleArray() {
		Object[] oldList = entry;
		int oldSize = oldList.length;
		entry = new Object[2*oldSize];
		for (int index = 0; index < oldSize; index++)
			entry[index] = oldList[index];
                //This way I can know each time it is doubled
                amountOfTimesDoubled++;
	}
	//*****************************************************************************
	private void makeRoom(int newPosition) {
		for (int index = length; index >= newPosition; index--)
			entry[index] = entry[index-1];
	}
	//*****************************************************************************	
	private void removeGap(int givenPosition) {
		for (int index = givenPosition; index < length; index++)
			entry[index-1] = entry[index];
	}
	//*****************************************************************************
} // end DynamicArrayList
//*********************************************************************************
//*********************************************************************************