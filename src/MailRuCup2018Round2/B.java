package MailRuCup2018Round2;

import java.io.IOException;
import java.util.Scanner;

/**
 * Copyright © 2018 Chris. All rights reserved.
 *
 * @author Chris
 * 2018/7/9 15:33
 * @see format
 */
public class B {

    static Scanner sc;

    private static void solve() throws IOException {
        int n = nextInt();
        int m = nextInt();
        long l = nextInt();
        long a[] = new long[n + 1];
        boolean large[] = new boolean[n + 1];
        int cnt = 0;
        for (int i = 1; i <= n; i++) {
            a[i] = nextInt();
            if (a[i] > l) {
                large[i] = true;
                if (!large[i - 1]) {
                    cnt++;
                }
            }
        }
        boolean flag = false;
        while (m-- > 0) {
            int op = nextInt();
            if (op == 0) {
                if (flag) {
                    System.out.println();
                }
                System.out.print(cnt);
                flag = true;
            } else {
                int p = nextInt();
                if (large[p]) {
                    sc.nextLine();
                    continue;
                }
                a[p] += nextInt();
                if (a[p] > l) {
                    large[p] = true;
                    if (n == 1) {
                        cnt++;

                        sc.nextLine();
                        continue;
                    }
                    if (p > 1 && p < n) {
                        if (!large[p - 1] && !large[p + 1]) {
                            cnt++;
                        } else if (large[p - 1] && large[p + 1]) {
                            cnt--;
                        }
                    } else if (p == 1 && !large[p + 1] || p == n && !large[p - 1]) {
                        cnt++;
                    }
                }
            }
            sc.nextLine();
        }
    }

    public static void main(String args[]) throws IOException {
        sc = new Scanner(System.in);

        solve();
    }

    private static int nextInt() throws IOException {
        return sc.nextInt();
    }
}
