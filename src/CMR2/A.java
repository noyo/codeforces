package CMR2;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

/**
 * Copyright © 2018 Chris. All rights reserved.
 *
 * @author Chris
 * 2018/7/25 12:20
 * @see CMR2
 */
public class A {
    private static BufferedReader br;
    private static StreamTokenizer st;
    private static PrintWriter pw;

    private static void solve() throws IOException {
        int N = nextInt();
        int S = nextInt();
        int C = nextInt();
        int H = nextInt();
        int U = nextInt();
        
        nextLine();
        char[] stripe = nextLine().toCharArray();
        char[] hands = nextLine().toCharArray();
        char[] bag = nextLine().toCharArray();
        int useI = 0;

        Map<Character, Integer> pre = new HashMap<>();
        Map<Character, int[]> next = new HashMap<>();
        for (int i = 0; i < N; i++) {
            char ch = stripe[i];
            if (pre.containsKey(ch)) {
                if (!next.containsKey(ch)) {
                    next.put(ch, new int[N]);
                }
                next.get(ch)[pre.get(ch)] = i;
            }
            pre.put(ch, i);
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
