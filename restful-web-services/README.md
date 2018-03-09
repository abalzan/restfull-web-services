
#Swagger Documentation
http://localhost:8080/swagger-ui.html

http://localhost:8080/v2/api-docs

#Hall Browser Monitoring your application
http://localhost:8080/browser/index.html#/


This is a pretty interesting project, we configure:
- swagger for documentation, 
- hal for monitoring services
- HATEOAS for links
- Static Filtering, just to exibit what we want on a rest call just using @JsonIgnore commented at User entity
- Dinamic Filtering it is useful but to use it, you need to configure all your methods to return MappingJacksonValue.
	To use it, you have a filtering call on the rest class and a comment filter on User entity.
 