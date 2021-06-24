package SuperMarket;

import javax.swing.*;

public class window extends JFrame {
    public window(int width,int height,String title){
        this.setSize(width, height);
        this.setTitle(title);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        this.setResizable(false);

    }
}
