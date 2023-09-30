# 테스트 케이스의 수 : number_of_test_case
# 총 일수: N
# 정당의 수 : P
# 정당 P개의 파업주기 파라미터: hartal_parameters
# 근무일 손실 수를 저장할 변수: lost_working_days => 초기화 필요

# 테스트 케이스 수 입력
number_of_test_case = int(input())

for _ in range(number_of_test_case):
    # 일 수 입력
    N = int(input())
    # 정당 갯수 입력
    P = int(input())

    # p개의 정당 각 파업 주기 배열  
    hartal_parameters = [int(input()) for _ in range(P)]
    print(hartal_parameters)

    # 근무일 손실 수 초기화
    lost_working_days = 0

    # N일 동안 반복
    for day in range(1, N+1):
        # 금요일(6) 또는 토요일(7)인 경우는 제외하고 나머지 요일들만
        # print(day, day % 7)
        if day % 7 != 6 and day % 7 != 0:
            # 각 정당의 파업 주기 확인
            hartal_occurred = False
            # print(hartal_occurred)
            # 모든 정당의 hartal parameter에 대해 검사
            for parameter in hartal_parameters:
                # print(day, parameter, day % parameter)
                # 일 수를 파라미터로 나눈 나머지가 0이면 파업이 있는 날
                if day % parameter == 0:
                    hartal_occurred = True
                    print("hartal_occurred:",hartal_occurred)
                    break # 정당 중 하나에서 파업이 시작했으므로 중단

            if hartal_occurred:
                lost_working_days += 1
                # print(lost_working_days)

    # 결과
    print(lost_working_days) 
