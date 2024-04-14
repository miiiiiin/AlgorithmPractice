# 교차로를 정점, 도로를 간선, 거리를 가중치로 하는 그래프를 구축
# 각 교차로에서 다른 교차로로 가는 전쌍 최단 경로를 구함
# 임의의 한 교차로에 소방서를 지은 후에, 다른 모든 교차로(즉, 집)에서 가장 가까운 소방서까지의 거리를 찾음(최단 경로)
# 이 최단 경로 중에서 가장 긴 경로가 소방서에서 가장 멀리 있는 집의 거리이며, 이를 최소화하는 것이 목적
# 모든 교차로에 소방서를 지어보면서 소방서에서 가장 멀리 있는 집의 거리가 최소화되는 교차로를 찾으면 됨


# 접근 방식
# 그래프 생성: 도시의 교차로와 도로를 그래프로 표현한다. 각 교차로를 노드로, 도로를 간선으로 나타낸다.

# 최단 경로 계산: 각 소방서에서 모든 교차로까지의 최단 경로를 계산한다. 이때, 다익스트라 알고리즘을 사용할 수 있다.

# 최대 거리 계산: 각 교차로에서 소방서까지의 최단 거리 중 최댓값을 계산한다.

# 최소화 문제 해결: 소방서를 지을 교차로 위치를 결정한다. 이때, 최대 거리가 최소가 되도록 소방서를 지으면 된다.

# 결과 출력: 각 테스트 케이스에 대해 소방서를 지을 교차로 번호를 출력한다.

# 이를 통해 소방서를 지을 위치를 찾을 수 있으며, 최단 거리와 최대 거리의 계산은 그래프에서 간선의 가중치를 활용하여 쉽게 구할 수 있다.

import sys
import heapq


# graph: 그래프의 인접 리스트 표현입니다. 각 노드(교차로)에서 연결된 노드들과 간선 가중치를 저장합니다.
# start: 최단 경로를 찾기 시작하는 노드(출발 교차로)입니다.
# distance: 출발 노드로부터 각 노드까지의 현재까지의 최단 거리를 저장하는 배열입니다.

def dijkstra(graph, start, distances):
    # distances = [sys.maxsize] * n
    distances[start] = 0
    # visited = [False] * n

    # 시작노드 큐에 추가
    # pq.put((0, start))
    pq = [(0, start)]

    # 힙이 비어있지 않은 동안 반복(빌때까지)
    while pq:
        # 현재 교차로와 그까지의 거리를 꺼냄
        current_distance, current_node = heapq.heappop(pq)
        # print("cd, cv:", current_distance, current_node)

        # if visited[current_vertex]:
        # 방문한 적 있으면 반복 계속
        # continue

        # 현재 노드를 방문한 노드로 업데이트
        # visited[current_vertex] = True

        # 현재 선택 노드에서 나갈 수 있는 에지 가져옴
        # distances[current_node]: 각 n번째 노드와 연결되어 있는 노드와 가중치

        # 이미 처리한 노드인 경우 무시
        if distances[current_node] < current_distance:
            continue

        # 현재 교차로에서 갈 수 있는 모든 이웃 교차로들에 대해 반복
        for neighbor, weight in graph[current_node]:
            if current_distance + weight < distances[neighbor]:
                # 현재까지의 최단 거리보다 더 짧은 거리를 발견한 경우
                # 최단 거리를 갱신하고 힙에 추
                distances[neighbor] = current_distance + weight
                # 우선순위 큐에 연결 노드 추가
                # 거리 리스트 기준으로 값 정렬하고 싶기 때문에 distances 먼저 넣음
                # 갱신된 거리와 교차로를 우선 순위 큐에 추가
                heapq.heappush(pq, (distances[neighbor], neighbor))


def find_intersection():

    # 소방서, 교차로의 개수
    f, i = map(int, input().split())

    # 기존 소방서가 위치한 교차로 번호 (출발 노드?)
    fire_station_no = [int(input().strip()) for _ in range(f)]
    graph = [[] for _ in range(i+1)]

    for g in range(i):
        # 각 도로를 나타내는 세 개의 양의 정수
        # 한 교차로 번호, 다른 교차로 번호, 도로의 길이
        # 시작, 종점, 가중치
        # 도로의 정보인 출발 교차로(u), 도착 교차로(v), 그리고 도로의 길이(w)를 입력으로 받음
        U, V, W = map(int, input().split())

        # 양방향 도로이므로, 출발 교차로에서 도착 교차로로 가는 간선과
        # 도착 교차로에서 출발 교차로로 가는 간선을 모두 추가함
        graph[U].append((V, W))
        graph[V].append((U, W))
        # 입력으로 받은 도로 정보를 그래프에 양방향 간선으로 추가하는 과정 수행

        print("gu:", graph[g])

    # 기존 소방서로부터의 최단 거리 계산을 위한 배열 초기화
    original_distances = [sys.maxsize] * (i + 1)

    # 각 소방서로부터의 최단 거리 계산
    for j in range(f):
        dijkstra(graph, fire_station_no[j], original_distances)

    # 소방서가 지어질 수 있는 모든 위치에서의 최단 거리 저장
    shortest_distances = original_distances[1:]
    print("sd:", shortest_distances)

    # 최소, 최대 거리 및 해당 위치 초기화
    min_max_distance = sys.maxsize
    best_loc = -1

    # 각 교차로에서의 최대 거리 계산
    for n in range(1, i+1):
        dijkstra(graph, n, original_distances)
        print("md:", original_distances[1:])
        max_distance = max(original_distances[1:])

        # 최소 최대 거리와 비교하여 갱신
        if min_max_distance > max_distance:
            min_max_distance = max_distance
            print("mmd:", min_max_distance)
            best_loc = n
            print("bsloc:", best_loc)

        # 기존 최단 거리로 복원
        original_distances[1:] = shortest_distances
        # print("od:", original_distances[1:])

    # 결과 출력
    print(best_loc)


def main():
    test_cases = int(input())
    for t in range(test_cases):
        input()
        find_intersection()
        if t < test_cases - 1:
            print()  # 테스트 케이스 사이에 빈 줄 출력


if __name__ == "__main__":
    main()

