package Segment_Tree;

import java.util.Arrays;
import java.util.Scanner;

public class MAGIC {

    static long[] tree;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int Q = sc.nextInt();

        int treeHeight = 0;
        int length = N;

        while (length != 0) {
            length /= 2;
            treeHeight++;
        }

        int treeSize = (int) Math.pow(2, treeHeight+1);
        int leftNodeIndex = treeSize/2 - 1;
        tree = new long[treeSize+1];

        Arrays.fill(tree, Long.MIN_VALUE);
        for (int i=leftNodeIndex+1; i<=leftNodeIndex+N; i++) {
            tree[i] = sc.nextLong();
        }

        setTree(treeSize-1);

        for (int i=0; i<Q; i++) {
            int a = sc.nextInt();
            int start = sc.nextInt();
            long end = sc.nextLong();

            if (a == 1) {
                changeVal(start+leftNodeIndex, (int) end);
            } else if (a == 2) {
                start = leftNodeIndex + start;
                end = leftNodeIndex + end;
                System.out.println(getMax(start, (int) end));
            } else {
                return;
            }
        }
    }

    private static long getMax(int start, int end) {
        long max = Long.MIN_VALUE;
        while (start<=end) {
            if (start % 2 == 1) {
                max = Math.max(max, tree[start]);
                start++;
            }
            start = start/2;

            if (end % 2 == 0) {
                max = Math.max(max, tree[end]);
                end--;
            }
            end = end/2;
        }
        return  max;
    }

    private static void changeVal(int index, int value) {
        tree[index] = value;
        index = index/2;
        while (index > 0) {
            tree[index] = Math.max(tree[2*index], tree[2*index+1]);
            index = index/2;
        }
    }

    private static void setTree(int i) {
        while (i != 0) {
            if (tree[i/2] < tree[i]) {
                tree[i/2] = tree[i];
            }
            i--;
        }
    }
}
