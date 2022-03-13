package com.tw.shoppingcart.repository

import com.tw.shoppingcart.domain.CartItem
import com.tw.shoppingcart.domain.Product
import com.tw.shoppingcart.domain.ShoppingCart
import com.tw.shoppingcart.event.CartChangedEvent
import com.tw.shoppingcart.event.CartEventType.CART_CLEARED
import com.tw.shoppingcart.event.CartEventType.ITEM_ADDED
import com.tw.shoppingcart.event.CartEventType.ITEM_QUANTITY_CHANGED
import com.tw.shoppingcart.event.CartEventType.ITEM_REMOVED
import com.tw.shoppingcart.exception.ShoppingCartNotFound
import org.springframework.stereotype.Repository
import java.lang.Integer.min
import java.time.LocalDateTime
import java.util.Collections.synchronizedList
import java.util.concurrent.ConcurrentHashMap
import javax.annotation.PostConstruct

@Repository
class InMemoryShoppingCartRepository : ShoppingCartRepository {

    val carts: MutableMap<Long, ShoppingCart> = ConcurrentHashMap<Long, ShoppingCart>()
    val products: MutableMap<Long, Product> = ConcurrentHashMap<Long, Product>()
    val events: MutableList<CartChangedEvent> = synchronizedList(mutableListOf())

    @PostConstruct
    private fun init() {
        carts[1] = ShoppingCart(1, mutableMapOf())
        addProduct(Product(1, "Pen", 250))
        addProduct(Product(2, "Chair", 20000))
        addProduct(Product(3, "Notebook", 460))
        addProduct(Product(4, "Samsung TV", 99999))
        addProduct(Product(5, "Air conditioner", 30000))
        addProduct(Product(6, "Desk", 25000))
        addProduct(Product(7, "Nokia smart phone", 53600))
        addProduct(Product(8, "Color Pencil", 1099))
        addProduct(Product(9, "Vacuum cleaner", 16950))
        addProduct(Product(10, "Glass Bottle", 1200))
    }

    override suspend fun addToCart(id: Long, items: List<CartItem>) =
        getCart(id)
            .also { (_, itemsInCart) ->
                items.forEach {
                    when {
                        products.keys.contains(it.id) -> with(products[it.id]!!) {
                            itemsInCart.computeIfPresent(it.id) { _, u ->
                                events.add(CartChangedEvent(it.id, it.qty, ITEM_QUANTITY_CHANGED, LocalDateTime.now()))
                                u.copy(qty = min(u.qty + it.qty, 1000))
                            }
                            itemsInCart.computeIfAbsent(it.id) { _ ->
                                events.add(CartChangedEvent(it.id, it.qty, ITEM_ADDED, LocalDateTime.now()))
                                CartItem(it.id, name, price, min(it.qty, 1000))
                            }
                        }
                    }
                }
            }
            .also { return it }

    override suspend fun reduceItemsInCart(id: Long, items: List<CartItem>) =
        getCart(id)
            .also { (_, itemsInCart) ->
                items.forEach {
                    when {
                        itemsInCart.keys.contains(it.id) -> {
                            itemsInCart.computeIfPresent(it.id) { _, u -> u.copy(qty = u.qty - it.qty) }
                            if (itemsInCart[it.id]!!.qty <= 0) {
                                itemsInCart.remove(it.id)
                                events.add(CartChangedEvent(it.id, null, ITEM_REMOVED, LocalDateTime.now()))
                            } else {
                                events.add(CartChangedEvent(it.id, it.qty, ITEM_QUANTITY_CHANGED, LocalDateTime.now()))
                            }
                        }
                    }
                }
            }
            .also { return it }

    override suspend fun removeFromCart(id: Long, itemIds: List<Long>) =
        getCart(id)
            .also { cart ->
                itemIds.forEach {
                    cart.items.remove(it)
                    events.add(CartChangedEvent(it, null, ITEM_REMOVED, LocalDateTime.now()))
                }
            }
            .also { return it }

    override suspend fun removeAll(id: Long) =
        getCart(id)
            .also {
                it.items.clear()
                events.add(CartChangedEvent(null, null, CART_CLEARED, LocalDateTime.now()))
            }
            .also { return it }

    override suspend fun getCart(id: Long) =
        carts[id] ?: throw ShoppingCartNotFound(id)

    override suspend fun getProducts() =
        products.values.toList()

    private fun addProduct(product: Product) {
        products[product.id] = product
    }
}
