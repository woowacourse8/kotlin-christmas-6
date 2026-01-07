package christmas.controller

import christmas.view.InputView
import christmas.view.OutputView

class Controller(
    private val inputView: InputView,
    private val outputView: OutputView,

) {
    fun run() {
        // 1. 날짜와 메뉴/개수 입력받기
        outputView.printWelcome()
        val date = inputView.readVisitDate()
    }
}
