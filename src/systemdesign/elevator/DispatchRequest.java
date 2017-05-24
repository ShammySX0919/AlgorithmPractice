package systemdesign.elevator;

/**
 * Created by andrew on 5/23/2017.
 */
public class DispatchRequest implements Request {
    int toFloor;
    int fromElevator;
    public DispatchRequest(int fromElevator,int floor){
        this.toFloor = floor;
        this.fromElevator = fromElevator;
    }

    @Override
    public int getToFloor() {
        return this.toFloor;
    }

    @Override
    public int getFromElevator() {
        return this.fromElevator;
    }
}
