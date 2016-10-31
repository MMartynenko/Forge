/**
 * Created by Mark on 24.06.2015.
 */
public class AccesInfo {
    private int ID;
    private String Garda;
    private String Handle;
    private String Handle_Braiding;
    private String Engraving;
    private String Pommel;
    private String Scabbard;
    private String Status;
    private int OrderID;
    private boolean canEdit;

    public AccesInfo(int ID, String garda, String handle, String handle_Braiding, String engraving, String pommel, String scabbard, String status, int orderID) {
        this.ID = ID;
        this.Garda = garda;
        this.Handle = handle;
        this.Handle_Braiding = handle_Braiding;
        this.Engraving = engraving;
        this.Pommel = pommel;
        this.Scabbard = scabbard;
        this.Status = status;
        this.OrderID = orderID;
        canEdit = false;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getGarda() {
        return Garda;
    }

    public void setGarda(String garda) {
        Garda = garda;
    }

    public String getHandle() {
        return Handle;
    }

    public void setHandle(String handle) {
        Handle = handle;
    }

    public String getHandle_Braiding() {
        return Handle_Braiding;
    }

    public void setHandle_Braiding(String handle_Braiding) {
        Handle_Braiding = handle_Braiding;
    }

    public String getEngraving() {
        return Engraving;
    }

    public void setEngraving(String engraving) {
        Engraving = engraving;
    }

    public String getPommel() {
        return Pommel;
    }

    public void setPommel(String pommel) {
        Pommel = pommel;
    }

    public String getScabbard() {
        return Scabbard;
    }

    public void setScabbard(String scabbard) {
        Scabbard = scabbard;
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
