import javax.swing.table.DefaultTableModel;
import java.io.Serializable;
import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.ResourceBundle;
import java.text.*;
import org.primefaces.context.RequestContext;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;


/**
 * Created by Георгий on 26.01.2015.
 */

public class Orders implements Serializable {
    private int ID;
    private String Name;
    private String Date;
    private String Status;

    private boolean showOrderInfo;
    private boolean showDialog;
    private boolean flag=false;
    private OrderInfo chosenOrder;
    private String orderName;
    private String orderMail;
    private String orderQuality;
    private int orderLength;
    private String orderDate;
    private String guard;
    private String handle;
    private String braiding;
    private String engraving;
    private String header;
    private String scabbard;

    private String metal;
    private String orderStatus;
    private String orderPolish;

    static ResourceBundle queries = ResourceBundle.getBundle("Strings");

    private ArrayList<OrderInfo> _orders;

    public Orders(){
        _orders = new ArrayList<OrderInfo>();
        showOrderInfo = false;
        showDialog = false;
        try {
            getOrdersFromDB();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<OrderInfo> getOrders() {
        return _orders;
    }

    public String addOrder() {
        showDialog = false;
        OrderInfo _order = new OrderInfo(ID,Name,Date,Status);
        _orders.add(_order);
        return null;
    }

    public void resetMsg() {
        if (flag) flag=false;
        else this.showDialog = false;
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

    public boolean isShowOrderInfo() {
        return showOrderInfo;
    }

    public void setShowOrderInfo(boolean showOrderInfo) {
        this.showOrderInfo = showOrderInfo;
    }

    public boolean isShowDialog() {
        return showDialog;
    }

    public void setShowDialog(boolean showDialog) {
        this.showDialog = showDialog;
    }

    public OrderInfo getChosenOrder() {
        return chosenOrder;
    }

    public void setChosenOrder(OrderInfo chosenOrder) {
        this.chosenOrder = chosenOrder;
    }

    public void refresh(){
        showOrderInfo = false;
        showDialog = false;
        try {
            getOrdersFromDB();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void getOrdersFromDB()throws SQLException {
        showDialog = false;
        _orders.clear();
        Statement stmt = null;

        String query = queries.getString("get_orders");

        try {

            Connection con = DriverManager.getConnection(queries.getString("dburl"), queries.getString("dbuser"), queries.getString("dbpassword"));

            stmt = con.createStatement();
            ResultSet r = stmt.executeQuery(query);

            int i=0;
            while (r.next()) {
                ID = r.getInt("ID");
                Name = r.getString("NAME");
                Date = r.getString("DATE");
                Status = r.getString("STATUS");
                //System.out.println(ID);
                addOrder();
                i++;
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

    public void displayOrder() throws SQLException{
        showOrderInfo = true;
        showDialog = false;

        orderStatus = chosenOrder.getStatus();

        Statement stmt = null;

        String query = queries.getString("get_order_info");

        query = String.format(query, chosenOrder.getID());

        try {

            Connection con = DriverManager.getConnection(queries.getString("dburl"), queries.getString("dbuser"), queries.getString("dbpassword"));

            stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            while (rs.next()) {
                orderName = (rs.getString("NAME"));
                orderDate = (rs.getString("DATE"));
                orderMail = (rs.getString("EMAIL"));
                orderQuality = (rs.getString("QUALITY"));
                orderLength = (rs.getInt("LENGTH"));
                guard = (rs.getString("GARDA"));
                handle = (rs.getString("HANDLE"));
                braiding = (rs.getString("HANDLE_BRAIDING"));
                engraving = (rs.getString("ENGRAVING"));
                header = (rs.getString("POMMEL"));
                scabbard = (rs.getString("SCABBARD"));
            }

        } catch (SQLException e ) {
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

    public String getOrderName() {
        return orderName;
    }

    public void setOrderName(String orderName) {
        this.orderName = orderName;
    }

    public String getOrderMail() {
        return orderMail;
    }

    public void setOrderMail(String orderMail) {
        this.orderMail = orderMail;
    }

    public String getOrderQuality() {
        return orderQuality;
    }

    public void setOrderQuality(String orderQuality) {
        this.orderQuality = orderQuality;
    }

    public int getOrderLength() {
        return orderLength;
    }

    public void setOrderLength(int orderLength) {
        this.orderLength = orderLength;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

    public String getGuard() {
        return guard;
    }

    public void setGuard(String guard) {
        this.guard = guard;
    }

    public String getHandle() {
        return handle;
    }

    public void setHandle(String handle) {
        this.handle = handle;
    }

    public String getBraiding() {
        return braiding;
    }

    public void setBraiding(String braiding) {
        this.braiding = braiding;
    }

    public String getEngraving() {
        return engraving;
    }

    public void setEngraving(String engraving) {
        this.engraving = engraving;
    }

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public String getScabbard() {
        return scabbard;
    }

    public void setScabbard(String scabbard) {
        this.scabbard = scabbard;
    }

    public String getMetal() {
        return metal;
    }

    public void setMetal(String metal) {
        this.metal = metal;
    }

    public void orderMetal() throws SQLException{

        showDialog = false;
        int upperMass;
        int lowerMass;

        switch (orderQuality) {
            case "Вариба": lowerMass = Integer.parseInt(queries.getString("variba_mass"));
                break;
            case "Мару": lowerMass = Integer.parseInt(queries.getString("maru_mass"));
                break;
            case "Кобусэ": lowerMass = Integer.parseInt(queries.getString("kobuse_mass"));
                break;
            case "Хан-Санмаи-Авасэ": lowerMass = Integer.parseInt(queries.getString("han_mass"));
                break;
            case "Сихо-Дзумэ": lowerMass = Integer.parseInt(queries.getString("siho_mass"));
                break;
            default: lowerMass = 1;

        }

        lowerMass = lowerMass * orderLength;
        upperMass = lowerMass + 2;
        int mass = Integer.parseInt(metal);

        if ((mass < lowerMass) || (mass > upperMass))  {
            showDialog = true;
            flag=true;
        }
        else
        {
            SteelInfo orderMetal = new SteelInfo(0,metal,null,chosenOrder.getID());

            Statement stmt = null;

            String query = queries.getString("create_metal_order");

            query = String.format(query, metal, chosenOrder.getID(), 0);

            try {

                Connection con = DriverManager.getConnection(queries.getString("dburl"), queries.getString("dbuser"), queries.getString("dbpassword"));

                stmt = con.createStatement();

                stmt.executeUpdate(query);

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

    public void orderPolish() throws SQLException{
        showDialog = false;
        PolishInfo polishOrder = new PolishInfo(0,orderPolish,null,chosenOrder.getID());
        Statement stmt = null;

        String query = queries.getString("create_polish_order");

        query = String.format(query, orderPolish, chosenOrder.getID());

        try {

            Connection con = DriverManager.getConnection(queries.getString("dburl"), queries.getString("dbuser"), queries.getString("dbpassword"));

            stmt = con.createStatement();

            stmt.executeUpdate(query);

        } catch (SQLException e ) {
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

    public void orderAcces() throws SQLException{
        showDialog = false;
        AccesInfo orderAcces = new AccesInfo(0, guard, handle, braiding, engraving, header, scabbard,null,chosenOrder.getID());
        Statement stmt = null;

        String query = queries.getString("create_acces_order");

        query = String.format(query, guard, handle, braiding, engraving, header, scabbard, chosenOrder.getID());

        try {

            Connection con = DriverManager.getConnection(queries.getString("dburl"), queries.getString("dbuser"), queries.getString("dbpassword"));

            stmt = con.createStatement();

            stmt.executeUpdate(query);

        } catch (SQLException e ) {
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

    public void orderTest() throws SQLException{
        showDialog = false;
        TestingInfo testOrder = new TestingInfo(0,null,null,chosenOrder.getID());
        Statement stmt = null;

        String query = queries.getString("create_test_order");

        query = String.format(query, chosenOrder.getID());

        try {

            Connection con = DriverManager.getConnection(queries.getString("dburl"), queries.getString("dbuser"), queries.getString("dbpassword"));

            stmt = con.createStatement();

            stmt.executeUpdate(query);

        } catch (SQLException e ) {
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

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public String getOrderPolish() { return orderPolish; }

    public void setOrderPolish(String orderPolish) { this.orderPolish = orderPolish; }

    public void changeOrderStatus() throws SQLException {
        showDialog = false;
        Statement stmt = null;

        String query = queries.getString("change_order_status");

        query = String.format(query, orderStatus, chosenOrder.getID());

        try {

            Connection con = DriverManager.getConnection(queries.getString("dburl"), queries.getString("dbuser"), queries.getString("dbpassword"));

            stmt = con.createStatement();
            stmt.executeUpdate(query);

            getOrdersFromDB();

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
