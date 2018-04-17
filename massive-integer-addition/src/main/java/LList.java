//*********************************************************************************
//*********************************************************************************
public class LList implements ListInterface {
	private Node firstNode;
        private Node lastNode; //Use this below to be more effecient
	private int  length;
	//*****************************************************************************	
        //Constructor for the chain list. We want it to be empty so we call clear().
	public LList() {
		clear();
	}
	//*****************************************************************************	
        //newEntry is the data we want to put in the list (adds at the end [see ListInterface])
	public boolean add(Object newEntry)	{
                //Calls the first constructor from private class Node
		Node newNode = new Node(newEntry);
		if (isEmpty())
			firstNode = newNode;
		else {
			Node lastNode = getNodeAt(length);//Look through the whole list to find the last one (inefficient)
                                                          //We can now use the lastNode from above
			lastNode.next = newNode;
		}
		length++;
		return true;
	}
	//*****************************************************************************
	public boolean add(int newPosition, Object newEntry) {
		boolean isSuccessful = true;
		if ((newPosition >= 1) && (newPosition <= length+1)) {
			Node newNode = new Node(newEntry);

			if (isEmpty() || (newPosition == 1)) {
                                //These next two lines must be in this order or you lose the whole chain
				newNode.next = firstNode;
				firstNode = newNode;
			}
			else {
				Node nodeBefore = getNodeAt(newPosition-1);
				Node nodeAfter = nodeBefore.next;
                                //These next two lines must be in this order or you lose the whole chain                                
				newNode.next = nodeAfter;
				nodeBefore.next = newNode;
			}
			length++;
		}
		else
			isSuccessful = false;

		return isSuccessful;
	}
	//*****************************************************************************	
	public Object remove(int givenPosition)	{
                //The object we are returning is the data NOT the node
		Object result = null;

		if (!isEmpty() && (givenPosition >= 1) && (givenPosition <= length)) {
			if (givenPosition == 1)	{
				result = firstNode.data;
				firstNode = firstNode.next;
			}
			else {
				Node nodeBefore = getNodeAt(givenPosition-1);
				Node nodeToRemove = nodeBefore.next;
				Node nodeAfter = nodeToRemove.next;
				nodeBefore.next = nodeAfter;
				result = nodeToRemove.data;
			}

			length--;
		}

		return result;
	}
	//*****************************************************************************	
	public final void clear() {
		firstNode = null;
		length = 0;
	}
	//*****************************************************************************
	public boolean replace(int givenPosition, Object newEntry) {
		boolean isSuccessful = true;

		if (!isEmpty() && (givenPosition >= 1) && (givenPosition <= length)) {
			Node desiredNode = getNodeAt(givenPosition);
			desiredNode.data = newEntry;
		} 
		else
			isSuccessful = false;

		return isSuccessful;
	}
	//*****************************************************************************
	public Object getEntry(int givenPosition) {
		Object result = null;

		if (!isEmpty() && (givenPosition >= 1) && (givenPosition <= length))
			result = getNodeAt(givenPosition).data;

		return result;
	}
	//*****************************************************************************
	public boolean contains(Object anEntry) {
		boolean found = false;
		Node currentNode = firstNode;
		while (!found && (currentNode != null)) {
			if (anEntry.equals(currentNode.data))
				found = true;
			else
				currentNode = currentNode.next;
		}

		return found;
	}
	//*****************************************************************************
	public int getLength() {
		return length;
	}
	//*****************************************************************************
	public boolean isEmpty() {
		return length == 0;
	}
	//*****************************************************************************	
	public boolean isFull() {
		return false;
	}
	//*****************************************************************************	
	public void display() {
		for (Node current = firstNode; current != null; current = current.next)
			System.out.println(current.data);
	}
	//*****************************************************************************
	private Node getNodeAt(int givenPosition) {
                //Start at the first position
		Node currentNode = firstNode;
                //Basically this repeats currentNode.next.next.next... until you get to the one you need
		for (int counter = 1; counter < givenPosition; counter++)
			currentNode = currentNode.next;

		return currentNode;
	}
	//*****************************************************************************
	//*****************************************************************************
        //This class puts the object we are storing inside the node which is also an object
        //So then we have an object stored inside an object stored inside a list
        //This class is private so the user's program can't change the nodes
	private class Node {
		private Object data;//The object data we are storing
		private Node   next;//The storage location of the next node
		//*************************************************************************
                //This constructor has no next node to reference
		private Node(Object dataPortion) {
			data = dataPortion;
			next = null;
		}
		//*************************************************************************
                //This constructor has a reference to the next node)
		private Node(Object dataPortion, Node nextNode) {
			data = dataPortion;
			next = nextNode;
		}
		//*************************************************************************
	} // end Node
	//*****************************************************************************
	//*****************************************************************************
} // end LList
//*********************************************************************************
//*********************************************************************************