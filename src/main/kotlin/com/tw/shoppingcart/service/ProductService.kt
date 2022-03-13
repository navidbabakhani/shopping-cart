package com.tw.shoppingcart.service

import com.tw.shoppingcart.repository.ShoppingCartRepository
import org.springframework.stereotype.Service

@Service
class ProductService(
    val repository: ShoppingCartRepository,
) {

    suspend fun getProducts() =
        repository.getProducts()
}
