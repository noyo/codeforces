package CFR509_d2;

import java.io.*;
import java.util.*;

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
        int m = nextInt();
        int d = nextInt();
        int a[] = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            int val = nextInt();
            a[i] = val;
        }
        Integer order[] = new Integer[n + 1];
        for (int i = 0; i <= n; i++) {
            order[i] = i;
        }
        Arrays.sort(order, Comparator.comparingInt(o -> a[o]));
        int cnt = 0;
        int low = 1;
        int high = n;

        while (low < high) {
            int mid = (low + high) / 2;
            boolean flag = true;
            for (int i = 1; i <= mid; i++) {
                for (int j = i + mid; j <= n; j += mid) {
                    if (a[order[j]] - a[order[j - mid]] <= d) {
                        flag = false;
                        break;
                    }
                }
                if (!flag) {
                    break;
                }
            }
            if (!flag) {
                low = mid + 1;
            } else {
                high = mid;
            }
        }
        cnt = low;
        Map<Integer, Integer> days = new HashMap<>();
        for (int i = 1; i <= cnt; i++) {
            for (int j = i; j <= n; j += cnt) {
                days.put(order[j], i);
            }
        }

        pw.println(cnt);
        for (int i = 1; i <= n; i++) {
            pw.print(days.get(i));
            if (i < n) {
                pw.print(" ");
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
