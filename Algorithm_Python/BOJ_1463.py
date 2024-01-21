import sys

# 재정의 해주면 readline()해주는 것과 같음
input = sys.stdin.readline


def Solution(N):
    N = int(input())

    # 연산 횟수 최소값을 저장하는 DP 테이블
    D = [0] * (N+1)  # 배열의 리스트 인덱스는 0부터 시작하니까 N+1

    # D[1] = 1이 될 떄까지 필요한 연산 횟수 (이미 1이니까 1이 될 때까지 연산 불필요 그래서 D[1] = 0)
    D[1] = 0

    # N 까지 루프 돌림
    # 1은 이미 위와 같이 초기화 되어 있어서 2부터 시작
    for i in range(2, N+1):
        # -1 연산 표현
        D[i] = D[i-1] + 1

        # 2의 배수이면
        if i % 2 == 0:  # 나누기 2 연산
            # 2로 나누어 떨어지니까 나누기 연산을 했다고 가정
            # 기존 D[i]와 i를 2로 나눈 몫 중에서 비교
            D[i] = min(D[i], D[i//2] + 1)

        if i % 3 == 0:  # 나누기 3 연산
            # 처음에 3으로 나눠서 연산을 했다고 가정
            D[i] = min(D[i], D[i//3] + 1)
            # 연산의 수가 한번 늘어나니까 +1 해줌

    # D[N]: N을 1로 만드는데 필요한 최소 연산 횟수
    return D[N]
    # print(D[N])


result = Solution(6)
print(result)

