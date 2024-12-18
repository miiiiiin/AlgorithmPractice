package Segment_Tree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/** 구현 방법
 * 구간 질의 및 업데이트 작업을 효율적으로 처리해야 하므로 세그먼트 트리를 사용하였다.
 * 1. 세그먼트 트리 생성
 *  1.1 초기 트리 배열은 Long.MIN_VALUE로 초기화
 *  1.2 입력 배열을 기반으로 세그먼트 트리를 생성하여 입력받은 N개의 수들을 리프 노드의 시작 위치부터 저장
 *  1.3 트리 사이즈 배열 크기 : 2^k * 2
 *  1.4 리프 노드 시작 위치 : 2^k
 *  1.5 setTree함수 : 2^k - 1부터 1까지 가는 방향으로 트리 배열의 앞부분 값 채워나감
 *      => 각 노드의 값은 자식 노드의 최대값으로 설정됨
 * 2. 갱신
 *  2.1 Q줄에 따른 입력을 받을 때, 첫 번째 숫자가 1이면 세그먼트 트리에서 x번째 값을 v로 변경하고, 변경된 값을 반영해 변경된 값이 포함된 세그먼트 트리 노드들을 업데이트
 *  2.2 changeVal : 리프 노드에서 시작하여 부모 노드로 올라가며, 현재 노드의 값을 자식 노드 값의 최대값으로 갱신
 *  2.3 Q줄에 따른 입력을 받을 때, 첫 번째 숫자가 2이면 세그먼트 트리에서 구간 [l, r]의 최대값을 조회하여 출력
 *
 * 3. l부터 r까지의 두루마리 중 최대 마력 수치(최대값) 조회
 *  3.1 시작점(start)이 홀수라면, 해당 노드의 값을 최대값에 반영하고, start를 오른쪽으로 한 칸 이동
 *  3.2 끝점(end)이 짝수라면, 해당 노드의 값을 최대값에 반영하고, end를 왼쪽으로 한 칸 이동
 *  3.3 각 반복마다 start와 end를 부모 노드로 이동시킴
 *  3.4 start와 end값이 교차하게 되면 반복을 종료하고, 최종 최대값을 반환
 */

/** 시간 복잡도
 * 1. 트리 배열 초기화는 O(N)에 수행
 * 2. 각 작업(갱신 및 질의)은 O(log n)에 수행
 * 3. 전체 시간 복잡도는 O((n + q) * log n)이다.
 */
public class AS_MAGIC {
    static long[] tree;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        // 두루마리 개수
        int N = Integer.parseInt(st.nextToken());
        // 작업 개수
        int Q = Integer.parseInt(st.nextToken());

        // 세그먼트 트리 초기화 단계
        int treeHeight = 0;
        int length = N;

        while (length != 0) {
            length /= 2;
            treeHeight++;
        }

        // 배열 크기
        int treeSize = (int) Math.pow(2, treeHeight+1);
        // 리프노드 시작 인덱스 (실제 데이터가 시작되는 리프 노드의 시작 위치)
        int leftStartIdx = treeSize / 2 -1;
        tree = new long[treeSize+1];
        Arrays.fill(tree, Long.MIN_VALUE);

        st = new StringTokenizer(br.readLine());
        for (int i=leftStartIdx+1; i<=leftStartIdx+N; i++) {
            tree[i] = Integer.parseInt(st.nextToken());
        }

        // 초기 트리 생성
        setTree(treeSize-1);

        for (int i=0; i<Q; i++) {
            st = new StringTokenizer(br.readLine());
            long type = Long.parseLong(st.nextToken());
            // 시작 끝 인덱스 (x, v와 l, r 의미)
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());

            if (type == 1) {
                // start번째 두루마리의 마력 수치를 end로 변경
                changeVal(leftStartIdx+start, end);
            }
            if (type == 2) {
                // leftStartIdx 더해서 원래 위치 적용
                start = start + leftStartIdx;
                end = end + leftStartIdx;
                // start부터 end까지의 두루마리 중 최대 마력 수치를 조회
                System.out.println(getMax(start, end));
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
            start = start / 2;
            if (end % 2 == 0) {
                max = Math.max(max, tree[end]);
                end--;
            }
            end = end / 2;
        }
        return max;
    }

    private static void changeVal(int index, long value) {
        tree[index] = value;
        index = index/2;
        while (index > 0) {
            tree[index] = Math.max(tree[2*index], tree[2*index+1]);
            index = index/2;
        }
    }

    private static void setTree(int node) {
        while (node != 1) {
            if (tree[node/2] < tree[node]) {
                tree[node/2] = tree[node];
            }
            node--;
        }
    }
}
