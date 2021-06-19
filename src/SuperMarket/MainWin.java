package SuperMarket;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainWin extends JFrame implements ActionListener {
    JLabel title = new JLabel("欢 迎 使 用 星 期 八 超 市 管 理 系 统");
    JButton btn1 = new JButton(Data.log1);
    JButton btn2 = new JButton(Data.log2);
    JButton btn3 = new JButton(Data.log3);
    JButton btn4 = new JButton(Data.log4);
    JButton btn5 = new JButton(Data.log5);
    JButton btn6 = new JButton(Data.log6);
    JButton btn7 = new JButton(Data.log7);
    JLabel lab1 = new JLabel("商品库存");
    JLabel lab2 = new JLabel("进 货");
    JLabel lab3 = new JLabel("购 物");
    JLabel lab4 = new JLabel("收银明细");
    JLabel lab5 = new JLabel("会员管理");
    JLabel lab6 = new JLabel("账号注销");
    JLabel lab7 = new JLabel("退出系统");
    public MainWin(){
        this.setSize(900,600);
        this.setTitle("星期八超市");
        this.setVisible(true);
        this.setResizable(false);
        this.setBackground(new Color(255,255,255));
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLayout(null);

        ImageIcon background = Data.Background2;
        JLabel lbBg = new JLabel(background);
        lbBg.setBounds(0, 0, 900, 600);

        title.setFont(new Font("华文楷体",1,40));
        title.setBounds(110,10,700,40);
        title.setForeground(Color.RED);

        lab1.setFont(new Font("华文楷体",1,20));
        lab1.setBounds(40,125,90,40);
        lab2.setFont(new Font("华文楷体",1,20));
        lab2.setBounds(180,125,90,40);
        lab3.setFont(new Font("华文楷体",1,20));
        lab3.setBounds(300,125,90,40);
        lab4.setFont(new Font("华文楷体",1,20));
        lab4.setBounds(400,125,90,40);
        lab5.setFont(new Font("华文楷体",1,20));
        lab5.setBounds(520,125,90,40);
        lab6.setFont(new Font("华文楷体",1,20));
        lab6.setBounds(640,125,90,40);
        lab7.setFont(new Font("华文楷体",1,20));
        lab7.setBounds(760,125,90,40);

        btn1.setBounds(60,80,48,48);
        btn1.setBorderPainted(false);
       // btn1.setText("1");
        btn2.setBounds(180,80,48,48);
        btn2.setBorderPainted(false);
        btn3.setBounds(300,80,48,48);
        btn3.setBorderPainted(false);
        btn4.setBounds(420,80,48,48);
        btn4.setBorderPainted(false);
        btn5.setBounds(540,80,48,48);
        btn5.setBorderPainted(false);
        btn6.setBounds(660,80,48,48);
        btn6.setBorderPainted(false);
        btn7.setBounds(780,80,48,48);
        btn7.setBorderPainted(false);

        btn1.addActionListener(this);
        btn2.addActionListener(this);
        btn3.addActionListener(this);
        btn4.addActionListener(this);
        btn5.addActionListener(this);
        btn6.addActionListener(this);
        btn7.addActionListener(this);

        this.add(title);

        this.add(lab1);
        this.add(lab2);
        this.add(lab3);
        this.add(lab4);
        this.add(lab5);
        this.add(lab6);
        this.add(lab7);

        this.add(btn1);
        this.add(btn2);
        this.add(btn3);
        this.add(btn4);
        this.add(btn5);
        this.add(btn6);
        this.add(btn7);

        this.add(lbBg);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btn1){
            new Commodity();
        }else if(e.getSource() == btn2){
            new Purchase();
        }else if (e.getSource() == btn5){
            new VIP();
        }
    }
}
