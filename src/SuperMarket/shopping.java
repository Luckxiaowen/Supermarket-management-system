package SuperMarket;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Vector;

public class shopping implements ActionListener{
    int index;
    int index2;
    int index3;
    int count;
    Vector buyData = new Vector();

    window win;
    window win2;
    window win3;


    JTable jt;

    JComboBox<String> name;


    JTextField payPeopleIdText;
    JTextField payPeopleTelText;
    JTextField payPeopleMoenyText;
    JTextField buyMoenyText;
    JTextField buyAfterMoenyText;
    JTextField dearleIdText;

    Vector<String> teacherName = new Vector<String>();


    JLabel goodsLab = new JLabel("商品:");
    JLabel goodsPriceLab = new JLabel("价格:");
    JLabel goodsAmountLab = new JLabel("库存:");
    JLabel buyAmountLab = new JLabel("购买数量:");
    JTextField goodsAmountText = new JTextField();
    JTextField buyAmountText = new JTextField();
    JTextField goodsPriceText = new JTextField();
    JButton joinShoppingBtn = new JButton("加入购物车");
    JButton payMoneyBtn = new JButton("买单");
    private JComboBox<String> shopping;//商品
    Vector<String> shopingName = new Vector<String>();
    Vector<Vector<String>> shopInfo = new Vector<Vector<String>>();
    Vector<Vector<String>> teacherInfo = new Vector<Vector<String>>();
    public shopping(){
        ResultSet resultSet = SQL.query("SELECT * FROM commodity",false,null);
        while (true){
            Vector<String> temp = new Vector<String>();
            try {
                if (!resultSet.next())break;
                shopingName.add(resultSet.getString(2));
                temp.add(resultSet.getInt(3)+"");
                temp.add(resultSet.getInt(4)+"");
                temp.add(resultSet.getString(5));
                shopInfo.add(temp);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        Vector shopArr[] = shopInfo.toArray(new Vector[shopInfo.size()]);

        win = new window(350,500,"购物");
        win.setLayout(null);

        goodsLab.setBounds(20,30,50,30);
        goodsPriceLab.setBounds(20,80,50,30);
        goodsAmountLab.setBounds(20,130,50,30);
        buyAmountLab.setBounds(0,180,60,30);

        goodsPriceText.setBounds(70,80,200,30);
        goodsPriceText.setEditable(false);
        goodsAmountText.setBounds(70,130,200,30);
        goodsAmountText.setEditable(false);
        buyAmountText.setBounds(70,180,200,30);

        joinShoppingBtn.setBounds(50,230,90,30);
        payMoneyBtn.setBounds(180,230,90,30);

        shopping = new JComboBox<>(shopingName);
        shopping.setBounds(70,30,200,30);
        shopping.setSelectedItem(null);
        shopping.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                index = shopping.getSelectedIndex();
                Vector info = shopArr[index];
                goodsPriceText.setText((String) info.get(0));
                goodsAmountText.setText((String) info.get(1));
            }
        });

        joinShoppingBtn.addActionListener(this);
        payMoneyBtn.addActionListener(this);


