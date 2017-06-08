package amazon.systemdesign.parkinglot;

/**
 * Created by andrew on 6/7/2017.
 */
public class Lot {
    int row,col, area;
    VehicleSize size;//share vehicle size definition
    Vehicle vehicle;//vehicle parked
    public boolean isEmpty(){
        return vehicle==null?true:false;
    }
    public Vehicle getVehicle(){return vehicle;}
    public void parkVehicle(Vehicle v){
        if(!isEmpty())throw new RuntimeException("Occuplied!");
        this.vehicle = v;
    }
    public void releaseVehicle(){
        this.vehicle = null;
    }
    public Lot(int area,int row, int col, VehicleSize size){
        this.area = area;
        this.row = row;
        this.col = col;
        this.size = size;
    }
}
