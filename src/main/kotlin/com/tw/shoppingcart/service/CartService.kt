package com.tw.shoppingcart.service

import com.tw.shoppingcart.domain.CartItem
import com.tw.shoppingcart.repository.ShoppingCartRepository
import org.springframework.stereotype.Service

@Service
class CartService(
    private val repository: ShoppingCartRepository,
) {
    suspend fun getCartItems() =
        repository.getCart().also { it.computePrices() }

    suspend fun addItems(items: List<CartItem>) =
        repository.addToCart(items = items).also { it.computePrices() }

    suspend fun reduceNumbersInCart(items: List<CartItem>) =
        repository.reduceItemsInCart(items = items).also { it.computePrices() }

    suspend fun removeItems(itemIds: List<Long>) =
        repository.removeFromCart(itemIds = itemIds).also { it.computePrices() }

    suspend fun removeAllItems() =
        repository.removeAll().also { it.computePrices() }

    suspend fun applyDiscount(discountCoupon: String) =
        repository.getCart()
            .apply { coupon = discountCoupon }
            .also { it.computePrices() }

    suspend fun removeDiscount() =
        applyDiscount("")
}
