package SuperMarket;

import javax.swing.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

public class VIP {
    JTextField search = new JTextField();
    JButton searchBtn = new JButton("搜索");
    JTable jt;
    JScrollPane jsp;
    Vector teacherInfo = new Vector();
    Vector teacherData = new Vector();
    public VIP(){
        window win = new window(600,400,"会员管理");
        win.setLayout(null);
        search.setBounds(100,5,250,30);
        searchBtn.setBounds(370,5,80,30);
        ResultSet resultSet = SQL.query("SELECT * FROM teacher",false,null);
        while (true){
            Vector temp = new Vector();
            try {
                if (!resultSet.next())break;
                temp.add(resultSet.getInt(1));
                temp.add(resultSet.getString(2));
                temp.add(resultSet.getString(3));
                temp.add(resultSet.getInt(4));
                teacherData.add(temp);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }

        teacherInfo.add("编号");
        teacherInfo.add("名字");
        teacherInfo.add("电话");
        teacherInfo.add("余额");

        jt = new JTable(teacherData,teacherInfo);
        jt.getTableHeader().setReorderingAllowed(false);//不可拖动表头
        jsp = new JScrollPane(jt);
        jsp.setBounds(0,35,600,365);



        win.add(search);
        win.add(searchBtn);
        win.add(jsp);
    }
}
