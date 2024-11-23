package DFS;

import java.util.LinkedList;
import java.util.Queue;

public class PGM46163 {
    public static void main(String[] args) {
        String begin = "hit";
        String target = "cog";
        String[] words = {"hot", "dot", "dog", "lot", "log", "cog"};
        int answer = solution(begin, target, words);
        System.out.println(answer);
    }

    public static int solution(String begin, String target, String[] words) {
        int answer = 0;

        boolean[] visited = new boolean[words.length];
        Queue<String> queue = new LinkedList<>();
        queue.offer(begin);

        while (!queue.isEmpty()) {
            // 현재 레벨 큐 크기
            int size = queue.size();

            for (int i=0; i<size; i++) {
                // 현재 단어
                String curr = queue.poll();

                if (curr.equals(target)) {
                    return answer;
                }

                for (int j=0; j< words.length; j++) {
                    // 방문 조건 : 방문하지 않았고, 변환이 가능하면
                    if (!visited[j] && canConvert(curr, words[j])) {
                        visited[j] = true;
                        queue.offer(words[j]);
                    }
                }
            }
            // 현재 레벨의 모든 단어를 처리하고 단계 증가
            answer++;
        }

        return 0;
    }

    public static boolean canConvert(String from, String to) {
        // 문자 차이 나타냄
        int diff = 0;
        for (int i=0; i<from.length(); i++) {
            if (from.charAt(i) != to.charAt(i)) {
                // 다른 문자 발생 시마다 증가
                diff++;
            }
            if (diff > 1) {
                // 다른 문자가 한 개를 초과하면 변환 여부 false
                return false;
            }
        }
        // 다른 문자가 1개면 변환 여부 true
        return diff == 1;
    }
}
