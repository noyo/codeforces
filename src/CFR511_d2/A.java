package CFR511_d2;

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

    /**
     * ps: n >= m, choose m from n;
     */
    private static long[][] initC(int n) {
        long c[][] = new long[n][n];

        for (int i = 0; i < n; i++) {
            c[i][0] = 1;
        }

        for (int i = 1; i < n; i++) {
            for (int j = 1; j <= i; j++) {
                c[i][j] = c[i - 1][j - 1] + c[i - 1][j];
            }
        }
        return c;
    }

    /**
     * ps: n >= m, choose m from n;
     */
    private static int c(int n, int m) {
        int c[] = new int[n];
        c[0] = 1;
        int min = Math.min(m, n - m);
        for (int i = 1; i <= min; i++) {
            c[i] = c[i - 1] * (n - i + 1) / i;
        }
        return c[m];
    }

    static int[] getBit(int num) {
        int bit[] = new int[32];
        int cur = 0;
        while (num > 0) {
            bit[cur++] = num % 2;
            num >>= 1;
        }
        return bit;
    }

    static int getBitLen(int num) {
        int cur = 0;
        while (num > 0) {
            cur++;
            num >>= 1;
        }
        return cur;
    }

    private static void solve() throws IOException {
        int n = nextInt();

        int a, b, c;
        if (n % 3 <= 1) {
            a = 1;
            b = 1;
            c = n - a - b;
        } else {
            a = 2;
            b = 2;
            c = n - a - b;
        }
        pw.print(a + " " + b + " " + c);
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
