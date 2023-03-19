import Foundation


func solution(_ x:Int, _ n:Int) -> [Int64] {
    var answer: [Int64] = []
    for _ in 0..<n { answer.endIndex == 0 ? answer.append(Int64(answer.endIndex + x)) : answer.append((answer.last ?? Int64(answer.endIndex)) + Int64(x)) }
    return answer
}
