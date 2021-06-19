package SuperMarket;
import javax.swing.*;
import java.security.PublicKey;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.Vector;

public class SQL {


    public static ResultSet query(String sqlSentence, boolean isSetVual, String vual){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn= DriverManager.getConnection("jdbc:mysql://localhost:3306/supermarket", "root", "111111");
            PreparedStatement ps = conn.prepareStatement(sqlSentence);
            if (isSetVual){
                ps.setString(1,vual);
            }
            ResultSet rs = ps.executeQuery();
            return rs;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }
    public static boolean insert(String sqlsentence, Vector data){
        Random rand = new Random();
        int d_id = rand.nextInt(90000000)+10000000;
        try {

            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/supermarket", "root", "111111");
            PreparedStatement ps = conn.prepareStatement(sqlsentence);
            ps.setString(1, (String) data.get(0));
            ps.setString(2, (String) data.get(1));
            ps.setString(3, String.valueOf(d_id));
            ps.setString(4, (String) data.get(3));
            ps.setInt(5, (Integer) data.get(4));

            //PreparedStatement ps1 = conn.prepareStatement("SELECT * FROM student");
            //ResultSet rs = ps1.executeQuery();


            int res = ps.executeUpdate();
            if (res==1){
                return true;
            }else {
                return false;
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }catch (SQLException e){
            e.printStackTrace();
        }


        return true;
    }







   /* public static int modify(student stu){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/student", "root", "111111");
            PreparedStatement ps = conn.prepareStatement("update student set C=?,java=?,python=? where xh=?");
            ps.setInt(1,stu.getC());
            ps.setInt(2,stu.getJava());
            ps.setInt(3,stu.getPy());
            ps.setInt(4,stu.getXh());

            int res = ps.executeUpdate();
            return res;

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }catch (SQLException e){
            System.out.println(e);
        }
        return 0;
    }*/
}
