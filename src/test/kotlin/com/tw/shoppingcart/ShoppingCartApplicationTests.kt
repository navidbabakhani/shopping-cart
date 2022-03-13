package com.tw.shoppingcart

import com.tw.shoppingcart.repository.ShoppingCartRepository
import com.tw.shoppingcart.service.CartService
import com.tw.shoppingcart.web.controller.CartController
import com.tw.shoppingcart.web.controller.ProductController
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class ShoppingCartApplicationTests {

    @Autowired
    lateinit var cartController: CartController

    @Autowired
    lateinit var productController: ProductController

    @Autowired
    lateinit var cartService: CartService

    @Autowired
    lateinit var cartRepository: ShoppingCartRepository

    @Test
    fun contextLoads() {
        Assertions.assertNotNull(cartController)
        Assertions.assertNotNull(productController)
        Assertions.assertNotNull(cartService)
        Assertions.assertNotNull(cartRepository)
    }
}
