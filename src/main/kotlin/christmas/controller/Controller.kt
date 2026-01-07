package christmas.controller

import christmas.Util
import christmas.model.Menus
import christmas.service.OrderService
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
        val day = Util.retryUntilValid {
            inputView.readVisitDate()
        }
        val orders = Util.retryUntilValid {
            inputView.readMenuAndCount(basicMenus)
        }
        outputView.printPreviewMent(day)

        // 2. 주문 메뉴 출력
        outputView.printOrders(orders)

        // 3. 할인 전 총주문 금액 출력
        val sumBeforeDiscount = outputView.printSumBeforeDiscount(orders)

        // 4. 증정 메뉴 출력
        val isGiftTarget = outputView.printGiftMenu(sumBeforeDiscount)

        // 5. 혜택 내역, 총혜택 금액 출력
        val service = OrderService(day)
        val christmasDiscount = service.christmasDiscount()
        val weekdayDiscount = service.weekdayDiscount(orders)
        val weekendDiscount = service.weekendDiscount(orders)
        val specialDiscount = service.specialDiscount()

        var totalBenefit = 0
        if (!service.isEventTarget(sumBeforeDiscount)) outputView.printBenefit(0, 0, 0, 0, isGiftTarget)
        if (service.isEventTarget(sumBeforeDiscount)) totalBenefit = outputView.printBenefit(christmasDiscount, weekdayDiscount, weekendDiscount, specialDiscount, isGiftTarget)

        // 6. 할인 후 예상 결제 금액 출력
        val totalPrice = sumBeforeDiscount - christmasDiscount - weekdayDiscount - weekendDiscount - specialDiscount
        outputView.printTotalPrice(totalPrice)

        // 7. 이벤트 배지 출력
        val eventBadge = service.getEventBadge(totalBenefit)
        outputView.printEventBadge(eventBadge)
    }
}
