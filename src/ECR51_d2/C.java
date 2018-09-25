package ECR51_d2;

import java.io.*;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

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

    private static void solve() throws IOException {
        int n = nextInt();
        int s[] = new int[n];
        int cnt[] = new int[101];
        for (int i = 0; i < n; i++) {
            s[i] = nextInt();
            cnt[s[i]]++;
        }
        int single = 0;
        int mul = 0;
        for (int i = 1; i < 101; i++) {
            if (cnt[i] == 1) {
                single++;
            } else if (cnt[i] > 2) {
                mul++;
            }
        }
        if (single % 2 == 1 && mul == 0) {
            pw.println("NO");
            return;
        }
        int k = -1;
        for (int i = 0; i < n; i++) {
            if (cnt[s[i]] > 2) {
                k = i;
                break;
            }
        }
        pw.println("YES");
        boolean flag = false;
        for (int i = 0; i < n; i++) {
            if (cnt[s[i]] != 1) {
                if (k == i && single % 2 == 1) {
                    pw.print("B");
                } else {
                    pw.print("A");
                }
            } else if (!flag) {
                flag = !flag;
                pw.print("A");
            } else {
                flag = !flag;
                pw.print("B");
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
