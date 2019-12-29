# SpringBootJWT
Spring boot project with a microservice architecture which uses JWT as authentication

## UserService
UserService is a microservice which provide an API to manage user data, users are stored in a database.

## GatewayService
GatewayService is a microservice which is the gateway of the app to the outside. The gateway provides an authetication mecanism with the Springboot Security framework. It grants access to users to the API with JSON Web Tokens.

### Source
- https://www.youtube.com/watch?v=X80nJ5T7YpE
