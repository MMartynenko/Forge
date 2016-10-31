import javax.swing.table.DefaultTableModel;
import java.io.Serializable;
import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.ResourceBundle;
import java.text.*;

/**
 * Created by Mark on 24.06.2015.
 */
public class AccesOrders {
    private int ID;
    private String Garda;
    private String Handle;
    private String Handle_Braiding;
    private String Engraving;
    private String Pommel;
    private String Scabbard;
    private String Status;
    private int OrderID;

    private String metal;
    private String metalID;
    private String Mass;

    static ResourceBundle queries = ResourceBundle.getBundle("Strings");

    private ArrayList<AccesInfo> _orders;
    private ArrayList<SteelInfo> _metalOrders;

    public AccesOrders() {
        _orders = new ArrayList<AccesInfo>();
        _metalOrders = new ArrayList<SteelInfo>();
        try {
            getOrdersFromDB();
            getMetalOrdersFromDB();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<AccesInfo> getOrders() {
        return _orders;
    }

    public ArrayList<SteelInfo> getMetalOrders() {
        return _metalOrders;
    }

    public String addOrder() {
        AccesInfo _order = new AccesInfo(ID,Garda,Handle,Handle_Braiding,Engraving,Pommel,Scabbard,Status,OrderID);
        _orders.add(_order);
        return null;
    }

    public String addMetalOrder() {
        SteelInfo _metalOrder = new SteelInfo(ID,Mass,Status,OrderID);
        _metalOrders.add(_metalOrder);
        return null;
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

    public String getMetal() {
        return metal;
    }

    public void setMetal(String metal) {
        this.metal = metal;
    }

    public String getMetalID() {
        return metalID;
    }

    public void setMetalID(String metalID) {
        this.metalID = metalID;
    }

    public void refresh(){
        try {
            getOrdersFromDB();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void refreshMetal(){
        try {
            getMetalOrdersFromDB();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public void save(){
        try {
            setAccesOrdersStatus();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void getOrdersFromDB() throws SQLException {
        _orders.clear();
        Statement stmt = null;

        String query = queries.getString("get_acces_orders");

        try {

            Connection con = DriverManager.getConnection(queries.getString("dburl"), queries.getString("dbuser"), queries.getString("dbpassword"));

            stmt = con.createStatement();
            ResultSet r = stmt.executeQuery(query);

            while (r.next()) {
                ID = r.getInt("ID");
                Garda = r.getString("GARDA");
                Handle = r.getString("HANDLE");
                Handle_Braiding = r.getString("HANDLE_BRAIDING");
                Engraving = r.getString("ENGRAVING");
                Pommel = r.getString("POMMEL");
                Scabbard = r.getString("SCABBARD");
                Status = r.getString("STATUS");
                OrderID = r.getInt("ORDER_ID");

                addOrder();
            }

            con.close();
        } catch (SQLException e ) {
            e.printStackTrace(System.err);
        } finally {

            if (stmt != null) { try {
                stmt.close();
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } }

        }
    }

    private void getMetalOrdersFromDB() throws SQLException {
        _metalOrders.clear();
        Statement stmt = null;

        String query = queries.getString("get_acc_metal_orders");

        try {

            Connection con = DriverManager.getConnection(queries.getString("dburl"), queries.getString("dbuser"), queries.getString("dbpassword"));

            stmt = con.createStatement();
            ResultSet r = stmt.executeQuery(query);

            while (r.next()) {
                ID = r.getInt("ID");
                Mass = r.getString("MASS");
                Status = r.getString("STATUS");
                OrderID = r.getInt("ORDER_ID");

                addMetalOrder();
            }

            con.close();
        } catch (SQLException e ) {
            e.printStackTrace(System.err);
        } finally {

            if (stmt != null) { try {
                stmt.close();
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } }

        }
    }

    private void setAccesOrdersStatus() throws SQLException{
        Statement stmt = null;

        String query = queries.getString("change_acces_order_status");

        try {

            Connection con = DriverManager.getConnection(queries.getString("dburl"), queries.getString("dbuser"), queries.getString("dbpassword"));

            stmt = con.createStatement();


            for (int i=0; i<_orders.size();i++){
                System.out.println((query +" "+ _orders.get(i).getStatus() +" "+ _orders.get(i).getID()));
                System.out.println("------>"+ String.format(query, _orders.get(i).getStatus(), _orders.get(i).getID()));
                stmt.executeUpdate(String.format(query, _orders.get(i).getStatus(), _orders.get(i).getID()));
            }


            //stmt.executeUpdate(String.format(query, Status, ID));
            //System.out.println("SAAAAAAAAAAVVVVVEEEEEEEEE!!! \n"+String.format(query, Status, ID));

            con.close();
        } catch (SQLException e ) {
            e.printStackTrace(System.err);
        } finally {
            if (stmt != null) { try {
                stmt.close();
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } }
        }
    }

    public void orderMetal() throws SQLException{

            SteelInfo orderMetal = new SteelInfo(0,metal,null,Integer.parseInt(metalID));

            Statement stmt = null;

            String query = queries.getString("create_metal_order");

            query = String.format(query, metal, Integer.parseInt(metalID), 1);

            try {

                Connection con = DriverManager.getConnection(queries.getString("dburl"), queries.getString("dbuser"), queries.getString("dbpassword"));

                stmt = con.createStatement();

                stmt.executeUpdate(query);

                refreshMetal();

            } catch (SQLException e) {
                e.printStackTrace(System.err);
            } finally {

                if (stmt != null) {
                    try {
                        stmt.close();
                    } catch (SQLException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }

            }
    }
}
