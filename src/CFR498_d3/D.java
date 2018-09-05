package CFR498_d3;

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
        nextLine();
        char[] a = nextLine().toCharArray();
        char[] b = nextLine().toCharArray();

        int max = 0;
        int mid = (n - 1) / 2;
        for (int i = 0; i <= mid; i++) {
            if (i == mid && n % 2 == 1) {
                if (a[i] != b[i]) {
                    max++;
                }
                break;
            }
            if (b[i] == b[n - 1 - i]) {
                if (a[i] != a[n - 1 - i]) {
                    max++;
                }
                continue;
            }
            int[] cnt = new int[128];
            cnt[b[i]]++;
            cnt[b[n - 1 - i]]++;
            cnt[a[i]]--;
            cnt[a[n - 1 - i]]--;

            if (cnt[b[i]] > 0) {
                max += cnt[b[i]];
            }
            if (cnt[b[n - 1 - i]] > 0) {
                max += cnt[b[n - 1 - i]];
            }
        }
        pw.print(max);
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