package christmas.service

import christmas.dto.BenefitResult
import christmas.model.MenuType
import christmas.model.Order
import java.time.LocalDate

class OrderService(private val visitDate: Int) {
    private val visitDateObj = LocalDate.of(EVENT_YEAR, EVENT_MONTH, visitDate)
    private val dayOfWeek = visitDateObj.dayOfWeek.value

    fun calculateBenefits(orders: List<Order>, totalOrderAmount: Int): BenefitResult {
        if (totalOrderAmount < MIN_EVENT_AMOUNT) {
            return createEmptyBenefit(totalOrderAmount)
        }

        val christmasDiscount = getChristmasDiscount()
        val weekdayDiscount = getWeekdayDiscount(orders)
        val weekendDiscount = getWeekendDiscount(orders)
        val specialDiscount = getSpecialDiscount()
        val isGiftTarget = totalOrderAmount >= MIN_GIFT_AMOUNT

        val totalDiscount = christmasDiscount + weekdayDiscount + weekendDiscount + specialDiscount
        val totalBenefitAmount = totalDiscount + if (isGiftTarget) GIFT_PRICE else 0
        val expectedPayment = totalOrderAmount - totalDiscount

        return BenefitResult(
            christmasDiscount,
            weekdayDiscount,
            weekendDiscount,
            specialDiscount,
            isGiftTarget,
            totalBenefitAmount,
            expectedPayment,
            getEventBadge(totalBenefitAmount)
        )
    }

    private fun createEmptyBenefit(totalOrderAmount: Int) = BenefitResult(
        0, 0, 0, 0, false, 0, totalOrderAmount, "없음"
    )

    private fun getChristmasDiscount(): Int {
        if (visitDate !in CHRISTMAS_START..CHRISTMAS_END) return 0
        return CHRISTMAS_BASE_DISCOUNT + (visitDate - 1) * CHRISTMAS_DAILY_DISCOUNT
    }

    private fun getWeekdayDiscount(orders: List<Order>): Int {
        if (!isWeekDay()) return 0
        val dessertCount = orders.filter { it.menu.menuType == MenuType.DESSERT }.sumOf { it.count }
        return dessertCount * WEEK_DISCOUNT_AMOUNT
    }

    private fun getWeekendDiscount(orders: List<Order>): Int {
        if (isWeekDay()) return 0
        val mainCount = orders.filter {it.menu.menuType == MenuType.MAIN}.sumOf { it.count }
        return mainCount * WEEK_DISCOUNT_AMOUNT
    }

    private fun getSpecialDiscount(): Int {
        if (dayOfWeek == 7 || visitDate == 25) return SPECIAL_DISCOUNT_AMOUNT
        return 0
    }

    private fun getEventBadge(totalBenefit: Int): String {
        return when {
            totalBenefit >= BADGE_SANTA_THRESHOLD -> "산타"
            totalBenefit >= BADGE_TREE_THRESHOLD -> "트리"
            totalBenefit >= BADGE_STAR_THRESHOLD -> "별"
            else -> "없음"
        }
    }

    private fun isWeekDay(): Boolean {
        return when (dayOfWeek) {
            5, 6 -> false
            else -> true
        }
    }

    companion object {
        private const val EVENT_YEAR = 2023
        private const val EVENT_MONTH = 12
        private const val MIN_EVENT_AMOUNT = 10000
        private const val MIN_GIFT_AMOUNT = 120000
        private const val GIFT_PRICE = 25000

        private const val CHRISTMAS_START = 1
        private const val CHRISTMAS_END = 25
        private const val CHRISTMAS_BASE_DISCOUNT = 1000
        private const val CHRISTMAS_DAILY_DISCOUNT = 100

        private const val WEEK_DISCOUNT_AMOUNT = 2023
        private const val SPECIAL_DISCOUNT_AMOUNT = 1000

        private const val BADGE_SANTA_THRESHOLD = 20000
        private const val BADGE_TREE_THRESHOLD = 10000
        private const val BADGE_STAR_THRESHOLD = 5000
    }
}
