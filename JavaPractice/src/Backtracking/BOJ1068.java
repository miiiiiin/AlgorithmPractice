package Backtracking;

import java.util.ArrayList;
import java.util.Scanner;

public class BOJ1068 {
    // DFS 활용 백트래킹

    static ArrayList<Integer>[] tree;
    static boolean[] visited;
    // 지울 노드의 번호
    static int removeNode = 0;
    // 리프 노드 개수
    static int answer = 0;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        // 트리 노드 개수
        int n = sc.nextInt();
        // 루트
        int root = 0;

        // 인접리스트 배열
        tree = new ArrayList[n+1];
        visited = new boolean[n+1];


        // 인접 리스트 초기화
        for (int i=0; i<n; i++) {
            tree[i] = new ArrayList<Integer>();
        }

        for (int i=0; i<n; i++) {
            int parent = sc.nextInt();
            // 부모 노드가 루트 노드가 아닌 경우 인접 그래프 연결
            if (parent != -1) {
                tree[i].add(parent);
                tree[parent].add(i);
            } else {
                // 부모 노드가 루트 노드인 경우, 루트 노드 번호 설정
                root = i;
            }
        }

        removeNode = sc.nextInt();
        // 루트 노드가 삭제 노드
        if (removeNode == root) {
            // 모두 삭제되므로 0출력
            System.out.println(0);
        } else {
            DFS(root);
            System.out.println(answer);
        }
    }

    public static void DFS(int node) {
        visited[node] = true;
        int checkNode = 0;
        for (int i : tree[node]) {
            if (!visited[i] && removeNode != i) {
                checkNode++;
                DFS(i);
            }
        }
        // checkNode가 0이면 리프 노드라는 뜻
        if (checkNode == 0) {
            answer++;
        }
    }
}

