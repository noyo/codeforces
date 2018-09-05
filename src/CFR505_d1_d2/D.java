package CFR505_d1_d2;

import java.io.*;
import java.util.*;

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

    static boolean canLink(int low, int high) {
        while (low > 0) {
            int r = high % low;
            high = low;
            low = r;
        }
        return high > 1;
    }

    private static void solve() throws IOException {
        int n = nextInt();
        int a[] = new int[n];

        a[0] = nextInt();
        Map<Integer, Integer> cnt = new HashMap<>();
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 1; i < n; i++) {
            a[i] = nextInt();
            boolean flag = false;
            for (int j = i - 1; j >= 0; j--) {
                if (cnt.getOrDefault(a[j], 0) >= 2) {
                    continue;
                }
                if (canLink(a[j], a[i])) {
                    cnt.put(a[j], cnt.getOrDefault(a[j], 0) + 1);
                    flag = true;
                    break;
                }
            }
            if (!flag) {
                queue.offer(a[i]);
            }
        }
        int cur = 0;
        Set<Integer> vst = new HashSet<>();
        while (cur < n && !queue.isEmpty()) {
            while (cur < n && a[cur] <= queue.peek()) {
                cur++;
            }
            if (cur >= n) {
                break;
            }
            boolean flag = false;
            for (int i = cur; i < n; i++) {
                if (vst.contains(a[i])) {
                    continue;
                }
                if (canLink(queue.peek(), a[i])) {
                    vst.add(a[i]);
                    flag = true;
                    break;
                }
            }
            if (!flag) {
                break;
            }
            queue.poll();
        }

        if (!queue.isEmpty()) {
            pw.print("No");
        } else {
            pw.print("Yes");
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
