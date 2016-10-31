/**
 * Created by Mark on 26.01.2015.
 */
public class SteelInfo {
    private int ID;
    private String Mass;
    private String Status;
    private int OrderID;
    private boolean canEdit;


    public SteelInfo(int ID, String Mass, String Status, int OrderID){
        this.ID = ID;
        this.Mass = Mass;
        this.Status = Status;
        this.OrderID = OrderID;
        canEdit = false;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getMass() {
        return Mass;
    }

    public void setMass(String mass) {
        this.Mass = mass;
    }

    public int getOrderID() {
        return OrderID;
    }

    public void setOrderID(int OrderID) {
        this.OrderID = OrderID;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        this.Status = status;
    }

}
