package SuperMarket;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.Vector;

public class Purchase {
    private JComboBox<String> goods;//商品
    private JComboBox<String> vendor;//供货商
    private JComboBox<String> manager;//经手人

    Vector mInfo;

    JTable jt;
    JScrollPane jsp;

    int index;
    int index2;
    int index3;
    int count;
    Vector<Vector<String>> Data = new Vector();//批发市场信息
    Vector<Vector<String>> mangerinfo = new Vector();//经手人详细

    Vector datas = new Vector();

    Vector<String> managerName = new Vector<String>();//经手人姓名

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
        ResultSet res2 = SQL.query("SELECT * FROM user",false,null);
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

                JButton ok = new JButton("确定结账");
                window win2 = new window(600,400,"购物车");
                win2.setLayout(null);
                ok.setBounds(250,0,100,30);
                ok.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        while (true){
                            Vector<String> managerData = new Vector<String>();//经手人姓名信息
                            try {
                                if (!res2.next()) break;
                                managerData.add(res2.getInt(1)+"");
                                managerName.add(res2.getString(2));
                                managerData.add(res2.getString(3));
                                mangerinfo.add(managerData);

                            } catch (SQLException throwables) {
                                throwables.printStackTrace();
                            }
                        }




                        JButton okCheckout = new JButton("确定结账");

                        JLabel slpId = new JLabel("商家编号:");
                        JLabel slpTeTelephone = new JLabel("商家电话:");
                        JLabel slpRegistId = new JLabel("商家注册号:");
                        JLabel slp = new JLabel("供货商:");
                        JLabel Manager = new JLabel("经手人:");
                        JLabel totalMoney = new JLabel("总金额:");

                        JTextField slpIdText = new JTextField();
                        JTextField slpTelephoneText = new JTextField();
                        JTextField slpRegistIdText = new JTextField();
                        JTextField totalMoneyText = new JTextField();

                        okCheckout.setBounds(100,300,80,40);

                        window win3 = new window(300,500,"账单");
                        win3.setLayout(null);

                        slp.setBounds(5,30,60,30);
                        slpId.setBounds(5,70,60,30);
                        slpTeTelephone.setBounds(5,110,60,30);
                        slpRegistId.setBounds(0,150,65,30);
                        Manager.setBounds(5,190,60,30);
                        totalMoney.setBounds(5,230,60,30);

                        slpIdText.setBounds(70,70,200,30);
                        slpIdText.setEditable(false);
                        slpTelephoneText.setBounds(70,110,200,30);
                        slpTelephoneText.setEditable(false);
                        slpRegistIdText.setBounds(70,150,200,30);
                        slpRegistIdText.setEditable(false);
                        totalMoneyText.setBounds(70,230,200,30);
                        totalMoneyText.setEditable(false);

                        vendor = new JComboBox<String>();
                        while (true){
                            Vector<String> supplyData = new Vector<String>();
                            try {
                                if (!res1.next()) break;
                                vdor.add(res1.getString(2));
                                supplyData.add(res1.getString(1));
                                supplyData.add(res1.getString(3));
                                supplyData.add(res1.getString(4));
                                Data.add(supplyData);
                            } catch (SQLException throwables) {
                                throwables.printStackTrace();
                            }
                        }

                        String tmpVendor[]=vdor.toArray(new String[vdor.size()]);
                        vendor = new JComboBox<String>(tmpVendor);
                        vendor.setSelectedItem(null);
                        vendor.setBounds(70,30,200,30);
                        vendor.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                Vector tmpData[] = Data.toArray(new Vector[Data.size()]);
                                index2 = vendor.getSelectedIndex();
                                Vector data = tmpData[index2];
                                //slpIdText.setText();
                                slpIdText.setText(data.get(0).toString());
                                slpTelephoneText.setText(data.get(1).toString());
                                slpRegistIdText.setText(data.get(2).toString());
                            }
                        });

                        String tmpMaragerData[] = managerName.toArray(new String[managerName.size()]);
                        manager = new JComboBox<String>(tmpMaragerData);
                        manager.setSelectedItem(null);
                        manager.setBounds(70,190,200,30);

                        int result = sumMoney(count);
                        totalMoneyText.setText(""+result);

                        win3.add(vendor);
                        win3.add(slp);
                        win3.add(slpTeTelephone);
                        win3.add(slpId);
                        win3.add(slpRegistId);
                        win3.add(slpIdText);
                        win3.add(slpTelephoneText);
                        win3.add(slpRegistIdText);
                        win3.add(Manager);
                        win3.add(manager);
                        win3.add(totalMoneyText);
                        win3.add(totalMoney);
                        win3.add(okCheckout);

                        manager.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {

                                index3 = manager.getSelectedIndex();

                            }
                        });

                        okCheckout.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                if (totalMoneyText.getText().equals("0") || slpIdText.getText().equals("") || manager.getSelectedItem()==null ){
                                    JOptionPane.showMessageDialog(null,"结账失败,请检查数据是否正确或者完全!","提示",0,null);
                                    return;
                                }
                                Vector tmpMangerData[] = mangerinfo.toArray(new Vector[mangerinfo.size()]);
                                mInfo = tmpMangerData[index3];
                                Random rand = new Random();
                                int d_id = rand.nextInt(90000000)+10000000;
                                String s_id = slpIdText.getText();
                                String u_id = mInfo.get(0).toString();
                                Date date = new Date();
                                SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
                                String time = formatter.format(date);
                                int total = Integer.parseInt(totalMoneyText.getText());
                                Vector data = new Vector();
                                data.add(s_id);
                                data.add(u_id);
                                data.add(d_id);
                                data.add(time);
                                data.add(total);

                                String sql = "insert into warehouse(s_id,u_id,d_id,date,total) values (?,?,?,?,?)";
                                boolean isSucc = SQL.insertOrder(sql,data);
                                if (isSucc){
                                    JOptionPane.showMessageDialog(null,"进货成功","提示",0,null);
                                    ruku(count);
                                    win.setVisible(false);
                                    win2.setVisible(false);
                                    win3.setVisible(false);
                                }else {
                                    JOptionPane.showMessageDialog(null,"失败","提示",0,null);
                                    return;
                                }




                            }
                        });

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
                count++;
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
    public int sumMoney(int count){
        int total = 0;
        for (int i = 0; i < count; i++) {
            int tmp = Integer.parseInt(jt.getValueAt(i,3).toString());

            total += tmp;
        }
        return total;
    }

    public void ruku(int count){
        Vector<Vector<String>> data = new Vector<Vector<String >>();
        for (int i = 0; i < count; i++) {
            Vector temp = new Vector();
            for (int j = 0;j < 3; j++){
                temp.add(jt.getValueAt(i,j));
            }
            data.add(temp);
        }


        Vector arrData[] = data.toArray(new Vector[data.size()]);
        for (int i = 0; i < count; i++) {
            ResultSet res = SQL.query("SELECT * FROM commodity",false,null);
            int flag = 0;
            Vector info = arrData[i];
            while (true){
                try {
                    if (!res.next())break;
                    if (res.getString(2).equals(info.get(0))){
                        int amount = res.getInt(4) + Integer.parseInt((String) info.get(2));
                        SQL.upData("update commodity set amount=? where name=?",amount,res.getString(2));
                        flag=1;
                        break;
                    }
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
            if (flag == 0)SQL.insertCommodity("insert into commodity(name,price,amount) values (?,?,?)",info);
        }


    }

}
