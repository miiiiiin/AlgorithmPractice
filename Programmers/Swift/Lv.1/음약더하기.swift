import Foundation

func solution(_ absolutes:[Int], _ signs:[Bool]) -> Int {
        var answer = 0
        for i in 0..<absolutes.count {
            if signs[i] == false {
                answer -= absolutes[i]
            } else {
                answer += absolutes[i]
            }
        }
        return answer
    }




// 축약한 풀이

 func solution(_ absolutes:[Int], _ signs:[Bool]) -> Int {
    return zip(absolutes, signs)
        .map { $1 ? $0 : -$0 }
        .reduce(0, +)
}



func solution(_ absolutes:[Int], _ signs:[Bool]) -> Int {
    var answer = 0
    answer = (0..<absolutes.count).map { signs[$0] ? absolutes[$0] : -absolutes[$0]}.reduce(0, +)
    return answer
}
