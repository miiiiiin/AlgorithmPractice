func solution(_ s:String) -> [Int] {
    var answer: [Int] = []
    var charDict: [Character:Int] = [:]
    let charArr = s.map { $0 }
    var currIndex = 0
    
    for char in charArr {
        if charDict[char] == nil {
            answer.append(-1)
        } else {
            let index = currIndex - charDict[char]!
            answer.append(index)
        }
        
        // 기존에 앞에서 나온 charDict[char]에 현재 인덱스 넘버를 덮어씌움.
        charDict[char] = currIndex
        currIndex += 1
    }
    return answer
}
