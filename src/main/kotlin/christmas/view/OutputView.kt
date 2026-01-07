package christmas.view

import christmas.model.Order
import java.text.DecimalFormat

class OutputView {
    private val moneyFormat = DecimalFormat("#,###")

    fun printWelcome() = println("안녕하세요! 우테코 식당 12월 이벤트 플래너입니다.")
    fun printPreviewMent() = println("12월 26일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!\n")

    fun printOrders(orders: List<Order>) {
        print("<주문 메뉴>\n")
        orders.forEach { (menu, count) ->
            print("${menu.name} ${count}개\n")
        }
    }

    fun printSumBeforeDiscount(orders: List<Order>): Int {
        print("\n<할인 전 총주문 금액>\n")
        val totalPrice = orders.sumOf { it.menu.price * it.count }
        print("${moneyFormat.format(totalPrice)}원")
        return totalPrice
    }
}
