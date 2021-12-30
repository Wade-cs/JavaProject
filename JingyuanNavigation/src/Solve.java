import java.io.*;
import java.nio.charset.StandardCharsets;

// ��ͼ��������
class node {
    int  x, y;
}

// �����㷨��
/**
 * ��ע��
 *
 * name �ص�����
 * dian ��¼ÿ���ص������
 * cnt  ���еص���������ֱ��
 * introduction �ص�Ľ���
 * can ��ά���� ��¼����֮������ӹ�ϵ ���ڽӾ���  ������can[1][2] = 1 ==> �� ���Ϊ1�ĵط��ͱ��Ϊ2�ĵط� ��·
 * map ��ά���� ��¼����֮��ľ���
 * vis  ��ʾ��Ӧ��ŵĵص��Ƿ���Դ����㷨...����֮���Ǹõ��Ƿ���ڣ���Ϊ���ʵѵ����ɾ���ص�Ҫ��
 *
 * ans �洢 ���·�����
 * pre ��Ÿ����ϵ�<���·��>ǰ��
 * dis dijkstra�㷨������㵽������ľ���
 * vis1   dijkstra�㷨����flag
 * plan_cnt  ���·���ľ�������
 */
public class Solve {

    public String[] name = new String[1010];
    public node[] dian = new node[1010];
    public int cnt;
    public String[] introduction = new String[1010];
    int[][] can = new int[1010][1010];
    double[][] map = new double[1010][1010];
    boolean[] vis = new boolean[1010];

    int[] ans = new int[1010];
    int[] pre = new int[1010];
    double[] dis = new double[1010];
    boolean[] vis1 = new boolean[1010];
    int plan_cnt;

    // ���캯��
    public Solve() throws IOException {
        for (int i = 0; i <= 1000; i++)
            dian[i] = new node();
        read();
        update_map();
    }

