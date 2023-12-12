from collections import deque


def main():

    while True:
        # 정점의 개수
        n = int(input())
        # 간선의 개수
        m = int(input())

        graph = [[0] * n for _ in range(n)]
        # 정점의 색 나타내는 리스트 초기화
        color = [-1] * n

        visited = True

        for i in range(m):
            # 각 간선으로 이어지는 정점의 쌍
            u, v = map(int, input().split())
            graph[u][v] = 1
            graph[v][u] = 1

        # 시작 정점 0으로 초기화
        q = deque([0])
        color[0] = 0  # 시작 정점 0번 색으로 색칠
        flag = True  # 그래프를 두가지 색으로 칠할 수 있는지 여부

        # queue가 빌 때까지 실행
        while q:
            # 큐에서 정점 꺼내옴
            u = q.popleft()

            for v in range(n):

                # 색 아직 안칠한 경우
                if color[v] == -1:
                    color[v] = color[u] + 1
                    q.append(v)  # 새로운 정점 큐에 추가하여 bfs 계속 수행

                # 인접한 정점과 같은 색으로 칠해질 경우
                elif color[u] == color[v]:
                    flag = False
                    break

            # 불가능할 경우 반복문 종료
            if not flag:
                break
        if flag:
            print("BICOLORABLE.")
        else:
            print('NOT BICOLORABLE.')


if __name__ == "__main__":
    main()

