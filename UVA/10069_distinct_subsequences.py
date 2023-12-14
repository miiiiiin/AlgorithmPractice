
def count(X, Z):
    m, n = len(X), len(Z)

    # dp table create (초기화)
    # dp[i][j]는 X의 처음 i개 문자와 Z의 처음 j개 문자까지의 부분문자열에서 나타나는 횟수를 나타냅니다.
    dp = [[0] * (n+1) for _ in range(m+1)]

    # 첫 행과 첫 열은 각각 0 또는 1로 초기화됩니다. (Base case)
    for i in range(m+1):
        # dp[i][0]=1인 이유는 어떤 문자열에 대해서 빈 문자열은 항상 부분문자열로 나타나기 때문
        # 초기값으로 dp[i][0] = 1을 설정한 것은, X의 처음 i개 문자와 빈 문자열(길이 0) 사이에는 항상 부분문자열이 존재한다는 것을 의미
        dp[i][0] = 1

    print("dp:", dp)

    # dp 테이블 채우기
    # 각각 X와 Z의 첫 번째 문자부터 마지막 문자까지 순차적으로 확인하는 반복문
    for i in range(1, m+1):
        for j in range(1, n+1):

            print(X[i-1], Z[j-1])
            # 현재 비교하는 X와 Z의 문자가 같은지를 확인합니다. 여기서 X[i - 1]은 X 문자열의 i번째 문자를 나타내며, Z[j - 1]은 Z 문자열의 j번째 문자
            # 문자열의 인덱스는 0부터 시작하므로 1을 빼줘야 실제 문자에 접근
            if X[i-1] == Z[j-1]:
                # 점화식 구현
                # 현재 부분문자열에서 새로운 문자가 일치하는 경우
                # dp[i][j]에는 이전 부분문자열에서 나타났던 횟수와 현재 부분문자열에서 새로운 문자가 일치하는 경우의 횟수를 합한 값을 저장
                dp[i][j] = dp[i-1][j-1] + dp[i-1][j]
                print(i, dp[i], j, dp[i][j])
            else:
                # 현재 부분문자열에서 새로운 문자가 일치하지 않는 경우입니다. 따라서 dp[i][j]에는 이전 부분문자열에서 나타났던 횟수를 그대로 가져옵니다.
                dp[i][j] = dp[i-1][j]
                print(i, dp[i], j, dp[i][j])

            # print(i, dp[i], j, dp[i][j])
    # dp[m][n]의 값이 문자열 Z가 문자열 X의 부분문자열로 나타나는 횟수를 나타냅
    print("an:", m, n, dp[m])
    return dp[m][n]


def main():
    test_cases = int(input())

    for _ in range(test_cases):
        X = str(input())
        Z = str(input())
        answer = count(X, Z)
        print(answer)


if __name__ == '__main__':
    main()

