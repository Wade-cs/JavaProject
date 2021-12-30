import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;

//��������ѯ�ص�UI
public class QuerySite {
    // �������壬���ñ���
    JFrame jf = new JFrame("��Ժ����ϵͳ");
    final Solve solve = new Solve();

    public QuerySite() throws IOException {

        // ���ñ����ǩ
        JLabel lb0 = new JLabel("��Ժ����ϵͳ");

        // �����Ȼ���У��
        ImageIcon icon = new ImageIcon("src/image/logo.jpg");
        jf.setIconImage(icon.getImage());

        // ���ر���ͼƬ
        ImageIcon background = new ImageIcon("src/image/��ͼ3.0.jpg");
        JLabel label = new JLabel(background);

        // ����ͼƬ��ǩ��ʾλ�úʹ�С
        label.setBounds(0, 0, background.getIconWidth(), background.getIconHeight());

        //ʹ�ô���ķֲ����,������������JPanel��������ʾ����ͼ�ı�ǩ��LayeredPane����ǰ�ߡ�
        jf.getLayeredPane().add(label, new Integer(Integer.MIN_VALUE));

        // ��ȡContentPane������,ǿ��ת����JPanel
        JPanel jp = (JPanel) jf.getContentPane();


        // JPanel����ſ��Ե���setOpaque(false);�����Ƿ�͸��
        jp.setOpaque(false);
        JPanel jpanel = new JPanel();
        jpanel.setOpaque(false);

        // ȥ��JPanelĬ�ϲ��ַ�ʽ,��ʵ�ָ����ؼ��Լ��Ķ�λ
        jpanel.setLayout(null);

        lb0.setFont(new Font("����", Font.PLAIN, 40));
        lb0.setForeground(Color.RED);


        JLabel lb1 = new JLabel("�������ѯ�ص�����:", JLabel.CENTER);
        lb1.setFont(new Font("����", Font.PLAIN, 35));
        lb1.setForeground(Color.RED);
        lb1.setBounds(12150, -100, 360, 240);

        JButton jb = new JButton("ȷ��");
        jb.setBorderPainted(false);
        jb.setFont(new Font("����", Font.PLAIN, 18));
        jb.setForeground(Color.BLACK);
        jb.setBackground(Color.YELLOW);
        jb.setBorderPainted(false);


        final JTextField c = new JTextField(40);
        c.setFont(new Font("����", Font.PLAIN, 20));
        final JTextArea text = new JTextArea();
        text.setFont(new Font("����", Font.PLAIN, 20));
        text.setOpaque(false);

        c.setBounds(1150, 200, 200, 50);
        jb.setBounds(1350, 200, 70, 50);
        text.setBounds(1140, 300, 400, 200);
        text.setText("�ص���");

        JButton exit = new JButton("�˳�");
        exit.setFont(new Font("����", Font.PLAIN, 20));
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
        jf.setLocationRelativeTo(null); //����Ļ�м���ʾ(������ʾ)
        //jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //�˳��ر�JFrame
        jf.setResizable(false);
        jf.setVisible(true); //��ʾ����

        //�����¼����������ڹرճ���
        //������ģʽ��
        jf.addWindowListener(new WindowAdapter() {
            //�������ر�ʱ��Ҫ������
            @Override
            public void windowClosing(WindowEvent e) {
                super.windowClosing(e);
                new MainLogin();
            }
        });

        // ��ȷ����ť���������
        jb.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                String s = c.getText();
                int u = solve.checkname(s);  // ��ȡ���ֶ�Ӧ��ID
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
        JOptionPane.showMessageDialog(null, "����ֵ�Ƿ�!", "����!", JOptionPane.ERROR_MESSAGE);
    }

    public String query(int id) {
        String s = "";
        s += "�ص���:" + Integer.toString(id) + "\n";
        s += "�ص�����:" + solve.name[id] + "\n";
        s += "�ص�����:(" + Integer.toString(solve.dian[id].x) + "," + Integer.toString(solve.dian[id].y) + ")" + "\n";
        s += "�ص����:" + solve.introduction[id];
        return s;
    }

}
