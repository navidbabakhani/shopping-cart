package com.tw.shoppingcart.web.model

import com.tw.shoppingcart.domain.CartItem

data class ShoppingCartItem(
    val id: Long,
    val displayName: String?,
    val price: Double?,
    var quantity: Int,
) {
    fun toCartItem() = CartItem(
        id = id,
        qty = quantity,
    )

    companion object {
        fun from(item: CartItem) = with(item) {
            ShoppingCartItem(
                id = id,
                displayName = name,
                price = price / 100.0,
                quantity = qty
            )
        }
    }
}
