package CFR516_d2;

import java.io.*;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

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

    static final int INF = 1000000007;
    static final int MOD = 1000000007;
    static int R;
    static int C;
    static char[][] grid;

    static int dir[] = new int[]{0, -1, 0, 1, 0};

    static boolean legal(int r, int c) {
        return r > 0 && r <= R && c > 0 && c <= C;
    }

    static boolean VX[][];
    static boolean VY[][];

    private static void solve() throws IOException {
        R = nextInt();
        C = nextInt();
        int sr = nextInt();
        int sc = nextInt();
        int x = nextInt();
        int y = nextInt();
        nextLine();
        grid = new char[R + 1][C + 1];
        for (int i = 1; i <= R; i++) {
            char[] ch = nextLine().toCharArray();
            for (int j = 0; j < C; j++) {
                grid[i][j + 1] = ch[j];
            }
        }

        VX = new boolean[R + 1][C + 1];
        VY = new boolean[R + 1][C + 1];
        VX[sr][sc] = true;
        VY[sr][sc] = true;
        int cntX[][] = new int[R + 1][C + 1];
        int cntY[][] = new int[R + 1][C + 1];
        cntX[sr][sc] = x;
        cntY[sr][sc] = y;
//        bfsX(cntX, sr, sc, x);
//        bfsY(cntY, sr, sc, y);

        Queue<int[]> queue = new LinkedList<>();
        if (y < R * C) {
            queue.offer(new int[]{sr, sc, y});
            while (!queue.isEmpty()) {
                int k = queue.size();
                while (k-- > 0) {
                    int[] tmp = queue.poll();
                    for (int i = 0; i < 4; i++) {
                        if (i == 2 && tmp[2] <= 0) {
                            continue;
                        }
                        int r = tmp[0] + dir[i];
                        int c = tmp[1] + dir[i + 1];
                        if (!legal(r, c) || grid[r][c] == '*') {
                            continue;
                        }
                        if (VY[r][c] && cntY[r][c] >= tmp[2]) {
                            continue;
                        }
                        VY[r][c] = true;
                        if (i == 2) {
                            cntY[r][c] = tmp[2] - 1;
                            queue.offer(new int[]{r, c, tmp[2] - 1});
                        } else {
                            cntY[r][c] = tmp[2];
                            queue.offer(new int[]{r, c, tmp[2]});
                        }
                    }
                }
            }
        }

        if (x < R * C) {
            queue.clear();
            queue.offer(new int[]{sr, sc, x});
            while (!queue.isEmpty()) {
                int k = queue.size();
                while (k-- > 0) {
                    int[] tmp = queue.poll();
                    for (int i = 0; i < 4; i++) {
                        if (i == 0 && tmp[2] <= 0) {
                            continue;
                        }
                        int r = tmp[0] + dir[i];
                        int c = tmp[1] + dir[i + 1];
                        if (!legal(r, c) || grid[r][c] == '*') {
                            continue;
                        }
                        if (VX[r][c] && cntX[r][c] >= tmp[2]) {
                            continue;
                        }
                        VX[r][c] = true;
                        if (i == 0) {
                            cntX[r][c] = tmp[2] - 1;
                            queue.offer(new int[]{r, c, tmp[2] - 1});
                        } else {
                            cntX[r][c] = tmp[2];
                            queue.offer(new int[]{r, c, tmp[2]});
                        }
                    }
                }
            }
        }

        int ans = 0;
        for (int i = 1; i <= R; i++) {
            for (int j = 1; j <= C; j++) {
                if (grid[i][j] == '*') {
                    continue;
                }
                if ((x >= R * C || VX[i][j]) && (VY[i][j] || y >= R * C)) {
                    ans++;
                }
            }
        }
        pw.print(ans);
    }


    private static void bfsY(int cnt[][], int sr, int sc, int cur) {
        for (int i = 0; i < 4; i++) {
            if (i == 2 && cur <= 0) {
                continue;
            }
            int r = sr + dir[i];
            int c = sc + dir[i + 1];
            if (!legal(r, c) || grid[r][c] == '*') {
                continue;
            }
            if (VY[r][c] && cnt[r][c] >= cur) {
                continue;
            }
            VY[r][c] = true;
            if (i == 2) {
                cnt[r][c] = cur - 1;
                bfsY(cnt, r, c, cur - 1);
            } else {
                cnt[r][c] = cur;
                bfsY(cnt, r, c, cur);
            }
        }
    }

    private static void bfsX(int cnt[][], int sr, int sc, int cur) {
        for (int i = 0; i < 4; i++) {
            if (i == 0 && cur <= 0) {
                continue;
            }
            int r = sr + dir[i];
            int c = sc + dir[i + 1];
            if (!legal(r, c) || grid[r][c] == '*') {
                continue;
            }
            if (VX[r][c] && cnt[r][c] >= cur) {
                continue;
            }
            VX[r][c] = true;
            if (i == 0) {
                cnt[r][c] = cur - 1;
                bfsX(cnt, r, c, cur - 1);
            } else {
                cnt[r][c] = cur;
                bfsX(cnt, r, c, cur);
            }
        }
    }

    static void getDiv(Map<Integer, Integer> map, int n) {
        int sqrt = (int) Math.sqrt(n);
        for (int i = sqrt; i >= 2; i--) {
            if (n % i == 0) {
                getDiv(map, i);
                getDiv(map, n / i);
                return;
            }
        }
        map.put(n, map.getOrDefault(n, 0) + 1);
    }

    public static boolean[] generatePrime(int n) {
        boolean p[] = new boolean[n + 1];
        p[2] = true;

        for (int i = 3; i <= n; i += 2) {
            p[i] = true;
        }

        for (int i = 3; i <= Math.sqrt(n); i += 2) {
            if (!p[i]) {
                continue;
            }
            for (int j = i * i; j <= n; j += i << 1) {
                p[j] = false;
            }
        }
        return p;
    }

    // 18位素数：154590409516822759
    // 19位素数：2305843009213693951 (梅森素数)
    // 19位素数：4384957924686954497
    static boolean isPrime(long n) { //determines if n is a prime number
        int p[] = {2, 3, 5, 233, 331};
        int pn = p.length;
        long s = 0, t = n - 1;//n - 1 = 2^s * t
        while ((t & 1) == 0) {
            t >>= 1;
            ++s;
        }
        for (int i = 0; i < pn; ++i) {
            if (n == p[i]) {
                return true;
            }
            long pt = pow(p[i], t, n);
            for (int j = 0; j < s; ++j) {
                long cur = llMod(pt, pt, n);
                if (cur == 1 && pt != 1 && pt != n - 1) {
                    return false;
                }
                pt = cur;
            }
            if (pt != 1) {
                return false;
            }
        }
        return true;
    }

    static long llMod(long a, long b, long mod) {
        return (a * b - (long) ((double) a / mod * b + 0.5) * mod + mod) % mod;
//        long r = 0;
//        a %= mod;
//        b %= mod;
//        while (b > 0) {
//            if ((b & 1) == 1) {
//                r = (r + a) % mod;
//            }
//            b >>= 1;
//            a = (a << 1) % mod;
//        }
//        return r;
    }

    static int pow(long a, long n) {
        long ans = 1;
        while (n > 0) {
            if ((n & 1) == 1) {
                ans = (ans * a) % MOD;
            }
            a = (a * a) % MOD;
            n >>= 1;
        }
        return (int) ans;
    }

    static int pow(long a, long n, long mod) {
        long ans = 1;
        while (n > 0) {
            if ((n & 1) == 1) {
                ans = llMod(ans, a, mod);
            }
            a = llMod(a, a, mod);
            n >>= 1;
        }
        return (int) ans;
    }

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
    private static int c(long n, long m) {
        if (m > n) {
            n ^= m;
            m ^= n;
            n ^= m;
        }
        m = Math.min(m, n - m);

        long top = 1;
        long bot = 1;
        for (long i = n - m + 1; i <= n; i++) {
            top = (top * i) % MOD;
        }
        for (int i = 1; i <= m; i++) {
            bot = (bot * i) % MOD;
        }

        return (int) ((top * pow(bot, MOD - 2)) % MOD);
    }

    static int gcd(int a, int b) {
        if (a < b) {
            a ^= b;
            b ^= a;
            a ^= b;
        }
        while (b != 0) {
            int tmp = a % b;
            a = b;
            b = tmp;
        }
        return a;
    }

    static boolean even(long n) {
        return (n & 1) == 0;
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

    private static long[] anLong(int n) throws IOException {
        long a[] = new long[n];
        for (int i = 0; i < n; i++) {
            a[i] = nextInt();
        }
        return a;
    }

    private static String next(int len) throws IOException {
        char ch[] = new char[len];
        int cur = 0;
        char c;
        while ((c = (char) br.read()) == '\n' || c == '\r' || c == ' ' || c == '\t') ;
        do {
            ch[cur++] = c;
        } while (!((c = (char) br.read()) == '\n' || c == '\r' || c == ' ' || c == '\t'));
        return String.valueOf(ch, 0, cur);
    }

    private static int nextInt() throws IOException {
        st.nextToken();
        return (int) st.nval;
    }

    private static long nextLong() throws IOException {
        return Long.parseLong(nextLine());
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
