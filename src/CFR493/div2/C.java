package CFR493.div2;

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

    private static void solve(Scanner sc) {
        int n = sc.nextInt();
        long x = sc.nextLong();
        long y = sc.nextLong();
        StringBuilder sb = new StringBuilder(sc.next());

        long cnt = 0;
        for (int i = 0; i < n;) {
            while (i < n && sb.charAt(i) == '1') {
                i++;
            }
            if (i < n) {
                cnt++;
            }
            while (i < n && sb.charAt(i) == '0') {
                i++;
            }
        }

        if (cnt == 0) {
            System.out.println(0);
            return;
        }

        if (cnt == 1) {
            System.out.println(y);
            return;
        }

        if (x >= y) {
            System.out.println(y * cnt);
            return;
        }

        System.out.println((cnt - 1) * x + y);
    }

    public static void main(String args[]) throws FileNotFoundException {
        System.setIn(new FileInputStream("in.txt"));
        System.setOut(new PrintStream("out.txt"));

        solve(new Scanner(System.in));
    }
}
