import Foundation

func solution(_ phone_number:String) -> String {
    var answer: String = ""
    answer = phone_number.enumerated().map { index, char -> String in
            let mask = Array(phone_number.count - 4 ..< phone_number.count).contains(index) ? char : "*"
            return String(mask)
        }.joined()
    return answer
}
