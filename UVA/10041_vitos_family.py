# 부분 정렬
# heap 정렬 알고리즘 직접 구현하는 방법도 있음

import statistics
# 파이썬에서는 nth_element와 같은 기능을 직접 제공하는 내장 함수가 없습니다. 하지만 statistics 모듈에서 중앙값(median)을 구하는 함수 median()를 사용하여 중앙값을 계산
# 중앙값은 중간에 위치한 값 또는 중간 두 값의 평균
# 이 코드는 2와 4의 중간값을 계산하여 3.0을 출력할 것입니다. 만약 정수 결과를 원한다면 결과를 정수로 변환할 수 있습니다:

def read_input():
    # 테스트케이스 처리
    test_cases = int(input())

    for _ in range(test_cases):
        # 친척집 번지수
        relatives = list(map(int, input().split()))
       
        # 친척 수
        relatives_count = relatives[0]
        # 각 친척의 번지수
        addr = relatives[1:] 
        # 번지수 오름차순 정렬
        addr.sort()

        # 친척집과 중간 위치 사이 거리차 총합
        total_distance = 0

        # 중간값
        median = statistics.median(addr)
        print("median:", median)

        for a in addr:
            # print("번지:", a, a - median, abs(a - median))
            # 거리차 절대값 합하기
            total_distance += abs(a - median)
            print(total_distance)

if __name__ == "__main__":
    read_input()

