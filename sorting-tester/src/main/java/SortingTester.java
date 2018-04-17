//Program:      SortingTester.java
//Course:       COSC210
//Description:  This is a program that creates a random array of objects that contain an
//              integer, a double, and a string value. It then asks the user for how they
//              would like to sort the array; over the integers, doubles, or strings.
//              It then sorts the array in six different ways (quick, merge, iterative 
//              selection, iterative bubble, iterative insertion, and shell), prints 
//              the sorted and unsorted array if the user chooses to, and shows the computation
//              time of each sort.
//Author:       Jack Kawell
//Revised:      3/16/16
//Language:     Java
//IDE:          NetBeans 8.0.2
//*******************************************************************************
//*******************************************************************************
//Class:        Kawell3
//Description:  Main class of the program that contains all the methods required and
//              calls KeyboardInputClass, SortArrayClass, and the DataClass object.

public class SortingTester {

    private static KeyboardInputClass keyboardInput;
    private static DataClass[] arrayOriginal;
    private static int sizeOfArray;
    private static int sortField;
//*******************************************************************************
//Method:       main
//Description:  The main method that asks the user for a sort field and whether they
//              would like to show the sorted and unsorted arrays. It also calls the
//              methods to build and sort and keeps running until the user chooses to quit
//Parameters:   Default
//Returns:      None
//Calls:        KeyboardInputClass
//              buildArray
//              keyboardInput.getInteger
//              collectSortedAndPrint
//              keyboardInput.getCharacter
//Globals:      keyboardInput
//              sortField
    
    public static void main(String[] args) {

        keyboardInput = new KeyboardInputClass();

        char quit = 'N';

        while (quit == 'N') {

            buildArray();
            
            //SortArrayClass.quickSort(arrayOriginal, sizeOfArray);

            sortField = keyboardInput.getInteger(true, 1, 1, 3,
                    "Select a sort field: 1=iCode; 2=dCode; 3=sCode (default = 1):");

            char showUnsorted = keyboardInput.getCharacter(true, 'N', "YN", 1,
                    "Show the unsorted arrays? (Y/N; default = N):");

            char showSorted = keyboardInput.getCharacter(true, 'N', "YN", 1,
                    "Show the sorted arrays? (Y/N; default = N):");

            System.out.println("");

            collectSortedAndPrint(showUnsorted, showSorted);

            quit = keyboardInput.getCharacter(true, 'Y', "YN", 1,
                    "Would you like to quit? (Y/N; default = Y):");
        }
    }
//*******************************************************************************
//Method:       buildArray
//Description:  This method builds an array of random DataClass objects.    
//Parameters:   None
//Returns:      None
//Calls:        keyboardInput.getInteger
//              DataClass                       
//Globals:      sizeOfArray
//              keyboardInput
//              arrayOriginal
//              arrayOriginal.length
    
    private static void buildArray() {
        sizeOfArray = keyboardInput.getInteger(true, 100, 0, Integer.MAX_VALUE,
                "Specify the # of objects to be created and sorted (default = 100):");

        System.out.println("Creating array of objects with random data...");

        arrayOriginal = new DataClass[sizeOfArray];

        for (int position = 0; position < arrayOriginal.length; position++) {
            DataClass newObject = new DataClass();
            arrayOriginal[position] = newObject;
        }
    }
//*******************************************************************************
//Method:       collectSortedAndPrint
//Description:  This method calls the methods required to sort and print the array           
//Parameters:   showUnsorted - These are the values the user gave to decide whether to print or not
//              showSorted
//Returns:      None
//Calls:        sortArrays
//              printResults                          
//Globals:      None             

    private static void collectSortedAndPrint(char showUnsorted, char showSorted) {
        for (int i = 1; i <= 6; i++) {
            DataClass[] sortedArray = sortArrays(i);
            printResults(sortedArray, showUnsorted, showSorted);
        }
    }
//*******************************************************************************
//Method:       sortArrays
//Description:  This method calls and times the individual sorting methods. It prints
//              the name and time taken for each sort and returns the sorted array as
//              a DataClass array.
//Parameters:   whichSort - This keeps track of which sorting algorithm to call so that
//                          the switch cycles through each algorithm
//Returns:      An unnamed DataClass array
//Calls:        buildComparableArray - This builds a comparable array to sort
//              SortArrayClass.quickSort
//              SortArrayClass.mergeSort
//              SortArrayClass.iterativeSelectionSort
//              SortArrayClass.iterativeBubbleSort
//              SortArrayClass.iterativeInsertionSort
//              SortArrayClass.shellSort
//              buildDataClassSortedArray
//Globals:      sizeOfArray
    
