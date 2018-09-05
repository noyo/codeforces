package MTC_d1_d2;

import java.io.*;
import java.util.*;

/**
 * Copyright © 2018 Chris. All rights reserved.
 *
 * @author Chris
 * 2018/7/9 15:33
 * @see format
 */
public class E {

    private static BufferedReader br;
    private static StreamTokenizer st;
    private static PrintWriter pw;

    private static void solve() throws IOException {
        int n = nextInt();
        int m = nextInt();
        int k = nextInt();

        Set<Integer> edges[] = new Set[n + 1];
        for (int i = 1; i <= n; i++) {
            edges[i] = new HashSet<>();
        }
        Set<Integer> set = new HashSet<>();
        Map<Integer, Integer> cnt = new HashMap<>();
        for (int i = 1; i <= m; i++) {
            int a = nextInt();
            int b = nextInt();
            edges[a].add(b);
            edges[b].add(a);

            if (!set.contains(a) && !set.contains(b)) {
                if (edges[a].size() >= k && edges[b].size() >= k) {
                    if (set.size() > 0) {
                        int t1 = 0;
                        Iterator<Integer> iterator = edges[a].iterator();
                        while (iterator.hasNext()) {
                            if (set.contains(iterator.next())) {
                                t1++;
                            }
                            if (t1 >= k - 1) {
                                break;
                            }
                        }
                        int t2 = 0;
                        iterator = edges[b].iterator();
                        while (iterator.hasNext()) {
                            if (set.contains(iterator.next())) {
                                t2++;
                            }
                            if (t2 >= k - 1) {
                                break;
                            }
                        }
                        if (t1 >= k - 1 && t2 >= k - 1) {
                            set.add(a);
                            set.add(b);
                        }
                    } else {
                        Queue<Integer> queue = new LinkedList<>();
                        queue.offer(a);
                        int cur = k - 1;
                        while (cur > 0) {
                            int reCnt = 0;
                            Iterator<Integer> iterator = edges[a].iterator();
                            while (iterator.hasNext()) {
                                int u = iterator.next();
                                if (edges[b].contains(u)) {
                                    reCnt++;
                                }
                            }
                            if (reCnt < cur) {
                                queue.clear();
                                break;
                            }
                        }

                    }
                }
            } else if (!set.contains(a)) {
                int t1 = 0;
                Iterator<Integer> iterator = edges[a].iterator();
                while (iterator.hasNext()) {
                    if (set.contains(iterator.next())) {
                        t1++;
                    }
                    if (t1 >= k) {
                        set.add(a);
                        break;
                    }
                }
            } else if (!set.contains(b)) {
                int t2 = 0;
                Iterator<Integer> iterator = edges[b].iterator();
                while (iterator.hasNext()) {
                    if (set.contains(iterator.next())) {
                        t2++;
                    }
                    if (t2 >= k) {
                        set.add(b);
                        break;
                    }
                }
            }

            pw.print(set.size());

            if (i < m) {
                pw.println();
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
