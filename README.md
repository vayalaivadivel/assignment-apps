# Spring boot with gateway, registry and hystrix
The main aim of this project is to develop micro-service using Java 11, Spring framework components and Docker, implement an API gateway which forward all incoming requests to internal services with secure JWT token.
To achieve this, we are using spring-boot with micro-service architecture and below are the 3 important section will describe the details of this application,
- Design and considerations
- Limitations
- Getting started / Usage Guide
# Design and considerations!
  It has been designed 5 important components. They are,
  - api-registry - where all the services are registered with service and client can discovery the require service with unique name
  - gateway-service - where the end-user can access all the internal services. This is also registered as part of api-registry since its require to find the internal service.
  - services - There 3 internal services called service-one, service-two and service-three and each service exposing one rest endpoint.
  - authorization server - We are using free-tier account of Okta authorization server for the secure communication among the various services using JWT token.
  - hystrix - To avoid cascade failure.
  Dockerizing all the above components; 

# Limitations
- No test cases involved in it
- No Kubernetes
- No profiles(Dev,Test etc.,) maintained.
- No actuator api.
- No https involved.

# Getting started / Usage Guide
- Clone the source code from the git repo.
- Navigate to root folder and execute below mvn command to build all the jars
```sh
       $ mvn clean install
```
- Build images for all the 5 components and start all the services in independent docker container
```sh
       $ docker-compose up --build
```
- Grab the JWT token from by clicking [here](https://oidcdebugger.com/debug). Please refer my mail for the credentials.
- Use postman to test below services,

	   i) Click [here](http://localhost:9090) to check all the services registered in service registry
       i) Validate the token
	      GET http://localhost:8080/get
		  Header: Authorization: Bearer <JWT Token>
		  
	   ii) Test service1
	      GET http://localhost:8080/service1/test1?param1=1
		  Header: Authorization: Bearer <JWT Token>
		  
	   iii) Test service2
	      POST http://localhost:8080/service2/test2
		  Header: Authorization: Bearer <JWT Token>
		  Body
		  {
		    {
				"msg": "Test message"
			}
		  }
		  
	   iv) Test service2
	      PATCH http://localhost:8080/service3/test3
		  Header: Authorization: Bearer <JWT Token>
		  Body:
		  	{
				"messages":
					[
					  	{
					  		"msgOne": "Test message one"	
					  	},
					  	{
					  		"msgTwo": "Test message two"	
					  	}
					]
			}
		v) Examine the server console log of gateway-service which is displaying all the request details from the h2 database
		
- Stop all the containers
```sh
       $ docker-compose down
```
