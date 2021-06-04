package SuperMarket;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.security.PublicKey;

public class Login extends JFrame implements ActionListener {
    JLabel title = new JLabel("星期八超市管理系统");
    JLabel user = new JLabel("账号");
    JLabel pas = new JLabel("密码");
    JTextField userText = new JTextField();
    JPasswordField pasText = new JPasswordField();
    JButton Login = new JButton("登录");

    JPanel jp;
    public Login(){
        this.setSize(600,400);

        this.setLayout(null);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setTitle("星期八超市管理系统");
        this.setResizable(false);
        this.setLocationRelativeTo(null);

        ImageIcon background = Data.Background;
        JLabel lbBg = new JLabel(background);
        lbBg.setBounds(0, 0, 600, 400);

        title.setFont(new Font("华文彩云",1,30));
        title.setForeground(Color.yellow);
        title.setBounds(155,5,300,70);

        user.setBounds(110,85,60,60);
        user.setFont(new Font("华文楷体",1,25));
        user.setForeground(Color.white);

        pas.setBounds(110,145,60,60);
        pas.setFont(new Font("华文楷体",1,25));
        pas.setForeground(Color.white);

        userText.setBounds(180,100,250,35);
        userText.setFont(new Font("华文楷体",1,25));
        pasText.setBounds(180,160,250,35);
        pasText.setFont(new Font("华文楷体",1,25));


        Login.setBounds(250,220,70,50);
        Login.setFont(new Font("华文楷体",1,20));
        Login.setBorderPainted(false);
        Login.setForeground(Color.red);
        Login.setBackground(Color.blue);

        Login.addActionListener(this);

        jp = new JPanel();
        jp.setBounds(0, 0, 600, 400);
        jp.setLayout(null);
        jp.add(lbBg);

        this.add(title);
        this.add(user);
        this.add(pas);
        this.add(userText);
        this.add(pasText);
        this.add(Login);
        this.setVisible(true);

        this.add(jp);

        pasText.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == 10) {
                    login();
                }
            }
        });
    }

    public void login(){
        this.setVisible(false);
        new MainWin();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("登录")){
            login();
        }
    }
}
