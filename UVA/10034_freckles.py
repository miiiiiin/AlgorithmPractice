# MST 핵심 이론:

# 1. 에지리스트로 그래프 구현
# 2. 유니온 파인드 리스트 초기화

# 3. 가중치가 낮은 에지부터 연결 시도 (사이클이 안생겨야 함 확인 필요!)
# 그래프 사이클 형성 유무를 find 연산으로 확인
# 사이클이 형성되지 않을 때만 union 연산으로 두 노드를 연결

# 4. 과정 3 반복 (노드의 수가 N이면 연결한 에지의 수가 N-1이 될 때까지 반복)

import math

# 두 점 간의 직선 거리를 계산하는 함수


def get_distance(point1, point2):
    # 좌표 차이의 제곱합
    return math.sqrt((point1[0] - point2[0]) ** 2 + (point1[1] - point2[1]) ** 2)


def kruskal(graph, n):
    # 가중치 낮은 기준 오름차순 정렬
    # x[2]는 u, v, w 중 w
    graph = sorted(graph, key=lambda x: x[2])

    parent = [i for i in range(n)]
    # print("parent:", parent)

    # 최소비용 신장 트리의 가중치 리스트
    mst = []

    def find(node):
        if parent[node] == node:
            return node
        else:
            parent[node] = find(parent[node])
        return parent[node]

    def union(x, y):
        # u가 속한 집합을 대표하는 정점 찾기
        root1 = find(x)
        root2 = find(y)

        if root1 != root2:
            parent[root2] = root1

    for edge in graph:
        # 시점과 종점의 대표 노드가 다르면
        print("edge:", find(edge[0]), find(edge[1]))
        if find(edge[0]) != find(edge[1]):
            union(edge[0], edge[1])

            # 최소비용 신장 트리의 가중치 합 계산
            mst.append(edge[2])

    # kruskal: [(0, 1, 1.4142135623730951), (1, 2, 2.0)]
    print("kruskal:", mst)
    return mst


def findMST(tcs):
    results = []
    for tc in tcs:

        total_distance = 0

        # 간선 정보 저장할 리스트
        graph = []
        n, coordinates = tc

        for i in range(n):
            x, y = coordinates[i]

            for j in range(i+1, n):
                # 다음 순서의 좌표와 비교
                x2, y2 = coordinates[j]
                # 정점 간의 거리
                distance = get_distance((x, y), (x2, y2))
                # 시작, 종점, 가중치
                graph.append((i, j, distance))
                # print("graph:", graph)

        # 최소 비용 신장트리 찾기
        minimum_spanning_tree = kruskal(graph, n)
        # print("results:", minimum_spanning_tree)

        # 최소 비용 신장트리의 가중치 합 계산
        for edge in minimum_spanning_tree:
            total_distance += edge

        results.append(total_distance)

    return results


def main():

    test_cases = int(input())
    # 튜플 입력받을 리스트
    tcs = []

    for _ in range(test_cases):
        input()
        # 주근깨의 개수
        n = int(input())

        # 좌표 입력 받기
        coordinates = [tuple(map(float, input().split()))
                       for _ in range(n)]
        tcs.append((n, coordinates))
        results = findMST(tcs)

        # print("results:", results)

        for result in results:
            print("{0:.2f}".format(result))
            print()

        if _ < test_cases - 1:
            print()


if __name__ == '__main__':
    main()

