//Program:      RecursiveImageManipulator.java
//Course:       COSC210
//Description:  This is a program that reads a specified text file into a 2D character array.
//              It then allows the user to change a specified character into another character
//              and then recursively checks the surrounding characters to see if they match
//              the original character and changes them as well if they match.
//Author:       Jack Kawell.
//Revised:      3/1/16
//Language:     Java
//IDE:          NetBeans 8.0.2
//*******************************************************************************
//*******************************************************************************
//Class:        RecursiveImageManipulator
//Description:  Main class of the program that contains all the methods required and
//              calls KeyboardInputClass and TextFileClass.

public class RecursiveImageManipulator {

    static char[][] charArray;//the character array containing the image
    static String[] stringArray;//the string array containing the image
    static int numRows;//the # of rows in the image not including the numbers before the image
    static int numColumns;//the # of columns in the image not including the numbers before the image
    static int userNeighborhood;//the neighborhood (4 or 8) chosen by the user
    static KeyboardInputClass keyboardInput;

//*******************************************************************************
//Method:       main
//Description:  The main method that calls all the others. It also prints out the
//              number of columns and rows.
//Parameters:   None
//Returns:      None
//Calls:        KeyboardInputClass
//              initializeStringArray
//              buildCharArray
//              printImage
//              keyboardInput.getInteger
//              getChar
//              getRow
//              getColumn
//              getNeighborhood
//              neighborhoodSearchAndReplace
//Globals:      keyboardInput
//              numColumns
//              numRows
//              charArray
    public static void main(String[] args) {
        keyboardInput = new KeyboardInputClass();

        //read file and initialize arrays
        initializeArrays();

        System.out.println("The number of columns is: " + numColumns
                + "\nThe number of rows is: " + numRows
                + "\n----------------------\n");

        //build and print the 2D character array
        buildCharArray();
        printImage();

        int endProgram = 1;

        while (endProgram == 1) {
            endProgram = keyboardInput.getInteger(true, 1, 0, 1,
                    "Would you like to continue?\n"
                    + "0) No\n1) Yes");
            if (endProgram == 1) {
                char userChar = getChar();
                int userRow = getRow();
                int userColumn = getColumn();
                char charToFind = charArray[userColumn][userRow];
                if (Character.compare(userChar, charToFind) != 0) {
                    userNeighborhood = 0;
                    getNeighborhood();

                    //Search and replace all adjacent mathches
                    neighborhoodSearchAndReplace(userColumn, userRow, userChar, charToFind);
                    //Print new image
                    printImage();
                } else {
                    System.out.println("\'" + userChar + "\' is already at position ("
                            + userRow + ", " + userColumn + ").");
                }
            }
        }
    }
    //*******************************************************************************
    //Method:       initializeArrays
    //Description:  Initializes the string and character arrays that contains the image
    //              using TextFileClass. It also assigns values to numColumns and numRows.
    //Parameters:   None
    //Returns:      None
    //Calls:        textFile.TextFileClass
    //              textFile.getFileName
    //              textFile.fileName.length
    //              textFile.getFileContents
    //Globals:      stringArray
    //              textFile.text
    //              textFile.fileName
    //              numColumns
    //              numRows
    //              charArray

    private static void initializeArrays() {

        TextFileClass textFile = new TextFileClass();

        textFile.getFileName("Specify the text file to be read:");
        stringArray = textFile.text;

        //Check to make sure that the user has entered a file name
        if (textFile.fileName.length() > 0) {
            textFile.getFileContents();
        }

        numRows = Integer.parseInt(stringArray[0]);
        numColumns = Integer.parseInt(stringArray[1]);

        //Initialize with sizes
        charArray = new char[numColumns][numRows];
    }

//*******************************************************************************
//Method:       buildCharArray
//Description:  Strips out the characters from the strings in stringArray and assigns
//              them to positions in charArray.
//Parameters:   None
//Returns:      None
//Calls:        None
//Globals:      numRows
//              stringArray
//              numColumns
//              charArray
    private static void buildCharArray() {
        //This is to change the string array into a 2D character array
        //We start at index 2 so that we skip the first two lines that hold the
        //values of the # of rows and # of columns
        for (int i = 2; i < numRows + 2; i++) {
            String currentString = stringArray[i];
            for (int j = 0; j < numColumns; j++) {
                char currentCharacter = currentString.charAt(j);
                charArray[j][i - 2] = currentCharacter;
            }
        }//end for loop
    }

//*******************************************************************************
//Method:       printImage
//Description:  Prints the image from charArray
//Parameters:   None
//Returns:      None
//Calls:        None
//Globals:      numRows
//              numColumns
//              charArray
    private static void printImage() {
        //This is to print it out
        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j < numColumns; j++) {
                System.out.print(charArray[j][i]);
            }
            System.out.println("");
        }//end for loop
    }

