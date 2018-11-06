# HOMEWORK 20
_____

### Description
In this homework, I created a new implementation for recipe-persistence: It based on the DB - **H2**-in-memory.

Repository methods work using *JdbcTemplate*. Also, when the bean *InMemoryRecipeRepository* is initialized, the schema is built in the database and the initial data is set.

To implement the service layer, I added 5 CRUD methods to the interface *RecipeService* and implemented them in class *DefaultRecipeService*.

To work through the browser, use the class *RecipeController*. There is one more additional class *AuxiliaryController* for auxiliary purposes (control views for mapping *index*, *error* and *success*). 
Start page mapping: **http://localhost/index**.

To implement the work via the restful API, *RestRecipeController* is made with all the necessary HTTP-methods. 
Request mapping starts at **http://localhost/api/**, and have few suffixes:

* **recipe_of_the_day** - finds recipe of the day (GET method);
* **recipe** - finds all recipes (GET method);
* **recipe/{id}** - finds one recipe by id (GET method);
* **recipe** - creates new recipe (POST method, which has new recipe title in its body);
* **recipe/{id}** - updates existing recipe by id (PUT method, which has new recipe title in its body);
* **recipe/{id}** - deletes existing recipe by id (DELETE method).

Link to shared **Postman Collection**:
https://www.getpostman.com/collections/d7c6e225e2b78c20ed78
