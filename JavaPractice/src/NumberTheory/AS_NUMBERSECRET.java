package NumberTheory;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * 구현 방법
 * 문제에서 주어진 각 숫자의 범위는 1 ≤ A[i] ≤ 10,000
 * 숫자 A[i]가 최대 10,000일 경우,
 * 숫자 10,000의 자릿수 합: 1 + 0 + 0 + 0 + 0 = 1
 * 숫자 9,999의 자릿수 합: 9 + 9 + 9 + 9 = 36 => 10,000 이하의 숫자들 중 자릿수의 합 최대값은 36임
 * 자릿수의 최대 합은 두자리 수이나 인덱스 범위 초과 방지를 위해 소수 판별 시에는 그 범위를 3자리 수로 넉넉하게 함
 *
 * 1. 입력된 숫자들 중 자릿수의 합이 소수인 숫자들을 판별한다.
 * 2. 소수 조건을 만족하는 숫자들을 리스트에 따로 저장한다.
 * 3. 소수 조건 만족하는 리스트 안의 숫자들끼리 쌍을 이뤄 최대 공약수를 계산하고, 이들 숫자 조합 중 가장 큰 최대 공약수를 구한다.
 * 4. 조건을 만족하는 자릿수의 합이 2개 미만이면 -1을 출력한다. 조건을 만족하면 가장 큰 최대 공약수를 출력한다.
 */

/**
 * 시간 복잡도
 * 1. 자릿수 합 계산 : 각 숫자에 대한 자릿수 합 계산은 O(log(num))임.
 * num의 최대 크기가 10,000로 5자리이므로 자릿 수 합 계산 시간은 O(1)로 상수 시간임.
 * 그러나 N개의 숫자가 있으므로, O(N)의 시간복잡도를 가짐
 *
 * 2. 소수 판별 : 소수 판별은 배열의 최대 범위인 100이하의 수에서 이루어짐.
 * 각 배열의 숫자 원소에 대해 소수를 판별하는 연산은 상수 시간 O(1)에 처리할 수 있음.
 * 그러므로 O(N)의 시간복잡도를 가짐
 *
 * 3. 최대 공약수 계산
 * 최대 공약수 gcd를 계산하는데 드는 시간 복잡도는 O(log(min(a, b)))이다. => O(logM)
 * 이 연산을 소수인 자릿수 합을 가진 모든 숫자 쌍에 대해 진행하므로 O(NlogM)의 시간복잡도를 가진다.
 *
 * 그래서 전체 시간 복잡도는 가장 큰 O(NlogM)에 의해 결정된다.
 */
public class AS_NUMBERSECRET {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        /**
         * 자릿수의 합이 소수인 숫자들 중에서 최대 GCD를 찾아야 해(즉, 자릿수의 합이 소수인 숫자 쌍 끼리의
         * GCD 중 제일 큰 값을 찾아야 해)
         * 만약 조건을 만족하는 숫자가 두 개 미만이라면, 그 비밀을 풀 수 없다는 뜻으로 -1을 출력해.
         *
         * 자릿수의 합: 각 숫자의 자릿수의 합을 계산해야 해. 예를 들어, 숫자 123의 자릿수의 합은 1 + 2 + 3 = 6이야.
         * 소수 판별: 자릿수의 합이 소수인지 확인해야 해. 소수는 1과 자기 자신만을 약수로 가지는 자연수야. 예를 들어, 2, 3, 5, 7 등이 소수야.
         * GCD 계산: 조건을 만족하는 숫자들 간의 최대 공약수를 계산해. 최대 공약수는 주어진 숫자 집합에서 공통적으로 나누어 떨어지는 가장 큰 숫자야.
         */

        // 숫자 저장하는 배열
        int[] numArr = new int[N];

        for (int i=0; i<N; i++) {
            numArr[i] = sc.nextInt();
        }

        // 자릿수 합 계산
        boolean[] isPrime = primeCheck();

        // 소수 조건을 만족하는 숫자들을 저장할 리스트
        ArrayList<Integer> primeSumList = new ArrayList<>();

        // 각 숫자에 대해 자릿수의 합을 구하고 소수인지 판별
        for (int num: numArr) {
            int digitSum = getSumDigit(num);
            if (isPrime[digitSum]) {
                primeSumList.add(num);
            }
        }

//        System.out.println(primeSumList);

        // 자릿수 합이 소수임을 만족하는 숫자가 2개 미만이면 -1 출력
         if (primeSumList.size() < 2) {
             System.out.println(-1);
         } else {
             // 자릿수의 합이 소수인 숫자들 중에서 최대 GCD
             int max = 0;

             // 원소의 두 숫자씩 GCD 계산, 가장 큰 GCD 계산
             for (int i=0; i<primeSumList.size(); i++) {
                 for (int j=i+1; j<primeSumList.size(); j++) {
                    int gcd = gcd(primeSumList.get(i), primeSumList.get(j));
                    max = Math.max(max, gcd);
                 }
             }

             System.out.println(max);
         }
    }

    // 유클리드 호제법 이용한 GCD 계산 함수
    public static Integer gcd(int a, int b) {
        if (b==0) return a;
        else return gcd(b, a%b);
    }

    // 소수 구하는 함수 (에라토스테네스의 체 원리 이용)
    public static boolean[] primeCheck() {
        boolean[] isPrime = new boolean[101];

        for (int i=2; i<isPrime.length; i++) {
            isPrime[i] = true;
        }

        for (int i=2; i<=Math.sqrt(isPrime.length); i++) {
            if (isPrime[i] == false) continue;
            for (int j=i+i; j<isPrime.length; j=j+i) {
                // 배수 지우기(해당 수가 소수가 아님을 표시)
                isPrime[j] = false;
            }
        }
        return isPrime;
    }

    // 자릿수 합 구하는 합수
    public static Integer getSumDigit(int num) {
        int sum = 0;
        while (num>0) {
            sum += num % 10;
            num /= 10;
        }
        return sum;
    }
}
