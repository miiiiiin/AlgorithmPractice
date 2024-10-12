package DataStructure;

import java.util.Scanner;
import java.util.Stack;

/**
 * 구현 방법
 * 1. 처음에 괄호 문제와 비슷한 줄 알고 비슷하게 구현했으나 실패
 * 2. <div></div> 태그 구조일 경우, <와 >안의 태그명도 비교해야 하는 것을 확인
 * 3. 열거형을 정의하여 태그 유형을 OPEN, CLOSE, EMPTY로 정의
 * 태그 유형을 확인하는 함수를 만들어 < 이후의 문자열 매개변수로 넘겨 리턴하도록 구현
 * OPEN은 아무것도 없는 경우, CLOSE는 < 이후 문자열이 "/"로 시작하는 경우, EMPTY는 < 이후 문자열이 "/"로 끝나는 경우
 * 4. 시작 인덱스부터 문자열 끝 인덱스인 종료 인덱스까지 문자열을 순회하며 OPEN인 경우 태그명을 푸쉬하고, 이후 CLOSE 타입일 경우
 * 이전에 스택에 푸쉬한 값과 비교하여 같으면 스택에서 제거, 다르면 "false" 출력
 * 5. while문을 다 수행한 결과 스택에 값이 남아있으면 올바른 태그 구조가 아니라는 의미로 false 출력. 스택이 비어있으면
 * 올바른 태그 구조로 true 출력
 */

/** 시간복잡도 분석
 * 1. while (i<k) 순회할 경우, 항상 그 다음 위치의 문자열로 이동하기 때문에 O(N)
 * 2. 문자열을 순회하며 스택에 푸쉬하거나 팝하는 연산의 시간복잡도 : O(N)
 * 따라서 전체 시간 복잡도는 O(N)이다.
 */
public class AS_TAG {

    // 태그유형을 나타내는 열거형
    enum TagType {
        OPEN, CLOSE, EMPTY
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String str = sc.nextLine();

        System.out.println(match(str));
    }

    public static String match(String str) {

        char[] c = str.toCharArray();

        Stack<String> stack = new Stack<>();

        // 시작 인덱스
        int i=0;
        // 종료 인덱스
        int k=str.length();

        while (i<k) {
            if (str.charAt(i) == '<') {
                // 닫는 '>'의 위치 찾기
                int j = str.indexOf('>', i + 1);
                if (j == -1) {
                    // > 가 없으면 올바르지 않은 태그 구조
                    return "false";
                }

                // 문자열의 < 다음에 위치한 인덱스 i+1과 >가 있는 인덱스 j 사이의 태그 속성 문자열을 추출
                String content = str.substring(i+1, j).trim();
                // 태그 유형 확인
                TagType type = getTagType(content);
                switch (type) {
                    case CLOSE:
                        // "/span"일 경우, /를 삭제하여 스택의 탑 값과 비교하여 현재 태그명과 다르면 false 출력. 같으면 스택의 pop
                        // 태그 이름 추출
                        String closeTag = content.substring(1);
                        if (stack.isEmpty() || !stack.peek().equals(closeTag)) {
                            return "false";
                        }
                        // 올바른 경우 스택에서 제거
                        stack.pop();
                        break;
                    case EMPTY:
                        break;
                    case OPEN:
                        // < 다음 시작 태그
//                        String openingTagName = content.split("\\s+|/")[0]; // 속성을 제외한 태그 이름 추출
//                        stack.push(openingTagName); // 스택에 추가

                        /**
                         * .split("\\s+|/")는 태그의 문자열을 공백 또는 **슬래시(/)**를 기준으로 나누는 역할
                         * \\s+는 하나 이상의 공백을 의미
                         * |는 **또는(or)**을 의미하며, 공백 또는 /가 나타나는 곳에서 문자열을 분리
                         * .split("\\s+|/")는 태그 문자열에서 공백이나 슬래시를 기준으로 태그 이름과 속성을 분리
                         */

                        // 시작 태그가 <a href="#">일 경우, 공백 기준으로 문자열얼 나눠서 태그명인 첫번째만 추출
                        String openTag = content.split(" ")[0];
                        // 태그명 스택에 푸쉬
                        stack.push(openTag);
                        break;
                }

                // >가 위치해있는 j 인덱스 다음 문자로 이동
                i = j+1;
            } else {
                // < 이외의 문자일 경우, 다음 문자로 이동
                i++;
            }
        }

        return stack.isEmpty() ? "true" : "false";
    }

    private static TagType getTagType(String str) {
        if (str.startsWith("/")) {
            // 종료 태그명 ex) /span
            return TagType.CLOSE;
        } else if (str.endsWith("/")) {
            // 빈 태그명 ex) br/
            return TagType.EMPTY;
        } else {
            return TagType.OPEN;
        }
    }
}
