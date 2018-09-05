package ECR49_d2;

import java.io.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Copyright © 2018 Chris. All rights reserved.
 *
 * @author Chris
 * 2018/7/9 15:33
 * @see format
 */
public class D {

    private static BufferedReader br;
    private static StreamTokenizer st;
    private static PrintWriter pw;

    private static void solve() throws IOException {
        int n = nextInt();
        int c[] = new int[n + 1];
        int a[] = new int[n + 1];

        int ans = 0;
        for (int i = 1; i <= n; i++) {
            c[i] = nextInt();
            ans += c[i];
        }
        for (int i = 1; i <= n; i++) {
            a[i] = nextInt();
        }

        Set<Integer> vst = new HashSet<>();
        long min = 0;
        for (int i = 1; i <= n; i++) {
            if (vst.contains(i)) {
                continue;
            }
            Set<Integer> circle = new HashSet<>();
            circle.add(i);
            int cur = i;
            boolean flag = false;
            while (!circle.contains(a[cur])) {
                if (vst.contains(a[cur])) {
                    flag = true;
                    break;
                }
                circle.add(a[cur]);
                cur = a[cur];
            }
            if (!flag && circle.size() == 1) {
                continue;
            }
            vst.addAll(circle);

            if (flag) {
                continue;
            }
            circle.clear();
            int cost = Integer.MAX_VALUE;
            while (!circle.contains(a[cur])) {
                if (c[cur] < cost) {
                    cost = c[cur];
                }
                circle.add(a[cur]);
                cur = a[cur];
            }
            min += cost;
        }
        for (int i = 1; i <= n; i++) {
            if (!vst.contains(i)) {
                min += c[i];
            }
        }
        pw.print(min);
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
