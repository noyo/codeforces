package CFR503_d2;

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

    static class Vote {
        int p;
        int c;

        Vote(int p, int c) {
            this.p = p;
            this.c = c;
        }
    }

    private static void solve() throws IOException {
        int n = nextInt();
        int m = nextInt();

        Map<Integer, Integer> cost = new HashMap<>();
        List<Vote> votes = new ArrayList<>();
        PriorityQueue<Integer>[] vp = new PriorityQueue[m + 1];
        for (int i = 1; i <= m; i++) {
            vp[i] = new PriorityQueue<>();
        }

        for (int i = 0; i < n; i++) {
            int p = nextInt();
            int c = nextInt();
            vp[p].offer(i);
            votes.add(new Vote(i, c));
            cost.put(i, c);
        }
        votes.sort(Comparator.comparingInt(o -> o.c));

        Integer order[] = new Integer[m + 1];
        for (int i = 0; i <= m; i++) {
            order[i] = i;
        }
        Arrays.sort(order, Comparator.comparingInt(o -> vp[o].size()));

        int cur = 0;
        while (vp[1].size() != vp[order[m]].size()) {
            while (vp[1].contains(votes.get(cur).p)) {
                cur++;
            }
            int max = vp[order[m]].size();
            boolean flag = false;
            for (int i = m; i >= 0; i--) {
                if (vp[order[i]].size() < max) {
                    break;
                }
                if (vp[order[i]].contains(votes.get(cur).p)) {
                    flag = true;
                    vp[order[i]].remove(votes.get(cur).p);
                    break;
                }
//                add += cost.get(vp[order[i]].peek());
            }
            if (flag) {
                vp[1].add(cur++);
            } else {
//                if (cost.get(votes.get(cur).p) < add) {
//                    vp[1].add(cur++);
//                } else {
//
//                }
            }
            Arrays.sort(order, Comparator.comparingInt(o -> vp[o].size()));
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
