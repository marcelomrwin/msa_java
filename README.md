# Microservices for Java

## Clone
``git clone https://github.com/marcelomrwin/msa_java.git``

## Change to master branch
``git checkout master``

## Enter hola-springboot
``cd hola-springboot``

## Build and run
``mvn clean package spring-boot:run``

## Access URL
http://localhost:8080

## Using curl
``curl http://localhost:8080/api/hola; echo``

## Verifying metrics
- http://localhost:8080/beans
- http://localhost:8080/env
- http://localhost:8080/health
- http://localhost:8080/metrics
- http://localhost:8080/trace
- http://localhost:8080/mappings

## Run outside of maven
``mvn clean package`` <br>
``java -jar target/hola-springboot-1.0.jar``

# stop application

## Acess backend service
``cd ../backend/``

## Buind and run ##
`` mvn clean package jetty:run``

## Test backend with curl
``curl -X GET http://localhost:8080/api/backend?greeting=Hello``

## Returning to hola-springboot
``cd ../hola-springboot``

## Running with different port
``mvn clean package spring-boot:run -Dserver.port=9090``

## Test URL
http://localhost:9090/api/greeting

## Stop backend and hola-springboot services

## Navigate to hola-dropwizard
``cd ../hola-dropwizard/``

## build project
``mvn clean package``

## run project 
``mvn exec:java``

## test rest service
http://localhost:8080/api/hola

## test update variable
``HELLOAPP_SAYING='Hello Dropwizard inline from' mvn clean package exec:java``

## see metrics
http://localhost:8081/

## Run outside of maven
``mvn clean package``<br/>
``java -jar target/hola-dropwizard-1.0.jar server conf/application.yml``

# stop application

## Acess backend service
``cd ../backend/``

## Buind and run ##
`` mvn clean package jetty:run``

## Test backend with curl
``curl -X GET http://localhost:8080/api/backend?greeting=Hello``

## Navigate to hola-dropwizard
``cd ../hola-dropwizard``

## execute with different port
``mvn clean package exec:java -Ddw.server.applicationConnectors[0].port=9090``

## Stop backend and hola-dropwizard services

## Navigate to hola-wildflyswarm
``cd ../hola-wildflyswarm``

## start jboss forge
``forge``

## execute application
``wildfly-swarm-run``

## stop run 
Ctrl + c

## exit forge
``exit``

## Execute with maven
``mvn clean package wildfly-swarm:run``

## test url
http://localhost:8080

## Execute with environment variables
``WF_SWARM_SAYING='Yo man' mvn clean package wildfly-swarm:run``

## test url with new variable name
http://localhost:8080

## monitor metrics
- http://localhost:8080/node
- http://localhost:8080/heap
- http://localhost:8080/threads

## Run outside of maven
``mvn clean package``<br/>
``java -jar target/hola-wildflyswarm-swarm.jar``

# stop application

## Acess backend service
``cd ../backend/``

## Buind and run ##
`` mvn clean package jetty:run``

## Test backend with curl
``curl -X GET http://localhost:8080/api/backend?greeting=Hello``

## back to hola-wildflyswarm
``cd ../hola-wildflyswarm``

## run with different port
``mvn clean package wildfly-swarm:run -Dswarm.port.offset=1``

## Test service
http://localhost:8081