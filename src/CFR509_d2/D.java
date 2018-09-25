package CFR509_d2;

import java.io.*;

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
        int h = nextInt();
        int l[] = new int[n];
        int r[] = new int[n];
        for (int i = 0; i < n; i++) {
            l[i] = nextInt();
            r[i] = nextInt();
        }

        int cur = 0;
        int curMax = r[0] - l[0];
        int max = curMax;
        int pre = 0;
        for (int i = 1; i < n; i++) {
            cur += l[i] - r[i - 1];
            curMax += r[i] - l[i];
            if (cur > h - 1) {
                while (cur > h - 1) {
                    if (i == pre + 1) {
                        cur = 0;
                        curMax = r[i] - l[i];
                        pre = i;
                        break;
                    }
                    cur -= l[pre + 1] - r[pre];
                    curMax -= r[pre] - l[pre];
                    pre++;
                }
            }
            max = Math.max(curMax, max);
        }
        pw.print(h + max);
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
