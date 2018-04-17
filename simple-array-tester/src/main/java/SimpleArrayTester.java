//Program:      SimpleArrayTester.java
//Course:       COSC210
//Description:  Multi function program that places user entered integers in an 
//              array and allows the user to run different functions on the data.
//Author:	Jack Kawell
//Revised:	1/28/16
//Language:     Java
//IDE:          NetBeans 8.0.2
//*******************************************************************************
//*******************************************************************************
//Class:        SimpleArrayTester
//Description:  Multi function class that places user entered integers in an 
//              array and allows the user to run different functions on the data.

public class SimpleArrayTester {
//*******************************************************************************
//Method:       main
//Description:  The main method that prints the menu list and calls each method
//              for whichever selection the user makes
//Parameters:   Default
//Returns:      None
//Calls:        addToArray
//              findLargest
//              findSmallest    
//              findClosestValue
//              findAverage
//              printArray
//Globals:   `  None
    public static void main(String[] args) {

        int[] integers = new int[50];
        int counter = 0; //This keeps track of how many values are in the array
        boolean areThereValues = false;
        boolean endProgram = false;

        KeyboardInputClass keyboardInput = new KeyboardInputClass();
        int userSelection = 0;

        while (endProgram == false) {

            System.out.println("\n1=Enter a new integer\n"
                    + "2=Find the largest value\n"
                    + "3=Find the smallest value\n"
                    + "4=Find the value closest to a user specified entry\n"
                    + "5=Find the average\n"
                    + "6=Display the values\n"
                    + "7=Exit program");

            userSelection = keyboardInput.getInteger(true, 0, 1, 7,
                    "Please select one...");

            if (userSelection == 1) {
                counter = addToArray(integers, counter);
                areThereValues = true;
            } else if (areThereValues == true) {
                switch (userSelection) {
                    case 2:
                        findLargest(integers, counter);
                        break;
                    case 3:
                        findSmallest(integers, counter);
                        break;
                    case 4:
                        int checkInt = keyboardInput.getInteger(false, 0, 0, 0,
                                "\nEnter integer to find closest value");
                        findClosestValue(integers, counter, checkInt);
                        break;
                    case 5:
                        findAverage(integers, counter);
                        break;
                    case 6:
                        printArray(integers, counter);
                        break;
                    case 7:
                        endProgram = true;
                        break;
                }
            } else if (userSelection == 7) {
                endProgram = true;
            } else {
                System.out.println("\nThere are no values yet. Please select "
                        + "1 to enter \na new integer or 7 to exit...");
            }
        }
    }
//*******************************************************************************
//Method:       addToArray
//Description:  Takes an integer from the user and stores it in the array. Also
//              keeps track of the number in of values in the array and returns  
//              it to the main method.
//Parameters:   array       The integer array to place the value in.
//              counter     The current amount of values in the array
//Returns:      counter     The new number of values in the array
//Globals:   `  None
    public static int addToArray(int[] array, int counter) {

        KeyboardInputClass keyboardInput = new KeyboardInputClass();

        int newInt = 0;
        newInt = keyboardInput.getInteger(false, 0, 0, 0,
                "\nEnter an integer to add to the array:");

        array[counter] = newInt;

        counter++;

        return counter;
    }
//*******************************************************************************
//Method:       findLargest
//Description:  Finds the largest value in the array and prints it out
//Parameters:   array     The integer array to look for the largest value in  
//              counter   The current amount of values in the array
//Returns:      None    
//Globals:   `  None
    public static void findLargest(int[] array, int counter) {
        //Example of finding biggest
        int largest = array[0];
        for (int i = 1; i < counter; i++) {
            if (array[i] > largest) {
                largest = array[i];
            }
        }
        System.out.println("\nThe largest value is: " + largest);
    }
//*******************************************************************************
//Method:       findSmallest
//Description:  Finds the smallest value in the array and prints it out
//Parameters:   array     The integer array to look for the smallest value in  
//              counter   The current amount of values in the array
//Returns:      None
//Globals:   `  None
    public static void findSmallest(int[] array, int counter) {
        //Example of finding smallest
        int smallest = array[0];
        for (int i = 1; i < counter; i++) {
            if (array[i] < smallest) {
                smallest = array[i];
            }
        }
        System.out.println("\nThe smallest value is: " + smallest);
    }
//*******************************************************************************
//Method:       findClosestValue
//Description:  Finds and prints the closest value in the array to a user 
//              specified value
//Parameters:   array     The integer array to look for the closest value in  
//              counter   The current amount of values in the array
//              checkInt  The user specified value for which it will find the 
//                        closest value
//Returns:      None
//Globals:   `  None
    public static void findClosestValue(int[] array, int counter, int checkInt) {
        int difference = Math.abs(array[0] - checkInt);
        int place = 0;
        for (int i = 1; i < counter; i++) {
            if (Math.abs(array[i] - checkInt) < difference) {
                difference = Math.abs(array[i] - checkInt);
                place = i;
            }
        }
        System.out.println("\nThe closest value is: " + array[place]);
    }
//*******************************************************************************
//Method:       findAverage
//Description:  Finds and prints the average of the values in the array
//Parameters:   array     The integer array to find the average of 
//              counter   The current amount of values in the array
//Returns:      None
//Globals:   `  None
    public static void findAverage(int[] array, int counter) {
        int sum = 0;
        for (int i = 0; i < counter; i++) {
            sum += array[i];
        }
        int average = sum / counter;
        System.out.println("\nThe average value is: " + average);
    }
//*******************************************************************************
//Method:       printArray
//Description:  Prints out the values in the array
//Parameters:   array     The integer array to print 
//              counter   The current amount of values in the array
//Returns:      None
//Globals:   `  None
    public static void printArray(int[] array, int counter) {
        System.out.print("\nThe list is: {");
        for (int i = 0; i < counter; i++) {
            System.out.print(array[i]);
            if (i != counter - 1) {
                System.out.print(", ");
            } else {
                System.out.print("}\n");
            }
        }//end of for loop
    }
//*******************************************************************************
}//end of class
