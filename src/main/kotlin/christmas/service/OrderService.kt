package christmas.service

import christmas.model.MenuType
import christmas.model.Order
import java.time.LocalDate

class OrderService(val day: Int) {
    private val dayOfWeek = LocalDate.of(2023, 12, day).dayOfWeek.value

    fun christmasDiscount(): Int {
        if (!isChristmasDiscountTarget()) return 0
        var discount = 1000
        repeat(day) {
            discount += 100
        }
        return discount
    }

    fun weekdayDiscount(orders: List<Order>): Int {
        if (!isWeekDay()) return 0

        val dessertList = orders.filter {
            it.menu.menuType == MenuType.DESSERT
        }
        val count = dessertList.sumOf { it.count }
        return 2023 * count
    }

    fun weekendDiscount(orders: List<Order>): Int {
        if (isWeekDay()) return 0

        val mainList = orders.filter {
            it.menu.menuType == MenuType.MAIN
        }
        val count = mainList.sumOf { it.count }
        return 2023 * count
    }

    fun specialDiscount(): Int {
        if (dayOfWeek == 7 || day == 25) return 1000
        return 0
    }

    fun getEventBadge(totalBenefit: Int): String {
        if (totalBenefit >= 20000) return "산타"
        if (totalBenefit >= 10000) return "트리"
        if (totalBenefit >= 5000) return "별"
        return "없음"
    }

    private fun isWeekDay(): Boolean {
        return when (dayOfWeek) {
            5, 6 -> false
            else -> true
        }
    }

    private fun isChristmasDiscountTarget(): Boolean {
        return day in 1..25
    }

    fun isEventTarget(price: Int): Boolean {
        return price >= 10000
    }
}
