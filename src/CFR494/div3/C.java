package CFR494.div3;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.Scanner;

/**
 * Copyright © 2018 Chris. All rights reserved.
 *
 * @author Chris
 * 2018/7/1 22:34
 * @see CFR493.div2
 */
public class C {

    private static double count(double sum[], int x, int y) {
        return (sum[y + 1] - sum[x]) / (y - x + 1);
    }

    private static void solve(Scanner sc) {
        int n = sc.nextInt();
        int k = sc.nextInt();
        double a[] = new double[n];
        double sum[] = new double[n + 1];

        for (int i = 0; i < n; i++) {
            a[i] = sc.nextDouble();
            sum[i + 1] = sum[i] + a[i];
        }


        double max = 0;
        for (int i = 0; i < n; i++) {
            for (int j = i + k - 1; j < n; j++) {
                max = Math.max(max, count(sum, i, j));
            }
        }

        System.out.println(max);
    }

    public static void main(String args[]) throws FileNotFoundException {
        System.setIn(new FileInputStream("in.txt"));
        System.setOut(new PrintStream("out.txt"));

        solve(new Scanner(System.in));
    }
}
