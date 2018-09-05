package CRF500_d2;

import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * Copyright © 2018 Chris. All rights reserved.
 *
 * @author Chris
 * 2018/7/9 15:33
 * @see format
 */
public class C {

    private static BufferedReader br;
    private static StreamTokenizer st;
    private static PrintWriter pw;

    private static void solve() throws IOException {
        int n = nextInt();
        List<Integer> list = new ArrayList<>();

        int minSub = Integer.MAX_VALUE;
        for (int i = 0; i < (n << 1); i++) {
            list.add(nextInt());
        }
        list.sort(Comparator.naturalOrder());

        for (int i = 0; i < (n << 1); i++) {
            if (i >= n && i < ((n << 1) - 1)) {
                minSub = Math.min(minSub, list.get(i) - list.get(i - n + 1));
            }
        }

        long x = list.get(n - 1) - list.get(0);
        long y = list.get(2 * n - 1) - list.get(n);
        long min = x * y;

        if (n >= 2) {
            x = list.get(2 * n - 1) - list.get(0);
            y = minSub;
            min = Math.min(min, x * y);
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
