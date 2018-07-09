package CFR493.div2;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.PriorityQueue;
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
        int n = sc.nextInt();
        int B = sc.nextInt();
        int a[] = new int[n];
        PriorityQueue<Integer> queue = new PriorityQueue<>();

        int odd = 0;

        for (int i = 0; i < n; i++) {
            a[i] = sc.nextInt();
        }

        for (int i = 0; i < n - 1; i++) {
            if (a[i] % 2 == 1) {
                odd++;
            }
            if (odd * 2 == (i + 1)) {
                queue.offer(Math.abs(a[i + 1] - a[i]));
            }
        }

        int k = 0;
        while (B > 0 && !queue.isEmpty()) {
            if (B < queue.peek()) {
                break;
            }
            B -= queue.poll();
            k++;
        }
        System.out.println(k);
    }

    public static void main(String args[]) throws FileNotFoundException {
        System.setIn(new FileInputStream("in.txt"));
        System.setOut(new PrintStream("out.txt"));

        solve(new Scanner(System.in));
    }
}
