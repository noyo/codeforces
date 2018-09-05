package AIMTR_d1_d2;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

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

    static class Point {
        int x;
        int y;

        Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    private static void solve() throws IOException {
        int n = nextInt();

        Map<Integer, Map<Integer, Point>> map = new HashMap<>();
        Map<Integer, Map<Integer, Point>> map2 = new HashMap<>();

        PriorityQueue<Integer> left = new PriorityQueue<>();
        PriorityQueue<Integer> right = new PriorityQueue<>();
        PriorityQueue<Integer> bottom = new PriorityQueue<>();
        PriorityQueue<Integer> top = new PriorityQueue<>();

        Map<Integer, Integer> LX = new HashMap<>();
        Map<Integer, Integer> RX = new HashMap<>();
        Map<Integer, Integer> BY = new HashMap<>();
        Map<Integer, Integer> TY = new HashMap<>();
        for (int i = 0; i < n; i++) {
            int x1 = nextInt();
            int y1 = nextInt();
            int x2 = nextInt();
            int y2 = nextInt();
            if (!map.containsKey(x1)) {
                map.put(x1, new HashMap<>());
            }
            if (!map.get(x1).containsKey(y1)) {
                map.get(x1).put(y1, new Point(x2, y2));
            } else {
                Point p = map.get(x1).get(y1);
                p.x = Math.min(p.x, x2);
                p.y = Math.min(p.y, y2);
                map.get(x1).put(y1, p);
            }

            if (!map2.containsKey(x2)) {
                map2.put(x2, new HashMap<>());
            }
            if (!map2.get(x2).containsKey(y2)) {
                map2.get(x2).put(y2, new Point(x1, y1));
            } else {
                Point p = map2.get(x2).get(y2);
                p.x = Math.max(p.x, x1);
                p.y = Math.max(p.y, y1);
                map2.get(x2).put(y2, p);
            }

            left.offer(-x1);
            if (!LX.containsKey(x1)) {
                LX.put(x1, y1);
            } else {
                LX.put(x1, Math.max(LX.get(x1), y1));
            }

            right.offer(x2);
            if (!RX.containsKey(x2)) {
                RX.put(x2, y2);
            } else {
                RX.put(x2, Math.min(RX.get(x2), y2));
            }

            bottom.offer(-y1);
            if (!BY.containsKey(y1)) {
                BY.put(y1, x1);
            } else {
                BY.put(y1, Math.max(BY.get(y1), x1));
            }

            top.offer(y2);
            if (!TY.containsKey(y2)) {
                TY.put(y2, x2);
            } else {
                TY.put(y2, Math.min(TY.get(y2), x2));
            }
        }

        if (left.peek() + right.peek() >= 0 && bottom.peek() + top.peek() >= 0) {
            pw.print(-left.peek() + " " + -bottom.peek());
        } else {
            int leftPoll = left.poll();
            int botPoll = -LX.get(-leftPoll);
            bottom.remove(botPoll);
            int rightPoll = map.get(-leftPoll).get(-botPoll).x;
            int topPoll = map.get(-leftPoll).get(-botPoll).y;
            right.remove(rightPoll);
            top.remove(topPoll);
            if (left.peek() + right.peek() >= 0 && bottom.peek() + top.peek() >= 0) {
                pw.print(-left.peek() + " " + -bottom.peek());
            } else {
                left.offer(leftPoll);
                right.offer(rightPoll);
                bottom.offer(botPoll);
                top.offer(topPoll);

                botPoll = bottom.poll();
                leftPoll = -BY.get(-botPoll);
                left.remove(leftPoll);
                rightPoll = map.get(-leftPoll).get(-botPoll).x;
                topPoll = map.get(-leftPoll).get(-botPoll).y;
                right.remove(rightPoll);
                top.remove(topPoll);
                if (left.peek() + right.peek() >= 0 && bottom.peek() + top.peek() >= 0) {
                    pw.print(-left.peek() + " " + -bottom.peek());
                } else {
                    left.offer(leftPoll);
                    right.offer(rightPoll);
                    bottom.offer(botPoll);
                    top.offer(topPoll);

                    rightPoll = right.poll();
                    topPoll = RX.get(rightPoll);
                    top.remove(topPoll);
                    Point p = map2.get(rightPoll).get(topPoll);
                    leftPoll = -p.x;
                    botPoll = -p.y;
                    left.remove(leftPoll);
                    bottom.remove(botPoll);
                    if (left.peek() + right.peek() >= 0 && bottom.peek() + top.peek() >= 0) {
                        pw.print(-left.peek() + " " + -bottom.peek());
                    } else {
                        left.offer(leftPoll);
                        right.offer(rightPoll);
                        bottom.offer(botPoll);
                        top.offer(topPoll);

                        topPoll = top.poll();
                        rightPoll = TY.get(rightPoll);
                        right.remove(rightPoll);
                        p = map2.get(rightPoll).get(topPoll);
                        leftPoll = -p.x;
                        botPoll = -p.y;
                        left.remove(leftPoll);
                        bottom.remove(botPoll);
                        if (left.peek() + right.peek() >= 0 && bottom.peek() + top.peek() >= 0) {
                            pw.print(-left.peek() + " " + -bottom.peek());
                        }
                    }
                }
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
