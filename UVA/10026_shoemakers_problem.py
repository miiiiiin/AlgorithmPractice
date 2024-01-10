import functools

# 두 개의 주문을 비교하여 어느 주문을 먼저 처리할지 결정하는 데 사용하는 함수
# order1과 order2는 각각 주문의 정보를 나타내는 튜플


def compare_orders(order1, order2):
    # 시간, 지연 벌금, 인덱스
    print("order check:", order1)
    t1, s1, i1 = order1
    t2, s2, i2 = order2

    # 지연 벌금 서로 곱해서 비교하여 둘 중 낮은 값을 우선순위로 함
    cost1 = t1 * s2  # 주문 1을 먼저 처리했을 때 발생하는 벌금
    cost2 = t2 * s1  # 주문 2를 먼저 처리했을 때 발생하는 벌금

    # 만약 두 주문의 지연 벌금이 같다면 테스트 케이스 상 먼저 나온 주문이 앞서야 함에 주의
    # 예. T1 * S2 = T2 * S1라면, 주문 1부터 할 것

    print("order1:", t1, s1, i1)
    print("order2:", t2, s2, i2)
    print("cost cmp:", cost1, cost2)

    # 두개의 task 중 다른 주문으로 인한 지연 벌금이 낮은 쪽을 먼저 처리해야함
    # -1: 주문 1이 먼저 처리되어야 하는 경우
    # 1: 주문 2가 먼저 처리되어야 하는 경우
    # 0: 벌금이 같을 때 주문의 순서를 결정하기 위한 비교

    if cost1 < cost2:
        return -1  # 인덱스 1씩 앞으로 이동
    # elif cost1 > cost2:
    #     return 1

    # elif i1 < i2:
    #     return -1

    else:
        return 1


def find_priority(works):
    # 이 코드는 order 리스트의 각 요소는 (소요시간, 벌금) 형태의 튜플로 되어있다고 가정하며, key 매개변수를 사용하여 정렬 기준을 설정합니다. lambda x: x[1] 부분은 벌금을 기준으로 정렬하도록 설정한 것
    # 벌금 최소 기준 작업 정렬
    # works.sort(key=lambda x: x[1])

    # sorted_order는 compare_orders 함수를 사용하여 주문을 정렬한 리스트입니다.
    # 이 리스트는 최소 벌금을 가진 순서로 정렬됨
    sorted_order = sorted(works, key=functools.cmp_to_key(compare_orders))
    return [order[2] for order in sorted_order]


def read_input():
    test_cases = int(input())
    # 빈 줄 무시
    input()

    for t in range(0, test_cases):

        # 총 주문 처리 수
        orders = int(input())
        works = []

        # 작업 정보 입력
        for i in range(orders):
            # 각 주문의 필요 작업시간, 일일 지연 벌금
            # times, penalty = list(map(int, input().split()))
            # works.append((times, penalty))

            task = list(map(int, input().split()))
            # works에 튜플 형태로 (필요 작업시간, 일일 지연 벌금, 인덱스) 추가
            works.append((task[0], task[1], i + 1))

        priority_work = find_priority(works)
        # print(priority_work)

        # 작업 순서를 출력
        print(" ".join(map(str, priority_work)))

        if t < test_cases - 1:
            print()  # 테스트 케이스 사이에 빈 줄 출력
            input()


if __name__ == "__main__":
    read_input()

