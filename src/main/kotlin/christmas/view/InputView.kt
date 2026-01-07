package christmas.view

import camp.nextstep.edu.missionutils.Console
import christmas.model.Menu
import christmas.model.Order

class InputView {
    fun readVisitDate(): Int {
        println("12월 중 식당 예상 방문 날짜는 언제인가요? (숫자만 입력해 주세요!)")
        val input = getInput()
        val date = input.toIntOrNull()
            ?: throw IllegalArgumentException("[ERROR] 유효하지 않은 날짜입니다. 다시 입력해 주세요.")
        require(date in 1..31) { "[ERROR] 유효하지 않은 날짜입니다. 다시 입력해 주세요." }
        return date
    }

    fun readMenuAndCount(menus: List<Menu>): List<Order> {
        println("주문하실 메뉴를 메뉴와 개수를 알려 주세요. (e.g. 해산물파스타-2,레드와인-1,초코케이크-1)")
        val input = getInput()
        val data = input.split(",")
        val errorMessage = "[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요."
        var totalCount = 0
        val orders = mutableListOf<Order>()
        data.forEach { d ->
            val parts = d.split("-")
            require(parts.size == 2) { errorMessage }
            val menu = menus.find { it.name == parts[0].trim() }
                ?: throw IllegalArgumentException(errorMessage)
            val count = parts[1].trim().toIntOrNull()
                ?: throw IllegalArgumentException(errorMessage)
            orders.add(Order(menu, count))
            totalCount += count
        }
        if (totalCount > 20) throw IllegalArgumentException(errorMessage)
        return orders
    }

    private fun getInput(): String {
        return Console.readLine()
            ?: throw IllegalArgumentException("[ERROR] 입력값이 없습니다. 다시 입력해 주세요.")
    }
}
