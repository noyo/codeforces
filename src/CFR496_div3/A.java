package CFR496_div3;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Copyright © 2018 Chris. All rights reserved.
 *
 * @author Chris
 * 2018/7/9 15:49
 * @see CFR496_div3
 */
public class A {

    private static BufferedReader br;
    private static StreamTokenizer st;
    private static PrintWriter pw;

    private static void solve() throws IOException {

        int n = nextInt();
        int b[] = new int[n];
        int ar[] = new int[n];
        int u = 0;
        for (int i = 0; i < n; i++) {
            b[i] = nextInt();
            if (b[i] == 1 && i > 0) {
                ar[u++] = b[i - 1];
            }
        }
        ar[u++] = b[n - 1];
        pw.println(u);
        for (int i : ar) {
            pw.print(i + " ");
        }
//        int n = nextInt();
//        int a[] = new int[n + 1];
//        a[0] = -1;
//        int cnt = 0;
//        List<Integer> layer = new ArrayList<>();
//        int floor = 0;
//        for (int i = 1; i <= n; i++) {
//            a[i] = nextInt();
//        }
//        for (int i = 1; i <= n; i++) {
//            if (a[i] > a[i - 1]) {
//                floor++;
//            } else {
//                cnt++;
//                layer.add(floor);
//                floor = 1;
//            }
//        }
//        cnt++;
//        layer.add(floor);
//        pw.println(cnt);
//        for (int i = 0; i < layer.size(); i++) {
//            pw.print(layer.get(i));
//            if (i < layer.size() - 1) {
//                pw.print(" ");
//            }
//        }
    }

    public static void main(String args[]) throws IOException {
        boolean oj = System.getProperty("ONLINE_JUDGE") != null;
        if (!oj) {
            System.setIn(new FileInputStream("in.txt"));
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

    private static String next() throws IOException {
//        br.readLine();
        return br.readLine();
    }
}
