package CFR495_div2;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.Arrays;
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
        int d = sc.nextInt();
        int x[] = new int[n];

        for (int i = 0; i < n; i++) {
            x[i] = sc.nextInt();
        }

        Arrays.sort(x);

        int sum = 2;
        for (int i = 1; i < n; i++) {
            int sub = x[i] - x[i - 1];
            if (sub >= d * 2) {
                sum += Math.min(2, sub - (d * 2 - 1));
            }
        }
        System.out.println(sum);
    }

    public static void main(String args[]) throws FileNotFoundException {
        System.setIn(new FileInputStream("in.txt"));
        System.setOut(new PrintStream("out.txt"));

        solve(new Scanner(System.in));
    }
}
