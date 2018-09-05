package CFR498_d3;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    static class Tree {
        int index;
        List<Integer> children = new ArrayList<>();

        Tree(int index) {
            this.index = index;
        }
    }

    static Map<Integer, Tree> map = new HashMap<>();
    static Map<Integer, Integer> indexMap = new HashMap<>();
    static List<Integer> pre = new ArrayList<>();
    static int size[];
    static int m = 1;

    private static int getPreOrder(Tree root) {
        pre.add(root.index);

        size[root.index] = 1;
        indexMap.put(root.index, m);
        List<Integer> list = root.children;
        for (int i = 0; i < list.size(); i++) {
            m++;
            size[root.index] += getPreOrder(map.get(list.get(i)));
        }
        return size[root.index];
    }

    private static void solve() throws IOException {
        int n = nextInt();
        int q = nextInt();
        size = new int[n + 1];

        Tree root = new Tree(1);
        map.put(1, root);
        for (int i = 2; i <= n; i++) {
            int j = nextInt();
            Tree node = new Tree(i);
            map.put(i, node);
            map.get(j).children.add(i);
        }

        getPreOrder(root);
        for (int i = 0; i < q; i++) {
            int parent = nextInt();
            int k = nextInt();
            if (k > size[parent]) {
                pw.print(-1);
            } else {
                pw.print(pre.get(indexMap.get(parent) + k - 2));
            }

            if (i < q - 1) {
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