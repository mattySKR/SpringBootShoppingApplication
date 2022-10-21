# SpringBootShoppingApplication

1.Products API:

Created a Spring Boot application that will provide a REST API service to add, update and delete a product, and to retrieve all products or a specific     product by its id or by its name. If a product resource cannot be found when updating, deleting or retrieving a target product, then an HTTP status code   of ‘NOT FOUND’ should be sent in the response.

Additionally, my API includes the following: 
  •	Custom Runtime Exceptions 
  •	Swagger Documentation 
  •	Validation with javax.validation 

All of the above (1.) is implemented in ---> SpringBoot_REST_API_ProductServer folder.  (The server for the application).


2.Shopper User Interface Client:

Created a Spring Boot application that will be a Spring REST client web UI for CRUD operations of products. This app is consuming services                 available in the Products API that was created in STEP 1.

All of the above (2.) is implemented in ---> SpringBoot_REST_API_ProductClient folder.  (The web client for the application).
