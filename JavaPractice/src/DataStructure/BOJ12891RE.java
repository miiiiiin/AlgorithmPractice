package DataStructure;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ12891RE {

    static int checkArr[];
    static int currArr[];
    static int checkSecret;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int S = Integer.parseInt(st.nextToken());
        // 부분 문자열 길이
        int P = Integer.parseInt(st.nextToken());

        int answer = 0;

        // 비밀번호 체크 배열 (dna 비밀번호 구성요소가 4개므로 배열 크기 4로 설정)
        checkArr = new int[4];
        // 현재 비밀번호 상태 체크
        currArr = new int[4];
        // 문자열 저장 배열
        char A[] = new char[S];
        // 비밀번호 부분문자열 충족 카운팅
        checkSecret = 0;

        A = br.readLine().toCharArray();
        st = new StringTokenizer(br.readLine());

        // 비밀번호 각 문자별 충족요건 입력받기
        for (int i=0; i<4; i++) {
            checkArr[i] = Integer.parseInt(st.nextToken());
            // 예외처리 (해당 문자열을 포함하지 않아도 상관 x)
            if (checkArr[i] == 0) {
                checkSecret++;
            }
        }

        // 부분 문자열 처음 받을 때 세팅
        for (int i=0; i<P; i++) {
            add(A[i]);
        }

        // 처음 나온 게 부분 문자열이 비밀번호가 가능한 경우, answer 증가
        if (checkSecret == 4) {
            answer++;
        }

        // 여기까지는 첫 인덱스부터 부분 문자열 길이인 P까지의 탐색이 완료된 상황.


        // 슬라이딩 윈도우 한 칸 이동한 상태부터 다시 탐색 시작 (이동하면서 새로운 것 넣고,뺌)
        for (int i=P; i<S; i++) {
            // 부분문자열에서 j는 맨 왼쪽 인덱스, i는 맨 오른쪽 인덱스가 된다.
            // 슬라이딩 윈도우 범위를 유지하면서 i,j가 한 칸씩 가는 효과가 있음.
            int j = i-P;
            add(A[i]); // CCTGGATTG일 경우, G부터 들어감 (A[i]=G)
            System.out.println("j = " + j + ", a[j] = " + A[i]);
            // 맨 처음 문자열 슬라이딩 윈도우 범위에서 삭제 (CCTGGATTG일 경우, 맨 앞글자인 C)
            remove(A[j]);

            if (checkSecret == 4) answer++;
        }

        System.out.println("answer = " + answer);
        br.close();
    }

    private static void remove(char c) {
        switch (c) {
            case 'A':
                // 슬라이딩 윈도우 범위에서 빠지면 현재 상태 배열 원소뿐만 아니라 checkSecret도 감소
                if (checkArr[0] == currArr[0]) checkSecret--;
                currArr[0]--;
                break;
            case 'C':
                if (checkArr[1] == currArr[1]) checkSecret--;
                currArr[1]--;
                break;
            case 'G':
                if (checkArr[2] == currArr[2]) checkSecret--;
                currArr[2]--;
                break;
            case 'T':
                if (checkArr[3] == currArr[3]) checkSecret--;
                currArr[3]--;
                break;
        }

    }
    private static void add(char c) {
        switch (c) {
            case 'A':
                currArr[0]++;
                // 비밀번호 요건 충족 시 카운팅 증가
                if (checkArr[0] == currArr[0]) checkSecret++;
                break;
            case 'C':
                currArr[1]++;
                // 비밀번호 요건 충족 시 카운팅 증가
                if (checkArr[1] == currArr[1]) checkSecret++;
                break;
            case 'G':
                currArr[2]++;
                // 비밀번호 요건 충족 시 카운팅 증가
                if (checkArr[2] == currArr[2]) checkSecret++;
                break;
            case 'T':
                currArr[3]++;
                // 비밀번호 요건 충족 시 카운팅 증가
                if (checkArr[3] == currArr[3]) checkSecret++;
                break;
        }
    }
}
