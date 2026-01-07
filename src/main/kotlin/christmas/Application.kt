package christmas

import christmas.controller.Controller
import christmas.service.OrderService
import christmas.view.InputView
import christmas.view.OutputView

fun main() {
    val inputView = InputView()
    val outputView = OutputView()
    val orderService = OrderService()
    val controller = Controller(inputView, outputView)

    controller.run()
}
