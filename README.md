# Code Kata style screening test

Requirements:

* Java 17
* Maven

## Application overview
You have been provided with a pre-initialized Maven project created from the Spring Initializr https://start.spring.io/ 
with the following additional dependencies:

* Spring Web
* Spring Data JPA 
* H2 Database
* Apache Commons Lang3

You can run the application using the command `mvn spring-boot:run`. Run all tests with `mvn verify`. 
Alternatively, you can run them from within your IDE.

## Task - Remove First and Last Character
You have 30 minutes to complete the task below. You will need to include automated tests for your solution.

Your goal is to create a `GET /remove` endpoint in the class `DemoController.java`. The endpoint takes 
one query parameter (the original string), removes the first and last characters of a string and returns the modified 
string in the response body.

Important: Your endpoint should handle strings of any length ≥ 2 characters. For strings with exactly 2 characters, 
return an empty string. For strings with less than 2 characters, return a 400 Bad Request response.

Examples:

* 'eloquent' -> 'loquen'
* 'country' -> 'ountr'
* 'person' -> 'erso'
* 'ab' -> '' (empty string)
* 'xyz' -> 'y'
* '_123_%qwerty+' -> '123_%qwerty'
* 'a' -> 400 Bad Request (error response)

## Requirements

* For strings with less than 2 characters, return a 400 Bad Request response
* For strings with exactly 2 characters, return an empty string
* For strings with 3 or more characters, remove the first and last character
* The endpoint should handle strings containing letters, numbers, and special characters

## Test Cases
Add automated tests in `DemoControllerTest.java` to demonstrate your solution works as expected. 
The tests should cover the following variation of test data

1. Basic functionality with common words of varying lengths
2. Edge cases with 0 to 3-character strings
3. Strings containing numbers and special characters
