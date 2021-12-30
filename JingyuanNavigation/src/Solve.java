import java.io.*;
import java.nio.charset.StandardCharsets;

// 地图点坐标类
class node {
    int  x, y;
}

// 核心算法类
/**
 * 备注：
 *
 * name 地点名称
 * dian 记录每个地点的坐标
 * cnt  所有地点坐标的数字标记
 * introduction 地点的介绍
 * can 二维数组 记录两点之间的连接关系 即邻接矩阵  举例：can[1][2] = 1 ==> 即 标记为1的地方和标记为2的地方 有路
 * map 二维数组 记录两点之间的距离
 * vis  表示对应标号的地点是否可以带入算法...换言之就是该点是否存在，因为这次实训还有删除地点要求。
 *
 * ans 存储 最短路径结点
 * pre 存放各点上的<最短路径>前驱
 * dis dijkstra算法所用起点到其他点的距离
 * vis1   dijkstra算法所用flag
 * plan_cnt  最短路径的经过点数
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

    // 构造函数
    public Solve() throws IOException {
        for (int i = 0; i <= 1000; i++)
            dian[i] = new node();
        read();
        update_map();
    }

    // 写入
    public void write1() throws IOException {
        File file = new File("src/file/site1.txt");
        String s = "";
        if (file.exists()) {
            for (int i = 1; i <= cnt; i++) {
                // 判断该坐标点是否删除，即是否可以访问
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
        out.append(s);  // 将多行字符串重写入文件中
        out.flush();
    }

    // 能到达的地点的写入，图之间的结点 连通性的写入
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
     * @param s 接收到的地点名称
     * @return  核对名字，判断是否有该地点，返回对应位置的编号
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

    // 读取
    // 读取地点信息，在txt中
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
                vis[number] = true;  // 设置可以访问
                dian[number].x = Integer.parseInt(x[2]);  // 坐标x
                dian[number].y = Integer.parseInt(x[3]);  // 坐标y
                introduction[number] = x[4];  // 地点 介绍
                cnt++;
            }
        } else {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        // 获取二维数组的，使用邻接矩阵存储图的
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

    //更新地图点之间距离,构造map邻接矩阵
    public void update_map() {
        for (int i = 1; i <= cnt; i++) {
            for (int j = 1 + 1; j <= cnt; j++) {
                // 如果地点存在
                if (vis[i] && vis[j]) {
                    // 并且两个点可以到达,就计算两点之间距离
                    if (can[i][j] != 0)
                        map[i][j] = map[j][i] = Math.sqrt((dian[i].x - dian[j].x) * (dian[i].x - dian[j].x) + (dian[i].y - dian[j].y) * (dian[i].y - dian[j].y));
                    else
                        map[i][j] = map[j][i] = 0x3f3f3f3f;
                }
            }
        }
    }

    //迪杰斯特拉
    //迪杰斯特拉算法，求最短路径
    /**
     * @param
     * @param e
     */
    public void dijkstra(int s, int e) {
        // 初始化dis[].ans[],vis1[]
        plan_cnt = 0;
        for (int i = 1; i <= cnt; i++) {
            ans[i] = 0;
            vis1[i] = false;
            dis[i] = 0x3f3f3f3f;  // 起点到i之间最短的距离，默认最大值。
        }

        for (int i = 1; i <= cnt; i++) {
            dis[i] = map[s][i];  // 初始化起点s到其余点的距离
            pre[i] = s;  // 设置每个地点的前驱结点为起点s
        }

        vis1[s] = true;  // 将s点设置已访问

        // 循环(地点数目-1)次
        for (int i = 1; i < cnt; i++) {
            if (!vis[i])
                continue;
            double MIN = 0x3f3f3f3f;  // 当前距离最小值
            int k = -1;  // 记录下标
            // 循环遍历所有地点，获取当前最短路径，记录下标
            for (int j = 1; j <= cnt; j++) {
                /**
                 * vis 存在与否
                 * visl 是否已访问
                 */
                if (vis[j] && !vis1[j] && dis[j] < MIN) {
                    MIN = dis[j];
                    k = j;
                }
            }
            // 判断如果k==e就break；
            if (k == e)
                break;

            vis1[k] = true;
            for (int j = 1; j <= cnt; j++) {
                // 如果j顶点存在，且目前未访问就next
                if (vis[j] && !vis1[j]) {
                    // 如果当前最短下标k到j有关系
                    if (can[k][j] != 0) {
                        // 计算k到j的距离加上s到k的最短距离，如果到j的距离小于dis[j]则更改值
                        double u = MIN + 1.0 * map[k][j];
                        if (dis[j] > u) {
                            dis[j] = u;  // 更新值
                            pre[j] = k;  // 更新j的前驱为当前最小下标
                        }
                    }
                }
            }
        }
        // 设置起点的前驱为0，即无
        pre[s] = 0;
        int k = e; // flag等于终点
        // 倒着遍历前驱结点的数组，一直到0
        while (pre[k] != 0) {
            ans[++plan_cnt] = pre[k];
            k = pre[k];
        }
        // 设置最短路径数组起点是e
        ans[0] = e;
    }
}