    // д��
    public void write1() throws IOException {
        File file = new File("src/file/site1.txt");
        String s = "";
        if (file.exists()) {
            for (int i = 1; i <= cnt; i++) {
                // �жϸ�������Ƿ�ɾ�������Ƿ���Է���
                if (vis[i])
                    s += i + " " + name[i] + " " + dian[i].x + " " + dian[i].y + " " + introduction[i] + "\r\n";
            }
        } else {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        FileOutputStream r = new FileOutputStream(file);
        BufferedWriter out = new BufferedWriter(new OutputStreamWriter(r, StandardCharsets.UTF_8));
        out.append(s);  // �������ַ�����д���ļ���
        out.flush();
    }

    // �ܵ���ĵص��д�룬ͼ֮��Ľ�� ��ͨ�Ե�д��
    public void write2() throws IOException {
        File file = new File("src/file/map1.txt");
        String s = "";
        if (file.exists()) {
            for (int i = 1; i <= cnt; i++) {
                for (int j = i + 1; j <= cnt; j++) {
                    if (can[i][j] == 1 && vis[i] && vis[j])
                        s += i + " " + j + "\r\n";
                }
            }
        } else {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        BufferedWriter out = new BufferedWriter(new PrintWriter(file));
        out.append(s);
        out.flush();
    }

    /**
     * @param s ���յ��ĵص�����
     * @return  �˶����֣��ж��Ƿ��иõص㣬���ض�Ӧλ�õı��
     */
    public int checkname(String s) {
        int id = 0;
        for (int i = 1; i <= cnt; i++) {
            if (vis[i] && s.equals(name[i])) {
                id = i;
                break;
            }
        }
        return id;
    }

    // ��ȡ
    // ��ȡ�ص���Ϣ����txt��
    public void read() throws IOException {
        String filename = "src/file/site1.txt";
        File file = new File(filename);
        if (file.exists()) {
            FileInputStream r = new FileInputStream(file);
            BufferedReader in = new BufferedReader(new InputStreamReader(r, StandardCharsets.UTF_8));
            String output;
            while ((output = in.readLine()) != null) {
                String[] x = output.split("\\s+|\\s");
                int number = Integer.parseInt(x[0]);
                name[number] = x[1];
                System.out.println(name[number]);
                vis[number] = true;  // ���ÿ��Է���
                dian[number].x = Integer.parseInt(x[2]);  // ����x
                dian[number].y = Integer.parseInt(x[3]);  // ����y
                introduction[number] = x[4];  // �ص� ����
                cnt++;
            }
        } else {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        // ��ȡ��ά����ģ�ʹ���ڽӾ���洢ͼ��
        String filename1 = "src/file/map1.txt";
        File file1 = new File(filename1);
        if (file1.exists()) {
            BufferedReader in = new BufferedReader(new FileReader(filename1));
            String output;
            while ((output = in.readLine()) != null) {
                String[] x = output.split("\\s+|\\s");
                can[Integer.parseInt(x[0])][Integer.parseInt(x[1])] = can[Integer.parseInt(x[1])][Integer.parseInt(x[0])] = 1;
            }
        } else {
            try {
                file1.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    //���µ�ͼ��֮�����,����map�ڽӾ���
    public void update_map() {
        for (int i = 1; i <= cnt; i++) {
            for (int j = 1 + 1; j <= cnt; j++) {
                // ����ص����
                if (vis[i] && vis[j]) {
                    // ������������Ե���,�ͼ�������֮�����
                    if (can[i][j] != 0)
                        map[i][j] = map[j][i] = Math.sqrt((dian[i].x - dian[j].x) * (dian[i].x - dian[j].x) + (dian[i].y - dian[j].y) * (dian[i].y - dian[j].y));
                    else
                        map[i][j] = map[j][i] = 0x3f3f3f3f;
                }
            }
        }
    }

    //�Ͻ�˹����
    //�Ͻ�˹�����㷨�������·��
    /**
     * @param
     * @param e
     */
    public void dijkstra(int s, int e) {
        // ��ʼ��dis[].ans[],vis1[]
        plan_cnt = 0;
        for (int i = 1; i <= cnt; i++) {
            ans[i] = 0;
            vis1[i] = false;
            dis[i] = 0x3f3f3f3f;  // ��㵽i֮����̵ľ��룬Ĭ�����ֵ��
        }

        for (int i = 1; i <= cnt; i++) {
            dis[i] = map[s][i];  // ��ʼ�����s�������ľ���
            pre[i] = s;  // ����ÿ���ص��ǰ�����Ϊ���s
        }

        vis1[s] = true;  // ��s�������ѷ���

        // ѭ��(�ص���Ŀ-1)��
        for (int i = 1; i < cnt; i++) {
            if (!vis[i])
                continue;
            double MIN = 0x3f3f3f3f;  // ��ǰ������Сֵ
            int k = -1;  // ��¼�±�
            // ѭ���������еص㣬��ȡ��ǰ���·������¼�±�
            for (int j = 1; j <= cnt; j++) {
                /**
                 * vis �������
                 * visl �Ƿ��ѷ���
                 */
                if (vis[j] && !vis1[j] && dis[j] < MIN) {
                    MIN = dis[j];
                    k = j;
                }
            }
            // �ж����k==e��break��
            if (k == e)
                break;

            vis1[k] = true;
            for (int j = 1; j <= cnt; j++) {
                // ���j������ڣ���Ŀǰδ���ʾ�next
                if (vis[j] && !vis1[j]) {
                    // �����ǰ����±�k��j�й�ϵ
                    if (can[k][j] != 0) {
                        // ����k��j�ľ������s��k����̾��룬�����j�ľ���С��dis[j]�����ֵ
                        double u = MIN + 1.0 * map[k][j];
                        if (dis[j] > u) {
                            dis[j] = u;  // ����ֵ
                            pre[j] = k;  // ����j��ǰ��Ϊ��ǰ��С�±�
                        }
                    }
                }
            }
        }
        // ��������ǰ��Ϊ0������
        pre[s] = 0;
        int k = e; // flag�����յ�
        // ���ű���ǰ���������飬һֱ��0
        while (pre[k] != 0) {
            ans[++plan_cnt] = pre[k];
            k = pre[k];
        }
        // �������·�����������e
        ans[0] = e;
    }
}