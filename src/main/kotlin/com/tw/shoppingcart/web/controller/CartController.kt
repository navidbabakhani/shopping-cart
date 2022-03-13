package com.tw.shoppingcart.web.controller

import com.tw.shoppingcart.domain.ShoppingCart
import com.tw.shoppingcart.service.CartService
import com.tw.shoppingcart.web.model.CommonResponse
import com.tw.shoppingcart.web.model.CommonResponseData
import com.tw.shoppingcart.web.model.ShoppingCartItem
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/cart")
class CartController(
    val service: CartService,
) {
    @GetMapping
    suspend fun getCart(): CommonResponse =
        service.getCartItems().toResponseDto()

    @PostMapping
    suspend fun addItems(@RequestBody items: List<ShoppingCartItem>) =
        service.addItems(items.map { it.toCartItem() }).toResponseDto()

    @PatchMapping
    suspend fun reduceItemNumbers(@RequestBody items: List<ShoppingCartItem>) =
        service.reduceNumbersInCart(items.map { it.toCartItem() }).toResponseDto()

    @DeleteMapping
    suspend fun deleteItems(@RequestParam itemIds: List<Long>) =
        service.removeItems(itemIds).toResponseDto()

    @DeleteMapping("/all")
    suspend fun deleteAllItems() =
        service.removeAllItems().toResponseDto()

    @GetMapping("/discount")
    suspend fun getDiscount(@RequestParam discountCoupon: String) =
        service.applyDiscount(discountCoupon).toResponseDto()

    @DeleteMapping("/discount")
    suspend fun removeDiscount() =
        service.removeDiscount().toResponseDto()

    private fun ShoppingCart.toResponseDto() = CommonResponseData(
        items = items.values.map { ShoppingCartItem.from(it) },
        totalPrice = totalPrice / 100.0,
        priceAfterDiscount = priceAfterDiscount / 100.0
    ).wrap()
}
