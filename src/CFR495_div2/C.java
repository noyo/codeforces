package CFR495_div2;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.*;

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
        int a[] = new int[n];

        Map<Integer, Integer> cnt = new HashMap<>();
        for (int i = 0; i < n; i++) {
            a[i] = sc.nextInt();
            cnt.put(a[i], cnt.getOrDefault(a[i], 0) + 1);
        }

        long sum = 0;
        Set<Integer> vst = new HashSet<>();
        int size = 0;

        for (int i = 0; i < n - 1; i++) {
            cnt.put(a[i], Math.max(0, cnt.get(a[i]) - 1));
            if (cnt.get(a[i]) == 0) {
                size++;
            }
            if (vst.contains(a[i])) {
                continue;
            }
            vst.add(a[i]);

            sum += cnt.size() - size;
        }
        System.out.println(sum);
    }

    public static void main(String args[]) throws FileNotFoundException {
        System.setIn(new FileInputStream("in.txt"));
        System.setOut(new PrintStream("out.txt"));

        solve(new Scanner(System.in));
    }
}
