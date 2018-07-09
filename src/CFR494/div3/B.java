package CFR494.div3;

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

    private static void solve(Scanner sc) {
        int a = sc.nextInt();
        int b = sc.nextInt();
        int x = sc.nextInt();

        int n = a + b;

        int ans[] = new int[n];

        if (b >= a) {
            for (int i = 0; i < b; i++) {
                ans[i] = 1;
            }
            x--;
            int k = 1;
            if (x % 2 == 1) {
                ans[b - 1] = 0;
                ans[n - k] = 1;
                k++;
                x--;
                b--;
            }

            k++;
            while (x > 0) {
                ans[b - 1] = 0;
                ans[n - k] = 1;
                k += 2;
                x -= 2;
                b -= 1;
            }
        } else {
            for (int i = a; i < n; i++) {
                ans[i] = 1;
            }
            x--;
            int k = 1;
            if (x % 2 == 1) {
                ans[a - 1] = 1;
                ans[n - k] = 0;
                k++;
                x--;
                a--;
            }

            k++;
            while (x > 0) {
                ans[a - 1] = 1;
                ans[n - k] = 0;
                k += 2;
                x -= 2;
                a -= 1;
            }
        }

        for (int i = 0; i < n; i++) {
            System.out.print(ans[i]);
        }
    }

    public static void main(String args[]) throws FileNotFoundException {
        System.setIn(new FileInputStream("in.txt"));
        System.setOut(new PrintStream("out.txt"));

        solve(new Scanner(System.in));
    }
}
