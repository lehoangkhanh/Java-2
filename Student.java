package terminal;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.*;

public class Student {
    private int iid,age;
    private String name;
    
    public Student(){
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Student.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public Student(int id){
        this();
        this.iid= id ;
        Connection con = null;
        try {
            con =DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databaseName=HoangKhanh;user=sa;password=sa");
            PreparedStatement stm=con.prepareStatement("Select id,name,age from Student where id = ?");
            stm.setInt(1, iid);
            ResultSet rs= stm.executeQuery();
            if(rs.next()){
                iid = rs.getInt(1);
                name = rs.getString(2);
                age = rs.getInt(3);
            }
        } catch(Exception e){
                    System.out.println(e.getMessage());                    
                    }            
            finally{
            try {
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(Student.class.getName()).log(Level.SEVERE, null, ex);
            }                    
        }   
    }

    public Student(int iid, int age, String name) {
        this.iid = iid;
        this.age = age;
        this.name = name;
    }

    public int getIid() {
        return iid;
    }

    public void setIid(int iid) {
        this.iid = iid;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    @Override
    public String toString() {
        return "Xin chào sinh viên {" + "id= " + iid + ", Họ và tên= " + name + ", Tuổi= " + age + '}';
    }
    
    public Student ThemStudent(String name, int age){
        Connection con = null;
        try {
            con = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databaseName=HoangKhanh;user=sa;password=sa");
            PreparedStatement stm=con.prepareStatement("Insert into Student(name,age) values(?,?) ");
            stm.setString(1, name);
            stm.setInt(2, age);
            int rc = stm.executeUpdate();
            if(rc==1){
                 stm=con.prepareStatement("Update Student set name,age where name =?, age=? ");
                 stm.setString(1, name);
                 stm.setInt(2, age);
                 
            }
//            con.close();
            return new Student(iid);
        } catch (SQLException ex) {
            Logger.getLogger(Student.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    
    
    public static void main(String[] args) {
        Student st = new Student(2);
        st.ThemStudent("Nguyễn Thị Hoài", 21);
        System.out.println(st);
    }
    
}
