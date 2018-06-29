Spring Boot Rest API 
====================
Voting Machine written by Kevin Duraj

### Compiling and Running the project
```
mvn clean install
mvn spring-boot:run
```


### API Endpoints

### Create a new candidate:
[http://localhost:8080/vote?add=Beatles](http://localhost:8080/vote?add=Beatles)


### List all candidates:
[http://localhost:8080/vote](http://localhost:8080/vote)


### Vote UP for candidate name and client id: 
* If candidate exist will increase vote count, if candidate does not exist will add new candidate
* Client can only vote once Up or Down 

[http://localhost:8080/vote?up=Galantis&client=1100](http://localhost:8080/vote?up=Galantis&client=1100)

### Vote Down for candidate name and client id: 
* If candidate exist will increase vote count, if candidate does not exist will add new candidate
* Client can only vote once Up or Down 

[http://localhost:8080/vote?down=Rudimental&client=1200](http://localhost:8080/vote?down=Rudimental&client=1200)

### View Total Vote Count for given object id:
[http://localhost:8080/vote/4](http://localhost:8080/vote/4)


### Delete a candidate:

[http://localhost:8080/vote?del=Galantis](http://localhost:8080/vote?del=Galantis)



### The pros and cons of separating front-end and back-end.

* Pros:  Development can proceed at different paces.  Unit testing, build and deploy strategies are wholly separate, and allowing the developer to minimize disruption to user experience during deploys.

* Cons: Builds / Deployments:  must come up with independent unit testing, build, and deployment strategies for application separated by front-end and back-end, which takes time and resources.


