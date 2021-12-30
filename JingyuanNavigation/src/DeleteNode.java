import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;

//// ����������ɾ���ص�
public class DeleteNode {

    // �������壬���ñ���
    JFrame jf = new JFrame("��Ժ����ϵͳ");
    final Solve solve = new Solve();

    public DeleteNode() throws IOException {


        // ���ñ����ǩ
        JLabel lb0 = new JLabel("��Ժ����ϵͳ");

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

        JLabel a = new JLabel("������Ҫɾ���ص�����:", JLabel.CENTER);
        a.setFont(new Font("����", Font.PLAIN, 26));
        a.setBounds(1115, 160, 300, 100);
        final JButton b = new JButton("ѡ��");
        b.setBorderPainted(false);
        b.setFont(new Font("����", Font.PLAIN, 15));
        b.setForeground(Color.BLACK);
        b.setBackground(Color.GREEN);
        JButton exit = new JButton("�˳�");
        exit.setFont(new Font("����", Font.PLAIN, 20));
        exit.setForeground(Color.BLACK);
        exit.setBackground(Color.YELLOW);
        exit.setBorderPainted(false);
        JButton sure = new JButton("ȷ��");
        sure.setFont(new Font("����", Font.PLAIN, 20));
        sure.setForeground(Color.WHITE);
        sure.setBackground(Color.RED);
        sure.setBorderPainted(false);
        final JTextField c = new JTextField();
        final JTextArea text = new JTextArea();
        text.setOpaque(false);
        text.setFont(new Font("����", Font.PLAIN, 20));

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

        // �����»س���ʱ��������ѡ��ť������ѯ����
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

        // ���˳��󶨵����¼�
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
                    String x = JOptionPane.showInputDialog("���������룡");
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
                        JOptionPane.showMessageDialog(null, "�������", "����!", JOptionPane.ERROR_MESSAGE);
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
        jf.setLocationRelativeTo(null); //����Ļ�м���ʾ(������ʾ)

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

    public boolean isnum(String s) {
        for (int i = 0; i < s.length(); i++) {
            if (!Character.isDigit(s.charAt(i)))
                return false;
        }
        return true;
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
