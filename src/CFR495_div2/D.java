package CFR495_div2;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Arrays;
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

    private static void solve(Scanner sc) {
        int t = sc.nextInt();
        int a[] = new int[t];
        int cnt[] = new int[t + 1];

        for (int i = 0; i < t; i++) {
            a[i] = sc.nextInt();
            cnt[a[i]]++;
        }

        Arrays.sort(a);

        if (cnt[0] != 0 || cnt[a[t - 1]] > 4) {
            System.out.println(-1);
            return;
        }

        int next = 0;
        boolean flag = true;
        for (int i = 1; i <= a[t - 1]; i += cnt[a[i]]) {
            int cur = a[i];
            if (cnt[cur] == cur * 4) {
                continue;
            } else if (cnt[cur] > cur * 4) {
                flag = false;
            }
            next = cur;
            int tmp = (cur - 1) * 2;
            for (int k = cur; flag && k <= tmp; k++) {
                cnt[k] -= 4 * (tmp + 1 - k);
                if (cnt[k] < 0) {
                    flag = false;
                }
            }
        }

        int n = next * 2 - 1;
        int m = n;
        int r = next;
        int c = next;
        for (int k = next; flag && k <= a[t - 1] + 1; k++) {
            switch (cnt[k]) {
                case 0:
                    for (int i = k; flag && i <= a[t - 1]; i++) {
                        if (cnt[i] != 0) {
                            flag = false;
                        }
                    }
                    if (flag) {
                        System.out.println(n + " " + m);
                        System.out.println(r + " " + c);
                    }
                    break;
                case 1:
                    cnt[k]--;
                    for (int i = k + 1; i <= r + c + 1; i++) {
                        cnt[i] -= 2;
                    }
                    cnt[k + 1] = 0;
                    break;
                case 2:
                    break;
                case 3:
                    break;
            }
        }

        if (!flag) {
            System.out.println(-1);
        }
    }

    public static void main(String args[]) throws FileNotFoundException {
        System.setIn(new FileInputStream("in.txt"));
        System.setOut(new PrintStream("out.txt"));

        solve(new Scanner(System.in));
    }
}
