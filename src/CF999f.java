import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * Copyright © 2018 Chris. All rights reserved.
 *
 * @author Chris
 * 2018/6/25 20:48
 * @see
 */
public class CF999f {

    private static void solve(Scanner sc) {
        int n = sc.nextInt();
        int k = sc.nextInt();
        int m = n * k;
        Map<Integer, Integer> card = new HashMap<>();
        Map<Integer, Integer> f = new HashMap<>();
        int h[] = new int[k];

        for (int i = 0; i < m; i++) {
            int c = sc.nextInt();
            card.put(c, card.getOrDefault(c, 0) + 1);
        }
        for (int i = 0; i < n; i++) {
            int val = sc.nextInt();
            f.put(val, f.getOrDefault(val, 0) + 1);
        }
        for (int i = 0; i < k; i++) {
            h[i] = sc.nextInt();
        }

        int sum = 0;
        for (int key : f.keySet()) {
            sum += cnt(f.get(key), card.getOrDefault(key, 0), h);
        }
        System.out.println(sum);
    }

    private static int cnt(int n, int k, int h[]) {
        int contains = Math.min(n * h.length, k);
        int dp[] = new int[contains + 1];

        for (int m = n; m >= 1; m--) {
            for (int j = contains; j >= 0; j--) {
                for (int i = 1; i <= h.length; i++) {
                    if (j >= i) {
                        dp[j] = Math.max(dp[j], dp[j - i] + h[i - 1]);
                    }
                }
            }
        }

        return dp[contains];
    }

    public static void main(String args[]) throws FileNotFoundException {
        System.setIn(new FileInputStream("in.txt"));
        System.setOut(new PrintStream("out.txt"));

        Scanner sc = new Scanner(System.in);

        solve(sc);
    }
}
