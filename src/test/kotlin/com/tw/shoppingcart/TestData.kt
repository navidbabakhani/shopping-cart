package com.tw.shoppingcart

import com.tw.shoppingcart.domain.CartItem
import com.tw.shoppingcart.domain.ShoppingCart

val CART = ShoppingCart(
    id = 1,
    items = mutableMapOf(1.toLong() to CartItem(1, "Pen", 120, 2)),
    totalPrice = 240,
    priceAfterDiscount = 240
)
