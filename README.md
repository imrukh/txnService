# TRANSACTION-SERVICE
Service for maintaining transaction, with parent child relation, for each parent there are multiple childs, 
Main api, For this we use Closure table based approach.  

## Getting Started
* Execute ```$mvn clean install```

# Prerequisites
Psql, Mvn.

# Installing
Run Psql in backgroud, go to application.properties and change database name accordingly
## Built With

* [SpringBoot](https://spring.io/projects/spring-boot) - Spring frame work used in this project
* [Maven](https://maven.apache.org/) - Dependency Management


API Spec:

PUT /transactionservice/transaction/$transaction_id Body:

{ "amount":double,"type":string,"parent_id":long } where:

• transaction id is a long specifying a new transaction

• amount is a double specifying the amount

• type is a string specifying a type of the transaction.

• parent id is an optional long that may specify the parent transaction of this transaction.



GET /transactionservice/transaction/$transaction_id Returns: { "amount":double,"type":string,"parent_id":long }

GET /transactionservice/types/$type Returns: [long, long, ... ] A json list of all transaction ids that share the same type $type.

GET /transactionservice/sum/$transaction_id Returns: { "sum": double } A sum of all transactions that are transitively linked by their parent_id to $transaction_id.( If A is parent of B and C,  and C is parent of D and E . sum(A) = B + C + D + 
E -- note: not just immediate child transactions, pls make sure you understand this.)



Examples

PUT /transactionservice/transaction/10 { "amount": 5000, "type":"cars" } => { "status": "ok" } 

PUT /transactionservice/transaction/11 { "amount": 10000, "type": "shopping", "parent_id": 10} => { "status": "ok" } 

GET /transactionservice/types/cars => [10] 

GET /transactionservice/sum/10 => {"sum":15000} 

GET /transactionservice/sum/11 => {"sum":10000}