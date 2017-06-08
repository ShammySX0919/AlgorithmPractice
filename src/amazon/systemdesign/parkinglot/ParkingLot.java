package amazon.systemdesign.parkinglot;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by andrew on 6/7/2017.
 */
public class ParkingLot {
    List<Area> areas=null;
    String address;
    List<Gate> gates=null;
    int availableLot = 0;

    public ParkingLot(String addr){
        this.address = addr;
        this.areas = new ArrayList<>();
        this.gates = new ArrayList<>();
    }

    public void addArea(Area a){
        this.areas.add(a);
        this.availableLot+=a.availableLots().length;
    }

    public void addGate(Gate g){
        this.gates.add(g);
    }
    private Lot[] findLotsForVehicle(Vehicle v) {
        Lot[] res = null;
        for(Area a:areas){
            res = a.findLotsForVehicle(v);
            if(res!=null)return res;
        }
        return null;
    }
    public void parkVehicle(Vehicle v, Gate g){
        v.takeParkingTicket(g.issueTicket());
        Lot[] lots = findLotsForVehicle(v);
        if(lots!=null){
            availableLot-=lots.length;
        }
        //park the vehicle
        for(Lot l:lots){
            l.parkVehicle(v);
        }
    }
    public void releaseVehicle(Vehicle v, Gate g){
        if(g.chargeTicket(v.getParkingTicket())) {
            //lots release vehicle
            for (Lot l : v.getOccupiedLots()) {
                l.releaseVehicle();
                availableLot++;
            }
            g.openGate();
            g.closeGate();
        }else{
            //call human attendants
            //
        }
    }

}
