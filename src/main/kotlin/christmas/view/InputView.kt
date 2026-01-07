package christmas.view

import camp.nextstep.edu.missionutils.Console

class InputView {
    fun readVisitDate(): Int {
        println("12월 중 식당 예상 방문 날짜는 언제인가요? (숫자만 입력해 주세요!)")
        val input = getInput()
        val date = input.toIntOrNull()
            ?: throw IllegalArgumentException("[ERROR] 유효하지 않은 날짜입니다. 다시 입력해 주세요.")
        require(date in 1..31) { "[ERROR] 유효하지 않은 날짜입니다. 다시 입력해 주세요." }
        return date
    }

    private fun getInput(): String {
        return Console.readLine()
            ?: throw IllegalArgumentException("[ERROR] 입력값이 없습니다. 다시 입력해 주세요.")
    }
}
