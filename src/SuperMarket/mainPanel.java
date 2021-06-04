package SuperMarket;

import javax.swing.*;
import java.awt.*;

public class mainPanel extends JPanel {
    JButton btn1 = new JButton();
    public mainPanel(){
        this.setBounds(0,0,900,600);
        this.setBackground(new Color(255,255,255));
        btn1.setIcon(Data.log1);
        btn1.setBounds(10,20,50,50);
        this.add(btn1);

    }
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

    }
}
