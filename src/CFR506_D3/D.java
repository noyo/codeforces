package CFR506_D3;

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

    static long POW[] = new long[11];

    static class Point {
        int index;
        long val;

        Point(int index, long val) {
            this.index = index;
            this.val = val;
        }
    }

    private static void solve() throws IOException {
        int n = nextInt();
        if (n < 2) {
            pw.print(0);
            return;
        }
        int k = nextInt();
        long a[] = new long[n];

        POW[0] = 1L;
        for (int i = 1; i < 10; i++) {
            POW[i] = 10 * POW[i - 1];
        }
        for (int i = 0; i < n; i++) {
            a[i] = nextLong();
        }

        long max = getMax(n, k, a) + getMax2(n, k, a);
        pw.print(max);
    }

    private static long getMax(int n, int k, long[] a) {
        Map<Long, Set<Point>> mod = new HashMap<>();
        Map<Long, Integer> next = new HashMap<>();
        long ans[] = new long[n];
        mod.put(a[n - 1] % k, new HashSet<>());
        mod.get(a[n - 1] % k).add(new Point(n - 1, a[n - 1]));
        int neg = 0;
        for (int i = n - 2; i >= 0; i--) {

            long r = a[i] % k;

            if (next.containsKey(r)) {
                ans[i] = ans[next.get(r)];
                long val = a[next.get(r)] / 10;
                int pow = 1;
                while (val > 0) {
                    pow++;
                    val /= 10;
                }
                val = a[i] * POW[pow] + a[next.get(r)];
                if (val % k == 0) {
                    ans[i]++;
                }
            } else {
                long tmp = r;
                Set<Long> vst = new HashSet<>();
                for (int j = 1; j <= 9; j++) {
                    tmp = (tmp * 10) % k;
                    if (vst.contains(tmp) || !mod.containsKey((k - tmp) % k)) {
                        vst.add(tmp);
                        continue;
                    }
                    vst.add(tmp);
                    Set<Point> set = mod.get((k - tmp) % k);
                    Iterator<Point> it = set.iterator();
                    while (it.hasNext()) {
                        Point point = it.next();
                        if (point.val >= POW[j - 1] && point.val < POW[j]) {
                            ans[i]++;
                            ans[point.index]++;
                            neg++;
                        }
                    }
                }
            }

            if (!mod.containsKey(r)) {
                mod.put(r, new HashSet<>());
            }
            mod.get(r).add(new Point(i, a[i]));
            next.put(r, i);
        }

        long max = 0;
        for (int i = 0; i < n; i++) {
            max += ans[i];
        }
        return max - neg;
    }

    private static long getMax2(int n, int k, long[] a) {
        Map<Long, Set<Point>> mod = new HashMap<>();
        Map<Long, Integer> next = new HashMap<>();
        long ans[] = new long[n];
        int neg = 0;
        mod.put(a[0] % k, new HashSet<>());
        mod.get(a[0] % k).add(new Point(0, a[0]));
        for (int i = 1; i < n; i++) {

            long r = a[i] % k;

            if (next.containsKey(r)) {
                ans[i] = ans[next.get(r)];
                long val = a[next.get(r)] / 10;
                int pow = 1;
                while (val > 0) {
                    pow++;
                    val /= 10;
                }
                val = a[i] * POW[pow] + a[next.get(r)];
                if (val % k == 0) {
                    ans[i]++;
                }
            } else {
                long tmp = r;
                Set<Long> vst = new HashSet<>();
                for (int j = 1; j <= 9; j++) {
                    tmp = (tmp * 10) % k;
                    if (vst.contains(tmp) || !mod.containsKey((k - tmp) % k)) {
                        vst.add(tmp);
                        continue;
                    }
                    vst.add(tmp);
                    Set<Point> set = mod.get((k - tmp) % k);
                    Iterator<Point> it = set.iterator();
                    while (it.hasNext()) {
                        Point point = it.next();
                        if (point.val >= POW[j - 1] && point.val < POW[j]) {
                            ans[i]++;
                            ans[point.index]++;
                            neg++;
                        }
                    }
                }
            }

            if (!mod.containsKey(r)) {
                mod.put(r, new HashSet<>());
            }
            mod.get(r).add(new Point(i, a[i]));
            next.put(r, i);
        }

        long max = 0;
        for (int i = 0; i < n; i++) {
            max += ans[i];
        }
        return max - neg;
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
