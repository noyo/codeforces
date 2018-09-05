package CFR496_div3;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

/**
 * Copyright © 2018 Chris. All rights reserved.
 *
 * @author Chris
 * 2018/7/9 15:49
 * @see CFR496_div3
 */
public class E {

    private static BufferedReader br;
    private static StreamTokenizer st;
    private static PrintWriter pw;
    private static int target;

    private static class Tree {
        int cntL;
        int left;
        int right;

        Tree(int left, int right) {
            this.left = left;
            this.right = right;
        }

        static void updateUp(Map<Integer, Tree> lineTree, int k) {
            lineTree.get(k).cntL = lineTree.get(k << 1).cntL + lineTree.get(k << 1 | 1).cntL;
        }

        static void build(int l, int r, Map<Integer, Tree> lineTree, int a[], int k) {
            lineTree.put(k, new Tree(l, r));
            if (l == r) {
                if (a[l] <= target) {
                    lineTree.get(k).cntL = 1;
                }
                return;
            }
            int mid = l + (r - l) / 2;
            build(l, mid, lineTree, a, k << 1);
            build(mid + 1, r, lineTree, a, k << 1 | 1);
            updateUp(lineTree, k);
        }

        static int query(int L, int R, int l, int r, Map<Integer, Tree> lineTree, int k) {
            if (L <= l && r <= R) {
                return lineTree.get(k).cntL;
            }
            if (r < L || l > R) {
                return 0;
            }
            int mid = l + (r - l) / 2;
            int sum = 0;
            sum += query(L, R, l, mid, lineTree, k << 1);
            sum += query(L, R, mid + 1, r, lineTree, k << 1 | 1);
            return sum;
        }
    }

    private static void solve() throws IOException {
        int n = nextInt();
        target = nextInt();

        int a[] = new int[n + 1];
        int cnt = 0;
        int k = -1;
        int left[] = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            a[i] = nextInt();
            if (a[i] == target) {
                cnt++;
                k = i;
            }
            left[i] = left[i - 1];
            if (a[i] <= target) {
                left[i]++;
            }
        }
        int L = 0;
        for (int i = k - 1; i > 0; i--) {
            int l = left[k - 1] - left[i - 1];
            if (l == k - i - l) {
                L++;
            }
        }

//        Map<Integer, Tree> lineTree = new HashMap<>(n << 1);
//        Tree.build(1, n, lineTree, a, 1);
//
//        for (int i = 1; i <= k; i++) {
//            int tmp = left[n] - left[i - 1];
//            for (int j = Math.max(i + 1, k); j <= Math.min(n, tmp * 2 + i - 1); j++) {
////                int sum = Tree.query(i, j, 1, n, lineTree, 1);
//                int sum = left[j] - left[i - 1];
//                if (sum == (j - i + 2) / 2) {
//                    cnt++;
//                }
//            }
//        }

        pw.print(cnt);
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
