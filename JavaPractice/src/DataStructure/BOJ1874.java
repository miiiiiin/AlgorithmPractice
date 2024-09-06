package DataStructure;

import java.util.Scanner;
import java.util.Stack;

public class BOJ1874 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] numbers = new int[n];

        for (int i=0; i<n; i++) {
            numbers[i] = sc.nextInt();
        }

        Stack<Integer> stack = new Stack<>();
        StringBuffer bf = new StringBuffer();

        int num = 1; // 스택에 순서대로 넣는 수 (오름차순)
        boolean result = true;

        for(int i=0; i<numbers.length; i++) {
            // 현재 수열의 수
            int su = numbers[i];
//            System.out.println("su1 = " + su + ", num = " + num);
            if (su >= num) {
                // 원소 값과 같아질 때까지 스택에 push
                while (su >= num) {
                    stack.push(num++);
//                    System.out.println("su2 = " + su + ", num = " + num);
                    bf.append("+\n");
                }
                // su와 num이 같아지면 pop
                stack.pop();
                bf.append("-\n");
            } else { // su가 num보다 작거나 같은 경우
                // 오름차순 자연수
                int number = stack.pop();

                // number와 su가 같으면 스택 push/pop 정상 진행 가능
                // 현재 수열에 있는 값이 스택에 있는 가장 마지막 값보다 더 크다는 것 (오름차순으로
                // 스택을 만들었기 때문에 절대로 진행 불가. 예시의 수열대로 출력 불가)
//                System.out.println("number = " + number + ", su = " + su);
                if (number > su) {
                    System.out.println("NO");
                    result = false;
                    break;
                } else {
                    // number와 su가 같을 때
                    bf.append("-\n");
                }
            }
        }

        if (result) System.out.println(bf.toString());

    }
}
