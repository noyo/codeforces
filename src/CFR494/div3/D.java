package CFR494.div3;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.*;

/**
 * Copyright © 2018 Chris. All rights reserved.
 *
 * @author Chris
 * 2018/7/1 22:48
 * @see CFR493.div2
 */
public class D {

    private static void solve(Scanner sc) {
        int n = sc.nextInt();
        int q = sc.nextInt();
        int cnt2[] = new int[32];

        for (int i = 0; i < n; i++) {
            int a = sc.nextInt();
            int b = 0;
            while ((a >> 1) > 0) {
                b++;
                a >>= 1;
            }
            cnt2[b]++;
        }

        while (q-- > 0) {
            int b = sc.nextInt();

            int min = 0;

            int tmp[] = new int[32];
            int cnt[] = Arrays.copyOf(cnt2, 32);
            if (b % 2 == 1) {
                if (cnt[0] <= 0) {
                    System.out.println(-1);
                    continue;
                }
                cnt[0]--;
                b--;
                min++;
            }
            int cur = 0;
            while (b > 0) {
                if (b % 2 == 1) {
                    tmp[cur] = 1;
                }
                cur++;
                b >>= 1;
            }

            for (int i = 1; i < cur; i++) {
                if (tmp[i] == 0) {
                    continue;
                }
                if (cnt[i] > 0) {
                    cnt[i]--;
                    min++;
                } else {
                    int k = 2;
                    for (int j = i - 1; j >= 0; j--) {
                        if (cnt[j] >= k) {
                            cnt[j] -= k;
                            min += k;
                            k = 0;
                            break;
                        } else if (cnt[j] > 0){
                            k -= cnt[j];
                            min += cnt[j];
                            cnt[j] = 0;
                        }
                        k <<= 1;
                    }
                    if (k != 0) {
                        min = -1;
                        break;
                    }
                }
            }

            System.out.println(min == 0 ? -1 : min);
        }
    }

    public static void main(String args[]) throws FileNotFoundException {
        System.setIn(new FileInputStream("in.txt"));
        System.setOut(new PrintStream("out.txt"));

        solve(new Scanner(System.in));
    }
}
