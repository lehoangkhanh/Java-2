
package terminal;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Classs {
    private int iid;
    private String class_name;
    
    public Classs(){
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Student.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public Classs(int id){
        this();
        this.iid= id ;
        Connection con = null;
        try {
            con =DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databaseName=HoangKhanh;user=sa;password=sa");
            PreparedStatement stm=con.prepareStatement("Select id,class_name from Class where id = ?");
            stm.setInt(1, iid);
            ResultSet rs= stm.executeQuery();
            if(rs.next()){
                iid = rs.getInt(1);
                class_name = rs.getString(2);
            }
        } catch(Exception e){
                    System.out.println(e.getMessage());                    
                    }            
            finally{
            try {
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(Classs.class.getName()).log(Level.SEVERE, null, ex);
            }                    
        }   
    }

    public Classs(int iid, String class_name) {
        this.iid = iid;
        this.class_name = class_name;
    }

    public int getIid() {
        return iid;
    }

    public void setIid(int iid) {
        this.iid = iid;
    }

    public String getClass_name() {
        return class_name;
    }

    public void setClass_name(String class_name) {
        this.class_name = class_name;
    }

    @Override
    public String toString() {
        return "Môn học {" + "id= " + iid + ", Tên môn học= " + class_name +  '}';
    }
    
    public Classs ThemClass(String class_name){
        Connection con = null;
        try {
            con = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databaseName=HoangKhanh;user=sa;password=sa");
            PreparedStatement stm=con.prepareStatement("Insert into Class(class_name) values(?) ");
            stm.setString(1, class_name);
            int rc = stm.executeUpdate();
            if(rc==-1){
                 stm=con.prepareStatement("Update Class set class_name where class_name =? ");
                 stm.setString(1, class_name);
                 stm.executeUpdate();
            }
//            con.close();
            return new Classs(iid);
        } catch (SQLException ex) {
            Logger.getLogger(Classs.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    
    public static void main(String[] args) {
        Classs cs = new Classs(1);
        cs.ThemClass("Toán rời rạc");
        System.out.println(cs);
    }
    
    
}
