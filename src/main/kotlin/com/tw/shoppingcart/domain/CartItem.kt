package com.tw.shoppingcart.domain

data class CartItem(
    val id: Long,
    val name: String? = null,
    val price: Int = 0,
    val qty: Int,
)
