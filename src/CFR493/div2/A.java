package CFR493.div2;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.Scanner;

/**
 * Copyright © 2018 Chris. All rights reserved.
 *
 * @author Chris
 * 2018/7/1 21:42
 * @see CFR493.div2
 */
public class A {

    private static void solve(Scanner sc) {
        int n = sc.nextInt();
        int a[] = new int[n + 1];
        int sum = 0;

        for (int i = 1; i <= n; i++) {
            a[i] = sc.nextInt();
            sum += a[i];
        }

        if (n == 1) {
            System.out.println(-1);
            return;
        }

        if (n == 2) {
            if (a[1] == a[2]) {
                System.out.println(-1);
            } else {
                System.out.println(1);
                System.out.println(1);
            }
            return;
        }

        int tmp = 0;
        for (int i = 1; i < n; i++) {
            tmp += a[i];
            if (tmp != sum - tmp) {
                System.out.println(i);
                for (int j = 1; j <= i; j++) {
                    System.out.print(j);
                    if (j != i) {
                        System.out.print(" ");
                    }
                }
                return;
            }
        }
        System.out.println(-1);
    }

    public static void main(String args[]) throws FileNotFoundException {
        System.setIn(new FileInputStream("in.txt"));
        System.setOut(new PrintStream("out.txt"));

        solve(new Scanner(System.in));
    }
}
