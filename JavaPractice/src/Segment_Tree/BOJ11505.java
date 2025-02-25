package Segment_Tree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Scanner;
import java.util.StringTokenizer;

public class BOJ11505 {

    static long[] tree;
    static int MOD = 1000000007;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        int treeHeight = 0;
        int length = N;

        while (length != 0) {
            length /= 2;
            treeHeight++;
        }

        int treeSize = (int) Math.pow(2, treeHeight+1);
        int leftNodeIndex = treeSize/2 - 1;
        tree = new long[treeSize+1];

        Arrays.fill(tree, 1);
       for (int i=leftNodeIndex+1; i<=leftNodeIndex+N; i++) {
            st = new StringTokenizer(br.readLine());
            tree[i] = Long.parseLong(st.nextToken());
        }
        setTree(treeSize-1);

        for (int i=0; i<M+K; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int start = Integer.parseInt(st.nextToken());
            long end = Integer.parseInt(st.nextToken());

            if (a == 1) {
                changeVal(start+leftNodeIndex, end);
            } else if (a == 2) {
                start = start + leftNodeIndex;
                end = end + leftNodeIndex;
                System.out.println(getMul(start, (int) end));
            } else {
                return;
            }
        }
    }

    private static long getMul(int start, int end) {
        long partMul = 1;
        while (start<=end) {
            if (start % 2 == 1) {
                partMul = (partMul * tree[start]) % MOD;
                start++;
            }
            if (end % 2 == 0) {
                partMul = (partMul * tree[end]) % MOD;
                end--;
            }
            start = start/2;
            end = end/2;
        }
        return partMul;
    }

    private static void changeVal(int index, long value) {
        tree[index] = value;
        index = index/2;
        while (index > 0) {
            tree[index] = tree[2*index] % MOD * tree[2*index+1] % MOD;
            index = index/2;
        }
    }

    private static void setTree(int i) {
        while (i != 1) {
            tree[i/2] = tree[i/2] * tree[i] % MOD;
            i--;
        }
    }
}
