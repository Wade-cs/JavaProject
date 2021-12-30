import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;


// 景院导航：主窗口
public class MainLogin {

    // 创建窗体，设置标题
    JFrame jf = new JFrame("景院导航系统");

    public MainLogin() {
        // 设置标题标签
        JLabel lb0 = new JLabel("景院导航系统");

        // 左上角换成校徽
        ImageIcon icon=new ImageIcon("src/image/logo.jpg");
        jf.setIconImage(icon.getImage());

        // 加载背景图片
        ImageIcon background = new ImageIcon("src/image/0.jpg");
        JLabel label = new JLabel(background);


        // 设置图片标签显示位置和大小
        label.setBounds(50, 0, 1100, 680);

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

        JButton jb1 = new JButton("查询地点");
        JButton jb2 = new JButton("查询路径");
        JButton jb3 = new JButton("显示地图");
        JButton jb4 = new JButton("增加地点");
        JButton jb5 = new JButton("删除地点");

        jb1.setFont(new Font("楷体", Font.PLAIN, 20));
        jb2.setFont(new Font("楷体", Font.PLAIN, 20));
        jb3.setFont(new Font("楷体", Font.PLAIN, 20));
        jb4.setFont(new Font("楷体", Font.PLAIN, 20));
        jb5.setFont(new Font("楷体", Font.PLAIN, 20));

        lb0.setBounds(500, 25, 300, 50);
        jb1.setBounds(550, 120, 120, 50);
        jb2.setBounds(550, 200, 120, 50);
        jb3.setBounds(550, 280, 120, 50);
        jb4.setBounds(550, 360, 120, 50);
        jb5.setBounds(550, 440, 120, 50);

        jpanel.add(lb0);
        jpanel.add(jb1);
        jpanel.add(jb2);
        jpanel.add(jb3);
        jpanel.add(jb4);
        jpanel.add(jb5);

        jf.add(jpanel);
        jf.setBounds(600, 600, 1200, 680);
        jf.setLocationRelativeTo(null); //在屏幕中间显示(居中显示)
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //退出关闭JFrame
        jf.setResizable(false);
        jf.setVisible(true); //显示窗体

        // 给查询地点绑定事件监听
        jb1.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                jf.setVisible(false);
                try {
                    new QuerySite();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        });

        // 给查询路径绑定事件监听
        jb2.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                jf.setVisible(false);
                try {
                    new QueryPath();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        });

        // 给增加地点绑定事件监听
        jb3.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                jf.setVisible(false);
                try {
                    new ShowMap();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        });

        // 给增加地点绑定事件监听
        jb4.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                jf.setVisible(false);
                try {
                    new AddNode();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        });

        // 给删除地点绑定事件监听
        jb5.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                jf.setVisible(false);
                try {
                    new DeleteNode();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        });

    }

    public static void main(String[] args) {
        new MainLogin();
    }
}