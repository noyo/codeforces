package CRF500_d2;

import java.io.*;
import java.util.*;

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

    static class Point {
        int r;
        int c;

        Point(int r, int c) {
            this.r = r;
            this.c = c;
        }

        @Override
        public String toString() {
            return "Point{" +
                    "r=" + r +
                    ", c=" + c +
                    '}';
        }
    }

    static int n = 0;
    static int m = 0;
    static int maxR = 1;
    static int maxC = 1;

    private static void solve() throws IOException {
        n = nextInt();
        m = nextInt();
        int q = nextInt();
        Map<Integer, Set<Integer>> vstR = new HashMap<>();
        Map<Integer, Set<Integer>> vstC = new HashMap<>();

        for (int i = 0; i < q; i++) {
            int r = nextInt();
            int c = nextInt();
            if (!vstR.containsKey(r)) {
                vstR.put(r, new HashSet<>());
            }
            vstR.get(r).add(c);

            if (!vstC.containsKey(c)) {
                vstC.put(c, new HashSet<>());
            }
            vstC.get(c).add(r);
        }
        if (n == 1) {
            if (null == vstR.get(1)) {
                pw.print(m);
            } else {
                pw.print(m - vstR.get(1).size());
            }
            return;
        }
        if (m == 1) {
            if (null == vstC.get(1)) {
                pw.print(n);
            } else {
                pw.print(n - vstC.get(1).size());
            }
            return;
        }

        int ans = 0;
        for (int r = 1; r <= n; r++) {
            if (null != vstC.get(1) && vstC.get(1).contains(r)) {
                continue;
            }
            if (null != vstC.get(1) && null != vstR.get(r)) {
                Iterator<Integer> itc = vstC.get(1).iterator();
                while (itc.hasNext()) {
                    int tmp = itc.next();

                }
                continue;
            }
            if (null == vstC.get(1)) {
//                vstC.put(1, )
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
