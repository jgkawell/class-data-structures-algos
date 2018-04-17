//Program:      AirportSimulator.java
//Course:       COSC210
//Description:  The purpose of this program is to simulate the time behavior of 
//              airplanes arriving at an airport and waiting to land. Imagine that 
//              airplanes arrive at random intervals with a limited quantity of 
//              fuel which determines their position in the holding pattern. Airlines 
//              with less fuel are given landing priority over airlines with more fuel. 
//              The airport can accept a landing every two minutes. Each plane belongs 
//              to one of eight airlines (United, Southwest, Northwest, Continental, 
//              American, Frontier, Alaska, or Lufthansa), has a flight number (1000-9999), 
//              and an amount of fuel left upon arrival (expressed in minutes as an 
//              integer from 1 to 20), and an arrival time.            
//Author:       Jack Kawell
//Revised:      5/2/16
//Language:     Java
//IDE:          NetBeans 8.0.2
//*******************************************************************************
//*******************************************************************************
//Class:        AirportSimulator
//Description:  This class contains all the necessary methods to complete the simulation.
//              It also calls KeyboardInputClass to get input from the user and PriorityQueue
//              as the list structure for storing the Airplane objects.

public class AirportSimulator {

    public static int simulationTime;//public so that Airplane class can use it
    private static int freeToLand;//keeps track of whether a plane can land
    private static PriorityQueue queue;//main queue
    private static KeyboardInputClass keyboardInput;//object for user input
    private static PriorityQueue tempQueue;//temporary queue for copying

//*******************************************************************************
//Method:       main
//Description:  Main method that calls all other relavant methods. Also prints 
//              the output for the user and prompts the user for the next action.
//Parameters:   None
//Returns:      Nothing
//Calls:        PriorityQueue()
//              KeyboardInputClass()
//              PriorityQueue()
//              keyboardInput.getCharacter
//              dequeueAndRequeue
//              checkAndLandPlane
//              addPlane
//Globals:      queue
//              keyboardInput
//              freeToLand
//              tempQueue
//              simulationTime
    public static void main(String[] args) {
        queue = new PriorityQueue();
        keyboardInput = new KeyboardInputClass();
        freeToLand = 2;
        tempQueue = new PriorityQueue();
        char input = 'A';

        System.out.println("Airport Simulation Program (Kawell)\n"
                + "-----------------------------------\n"
                + "ENTER = increment the simulation clock only\n"
                + " P    = generate a new arrival (and increment the clock)\n"
                + " S    = show priority queue contents (clock does not change)\n"
                + " X    = exit simulation program\n");

        while (input != 'X') {
            input = keyboardInput.getCharacter(true, 'A', "PSX", 1, "Next action:");
            if (input == 'A') {//user presses ENTER
                dequeueAndRequeue(input);
                checkAndLandPlane();
                freeToLand++;
                simulationTime++;
            } else if (input == 'P') {
                dequeueAndRequeue(input);
                Airplane airplaneToAdd = new Airplane();
                airplaneToAdd.arrivingMessage();
                queue.priorityEnqueue(airplaneToAdd);
                checkAndLandPlane();
                freeToLand++;
                simulationTime++;
            } else if (input == 'S') {
                dequeueAndRequeue(input);
            }
            System.out.println("");
        }
    }

//*******************************************************************************
//Method:       checkAndLandPlane
//Description:  Checks to see if the plane at the front of the queue can land. if
//              it can, it dequeues the plane and prints out the landing message.
//Parameters:   None
//Returns:      Nothing
//Calls:        queue.dequeue
//              landingAirplane.landingMessage
//Globals:      freeToLand
//              queue
    private static void checkAndLandPlane() {
        if (freeToLand >= 2) {
            Airplane landingAirplane = (Airplane) queue.dequeue();
            if (landingAirplane != null) {
                landingAirplane.landingMessage();
                freeToLand = 0;
            }
        }
    }
//*******************************************************************************
//Method:       dequeueAndRequeue
//Description:  Dequeues all planes in the queue and saves them in a temporary queue
//              then requeues them back into the original queue. While it is taking
//              the planes from the original queue it checks to see if it the call is
//              needs to print or change the fuel and time. If it needs to print it
//              it doesn't change fuel and time. Otherwise, it decrements the fuel of
//              each plane and increments the wait time.
//Parameters:   printOrTime     -       This char is the user input and tells the method
//                                      whether to change fuel and time or just print.
//Returns:      Nothing
//Calls:        queue.dequeue
//              currentAirplane.decrementFuel
//              currentAirplane.incrementWaitTime
//              currentAirplane.getFuel
//              currentAirplane.crashingMessage
//              tempQueue.enqueue
//              currentAirplane.printAirplane
//              tempQueue.dequeue
//              queue.priorityEnqueue
//Globals:      queue
//              tempQueue  

    private static void dequeueAndRequeue(char printOrTime) {
        boolean done = false;
        while (!done) {//loop to dequeue and store aiplanes
            Airplane currentAirplane = (Airplane) queue.dequeue();
            if (currentAirplane != null) {
                if (printOrTime == 'A' || printOrTime == 'P') {//lower fuel and add time
                    currentAirplane.decrementFuel();
                    currentAirplane.incrementWaitTime();
                    if (currentAirplane.getFuel() < 0) {//check if it crashes
                        currentAirplane.crashingMessage();
                    } else {
                        tempQueue.enqueue(currentAirplane);//temporarily store plane
                    }
                } else if (printOrTime == 'S') {//just print
                    currentAirplane.printAirplane();
                    tempQueue.enqueue(currentAirplane);//temporarily store plane
                }
            } else {
                done = true;
            }
        }
        done = false;//reset done
        while (!done) {//loop to requeue airplanes
            Airplane airplaneToAddBack = (Airplane) tempQueue.dequeue();
            if (airplaneToAddBack != null) {
                queue.priorityEnqueue(airplaneToAddBack);
            } else {
                done = true;
            }
        }
    }
}//end of class
//*******************************************************************************
//*******************************************************************************
