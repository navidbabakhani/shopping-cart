package com.tw.shoppingcart.web.model

data class CommonResponse(
    val meta: CommonResponseMeta = CommonResponseMeta(Pagination(null)),
    val data: CommonResponseData,
)

data class CommonResponseMeta(
    val pagination: Pagination,
)

data class Pagination(val nextToken: String?)

data class CommonResponseData(
    val totalPrice: Double? = null,
    val priceAfterDiscount: Double? = null,
    val items: List<ShoppingCartItem>? = null,
    val products: List<ProductDto>? = null,
) {

    fun wrap(
        pagination: Pagination = Pagination(null),
    ) = CommonResponse(meta = CommonResponseMeta(pagination), data = this)
}
