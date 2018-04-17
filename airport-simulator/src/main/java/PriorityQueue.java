//************************************************************************
//************************************************************************
public class PriorityQueue implements QueueInterface, java.io.Serializable {
	private Node firstNode;
	private Node lastNode;
	//********************************************************************
	public PriorityQueue() {
		firstNode = null;
		lastNode = null;
	}
        //********************************************************************
        //Method:       priorityEnqueue
        //Description:  Enqueues objects according to their object values in ascending
        //              order.
        //Parameters:   newEntry    -   The object to add to the queue
        //Returns:      Nothing
        //Calls:        isEmpty
        //              compareTo
        //              Node.getData
        //              Node.setNextNode
        //Globals:      firstNode
        //              lastNode
        //Notes:        Written by Jack Kawell and last modified on 5/2/16

        public void priorityEnqueue(Object newEntry){
            Node newNode = new Node(newEntry, null);
		if (isEmpty()){
			firstNode = newNode;
                        lastNode = newNode;
                }else{
                    Comparable comparableToAdd = (Comparable) newNode.getData();
                    if(comparableToAdd.compareTo((Comparable) firstNode.getData()) < 0){
                        newNode.setNextNode(firstNode);
                        firstNode = newNode;
                    }else if (comparableToAdd.compareTo((Comparable) lastNode.getData()) >= 0){
                        lastNode.setNextNode(newNode);
                        lastNode = newNode;
                    }else{
                        Node currentNode = firstNode.getNextNode();
                        Node previousNode = firstNode;
                        while(comparableToAdd.compareTo((Comparable) currentNode.getData()) >= 0){
                            previousNode = currentNode;
                            currentNode = currentNode.getNextNode();
                        }
                        newNode.setNextNode(currentNode);
                        previousNode.setNextNode(newNode);
                    }
                }
        }
	//********************************************************************
	public void enqueue(Object newEntry) {
		Node newNode = new Node(newEntry, null);
		if (isEmpty())
			firstNode = newNode;
		else
			lastNode.setNextNode(newNode);
		lastNode = newNode;
	}
	//********************************************************************
	public Object dequeue() {
		Object front = null;
		if (!isEmpty()) {
			front = firstNode.getData();
			firstNode = firstNode.getNextNode();
			if (firstNode == null)
				lastNode = null;
		}
		return front;
	}
	//********************************************************************
	public Object getFront() {
		Object front = null;
		if (!isEmpty())
			front = firstNode.getData();
		return front;
	}
	//********************************************************************
	public boolean isEmpty() {
		return firstNode == null;
	}
	//********************************************************************
	public void clear() {
		firstNode = null;
		lastNode = null;
	}
	//********************************************************************
	//********************************************************************
	private class Node {
		private Object data;
		private Node next;

		private Node(Object dataPortion) {
			data = dataPortion;
			next = null;	
		}
		
		private Node(Object dataPortion, Node nextNode) {
			data = dataPortion;
			next = nextNode;	
		}
		
		private Object getData() {
			return data;
		}
		
		private void setData(Object newData) {
			data = newData;
		}
		
		private Node getNextNode() {
			return next;
		}
		
		private void setNextNode(Node nextNode) {
			next = nextNode;
		}
	}
	//********************************************************************
	//********************************************************************
}
//************************************************************************
//************************************************************************