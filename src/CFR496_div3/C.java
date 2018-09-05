package CFR496_div3;

import java.io.*;
import java.util.*;

/**
 * Copyright © 2018 Chris. All rights reserved.
 *
 * @author Chris
 * 2018/7/9 15:49
 * @see CFR496_div3
 */
public class C {

    private static BufferedReader br;
    private static StreamTokenizer st;
    private static PrintWriter pw;

    static Map<Integer, Set<Integer>> map = new HashMap<>();

    private static void init(int a[]) {
        int MAX = (int) Math.pow(10, 9);
        for (int i = 0; i < a.length; i++) {
            map.put(a[i], new HashSet<>());
            int d = 1;
            while (d <= a[i]) {
                d <<= 1;
            }
            while (d - a[i] < MAX) {
                map.get(a[i]).add(d - a[i]);
                d <<= 1;
            }
        }
    }

    private static void solve() throws IOException {
        int n = nextInt();
        int a[] = new int[n];
        Map<Integer, Integer> cnt = new HashMap<>();
        for (int i = 0; i < n; i++) {
            a[i] = nextInt();
            cnt.put(a[i], cnt.getOrDefault(a[i], 0) + 1);
        }
        init(a);

        int min = 0;
        for (int key : cnt.keySet()) {
            boolean flag = false;
            Set<Integer> set = map.get(key);
            Iterator it = set.iterator();
            while (!flag && it.hasNext()) {
                int k = (int) it.next();
                if (k == key && cnt.get(k) > 1 || k != key && cnt.getOrDefault(k, 0) > 0) {
                    flag = true;
                }
            }

            if (!flag) {
                min += cnt.get(key);
            }
        }

        pw.println(min);
    }


    public static void main(String args[]) throws IOException {
        boolean oj = System.getProperty("ONLINE_JUDGE") != null;
        if (!oj) {
            System.setIn(new FileInputStream("in.txt"));
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
        br.close();
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

    private static String next() throws IOException {
//        br.readLine();
        return br.readLine();
    }
}
