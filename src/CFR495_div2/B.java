package CFR495_div2;

import java.io.*;
import java.util.Scanner;

/**
 * Copyright © 2018 Chris. All rights reserved.
 *
 * @author Chris
 * 2018/7/1 22:23
 * @see CFR493.div2
 */
public class B {

    private static void solve(StreamTokenizer st, PrintWriter pw) throws IOException {
        st.nextToken();
        int n = (int) st.nval;
        for (int i = 0; i < n; i++) {
            pw.print(i % 2);
        }
    }

    public static void main(String args[]) throws IOException {
        boolean oj = System.getProperty("ONLINE_JUDGE") != null;
        if (!oj) {
            System.setIn(new FileInputStream("src/in.txt"));
        }
        StreamTokenizer st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
        PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out));

        long t = System.currentTimeMillis();
        solve(st, pw);
        if (!oj) {
            pw.println("[" + (System.currentTimeMillis() - t) + "ms]");
        }
        pw.flush();
    }
}
