package Implementation;

public class PGM43162 {
    public static void main(String[] args) {
        int n = 3;
        int[][] computers = new int[][]{{1, 1, 0}, {1, 1, 0}, {0, 0, 1}};
        int answer = solution(n, computers);
        System.out.println(answer);
    }

    public static int solution(int n, int[][] computers) {
        int answer = 0;
        boolean[] visited = new boolean[n];
        for (int i=0; i<computers.length; i++) {
            if (!visited[i]) {
                DFS(i, visited, computers);
                answer++;
            }
        }
        return answer;
    }

    public static void DFS(int node, boolean[] visited, int[][] computers) {
        visited[node] = true;

        for (int j=0; j<computers.length; j++) {
            // i != j (자기 자신은 포함시키지 x)
            if (!visited[j] && computers[node][j] == 1) {
                DFS(j, visited, computers);
            }
        }
    }
}
