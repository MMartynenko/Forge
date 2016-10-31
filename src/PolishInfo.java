/**
 * Created by Mark on 22.06.2015.
 */
public class PolishInfo {
    private int ID;
    private String Quality;
    private String Status;
    private int OrderID;
    private boolean canEdit;

    public PolishInfo(int ID, String Quality, String Status, int OrderID){
        this.ID = ID;
        this.Quality = Quality;
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

    public String getQuality() {
        return Quality;
    }

    public void setQuality(String quality) {
        Quality = quality;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }

    public int getOrderID() {
        return OrderID;
    }

    public void setOrderID(int orderID) {
        OrderID = orderID;
    }
}
