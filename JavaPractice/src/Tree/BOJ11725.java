package DFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ11725 {

    static int N, n1, n2;

    static ArrayList<Integer>[] tree; // 트리 어레이리스트
    static boolean[] visited; // 방문 여부 저장 배열
    static int[] answers; // 부모 노드 저장 배열

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        visited = new boolean[N+1];
        tree = new ArrayList[N+1];
        answers = new int[N+1];

        // 인접리스트 초기화
        for (int i=0; i<N+1; i++) {
            tree[i] = new ArrayList<Integer>();
            visited[i] = false;

        }

        // 인접리스트 구성
        for (int i=1; i<N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            n1 = Integer.parseInt(st.nextToken());
            n2 = Integer.parseInt(st.nextToken());

            tree[n1].add(n2);
            tree[n2].add(n1);
        }

        // 루트 노드 1부터 실행
        DFS(1);

        // 루트 노드를 제외한 2번째 노드부터 출력해주기로 함
        for (int i=2; i<N+1; i++) {
            System.out.println(answers[i]);
        }
    }

    // DFS
    public static void DFS(int num) {

        visited[num] = true;

        System.out.println("trees:" + num + " " + Arrays.toString(tree) + " " + Arrays.toString(visited));
        // tree의 현재 노드들과 연결되어 있는 다른 노드들 순회
        for (int i: tree[num]) {

            // 아직 방문하지 않은 노드들만 재귀적으로 이동
            if (!visited[i]) {
                // 다음 노드로 가기 전 현재 노드를 다음 노드의 부모 노드로 저장
                answers[i] = num;
                System.out.println("for:" + i + " " + tree[num] + " " + visited[num]);
                DFS(i);
            }
        }
    }
}
