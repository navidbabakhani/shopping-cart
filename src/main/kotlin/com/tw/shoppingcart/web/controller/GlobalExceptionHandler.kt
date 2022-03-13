package com.tw.shoppingcart.web.controller

import com.tw.shoppingcart.exception.ShoppingCartNotFound
import com.tw.shoppingcart.web.model.ErrorResponse
import mu.KLogging
import org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR
import org.springframework.http.HttpStatus.NOT_FOUND
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class GlobalExceptionHandler {

    @ExceptionHandler(Throwable::class)
    fun handleThrowable(ex: Throwable): ResponseEntity<Any> {
        logger.error(ex) { "Throwable" }
        return createResponseEntityWithStatus(INTERNAL_SERVER_ERROR.value(), ex.message)
    }

    @ExceptionHandler(ShoppingCartNotFound::class)
    fun handleShoppingCartNotFound(ex: ShoppingCartNotFound): ResponseEntity<Any> {
        logger.error(ex) { "ShoppingCartNotFound" }
        return createResponseEntityWithStatus(NOT_FOUND.value(), ex.message)
    }

    fun createResponseEntityWithStatus(status: Int, msg: String? = null) =
        ResponseEntity.status(status).body(
            ErrorResponse(
                serviceName = "shopping-cart",
                errorCode = status,
                errorMessage = msg ?: "N/A",
            ) as Any
        )

    companion object : KLogging()
}
