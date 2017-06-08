package amazon.systemdesign.parkinglot;

/**
 * hide all other information such as card reader, payment system etc
 * Created by andrew on 6/7/2017.
 */
public class Gate {
    public Ticket issueTicket(){return null;}
    public boolean chargeTicket(Ticket tkt){return false;}
    public void openGate(){}
    public void closeGate(){}
}
