# tollparking
toll parking library rest api on spring boot

App Name: tollParkingLibrary
Public Url: https://github.com/NebukadNesar/tollparking

To build jar file, download project as zip from public url.
extract it and open command line inside the extracted folder
run below commands in order
*	1-mvn clean
*	2-mvn compile 
*	3-mvn install

this will generate jar file into target directory. Already generated one is in the following url
Application jar file name: tollParkingLibrary.jar, URL: https://github.com/NebukadNesar/tollparking/blob/master/tollParkingLibrary.jar

To run jar file from terminal, terminal should be opened in the same folder with the config-properties.json file which is in base directory of project
then
below commands would run tollParkingLibrary as a standalone app.
* if you are in windows(tested windown 10) : "C:\Program Files\Java\jdk1.8.0_241\bin\java.exe" -jar target\tollParkingLibrary.jar
* if you are in linux(tested ubuntu mate)  : java -jar tollParkingLibrary.jar

hit: localhost:8080 on a browser to see its working


For test run:
* mvn test

There are 24 test cases each for different scenario, app doc read, performance, faulty requests, exceptions etc..

Application reqires a json file called config-properties.json in app directory as in URL: https://github.com/NebukadNesar/tollparking/blob/master/config-properties.json

In this file policies and slot preferences can be changed. Api requires this file when starting.

	
Application Doc File Starts with index.html already generated one is in the URL: https://github.com/NebukadNesar/tollparking/blob/master/index.html
JavaDoc contains all the comments and descriptions.


Java file can be used by importing into another project or just by using it as a spring boot app.
Api has two public POST methods for "park"  and "unPark" and a GET method "statistics" that provides statistical information about the parking.

-----------------
<h3>park</h3>	
park: POST http://<ip>:8080/tollparkingmanager/park
request body: raw, type: application/json
sample json : 
```json
{
	"vehicle": {
		"type": "EC50"
	}
}
```
returns: Response object
sample response: 
```json
{
    "status": 11,
    "atNumber": 0,
    "statusDesc": "Car parked successfully",
    "parkStartAsMillis": 1600613051803,
    "price": 0,
    "appliedPolicies": null,
    "pricingPolicySet": null,
    "successFull": true
}
```
-----------------
<h3>unPark</h3>			  
unpark: POST http://<ip>:8080/tollparkingmanager/unpark
request body: raw
type: application/json
sample json : 
```json
{
	"vehicle": {
		"type": "EC50"
	}
}
```
returns: Response object
sample response:
```json
{
    "status": 12,
    "atNumber": 0,
    "statusDesc": "Car unparked successfully",
    "parkStartAsMillis": 1600613904921,
    "price": 3,
    "appliedPolicies": "EC50",
    "pricingPolicySet": [
        {
            "defaultPrice": 3,
            "hourlyPrice": 2.5,
            "pricingPolicyName": "EC50"
        }
    ],
    "successFull": true
}		 
```

returns policies applied and price total.

-----------------
<h3>statistics</h3>

statistics: GET http://<ip>:8080/tollparkingmanager/statistics
not request
returns: StatisticsResponse object


Already tested and used api call samples in POSTMAN added to the project git location url: https://github.com/NebukadNesar/tollparking/blob/master/tollParkingApiCalls.postman_collection.json
These samples can be imported and run.

-----

<h3>DockerFile</h3>
```
FROM openjdk:8
ADD target/tollParkingLibrary.jar tollParkingLibrary.jar
ADD config-properties.json config-properties.json
EXPOSE 8085
ENTRYPOINT ["java","-jar","tollParkingLibrary.jar"]
```
* docker build -f DockerFile -t docker-spring-boot .
* docker run -p 8085:8085 docker-spring-boot
