package christmas.view

import camp.nextstep.edu.missionutils.Console
import christmas.model.Menu
import christmas.model.MenuType
import christmas.model.Order

class InputView {
    fun readVisitDate(): Int {
        println("12월 중 식당 예상 방문 날짜는 언제인가요? (숫자만 입력해 주세요!)")
        val input = getInput()
        val date = input.toIntOrNull() ?: throw IllegalArgumentException("[ERROR] 유효하지 않은 날짜입니다. 다시 입력해 주세요.")
        require(date in 1..31) { "[ERROR] 유효하지 않은 날짜입니다. 다시 입력해 주세요." }
        return date
    }

    fun readMenuAndCount(menus: List<Menu>): List<Order> {
        println("주문하실 메뉴를 메뉴와 개수를 알려 주세요. (e.g. 해산물파스타-2,레드와인-1,초코케이크-1)")
        val input = getInput()
        val orders = input.split(",").map { parseToOrder(it, menus) }

        checkOrderValidation(orders)
        return orders
    }

    private fun parseToOrder(input: String, menus: List<Menu>): Order {
        val parts = input.split("-")
        require(parts.size == 2) { ORDER_ERROR }

        val menuName = parts[0].trim()
        val count = parts[1].trim().toIntOrNull() ?: throw IllegalArgumentException(ORDER_ERROR)
        require(count >= 1) { ORDER_ERROR }

        val menu = menus.find { it.name == menuName } ?: throw IllegalArgumentException(ORDER_ERROR)

        return Order(menu, count)
    }

    private fun checkOrderValidation(orders: List<Order>) {
        val totalCount = orders.sumOf { it.count }
        if (totalCount > MAX_ORDER_COUNT) throw IllegalArgumentException(ORDER_ERROR)

        val isAllDrink = orders.all { it.menu.menuType == MenuType.DRINK }
        if (isAllDrink) throw IllegalArgumentException(ORDER_ERROR)


        val uniqueMenus = orders.map { it.menu.name }.distinct()
        if (uniqueMenus.size != orders.size) throw IllegalArgumentException(ORDER_ERROR)
    }

    private fun getInput(): String {
        return Console.readLine().trim()
            .also { if (it.isBlank()) throw IllegalArgumentException("[ERROR] 입력값이 없습니다. 다시 입력해 주세요.") }
    }

    companion object {
        const val ORDER_ERROR = "[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요."
        private const val MAX_ORDER_COUNT = 20
    }
}
