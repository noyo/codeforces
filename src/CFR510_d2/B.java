package CFR510_d2;

import java.io.*;
import java.util.*;

/**
 * Copyright © 2018 Chris. All rights reserved.
 *
 * @author Chris
 * 2018/7/9 15:33
 * @see format
 */
public class B {

    private static BufferedReader br;
    private static StreamTokenizer st;
    private static PrintWriter pw;

    private static void solve() throws IOException {
        int n = Integer.parseInt(nextLine());
        int cost[] = new int[n];
        int minCost[] = new int[6];
        Arrays.fill(minCost, Integer.MAX_VALUE);
        int min = Integer.MAX_VALUE;

        for (int i = 0; i < n; i++) {
            String ss[] = nextSS(" ");
            cost[i] = Integer.parseInt(ss[0]);
            char ch[] = ss[1].toCharArray();
            Arrays.sort(ch);
            if (ch.length == 1) {
                minCost[ch[0] - 'A'] = Math.min(minCost[ch[0] - 'A'], cost[i]);
            } else if (ch.length == 2) {
                if (ch[0] == 'A') {
                    minCost[2 + ch[1] - 'A'] = Math.min(minCost[2 + ch[1] - 'A'], cost[i]);
                } else {
                    minCost[5] = Math.min(minCost[5], cost[i]);
                }
            } else {
                min = Math.min(min, cost[i]);
            }
        }

        if (minCost[0] < Integer.MAX_VALUE && minCost[1] < Integer.MAX_VALUE && minCost[2] < Integer.MAX_VALUE) {
            min = Math.min(minCost[0] + minCost[1] + minCost[2], min);
        }
        if (minCost[0] < Integer.MAX_VALUE && minCost[5] < Integer.MAX_VALUE) {
            min = Math.min(minCost[0] + minCost[5], min);
        }
        if (minCost[1] < Integer.MAX_VALUE && minCost[4] < Integer.MAX_VALUE) {
            min = Math.min(minCost[1] + minCost[4], min);
        }
        if (minCost[2] < Integer.MAX_VALUE && minCost[3] < Integer.MAX_VALUE) {
            min = Math.min(minCost[2] + minCost[3], min);
        }
        if (minCost[3] < Integer.MAX_VALUE && minCost[4] < Integer.MAX_VALUE) {
            min = Math.min(minCost[3] + minCost[4], min);
        }
        if (minCost[3] < Integer.MAX_VALUE && minCost[5] < Integer.MAX_VALUE) {
            min = Math.min(minCost[3] + minCost[5], min);
        }
        if (minCost[4] < Integer.MAX_VALUE && minCost[5] < Integer.MAX_VALUE) {
            min = Math.min(minCost[4] + minCost[5], min);
        }
        if (min == Integer.MAX_VALUE) {
            pw.print(-1);
        } else {
            pw.print(min);
        }
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
