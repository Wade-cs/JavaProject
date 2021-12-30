import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;

// 景院导航：查询路径
public class QueryPath {
    // 创建窗体，设置标题
    JFrame jf = new JFrame("景院导航系统");
    final Solve solve = new Solve();

    public QueryPath() throws IOException {

        // 将咖啡换成校徽
        Image icon = Toolkit. getDefaultToolkit().getImage("src/image/logo.jpg");
        jf.setIconImage(icon);

        // 加载背景图片
        ImageIcon background = new ImageIcon("src/image/地图3.0.jpg" );

        JLabel label = new JLabel(background);

        // 设置图片标签显示位置和大小
        label.setBounds(0, 0, background.getIconWidth(), background.getIconHeight());

        //使用窗体的分层面板,主界面获得容器JPanel，设置显示背景图的标签在LayeredPane的最前边。
        jf.getLayeredPane().add(label, new Integer(Integer.MIN_VALUE));

        // 获取ContentPane面板对象,强制转化成JPanel
        JPanel jp = (JPanel) jf.getContentPane();

        // JPanel对象才可以调用setOpaque(false);设置是否透明
        jp.setOpaque(false);


        // 获取ContentPane面板对象,强制转化成JPanel
        JPanel jp2 = (JPanel) jf.getContentPane();

        // JPanel对象才可以调用setOpaque(false);设置是否透明
        jp2.setOpaque(false);


        JPanel jpanel = new JPanel();
        jpanel.setOpaque(false);

        // 去除JPanel默认布局方式,以实现各个控件自己的定位
        jpanel.setLayout(null);

        JLabel a = new JLabel("起点:", JLabel.CENTER);
        a.setFont(new Font("楷体", Font.PLAIN, 25));
        a.setBounds(1118, 20, 100, 20);
        JLabel b = new JLabel("终点:", JLabel.CENTER);
        b.setFont(new Font("楷体", Font.PLAIN, 25));
        b.setBounds(1118, 50, 100, 20);
        final JTextField c = new JTextField();
        final JTextField d = new JTextField();
        c.setBounds(1218, 23, 170, 20);
        d.setBounds(1218, 53, 170, 20);
        JButton timeshao = new JButton("最短时间");
        timeshao.setFont(new Font("楷体", Font.PLAIN, 15));
        timeshao.setForeground(Color.BLACK);
        timeshao.setBackground(Color.GREEN);
        timeshao.setBorderPainted(false);
        final JTextArea text = new JTextArea();
        text.setBounds(1118, 195, 300, 300);
        text.setOpaque(false);
        jpanel.add(text);


        // 给最短时间绑定事件
        timeshao.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                int num1 = solve.checkname(c.getText());
                int num2 = solve.checkname(d.getText());
                if (num1 == num2 && num1 != 0) {
                    JOptionPane.showMessageDialog(null, "已经在目的地!", "提示", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    if (!solve.vis[num1] || !solve.vis[num2] || num1 == 0 || num2 == 0 || num1 > solve.cnt || num2 > solve.cnt)
                        show_error();
                    else {
                        solve.dijkstra(num1, num2);
                        Graphics graphics = jf.getGraphics();
                        newpaint(graphics);
                        String ans = "";
                        for (int i = solve.plan_cnt; i >= 0; i--) {
                            // 最后一个点不加箭头
                            if (i != 0) {
                                ans += solve.name[solve.ans[i]] + "--->";
                            } else
                                ans += solve.name[solve.ans[i]];
                            // 最后一个点直接跳出，不打印步行二字
                            if (i == 0)
                                continue;
                            ans += "步行\n";

                        }
                        ans += "\n预计时间:" + String.format("%.2f", solve.dis[num2] * 6 / 10000) + "h";
                        text.setFont(new Font("楷体", Font.PLAIN, 19));
                        text.setText(ans);
                        text.setForeground(Color.BLACK);
                    }
                }
            }
        });

        JLabel tuijian;    //标签
        tuijian = new JLabel("推荐路线", JLabel.CENTER);
        tuijian.setFont(new Font("楷体", Font.PLAIN, 20));
        tuijian.setBounds(1118, 140, 100, 50);
        jpanel.add(tuijian);
        JButton lujingduan = new JButton("最短路径");
        lujingduan.setBorderPainted(false);
        lujingduan.setFont(new Font("楷体", Font.PLAIN, 15));
        lujingduan.setForeground(Color.WHITE);
        lujingduan.setBackground(Color.RED);

        // 给最短路径添加事件监听
        lujingduan.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                int num1 = solve.checkname(c.getText());
                int num2 = solve.checkname(d.getText());
                if (num1 == num2 && num1 != 0) {
                    JOptionPane.showMessageDialog(null, "已经在目的地!", "提示", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    if (!solve.vis[num1] || !solve.vis[num2] || num1 == 0 || num2 == 0 || num1 > solve.cnt || num2 > solve.cnt)
                        show_error();
                    else {
                        solve.dijkstra(num1, num2);
                        Graphics graphics = jf.getGraphics();
                        newpaint(graphics);
                        String ans = "";
                        System.out.println("======================================");
                        for (int i = solve.plan_cnt; i >= 0; i--) {

                            //检验坐标的准确性
                            System.out.println(solve.dian[solve.ans[i]].x + "  "+solve.dian[solve.ans[i]].y);

                            if (i != 0) {
                                ans += solve.name[solve.ans[i]] + "--->";
                            } else
                                ans += solve.name[solve.ans[i]];
                            if (i == 0)
                                continue;

                            ans += "步行\n";

                        }
                        //Format(String, Object)  将指定的 String 中的格式项替换为指定的 Object 实例的值的文本等效项

                        ans += "\n预计距离:" + String.format("%.2f", solve.dis[num2]*2) + "m";
                        text.setFont(new Font("楷体", Font.PLAIN, 19));
                        text.setText(ans);
                        text.setForeground(Color.BLACK);
                    }
                }
            }
        });

        //鼠标实时获取坐标


        JButton exit = new JButton("退出");
        exit.setBorderPainted(false);
        exit.setFont(new Font("楷体", Font.PLAIN, 20));
        exit.setForeground(Color.BLACK);
        exit.setBackground(Color.YELLOW);
        exit.setBounds(1218, 700, 100, 40);

        exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jf.setVisible(false);
                new MainLogin();
            }
        });

        jpanel.add(exit);
        jpanel.add(a);
        jpanel.add(b);
        jpanel.add(c);
        jpanel.add(d);
        lujingduan.setBounds(1118, 99, 100, 30);
        jpanel.add(timeshao);
        jpanel.add(lujingduan);

        jf.add(jpanel, BorderLayout.CENTER);
        jpanel.setLayout(null);
        timeshao.setBounds(1318, 99, 100, 30);
        jf.setSize(1490, 900);

        jf.setLocationRelativeTo(null); //在屏幕中间显示(居中显示)
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

    }

    public void show_error() {
        JOptionPane.showMessageDialog(null, "输入值非法!", "错误!", JOptionPane.ERROR_MESSAGE);
    }

    // 在图上进行标点，画线路
    public void newpaint(Graphics graphics) {
        jf.paint(graphics);
        graphics.setColor(Color.GREEN);
        graphics.fillOval(10,10,10,10);
        for (int i = 0; i <= solve.plan_cnt; i++) {
            graphics.fillOval(solve.dian[solve.ans[i]].x-3, solve.dian[solve.ans[i]].y-3+34, 7, 7);
        }
        graphics.setColor(Color.RED);
        for (int i = 0; i < solve.plan_cnt; i++) {
            if (solve.vis[solve.ans[i]] && solve.vis[solve.ans[i + 1]]) {
                //<另外加34像素是因为，边框也要计算进去>
                graphics.drawLine((solve.dian[solve.ans[i]].x),(solve.dian[solve.ans[i]].y+34),(solve.dian[solve.ans[i + 1]].x), (solve.dian[solve.ans[i + 1]].y+34));
            }
        }
    }
}
