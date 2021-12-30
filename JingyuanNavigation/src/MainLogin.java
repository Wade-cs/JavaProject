import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;


// ��Ժ������������
public class MainLogin {

    // �������壬���ñ���
    JFrame jf = new JFrame("��Ժ����ϵͳ");

    public MainLogin() {
        // ���ñ����ǩ
        JLabel lb0 = new JLabel("��Ժ����ϵͳ");

        // ���Ͻǻ���У��
        ImageIcon icon=new ImageIcon("src/image/logo.jpg");
        jf.setIconImage(icon.getImage());

        // ���ر���ͼƬ
        ImageIcon background = new ImageIcon("src/image/0.jpg");
        JLabel label = new JLabel(background);


        // ����ͼƬ��ǩ��ʾλ�úʹ�С
        label.setBounds(50, 0, 1100, 680);

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

        JButton jb1 = new JButton("��ѯ�ص�");
        JButton jb2 = new JButton("��ѯ·��");
        JButton jb3 = new JButton("��ʾ��ͼ");
        JButton jb4 = new JButton("���ӵص�");
        JButton jb5 = new JButton("ɾ���ص�");

        jb1.setFont(new Font("����", Font.PLAIN, 20));
        jb2.setFont(new Font("����", Font.PLAIN, 20));
        jb3.setFont(new Font("����", Font.PLAIN, 20));
        jb4.setFont(new Font("����", Font.PLAIN, 20));
        jb5.setFont(new Font("����", Font.PLAIN, 20));

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
        jf.setLocationRelativeTo(null); //����Ļ�м���ʾ(������ʾ)
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //�˳��ر�JFrame
        jf.setResizable(false);
        jf.setVisible(true); //��ʾ����

        // ����ѯ�ص���¼�����
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

        // ����ѯ·�����¼�����
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

        // �����ӵص���¼�����
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

        // �����ӵص���¼�����
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

        // ��ɾ���ص���¼�����
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