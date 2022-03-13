package com.tw.shoppingcart.web.model

data class ErrorResponse(
    val serviceName: String,
    val errorCode: Int,
    val errorMessage: String,
)
