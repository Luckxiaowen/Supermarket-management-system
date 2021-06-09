package SuperMarket;

import java.sql.*;

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
