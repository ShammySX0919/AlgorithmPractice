package systemdesign.elevator;

/**
 * Created by andrew on 5/23/2017.
 */
public class User {
    //press button to request elevator
    void requestElevator(int fromFloor){
        Request req = new PickupRequest( fromFloor);
        RequestProcessCenter center = RequestProcessCenter.getInstance();
        center.addRequest(req);
    }
    //inside elevator, request floor to go
    void requestFloor(int fromFloor, int toFloor){
        Request req = new DispatchRequest(fromFloor, toFloor);
        RequestProcessCenter center = RequestProcessCenter.getInstance();
        center.addRequest(req);
    }
}
