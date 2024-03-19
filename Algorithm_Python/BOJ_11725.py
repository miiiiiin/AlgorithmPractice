import sys

input = sys. stdin.readline

# 재귀 사용 시 setrecursivelimit 해주는게 좋음
sys.setrecursionlimit(10**6)


N = int(input())
visited = [False] * (N+1)
tree = [[] for _ in range(N+1)]
# 부모 노드 저장 배열
answers = [0] * (N+1)

for _ in range(1, N):  # 트리를 인접리스트로 저장
    n1, n2 = list(map(int, input().split()))
    tree[n1].append(n2)
    tree[n2].append(n1)
    # print("tree:", tree)


# DFS
def DFS(num):
    # 방문 노드 표시
    visited[num] = True
    # tree의 현재 노드와 연결되어 있는 다른 노드들 순회
    print("before:", num, tree)
    for i in tree[num]:
        print("for:", i, tree[num])
        # 아직 방문하지 않은 것만 재귀적으로 이동
        if not visited[i]:
            # 다음 노드로 가기 전 부모 노드(현재 번호) 저장
            print("i:", i, tree[num])
            answers[i] = num
            DFS(i)


# 문제에서 루트 노드를 1로 정해줬으므로 1번부터 실행
DFS(1)

# 2번부터 출력해주기로 함
for i in range(2, N+1):
    print(answers[i])

