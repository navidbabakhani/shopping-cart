package com.tw.shoppingcart.web.model

import com.tw.shoppingcart.domain.Product

data class ProductDto(
    val id: Long,
    val displayName: String,
    val price: Int,
) {
    companion object {
        fun from(product: Product) = with(product) {
            ProductDto(
                id = id,
                displayName = name,
                price = price,
            )
        }
    }
}
