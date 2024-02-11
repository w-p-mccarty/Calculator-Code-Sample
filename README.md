# WorkSample
##### Author: William McCarty
##### 2024
----
### Description
----
This is a simple project that has two parts.  First is a set of Calculation that store the result in a MongoDB.  This is similar to how a calculation has the history function.  As of this writing it simply takes in a set of numbers and does the same operations.

If you are wanting to evaluate different combinations of operators in the same equation, then you would use the Equation Services.  In this you can build your equation piece by piece.  It accepts any numeric value and one of four operations, that are both considered terms.  These are also saved in MongoDB, but with a different structure to allow for easier access and processing.  It also allows for multiple equations to be worked on in parallel.

----
### Requirements
----
- Java
- MongoDB
- Gradle
- mongo-migrate
----
### Running Program
----
                
1. Start Monogo db, how I do it is with the following command, you will need to adjust the path files to your local machine
`mongod.exe --auth --dbpath="G:\Mongo\data\db"`

2. Create authorized User in mongo
		monogosh

		db.createUser({ user: "user" , pwd: "password", roles: ["userAdminAnyDatabase", "dbAdminAnyDatabase", "readWriteAnyDatabase"]})`

3. Run the migrations
`migrate-mongo up`
4. At this point you should be able to see the database in mongodb
5. Run Server by either running
		gradle clean
		gradle build
		gradle bootRun
or
`.\startServer.bat`

6. To Test you can use Postman to submit request for each endpoint.  Below is an example for all endpoints

----
### API
----
#### /sum-values
POST
Summary: Gives the sum of all values passed in the request


##### Example
- Request
```
{
  "Values": [1, 2, 3, 4, 5]
}
```
- Response
```
{
    "Result": 15.0
}
```
----
#### /sub-values
POST
Summary: Gives the subtracted value of all values passed in the request


##### Example
- Request
```
{
  "Values": [1, 2, 3, 4, 5]
}
```
- Response
```
{
    "Result": -13.0
}
```
----
#### /div-values
POST
Summary: Gives divided value of all values passed in the request


##### Example
- Request
```
{
  "Values": [1, 2, 3, 4, 5]
}
```
- Response
```
{
    "Result": 0.008333334
}
```
----
#### /mult-values
POST
Summary: Gives Multiplied value of all values passed in the request


##### Example
- Request
```
{
  "Values": [1, 2, 3, 4, 5]
}
```
- Response
```
{
    "Result": 120.0
}
```
----
#### /add-to-equation
POST
Summary: Adds the Term to the Equation Id passed in the request


##### Example
- Request
```
{
  "Id": "3",
  "Term":"1"
}
```
- Response
```
{
    "Success": true
}
```
----
#### /print-equation
GET
Summary: Print the Equation of the Id in the request


##### Example
- Request
```
{
  "Id": "3"
}
```
- Response
```
{
    "Equation": "1 + 2"
}
```
----
#### /eval-equation
GET
Summary: Evaluates the Equation of the Id in the request


##### Example
- Request
```
{
  "Id": "3"
}
```
- Response
```
{
    "Result": 3.0
}
```
----
#### /clear-equation
POST
Summary: Clears the Equation of the Id in the request


##### Example
- Request
```
{
  "Id": "3"
}
```
- Response
```
{
    "Success": true
}
```
