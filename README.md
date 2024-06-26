
***Amezone E-commerce Backend***

Project Overview

The Amezone E-commerce Backend is a comprehensive backend solution for an Amazon-like e-commerce platform developed using Java
 and Spring Boot. This project serves as the backbone for an e-commerce application, providing essential functionalities
  such as user authentication, product management, order processing, and more.

**Features**

*User Authentication and Authorization:*

Login: Secure user login with JWT-based authentication.

Sign-Up: New user registration with validation and confirmation.


*Product Management*

Product Listing: Display a list of available products.

Product Details: View detaile information about each product, including descriptions, prices, and images.
Product Category: view product descriptions and details about Category of products.

*User Management*


Profile Management: Users can view and update their profile information.

Order History: Users can view their past orders and details.

*Shopping Cart*



Add to Cart: Users can add products to their shopping cart.

Update Cart: Users can update quantities or remove items from the cart.
*Order Management*

Checkout: Users can place orders and complete the checkout process.

Order Processing: Handle order creation.

**Technology Use**

Spring Boot: Framework for building the application, providing ease of setup and a wide range of features.

Java: The primary programming language for backend development.
Microservices: Architecting the application into loosely coupled services for scalability and maintainability.

Database Configuration (MySQL): Storing and managing application data.

Swagger: For API documentation and testing, ensuring clear and accessible API specifications.

Spring Boot Data JPA: For database interactions, providing an abstraction layer over SQL queries.

Windows OS: The operating system for development and deployment.

Spring Tool Suite 4: Integrated development environment for Spring applications.

Hibernate: ORM tool for managing database interactions using Java objects.

MySQL Workbench: Tool for database design, development, and administration.

**Project Structure**

Controllers: Handle HTTP requests and map them to appropriate service methods.

Services: Contain business logic and interact with repositories.

Repositories: Interface with the database using Spring Data JPA.

Models: Define the data structures for users, products, orders, etc.

Configurations: Setup configurations for MySql DataBase, Swaggeretc

**API Endpoints**

#### Signup page

```http
UserApI
```

| Index     | API     | User               |
| :-------- | :------- | :------------------------- |
| 1 | http://localhost:8081/api/user/signup |POST- Create account on Amezone |
2|http://localhost:8081/api/user/signIn|Post-login account and get tokan|
3|http://localhost:8081/api/user/all?token=df73510b-c567-4b82-a141-450ddc366bc6|GET-we get all users by tokan||
4|http://localhost:8081/api/user/getbyid/2|GetById-get user by using id

#### 

```http
CategoryApi
```

| Index | API    | Category                    |
| :-------- | :------- | :-------------------------------- |
| 1    | http://localhost:8081/api/category/create |POST-Add category |
2 |http://localhost:8081/api/category/getall|GET-get all category which is present|
3|http://localhost:8081/api/category/getbyid/1|GET-get category by Id|
4|http://localhost:8081/api/category/update/3|update-update category by Id|


```http
ProductApi
```

| Index | API    | Product                       |
| :-------- | :------- | :-------------------------------- |
| 1    | http://localhost:8081/api/product/add|POST-Add Product |
2 |http://localhost:8081/api/product/|GET-get all Product which is present|
3|http://localhost:8081/api/product/get/2|GET-get Product by Id|
4|http://localhost:8081/api/product/update/2|update-update product by Id|


```http
OrderApi
```

| Index | API    | Order                      |
| :-------- | :------- | :-------------------------------- |
| 1    |http://localhost:8081/api/order/create-checkout-session|POST-First cheak product which we have Order |
2 |http://localhost:8081/api/order/add?sessionId=Zdcbnm&token=d1429414-|POST-Order Product which is present|
3|http://localhost:8081/api/order/2?token=d1429414-de79-4edb-8af5-cd56d087afdd|GetById-get Ordered-Product by Id|
4|http://localhost:8081/api/order/?token=d1429414-de79-4edb-8af5-cd56d087afdd|GET-get order- product by Id|

```http
CartApi
```

| Index | API    | Cart                      |
| :-------- | :------- | :-------------------------------- |
| 1    | http://localhost:8081/api/cart/add?token=d1429414-de79-4edb-8af5-cd56d087afdd|POST-Add Product to cart |
2 |http://localhost:8081/api/cart/delete/1?token=d1429414-de79-4edb-8af5-cd56d087afdd|delete-Delet cart Product which is present|
3|http://localhost:8081/api/cart/update/{cartItemId}?token=d1429414-de79-4edb-8af5-cd56d087afdd|Update-update Product by Id and |
4|http://localhost:8081/api/cart/?token=d1429414-de79-4edb-8af5-cd56d087afdd|GET-Get all cart product presented|


```http
File-UploadApi
```

| Index | API    | File-Uplode                    |
| :-------- | :------- | :-------------------------------- |
| 1    | http://localhost:8081/api/fileUpload/|POST-Customer can upload file if roduct is wrong or bad including photost and information of product|
2 |http://localhost:8081/api/fileUpload/files/Vaishnavi_Amezon_resume|GET-we get Uploaded file by file name|
3|http://localhost:8081/api/fileUpload/|GET-Get all file |


```http
WishListApi
```

| Index | API    | WishList                |
| :-------- | :------- | :-------------------------------- |
| 1    | http://localhost:8081/api/wishlist/add?token=d1429414-de79-4edb-8af5-cd56d087afdd|POST-AddWishList|
2 |http://localhost:8081/api/wishlist/d1429414-de79-4edb-8af5-cd56d087afdd|GET-get all wishlist|



## Documentation

[Main Files](Amezon/src/main/java/com/webtutsplus/ecommerce)|

[Images](Amezon/Images)

[Swagger](Amezon/Images/2024-06-26 (1).png)
