package ECR51_d2;

import java.io.*;
import java.util.*;

/**
 * Copyright © 2018 Chris. All rights reserved.
 *
 * @author Chris
 * 2018/7/9 15:33
 * @see format
 */
public class F {

    private static BufferedReader br;
    private static StreamTokenizer st;
    private static PrintWriter pw;

    static final int MOD = 998244353;

    static int N = 100005;
    static Map<Integer, Long>[] edges = new HashMap[N + 1];

    static void floyd() {
        for (int k = 1; k <= N; k++) {
            for (int i = 1; i <= N; i++) {
                if (k != i) {
                    long t = (null != edges[k] && edges[k].containsKey(i)) ? edges[k].get(i) : -1;
                    if (t == -1) {
                        continue;
                    }
                    for (int j = 1; j <= ((k < i) ? k : i); j++) {
                        if (!edges[k].containsKey(j)) {
                            continue;
                        }
                        if (!edges[i].containsKey(j) || t + edges[k].get(j) < edges[i].get(j))  {
                            edges[i].put(j, (t + edges[k].get(j) % MOD));
                            edges[j].put(i, (t + edges[k].get(j) % MOD));
                        }
                    }
                    for (int j = k + 1; j <= i; j++) {
                        if (!edges[k].containsKey(j)) {
                            continue;
                        }
                        if (!edges[i].containsKey(j) || t + edges[j].get(k) < edges[i].get(j)) {
                            edges[i].put(j, (t + edges[j].get(k)) % MOD);
                            edges[j].put(i, (t + edges[j].get(k)) % MOD);
                        }
                    }
                }
            }
        }
    }

    private static void solve() throws IOException {
        int n = nextInt();
        int m = nextInt();
        for (int i = 0; i < m; i++) {
            int a = nextInt();
            int b = nextInt();
            long d = nextInt();
            if (null == edges[a]) {
                edges[a] = new HashMap<>();
            }
            if (null == edges[b]) {
                edges[b] = new HashMap<>();
            }
            edges[a].put(b, d);
            edges[b].put(a, d);
        }

        for (int i = 1; i <= N; i++) {
            if (null == edges[i]) {
                continue;
            }
            if (edges[i].size() == 2) {
                List<Integer> list = new ArrayList<>();
                for (int key : edges[i].keySet()) {
                    list.add(key);
                }
                int a = list.get(0);
                int b = list.get(1);
                if (edges[a].containsKey(b)) {
                    edges[a].put(b, Math.min(edges[i].get(a) + edges[i].get(b), edges[a].get(b)));
                    edges[b].put(a, Math.min(edges[i].get(a) + edges[i].get(b), edges[a].get(b)));
                } else {
                    edges[a].put(b, edges[i].get(a) + edges[i].get(b));
                    edges[b].put(a, edges[i].get(a) + edges[i].get(b));
                }
                edges[i].clear();
            }
        }

        floyd();

        int q = nextInt();
        while (q-- > 0) {

            int u = nextInt();
            int v = nextInt();

            if (null == edges[u]) {
                pw.print(edges[v].get(u));
            } else {
                pw.print(edges[u].get(v));
            }

            if (q > 0) {
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
