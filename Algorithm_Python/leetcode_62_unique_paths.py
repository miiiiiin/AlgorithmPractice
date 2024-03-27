# leet code 62 unique paths
# bottom-up

def uniquePaths(m, n):
    # 이중 리스트로 dp 테이블 생성
    # 격자의 크기가 m x n일 때, dp 배열 초기화
    memo = [[-1] * n for _ in range(m)]

    # 초기화 (첫번 째 행, 열)
    # 맨 위쪽 행과 맨 왼쪽 열은 한 가지 경로밖에 없음
    for r in range(m):
        memo[r][0] = 1
    for c in range(n):
        memo[0][c] = 1

    # 나머지 칸에 대한 경로 수 계산
    # 첫 행, 열 1로 초기화 해두었으니 1부터 시작
    for r in range(1, m):
        for c in range(1, n):
            # 하나씩 값 채움 (점화식)
            # memo[r][c]는 위 쪽 칸과 왼 쪽 칸을 더한 값

            print(memo)
            memo[r][c] = memo[r-1][c] + memo[r][c-1]

    # 우측 하단 칸까지 도달할 수 있는 경로의 수 반환
    return memo[m-1][n-1]


print(uniquePaths(3, 7))
# print(uniquePaths(3, 4))


# 다른 해결법

def uniquePaths2(m, n):
    dp = [[0] * n for _ in range(m)]

    # print(dp)

    dp[0][0] = 1

    for r in range(m):
        for c in range(n):
            if r == 0 and c == 0:
                continue
            if r == 0:
                dp[r][c] = dp[r][c-1]

            elif c == 0:
                dp[r][c] = dp[r-1][c]
            else:
                dp[r][c] = dp[r][c-1] + dp[r-1][c]

    return dp[m-1][n-1]


print(uniquePaths2(3, 7))


class Solution(object):
    def uniquePaths(self, m, n):
        """
        :type m: int
        :type n: int
        :rtype: int
        """
        # 초기화
        grid = [[0] * n] * m

        # 1가지 방법만 있는 길 초기화
        grid[0] = [1] * n
        for i in range(len(grid)):
            grid[i][0] = 1

        for i in range(m):
            for j in range(n):
                if 0 < i < m and 0 < j <= n:
                    grid[i][j] = grid[i-1][j] + grid[i][j-1]
        return grid[m-1][n-1]

