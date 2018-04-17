//Class:        NameHobby
//Description:  This is a class of objects that holds strings for names and hobbies
//              and provides get and set methods for the user
//Globals:      name
//              hobby
//Last edited:  2/16/2016
//Edited by:    Jack Kawell
public class NameHobby {

    private String name;
    private String hobby;
//*******************************************************************************
//Method:       NameHobby
//Description:  An empty constructor              
//Parameters:   None
//Returns:      None
//Calls:        None         
//Globals:      name
//              hobby

    public NameHobby() {
        name = "";
        hobby = "";
    }
//*******************************************************************************
//Method:       NameHobby
//Description:  A constructor that provides a name and hobby for the object              
//Parameters:   inputName
//              inputHobby
//Returns:      None
//Calls:        None             
//Globals:      name
//              hobby

    public NameHobby(String inputName, String inputHobby) {
        name = inputName;
        hobby = inputHobby;
    }
//*******************************************************************************
//Method:       getName
//Description:  Gets the name part of the object       
//Parameters:   None
//Returns:      name
//Calls:        None          
//Globals:      name             

    public String getName() {
        return name;
    }
//*******************************************************************************
//Method:       setName
//Description:  Sets the name part of the object
//Parameters:   name
//Returns:      None
//Calls:        None              
//Globals:      name
    
    public void setName(String name) {
        this.name = name;
    }
//*******************************************************************************
//Method:       getHobby
//Description:  Gets the hobby part of the object
//Parameters:   None
//Returns:      hobby
//Calls:        None        
//Globals:      hobby 

    public String getHobby() {
        return hobby;
    }
//*******************************************************************************
//Method:       setHobby
//Description:  Sets the hobby part of the object
//Parameters:   hobby
//Returns:      None
//Calls:        None             
//Globals:      hobby        

    public void setHobby(String hobby) {
        this.hobby = hobby;
    }
}
