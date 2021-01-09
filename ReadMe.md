http://localhost:8181/swagger-ui.html
h2-database Is running H2 DB on port 9292. Start this application.
rest-microservices Is running on port 8181 and using above h2-database.

Request Json Object :
{
    "id": 1,
    "firstName": "John Update",
    "lastName": "abc",
    "email": "abc@abc.com",
    "phoneNumber": "123-345-234"
}

Update and POST PUT, - http://localhost:8181/employee
GET http://localhost:8181/employee/1
DELETE http://localhost:8181/employee/1
