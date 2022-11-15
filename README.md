example POST curls

curl http://localhost:8442/createCustomer -d '{"name": "asen" , "age" : 32 }' -H 'Content-Type: application/json'
curl http://localhost:8442/getCustomers


Building steps: 

.\gradlew clean build
