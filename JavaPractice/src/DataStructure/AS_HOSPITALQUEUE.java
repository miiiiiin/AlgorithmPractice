package DataStructure;

import javax.swing.*;
import java.io.IOException;
import java.util.*;
//import java.util.*;

/**
 * 구현 방법
 * 각 환자별 도착 시각, 치료 필요 시간, 우선 순위를 담기 위해 2차원 배열인 [N][2]로 구현하려다가
 * 클래스를 정의하는게 더 직관적일 것 같아서 클래스를 따로 정의하기로 함.
 * 1. 각 환자들의 우선 순위, 도착 시간, 치료 필요 시간을 입력받아 startTimes 배열에 저장 (입출력 순서를 맞추기 위해)
 * 2. 현재 시간인 currTimes를 0으로 두고, 도착 시간이 currTimes 이전일 경우의 모든 환자 정보를 우선순위 큐에 저장
 * 3. 우선순위 큐(우선순위가 높은 순대로 자동 정렬됨)에서 하나씩 환자를 꺼내어 치료
 * 4. 치료가 끝나면 currTimes 변수에 치료 필요시간을 더하여 현재 시간을 갱신
 * 5. 우선순위 큐에 다음 대기 중인 환자를 꺼내어 처리
 */

/**
 * 시간 복잡도
 * 우선순위 큐는 최소 힙(min-heap) 구조를 사용하므로 삽입 작업의 시간 복잡도는 O(log n)
 * N명의 환자를 도착 시간에 따라 큐에 삽입
 * N명의 환자에 대해 N번 삽입 총 삽입하므로 전체 알고리즘의 시간 복잡도는 O(NlogN)
 */
public class AS_HOSPITALQUEUE {
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);

        // 대기하고 있는 환자 수
        int N = sc.nextInt();

        // 각 환자의 치료 시작 시각 저장하는 배열
        int[] startTimes = new int[N];
        List<Patient> patients = new ArrayList<>();

        // 각 환자는 도착 시각, 치료를 받기 위해 필요한 시간, 그리고 우선 순위를 가지고 있습니다
        PriorityQueue<Patient> pq = new PriorityQueue<>();

        for (int i=0; i<N; i++) {
            int pr = sc.nextInt();
            int at = sc.nextInt();
            int rt = sc.nextInt();

            patients.add(new Patient(i, pr, at, rt));
        }

        //  우선 순위가 높은 환자가 먼저 치료를 받게 됩니다
        //  만약 우선 순위가 같다면, 도착 시각이 더 빠른 환자가 먼저 치료를 받습니다.

        // 먼저 모든 환자의 도착 시각과 우선순위를 고려해 환자를 우선순위 큐에 넣습니다.
        // 큐에서 환자를 하나씩 꺼내어 치료를 시작하고, 해당 치료가 끝난 후 다음 환자를 큐에서 꺼내 치료를 시작합니다.

        int currTime = 0;
        int idx = 0;

        while (!pq.isEmpty() || idx<N) {
            // 도착한 환자를 큐에 추가 (도착한 시간 값은 currTime보다 작아야 함)
            if (idx < N && patients.get(idx).arrivalTime <= currTime) {
                pq.offer(patients.get(idx++));
                continue;
            }

            // 큐에 환자가 있으면 치료 시작
            if (!pq.isEmpty()) {
                Patient pt = pq.poll();
                // 각 환자별 치료 시작 시간 저장
                startTimes[pt.id] = currTime;
                // 치료 끝난 이후의 현재 시간 갱신
                currTime += pt.requiredTime;
            } else {
                // 큐가 비었으면 다음 환자 도착 시간으로 감
                currTime = patients.get(idx).arrivalTime;
            }
        }

        // 치료 시작 시각을 환자 1부터 N까지 순서로 한 줄에 하나씩 출력
        for (int startTime : startTimes) {
            System.out.println(startTime);
        }
    }
}

// 환자 클래스
class Patient implements Comparable<Patient> {
    // 우선순위
    int priority;
    // 도착 시각
    int arrivalTime;
    // 치료 필요 시간
    int requiredTime;
    // 환자 번호
    int id;

    // 생성자
    public Patient(int id, int priority, int arrivalTime, int requiredTime) {
        this.id = id;
        this.priority = priority;
        this.arrivalTime = arrivalTime;
        this.requiredTime = requiredTime;
    }

    // 우선순위 큐에서 우선순위 및 도착 시각으로 정렬하기 위한 비교 함수
    @Override
    public int compareTo(Patient o) {
        if (this.priority != o.priority) {
            return this.priority - o.priority;
        } else {
            // 우선 순위가 같을 경우, 도착 시간이 더 빠른 환자가 진료를 받기 때문에 도착 시간 비교
            return this.arrivalTime - o.arrivalTime;
//            return Integer.compare(this.arrivalTime, o.arrivalTime);
        }
    }
}