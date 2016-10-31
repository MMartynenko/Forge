import javax.swing.table.DefaultTableModel;
import java.io.Serializable;
import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.ResourceBundle;
import java.text.*;

/**
 * Created by Mark on 22.06.2015.
 */
public class TestingOrders implements Serializable {
    private int ID;
    private String Result;
    private String Status;
    private int OrderID;

    static ResourceBundle queries = ResourceBundle.getBundle("Strings");

    private ArrayList<TestingInfo> _orders;

    public TestingOrders() {
        _orders = new ArrayList<TestingInfo>();
        try {
            getOrdersFromDB();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<TestingInfo> getOrders() {
        return _orders;
    }

    public String addOrder() {
        TestingInfo _order = new TestingInfo(ID,Result,Status,OrderID);
        _orders.add(_order);
        return null;
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

    public void refresh(){
        try {
            getOrdersFromDB();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public void save(){
        try {
            setTestingOrdersStatus();
            setTestingOrdersResult();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void getOrdersFromDB() throws SQLException {
        _orders.clear();
        Statement stmt = null;

        String query = queries.getString("get_testing_orders");

        try {

            Connection con = DriverManager.getConnection(queries.getString("dburl"), queries.getString("dbuser"), queries.getString("dbpassword"));

            stmt = con.createStatement();
            ResultSet r = stmt.executeQuery(query);

            while (r.next()) {
                ID = r.getInt("ID");
                Result = r.getString("RESULT");
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

    private void setTestingOrdersStatus() throws SQLException{
        Statement stmt = null;

        String query = queries.getString("change_testing_order_status");

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

    private void setTestingOrdersResult() throws SQLException{
        Statement stmt = null;

        String query = queries.getString("change_testing_order_result");

        try {

            Connection con = DriverManager.getConnection(queries.getString("dburl"), queries.getString("dbuser"), queries.getString("dbpassword"));

            stmt = con.createStatement();


            for (int i=0; i<_orders.size();i++){
                System.out.println((query +" "+ _orders.get(i).getStatus() +" "+ _orders.get(i).getID()));
                System.out.println("------>"+ String.format(query, _orders.get(i).getStatus(), _orders.get(i).getID()));

                stmt.executeUpdate(String.format(query, _orders.get(i).getResult(), _orders.get(i).getID()));
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


