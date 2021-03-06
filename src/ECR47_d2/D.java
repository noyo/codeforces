package ECR47_d2;

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


    private static int gcd(int a, int b) {
        while (b != 0) {
            int tmp = a % b;
            a = b;
            b = tmp;
        }
        return a;
    }

    private static void solve() throws IOException {
        long n = nextLong();
        long m = nextLong();

        if (m < n - 1 || m > n * (n - 1) / 2) {
            pw.print("Impossible");
            return;
        }

        int V[] = new int[(int) m];
        int v = 0;
        int U[] = new int[(int) m];
        int u = 0;
        for (int i = 1; i <= n; i++) {
            if (v >= m) {
                break;
            }
            for (int j = i + 1; j <= n; j++) {
                if (v >= m) {
                    break;
                }
                if (i == 1) {
                    V[v++] = i;
                    U[u++] = j;
                    continue;
                }
                if (i == 2) {
                    if (j % 2 == 1) {
                        V[v++] = i;
                        U[u++] = j;
                        j++;
                    }
                    continue;
                }
                if (gcd(i, j) == 1) {
                    V[v++] = i;
                    U[u++] = j;
                }
                if (i % 2 == 0) {
                    j++;
                }
            }
        }

        if (v < m) {
            pw.print("Impossible");
            return;
        }
        pw.println("Possible");
        for (int i = 0; i < m; i++) {
            pw.print(V[i] + " " + U[i]);
            if (i != m - 1) {
                pw.println();
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