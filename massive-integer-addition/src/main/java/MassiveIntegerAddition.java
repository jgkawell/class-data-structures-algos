//Program:      MassiveIntegerAddition.java
//Course:       COSC210
//Description:  This program takes two integers of any length and adds them together.
//              It does this by taking in integers as strings and converting them
//              to single numeral ints placed in a linked list. It then adds the 
//              values from the two addend lists into another linked list. It also
//              prints out the two user entered addends and the resulting sum.
//Author:       Jack Kawell
//Revised:      4/6/16
//Language:     Java
//IDE:          NetBeans 8.0.2
//*******************************************************************************
//*******************************************************************************
//Class:        MassiveIntegerAddition
//Description:  Main class of the program that contains all the methods required and
//              calls KeyboardInputClass, ListInterface, and LList.

public class MassiveIntegerAddition {

    private static KeyboardInputClass keyboardInput;
    private static LList addend1;
    private static LList addend2;
    private static LList sum;

//*******************************************************************************
//Method:       main
//Description:  Main method that calls all other relavant methods. Also prints 
//              the output for the user.
//Parameters:   Default
//Returns:      None
//Calls:        KeyboardInputClass
//              getAddend
//              printList        
//              computeSum
//              keyboardInput.getCharacter
//Globals:      keyboardInput
//              addend1
//              addend2
//              sum
    
    public static void main(String[] args) {
        keyboardInput = new KeyboardInputClass();

        char quit = 'N';
        
        while (quit == 'N') {

            addend1 = getAddend();
            addend2 = getAddend();
            sum = new LList();

            System.out.println("The first addend is:");
            printList(addend1, true);
            System.out.println("The second addend is:");
            printList(addend2, true);

            computeSum();

            System.out.println("The sum is:");
            printList(sum, false);

            quit = keyboardInput.getCharacter(true, 'Y', "YN", 1,
                    "Would you like to quit? (Y/N; default = Y):");
        }//end of while loop
    }
//*******************************************************************************
//Method:       getAddend
//Description:  Asks the user for a string of integers and then converts them into 
//              ints and places them in an LList for an addend.
//Parameters:   None
//Returns:      addend      The created LList addend populated with ints
//Calls:        LList
//              keyboardInput.getString
//              userString.length     
//              userString.charAt
//              addend.add
//Globals:      keyboardInput             

    public static LList getAddend() {
        LList addend = new LList();

        String prompt = "Enter an integer of any length:";

        String userString = keyboardInput.getString("0", prompt);

        boolean nonNumberInString = false;//this is a trigger to see if one of the
                                          //values was not a numberr

        for (int index = 0; index < userString.length(); index++) {
            char userChar = userString.charAt(index);
            if (userChar >= 48 && userChar <= 57) {
                int intAtIndex = userChar - 48;
                addend.add(intAtIndex);
            } else {
                nonNumberInString = true;
            }
        }//end of for loop

        if (nonNumberInString) {
            System.out.println("\nSome value(s) in the input were not numbers."
                    + "\nThey were ommitted from the addend.\n");
        }//end of if

        return addend;
    }
//*******************************************************************************
//Method:       printList
//Description:  Takes in an LList and then prints it. Also checks to see if it is
//              the sum or an addend and chooses to print in forward or reverse order.
//Parameters:   list        the sum or addend
//              direction   a boolean designating the direction to print (forward or reverse)
//Returns:      None
//Calls:        list.getLength
//              list.getEntry                           
//Globals:      None    

    public static void printList(LList list, boolean direction) {

        //The boolean direction decides whether to print from the beginning (true)
        //or from the ending (false)
        if (direction) {
            for (int position = 1; position <= list.getLength(); position++) {
                System.out.print(list.getEntry(position));
            }
        } else {
            for (int position = list.getLength(); position > 0; position--) {
                System.out.print(list.getEntry(position));
            }
        }//end of if

        System.out.println("");
    }
//*******************************************************************************
//Method:       computeSum
//Description:  This method does the actual addition and placement into the LList sum.              
//Parameters:   None
//Returns:      None
//Calls:        LList.getLength
//              LList.getEntry
//              LList.add
//              LList.replace
//Globals:      addend1
//              addend2
//              sum

    public static void computeSum() {

        int position = 1;
        int position1 = addend1.getLength();
        int position2 = addend2.getLength();

        while (position1 > 0 || position2 > 0) {

            int sumAtCurrentPosition = 0;

            if ((sum.getEntry(position) != null) && (position1 > 0) && (position2 > 0)) {
                sumAtCurrentPosition = (int) sum.getEntry(position)
                        + (int) addend1.getEntry(position1) + (int) addend2.getEntry(position2);
            } else if ((position1 > 0) && (position2 > 0)) {
                sumAtCurrentPosition = (int) addend1.getEntry(position1)
                        + (int) addend2.getEntry(position2);
            } else if (position1 > 0) {
                sumAtCurrentPosition = (int) addend1.getEntry(position1);
            } else {
                sumAtCurrentPosition = (int) addend2.getEntry(position2);
            }//end of if

            if (sumAtCurrentPosition < 10) {
                if (sum.getEntry(position) == null) {
                    sum.add(position, sumAtCurrentPosition);
                } else {
                    sum.replace(position, sumAtCurrentPosition);
                }
            } else {
                if (sum.getEntry(position) == null) {
                    sum.add(position, (sumAtCurrentPosition - 10));
                } else {
                    sum.replace(position, (sumAtCurrentPosition - 10));
                }
                sum.add(position + 1, 1);
            }//end of if

            position++;
            position1--;
            position2--;
        }//end of while loop
    }
}//end of class
//*******************************************************************************
//*******************************************************************************
