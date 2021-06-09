package SuperMarket;

import javax.swing.*;
import java.awt.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

public class Commodity extends JFrame {
    //支持鼠标滚动的面版
    JScrollPane jsp;
    //用于显示内容表格对象
    JTable jt;

    JComboBox comboBox = new JComboBox();


    JLabel searchMode = new JLabel("搜索方式");
    JTextField input = new JTextField();
    JButton search = new JButton("搜索");
    public Commodity(){
        this.setSize(800,600);
        this.setLayout(null);
        //this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setTitle("商品管理");
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setVisible(true);

        comboBox.addItem("编号");
        comboBox.addItem("名称");
        comboBox.addItem("价格");


        comboBox.setBounds(120,0,80,30);

        searchMode.setFont(new Font("华文楷体",1,20));
        searchMode.setForeground(Color.red);
        searchMode.setBounds(20,0,90,30);
        input.setBounds(200,0,300,30);
        search.setFont(new Font("华文楷体",1,20));
        search.setBackground(new Color(237,237,237));
        search.setForeground(Color.red);
        search.setBounds(510,0,80,30);

        this.add(input);
        this.add(search);
        this.add(searchMode);
        this.add(comboBox);

        ResultSet rs = SQL.query("SELECT * FROM commodity",false,null);
        init(rs);
    }
    public void init(ResultSet rs){
        Vector name = new Vector();
        name.add("商品编号");
        name.add("商品名称");
        name.add("商品价格");
        name.add("商品数量");
        name.add("商品备注");

        Vector data = new Vector();
        while(true)
        {
            try {
                if (!rs.next()) break;
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            //创建单行vector对象
            Vector hang=new Vector();
            try {
                hang.add(rs.getInt(1));
                hang.add(rs.getString(2));
                hang.add(rs.getInt(3));
                hang.add(rs.getInt(4));
                hang.add(rs.getString(5));
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }

        data.add(hang);

        jt = new JTable(data,name);
        jt.getTableHeader().setReorderingAllowed(false);//不可拖动表头

        //通过JTable对象创建支持鼠标滚动面板
        jsp =new JScrollPane(jt);
        jsp.setBounds(0,30,800,570);
        this.add(jsp);

    }

}
}
