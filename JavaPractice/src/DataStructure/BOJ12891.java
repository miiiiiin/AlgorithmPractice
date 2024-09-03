package DataStructure;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ12891 {
    // 비밀번호 체크 배열 (유효성 판단)
    static int checkArr[];
    // 현재 상태 배열 체크
    static int currArr[];
    // 몇 개의 문자와 관련된 개수를 충족했는지 체크
    static int checked = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int answer = 0;

        // DNA 문자열 길이
        int S = Integer.parseInt(st.nextToken());
        // 부분 문자열 길이
        int P = Integer.parseInt(st.nextToken());

        // 비밀번호 체크 배열 (유효성 판단)
        checkArr = new int[4];
        // 현재 상태 배열 체크
        currArr = new int[4];

        // DNA 문자열
        char dna[] = new char[S];
        dna = br.readLine().toCharArray();

        st = new StringTokenizer(br.readLine());
        for (int i=0; i<4; i++) {
            // 부분 문자열에 필요한 A,C,G,T의 최소 개수
            checkArr[i] = Integer.parseInt(st.nextToken());
            if (checkArr[i] == 0) {
                // checkArr 배열의 원소값이 0이면 자동으로 유효성을 충족한 것이므로 확인 1 증가
                checked++;
            }
        }

        // 슬라이딩 윈도우
        // P 범위 (0 ~ P-1)만큼 S배열에서 확인, 유효한 비밀번호인지 판단
        // 초기 P 부분 문자열 처리 부분
        for (int i=0; i<P; i++) {
            add(dna[i]);
        }

        // 4자리 비밀번호 유효성 충족 확인
        if (checked == 4) {
            answer++;
        }

        // P ~ S 범위 확인
        for (int i=P; i<S; i++) {
            System.out.println("P = " + P + ", S=" + S);
            int s_index = i - P;
            System.out.println("s_index = " + i);
            add(dna[i]);
            remove(dna[s_index]); // 맨 왼쪽 인덱스 슬라이딩 윈도우 영역에서 제거

            if (checked == 4) {
                answer++;
                System.out.println("answer = " + answer);
            }

            br.close();
        }

        System.out.println(answer);
    }

    // 새로 들어온 문자 처리하는 함수
    private static void add(char c) {
        switch (c) {
            case 'A':
                currArr[0]++;
                if (currArr[0] == checkArr[0]) {
                    checked++; // A 문자열 포함 개수 증가
                }
                break;

            case 'C':
                currArr[1]++;
                if (currArr[1] == checkArr[1]) {
                    checked++;
                }
                break;

            case 'G':
                currArr[2]++;
                if (currArr[2] == checkArr[2]) {
                    checked++;
                }
                break;

            case 'T':
                currArr[3]++;
                if (currArr[3] == checkArr[3]) {
                    checked++;
                }
                break;
        }
//        System.out.println("check = " + checked);
        System.out.println("currArr = " + Arrays.toString(currArr) + ", checkaRR = " + Arrays.toString(checkArr));
        }

    // 슬라이딩 윈도우에서 제거되는 문자 처리하는 함수
    private static void remove(char c) {
        switch (c) {
            case 'A':
                if (currArr[0] == checkArr[0]) {
                    checked--;
                }
                currArr[0]--;
                break;

            case 'C':
                if (currArr[1] == checkArr[1]) {
                    checked--;
                }
                currArr[1]--;
                break;

            case 'G':
                if (currArr[2] == checkArr[2]) {
                    checked--;
                }
                currArr[2]--;
                break;

            case 'T':
                if (currArr[3] == checkArr[3]) {
                    checked--;
                }
                currArr[3]--;
                break;
        }
    }
}