        win.add(shopping);
        win.add(goodsLab);
        win.add(goodsPriceLab);
        win.add(goodsPriceText);
        win.add(goodsAmountLab);
        win.add(goodsAmountText);
        win.add(buyAmountLab);
        win.add(buyAmountText);
        win.add(payMoneyBtn);
        win.add(joinShoppingBtn);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("加入购物车")){
            if (shopping.getSelectedItem()==null || buyAmountText.getText().equals("") || buyAmountText.getText().equals("0")){
                JOptionPane.showMessageDialog(null,"加入失败,请检查!","提示",0,null);
                return;
            }
            Vector temp = new Vector();
            int money = Integer.parseInt(goodsPriceText.getText()) * Integer.parseInt(buyAmountText.getText());
            temp.add(shopping.getSelectedItem());
            temp.add(buyAmountText.getText());
            temp.add(money);
            buyData.add(temp);
            JOptionPane.showMessageDialog(null,"加入成功","提示",0,null);
            count++;
        }
        else if (e.getActionCommand().equals("买单")){
            JButton buyBtn = new JButton("确定结账");

            Vector name = new Vector();
            name.add("商品");
            name.add("购买数量");
            name.add("小计");
            win2 = new window(600,400,"购物车");
            win2.setLayout(null);

            buyBtn.setBounds(260,0,80,30);
            buyBtn.addActionListener(this);

            jt = new JTable(buyData,name);
            jt.getTableHeader().setReorderingAllowed(false);//不可拖动表头
            JScrollPane jsp = new JScrollPane(jt);
            jsp.setBounds(0,30,600,370);
            win2.add(jsp);
            win2.add(buyBtn);
        }
        else if (e.getActionCommand().equals("确定结账")){
            JLabel payPeopleLab = new JLabel("付款人:");
            JLabel payPeopleIdLab = new JLabel("付款人ID:");
            JLabel payPeopleTelLab = new JLabel("付款人电话:");
            JLabel payPeopleMoenyLab = new JLabel("卡内余额:");
            JLabel buyMoenyLab = new JLabel("购物金额:");
            JLabel buyAfterMoenyLab = new JLabel("买后余额:");
            JLabel dealerLab = new JLabel("经手人:");
            JLabel dearleIdLab = new JLabel("经手人ID:");


            JComboBox<String> dealerName;

            JButton ok = new JButton("确定");

            payPeopleIdText = new JTextField();
            payPeopleTelText = new JTextField();
            payPeopleMoenyText = new JTextField();
            buyMoenyText = new JTextField();
            buyAfterMoenyText = new JTextField();
            dearleIdText = new JTextField();

            win3 = new window(300,350,"账单");
            win3.setLayout(null);


            Vector<String> userName = new Vector<String>();

            ResultSet resultSet = SQL.query("SELECT * FROM teacher",false,null);
            ResultSet resultSet1 = SQL.query("SELECT * FROM user",false,null);

            Vector userId = new Vector();
            while (true){
                try {
                    if (!resultSet1.next())break;
                    userName.add(resultSet1.getString(2));
                    userId.add(resultSet1.getInt(1));
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }

            }

            while (true){
                Vector<String> temp = new Vector<String>();
                try {
                    if (!resultSet.next())break;
                    teacherName.add(resultSet.getString(2));
                    temp.add(resultSet.getInt(1)+"");
                    temp.add(resultSet.getString(3));
                    temp.add(resultSet.getInt(4)+"");
                    teacherInfo.add(temp);
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }

            payPeopleLab.setBounds(10,5,60,30);
            payPeopleIdLab.setBounds(10,40,60,30);
            payPeopleTelLab.setBounds(5,75,65,30);
            payPeopleMoenyLab.setBounds(10,110,60,30);
            buyMoenyLab.setBounds(10,145,60,30);
            buyAfterMoenyLab.setBounds(10,180,60,30);
            dealerLab.setBounds(10,215,60,30);
            dearleIdLab.setBounds(10,240,60,30);

            ok.setBounds(100,270,80,30);
            ok.addActionListener(this);

            payPeopleIdText.setBounds(80,40,200,30);
            payPeopleIdText.setEditable(false);
            payPeopleTelText.setBounds(80,75,200,30);
            payPeopleTelText.setEditable(false);
            payPeopleMoenyText.setBounds(80,110,200,30);
            payPeopleMoenyText.setEditable(false);
            buyMoenyText.setBounds(80,140,200,30);
            buyMoenyText.setEditable(false);
            buyAfterMoenyText.setBounds(80,180,200,30);
            buyAfterMoenyText.setEditable(false);
            dearleIdText.setBounds(80,240,200,30);
            dearleIdText.setEditable(false);

            name = new JComboBox<>(teacherName);
            name.setBounds(80,5,200,30);
            name.setSelectedItem(null);
            name.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    int total = sumMoney(count);

                    Vector teacherArr[] = teacherInfo.toArray(new Vector[teacherInfo.size()]);
                    index2 = name.getSelectedIndex();
                    Vector data = teacherArr[index2];
                    payPeopleIdText.setText((String) data.get(0));
                    payPeopleTelText.setText((String) data.get(1));
                    payPeopleMoenyText.setText((String) data.get(2));
                    buyMoenyText.setText(total+"");
                    int afterTotal = Integer.parseInt(payPeopleMoenyText.getText()) - total;
                    buyAfterMoenyText.setText(afterTotal+"");
                }
            });

            dealerName = new JComboBox<>(userName);
            dealerName.setBounds(80,210,200,30);
            dealerName.setSelectedItem(null);
            dealerName.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    index3 = dealerName.getSelectedIndex();
                    //System.out.println(index3);
                    dearleIdText.setText(userId.get(index3)+"");
                }
            });

            win3.add(name);
            win3.add(payPeopleLab);
            win3.add(payPeopleIdLab);
            win3.add(payPeopleTelLab);
            win3.add(payPeopleMoenyLab);
            win3.add(payPeopleIdText);
            win3.add(payPeopleMoenyText);
            win3.add(payPeopleTelText);
            win3.add(buyAfterMoenyLab);
            win3.add(buyAfterMoenyText);
            win3.add(buyMoenyText);
            win3.add(buyMoenyLab);
            win3.add(dealerName);
            win3.add(dealerLab);
            win3.add(dearleIdLab);
            win3.add(dearleIdText);
            win3.add(ok);



        }
        else if (e.getActionCommand().equals("确定")){
            Date date = new Date();
            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
            String time = formatter.format(date);
            int amount = sumAmount(count);
            Vector data = new Vector();
            data.add(time);
            data.add(amount);
            data.add(Integer.parseInt(buyMoenyText.getText()));
            data.add(payPeopleIdText.getText());
            data.add(dearleIdText.getText());
            boolean res = SQL.insertchuku("insert into outbound(d_id,out_date,out_amount,out_total,t_id,u_id) values (?,?,?,?,?,?)",data);
            if (res){
                JOptionPane.showMessageDialog(null,"购买成功!","提示",0,null);
                buy(count);
                SQL.upData("update teacher set t_money=? where t_name=?",Integer.parseInt(buyAfterMoenyText.getText()),teacherName.get(index2));
                //System.out.println(name.getItemAt(index));
                System.out.println(index2);
                win.setVisible(false);
                win2.setVisible(false);
                win3.setVisible(false);
            }else {
                JOptionPane.showMessageDialog(null,"购买失败!","提示",0,null);
            }
        }
    }
    private int sumMoney(int count){
        int total = 0;
        for (int i = 0; i < count; i++) {
            int temp = Integer.parseInt(jt.getValueAt(i,2).toString());
            total += temp;
        }
        return total;
    }
    private int sumAmount(int count){
        int total = 0;

        for (int i = 0; i < count; i++) {
            int temp = Integer.parseInt(jt.getValueAt(i,1).toString());
            total += temp;
        }

        return total;
    }
    private void buy(int count){
        Vector<Vector<String>> data = new Vector<Vector<String >>();
        for (int i = 0; i < count; i++) {
            Vector temp = new Vector();
            for (int j = 0;j < 2; j++){
                temp.add(jt.getValueAt(i,j));
            }
            data.add(temp);
        }
        Vector arrData[] = data.toArray(new Vector[data.size()]);
        for (int i = 0; i < count; i++) {
            ResultSet res = SQL.query("SELECT * FROM commodity",false,null);
            Vector info = arrData[i];
            while (true){
                try {
                    if (!res.next())break;
                    if (res.getString(2).equals(info.get(0))){
                        int amount = res.getInt(4) - Integer.parseInt((String) info.get(1));
                        SQL.upData("update commodity set amount=? where name=?",amount,res.getString(2));
                    }
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }

    }
}

}
