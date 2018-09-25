package CFR507_d2;

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
        long t = nextLong();
        long a[] = new long[n + 1];
        for (int i = 1; i <= n; i++) {
            a[i] = nextLong();
        }

        int x[] = new int[n + 1];
        PriorityQueue<Integer> queue = new PriorityQueue<>();
        Set<Integer> set = new HashSet<>();
        for (int i = 1; i <= n; i++) {
            x[i] = nextInt();
            if (x[i] == i) {
                queue.offer(x[i]);
                set.add(i);
            }
        }

        long b[] = new long[n + 1];
        int cnt = 0;
        while (!queue.isEmpty()) {
            int poll = queue.poll();
            if (!set.contains(poll)) {
                pw.print("No");
                return;
            }
            int idx = poll;
            cnt++;
            if (idx == n) {
                b[idx] = a[idx] + t + n;
            } else {
                b[idx] = a[idx + 1] + t - 1;
            }
            while (x[idx - 1] == poll) {
                idx--;
                cnt++;
                b[idx] = b[idx + 1] - 1;
                if (b[idx] < a[idx + 1] + t) {
                    pw.print("No");
                    return;
                }
            }
        }
        if (cnt != n || b[1] < 1) {
            pw.print("No");
            return;
        }
        pw.println("Yes");
        for (int i = 1; i <= n; i++) {
            pw.print(b[i]);
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
