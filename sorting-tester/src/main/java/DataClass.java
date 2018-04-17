//Class:        DataClass
//Description:  An object that stores contains a random int (0-5000), double, and String of
//              forty (40) lowercase letters.

public class DataClass implements Comparable<DataClass> {

    private int iCode;
    private double dCode;
    private String sCode = "";
//*******************************************************************************
//Method:       DataClass
//Description:  This is a constructor that creates random values for iCode, dCode, and sCode              
//Parameters:   None
//Returns:      None
//Calls:        None                  
//Globals:      iCode
//              dCode
//              sCode

    public DataClass() {
        //Random randomGenerator = new Random();
        iCode = (int) (Math.random() * 50001);
        
        dCode = Math.random();
        for (int counter = 0; counter < 40; counter++) {
            sCode += (char) ((int) (97 + Math.random() * 26));
        }
    }
//*******************************************************************************
//Method:       getiCode
//Description:  Returns iCode              
//Parameters:   None
//Returns:      iCode
//Calls:        None                 
//Globals:      iCode

    public int getiCode() {
        return iCode;
    }
//*******************************************************************************
//Method:       getdCode
//Description:  Returns dCode              
//Parameters:   None
//Returns:      dCode
//Calls:        None                 
//Globals:      dCode

    public double getdCode() {
        return dCode;
    }
//*******************************************************************************
//Method:       getsCode
//Description:  Returns sCode              
//Parameters:   None
//Returns:      sCode
//Calls:        None                 
//Globals:      sCode

    public String getsCode() {
        return sCode;
    }
//*******************************************************************************
//Method:       compareTo
//Description:  This is a compareTo method that overrides the standard Java compareTo.
//              It allows the user to choose which parameter to compare (int, double, String)
//              and then runs the comparison and returns -1, 0, or 1 in accordance with
//              the standard Java compareTo protocol.
//Parameters:   object - The DataClass object to compare the current object to
//Returns:      value - The result of the comparison (-1 is <, 0 is =, 1 is >)
//Calls:        SortingTester.getSortField
//              this.getiCode
//              object.getiCode
//              this.getdCode
//              object.getdCode
//              this.getsCode.charAt
//              object.getsCode.charAt
//Globals:      sCode

    @Override
    public int compareTo(DataClass object) {
        int value = 0;

        switch (SortingTester.getSortField()) {
            case 1:
                Integer iFirst = this.getiCode();
                Integer iSecond = object.getiCode();
                value = iFirst.compareTo(iSecond);
                break;
            case 2:
                Double dFirst = this.getdCode();
                Double dSecond = object.getdCode();
                value = dFirst.compareTo(dSecond);
                break;
            case 3:
                value = this.sCode.compareTo(object.getsCode());
        }
        return value;
    }
}//end of class
//*******************************************************************************
//*******************************************************************************
