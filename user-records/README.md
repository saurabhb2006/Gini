# Getting Started

### Reference Documentation 

GET - http://localhost:8080/api/v1/records/?id=14a26b3d-b746-4d98-be4b-b0f53a8d0f0b  
GET - http://localhost:8080/api/v1/records/?userId=saurabh  
POST - http://localhost:8080/api/v1/records/  
 Body :  
   {  
	 "userId": "Jason",  
	 "type": "pdf",  
	 "description" : "Documentation",   
   }   

Post returns UUID of the created object

Patch - http://localhost:8080/api/v1/items/{id}?description=enter string  
PATCH - http://localhost:8080/api/v1/items/14a26b3d-b746-4d98-be4b-b0f53a8d0f0b?description=test to see  

To View Database : http://localhost:8080/h2-console  
username : username
password : password 
 
