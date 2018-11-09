# epm-recipe

## Prerequisites

This project requires H2 database installed and launched.

## Installing


The project has to be built and executed using gradle as in the following lines:

```
gradle clean run
```

or using the default gradle wrapper that comes with the project.

To stop the execution you can:

- Stop the task in the same terminal using `^C` to terminate the job
- Stop the task by terminating the containing terminal (Unix/Linux systems only)
- Use any other tool to terminate the running job

## Basic web URLs

### Basic UI (request bodies)

Basic web-UI features can be found at `localhost/`. Add one of the following words to open corresponding feature UI page:

- `get` to get one of the recipes by ID
- `add` to add another recipe
- `delete` to remove recipe by ID
- `update` to update existing recipe

### Basic responses

The corresponding response pages are located under `localhost/recipe/` and the same word as at the request page. Also there is additional page with all recipes that are on the list at `localhost/recipe/all` and recipe of the day at `localhost/recipe/`.

### Rest API

Rest API requests are located at `localhost/api/` address with the following links:

- `recipe_of_the_day` is a `GET` request
- `recipe/{id}` is a `GET` request
- `recipe/all` is a `GET` request
- `recipe/add` is a `PUT` request with parameters `id` (integer number) and `title` (string)
- `recipe/delete/{id}` is a `DELETE` request
- `recipe/update` is a `PATCH` request with parameters `id` (integer number) and `title` (string)

Instead of `{id}` you can place any integer number to perform the specified action over the recipe with corresponding `id` value