package ECR50_d2;

import java.io.*;
import java.math.BigInteger;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Copyright © 2018 Chris. All rights reserved.
 *
 * @author Chris
 * 2018/7/9 15:33
 * @see format
 */
public class C {

    private static BufferedReader br;
    private static StreamTokenizer st;
    private static PrintWriter pw;

    static int count(double n) {
        int cnt = 0;
        while (n >= 1) {
            int r = (int) (n % 10);
            if (r != 0) {
                cnt++;
            }
            n /= 10;
        }
        return cnt;
    }

    static double countS(double n, int cur, boolean flag) {
        if (cur == 0 && n < 1) {
            return 1;
        }

        List<Integer> cnt = new ArrayList<>();
        int r = 9;
        double tmp = n;
        double next = 0;
        double mul = 1;
        while (tmp >= 1) {
            r = (int) (tmp % 10);
            if (tmp >= 10) {
                next = next + r * mul;
                mul = mul * 10;
            }
            tmp /= 10;
        }
        if (flag) {
            r = 9;
        }
        if (r == 1) {
            return countS(next, cur, true) + countS(next, cur - 1, false);
        } else {
            return (r - 1) * countS(next, cur - 1, true)
                    + countS(next, cur, true) + countS(next, cur - 1, false);
        }
    }

    private static void solve() throws IOException {
        int q = nextInt();
        DecimalFormat df = new DecimalFormat("#");
        while (q-- > 0) {

            double L = nextDouble();
            double R = nextDouble();
            double ans = 0;

            if (Math.abs(L - R) < 1) {
                if (count(L) <= 3) {
                    ans = ans + 1;
                }
            } else {
                ans = countS(R, 3, false) - countS(L, 3, false) + (count(L) <= 3 ? 1 : 0);
            }

            pw.print(df.format(ans));
            if (q > 0) {
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
