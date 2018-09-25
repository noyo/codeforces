package CFR508_d2;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
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

    private static void solve() throws IOException {
        int n = nextInt();
        List<Integer> a = new ArrayList<>();
        List<Integer> b = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            a.add(nextInt());
        }
        for (int i = 0; i < n; i++) {
            b.add(nextInt());
        }
        a.sort(Comparator.naturalOrder());
        b.sort(Comparator.naturalOrder());
        int ai = n - 1;
        int bi = n - 1;
        long sumA = 0;
        long sumB = 0;
        int flag = 0;
        while (ai >= 0 || bi >= 0) {
            if (flag == 0) {
                if (ai < 0) {
                    bi--;
                } else if (bi < 0) {
                    sumA += a.get(ai);
                    ai--;
                } else {
                    if (a.get(ai) > b.get(bi)) {
                        sumA += a.get(ai);
                        ai--;
                    } else {
                        bi--;
                    }
                }
            } else {
                if (ai < 0) {
                    sumB += b.get(bi);
                    bi--;
                } else if (bi < 0) {
                    ai--;
                } else {
                    if (b.get(bi) > a.get(ai)) {
                        sumB += b.get(bi);
                        bi--;
                    } else {
                        ai--;
                    }
                }
            }
            flag = (flag + 1) % 2;
        }
        pw.print(sumA - sumB);
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
