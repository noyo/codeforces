package CFR505_d1_d2;

import java.io.*;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 * Copyright © 2018 Chris. All rights reserved.
 *
 * @author Chris
 * 2018/7/9 15:33
 * @see format
 */
public class B {

    private static BufferedReader br;
    private static StreamTokenizer st;
    private static PrintWriter pw;
    
    static Set<Integer> getCd(int n) {
        Set<Integer> set = new HashSet<>();
        set.add(n);
        int qrt = (int) Math.sqrt(n);
        for (int i = 2; i <= qrt; i++) {
            if (n % i == 0) {
                set.add(i);
                set.add(n / i);
            }
        }

        return set;
    }

    private static void solve() throws IOException {
        int n = nextInt();

        Set<Integer> set = new HashSet<>();
        set.addAll(getCd(nextInt()));
        set.addAll(getCd(nextInt()));

        for (int i = 1; i < n; i++) {
            int a = nextInt();
            int b = nextInt();
            set.removeIf(val -> (a % val != 0) && (b % val != 0));
            if (set.size() == 0) {
                break;
            }
        }

        if (set.size() == 0) {
            pw.print(-1);
        } else {
            Iterator<Integer> it = set.iterator();
            pw.print(it.next());
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
