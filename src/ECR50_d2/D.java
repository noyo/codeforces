package ECR50_d2;

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
        long a[] = new long[n];
        long sumA = 0;

        for (int i = 0; i < n; i++) {
            a[i] = nextLong();
            sumA += a[i];
        }

        int m = nextInt();
        long b[] = new long[m];
        long sumB = 0;
        for (int i = 0; i < m; i++) {
            b[i] = nextLong();
            sumB += b[i];
        }

        if (sumA != sumB) {
            pw.print(-1);
        } else {
            int j = 0;
            int i = 0;
            int ans = 0;
            while (i < n && j < m) {
                if (a[i] == b[j]) {
                    i++;
                    j++;
                    ans++;
                } else if (a[i] < b[j]) {
                    if (i < n - 1) {
                        i++;
                        a[i] += a[i - 1];
                    } else {
                        pw.print(-1);
                        return;
                    }
                } else {
                    if (j < m - 1) {
                        j++;
                        b[j] += b[j - 1];
                    } else {
                        pw.print(-1);
                        return;
                    }
                }
            }
            if (i != n || j != m) {
                pw.print(-1);
            } else {
                pw.print(ans);
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
