import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;

//导航：查询地点UI
public class QuerySite {
    // 创建窗体，设置标题
    JFrame jf = new JFrame("景院导航系统");
    final Solve solve = new Solve();

    public QuerySite() throws IOException {

        // 设置标题标签
        JLabel lb0 = new JLabel("景院导航系统");

        // 将咖啡换成校徽
        ImageIcon icon = new ImageIcon("src/image/logo.jpg");
        jf.setIconImage(icon.getImage());

        // 加载背景图片
        ImageIcon background = new ImageIcon("src/image/地图3.0.jpg");
        JLabel label = new JLabel(background);

        // 设置图片标签显示位置和大小
        label.setBounds(0, 0, background.getIconWidth(), background.getIconHeight());

        //使用窗体的分层面板,主界面获得容器JPanel，设置显示背景图的标签在LayeredPane的最前边。
        jf.getLayeredPane().add(label, new Integer(Integer.MIN_VALUE));

        // 获取ContentPane面板对象,强制转化成JPanel
        JPanel jp = (JPanel) jf.getContentPane();


        // JPanel对象才可以调用setOpaque(false);设置是否透明
        jp.setOpaque(false);
        JPanel jpanel = new JPanel();
        jpanel.setOpaque(false);

        // 去除JPanel默认布局方式,以实现各个控件自己的定位
        jpanel.setLayout(null);

        lb0.setFont(new Font("楷体", Font.PLAIN, 40));
        lb0.setForeground(Color.RED);


        JLabel lb1 = new JLabel("请输入查询地点名称:", JLabel.CENTER);
        lb1.setFont(new Font("楷体", Font.PLAIN, 35));
        lb1.setForeground(Color.RED);
        lb1.setBounds(12150, -100, 360, 240);

        JButton jb = new JButton("确定");
        jb.setBorderPainted(false);
        jb.setFont(new Font("楷体", Font.PLAIN, 18));
        jb.setForeground(Color.BLACK);
        jb.setBackground(Color.YELLOW);
        jb.setBorderPainted(false);


        final JTextField c = new JTextField(40);
        c.setFont(new Font("楷体", Font.PLAIN, 20));
        final JTextArea text = new JTextArea();
        text.setFont(new Font("楷体", Font.PLAIN, 20));
        text.setOpaque(false);

        c.setBounds(1150, 200, 200, 50);
        jb.setBounds(1350, 200, 70, 50);
        text.setBounds(1140, 300, 400, 200);
        text.setText("地点简介");

        JButton exit = new JButton("退出");
        exit.setFont(new Font("楷体", Font.PLAIN, 20));
        exit.setForeground(Color.WHITE);
        exit.setBackground(Color.RED);
        exit.setBorderPainted(false);
        exit.setBounds(1200, 600, 100, 60);

        jpanel.add(lb0);
        jpanel.add(lb1);
        jpanel.add(jb);
        jpanel.add(c);
        jpanel.add(text);
        jpanel.add(exit);

        jf.add(jpanel);
        jf.setSize(1490, 900);
        jf.setLocationRelativeTo(null); //在屏幕中间显示(居中显示)
        //jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //退出关闭JFrame
        jf.setResizable(false);
        jf.setVisible(true); //显示窗体

        //监听事件：监听窗口关闭程序
        //适配器模式：
        jf.addWindowListener(new WindowAdapter() {
            //窗体点击关闭时，要做的事
            @Override
            public void windowClosing(WindowEvent e) {
                super.windowClosing(e);
                new MainLogin();
            }
        });

        // 给确定按钮添加鼠标监听
        jb.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                String s = c.getText();
                int u = solve.checkname(s);  // 获取名字对应的ID
                if (s.length() == 0 || u > solve.cnt || !solve.vis[u]) {
                    show_error();
                    text.setText("");
                } else

                    text.setText(query(u));

            }
        });

        exit.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                jf.setVisible(false);
                new MainLogin();
            }
        });
    }

    public void show_error() {
        JOptionPane.showMessageDialog(null, "输入值非法!", "错误!", JOptionPane.ERROR_MESSAGE);
    }

    public String query(int id) {
        String s = "";
        s += "地点编号:" + Integer.toString(id) + "\n";
        s += "地点名称:" + solve.name[id] + "\n";
        s += "地点坐标:(" + Integer.toString(solve.dian[id].x) + "," + Integer.toString(solve.dian[id].y) + ")" + "\n";
        s += "地点介绍:" + solve.introduction[id];
        return s;
    }

}
