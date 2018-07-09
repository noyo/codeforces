package CFR493.div2;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

/**
 * Copyright © 2018 Chris. All rights reserved.
 *
 * @author Chris
 * 2018/7/1 22:48
 * @see CFR493.div2
 */
public class D {

    static List<Integer> list = new ArrayList<>();
    private static long c(int n, int m) {
        int min = Math.min(m, n - m);
        long c[] = new long[min + 1];
        c[0] = 1;
        for (int i = 1; i <= min; i++) {
            c[i] = c[i - 1] * (n - i + 1) / i;
        }
        return c[min];
    }

    private static void dfs(int i, int num, int a[], int cnt) {
        if (i == a.length - 1) {
            System.out.print(cnt + " ");
            System.out.println("  "+ (num + cnt * a[a.length - 1]));
            list.add(num + cnt * a[a.length - 1]);
            return;
        }
        int tmp = 0;
        while (tmp <= cnt) {
            System.out.print(tmp + " ");
            dfs(i + 1, num + a[i] * tmp, a, cnt - tmp);
            tmp++;
        }
    }

    private static void solve(Scanner sc) {
        int n = sc.nextInt();
        long C = c(n + 4 - 1, n);

        dfs(0, 0, new int[] {1, 5, 10, 50}, n);
        list.sort(Comparator.naturalOrder());
        for (int i = 1; i < list.size(); i++) {
            if (list.get(i) == list.get(i - 1)) {
                C--;
            }
        }
        System.out.println(C);
//        System.out.println(C - n / 10 * 42);
    }

    public static void main(String args[]) throws FileNotFoundException {
        System.setIn(new FileInputStream("in.txt"));
        System.setOut(new PrintStream("out.txt"));

        solve(new Scanner(System.in));
    }
}
