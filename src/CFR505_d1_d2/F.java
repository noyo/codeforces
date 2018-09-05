package CFR505_d1_d2;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * Copyright © 2018 Chris. All rights reserved.
 *
 * @author Chris
 * 2018/7/9 15:33
 * @see format
 */
public class F {

    private static BufferedReader br;
    private static StreamTokenizer st;
    private static PrintWriter pw;


    static List<Point> data = new ArrayList<>();
    static Point[] p;

    static class Point {
        int x;
        int y;
        double rad;

        Point() {
        }

        Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    private static int c(int n, int m) {
        int c[] = new int[n + 1];
        c[0] = 1;
        int min = Math.min(m, n - m);
        for (int i = 1; i <= min; i++) {
            c[i] = c[i - 1] * (n - i + 1) / i;
        }
        return c[m];
    }

    static boolean Left(Point A, Point B) {
        return A.x * B.y - A.y * B.x >= 0;
    }

    private static void solve() throws IOException {
        int n = nextInt();

        for (int i = 0; i < n; i++) {
            data.add(new Point(nextInt(), nextInt()));
        }

        long ans = 0;

        for (int i = 0; i < n; i++) {
            int k = 0;
            p = new Point[n - i - 1];
            for (int j = i + 1; j < n; j++) {
                if (null == p[k]) {
                    p[k] = new Point();
                }
                p[k].x = data.get(j).x - data.get(i).x;
                p[k].y = data.get(j).y - data.get(i).y;
                p[k].rad = Math.atan2(p[k].y, p[k].x);
                k++;
            }
            Arrays.sort(p, (o1, o2) -> {
                if (o1.rad > o2.rad) {
                    return 1;
                }
                if (o1.rad == o2.rad) {
                    return 0;
                } else {
                    return -1;
                }
            });

            int L = 0;
            int R = 0;
            int cnt = 0;
            while (L < k) {
                if (R == L) {
                    R = (R + 1) % k;
                    cnt++;
                }
                while (R != L && Left(p[L], p[R])) {
                    R = (R + 1) % k;
                    cnt++;
                }
                cnt--;
                L++;
                if (n - cnt - 2 >= 3) {
                    ans += cnt * c(n - cnt - 2, 3);
                }
            }
        }
        pw.print(ans);
    }

    public static void main(String args[]) throws IOException {
        boolean oj = System.getProperty("ONLINE_JUDGE") != null;
        if (!oj) {
            System.setIn(new FileInputStream("in.txt"));
//            System.setOut(new PrintStream("out.txt"));
        }
        br = new BufferedReader(new InputStreamReader(System.in));
        st = new StreamTokenizer(br);
        pw = new PrintWriter(new OutputStreamWriter(System.out));
        st.ordinaryChar('\''); //指定单引号、双引号和注释符号是普通字符
        st.ordinaryChar('\"');
        st.ordinaryChar('/');

        long t = System.currentTimeMillis();
        solve();
        if (!oj) {
            pw.println("[" + (System.currentTimeMillis() - t) + "ms]");
        }
        pw.flush();
    }

    private static int nextInt() throws IOException {
        st.nextToken();
        return (int) st.nval;
    }

    private static long nextLong() throws IOException {
        st.nextToken();
        return (long) st.nval;
    }

    private static double nextDouble() throws IOException {
        st.nextToken();
        return st.nval;
    }

    private static String[] nextSS(String reg) throws IOException {
        return br.readLine().split(reg);
    }

    private static String nextLine() throws IOException {
        return br.readLine();
    }
}
