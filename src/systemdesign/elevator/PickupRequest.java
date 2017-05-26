package systemdesign.elevator;

/**
 * Created by andrew on 5/23/2017.
 */
public class PickupRequest implements Request{
    int toFloor;
    public PickupRequest(int floor){
        //ignore the elevatorId
        this.toFloor = floor;
    }
    @Override
    public int getToFloor() {
        return this.toFloor;
    }

    @Override
    public int getFromElevator() {
        return -1;
    }

}
