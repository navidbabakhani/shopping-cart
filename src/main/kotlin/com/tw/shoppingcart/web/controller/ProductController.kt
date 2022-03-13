package com.tw.shoppingcart.web.controller

import com.tw.shoppingcart.service.ProductService
import com.tw.shoppingcart.web.model.CommonResponseData
import com.tw.shoppingcart.web.model.ProductDto
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api")
class ProductController(
    val service: ProductService,
) {

    @GetMapping("/product")
    suspend fun getProducts() = CommonResponseData(
        products = service.getProducts().map { ProductDto.from(it) }
    ).wrap()
}
