package systemdesign.elevator;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

/**
 * support multiple elevators
 * Created by andrew on 5/23/2017.
 */
public class RequestProcessCenter implements Runnable{
    private static RequestProcessCenter instance;
    private Map<Integer,Elevator> elevatorMap = new HashMap<>();
    //this can also be maintained by elevator itself
    private Map<Integer,Queue<Request>> elevatorDispatchQueue = new HashMap<>();
    private Queue<Request> floorRequest = new LinkedList<>();
    //make a singleton of it
    private RequestProcessCenter(){
    }

    public void regiesterElevator(int id){
        if(!elevatorMap.containsKey(id)){
            elevatorMap.put(id,new Elevator(id));
            elevatorDispatchQueue.put(id,new LinkedList<Request>());
        }
    }

    public void addRequest(Request req){
        if(req instanceof PickupRequest)addPickRequest(req);
        else if(req instanceof DispatchRequest)addDispatchRequest(req);
    }
    public void removeRequest(Request req){
        if(req instanceof PickupRequest)removePickRequest(req);
        else if(req instanceof DispatchRequest)removeDispatchRequest(req);
    }

    private void addPickRequest(Request request) {
        floorRequest.add(request);
    }

    private void removePickRequest(Request request) {
        floorRequest.remove(request);
    }

    private void addDispatchRequest(Request request) {
        elevatorDispatchQueue.get(request.getFromElevator()).add(request);
    }

    private void removeDispatchRequest(Request request) {
        elevatorDispatchQueue.get(request.getFromElevator()).remove(request);
    }

    public static RequestProcessCenter getInstance(){
        if(instance==null){
            synchronized (RequestProcessCenter.class){
                instance = new RequestProcessCenter();
            }
        }
        return instance;
    }
    @Override
    public void run() {
        while(true) {
            processRequest();
        }
    }

    /**
     * fullfill the request that is closest to current floor
     * @param elevatorId
     * @return
     */
    public Request getNextRequest(int elevatorId ) {
        Request shortestReq = null;
        int shortest = Integer.MAX_VALUE;
        int curFloor = elevatorMap.get(elevatorId).getCurrentFloor( );
        for (Request item : elevatorDispatchQueue.get(elevatorId)) {
            int distance = Math.abs(curFloor - item.getToFloor( ) );
            if (distance < shortest) {
                shortest = distance;
                shortestReq = item;
            }
        }
        return shortestReq;
    }
    public void processRequest( ) {
        //first process pickup request, converting pickup request to dispatch request
        for(Request req:floorRequest){
            int elevator = findElevator(req);//findelevator do the magic to find an elevator to serve the request
            elevatorDispatchQueue.get(elevator).add(new DispatchRequest(elevator,req.getToFloor()));
        }
        //for each elevator.
        //process each elevator's requests
        for(int elevatorId:elevatorMap.keySet()) {
            Request req = getNextRequest(elevatorId);
            if(req != null){
                int toFloor = req.getToFloor();
                Thread t = new Thread(){
                    @Override
                    public void run() {
                        elevatorMap.get(elevatorId).gotoFloor(toFloor);
                    }
                };
                t.start();

                removeRequest(req);
            }
        }
    }

    /**
     * find elevator according to requested floor and all elevator's current running status
     * @param req
     * @return
     */
    private int findElevator(Request req) {
        //todo: algorithm based on current floor and running direction
        return 0;
    }
}
