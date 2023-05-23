import Foundation

/**
 array의 각 element 중 divisor로 나누어 떨어지는 값을 오름차순으로 정렬한 배열을 반환하는 함수 solution을 작성하라.
 divisor로 나누어 떨어지는 element가 하나도 없다면 배열에 -1을 담아 반환하라.
 
 입출력 예시
 arr    divisor    return
 [5, 9, 7, 10]    5    [5, 10]
 [2, 36, 1, 3]    1    [1, 2, 3, 36]
 [3,2,6]    10    [-1]
 */

func solution(_ arr:[Int], _ divisor:Int) -> [Int] {
    var answer: [Int] = []
    for i in 0..<arr.count {
        if arr[i]%divisor == 0 {
            answer.append(arr[i])
        }
        if answer.count == 0 {
            answer.append(-1)
        }
    }
}

// 축약시킨 코드
func solution(_ arr:[Int], _ divisor:Int) -> [Int] {
    let answer = arr.filter { $0%divisor == 0 }.sorted()
    return answer.isEmpty ? [-1] : answer
}