//*******************************************************************************
//Method:       getChar
//Description:  Gets an integer from the user and assigns its ASCII value to a
//              character. This character is what characters in the image will be
//              changed to.
//Parameters:   None
//Returns:      userChar
//Calls:        keyboardInput.getInteger
//Globals:      keyboardInput
    private static char getChar() {
        char userChar = (char) keyboardInput.getInteger(true, 0, 0, 255,
                "Enter the ACII code for the character:");
        System.out.println("You have entered: " + userChar);
        return userChar;
    }

//*******************************************************************************
//Method:       getColumn
//Description:  Gets the column position from the user.
//Parameters:   None
//Returns:      userColumn
//Calls:        keyboardInput.getInteger
//Globals:      keyboardInput
//              charArray.length
    private static int getColumn() {
        int userColumn = keyboardInput.getInteger(true, 0, 0, charArray.length - 1,
                "Enter the column:");
        return userColumn;
    }

//*******************************************************************************
//Method:       getRow
//Description:  Gets the row position from the user.
//Parameters:   None
//Returns:      userRow
//Calls:        keyboardInput.getInteger
//Globals:      keyboardInput
//              charArray[0].length
    private static int getRow() {
        int userRow = keyboardInput.getInteger(true, 0, 0, charArray[0].length - 1,
                "Enter the row:");
        return userRow;
    }

//*******************************************************************************
//Method:       getNeighborhood
//Description:  Gets the neighborhood from the user.
//Parameters:   None
//Returns:      None
//Calls:        keyboardInput.getInteger
//Globals:      userNeighborhood
//              keyboardInput
    private static void getNeighborhood() {
        while (userNeighborhood != 4 && userNeighborhood != 8) {
            userNeighborhood = keyboardInput.getInteger(true, 4, 4, 8,
                    "Enter the neighborhood (4 or 8):");
        }
    }

//*******************************************************************************
//Method:       neighborhoodSearchAndReplace
//Description:  This is the recursive element of the program and does most of the work.
//              It checks if the position is valid, checks if the character value matches,
//              and then reassigns the value and checks in another adjacent location if
//              it does. It repeats these steps as necessary (calling itself to do so)
//              until it has checked every valid location.
//Parameters:   columnCurrent       -       the current column that the algorithm is checking
//              rowCurrent          -       the current row that the algorithm is checking
//              userChar            -       the user entered character that the current character
//                                          will be changed to if it checks out
//              charToFind          -       the character to check if it matches
//Returns:      None
//Calls:        neighborhoodSearchAndReplace
//Globals:      charArray.length
//              charArray[0].length
//              charArray
//              userNeighborhood
    private static void neighborhoodSearchAndReplace(int columnCurrent, int rowCurrent, char userChar, char charToFind) {
        //first check to make sure the position is within bounds
        if ((columnCurrent >= 0 && columnCurrent < charArray.length) && (rowCurrent >= 0 && rowCurrent < charArray[0].length)) {

            //pull and save the current character
            char currentChar = charArray[columnCurrent][rowCurrent];

            //now check to see if the character matches
            if (Character.compare(charToFind, currentChar) == 0) {

                //change the current character to what the user entered
                charArray[columnCurrent][rowCurrent] = userChar;

                //UP
                neighborhoodSearchAndReplace(columnCurrent, rowCurrent - 1, userChar, charToFind);
                //RIGHT
                neighborhoodSearchAndReplace(columnCurrent + 1, rowCurrent, userChar, charToFind);
                //DOWN
                neighborhoodSearchAndReplace(columnCurrent, rowCurrent + 1, userChar, charToFind);
                //LEFT
                neighborhoodSearchAndReplace(columnCurrent - 1, rowCurrent, userChar, charToFind);

                if (userNeighborhood == 8) {
                    //UP RIGHT
                    neighborhoodSearchAndReplace(columnCurrent + 1, rowCurrent - 1, userChar, charToFind);
                    //DOWN RIGHT
                    neighborhoodSearchAndReplace(columnCurrent + 1, rowCurrent + 1, userChar, charToFind);
                    //DOWN LEFT
                    neighborhoodSearchAndReplace(columnCurrent - 1, rowCurrent + 1, userChar, charToFind);
                    //UP LEFT
                    neighborhoodSearchAndReplace(columnCurrent - 1, rowCurrent - 1, userChar, charToFind);
                }
            }
        }
    }
//*******************************************************************************
}//end of class
