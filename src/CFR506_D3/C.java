package CFR506_D3;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
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
        Map<Integer, Integer> left = new HashMap<>();
        Map<Integer, Integer> right = new HashMap<>();

        PriorityQueue<Integer> low = new PriorityQueue<>();
        PriorityQueue<Integer> high = new PriorityQueue<>();
        for (int i = 0; i < n; i++) {
            int l = -nextInt();
            int r = nextInt();
            low.offer(l);
            high.offer(r);

            if (!left.containsKey(l) || left.get(l) > r) {
                left.put(l, r);
            }
            if (!right.containsKey(r) || right.get(r) < l) {
                right.put(r, l);
            }
        }

        int ans = 0;
        int pollLow = low.poll();
        int poolHigh = left.get(pollLow);
        high.remove(poolHigh);
        ans = Math.max(ans, high.peek() + low.peek());

        low.offer(pollLow);
        high.offer(poolHigh);
        poolHigh = high.poll();
        pollLow = right.get(poolHigh);
        low.remove(pollLow);
        ans = Math.max(ans, high.peek() + low.peek());

        pw.print(ans);
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
