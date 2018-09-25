package ECR51_d2;

import java.io.*;

/**
 * Copyright © 2018 Chris. All rights reserved.
 *
 * @author Chris
 * 2018/7/9 15:33
 * @see format
 */
public class A {

    private static BufferedReader br;
    private static StreamTokenizer st;
    private static PrintWriter pw;

    private static void solve() throws IOException {
          int T = Integer.parseInt(nextLine());
          while (T-- > 0) {

              char ch[] = nextLine().toCharArray();
              int n = ch.length;
              int low = 0;
              int up = 0;
              int dig = 0;

              for (char c : ch) {
                  if (Character.isUpperCase(c)) {
                      up++;
                  } else if (Character.isLowerCase(c)) {
                      low++;
                  } else if (Character.isDigit(c)){
                      dig++;
                  }
              }

              if (up == 0 || low == 0 || dig == 0) {
                  if (up > 0 && low > 0) {
                      if (up > 1) {
                          for (int i = 0; i < n; i++) {
                              if (Character.isUpperCase(ch[i])) {
                                  ch[i] = '1';
                                  break;
                              }
                          }
                      } else if (low > 1) {
                          for (int i = 0; i < n; i++) {
                              if (Character.isLowerCase(ch[i])) {
                                  ch[i] = '1';
                                  break;
                              }
                          }
                      }
                  } else if (up > 0 && dig > 0) {
                      if (up > 1) {
                          for (int i = 0; i < n; i++) {
                              if (Character.isUpperCase(ch[i])) {
                                  ch[i] = 'a';
                                  break;
                              }
                          }
                      } else if (dig > 1) {
                          for (int i = 0; i < n; i++) {
                              if (Character.isDigit(ch[i])) {
                                  ch[i] = 'a';
                                  break;
                              }
                          }
                      }
                  } else if (low > 0 && dig > 0) {
                      if (low > 1) {
                          for (int i = 0; i < n; i++) {
                              if (Character.isLowerCase(ch[i])) {
                                  ch[i] = 'A';
                                  break;
                              }
                          }
                      } else if (dig > 1) {
                          for (int i = 0; i < n; i++) {
                              if (Character.isDigit(ch[i])) {
                                  ch[i] = 'A';
                                  break;
                              }
                          }
                      }
                  } else if (up > 0) {
                      ch[0] = 'a';
                      ch[1] = '1';
                  } else if (low > 0) {
                      ch[0] = 'A';
                      ch[1] = '1';
                  } else if (dig > 0) {
                      ch[0] = 'A';
                      ch[1] = 'a';
                  }
              }

              pw.print(String.valueOf(ch));
              if (T > 0) {
                  pw.println();
              }
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
