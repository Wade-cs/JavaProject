import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;

// չʾ��ͼ
public class ShowMap {

    // �������壬���ñ���
    JFrame jf = new JFrame("��Ժ����ϵͳ");

    public ShowMap() throws IOException {

        final Solve solve = new Solve();

        // �����Ȼ���У��
        Toolkit t = Toolkit.getDefaultToolkit();
        Image img = t.getImage("src/image/logo.jpg");

        // ���ر���ͼƬ
        ImageIcon background = new ImageIcon("src/image/��ͼ4.0.jpg");
        JLabel label = new JLabel(background);

        // ����У��
        jf.setIconImage(img);

        // ����ͼƬ��ǩ��ʾλ�úʹ�С<�����34��������Ϊ���߿�ҲҪ�����ȥ>
        label.setBounds(0, 0, background.getIconWidth(), background.getIconHeight());

        //ʹ�ô���ķֲ����,������������JPanel��������ʾ����ͼ�ı�ǩ��LayeredPane����ǰ�ߡ�
        jf.getLayeredPane().add(label, new Integer(Integer.MIN_VALUE));

        // ��ȡContentPane������,ǿ��ת����JPanel
        JPanel jp = (JPanel) jf.getContentPane();

        // JPanel����ſ��Ե���setOpaque(false);
        // �����Ƿ�͸��
        jp.setOpaque(false);
        JPanel jpanel = new JPanel();

        //���������������û��Ч��,������Ǳ���ͼƬ�����ڴ�����ǰ��,�����˵�.
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

        // ȥ��JPanelĬ�ϲ��ַ�ʽ,��ʵ�ָ����ؼ��Լ��Ķ�λ
        jpanel.setLayout(null);

        jf.setBounds(0,0,background.getIconWidth(), background.getIconHeight()+34);
        jf.setLocationRelativeTo(null); //����Ļ�м���ʾ(������ʾ)
        jf.setVisible(true);
        jf.setResizable(false);
        // jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //�˳��ر�JFrame

        // �����¼����������ڹرճ���
        // ������ģʽ��
        jf.addWindowListener(new WindowAdapter() {
            //�������ر�ʱ��Ҫ������
            @Override
            public void windowClosing(WindowEvent e) {
                super.windowClosing(e);
                new MainLogin();
            }
        });
    }
}
