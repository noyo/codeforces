package AIMTR_d1_d2;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

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

    static int MOD = 1000000007;

    static void insert(List<Integer> list, int val) {
        int low = 0;
        int high = list.size() - 1;
        while (low <= high) {
            int mid = (low + high) / 2;
            if (list.get(mid) <= val) {
                low = mid + 1;
            } else {
                high = mid;
            }
        }
        list.add(low, val);
    }

    static int index(List<Integer> list, int val) {
        int low = 0;
        int high = list.size() - 1;
        while (low < high) {
            int mid = (low + high) / 2;
            if (list.get(mid) < val) {
                low = mid + 1;
            } else if (list.get(mid) > val){
                high = mid;
            } else {
                return mid;
            }
        }
        if (low >= list.size() || list.get(low) != val) {
            return -1;
        }
        return low;
    }

    private static void solve() throws IOException {
        int n = nextInt();
        boolean flag = false;
        long ans = 1;
        List<Integer> list = new ArrayList<>();
        PriorityQueue<Integer> buy = new PriorityQueue<>();
        PriorityQueue<Integer> sell = new PriorityQueue<>();

        nextLine();
        for (int i = 0; i < n; i++) {
            String ss[] = nextLine().split(" ");
            String dir = ss[0];
            int p = Integer.parseInt(ss[1]);
            if (!flag) {
                if ("ADD".equals(dir)) {
                    insert(list, p);
                } else {
                    flag = true;
                    int left = index(list, p);
                    if (left < 0) {
                        pw.print(0);
                        return;
                    }
                    if (left > 0) {
                        for (int j = 0; j < left; j++) {
                            buy.offer(-list.get(j));
                        }
                    }
                    if (left + 1 < list.size()) {
                        for (int j = left + 1; j < list.size(); j++) {
                            sell.offer(list.get(j));
                        }
                    }
                    if (buy.isEmpty() && sell.isEmpty()) {
                        flag = false;
                    }
                    list.clear();
                    ans = (ans * 2) % MOD;
                }
            } else {
                if ("ADD".equals(dir)) {
                    if (!sell.isEmpty() && p > sell.peek()) {
                        pw.print(0);
                        return;
                    }
                    if (!buy.isEmpty() && p < -buy.peek()) {
                        pw.print(0);
                        return;
                    }
                    insert(list, p);
                } else {
                    int index = -1;
                    if (!buy.isEmpty() && p == -buy.peek()) {
                        buy.poll();
                    } else if (!sell.isEmpty() && p == sell.peek()) {
                        sell.poll();
                    } else if ((index = index(list, p)) != -1) {
                        for (int j = 0; j < index; j++) {
                            buy.offer(-list.get(j));
                        }
                        for (int j = index + 1; j < list.size(); j++) {
                            sell.offer(list.get(j));
                        }
                        ans = (ans * 2) % MOD;
                        list.clear();
                    } else {
                        pw.print(0);
                        return;
                    }
                }
            }

        }

        if (ans == 1) {
            for (int i = 0; i < n; i++) {
                ans = (ans * 2) % MOD;
            }
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
