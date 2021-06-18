package SuperMarket;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.nio.charset.StandardCharsets;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

public class Purchase {
    private JComboBox<String> goods;
    private JComboBox<String> vendor;

    JTable jt;
    JScrollPane jsp;

    int index;


    Vector datas = new Vector();

    JButton shopingCart = new JButton("加入货单");
    JButton checkOut = new JButton("结账");

    JLabel commodity = new JLabel("商品:");
    JLabel price = new JLabel("进价:");
    JLabel quantity = new JLabel("数量:");
    //JLabel supplier = new JLabel("供货商:");
    JTextField priceText = new JTextField();
    JTextField quantityText = new JTextField();
    public Purchase(){
        ResultSet res = SQL.query("SELECT * FROM kind",false,null);
        ResultSet res1 = SQL.query("SELECT * FROM supply",false,null);
        Vector<String> kind = new Vector<String>();
        Vector<String> vdor = new Vector<String>();
        Vector Price = new Vector();
        while (true){
            try {
                if (!res.next()) break;
                kind.add(res.getString(2));
                Price.add(res.getInt(3));

            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }



        window win = new window(350,500,"进货");
        win.setLayout(null);

        commodity.setFont(new Font("华文楷体",1,20));
        commodity.setBounds(20,20,50,30);

        price.setFont(new Font("华文楷体",1,20));
        price.setBounds(20,70,50,30);

        priceText.setBounds(100,70,200,30);
        priceText.setEditable(false);

        quantity.setFont(new Font("华文楷体",1,20));
        quantity.setBounds(20,120,50,30);

        quantityText.setBounds(100,120,200,30);

        //supplier.setFont(new Font("华文楷体",1,20));
        //supplier.setBounds(5,170,70,30);

        shopingCart.setFont(new Font("华文楷体",1,15));
        shopingCart.setForeground(Color.red);
        shopingCart.setBackground(Color.CYAN);
        shopingCart.setBounds(100,250,120,30);

        checkOut.setFont(new Font("华文楷体",1,15));
        checkOut.setForeground(Color.red);
        checkOut.setBackground(Color.CYAN);
        checkOut.setBounds(100,300,120,30);
        checkOut.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JLabel slp = new JLabel("供货商:");

                JButton ok = new JButton("确定结账");
                window win2 = new window(600,400,"购物车");
                win2.setLayout(null);
                ok.setBounds(250,0,100,30);
                ok.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        Vector supplyData = new Vector();
                        window win3 = new window(300,500,"账单");
                        win3.setLayout(null);
                        slp.setBounds(5,30,50,30);
                        vendor = new JComboBox<String>();
                        while (true){
                            try {
                                if (!res1.next()) break;
                                vdor.add(res1.getString(2));
                            } catch (SQLException throwables) {
                                throwables.printStackTrace();
                            }
                        }
                        String tmpVendor[]=vdor.toArray(new String[vdor.size()]);
                        vendor = new JComboBox<String>(tmpVendor);
                        vendor.setSelectedItem(null);
                        vendor.setBounds(50,30,200,30);
                        win3.add(vendor);
                        win3.add(slp);
                    }
                });


                Vector name = new Vector();
                name.add("商品");
                name.add("价格");
                name.add("数量");
                name.add("小计");

                jt = new JTable(datas,name);
                jsp = new JScrollPane(jt);
                jsp.setBounds(0,30,600,370);
                win2.add(jsp);
                win2.add(ok);

            }
        });



        String tmpKind[]=kind.toArray(new String[kind.size()]);
        Object[] tmpPrice = Price.toArray();
        goods = new JComboBox<String>(tmpKind);
        goods.setSelectedIndex(0); //初始选择第一个选项
        goods.setBounds(100, 20, 200, 30);
        goods.setSelectedItem(null);
        goods.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                index = goods.getSelectedIndex();
                priceText.setText(tmpPrice[index].toString());
            }
        });

        shopingCart.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (goods.getItemAt(index).equals("") || priceText.getText().equals("") || quantityText.getText().equals("")){
                    JOptionPane.showMessageDialog(null,"数据有误，加入失败!","提示",0,null);
                    return;
                }
                Vector data = new Vector();
                data.add(goods.getItemAt(index));
                data.add(priceText.getText());
                data.add(quantityText.getText());
                int price = Integer.parseInt(quantityText.getText()) * Integer.parseInt(priceText.getText());
                data.add(price);
                datas.add(data);
                JOptionPane.showMessageDialog(null,"加入成功!","提示",0,null);
            }
        });



        win.add(commodity);
        win.add(price);
        //win.add(supplier);
        win.add(goods);
        win.add(priceText);
        win.add(quantity);
        win.add(quantityText);
        win.add(shopingCart);
        win.add(checkOut);

        //win.add(vendor);

    }

}
