import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;

// 展示地图
public class ShowMap {

    // 创建窗体，设置标题
    JFrame jf = new JFrame("景院导航系统");

    public ShowMap() throws IOException {

        final Solve solve = new Solve();

        // 将咖啡换成校徽
        Toolkit t = Toolkit.getDefaultToolkit();
        Image img = t.getImage("src/image/logo.jpg");

        // 加载背景图片
        ImageIcon background = new ImageIcon("src/image/地图4.0.jpg");
        JLabel label = new JLabel(background);

        // 设置校徽
        jf.setIconImage(img);

        // 设置图片标签显示位置和大小<另外加34像素是因为，边框也要计算进去>
        label.setBounds(0, 0, background.getIconWidth(), background.getIconHeight());

        //使用窗体的分层面板,主界面获得容器JPanel，设置显示背景图的标签在LayeredPane的最前边。
        jf.getLayeredPane().add(label, new Integer(Integer.MIN_VALUE));

        // 获取ContentPane面板对象,强制转化成JPanel
        JPanel jp = (JPanel) jf.getContentPane();

        // JPanel对象才可以调用setOpaque(false);
        // 设置是否透明
        jp.setOpaque(false);
        JPanel jpanel = new JPanel();

        //下面这个画点死活没有效果,大概率是背景图片设置在窗体最前面,挡到了点.
        jpanel= new JPanel() {
            public void paint(Graphics graphics) {
                super.paint(graphics);
                graphics.setColor(Color.RED);
                for (int i = 1; i <= solve.cnt; i++) {
                    if (solve.vis[i])
                        graphics.fillOval(solve.dian[i].x, solve.dian[i].y, 7, 7);
                }
            }
        };

        jpanel.setOpaque(true);

        // 去除JPanel默认布局方式,以实现各个控件自己的定位
        jpanel.setLayout(null);

        jf.setBounds(0,0,background.getIconWidth(), background.getIconHeight()+34);
        jf.setLocationRelativeTo(null); //在屏幕中间显示(居中显示)
        jf.setVisible(true);
        jf.setResizable(false);
        // jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //退出关闭JFrame

        // 监听事件：监听窗口关闭程序
        // 适配器模式：
        jf.addWindowListener(new WindowAdapter() {
            //窗体点击关闭时，要做的事
            @Override
            public void windowClosing(WindowEvent e) {
                super.windowClosing(e);
                new MainLogin();
            }
        });
    }
}
