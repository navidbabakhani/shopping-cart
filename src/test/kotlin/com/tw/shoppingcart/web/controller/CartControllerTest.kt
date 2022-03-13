package com.tw.shoppingcart.web.controller

import com.ninjasquad.springmockk.MockkBean
import com.tw.shoppingcart.CART
import com.tw.shoppingcart.service.CartService
import io.mockk.coEvery
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest
import org.springframework.test.web.reactive.server.WebTestClient

@WebFluxTest(CartController::class)
internal class CartControllerTest {

    @Autowired
    lateinit var client: WebTestClient

    @MockkBean
    lateinit var cartService: CartService

    @Test
    fun getCart() {
        coEvery { cartService.getCartItems() } returns CART

        client.get()
            .uri("/api/cart")
            .exchange()
            .expectStatus().isOk
            .expectBody()
            .json(
                """{"meta": {"pagination": {}},
                |"data": {"totalPrice": 2.4,
                |"priceAfterDiscount": 2.4,
                |"items": [{"id": 1,"displayName": "Pen","price": 1.2,"quantity": 2}]}}
                """.trimMargin()
            )
    }
}
