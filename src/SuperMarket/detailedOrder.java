package SuperMarket;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

public class detailedOrder {
    window win;
    JTable jt;
    JScrollPane jsp;
    Vector datas = new Vector();
    public detailedOrder(){
        ResultSet res = SQL.query("SELECT * FROM outbound",false,null);
        win = new window(800,500,"收银明细");
        win.setLayout(null);
        Vector name = new Vector();
        name.add("收银编号");
        name.add("收银日期");
        name.add("出售商品数量");
        name.add("总金额");
        name.add("顾客id");
        name.add("收银人id");

        while (true){
            Vector data = new Vector();
            try {
                if (!res.next())break;
                data.add(res.getString(2));
                data.add(res.getString(3));
                data.add(res.getInt(4));
                data.add(res.getInt(5));
                data.add(res.getString(6));
                data.add(res.getString(7));
                datas.add(data);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }

        }
        jt = new JTable(datas,name);
        jsp = new JScrollPane(jt);
        jt.getTableHeader().setReorderingAllowed(false);//不可拖动表头


        jsp.setBounds(0,0,800,500);
        win.add(jsp);

    }
}
