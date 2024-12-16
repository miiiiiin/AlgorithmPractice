package UnionFind;

import java.util.Scanner;

public class BOJ1717 {
    static int[] parent;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        // 입력으로 주어지는 연산의 개수
        int m = sc.nextInt();
        // 대표 노드 저장하는 배열
        // N이 만약 7이 들어오면 7이라는 노드도 생기기 때문
        parent = new int[n+1];

        for (int i=0; i<=n; i++) {
            // 대표 노드 자기 자신으로 초기화
            parent[i] = i;
        }

        for (int i=0; i<m; i++) {
            int q = sc.nextInt();
            int a = sc.nextInt();
            int b = sc.nextInt();

            if (q == 0) {
                // union (집합 합치기)
                union(a, b);
            } else {
                // find (같은 집합의 원소인지 확인)
                if (sameCheck(a, b)) {
                    System.out.println("YES");
                } else {
                    System.out.println("NO");
                }
            }
        }
    }

    public static void union(int a, int b) {
        // union 연산 : 대표 노드끼리 연결
        a = find(a);
        b = find(b);
        if (a != b) {
            parent[b] = a;
        }
    }

    public static int find(int a) {
        // index값과 value 노드 값이 같으면
        if (a == parent[a]) {
            return a;
        } else {
            // 경로 압축 (재귀 함수 형태)
            // 빠져나올 때의 값을 그 때의 대표 노드 값으로 업데이트 해주는 것 (경로 압축)
            return parent[a] = find(parent[a]); // value를 index로 해서 또 찾아보기
        }
    }

    // 두 원소가 같은 집합인지 확인
    // a와 b가 같은 집합에 포함되어 있는지를 확인하는 연산
    public static boolean sameCheck(int a, int b) {
        a = find(a);
        b = find(b);
        if (a == b) {
            return true;
        }
        return false;
    }
}
