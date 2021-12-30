import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;

// ��Ժ��������ѯ·��
public class QueryPath {
    // �������壬���ñ���
    JFrame jf = new JFrame("��Ժ����ϵͳ");
    final Solve solve = new Solve();

    public QueryPath() throws IOException {

        // �����Ȼ���У��
        Image icon = Toolkit. getDefaultToolkit().getImage("src/image/logo.jpg");
        jf.setIconImage(icon);

        // ���ر���ͼƬ
        ImageIcon background = new ImageIcon("src/image/��ͼ3.0.jpg" );

        JLabel label = new JLabel(background);

        // ����ͼƬ��ǩ��ʾλ�úʹ�С
        label.setBounds(0, 0, background.getIconWidth(), background.getIconHeight());

        //ʹ�ô���ķֲ����,������������JPanel��������ʾ����ͼ�ı�ǩ��LayeredPane����ǰ�ߡ�
        jf.getLayeredPane().add(label, new Integer(Integer.MIN_VALUE));

        // ��ȡContentPane������,ǿ��ת����JPanel
        JPanel jp = (JPanel) jf.getContentPane();

        // JPanel����ſ��Ե���setOpaque(false);�����Ƿ�͸��
        jp.setOpaque(false);


        // ��ȡContentPane������,ǿ��ת����JPanel
        JPanel jp2 = (JPanel) jf.getContentPane();

        // JPanel����ſ��Ե���setOpaque(false);�����Ƿ�͸��
        jp2.setOpaque(false);


        JPanel jpanel = new JPanel();
        jpanel.setOpaque(false);

        // ȥ��JPanelĬ�ϲ��ַ�ʽ,��ʵ�ָ����ؼ��Լ��Ķ�λ
        jpanel.setLayout(null);

        JLabel a = new JLabel("���:", JLabel.CENTER);
        a.setFont(new Font("����", Font.PLAIN, 25));
        a.setBounds(1118, 20, 100, 20);
        JLabel b = new JLabel("�յ�:", JLabel.CENTER);
        b.setFont(new Font("����", Font.PLAIN, 25));
        b.setBounds(1118, 50, 100, 20);
        final JTextField c = new JTextField();
        final JTextField d = new JTextField();
        c.setBounds(1218, 23, 170, 20);
        d.setBounds(1218, 53, 170, 20);
        JButton timeshao = new JButton("���ʱ��");
        timeshao.setFont(new Font("����", Font.PLAIN, 15));
        timeshao.setForeground(Color.BLACK);
        timeshao.setBackground(Color.GREEN);
        timeshao.setBorderPainted(false);
        final JTextArea text = new JTextArea();
        text.setBounds(1118, 195, 300, 300);
        text.setOpaque(false);
        jpanel.add(text);


        // �����ʱ����¼�
        timeshao.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                int num1 = solve.checkname(c.getText());
                int num2 = solve.checkname(d.getText());
                if (num1 == num2 && num1 != 0) {
                    JOptionPane.showMessageDialog(null, "�Ѿ���Ŀ�ĵ�!", "��ʾ", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    if (!solve.vis[num1] || !solve.vis[num2] || num1 == 0 || num2 == 0 || num1 > solve.cnt || num2 > solve.cnt)
                        show_error();
                    else {
                        solve.dijkstra(num1, num2);
                        Graphics graphics = jf.getGraphics();
                        newpaint(graphics);
                        String ans = "";
                        for (int i = solve.plan_cnt; i >= 0; i--) {
                            // ���һ���㲻�Ӽ�ͷ
                            if (i != 0) {
                                ans += solve.name[solve.ans[i]] + "--->";
                            } else
                                ans += solve.name[solve.ans[i]];
                            // ���һ����ֱ������������ӡ���ж���
                            if (i == 0)
                                continue;
                            ans += "����\n";

                        }
                        ans += "\nԤ��ʱ��:" + String.format("%.2f", solve.dis[num2] * 6 / 10000) + "h";
                        text.setFont(new Font("����", Font.PLAIN, 19));
                        text.setText(ans);
                        text.setForeground(Color.BLACK);
                    }
                }
            }
        });

        JLabel tuijian;    //��ǩ
        tuijian = new JLabel("�Ƽ�·��", JLabel.CENTER);
        tuijian.setFont(new Font("����", Font.PLAIN, 20));
        tuijian.setBounds(1118, 140, 100, 50);
        jpanel.add(tuijian);
        JButton lujingduan = new JButton("���·��");
        lujingduan.setBorderPainted(false);
        lujingduan.setFont(new Font("����", Font.PLAIN, 15));
        lujingduan.setForeground(Color.WHITE);
        lujingduan.setBackground(Color.RED);

        // �����·������¼�����
        lujingduan.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                int num1 = solve.checkname(c.getText());
                int num2 = solve.checkname(d.getText());
                if (num1 == num2 && num1 != 0) {
                    JOptionPane.showMessageDialog(null, "�Ѿ���Ŀ�ĵ�!", "��ʾ", JOptionPane.INFORMATION_MESSAGE);
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

                            //���������׼ȷ��
                            System.out.println(solve.dian[solve.ans[i]].x + "  "+solve.dian[solve.ans[i]].y);

                            if (i != 0) {
                                ans += solve.name[solve.ans[i]] + "--->";
                            } else
                                ans += solve.name[solve.ans[i]];
                            if (i == 0)
                                continue;

                            ans += "����\n";

                        }
                        //Format(String, Object)  ��ָ���� String �еĸ�ʽ���滻Ϊָ���� Object ʵ����ֵ���ı���Ч��

                        ans += "\nԤ�ƾ���:" + String.format("%.2f", solve.dis[num2]*2) + "m";
                        text.setFont(new Font("����", Font.PLAIN, 19));
                        text.setText(ans);
                        text.setForeground(Color.BLACK);
                    }
                }
            }
        });

        //���ʵʱ��ȡ����


        JButton exit = new JButton("�˳�");
        exit.setBorderPainted(false);
        exit.setFont(new Font("����", Font.PLAIN, 20));
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

        jf.setLocationRelativeTo(null); //����Ļ�м���ʾ(������ʾ)
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

    }

    public void show_error() {
        JOptionPane.showMessageDialog(null, "����ֵ�Ƿ�!", "����!", JOptionPane.ERROR_MESSAGE);
    }

    // ��ͼ�Ͻ��б�㣬����·
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
                //<�����34��������Ϊ���߿�ҲҪ�����ȥ>
                graphics.drawLine((solve.dian[solve.ans[i]].x),(solve.dian[solve.ans[i]].y+34),(solve.dian[solve.ans[i + 1]].x), (solve.dian[solve.ans[i + 1]].y+34));
            }
        }
    }
}
