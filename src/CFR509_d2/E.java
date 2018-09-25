package CFR509_d2;

import java.io.*;
import java.util.*;

/**
 * Copyright © 2018 Chris. All rights reserved.
 *
 * @author Chris
 * 2018/7/9 15:33
 * @see format
 */
public class E {

    private static BufferedReader br;
    private static StreamTokenizer st;
    private static PrintWriter pw;

    private static void solve() throws IOException {
        int n = nextInt();

        int cnt[] = new int[1001];
        for (int i = 1; i < n; i++) {
            int a = nextInt();
            int b = nextInt();
            if (a < b) {
                a ^= b;
                b ^= a;
                a ^= b;
            }
            if (a != n || a == b) {
                pw.print("NO");
                return;
            }
            cnt[b]++;
        }

        Set<Integer> set = new HashSet<>();
        Set<int[]> pair = new HashSet<>();
        for (int i = 1; i < n; i++) {
            if (cnt[i] == 0) {
                set.add(i);
                continue;
            }
            if (cnt[i] - 1 > set.size()) {
                pw.print("NO");
                return;
            }
            int pre = i;
            Iterator<Integer> it = null;
            for (int j = 1; j < cnt[i]; j++) {
                if (null == it) {
                    it = set.iterator();
                }
                int next = it.next();
                it.remove();
                pair.add(new int[]{pre, next});
                pre = next;
            }
            pair.add(new int[]{pre, n});
        }
        if (set.size() > 0) {
            pw.print("NO");
            return;
        }
        pw.println("YES");
        Iterator<int[]> it = pair.iterator();
        while (it.hasNext()) {
            int p[] = it.next();
            pw.println(p[0] + " " + p[1]);
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
