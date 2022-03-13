package com.tw.shoppingcart.exception

class ShoppingCartNotFound(id: Long) : RuntimeException("Shopping cart with id: $id is not found")
