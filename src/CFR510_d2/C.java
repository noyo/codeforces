package CFR510_d2;

import java.io.*;
import java.util.ArrayList;
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
        int a[] = new int[n + 1];
        List<Integer> neg = new ArrayList<>();
        List<Integer> zero = new ArrayList<>();
        List<Integer> pos = new ArrayList<>();
        int maxNeg = -1;
        for (int i = 1; i <= n; i++) {
            a[i] = nextInt();
            if (a[i] < 0) {
                if (maxNeg == -1 || a[i] > a[maxNeg]) {
                    maxNeg = i;
                }
                neg.add(i);
            } else if (a[i] == 0) {
                zero.add(i);
            } else {
                pos.add(i);
            }
        }
        List<Integer> finalPos = new ArrayList<>();
        if (pos.size() > 0) {
            finalPos.add(pos.get(pos.size() - 1));
        }
        for (int i = 1; i < pos.size(); i++) {
            pw.println(1 + " " + pos.get(i - 1) + " " + pos.get(i));
        }
        int end = -1;
        for (int i = 1; i < neg.size(); i++) {
            if (i == 1 && neg.size() % 2 == 1 && maxNeg == neg.get(0)) {
                continue;
            }
            if (neg.size() % 2 == 1 && maxNeg == neg.get(i)) {
                if (i == neg.size() - 1) {
                    break;
                }
                i++;
                pw.println(1 + " " + neg.get(i - 2) + " " + neg.get(i));
            } else {
                pw.println(1 + " " + neg.get(i - 1) + " " + neg.get(i));
            }
            end = neg.get(i);
        }
        if (end != -1) {
            finalPos.add(end);
        }
        for (int i = 1; i < finalPos.size(); i++) {
            pw.println(1 + " " + finalPos.get(i - 1) + " " + finalPos.get(i));
        }
        if (zero.size() == 0) {
            if (neg.size() % 2 == 1) {
                pw.println(2 + " " + maxNeg);
            }
        } else {
            if (neg.size() % 2 == 1) {
                zero.add(maxNeg);
            }
            end = zero.get(0);
            for (int i = 1; i < zero.size(); i++) {
                pw.println(1 + " " + zero.get(i - 1) + " " + zero.get(i));
                end = zero.get(i);
            }
            if (finalPos.size() > 0) {
                pw.println(2 + " " + end);
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
