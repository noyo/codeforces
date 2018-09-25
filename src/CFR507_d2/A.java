package CFR507_d2;

import java.io.*;

/**
 * Copyright © 2018 Chris. All rights reserved.
 *
 * @author Chris
 * 2018/7/9 15:33
 * @see format
 */
public class A {

    private static BufferedReader br;
    private static StreamTokenizer st;
    private static PrintWriter pw;

    private static void solve() throws IOException {
        int n = nextInt();
        int a = nextInt();
        int b = nextInt();
        int c[] = new int[n];

        for (int i = 0; i < n; i++) {
            c[i] = nextInt();
        }

        int ans = 0;
        for (int i = 0, j = n - 1; i <= j; i++, j--) {
            if (i == j) {
                if (c[i] == 2) {
                    ans += Math.min(a, b);
                }
            } else {
                if (c[i] == 2 || c[j] == 2) {
                    if (c[i] == 2 && c[j] == 2) {
                        ans += Math.min(a, b) * 2;
                    } else {
                        if (c[i] == 0 || c[j] == 0) {
                            ans += a;
                        } else {
                            ans += b;
                        }
                    }
                } else if (c[i] != c[j]){
                    pw.print(-1);
                    return;
                }
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
