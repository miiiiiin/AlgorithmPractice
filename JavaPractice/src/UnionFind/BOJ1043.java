package UnionFind;

import java.util.ArrayList;
import java.util.Scanner;

public class BOJ1043 {
    // 대표 노드 저장 배열
    static int[] parent;
    // 진실을 아는 사람의 수와 번호
    static int[] truthP;
    // 각 파티에 오는 사람 수와 번호
    static ArrayList<Integer>[] party;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        // 사람의 수
        int n = sc.nextInt();
        // 파티의 수
        int m = sc.nextInt();
        // 진실을 아는 사람의 수
        int T = sc.nextInt();
        // 과장할 수 있는 파티의 수
        int answer = 0;

        parent = new int[n+1];
        truthP = new int[T];
        party = new ArrayList[m+1];

        for (int i=0; i<T; i++) {
            // 진실 아는 사람 저장
            truthP[i] = sc.nextInt();
        }

        // 대표 노드 초기화
        for (int i=0; i<n; i++) {
            parent[i] = i;
        }

        for (int i=0; i<m; i++) {
            // party 어레이 리스트 초기화
            party[i] = new ArrayList<>();
            int party_people_come = sc.nextInt();
            // 파티 오는 사람 수만큼 번호 입력받기
            for (int j=0; j<party_people_come; j++) {
                party[i].add(sc.nextInt());
            }
        }

        for (int i=0; i<m; i++) {
            // 각 파티에 참여한 사람들 1개 그룹으로 만들기
            int firstPeople = party[i].get(0); // 각 파티의 첫번째 사람
            for (int j=0; j<party[i].size(); j++) {
                union(firstPeople, party[i].get(j));
            }
        }

        // 각 파티의 대표 노드와 진실을 아는 사람들의 대표 노드가 일치하면 과장할 수 없음
        for (int i=0; i<m; i++) {
            boolean isPossible = true;
            int curr = party[i].get(0);
            for (int j=0; j< truthP.length; j++) {
                if (find(curr) == find(truthP[j])) {
                    isPossible = false;
                    break;
                }
            }
            if (isPossible) answer++;
        }
        System.out.println(answer);
    }

    public static void union(int a, int b) {
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
            return parent[a] = find(parent[a]);
        }
    }
}
