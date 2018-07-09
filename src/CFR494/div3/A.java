package CFR494.div3;

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

        int a[] = new int[101];
        for (int i = 0; i < n; i++) {
            a[sc.nextInt()]++;
        }

        int max = 0;
        for (int i = 1; i <= 100; i++) {
            max = Math.max(max, a[i]);
        }
        System.out.println(max);
    }

    public static void main(String args[]) throws FileNotFoundException {
        System.setIn(new FileInputStream("in.txt"));
        System.setOut(new PrintStream("out.txt"));

        solve(new Scanner(System.in));
    }
}
