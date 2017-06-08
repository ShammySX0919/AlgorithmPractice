package amazon.systemdesign.parkinglot;

/**
 * Created by andrew on 6/7/2017.
 */
public class Area {
    int id;//area id
    Lot[][] lots = null;
    int rows, cols;
    public Lot[] availableLots(){return null;}
    public Lot[] findLotsForVehicle(Vehicle v){return null;}
    public void parkVehicle(Vehicle v){
        Lot[] occupies = findLotsForVehicle(v);
        parkVehicle(v,occupies);
    }
    private void parkVehicle(Vehicle v, Lot[] lots){
        for(Lot l:lots){
            l.parkVehicle(v);
        }
    }

    public Area(int rows, int cols, int id){
        this.id = id;
        lots = new Lot[rows][cols];
    }
    public void addLot(int row,int col, VehicleSize size){
        lots[row][col] = new Lot(id,row,col,size);
    }
}
