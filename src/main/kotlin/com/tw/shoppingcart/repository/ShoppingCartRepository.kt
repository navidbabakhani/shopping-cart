package com.tw.shoppingcart.repository

import com.tw.shoppingcart.domain.CartItem
import com.tw.shoppingcart.domain.Product
import com.tw.shoppingcart.domain.ShoppingCart

interface ShoppingCartRepository {

    suspend fun addToCart(id: Long = 1, items: List<CartItem>): ShoppingCart

    suspend fun reduceItemsInCart(id: Long = 1, items: List<CartItem>): ShoppingCart

    suspend fun removeFromCart(id: Long = 1, itemIds: List<Long>): ShoppingCart

    suspend fun removeAll(id: Long = 1): ShoppingCart

    suspend fun getCart(id: Long = 1): ShoppingCart

    suspend fun getProducts(): List<Product>
}
