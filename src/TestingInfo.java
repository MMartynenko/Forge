/**
 * Created by Mark on 22.06.2015.
 */
public class TestingInfo {
    private int ID;
    private String Result;
    private String Status;
    private int OrderID;
    private boolean canEdit;

    public TestingInfo(int ID, String Result, String Status, int OrderID){
        this.ID = ID;
        this.Result = Result;
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

    public String getResult() {
        return Result;
    }

    public void setResult(String result) {
        Result = result;
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
