//Program:      SimpleArrayListTester.java
//Course:       COSC210
//Description:  Multi function program that places user entered names and thier
//              corresponding hobbies into objects and then places them into an
//              dynamic array list. It also allows the user to perform multiple
//              different options to manipulate and view the objects.
//Author:       Jack Kawell
//Revised:      2/15/16
//Language:     Java
//IDE:          NetBeans 8.0.2
//*******************************************************************************
//*******************************************************************************
//Class:        SimpleArrayListTester
//Description:  Multi function class that places user entered objects in an
//              array and allows the user to run different functions on the data.

public class SimpleArrayListTester {

    private static DynamicArrayList nameHobbyList;
    private static KeyboardInputClass keyboardInput;
//*******************************************************************************
//Method:       main
//Description:  The main method that prints the menu list and calls each method
//              for whichever selection the user makes
//Parameters:   Default
//Returns:      None
//Calls:        KeyboardInputClass
//              keyboardInput.getInteger
//              DynamicArrayList(int initialSize)
//              addToBeginning
//              addToPosition
//              removeAtPosition
//              showSpecificPosition
//              showHobbyForName
//              nameHobbyList.getHowManyDoubles
//              printList
//Globals:      Integer.MAX_VALUE
//              keyboardInput
//              nameHobbyList

    public static void main(String[] args) {

        boolean endProgram = false;

        keyboardInput = new KeyboardInputClass();
        int userSelection = 0;

        int userSetMax = keyboardInput.getInteger(true, 10, 1, Integer.MAX_VALUE, "Enter the "
                + "beginning size of the list:");

        nameHobbyList = new DynamicArrayList(userSetMax);

        while (endProgram == false) {

            System.out.println("\n"
                    + "1) Add an entry to the end of the list\n"
                    + "2) Add an entry at a specified position in the list\n"
                    + "3) Remove an entry from the list\n"
                    + "4) Show the contents of a specified list position\n"
                    + "5) Show the hobby for a specified individual\n"
                    + "6) Show the number of entries in the list, its current\n"
                    + "   maximum size, and its contents\n"
                    + "7) Exit program");

            userSelection = keyboardInput.getInteger(true, 1, 1, 7,
                    "Please select one:");

            if (userSelection == 1) {
                addToEnd();
            } else if (!nameHobbyList.isEmpty()) {
                switch (userSelection) {
                    case 2:
                        addToPosition();
                        break;
                    case 3:
                        removeAtPosition();
                        break;
                    case 4:
                        showSpecificPosition();
                        break;
                    case 5:
                        showHobbyForName();
                        break;
                    case 6:
                        //I tried to call the getHowManyDoubles method inside my
                        //printList method but it wouldn't work. Do you have any
                        //idea about why it works in my main method but not below?
                        printList(userSetMax);
                        break;
                    case 7:
                        endProgram = true;
                        break;
                }
            } else if (userSelection == 7) {
                endProgram = true;
            } else {
                System.out.println("\nThere are no people yet. Please select "
                        + "1 to enter \na new person or 7 to exit:");
            }
        }
    }
//*******************************************************************************
//Method:       addToEnd
//Description:  Takes in a name and hobby from the user, creates an object and
//              adds it to the end of the list
//Parameters:   None
//Returns:      None
//Calls:        keyboardInput.getString
//              nameHobbyList.add
//Globals:      keyboardInput

    public static void addToEnd() {
        String addName = keyboardInput.getString("name", "Enter the name of"
                + " the person to add to the list:");
        String addHobby = keyboardInput.getString("hobby", "Enter the name of"
                + " the hobby to add to the list:");

        NameHobby newEntry = new NameHobby(addName, addHobby);

        nameHobbyList.add(newEntry);
    }
//*******************************************************************************
//Method:       addToPosition
//Description:  Takes in a name and hobby from the user, creates an object and
//              adds it to a user-defined position in the list
//Parameters:   None
//Returns:      None
//Calls:        keyboardInput.getInteger
//              nameHobbyList.getLength
//              keyboardInput.getString
//              NameHobby(inputName, inputHobby)
//              nameHobbyList.add
//Globals:      keyBoardInput
//              nameHobbyList

