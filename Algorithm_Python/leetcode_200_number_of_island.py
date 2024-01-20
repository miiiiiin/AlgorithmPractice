# bfs 풀이
from collections import deque
# bfs는 deque 자료구조를 씀


def numIslands(grid):
    number_of_islands = 0
    m = len(grid)  # row
    n = len(grid[0])  # column
    visited = [[False]*n for _ in range(m)]  # 2차원 배열이 만들어지면서 한 행이 다 false가 됨

    # 함수 안의 함수 형식으로 bfs 함수 생성(위의 인스턴스 참조위해)

    def bfs(x, y):
        # 동서 남북 표현 (상화좌우 이동 표현)
        dx = [-1, 1, 0, 0]  # (상하좌우 표현=>암시적 그래프 표현)
        dy = [0, 0, -1, 1]

        visited[x][y] = True
        queue = deque()
        queue.append((x, y))  # 튜플 형식으로 x,y 원소 추가

        while queue:
            cur_x, cur_y = queue.popleft()
            for i in range(4):  # 상화좌우 이동
                next_x = cur_x + dx[i]
                next_y = cur_y + dy[i]
                # if 방문하면 안되는 애들 좌표 (상하좌우에서 참조할 수 없으면 에러남, 물인곳, 이미 방문한 곳)
                if next_x >= 0 and next_x < m and next_y >= 0 and next_y < n:
                    if grid[next_x][next_y] == "1" and not visited[next_x][next_y]:
                        visited[next_x][next_y] = True
                        queue.append((next_x, next_y))

    for i in range(m):
        for j in range(n):
            # if 땅? & 방문안한거
            if grid[i][j] == "1" and not visited[i][j]:
                bfs(i, j)
                number_of_islands += 1
            # bfs or dfs 수행

    return number_of_islands
