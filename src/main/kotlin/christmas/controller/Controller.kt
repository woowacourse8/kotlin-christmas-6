package christmas.controller

import christmas.Util
import christmas.model.Menus
import christmas.view.InputView
import christmas.view.OutputView

class Controller(
    private val inputView: InputView,
    private val outputView: OutputView
) {
    fun run() {
        // 기본 메뉴판 로드
        val menus = Menus()
        val basicMenus = menus.findAll()

        // 1. 날짜와 메뉴/개수 입력받기
        outputView.printWelcome()
        val date = Util.retryUntilValid {
            inputView.readVisitDate()
        }
        val orders = Util.retryUntilValid {
            inputView.readMenuAndCount(basicMenus)
        }
        outputView.printPreviewMent()

        // 2. 주문 메뉴 출력
        outputView.printOrders(orders)

        // 3. 할인 전 총주문 금액 출력
        val sumBeforeDiscount = outputView.printSumBeforeDiscount(orders)
    }
}
