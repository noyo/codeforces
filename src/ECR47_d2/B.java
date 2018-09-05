package ECR47_d2;

import java.io.*;
import java.util.*;

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

    private static void solve() throws IOException {
        String s = nextLine();

        if (s.length() <= 1) {
            pw.print(s);
            return;
        }
        char ch[] = s.toCharArray();
        if (s.indexOf('2') == -1 || s.indexOf('0') == -1) {
            Arrays.sort(ch);
            pw.print(String.valueOf(ch));
            return;
        }
        if (s.indexOf('1') == -1) {
            pw.print(s);
            return;
        }

        StringBuilder sb = new StringBuilder();

        int insert1 = -1;
        for (int i = 0; i < ch.length; i++) {
            if (ch[i] == '0') {
                sb.append('0');
            } else if (ch[i] == '2') {
                if (insert1 == -1) {
                    insert1 = sb.length();
                }
                sb.append('2');
            }
        }
        int k = s.length() - sb.length();
        for (int j = 0; j < k; j++) {
            sb.insert(insert1++, '1');
        }

        pw.print(sb.toString());
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
