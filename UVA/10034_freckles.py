# 간선의 가중치 설정: 각 주근깨를 정점으로 보고, 각 주근깨 사이의 거리를 가중치로 하는 간선을 만듭니다. 이때, 간선은 방향이 없는 것으로 가정합니다.

# 최소 비용 신장 트리(Minimum Spanning Tree) 구하기: 주어진 그래프에서 모든 정점을 연결하는 최소 비용 신장 트리를 구합니다. 이는 Kruskal 알고리즘이나 Prim 알고리즘 등을 사용하여 해결할 수 있습니다.

# 선분의 길이의 합 계산: 최소 비용 신장 트리를 이루는 간선들의 가중치 합을 구합니다. 이 값이 주어진 주근깨를 모두 연결하는데 필요한 선분의 길이 합
# 간선 숫자 : 노드 - 1

# 두 점 간의 거리를 계산하는 함수 (가중치)
# 좌표 차이의 제곱 합: (x_diff**2 + y_diff**2): 두 점 간의 x 좌표 차이와 y 좌표 차이의 제곱을 합한 값입니다. 이는 피타고라스 정리에서 나오는 대각선의 제곱 길이와 동일합니다.
# 제곱근 씌우기: **0.5: 앞서 계산한 제곱의 합에 0.5 제곱근을 씌워주는 것입니다. 이는 거리를 구하는 과정에서 제곱한 값을 다시 제곱근으로 되돌리는 것이며, 이로써 두 점 사이의 유클리드 거리를 얻을 수 있습니다.
# 따라서 (x_diff**2 + y_diff**2)**0.5는 피타고라스 정리를 활용하여 두 점 사이의 거리를 계산하는 수식으로, 좌표 평면 상에서 두 점 사이의 직선 거리를 나타냅
def calculate_distance(point1, point2):
    x_diff = point1[0] - point2[0]
    y_diff = point1[1] - point2[1]
    # print("diff:", point1, point2)

    # 좌표 차이의 제곱 합
    # squared_distance = x_diff**2 + y_diff**2
    # print("squ:", squared_distance)
    # 좌표 차이의 제곱 합의 제곱근을 계산하는 부분을 하나의 식으로 표
    distance = (x_diff**2 + y_diff**2)**0.5
    # print("d:", distance)
    return distance

# 두 정점이 속한 집합을 찾는 함수


def find(parent, i):
    # 자신의 부모 노드 찾는 것
    # a가 자신의 부모 노드값과 같으면 대표노드라는 뜻
    # if a == parent[a]:
    #     return a
    # else:
    #     # parent[a]의 값을 한단계 더 찾아감
    #     parent[a] = find(parent[a])  # 재귀 (union find에서 경로 압축을 위해 이런식으로 씀)
    #     # 시간 복잡도를 줄이기 위해 위처럼 씀
    #     return parent[a]

    if parent[i] == i:
        return i
    else:
        parent[i] = find(parent, parent[i])
        return parent[i]

# 두 집합을 합치는 함수


def union(parent, rank, x, y):
    # 부모 노드 찾기 (부모 노드끼리 비교)
    x = find(parent, x)
    y = find(parent, y)

    # a와 b가 다르면 : 연결해도 사이클이 발생하지 않음(아직 둘이 연결되어 있는 노드가 아니라는 뜻)
    # x가 속한 트리의 높이가 더 낮으므로 x의 부모를 y로 지정
    if rank[x] < rank[y]:
        # 한 쪽을 부모로 지정(union 연산)
        parent[x] = y
    elif rank[x] > rank[y]:
        parent[y] = x
    else:
        # 두 트리의 높이가 같다면 한 쪽을 부모로 지정하고 그 트리의 높이를 1 증가시킵니다.
        # 이는 두 트리 중 하나를 선택하여 부모로 지정하되, 높이가 같은 경우 한 트리의 높이를 증가시켜야 다음에 연결될 때 더 효율적인 트리가 구성
        parent[x] = y
        rank[y] += 1


def kruskal(graph, n):
    # 간선을 가중치를 기준으로 오름차순 정렬
    # 각 간선은 (정점1, 정점2, 가중치)의 형태로 표현
    graph = sorted(graph, key=lambda x: x[2])

    # 각 정점의 부모를 나타내는 배열 초기화
    parent = [i for i in range(n)]
    print("parent:", parent)
    # 각 집합의 높이(트리의 높이)를 나타내는 배열 초기화
    rank = [0] * n
    min_spanning_tree = []

    # 간선을 하나씩 확인하며 트리 구성
    # 그래프의 간선을 하나씩 확인하며, 해당 간선의 양 끝점이 속한 트리의 루트를 찾는 부분
    for edge in graph:
        u, v, weight = edge
        # find 함수를 사용하여 정점 u가 속한 트리의 루트를 찾아 root_u에 저장합니다.
        # 이는 u가 속한 집합을 대표하는 정점을 의미
        root_u = find(parent, u)
        # 정점 v가 속한 트리의 루트를 찾아 root_v에 저장
        root_v = find(parent, v)
        # print("edge:", u, v, weight)

        print("root:", root_u, root_v)
        # 사이클이 발생하지 않으면 간선을 트리에 추가
        if root_u != root_v:
            min_spanning_tree.append(edge)
            # 간선과 노드 합침
            union(parent, rank, root_u, root_v)

    print("kruskal:", min_spanning_tree)
    return min_spanning_tree


def findMST(tcs):
    results = []

    for tc in tcs:
        # 간선 정보 저장할 리스트
        graph = []
        n_freckles, coordinates = tc

        for i in range(n_freckles):
            print(coordinates)
            x, y = coordinates[i]

            for j in range(i+1, n_freckles):
                # 다음 순서의 좌표와 비교하기 위함
                x2, y2 = coordinates[j]

                print("next:", x2, y2)
                distance = calculate_distance((x, y), (x2, y2))
                print("distance:", distance)
                graph.append((i, j, distance))
                print("graph:", graph)
        # 최소 비용 신장 트리 찾기
        min_spanning_tree = kruskal(graph, n_freckles)

        # 최소 비용 신장 트리의 가중치 합 계산
        total = sum(edge[2] for edge in min_spanning_tree)
        print(total)
        results.append(round(total, 2))
        print("results:", results)
    return results


def main():
    tcs = []
    test_cases = int(input())

    for _ in range(test_cases):
        input()
        n_freckles = int(input())
        coordinates = [tuple(map(float, input().split()))
                       for _ in range(n_freckles)]
        tcs.append((n_freckles, coordinates))
        print("tcs:", tcs)

    # print(tcs)
    answers = findMST(tcs)
    for answer in answers:
        print("{0:.2f}".format(answer))
        print()


if __name__ == '__main__':
    main()

