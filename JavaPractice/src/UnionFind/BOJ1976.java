package UnionFind;

import java.util.Scanner;

public class BOJ1976 {
    // 대표 노드 저장 배열
    static int[] parent;
    // 여행 계획 배열
    static int[] tripRoute;
    static int[][] city;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        // 도시의 수
        int n = sc.nextInt();
        // 여행 계획에 속한 도시들의 수
        int m = sc.nextInt();
        parent = new int[n+1];
        // 여행 계획
        tripRoute = new int[m+1];
        city = new int[n+1][n+1];

        for (int i=1; i<=n; i++) {
            // 대표 노드 배열 자기 자신으로 초기화
            parent[i] = i;
            for (int j=1; j<=n; j++) {
                // 인접행렬 채우기
                city[i][j] = sc.nextInt();
            }
        }

        for (int i=1; i<=m; i++) {
            // 여행 계획 입력받기
            tripRoute[i] = sc.nextInt();
        }

        // 인접행렬에서 도시 연결되어 있으면 union 연산
        for (int i=1; i<=n; i++) {
            for (int j=1; j<=n; j++) {
                if (city[i][j] == 1) {
                    union(i, j);
                }
            }
        }


        // route 배열의 대표 노드가 모두 같은지 확인하고 결과 출력
        // 여행 계획 도시들이 1개의 대표 도시로 연결돼 있는지 확인
        // route에 포함되는 노드들의 대표 노드가 모두 동일한지 확인 후 결괏값 출력
        // 여행 계획 도시들이 1개의 대표 도시로 연결돼 있는지 확인
        int rootCity = find(tripRoute[1]);
        for (int i=2; i<tripRoute.length; i++) {
            // find(tripRoute[i]) : 루트 배열 value를 통해 대표 노드 찾는 함수
            if (rootCity != find(tripRoute[i])) {
                System.out.println("NO");
                return;
            }
        }
        System.out.println("YES");
    }

    static void union(int a, int b) {
        a = find(a);
        b = find(b);

        if (a != b) {
            parent[b] = a;
        }
    }
    public static int find(int a) {
        if (parent[a] == a) {
            return a;
        } else {
            // 경로 압축
            return parent[a] = find(parent[a]);
        }
    }
}
