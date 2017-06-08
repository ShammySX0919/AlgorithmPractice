package amazon.systemdesign.parkinglot;

import java.util.List;

/**
 * Created by andrew on 6/7/2017.
 */
public abstract class Vehicle {
    protected VehicleSize size;
    protected String licensePlate;
    protected Ticket parkingTicket;//or let parking lot remembers it
    protected List<Lot> occupiedLots = null;
    //other
    String getLicensePlate(){return licensePlate;};
    public VehicleSize getSize(){return size;};
    public void takeParkingTicket(Ticket tkt){
        this.parkingTicket = tkt;
    }
    public Ticket getParkingTicket(){return parkingTicket;}
    public void resetParkingTicket(){
        this.parkingTicket = null;
    }
    public void occupyLots(Lot[] lots){
        for(Lot l:lots){
            occupyLot(l);
        }
    }
    public void occupyLot(Lot l){
        this.occupiedLots.add(l);
    }
    public List<Lot> getOccupiedLots(){return occupiedLots;}
}
