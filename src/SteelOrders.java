import javax.swing.table.DefaultTableModel;
import java.io.Serializable;
import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.ResourceBundle;
import java.text.*;

/**
 * Created by Георгий on 26.01.2015.
 */
public class SteelOrders implements Serializable {
    private int ID;
    private String Mass;
    private String Status;
    private int OrderID;

    static ResourceBundle queries = ResourceBundle.getBundle("Strings");

    private ArrayList<SteelInfo> _orders;

    public SteelOrders(){
        _orders = new ArrayList<SteelInfo>();
        try {
            getOrdersFromDB();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<SteelInfo> getOrders() {
        return _orders;
    }

    public String addOrder() {
        SteelInfo _order = new SteelInfo(ID,Mass,Status,OrderID);
        _orders.add(_order);
        return null;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public int getOrderID() {
        return OrderID;
    }

    public void setOrderID(int orderID) {
        this.OrderID = orderID;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        this.Status = status;
    }

    public String getMass() {
        return Mass;
    }

    public void setMass(String mass) {
        this.Mass = mass;
    }

    public void refresh(){
        try {
            getOrdersFromDB();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public void save(){
        try {
            setMetalOrdersStatus();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void getOrdersFromDB() throws SQLException {
        _orders.clear();
        Statement stmt = null;

        String query = queries.getString("get_metal_orders");

        try {

            Connection con = DriverManager.getConnection(queries.getString("dburl"), queries.getString("dbuser"), queries.getString("dbpassword"));

            stmt = con.createStatement();
            ResultSet r = stmt.executeQuery(query);

            while (r.next()) {
                ID = r.getInt("ID");
                Mass = r.getString("MASS");
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


    private void setMetalOrdersStatus() throws SQLException{
        Statement stmt = null;

        String query = queries.getString("change_metal_order_status");

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

}