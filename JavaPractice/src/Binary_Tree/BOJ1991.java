package Binary_Tree;

import java.util.Scanner;

public class BOJ1991 {
    // 이진트리 저장 배열
    static int[][] arr;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        // 이진트리 노드 개수
        int N = sc.nextInt();
        // 이진 트리의 노드의 개수 N(1 ≤ N ≤ 26)
        arr = new int[26][2];
        sc.nextLine();
        for (int i=0; i<N; i++) {
            String[] tmp = sc.nextLine().split(" ");
            // 인덱스 변환 위해 'A' 문자 뺌
            int node = tmp[0].charAt(0) - 'A';
            char left = tmp[1].charAt(0);
            char right = tmp[2].charAt(0);

            if (left == '.') {
                arr[node][0] = -1;
            } else {
                arr[node][0] = left - 'A';
            }

            if (right == '.') {
                arr[node][1] = -1;
            } else {
                arr[node][1] = right - 'A';
            }
        }

        preOrder(0);
        System.out.println();
        inOrder(0);
        System.out.println();
        postOrder(0);
    }

    // 전위 순회
    static void preOrder(int node) {
        if (node == -1) return;
        char order = (char) (node + 'A');
        System.out.print(order);
        // 왼쪽 자식
        preOrder(arr[node][0]);
        // 오른쪽 자식
        preOrder(arr[node][1]);
    }

    // 중위 순회
    static void inOrder(int node) {
        if (node == -1) return;
        char order = (char) (node + 'A');
        inOrder(arr[node][0]);
        System.out.print(order);
        inOrder(arr[node][1]);
    }

    // 후위 순회
    static void postOrder(int node) {
        if (node == -1) return;
        char order = (char) (node + 'A');
        postOrder(arr[node][0]);
        postOrder(arr[node][1]);
        System.out.print(order);
    }
}
