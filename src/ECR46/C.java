package ECR46;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.*;

/**
 * Copyright © 2018 Chris. All rights reserved.
 *
 * @author Chris
 * 2018/6/27 23:34
 * @see ECR46
 */
public class C {

    private static void solve(Scanner sc) {
        int n = sc.nextInt();

        Map<Long, Integer> left = new HashMap<>(n);
        Map<Long, Integer> right = new HashMap<>(n);

        long order[] = new long[n << 1];
        for (int i = 0; i < n; i++) {
            long l = sc.nextLong();
            long r = sc.nextLong();
            left.put(l, left.getOrDefault(l, 0) + 1);
            right.put(r, right.getOrDefault(r, 0) + 1);

            order[2 * i] = l;
            order[2 * i + 1] = r;
        }
        Arrays.sort(order);

        long cnt[] = new long[n + 1];
        int cur = left.getOrDefault(order[0], 0);
        long last = order[0];
        for (int i = 0; i < n << 1; i++) {
            if (i == 0) {
                cnt[cur]++;
                cur -= right.getOrDefault(order[0], 0);
            } else {
                long val = order[i];
                if (val == order[i - 1]) {
                    continue;
                }
                cnt[cur] += val - last - 1;
                cur += left.getOrDefault(val, 0);
                cnt[cur]++;
                cur -= right.getOrDefault(val, 0);
                last = val;
            }
        }

        for (int i = 1; i <= n; i++) {
            System.out.print(cnt[i]);
            if (i < n) {
                System.out.print(" ");
            }
        }
    }

    public static void main(String args[]) throws FileNotFoundException {
        System.setIn(new FileInputStream("in.txt"));
        System.setOut(new PrintStream("out.txt"));

        solve(new Scanner(System.in));
    }
}
