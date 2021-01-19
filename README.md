# Spring Boot starter for Java 11
This is a starter including:
* Spring Boot Web starter
* Spring Boot Data JPA starter
    * Using MySQL
* Spring Boot Devtools and Annotation Processor
* A sample REST controller

## MySQL in Docker
`application.yml` already contains connection information to this DB.
```shell script
docker-compose up -d
```

## Building and running app
Build jar with maven and run jar file with java.

Register user at "*/register". A user has a username, a password and a cart.

Get all products and add new ones at "/products" A product has a name, a description, a price, and a boolean indicating whether it is in the news section or not.

Update, delete and get individual products at "/products{id}"

Update, delete, get and add cart at "/customer{id}/cart". A cart is bound to a customer and contains a list of product elements with a name, price and quantity

Checkout the cart and add an order at "/customer{id}/cart/checkout"


## TODO
Add role based access to the resources.

Add proper input validation.

Add proper password management and authentication

Add update, modify and delete for orders.

Add proper responses



