//Class:        Airplane
//Description:  An object that contains one of eight airlines (United, Southwest, Northwest, Continental, 
//              American, Frontier, Alaska, or Lufthansa), has a flight number (1000-9999), 
//              and a random amount of fuel (expressed in minutes as an integer 
//              from 1 to 20), wait time, and an arrival time.         

public class Airplane implements Comparable<Airplane> {

    private final int FLIGHT_NUMBER, ARRIVAL_TIME;
    private final String AIRLINE;
    private int waitTime;
    private int fuel;
    private final String[] AIRLINE_ARRAY = {"United", "Southwest", "Northwest", "Continental",
        "American", "Frontier", "Alaska", "Lufthansa"};
//*******************************************************************************
//Method:       Airplane
//Description:  The basic constructor that assigns a random value for the flight number,
//              fuel and airline, as well as setting the wait time to zero and the
//              arrival time to the current simulation time from AirportSimulator.
//Parameters:   None
//Returns:      Nothing
//Calls:        Math.random
//              Kawell1.simulationTime
//Globals:      FLIGHT_NUMBER
//              fuel 
//              AIRLINE
//              AIRLINE_ARRAY
//              ARRIVAL_TIME
//              AirportSimulator.simulationTime
//              waitTime

    public Airplane() {
        FLIGHT_NUMBER = (int) (1000 + Math.random() * 9000);
        fuel = (int) (1 + Math.random() * 20);
        AIRLINE = AIRLINE_ARRAY[(int) (Math.random() * 8)];
        ARRIVAL_TIME = AirportSimulator.simulationTime;
        waitTime = 0;
    }

    @Override
    public int compareTo(Airplane entry) {
        if (fuel > entry.getFuel()) {
            return 1;
        } else if (fuel < entry.getFuel()) {
            return -1;
        } else {
            return 0;
        }
    }

//*******************************************************************************
//Method:       printAirplane
//Description:  Prints out the airline, flight number, fuel and arrival time of the 
//              current airplane.
//Parameters:   None
//Returns:      Nothing
//Calls:        Nothing
//Globals:      AIRLINE
//              FLIGHT_NUMBER   
//              fuel
//              ARRIVAL_TIME
    public void printAirplane() {
        System.out.println(AIRLINE + " flight " + FLIGHT_NUMBER + ": Fuel left=" + fuel
                + "; Arrival time= " + ARRIVAL_TIME);
    }
//*******************************************************************************
//Method:       landingMessage
//Description:  Prints out the airline, flight number, fuel and wait time of the 
//              current airplane for when it's landing.
//Parameters:   None
//Returns:      Nothing
//Calls:        Nothing
//Globals:      AIRLINE
//              FLIGHT_NUMBER   
//              fuel
//              waitTime

    public void landingMessage() {
        System.out.println(AIRLINE + " flight #" + FLIGHT_NUMBER + " lands with "
                + fuel + " min(s) of fuel left after waiting " + waitTime + " min(s)");
    }
//*******************************************************************************
//Method:       arrivingMessage
//Description:  Prints out the airline, flight number, simulation time and fuel of the 
//              current airplane for when it's arriving.
//Parameters:   None
//Returns:      Nothing
//Calls:        Nothing
//Globals:      AIRLINE
//              FLIGHT_NUMBER   
//              AirportSimulator.simulationTime
//              fuel

    public void arrivingMessage() {
        System.out.println(AIRLINE + " flight #" + FLIGHT_NUMBER + " arrives at "
                + AirportSimulator.simulationTime + " min(s) with " + fuel + " min(s) of fuel left");
    }
//*******************************************************************************
//Method:       crashingMessage
//Description:  Prints out the airline, flight number, wait time of the current
//              airplane for when it's crashing.
//Parameters:   None
//Returns:      Nothing
//Calls:        Nothing
//Globals:      AIRLINE
//              FLIGHT_NUMBER   
//              waitTime

    public void crashingMessage() {
        System.out.println(AIRLINE + " flight #" + FLIGHT_NUMBER + " CRASHES after waiting "
                + waitTime + " min(s)");
    }
//*******************************************************************************
//Method:       getFuel
//Description:  Returns the fuel
//Parameters:   None
//Returns:      fuel        -       The current fuel of the airplane
//Calls:        Nothing
//Globals:      fuel

    public int getFuel() {
        return fuel;
    }
//*******************************************************************************
//Method:       incrementWaitTime
//Description:  Increments the wait time
//Parameters:   None
//Returns:      Nothing
//Calls:        Nothing
//Globals:      waitTime

    public void incrementWaitTime() {
        waitTime++;
    }
//*******************************************************************************
//Method:       decrementFuel
//Description:  Decrements the fuel
//Parameters:   None
//Returns:      Nothing
//Calls:        Nothing
//Globals:      fuel

    public void decrementFuel() {
        fuel--;
    }
}//end of class
//*******************************************************************************
//*******************************************************************************
