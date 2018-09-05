package CFR504;

import java.io.*;
import java.util.Stack;

/**
 * Copyright © 2018 Chris. All rights reserved.
 *
 * @author Chris
 * 2018/7/9 15:33
 * @see format
 */
public class C {

    private static BufferedReader br;
    private static StreamTokenizer st;
    private static PrintWriter pw;

    private static void solve() throws IOException {
        int n = nextInt();
        int k = nextInt();
        nextLine();
        String s = nextLine();

        int left[] = new int[n + 2];
        int right[] = new int[n + 2];

        for (int i = 1; i <= n; i++) {
            if (s.charAt(i - 1) == '(') {
                left[i] = left[i - 1] + 1;
            } else {
                left[i] = left[i - 1] - 1;
            }
        }
        for (int i = n; i >= 1; i--) {
            if (s.charAt(i - 1) == '(') {
                right[i] = right[i + 1] + 1;
            } else {
                right[i] = right[i + 1] - 1;
            }
        }

        for (int i = 1; i <= n - k + 1; i++) {
            if (left[i - 1] == 0 && right[i + k] == 0) {
                pw.print(s.substring(i - 1, i + k - 1));
                return;
            }
        }

        Stack<Integer> stack = new Stack<>();
        int cur = 1;
        while (cur <= n) {
            if (stack.isEmpty()) {
                stack.push(left[cur]);
            } else {
                if (left[cur] > left[stack.peek()]) {
                    stack.push(cur);
                } else {
                    int pre = stack.pop();
                    if (cur - pre == k - 1) {
                        pw.print(s.substring(cur - k, cur));
                        return;
                    }
                    if (!stack.isEmpty() && cur - stack.peek() >= k && cur - pre < k - 1) {
                        if (left[pre - 1] == left[stack.peek()] && left[cur - k] == left[pre - 1] && left[cur - k] + right[cur + 1] == 0) {
                            pw.print(s.substring(cur - k, cur));
                            return;
                        }
                    }
                }
            }
            cur++;
        }
    }

    public static void main(String args[]) throws IOException {
        boolean oj = System.getProperty("ONLINE_JUDGE") != null;
        if (!oj) {
            System.setIn(new FileInputStream("in.txt"));
//            System.setOut(new PrintStream("out.txt"));
        }
        br = new BufferedReader(new InputStreamReader(System.in));
        st = new StreamTokenizer(br);
        pw = new PrintWriter(new OutputStreamWriter(System.out));
        st.ordinaryChar('\''); //指定单引号、双引号和注释符号是普通字符
        st.ordinaryChar('\"');
        st.ordinaryChar('/');

        long t = System.currentTimeMillis();
        solve();
        if (!oj) {
            pw.println("[" + (System.currentTimeMillis() - t) + "ms]");
        }
        pw.flush();
    }

    private static int nextInt() throws IOException {
        st.nextToken();
        return (int) st.nval;
    }

    private static long nextLong() throws IOException {
        st.nextToken();
        return (long) st.nval;
    }

    private static double nextDouble() throws IOException {
        st.nextToken();
        return st.nval;
    }

    private static String[] nextSS(String reg) throws IOException {
        return br.readLine().split(reg);
    }

    private static String nextLine() throws IOException {
        return br.readLine();
    }
}