    private static DataClass[] sortArrays(int whichSort) {
        DataClass[] arrayToSort = new DataClass[sizeOfArray];//The comparable array that will be a copy of the random array

        //build the comparable array that is a copy of the original array; the comparable array will be sorted
        //and the original array will be left alone
        arrayToSort = buildArrayToCompare(arrayToSort);

        long beginningTime = System.currentTimeMillis();
        switch (whichSort) {
            case 1://quick sort
                System.out.print("Quick sort: ");
                SortArrayClass.quickSort(arrayToSort, sizeOfArray);
                break;
            case 2://merge sort
                System.out.print("Merge sort: ");
                SortArrayClass.mergeSort(arrayToSort, sizeOfArray);
                break;
            case 3://iterative selection sort
                System.out.print("Iterative selection sort: ");
                SortArrayClass.iterativeSelectionSort(arrayToSort, sizeOfArray);
                break;
            case 4://iterative bubble sort
                System.out.print("Iterative bubble sort: ");
                SortArrayClass.iterativeBubbleSort(arrayToSort, sizeOfArray);
                break;
            case 5://iterative insertion sort
                System.out.print("Iterative insertion sort: ");
                SortArrayClass.iterativeInsertionSort(arrayToSort, sizeOfArray);
                break;
            case 6://shell sort
                System.out.print("Shell sort: ");
                SortArrayClass.shellSort(arrayToSort, sizeOfArray);
                break;
        }
        long endingTime = System.currentTimeMillis();
        long totalTime = endingTime - beginningTime;
        System.out.print("Elapsed time = " + totalTime + "ms\n");

        //build and return the DataClass sorted array
        //return buildDataClassSortedArray(arrayToSort, sortedArray);
        return arrayToSort;
    }
//*******************************************************************************
//Method:       buildArrayToCompare
//Description:  This method copies the values of the original DataClass array into
//              a duplicate array to sort.
//Parameters:   arrayToSort - The array to copy the data into
//Returns:      arrayToSort
//Calls:        None                    
//Globals:      arrayOriginal.length
//              arrayOriginal
    
    private static DataClass[] buildArrayToCompare(DataClass[] arrayToSort) {

        for (int i = 0; i < arrayOriginal.length; i++) {
            arrayToSort[i] = arrayOriginal[i];
        }
        return arrayToSort;
    }
//*******************************************************************************
//Method:       printResults
//Description:  This method checks to see if the user chose to print the sorted and
//              unsorted arrays earlier and then prints them if requested.
//Parameters:   sortedArray - The sorted array of DataClass objects
//              showUnsorted - The user choice of whether or not to show the unsorted array
//              showSorted - The user choice of whether or not to show the sorted array
//Returns:      None
//Calls:        keyboardInput.getString
//              printArray                           
//Globals:      keyboardInput
//              arrayOriginal

    private static void printResults(DataClass[] sortedArray, char showUnsorted, char showSorted) {
        if (showUnsorted == 'Y') {
            System.out.println("");
            keyboardInput.getString("", "Press ENTER to Continue...");
            System.out.println("Unsorted data:\n"
                    + "String---Integer---Double");
            printArray(arrayOriginal);
        }

        System.out.println("");

        if (showSorted == 'Y') {
            keyboardInput.getString("", "Press ENTER to Continue...");
            System.out.println("Sorted data:\n"
                    + "String---Integer---Double");
            printArray(sortedArray);
            System.out.println("");
            keyboardInput.getString("", "Press ENTER to Continue...");
        }
    }
//*******************************************************************************
//Method:       printArray
//Description:  This method iterates through an array of DataClass objects and prints 
//              the values of each object parameter; int, double, String.
//Parameters:   array - this is a DataClass array
//Returns:      None
//Calls:        currentObject.getsCode
//              currentObject.getiCode
//              currentObject.getdCode
//Globals:      array.length

    private static void printArray(DataClass[] array) {

        for (int position = 0; position < array.length; position++) {
            DataClass currentObject = array[position];
            System.out.println(currentObject.getsCode() + "; "
                    + currentObject.getiCode() + "; "
                    + currentObject.getdCode());
        }
    }
//*******************************************************************************
//Method:       getSortField
//Description:  This method returns the global sortField. This is needed for the 
//              compareTo method of DataClass to be able to know which parameter
//              to compare for the sort algorithms.
//Parameters:   None
//Returns:      sortField
//Calls:        None                    
//Globals:      sortField           

    public static int getSortField() {
        return sortField;
    }
    
}//end of class
//*******************************************************************************
//*******************************************************************************
