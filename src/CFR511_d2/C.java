package CFR511_d2;

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

    /**
     * ps: n >= m, choose m from n;
     */
    private static long[][] initC(int n) {
        long c[][] = new long[n][n];

        for (int i = 0; i < n; i++) {
            c[i][0] = 1;
        }

        for (int i = 1; i < n; i++) {
            for (int j = 1; j <= i; j++) {
                c[i][j] = c[i - 1][j - 1] + c[i - 1][j];
            }
        }
        return c;
    }

    /**
     * ps: n >= m, choose m from n;
     */
    private static int c(int n, int m) {
        int c[] = new int[n];
        c[0] = 1;
        int min = Math.min(m, n - m);
        for (int i = 1; i <= min; i++) {
            c[i] = c[i - 1] * (n - i + 1) / i;
        }
        return c[m];
    }

    static int[] getBit(int num) {
        int bit[] = new int[32];
        int cur = 0;
        while (num > 0) {
            bit[cur++] = num % 2;
            num >>= 1;
        }
        return bit;
    }

    static int getBitLen(int num) {
        int cur = 0;
        while (num > 0) {
            cur++;
            num >>= 1;
        }
        return cur;
    }

    private static int gcd(int a, int b) {
        if (a < b) {
            int tmp = a;
            a = b;
            b = tmp;
        }
        while (b != 0) {
            int tmp = a % b;
            a = b;
            b = tmp;
        }
        return a;
    }


    static List<Integer> getPri() {
        List<Integer> list = new ArrayList<>();
        list.add(2);
//        int n = (int) Math.sqrt(15000000);
        int n = 15000000;
        boolean p[] = new boolean[n + 1];
        p[2] = true;

        for (int i = 3; i <= n; i += 2) {
            p[i] = true;
        }

        for (int i = 3; i <= Math.sqrt(n); i+= 2) {
            if (!p[i]) {
                continue;
            }
            for (int j = i * i; j <=n; j += i << 1) {
                p[j] = false;
            }
        }
        for (int i = 3; i <= n; i += 2) {
            if (p[i]) {
                list.add(i);
            }
        }
        return list;
    }

    private static void solve() throws IOException {
        int n = nextInt();
        int a[] = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = nextInt();
        }
        Arrays.sort(a);
        if (a[n - 1] == 1) {
            pw.print(-1);
            return;
        }
        int all = gcd(a[0], a[1]);
        for (int i = 2; i < n; i++) {
            all = gcd(a[i], all);
        }

        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < n; i++) {
            a[i] /= all;
        }

        for (int i = 1; i < n; i++) {
            if (gcd(a[i], a[i - 1]) == 1) {
                set.add(i);
                set.add(i - 1);
            }
        }

        List<Integer> primes = getPri();
        int max = 0;
        for (int i = 0; i < primes.size(); i++) {
            int cur = 0;
            for (int j = 0; j < n; j++) {
                if (a[j] % primes.get(i) == 0) {
                    cur++;
                }
            }
            max = Math.max(max, cur);
        }
        pw.print(n - max);

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
