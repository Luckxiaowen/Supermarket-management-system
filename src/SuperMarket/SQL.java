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
    public static boolean insertOrder(String sqlsentence, Vector data){
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


    public static boolean insertCommodity(String sql,Vector data){

        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/supermarket", "root", "111111");
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, (String) data.get(0));
            ps.setInt(2, Integer.parseInt((String) data.get(1)));
            ps.setInt(3, Integer.parseInt((String) data.get(2)));
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


    public static boolean insertchuku(String sql,Vector data){
        Random rand = new Random();
        int d_id = rand.nextInt(90000000)+10000000;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/supermarket", "root", "111111");
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, d_id+"");
            ps.setString(2, (String) data.get(0));
            ps.setInt(3, (Integer) data.get(1));
            ps.setInt(4, (Integer) data.get(2));
            ps.setString(5, (String) data.get(3));
            ps.setString(6, (String) data.get(4));
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

    public static int upData(String sql,int value,String name){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/supermarket", "root", "111111");
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1,value);
            ps.setString(2,name);

            int res = ps.executeUpdate();
            return res;

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }catch (SQLException e){
            System.out.println(e);
        }
        return 0;
    }

    public static ResultSet query(int index){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn= DriverManager.getConnection("jdbc:mysql://localhost:3306/supermarket", "root", "111111");
            PreparedStatement ps = conn.prepareCall("{call C1(?)}");
            ps.setInt(1,index);
            ResultSet rs = ps.executeQuery();
            return rs;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    public static void del(String sql,int id){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn= DriverManager.getConnection("jdbc:mysql://localhost:3306/supermarket", "root", "111111");
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1,id);

            int res = ps.executeUpdate();
            if (res==1){
                JOptionPane.showMessageDialog(null,"注销成功","提示",0,null);
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
}
