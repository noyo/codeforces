package CFR497_d2;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

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

    static int N = 100001;
    static List<Integer>[] appro = new List[N];
    static int C[][] = new int[1001][4];

    private static void c() {
        for (int j = 1; j <= 1000; j++) {
            C[j][0] = 1;
            for (int i = 1; i <= 3 && i <= j; i++) {
                C[j][i] = C[j][i - 1] * (j - i + 1) / i;
            }
        }
    }

    private static void init() {
        c();
        for (int i = 1; i < N; i++) {
            int sqrt = (int) Math.sqrt(i);
            int k = sqrt * 2;
            if (i % sqrt == 0) {
                k--;
            }
            appro[i] = new ArrayList<>(k);
            for (int j = 1; j <= sqrt; j++) {
                if (i % j == 0) {
                    appro[i].add(j);
                    if (j != i / j) {
                        appro[i].add(i / j);
                    }
                }
            }
            appro[i].sort(Comparator.naturalOrder());
        }
    }

    private static int gcd(int a, int b) {
        while (b != 0) {
            int tmp = a % b;
            a = b;
            b = tmp;
        }
        return a;
    }


    private static void solve() throws IOException {
        init();

        int T = nextInt();
        while (T-- > 0) {
            int tri[] = {nextInt(), nextInt(), nextInt()};
            Arrays.sort(tri);
            List<Integer> a = appro[tri[0]];
            List<Integer> b = appro[tri[1]];
            List<Integer> c = appro[tri[2]];

            int ab = gcd(tri[1], tri[0]);
            int bc = gcd(tri[2], tri[1]);
            int ac = gcd(tri[2], tri[0]);
            int abc = gcd(tri[2], ab);

            int nab = appro[ab].size();
            int nbc = appro[bc].size();
            int nac = appro[ac].size();
            int nabc = appro[abc].size();

            int sum = a.size() * b.size() * c.size();
            int fail = 0;

            //A~B~C
            int n1 = a.size() - (nab + nac - nabc);
            if (nbc > 1) {
                fail += n1 * (nbc * nbc - C[nbc][1] - C[nbc][2]);
            }
            //A~BC
            int n2 = nac - nabc;
            if (n2 > 1) {
                fail += b.size() * (n2 * n2 - C[n2][1] - C[n2][2]);
            }
            //AB~C
            int n3 = nab - nabc;
            if (n3 > 1) {
                fail += c.size() * (n3 * n3 - C[n3][1] - C[n3][2]);
            }
            //ABC
            int n4 = nabc;
            if (n4 > 1) {
                fail += n4 * n4 * n4 - (C[n4][1] + C[n4][2] * 2 + C[n4][3]);
            }

            pw.print(sum - fail);
            if (T > 0) {
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
