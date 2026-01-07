package christmas.dto

data class BenefitResult(
    val christmasDiscount: Int,
    val weekdayDiscount: Int,
    val weekendDiscount: Int,
    val specialDiscount: Int,
    val isGiftTarget: Boolean,
    val totalBenefitAmount: Int,
    val expectedPaymentAmount: Int,
    val eventBadge: String
)