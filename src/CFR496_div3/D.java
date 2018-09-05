package CFR496_div3;

import java.io.*;

/**
 * Copyright © 2018 Chris. All rights reserved.
 *
 * @author Chris
 * 2018/7/9 15:49
 * @see CFR496_div3
 */
public class D {

    private static BufferedReader br;
    private static StreamTokenizer st;
    private static PrintWriter pw;

    private static void solve() throws IOException {
        char ch[] = next().toCharArray();
        int n = ch.length;
        int a[] = new int[n + 1];
        int sum[] = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            a[i] = ch[i - 1] - '0';
            sum[i] = sum[i - 1] + a[i];
        }

        int dp[] = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            if (a[i] % 3 == 0) {
                dp[i] = dp[i - 1] + 1;
                continue;
            }
            dp[i] = dp[i - 1];
            if (sum[i] < 3) {
                continue;
            }
            for (int j = i - 1; j > 0; j--) {
                if ((sum[i] - sum[j - 1]) % 3 == 0) {
                    dp[i] = Math.max(dp[i], dp[j - 1] + 1);
                    break;
                }
            }
        }

        pw.print(dp[n]);
    }

    public static void main(String args[]) throws IOException {
        boolean oj = System.getProperty("ONLINE_JUDGE") != null;
        if (!oj) {
            System.setIn(new FileInputStream("in.txt"));
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

    private static String next() throws IOException {
//        br.readLine();
        return br.readLine();
    }
}
