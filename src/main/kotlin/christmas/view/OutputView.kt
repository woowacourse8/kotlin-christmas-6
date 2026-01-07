package christmas.view

import christmas.dto.BenefitResult
import christmas.model.Order
import java.text.DecimalFormat

class OutputView {
    private val moneyFormat = DecimalFormat("#,###")

    fun printWelcome() = println("안녕하세요! 우테코 식당 12월 이벤트 플래너입니다.")
    fun printPreviewMent(date: Int) = println("12월 ${date}일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!")

    fun printOrders(orders: List<Order>) {
        println("\n<주문 메뉴>")
        orders.forEach { println("${it.menu.name} ${it.count}개") }
    }

    fun printSumBeforeDiscount(amount: Int) {
        println("\n<할인 전 총주문 금액>")
        println("${moneyFormat.format(amount)}원")
    }

    fun printGiftMenu(isGiftTarget: Boolean) {
        println("\n<증정 메뉴>")
        if (isGiftTarget) println("샴페인 1개") else println("없음")
    }

    fun printBenefitDetails(result: BenefitResult) {
        println("\n<혜택 내역>")
        if (result.totalBenefitAmount == 0) {
            println("없음")
            return
        }

        printDiscount("크리스마스 디데이 할인",result.christmasDiscount)
        printDiscount("평일 할인", result.weekdayDiscount)
        printDiscount("주말 할인", result.weekendDiscount)
        printDiscount("특별 할인", result.specialDiscount)
        if (result.isGiftTarget) println("증정 이벤트: -25,000원")
    }

    private fun printDiscount(title: String, amount: Int) {
        if (amount > 0)
            println("$title: -${moneyFormat.format(amount)}원")
    }

    fun printTotalBenefitAmount(amount: Int) {
        println("\n<총혜택 금액>")
        println("${moneyFormat.format(-amount)}원")
    }

    fun printExpectedPayment(amount: Int) {
        println("\n<할인 후 예상 결제 금액>")
        println("${moneyFormat.format(amount)}원")
    }

    fun printEventBadge(badge: String) {
        println("\n<12월 이벤트 배지>")
        println(badge)
    }
}
