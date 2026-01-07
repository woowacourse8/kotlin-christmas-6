package christmas.controller

import christmas.Util
import christmas.dto.BenefitResult
import christmas.model.Menu
import christmas.model.Menus
import christmas.model.Order
import christmas.service.OrderService
import christmas.view.InputView
import christmas.view.OutputView

class Controller(
    private val inputView: InputView,
    private val outputView: OutputView
) {
    fun run() {
        val menus = Menus()
        val basicMenus = menus.findAll()

        outputView.printWelcome()
        val day = getVisitDate()
        val orders = getOrders(basicMenus)
        outputView.printPreviewMent(day)

        processOrder(day, orders)
    }

    private fun processOrder(day: Int, orders: List<Order>) {
        val totalOrderAmount = orders.sumOf { it.menu.price * it.count }

        outputView.printOrders(orders)
        outputView.printSumBeforeDiscount(totalOrderAmount)

        val service = OrderService(day)
        val benefitResult = service.calculateBenefits(orders, totalOrderAmount)

        printResult(benefitResult)
    }

    private fun printResult(result: BenefitResult) {
        outputView.printGiftMenu(result.isGiftTarget)
        outputView.printBenefitDetails(result)
        outputView.printTotalBenefitAmount(result.totalBenefitAmount)
        outputView.printExpectedPayment(result.expectedPaymentAmount)
        outputView.printEventBadge(result.eventBadge)
    }

    private fun getVisitDate(): Int {
        return Util.retryUntilValid { inputView.readVisitDate() }
    }

    private fun getOrders(basicMenus: List<Menu>): List<Order> {
        return Util.retryUntilValid { inputView.readMenuAndCount(basicMenus) }
    }
}
