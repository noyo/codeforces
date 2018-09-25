package ECR50_d2;

import java.io.*;
import java.math.BigInteger;

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

    private static void solve() throws IOException {
        int q = nextInt();
        BigInteger L0 = new BigInteger("0");
        BigInteger L1 = new BigInteger("1");
        BigInteger L2 = new BigInteger("2");
        nextLine();
        while (q-- > 0) {

            String ss[] = nextLine().split(" ");
            BigInteger r = new BigInteger(ss[0]);
            BigInteger c = new BigInteger(ss[1]);
            BigInteger k = new BigInteger(ss[2]);

            if (k.compareTo(r) < 0 || k.compareTo(c) < 0) {
                pw.print(-1);
            } else {
                if (r.compareTo(c) == 0) {
                    BigInteger LL = k.subtract(r);
                    if (LL.mod(L2).compareTo(L0) == 0) {
                        pw.print(k);
                    } else {
                        pw.print(k.subtract(L2));
                    }
                } else {
                    BigInteger min;
                    BigInteger max;
                    if (r.compareTo(c) > 0) {
                        max = r;
                        min = c;
                    } else {
                        max = c;
                        min = r;
                    }
                    BigInteger LL = (max.subtract(min));
                    if (LL.mod(L2).compareTo(L0) == 0) {
                        if ((k.subtract(max)).mod(L2).compareTo(L0) == 0) {
                            pw.print(k);
                        } else {
                            pw.print(k.subtract(L2));
                        }
                    } else {
                        pw.print(k.subtract(L1));
                    }

                }
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
