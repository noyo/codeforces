package CFR498_d3;

import java.io.*;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

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

    static class Point implements Comparable {
        int index;
        int val;

        Point(int index, int va) {
            this.index = index;
            this.val = va;
        }

        @Override
        public int compareTo(Object o) {
            Point p = (Point) o;
            return -val + p.val;
        }
    }

    private static void solve() throws IOException {
        int n = nextInt();
        int k = nextInt();


        PriorityQueue<Point> queue = new PriorityQueue<>(Comparator.naturalOrder());
        int a[] = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            a[i] = nextInt();
            Point point = new Point(i, a[i]);
            queue.offer(point);
        }

        int sum = 0;
        int b[] = new int[k + 1];
        for (int i = 1; i <= k; i++) {
            b[i] = queue.poll().index;
            sum += a[b[i]];
        }
        Arrays.sort(b);
        pw.println(sum);
        for (int i = 1; i <= k; i++) {
            if (i < k) {
                pw.print(b[i] - b[i - 1]);
            } else {
                pw.print(n - b[i - 1]);
            }
            if (i < k) {
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