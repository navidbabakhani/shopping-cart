package com.tw.shoppingcart.domain

private val discounts = mapOf(
    "normal" to 0.1,
    "good-offer" to 0.25,
    "super-offer" to 0.4,
)

data class ShoppingCart(
    val id: Long,
    val items: MutableMap<Long, CartItem>,
    var totalPrice: Long = 0,
    var priceAfterDiscount: Long = 0,
) {
    var coupon: String = ""

    fun computePrices() {
        totalPrice = 0
        items.values.forEach { totalPrice += it.price * it.qty }
        priceAfterDiscount = (totalPrice * (1 - (discounts[coupon] ?: 0.0))).toLong()
    }
}
