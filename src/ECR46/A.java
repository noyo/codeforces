package ECR46;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.*;

/**
 * Copyright © 2018 Chris. All rights reserved.
 *
 * @author Chris
 * 2018/6/27 22:35
 * @see ECR46
 */
public class A {

    private static void solve(Scanner sc) {
        int n = sc.nextInt();

        int cnt[] = new int[128 << 2];
        for (int i = 0; i < n << 1; i++) {
            char ch[] = sc.next().toCharArray();
            for (int j = 0; j < ch.length; j++) {
                cnt[ch[j] * ch.length] += i < n ? 1 : -1;
            }
        }

        int sum = 0;
        for (int i = 0; i < 128 << 2; i++) {
            sum += Math.abs(cnt[i]);
        }
        System.out.println(sum / 2);

        /*List<String> last = new ArrayList<>();
        List<String> now = new ArrayList<>();
        int min = 0;

        for (int i = 0; i < n; i++) {
            last.add(sc.next());
            min += last.get(i).length();
        }
        for (int i = 0; i < n; i++) {
            now.add(sc.next());
        }
        last.sort((o1, o2) -> {
            if (o1.length() != o2.length()) {
                return o1.length() - o2.length();
            }
            return o1.compareTo(o2);
        });
        now.sort((o1, o2) -> {
            if (o1.length() != o2.length()) {
                return o1.length() - o2.length();
            }
            return o1.compareTo(o2);
        });

        int cur = 0;
        boolean vst[] = new boolean[n];
        for (int i = 0; i < n; i++) {
            String s = last.get(i);
            int len = s.length();
            cur = 0;
            while (cur < n && (now.get(cur).length() < len || vst[cur])) {
                cur++;
            }
            int max = 0;
            int index = -1;
            while (cur < n && now.get(cur).length() == len) {
                if (vst[cur]) {
                    cur++;
                    continue;
                }
                int tmp = 0;
                for (int j = 0; j < len; j++) {
                    if (s.charAt(j) == now.get(cur).charAt(j)) {
                        tmp++;
                    }
                }
                if (tmp > max) {
                    max = tmp;
                    index = cur;
                }
                cur++;
            }
            if (index >= 0) {
                vst[index] = true;
                min -= max;
            }
        }
        System.out.println(min);*/
    }

    public static void main(String args[]) throws FileNotFoundException {
        System.setIn(new FileInputStream("in.txt"));
        System.setOut(new PrintStream("out.txt"));

        solve(new Scanner(System.in));
    }
}
