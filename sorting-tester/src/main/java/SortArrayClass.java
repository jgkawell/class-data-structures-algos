//********************************************************************************
//********************************************************************************
//Class for sorting array of Comparable objects from smallest to largest. Each sort
//routine sorts the first n object in the array in ascending order.
public class SortArrayClass {
	public static final int MIN_SIZE = 5; // for quick sort
	//********************************************************************************
	public static void iterativeSelectionSort(Comparable[] a, int n) {
		for (int index = 0; index < n - 1; index++) {
			int indexOfNextSmallest = indexOfSmallest(a, index, n - 1);
			swap(a, index, indexOfNextSmallest);
			// Assertion: a[0] <= a[1] <= . . . <= a[index] <= all other a[i]
		}
	}
	//********************************************************************************
	//determine index of the smallest value in an array.
	private static int indexOfSmallest(Comparable[] a, int first, int last) {
		Comparable min = a[first];
		int indexOfMin = first;
		for (int index = first + 1; index <= last; index++) {
			if (a[index].compareTo(min) < 0) {
				min = a[index];
				indexOfMin = index;
			}
		}
		return indexOfMin;
	}
	//********************************************************************************
	//swap the array elements a[i] and a[j].
	private static void swap(Comparable[] a, int i, int j) {
		Comparable temp = a[i];
		a[i] = a[j];
		a[j] = temp;
	}
	//********************************************************************************
	public static void recursiveSelectionSort(Comparable[] a, int n) {
		recursiveSelectionSort(a, 0, n - 1);
	}
	//********************************************************************************
	public static void recursiveSelectionSort(Comparable[] array, int first, int last) {
		if (first < last) {  // place smallest value at beginning of array:
			int indexOfNextSmallest = indexOfSmallest(array, first, last);
			swap(array, first, indexOfNextSmallest);
			recursiveSelectionSort(array, first + 1, last);
		}
	}
	//********************************************************************************
	//********************************************************************************
	//********************************************************************************
	public static void alternativeRecursiveSelectionSort(Comparable[] a, int n) {
		recursiveSelectionSort(a, 0, n - 1);
	}
	//********************************************************************************
	public static void alternativeRecursiveSelectionSort(Comparable[] array, int first, int last) {
		if (first < last) {
			int indexOfNextSmallest = recursiveIndexOfSmallest(array, first, last, array[first], first);
			swap(array, first, indexOfNextSmallest);
			alternativeRecursiveSelectionSort(array, first + 1, last);
		}
	}
	//********************************************************************************
	//determine index of the smallest value in an array.
	private static int recursiveIndexOfSmallest(Comparable[] a, int index, int last, Comparable min, int indexOfMin) {
        if (index < last) {
            if (a[index].compareTo(min) < 0) {
                min = a[index];
                indexOfMin = index;
            }
            indexOfMin = recursiveIndexOfSmallest(a, index+1, last, min, indexOfMin);
        }
		return indexOfMin;
	}
	//********************************************************************************
	//********************************************************************************
	//********************************************************************************
        public static void iterativeInsertionSort(Comparable[] array, int n) {
		iterativeInsertionSort(array, 0, n - 1);
	}
	//********************************************************************************
	public static void iterativeInsertionSort(Comparable[] array, int first, int last) {
		int unsorted;
		for (unsorted = first + 1; unsorted <= last; unsorted++) {
			Comparable firstUnsorted = array[unsorted];
			insertInOrder(firstUnsorted, array, first, unsorted - 1);
		}
	}
	//********************************************************************************
	private static void insertInOrder(Comparable element, Comparable[] array, int begin, int end) {
		int index = end;
		while ((index >= begin) && (element.compareTo(array[index]) < 0)) {
			array[index + 1] = array[index]; // make room
			index--;
		}
		array[index + 1] = element;
	}
	//********************************************************************************
	public static void recursiveInsertionSort(Comparable[] array, int n) {
		recursiveInsertionSort(array, 0, n - 1);
	}
	//********************************************************************************
	public static void recursiveInsertionSort(Comparable[] a, int first, int last) {
		if (first < last) {
			recursiveInsertionSort(a, first, last - 1);
			recursiveInsertInOrder(a[last], a, first, last - 1);
		}
	}
	//********************************************************************************
	private static void recursiveInsertInOrder(Comparable element, Comparable[] array, int first, int last) {
		if (element.compareTo(array[last]) >= 0)
			array[last + 1] = element;
		else if (first < last) {
			array[last + 1] = array[last];
			recursiveInsertInOrder(element, array, first, last - 1);
		}
		else {
			array[last + 1] = array[last];
			array[last] = element;
		}
	}
	//********************************************************************************
	public static void shellSort(Comparable[] array, int n) {
		shellSort(array, 0, n - 1);
	}
	//********************************************************************************
	public static void shellSort(Comparable[] array, int first, int last) {
		int n = last - first + 1;
		for (int space = n / 2; space > 0; space = space / 2) {
			for (int begin = first; begin < first + space; begin++)
				incrementalInsertionSort(array, begin, last, space);
		}
	}
	//********************************************************************************
	public static void shellSortNoEven(Comparable[] array, int first, int last) {	//avoid even spacing
		int n = last - first + 1; // number of array elements
		for (int space = n / 2; space > 0; space = space / 2) {
			if (space % 2 == 0) // if space is even, add 1
				space++;
			for (int begin = first; begin < first + space; begin++)
				incrementalInsertionSort(array, begin, last, space);
		}
	}
	//********************************************************************************
	//sort equally-spaced elements of an array into ascending order.
	private static void incrementalInsertionSort(Comparable[] array, int first, int last, int space) {
		int unsorted, index;
		for (unsorted = first + space; unsorted <= last; unsorted = unsorted + space) {
			Comparable firstUnsorted = array[unsorted];
			for (index = unsorted - space; (index >= first) && (firstUnsorted.compareTo(array[index]) < 0); index = index - space) {
				array[index + space] = array[index];
			}
			array[index + space] = firstUnsorted;
		}
	}
	//********************************************************************************
	public static void iterativeBubbleSort(Comparable[] array, int n) {
		for (int lastIndex = n - 1; lastIndex > 0; lastIndex--) {
			for (int index = 0; index < lastIndex; index++)
				order(array, index, index + 1);
		}
	}
	//********************************************************************************
	//order two given array elements into ascending order so a[i] <= a[j]
	private static void order(Comparable[] a, int i, int j) {
		if (a[i].compareTo(a[j]) > 0)
			swap(a, i, j);
	}
	//********************************************************************************
	public static void recursiveBubbleSort(Comparable[] array, int n) {
		if (n > 1) {
			for (int index = 0; index < n - 1; index++)
				order(array, index, index + 1);
			recursiveBubbleSort(array, n - 1);
		}
	}
	//********************************************************************************
	public static void mergeSort(Comparable[] array, int n) {
		mergeSort(array, 0, n - 1);
	}
	//********************************************************************************
	public static void mergeSort(Comparable[] array, int first, int last) {
		if (first < last) {								//sort each half
			int mid = (first + last) / 2;					//index of midpoint
			mergeSort(array, first, mid);				//sort left half array[first..mid]
			mergeSort(array, mid + 1, last);				//sort right half array[mid+1..last]
			if (array[mid].compareTo(array[mid + 1]) > 0)
				merge(array, first, mid, last);			//merge the two halves
		}
	}
	//********************************************************************************
	private static void merge(Comparable[] array, int first, int mid, int last) {	// iterative merge
		//two adjacent subarrays are array[beginHalf1..endHalf1] and array[beginHalf2..endHalf2]
		int beginHalf1 = first;
		int endHalf1 = mid;
		int beginHalf2 = mid + 1;
		int endHalf2 = last;
		Comparable[] tempArray = new Comparable[array.length];
		// while both subarrays are not empty, copy the smaller item into the temporary array
		int index = beginHalf1;    // next available location in tempArray
		for (; (beginHalf1 <= endHalf1) && (beginHalf2 <= endHalf2); index++) {
			if (array[beginHalf1].compareTo(array[beginHalf2]) < 0) {
				tempArray[index] = array[beginHalf1];
				beginHalf1++;
			}
			else {
				tempArray[index] = array[beginHalf2];
				beginHalf2++;
			}
		}
		//finish off arrays if necessary
		for (; beginHalf1 <= endHalf1; beginHalf1++, index++)
			tempArray[index] = array[beginHalf1];
		for (; beginHalf2 <= endHalf2; beginHalf2++, index++)
			tempArray[index] = array[beginHalf2];
		// copy result back into the original array
		for (index = first; index <= last; index++)
			array[index] = tempArray[index];
	}
	//********************************************************************************
	public static void quickSort(Comparable[] array, int n) {
		quickSort(array, 0, n - 1);
	}
	//********************************************************************************
	//uses median-of-three pivot selection for arrays larger than MIN_SIZE elements;
	//uses insertion sort for other arrays
	public static void quickSort(Comparable[] array, int first, int last) {
		if (last - first + 1 < MIN_SIZE)
			iterativeInsertionSort(array, first, last);
		else {
			int pivotIndex = partition(array, first, last);
			quickSort(array, first, pivotIndex - 1);
			quickSort(array, pivotIndex + 1, last);
		}
	}
	//********************************************************************************
	private static void sortFirstMiddleLast(Comparable[] a, int first, int mid, int last) {
		order(a, first, mid); // make a[first] <= a[mid]
		order(a, mid, last);  // make a[mid] <= a[last]
		order(a, first, mid); // make a[first] <= a[mid]
	}
	//********************************************************************************
	private static int partition(Comparable[] a, int first, int last) {
		int mid = (first + last) / 2;
		sortFirstMiddleLast(a, first, mid, last);
		//note: pivot is a[mid]; a[first] <= pivot and a[last] >= pivot, so do not
		//compare these two array elements with pivot...
		// move pivot to next-to-last position in array
		swap(a, mid, last - 1);
		int pivotIndex = last - 1;
		Comparable pivot = a[pivotIndex];
		int indexFromLeft = first + 1;
		int indexFromRight = last - 2;
		boolean done = false;
		while (!done) {
			// starting at beginning of array, leave elements that are < pivot; 
			// locate first element that is >= pivot; there will be one,
			// since last element is >= pivot
			while (a[indexFromLeft].compareTo(pivot) < 0)
				indexFromLeft++;
			// starting at end of array, leave elements that are > pivot; 
			// locate first element that is <= pivot; ther will be one, 
			// since first element is <= pivot
			while (a[indexFromRight].compareTo(pivot) > 0)
				indexFromRight--;

			//now a[indexFromLeft] >= pivot and a[indexFromRight] <= pivot
			if (indexFromLeft < indexFromRight) {
				swap(a, indexFromLeft, indexFromRight);
				indexFromLeft++;
				indexFromRight--;
			}
			else
				done = true;
		}
		swap(a, pivotIndex, indexFromLeft);
		pivotIndex = indexFromLeft;
		return pivotIndex;
	}
	//********************************************************************************
	public static void heapSort(Comparable[] array, int n) {
		for (int index = n / 2; index >= 0; index--)	// create heap
			reheap(array, index, n - 1);
		swap(array, 0, n - 1);
		for (int last = n - 2; last > 0; last--) {
			reheap(array, 0, last);
			swap(array, 0, last);
		}
	}
	//********************************************************************************
	private static void reheap(Comparable[] heap, int first, int last) {
		boolean done = false;
		Comparable orphan = heap[first];
		int largerChildIndex = 2 * first; // index of left child, if any
		while (!done && (largerChildIndex <= last)) {
			int rightChildIndex = largerChildIndex + 1;
			if ((rightChildIndex <= last) &&
				  heap[rightChildIndex].compareTo(heap[largerChildIndex]) > 0) {
				largerChildIndex = rightChildIndex;
			}
			if (orphan.compareTo(heap[largerChildIndex]) < 0) {
				heap[first] = heap[largerChildIndex];

				first = largerChildIndex;
				largerChildIndex = 2 * first; // index of next left child
			}
			else
				done = true;
		}
		heap[first] = orphan;
	}
	//********************************************************************************
} // end SortArray
//************************************************************************************
//************************************************************************************