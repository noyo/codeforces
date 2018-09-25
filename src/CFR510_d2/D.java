package CFR510_d2;

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

    static int[] BT;

    static int lowbit(int x) {
        return x & (-x);
    }

    static void add(int x) {
        while (x < BT.length) {
            BT[x]++;
            x += lowbit(x);
        }
    }

    static int get(int x) {
        int s = 0;
        while (x > 0) {
            s += BT[x];
            x -= lowbit(x);
        }
        return s;
    }

    private static void solve() throws IOException {
        String ss[] = nextSS(" ");
        int n = Integer.parseInt(ss[0]);
        long t = Long.parseLong(ss[1]);
        long sum[] = new long[n + 1];
        long ans = 0;
        Map<Long, Integer> idx = new HashMap<>();
        List<Long> data = new ArrayList<>();

        data.add(-1L);
        for (int i = 1; i <= n; i++) {
            sum[i] = nextInt();
            sum[i] += sum[i - 1];
            data.add(sum[i] - 1);
            data.add(sum[i] - t);
        }
        data.sort(Comparator.naturalOrder());
        int cur = 1;
        List<Long> avail = new ArrayList<>();
        for (int i = 0; i < data.size(); i++) {
            if (!idx.containsKey(data.get(i))) {
                idx.put(data.get(i), cur++);
                avail.add(data.get(i));
            }
        }
        BT = new int[cur + 1];
        for (int i = n; i >= 1; i--) {
            add(idx.get(sum[i] - t));
            ans += get(idx.get(sum[i - 1] - 1));
        }
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
