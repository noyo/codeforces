package ECR46;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.Scanner;

/**
 * Copyright © 2018 Chris. All rights reserved.
 *
 * @author Chris
 * 2018/6/27 23:13
 * @see ECR46
 */
public class B {


    private static void solve(Scanner sc) {
        int n = sc.nextInt();
        int m = sc.nextInt();

        int a[] = new int[n + 1];
        int left[] = new int[n + 2];
        left[0] = 0;

        for (int i = 1; i <= n; i++) {
            a[i] = sc.nextInt();
            if (i == 1) {
                left[i] = a[i];
            } else {
                left[i] = left[i - 2] + a[i] - a[i - 1];
            }
        }
        left[n + 1] = left[n - 1] + m - a[n];

        int on = left[n % 2 == 0 ? n + 1 : n];
        int off = left[n % 2 == 0 ? n : n +1];
        int max = on;

        for (int i = 1; i <= n; i++) {
            if (a[i] - a[i - 1] == 1) {
                continue;
            }
            if (i % 2 == 1) {
                max = Math.max(max, left[i] - 1 + (off - left[i - 1]));
            } else {
                max = Math.max(max, left[i - 1] + (a[i] - a[i - 1] - 1) + (off - left[i]));
            }
        }
        if (n % 2 == 1 && m - a[n] > 1) {
            max = Math.max(max, on + (m - a[n] - 1));
        }
        System.out.println(max);
    }

    public static void main(String args[]) throws FileNotFoundException {
        System.setIn(new FileInputStream("in.txt"));
        System.setOut(new PrintStream("out.txt"));

        solve(new Scanner(System.in));
    }
}
