import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;

// // 景院导航：增加地点
public class AddNode {

    // 创建窗体，设置标题
    JFrame jf = new JFrame("景院景院系统");

    public AddNode() throws IOException {

        final Solve solve = new Solve();
        // 设置标题标签
        JLabel lb0 = new JLabel("景院景院系统");

        // 将咖啡换成校徽
        Toolkit t = Toolkit.getDefaultToolkit();
        Image img = t.getImage("src/image/logo.jpg");

        // 加载背景图片
        ImageIcon background = new ImageIcon("src/image/地图3.0.jpg");
        JLabel label = new JLabel(background);

        // 设置校徽
        jf.setIconImage(img);

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

        JLabel a = new JLabel("请填写增加地点信息", JLabel.CENTER);
        a.setFont(new Font("楷体", Font.PLAIN, 30));
        a.setForeground(Color.RED);
        JLabel b = new JLabel("地点名称:", JLabel.CENTER);
        b.setFont(new Font("楷体", Font.PLAIN, 17));
        b.setForeground(Color.BLACK);
        JLabel c = new JLabel("地点横坐标:", JLabel.CENTER);
        c.setFont(new Font("楷体", Font.PLAIN, 17));
        c.setForeground(Color.BLACK);
        final JLabel d = new JLabel("地点纵坐标:", JLabel.CENTER);
        d.setFont(new Font("楷体", Font.PLAIN, 17));
        d.setForeground(Color.BLACK);
        JLabel e = new JLabel("地点介绍:", JLabel.CENTER);
        e.setFont(new Font("楷体", Font.PLAIN, 17));
        e.setForeground(Color.BLACK);
        JTextArea f = new JTextArea("您可以在左侧的地图上，鼠标点击事件。直观获取要添加地点的x、y坐标。");
        f.setFont(new Font("楷体",Font.PLAIN,20));

        f.setForeground(Color.black);

        final JTextField b1 = new JTextField();
        final JTextField c1 = new JTextField();
        final JTextField d1 = new JTextField();
        final JTextField e1 = new JTextField();
        JButton exit = new JButton("退出");
        exit.setFont(new Font("楷体", Font.PLAIN, 20));
        exit.setForeground(Color.YELLOW);
        exit.setBackground(Color.RED);
        exit.setBorderPainted(false);
        JButton sure = new JButton("确认");
        sure.setFont(new Font("楷体", Font.PLAIN, 20));
        sure.setForeground(Color.BLACK);
        sure.setBackground(Color.YELLOW);
        sure.setBorderPainted(false);
        a.setBounds(1118, 125, 300, 50);
        b.setBounds(1118, 280, 100, 30);
        c.setBounds(1118, 315, 100, 30);
        d.setBounds(1118, 350, 100, 30);
        e.setBounds(1118, 385, 100, 30);
        f.setBounds(1118,500,380,200);
        f.setLineWrap(true);
        f.setWrapStyleWord(true);
        f.setOpaque(false);
        b1.setBounds(1218, 280, 130, 25);
        c1.setBounds(1218, 315, 130, 25);
        d1.setBounds(1218, 350, 130, 25);
        e1.setBounds(1218, 385, 130, 25);
        exit.setBounds(1118, 600, 150, 30);
        sure.setBounds(1318, 600, 150, 30);


        label.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                c1.setText(e.getX() + "");
                d1.setText(e.getY() + "");
            }
        });


        // 给确认按钮添加事件监听
        sure.addMouseListener(new MouseListener() {
            public void mouseClicked(MouseEvent a) {
                int id = 0;
                if (a.getButton() == a.BUTTON1) {
                    String x = JOptionPane.showInputDialog("请输入密码！");
                    if (x != null && x.equals("123456789")) {
                        // 遍历所有点，如果有序号空，则优先填充该序号，若中间不为空，则就加在最后一个地点之后
                        for (int i = 1; i <= 1010; i++) {
                            if (!solve.vis[i]) {
                                id = i;
                                break;
                            }
                        }
                        // 判断输入的点坐标是否为空或不是整数,并判断该点是否已经存在
                        //b1.getText() == solve.name[solve.checkname(b1.getText())]
                        //solve.checkname() 如果填写的地址如果存在则会返回 地址的id，这样用 solve.name[id] == b1.getText() 进行判断
                        if ( c1.getText().length() == 0 || !isnum(c1.getText()) ||d1.getText().length() == 0 || !isnum(d1.getText()) || b1.getText() == solve.name[solve.checkname(b1.getText())] )
                            show_error();
                        else {
                            solve.cnt++;
                            show_add(id); // 显示添加成功
                            solve.vis[id] = true;  // 设置该点
                            solve.name[id] = b1.getText();
                            solve.dian[id].x = Integer.parseInt(c1.getText());
                            solve.dian[id].y = Integer.parseInt(d1.getText());
                            solve.introduction[id] = e1.getText();
                            solve.update_map();  // 更新map
                            try {
                                solve.write1();
                                solve.write2();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    } else if (x != null)
                        JOptionPane.showMessageDialog(null, "密码错误", "警告！", JOptionPane.ERROR_MESSAGE);
                }
            }

            public void mousePressed(MouseEvent a) {
            }

            public void mouseReleased(MouseEvent a) {
            }

            public void mouseEntered(MouseEvent a) {
            }

            public void mouseExited(MouseEvent a) {
            }
        });

        // 退出按钮的事件绑定
        exit.addMouseListener(new MouseListener() {
            public void mouseClicked(MouseEvent a) {
                int id = 0;
                if (a.getButton() == a.BUTTON1) {
                    jf.setVisible(false);
                    new MainLogin();
                }
            }

            public void mousePressed(MouseEvent a) {
            }

            public void mouseReleased(MouseEvent a) {
            }

            public void mouseEntered(MouseEvent a) {
            }

            public void mouseExited(MouseEvent a) {
            }
        });


        jpanel.setLayout(null);
        jpanel.add(a);
        jpanel.add(b);
        jpanel.add(c);
        jpanel.add(d);
        jpanel.add(b1);
        jpanel.add(c1);
        jpanel.add(d1);
        jpanel.add(exit);
        jpanel.add(sure);
        jpanel.add(e);
        jpanel.add(f);
        jpanel.add(e1);
        jf.add(jpanel, BorderLayout.CENTER);
        jf.setSize(1500, 900);
        jf.setVisible(true);
        jf.setLocationRelativeTo(null); //在屏幕中间显示(居中显示)

        jf.setResizable(false);

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


    }

    public void show_delete(int id) {
        JOptionPane.showMessageDialog(null, id + "号点已被删除!", "成功", JOptionPane.INFORMATION_MESSAGE);
    }

    public void show_error() {
        JOptionPane.showMessageDialog(null, "输入值非法!", "错误!", JOptionPane.ERROR_MESSAGE);
    }

    public void show_add(int id) {
        JOptionPane.showMessageDialog(null, id + "号点被增加!", "成功", JOptionPane.INFORMATION_MESSAGE);
    }

    // 判断输入是否数字
    // Character.isDigit( c )，其中c是字符，这个方法可以用于判断字符c是不是数字形式的字符。
    public boolean isnum(String s) {
        for (int i = 0; i < s.length(); i++) {
            if (!Character.isDigit(s.charAt(i)))
                return false;
        }
        return true;
    }

}
