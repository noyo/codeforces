package CFR508_d2;

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


    private static int gcd(int a, int b) {
        while (b != 0) {
            int tmp = a % b;
            a = b;
            b = tmp;
        }
        return a;
    }

    private static int getSum(int n) {
        long p = ((long) n) * (n + 1);
        return (int) (p / 2);
    }

    private static void solve() throws IOException {
        int n = nextInt();
        if (n <= 2) {
            pw.print("No");
            return;
        }

        int sum = getSum(n);
        if (sum % 2 == 0) {
            pw.println("Yes");
            pw.println(1 + " " + 2);
            pw.print((n - 1) + " " + 1);
            for (int i = 3; i <= n; i++) {
                pw.print(" " + i);
            }
        } else {
            pw.println("Yes");
            pw.print((n / 2));
            for (int i = 2; i <= n; i += 2) {
                pw.print(" " + i);
            }
            pw.println();
            pw.print((n + 1) / 2);
            for (int i = 1; i <= n; i += 2) {
                pw.print(" " + i);
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
