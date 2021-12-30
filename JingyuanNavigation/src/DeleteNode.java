import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;

//// 哈哈导航：删除地点
public class DeleteNode {

    // 创建窗体，设置标题
    JFrame jf = new JFrame("景院导航系统");
    final Solve solve = new Solve();

    public DeleteNode() throws IOException {


        // 设置标题标签
        JLabel lb0 = new JLabel("景院导航系统");

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

        JLabel a = new JLabel("请输入要删除地点名称:", JLabel.CENTER);
        a.setFont(new Font("隶书", Font.PLAIN, 26));
        a.setBounds(1115, 160, 300, 100);
        final JButton b = new JButton("选择");
        b.setBorderPainted(false);
        b.setFont(new Font("隶书", Font.PLAIN, 15));
        b.setForeground(Color.BLACK);
        b.setBackground(Color.GREEN);
        JButton exit = new JButton("退出");
        exit.setFont(new Font("隶书", Font.PLAIN, 20));
        exit.setForeground(Color.BLACK);
        exit.setBackground(Color.YELLOW);
        exit.setBorderPainted(false);
        JButton sure = new JButton("确认");
        sure.setFont(new Font("隶书", Font.PLAIN, 20));
        sure.setForeground(Color.WHITE);
        sure.setBackground(Color.RED);
        sure.setBorderPainted(false);
        final JTextField c = new JTextField();
        final JTextArea text = new JTextArea();
        text.setOpaque(false);
        text.setFont(new Font("隶书", Font.PLAIN, 20));

        b.addMouseListener(new MouseListener() {
            public void mouseClicked(MouseEvent a) {
                if (a.getButton() == a.BUTTON1) {
                    String s = c.getText();
                    int u = solve.checkname(s);
                    if (s.length() == 0 || u > solve.cnt || !solve.vis[u]) {
                        show_error();
                        text.setText("");
                    } else
                        text.setText(query(u));
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

        // 当按下回车键时，即按下选择按钮，即查询内容
        c.addKeyListener(new KeyListener() {
            public void keyPressed(KeyEvent arg0) {
            }

            public void keyReleased(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    String s = c.getText();
                    int u = solve.checkname(s);
                    if (s.length() == 0 || u > solve.cnt || !solve.vis[u]) {
                        show_error();
                        text.setText("");
                    } else
                        text.setText(query(u));
                }
            }

            public void keyTyped(KeyEvent arg0) {
            }
        });

        // 给退出绑定单击事件
        exit.addMouseListener(new MouseListener() {
            public void mouseClicked(MouseEvent a) {
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

        sure.addMouseListener(new MouseListener() {
            public void mouseClicked(MouseEvent a) {
                if (a.getButton() == a.BUTTON1) {
                    String x = JOptionPane.showInputDialog("请输入密码！");
                    if (x != null && x.equals("123456789")) {
                        String s = c.getText();
                        int u = solve.checkname(s);
                        if (s.length() == 0 || u > solve.cnt || !solve.vis[u]) {
                            show_error();
                            text.setText("");
                        } else {
                            solve.vis[u] = false;
                            try {
                                solve.write1();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                            try {
                                solve.write2();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                            text.setText("");
                            show_delete(u);
                        }
                    } else if (x != null)
                        JOptionPane.showMessageDialog(null, "密码错误", "错误!", JOptionPane.ERROR_MESSAGE);
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
        c.setBounds(1200, 300, 120, 21);
        b.setBounds(1315, 300, 70, 21);
        text.setBounds(1150, 400, 350, 100);
        exit.setBounds(1315, 600, 150, 30);
        sure.setBounds(1115, 600, 150, 30);
        jpanel.add(c);
        jpanel.add(b);
        jpanel.add(a);
        jpanel.add(text);
        jpanel.add(exit);
        jpanel.setLayout(null);
        jpanel.add(sure);

        jf.add(jpanel, BorderLayout.CENTER);
        jf.setSize(1490, 900);
        jf.setVisible(true);

        jf.setResizable(false);
        jf.setLocationRelativeTo(null); //在屏幕中间显示(居中显示)

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

    public boolean isnum(String s) {
        for (int i = 0; i < s.length(); i++) {
            if (!Character.isDigit(s.charAt(i)))
                return false;
        }
        return true;
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
