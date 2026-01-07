package christmas

import christmas.controller.Controller
import christmas.view.InputView
import christmas.view.OutputView

fun main() {
    val inputView = InputView()
    val outputView = OutputView()
    val controller = Controller(inputView, outputView)

    controller.run()
}
