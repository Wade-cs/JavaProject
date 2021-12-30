import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;

// // ��Ժ���������ӵص�
public class AddNode {

    // �������壬���ñ���
    JFrame jf = new JFrame("��Ժ��Ժϵͳ");

    public AddNode() throws IOException {

        final Solve solve = new Solve();
        // ���ñ����ǩ
        JLabel lb0 = new JLabel("��Ժ��Ժϵͳ");

        // �����Ȼ���У��
        Toolkit t = Toolkit.getDefaultToolkit();
        Image img = t.getImage("src/image/logo.jpg");

        // ���ر���ͼƬ
        ImageIcon background = new ImageIcon("src/image/��ͼ3.0.jpg");
        JLabel label = new JLabel(background);

        // ����У��
        jf.setIconImage(img);

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

        JLabel a = new JLabel("����д���ӵص���Ϣ", JLabel.CENTER);
        a.setFont(new Font("����", Font.PLAIN, 30));
        a.setForeground(Color.RED);
        JLabel b = new JLabel("�ص�����:", JLabel.CENTER);
        b.setFont(new Font("����", Font.PLAIN, 17));
        b.setForeground(Color.BLACK);
        JLabel c = new JLabel("�ص������:", JLabel.CENTER);
        c.setFont(new Font("����", Font.PLAIN, 17));
        c.setForeground(Color.BLACK);
        final JLabel d = new JLabel("�ص�������:", JLabel.CENTER);
        d.setFont(new Font("����", Font.PLAIN, 17));
        d.setForeground(Color.BLACK);
        JLabel e = new JLabel("�ص����:", JLabel.CENTER);
        e.setFont(new Font("����", Font.PLAIN, 17));
        e.setForeground(Color.BLACK);
        JTextArea f = new JTextArea("�����������ĵ�ͼ�ϣ�������¼���ֱ�ۻ�ȡҪ��ӵص��x��y���ꡣ");
        f.setFont(new Font("����",Font.PLAIN,20));

        f.setForeground(Color.black);

        final JTextField b1 = new JTextField();
        final JTextField c1 = new JTextField();
        final JTextField d1 = new JTextField();
        final JTextField e1 = new JTextField();
        JButton exit = new JButton("�˳�");
        exit.setFont(new Font("����", Font.PLAIN, 20));
        exit.setForeground(Color.YELLOW);
        exit.setBackground(Color.RED);
        exit.setBorderPainted(false);
        JButton sure = new JButton("ȷ��");
        sure.setFont(new Font("����", Font.PLAIN, 20));
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


        // ��ȷ�ϰ�ť����¼�����
        sure.addMouseListener(new MouseListener() {
            public void mouseClicked(MouseEvent a) {
                int id = 0;
                if (a.getButton() == a.BUTTON1) {
                    String x = JOptionPane.showInputDialog("���������룡");
                    if (x != null && x.equals("123456789")) {
                        // �������е㣬�������ſգ�������������ţ����м䲻Ϊ�գ���ͼ������һ���ص�֮��
                        for (int i = 1; i <= 1010; i++) {
                            if (!solve.vis[i]) {
                                id = i;
                                break;
                            }
                        }
                        // �ж�����ĵ������Ƿ�Ϊ�ջ�������,���жϸõ��Ƿ��Ѿ�����
                        //b1.getText() == solve.name[solve.checkname(b1.getText())]
                        //solve.checkname() �����д�ĵ�ַ���������᷵�� ��ַ��id�������� solve.name[id] == b1.getText() �����ж�
                        if ( c1.getText().length() == 0 || !isnum(c1.getText()) ||d1.getText().length() == 0 || !isnum(d1.getText()) || b1.getText() == solve.name[solve.checkname(b1.getText())] )
                            show_error();
                        else {
                            solve.cnt++;
                            show_add(id); // ��ʾ��ӳɹ�
                            solve.vis[id] = true;  // ���øõ�
                            solve.name[id] = b1.getText();
                            solve.dian[id].x = Integer.parseInt(c1.getText());
                            solve.dian[id].y = Integer.parseInt(d1.getText());
                            solve.introduction[id] = e1.getText();
                            solve.update_map();  // ����map
                            try {
                                solve.write1();
                                solve.write2();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    } else if (x != null)
                        JOptionPane.showMessageDialog(null, "�������", "���棡", JOptionPane.ERROR_MESSAGE);
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

        // �˳���ť���¼���
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
        jf.setLocationRelativeTo(null); //����Ļ�м���ʾ(������ʾ)

        jf.setResizable(false);

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


    }

    public void show_delete(int id) {
        JOptionPane.showMessageDialog(null, id + "�ŵ��ѱ�ɾ��!", "�ɹ�", JOptionPane.INFORMATION_MESSAGE);
    }

    public void show_error() {
        JOptionPane.showMessageDialog(null, "����ֵ�Ƿ�!", "����!", JOptionPane.ERROR_MESSAGE);
    }

    public void show_add(int id) {
        JOptionPane.showMessageDialog(null, id + "�ŵ㱻����!", "�ɹ�", JOptionPane.INFORMATION_MESSAGE);
    }

    // �ж������Ƿ�����
    // Character.isDigit( c )������c���ַ�������������������ж��ַ�c�ǲ���������ʽ���ַ���
    public boolean isnum(String s) {
        for (int i = 0; i < s.length(); i++) {
            if (!Character.isDigit(s.charAt(i)))
                return false;
        }
        return true;
    }

}
