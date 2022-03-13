package com.tw.shoppingcart.event

import java.time.LocalDateTime

data class CartChangedEvent(
    val itemId: Long?,
    val count: Int?,
    val type: CartEventType,
    val happenedAt: LocalDateTime,
)

enum class CartEventType {
    ITEM_ADDED,
    ITEM_REMOVED,
    ITEM_QUANTITY_CHANGED,
    CART_CLEARED,
}
