# A simple shopping cart

This is a simple Kotlin RESTful microservice to add/edit/remove items from a shopping cart. also applies discount on the cart.

### Used Technology
* Kotlin 1.6
* Spring Boot version 2.6.4
* Spring Boot Actuator for health and http trace functionality
* Spring WebFlux, Mockk
* Swagger API documentation
* Postman collection for endpoints

### Setup and Run
After cloning this project and opening in an IDE like eclipse or IntelliJ IDEA, you can simply setup JDK, preferably 11 and Kotlin 1.6, and run ShoppingCartApplication class.

For checking application running fine, you can open this url: [http://localhost:8080/actuator/health](http://localhost:8080/actuator/health) and see: `{"status":"UP"}`
### How it works
There are 10 default products added to the application. You can see them by this endpoint:
* GET http://localhost:8080/api/product

Then using these endpoints, you can add/edit/remove items in the cart:

* GET http://localhost:8080/api/cart
* POST http://localhost:8080/api/cart
* PATCH http://localhost:8080/api/cart
* DELETE http://localhost:8080/api/cart
* DELETE http://localhost:8080/api/cart/all
* GET http://localhost:8080/api/discount
* DELETE http://localhost:8080/api/discount

You can find the Swagger Api documentation in `projectRoot/api/openapi.yaml`

Also for health and request trace:
* GET http://localhost:8080/actuator/health
* GET http://localhost:8080/actuator/httptrace


#### Postman Collection:
https://www.getpostman.com/collections/462e5848407fac9071e2 (import them easily to Postman)