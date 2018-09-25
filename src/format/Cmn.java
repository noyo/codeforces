package format;

/**
 * Copyright © 2018 Chris. All rights reserved.
 *
 * @author Chris
 * 2018/6/15 22:02
 * @see format
 */
public class Cmn {

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

    public static void main(String args[]) {

        initC(10);
        System.out.println(c(6, 2));
        System.out.println(c(4, 2));
        System.out.println(c(5, 2));

    }
}
