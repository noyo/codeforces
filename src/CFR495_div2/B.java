package CFR495_div2;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.Scanner;

/**
 * Copyright © 2018 Chris. All rights reserved.
 *
 * @author Chris
 * 2018/7/1 22:23
 * @see CFR493.div2
 */
public class B {
//
//    private static class T{
//        int l;
//        int r;
//        int d;
//
//        T(int l, int r, int d) {
//            this.l = l;
//            this.r = r;
//            this.d = d;
//        }
//    }

    private static void dfs(int res[], int cnt[]) {

    }

    private static void solve(Scanner sc) {
        int n = sc.nextInt();
        int m = sc.nextInt();
        int cnt[] = new int[n];

        for (int i = 0; i < m; i++) {
            int l = sc.nextInt();
            int r = sc.nextInt();
            int d = r - l;
            if (d > 1) {
                for (int j = l; j <= r; j++) {
                    cnt[j]++;
                }
            }
        }


    }

    public static void main(String args[]) throws FileNotFoundException {
        System.setIn(new FileInputStream("in.txt"));
        System.setOut(new PrintStream("out.txt"));

        solve(new Scanner(System.in));
    }
}
