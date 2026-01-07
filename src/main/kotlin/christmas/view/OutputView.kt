package christmas.view

import christmas.model.Order
import java.text.DecimalFormat

class OutputView {
    private val moneyFormat = DecimalFormat("#,###")

    fun printWelcome() = println("안녕하세요! 우테코 식당 12월 이벤트 플래너입니다.")
    fun printPreviewMent(date: Int) = println("12월 ${date}일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!")

    fun printOrders(orders: List<Order>) {
        print("\n<주문 메뉴>\n")
        orders.forEach { (menu, count) ->
            print("${menu.name} ${count}개\n")
        }
    }

    fun printSumBeforeDiscount(orders: List<Order>): Int {
        print("\n<할인 전 총주문 금액>\n")
        val totalPrice = orders.sumOf { it.menu.price * it.count }
        print("${moneyFormat.format(totalPrice)}원\n")
        return totalPrice
    }

    fun printGiftMenu(totalPrice: Int): Boolean {
        print("\n<증정 메뉴>\n")
        if (totalPrice >= 120000) {
            print("샴페인 1개\n")
            return true
        }
        print("없음\n")
        return false
    }

    fun printBenefit(
        christmasDiscount: Int,
        weekdayDiscount: Int,
        weekendDiscount: Int,
        specialDiscount: Int,
        isGiftTarget: Boolean
    ): Int {
        var totalBenefit = christmasDiscount + weekdayDiscount + weekendDiscount + specialDiscount
        if (isGiftTarget) totalBenefit += 25000

        print("\n<혜택 내역>\n")
        if (christmasDiscount != 0) print("크리스마스 디데이 할인: -${moneyFormat.format(christmasDiscount)}원\n")
        if (weekdayDiscount != 0) print("평일 할인: -${moneyFormat.format(weekdayDiscount)}원\n")
        if (weekendDiscount != 0) print("주말 할인: -${moneyFormat.format(weekendDiscount)}원\n")
        if (specialDiscount != 0) print("특별 할인: -${moneyFormat.format(specialDiscount)}원\n")
        if (isGiftTarget) print("증정 이벤트: -25,000원\n")
        if (totalBenefit == 0) print("없음\n")

        print("\n<총혜택 금액>\n")
        print("${moneyFormat.format(-totalBenefit)}원\n")
        return totalBenefit
    }

    fun printTotalPrice(price: Int) {
        print("\n<할인 후 예상 결제 금액>\n")
        print("${moneyFormat.format(price)}원\n")
    }

    fun printEventBadge(badge: String) {
        print("\n<12월 이벤트 배지>\n")
        print(badge)
    }
}
