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
public class D {

    private static BufferedReader br;
    private static StreamTokenizer st;
    private static PrintWriter pw;

    private static void solve() throws IOException {
        int n = nextInt();
        List<Integer> edges[] = new List[n + 1];
        for (int i = 1; i <= n; i++) {
            edges[i] = new ArrayList<>();
        }
        for (int i = 0; i < n - 1; i++) {
            int a = nextInt();
            int b = nextInt();
            edges[a].add(b);
            edges[b].add(a);
        }

        int a[] = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = nextInt();
        }

        if (a[0] != 1) {
            pw.print("No");
            return;
        }

        Queue<Integer> queue = new LinkedList<>();
        queue.offer(1);
        int curN = 1;
        int lastN = 1;
        boolean vst[] = new boolean[n + 1];
        vst[1] = true;
        int cur = 1;
        while (!queue.isEmpty()) {
            curN = 0;
            for (int i = 0; i < lastN; i++) {
                int e = queue.poll();
                Set<Integer> set = new HashSet<>();
                int tmp = 0;
                for (int j = 0; j < edges[e].size(); j++) {
                    int t = edges[e].get(j);
                    if (vst[t]) {
                        continue;
                    }
                    set.add(t);
                    vst[t] = true;
                    tmp++;
                }
                curN += tmp;
                for (int j = cur; j < cur + tmp; j++) {
                    if (!set.contains(a[j])) {
                        pw.print("No");
                        return;
                    }
                    queue.offer(a[j]);
                }
                cur += tmp;
            }
            lastN = curN;
        }
        pw.print("Yes");
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
