package SuperMarket;

import javax.swing.*;
import java.net.URL;

public class Data {
    public static URL BackgroundUrl =Data.class.getResource("/static/2.jpg");
    public static ImageIcon Background=new ImageIcon(BackgroundUrl);

    public static URL BackgroundUrl2 = Data.class.getResource("/static/bk2.jpg");
    public static ImageIcon Background2 = new ImageIcon(BackgroundUrl2);

    public static URL logUrl1 = Data.class.getResource("/static/商店.jpg");
    public static ImageIcon log1 = new ImageIcon(logUrl1);

    public static URL logUrl2 = Data.class.getResource("/static/购物.jpg");
    public static ImageIcon log2 = new ImageIcon(logUrl2);

    public static URL logUrl3 = Data.class.getResource("/static/商品.jpg");
    public static ImageIcon log3 = new ImageIcon(logUrl3);

    public static URL logUrl4 = Data.class.getResource("/static/收银.jpg");
    public static ImageIcon log4 = new ImageIcon(logUrl4);

    public static URL logUrl5 = Data.class.getResource("/static/用户.jpg");
    public static ImageIcon log5 = new ImageIcon(logUrl5);

    public static URL logUrl6 = Data.class.getResource("/static/账号注销.jpg");
    public static ImageIcon log6 = new ImageIcon(logUrl6);

    public static URL logUrl7 = Data.class.getResource("/static/退出.jpg");
    public static ImageIcon log7 = new ImageIcon(logUrl7);
}

