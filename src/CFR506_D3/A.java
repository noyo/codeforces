package CFR506_D3;

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
        int k = nextInt();
        nextLine();
        String t = nextLine();

        StringBuilder ans = new StringBuilder(t);
        int next = n;

        for (int i = 1; i < n; i++) {
            boolean flag = true;
            for (int j = i; j < n; j++) {
                if (t.charAt(j) != t.charAt(j - i)) {
                    flag = false;
                    break;
                }
            }
            if (flag) {
                next = i;
                break;
            }
        }

        if (next == n) {
            for (int i = 1; i < k; i++) {
                ans.append(t);
            }
        } else {
            t = t.substring(n - next, n);
            for (int i = 1; i < k; i++) {
                ans.append(t);
            }
        }
        pw.print(ans.toString());
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
