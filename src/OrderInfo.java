/**
 * Created by Mark on 26.01.2015.
 */
public class OrderInfo {
    private int ID;
    private String Name;
    private String Date;
    private String Status;
    private boolean canEdit;

        
    public OrderInfo(int ID, String Name, String Date, String Status){
        this.ID = ID;
        this.Name = Name;
        this.Date = Date;
        this.Status = Status;
        canEdit = false;
    }


    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        this.Name = name;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        this.Date = date;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        this.Status = status;
    }

}
