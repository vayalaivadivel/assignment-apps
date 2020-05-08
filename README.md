# assignment-apps
Procedure to run:
We are using Okta authorization server incase of JWT authenticaion.
So please follow steps in my mail to take JWT token with my credentials

Step1: Navigate to root folder and execute below command to build all the jars
       >mvn clean install
Step2: Build image and start all the services
       >docker-compose up --build
Step3: Use postman to test all the services

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
		  	"messages":[
					{
					  "msgOne": "Test message one"	
					},
					{
					  "msgTwo": "Test message two"	
					}
			]
		  
Step4: docker-compose down.