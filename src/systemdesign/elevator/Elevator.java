package systemdesign.elevator;

/**
 * elevator just move to floor, maybe open and close door
 * Created by andrew on 5/23/2017.
 */
public class Elevator {
    int id;//in case multiple elevator is supported
    int currentFloor;
    public Elevator(int id){
        this.id = id;
    }
    synchronized public void gotoFloor(int floor){
        //simulate elevator moving
        while(currentFloor>floor){
            currentFloor--;
        }
        while(currentFloor<floor){
            currentFloor++;
        }
    }
    public int getCurrentFloor(){
        return currentFloor;
    }
    public void openDoor(){
        //wait for 1 minute
    }
    public void closeDoor(){
        //close door
        //exception, open door
    }

}
