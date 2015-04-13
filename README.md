A restful micro-service registry application written in Java. A micro-services registers itself with this application so consumers can make requests without prior knowledge of where or how many of the micro-services exist.

Instructions
------------
* Open a command terminal and clone this repository.
```
git clone https://github.com/Samuel1989/service-lu.git
```
* Navigate to the service-lu directory.
```
cd service-lu
```
* Build the project using Maven.
```
mvn package
```
* Execute the service-lu-0.0.1-SNAPSHOT.jar file.
```
java -jar target/service-lu-0.0.1-SNAPSHOT.jar
```
* Register a micro-service by posting JSON to this application.
```
http://localhost:8080/rest/register
```
* Post your micro-service request to this application and it will load balance it.
```
http://localhost:8080/rest/service/{groupID}
```

Example of JSON Register POST
-----------------------------
```
{"groupID":"email","endpoint":"http://localhost:8081/rest/sendemail"}
```

Explination of endpoints
------------------------
Register your micro-service by providing the following url with a groupID (a groupId couples services together so you can provide this application with a post to the one group and it will be load balanced across all endpoints in that group) and it's restful endpoint.
```
http://localhost:8080/rest/register
```

Get a list of all groupIds, by submitting a GET request to the following url:
```
http://localhost:8080/rest/servicegroup
```

Submit a JSON POST to the groupId of interest, this application will then randomly load balance the request to all known endpoints in that category, so for an "email" groupId the url will be:
```
http://localhost:8080/rest/service/email
```
