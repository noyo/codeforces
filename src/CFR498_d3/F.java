package CFR498_d3;

import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * Copyright © 2018 Chris. All rights reserved.
 *
 * @author Chris
 * 2018/7/9 15:33
 * @see format
 */
public class F {

    private static BufferedReader br;
    private static StreamTokenizer st;
    private static PrintWriter pw;
    static int n;
    static int m;
    static long k;
    //    static long a[][];
    static int sum = 0;
    static List<Long>[] dp;

    private static boolean isLegal(int r, int c) {
        return r >= 0 && r < n && c >= 0 && c < m;
    }

    private static void dfs(int r, int c, long xor) {
//        xor ^= a[r][c];
        if (r == n - 1 && c == m - 1) {
            if (xor == k) {
                sum++;
            }
            return;
        }
        if (isLegal(r, c + 1)) {
            dfs(r, c + 1, xor);
        }
        if (isLegal(r + 1, c)) {
            dfs(r + 1, c, xor);
        }
    }

    private static void solve() throws IOException {
        n = nextInt();
        m = nextInt();
        k = nextLong();
        dp = new List[m];
        for (int i = 0; i < m; i++) {
            dp[i] = new ArrayList<>();
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                long a = nextLong();
                if ((i | j) == 0) {
                    dp[j].add(a);
                    continue;
                }
                if (i == 0) {
                    dp[j].add(dp[j - 1].get(0) ^ a);
                    continue;
                }
                if (j == 0) {
                    dp[j].set(0, dp[j].get(0) ^ a);
                    continue;
                }
                for (int k = 0; k < dp[j].size(); k++) {
                    dp[j].set(k, dp[j].get(k) ^ a);
                }
                for (int k = 0; k < dp[j - 1].size(); k++) {
                    dp[j].add(dp[j - 1].get(k) ^ a);
                }
            }
        }

//        dfs(0, 0, 0);
        dp[m - 1].sort(Comparator.naturalOrder());
        int low = 0;
        int high = dp[m - 1].size();
        while (low < high) {
            int mid = (low + high) / 2;
            long tmp = dp[m - 1].get(mid);
            if (tmp == k) {
                low = mid;
                break;
            }
            if (tmp < k) {
                low = mid + 1;
            } else {
                high = mid;
            }
        }

        if (low >= dp[m - 1].size()) {
            pw.print(0);
            return;
        }
        long tmp = dp[m - 1].get(low);
        if (tmp != k) {
            pw.print(0);
        } else {
            sum = 1;
            for (int i = low - 1; i >= 0; i--) {
                if (dp[m - 1].get(i) == k) {
                    sum++;
                } else {
                    break;
                }
            }
            for (int i = low + 1; i < dp[m - 1].size(); i++) {
                if (dp[m - 1].get(i) == k) {
                    sum++;
                } else {
                    break;
                }
            }
            pw.print(sum);
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