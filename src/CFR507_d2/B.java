package CFR507_d2;

import java.io.*;

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
        int n = nextInt();
        int k = nextInt();

        if (n <= 2 * k + 1) {
            pw.println(1);
            pw.print((n + 1) / 2);
            return;
        }

        int ans = n / (2 * k + 1);
        int r = n % (2 * k + 1);
        if (r == 0) {
            pw.println(ans);
            int cur = k + 1;
            pw.print(cur);
            cur += 2 * k + 1;
            for (int i = 2; i <= ans; i++) {
                pw.print(" " + cur);
                cur += 2 * k + 1;
            }
        } else if (r >= k + 1) {
            pw.println(ans + 1);
            pw.print(r - k);
            int cur = r + k + 1;
            for (int i = 1; i <= ans; i++) {
                pw.print(" " + cur);
                cur += 2 * k + 1;
            }
        } else {
            pw.println(ans + 1);
            int pre = (2 * k + 1 + r) / 2;
            pw.print(pre - k);
            int cur = pre + k + 1;
            for (int i = 1; i <= ans; i++) {
                pw.print(" " + cur);
                cur += 2 * k + 1;
            }
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
