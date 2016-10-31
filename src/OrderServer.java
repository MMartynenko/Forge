import java.io.Serializable;
import java.util.Random;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;


import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;
import java.text.*;

//@ManagedBean
//@SessionScoped
public class OrderServer implements Serializable {

    ResourceBundle queries = ResourceBundle.getBundle("Strings");

    String name = null;
    String mail = null;
    Integer length = null;
    String quality = null;
    String guard = null;
    String handle = null;
    String braiding = null;
    String engraving = null;
    String header = null;
    String scabbard = null;

    private boolean showMsg=false;
    private boolean flag=false;

    public boolean isShowMsg() {
        return showMsg;
    }

    public void setShowMsg(boolean showMsg) {
        this.showMsg = showMsg;
    }

    public void resetMsg() {
        if (flag) flag=false;
        else this.showMsg = false;
    }

    public String getQuality() {
        return quality;
    }

    public void setQuality(String quality) {
        this.quality = quality;
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

    public String getGuard() {
        return guard;
    }

    public void setGuard(String guard) {
        this.guard = guard;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public OrderServer() {
        //Class.forName("com.mysql.jdbc.Driver");
        showMsg=false;
    }


    public void setName(String _name){
        name = _name;
    }

    public String getName(){
        return name;
    }

    public void setLength(Integer _length){
        length = _length;
    }

    public Integer getLength(){
        return length;
    }

    public void formOrder() throws SQLException
    {
        showMsg=false;

        OrderInfo newOrder = new OrderInfo(0, name, null, null);

        Connection con = DriverManager.getConnection(queries.getString("dburl"), queries.getString("dbuser"), queries.getString("dbpassword"));

        Statement stmt = null;


        String query = queries.getString("create_order");

        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
        Date date = new Date();
        String dateString = dateFormat.format(date);

        query = String.format(query, name, mail, dateString, length, quality, guard, handle, braiding, engraving, header, scabbard);
        //System.out.println(query);

        try {
            stmt = con.createStatement();
            stmt.executeUpdate(query);
            showMsg=true;
            flag=true;

        } catch (SQLException e ) {
            e.printStackTrace(System.err);
            showMsg=false;
        } finally {
            if (stmt != null) { stmt.close(); con.close(); }
        }

    }


}
