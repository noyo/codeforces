package ECR49_d2;

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
        int T = nextInt();
        while (T-- > 0) {
            int n = nextInt();
            nextLine();
            String s = nextLine();

            boolean flag = true;
            for (int i = 0; i < n / 2; i++) {
                char left = s.charAt(i);
                char right = s.charAt(n - i - 1);
                if (left == 'a') {
                    if (right != 'a' && right != 'c') {
                        flag = false;
                        break;
                    }
                } else if (left == 'z') {
                    if (right != 'z' && right != 'x') {
                        flag = false;
                        break;
                    }
                } else if (left != right && Math.abs(left - right) != 2) {
                    flag = false;
                    break;
                }
            }
            if (flag) {
                pw.print("YES");
            } else {
                pw.print("NO");
            }
            if (T > 0) {
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