    public static void addToPosition() {
        int addPosition = keyboardInput.getInteger(
                true, (nameHobbyList.getLength() + 1), 1, (nameHobbyList.getLength() + 1),
                "Enter the position at which you would like to add the person:");

        String addName = keyboardInput.getString("name", "Enter the name of"
                + " the person to add to the list:");
        String addHobby = keyboardInput.getString("hobby", "Enter the name of"
                + " the hobby to add to the list:");

        NameHobby newEntry = new NameHobby(addName, addHobby);

        nameHobbyList.add(addPosition, newEntry);
    }
//*******************************************************************************
//Method:       removeAtPosition
//Description:  Removes an entry from a user specified position
//Parameters:   None
//Returns:      None
//Calls:        keyboardInput.getInteger
//              nameHobbyList.getLength
//              nameHobbyList.remove
//Globals:      keyboardInput
//              nameHobbyList

    public static void removeAtPosition() {
        int positionToRemove = keyboardInput.getInteger(true,
                nameHobbyList.getLength(), 1, nameHobbyList.getLength(),
                "Enter the position you would like to remove:");

        nameHobbyList.remove(positionToRemove);
    }
//*******************************************************************************
//Method:       showSpecificPosition
//Description:  Displays the name and hobby of a user specified position in the
//              list
//Parameters:   None
//Returns:      None
//Calls:        keyboardInput.getInteger
//              nameHobbyList.getLength
//              nameHobbyList.getEntry
//              printObject.getName
//              printObject.getHobby
//Globals:      keyboardInput
//              nameHobbyList

    public static void showSpecificPosition() {
        int position = keyboardInput.getInteger(true,
                1, 1, nameHobbyList.getLength(), "Enter the position to show:");

        NameHobby printObject = (NameHobby) nameHobbyList.getEntry(position);

        System.out.println("     NAME       HOBBY    ");
        System.out.printf("%3d) %-9s  %-9s\n", position, printObject.getName(), printObject.getHobby());
    }
//*******************************************************************************
//Method:       showHobbyForName
//Description:  Asks the user for a name then searches through the list for that
//              name. If found, it prints the hobby or hobbies for that name
//Parameters:   None
//Returns:      None
//Calls:        keyboardInput.getString
//              nameHobbyList.getLength
//              nameHobbyList.getEntry
//              currentNameHobby.getName
//              currentNameHobby.getHobby
//Globals:      keyboardInput
//              nameHobbyList

    public static void showHobbyForName() {
        boolean foundInList = false;

        String nameToFind = keyboardInput.getString("name",
                "Enter the name for which you would like to find the hobby:");

        for (int position = 1; position <= nameHobbyList.getLength(); position++) {
            NameHobby currentNameHobby = (NameHobby) nameHobbyList.getEntry(position);
            String nameToCheck = currentNameHobby.getName();
            if (nameToCheck.equalsIgnoreCase(nameToFind) && !foundInList) {
                System.out.println("The hobby/hobbies for " + currentNameHobby.getName()
                        + " is/are:\n" + currentNameHobby.getHobby() + "");
                foundInList = true;
            } else if (currentNameHobby.getName().equalsIgnoreCase(nameToFind) && foundInList) {
                System.out.println(currentNameHobby.getHobby());
            }
        }//end of for loop

        if (!foundInList) {
            System.out.println(nameToFind + " is not in the list.");
        }
    }
//*******************************************************************************
//Method:       printList
//Description:  Prints the current size, amount of entries, and the contents of
//              the list.
//Parameters:   userSetMax
//              amountOfTimesDoubled
//Returns:      None
//Calls:        nameHobbyList.getLength
//              nameHobbyList.getEntry
//              printObject.getName
//              printObject.getHobby
//Globals:      nameHobbyList

    public static void printList(int userSetMax) {
        int amountOfTimesDoubled = nameHobbyList.getHowManyDoubles();

        System.out.println("The number of entries in the list is: " + nameHobbyList.getLength());

        int amountOfPositions = (int) (userSetMax * Math.pow(2, amountOfTimesDoubled));

        System.out.println("The current maximum size of the list is: " + amountOfPositions);

        System.out.println("     NAME       HOBBY    ");
        for (int position = 1; position <= nameHobbyList.getLength(); ++position) {
            NameHobby printObject = (NameHobby) nameHobbyList.getEntry(position);
            System.out.printf("%3d) %-9s  %-9s\n", position, printObject.getName(), printObject.getHobby());
        }
    }
//*******************************************************************************
}//End of class
