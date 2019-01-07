# listenToYourself-microservices-pattern-implementation
Implementation of listen to yourself micro services design pattern using Spring cloud stream (Kakfa binder) and couchbase as persistence store 

# Pre-requisite:
1. Couchbase running locally
2. Create couchbase bucket with name "listentoyourself"
3. Create couchbase user with name "listentoyourself" and grant Bucket Admin access to the user for bucket "listentoyourself"
4. Internal Kafka cluster running on port 9092 and zookeeper port 2181. External Kafka cluster running on port 9093 and zookeeper port 2182.

# Functionality
Once the application is up and running, hit the createOrder POST endpoint(http://localhost:32011/v1/createOrder) using postman or any other tool which allows sending POST requests. The order request body json example is:

{
	"orderId":"123",
	"customerId":"999",
	"fulfilmentGroupId":"FG1",
	"deliveryGroupId":"DG1"
}

On receiving the createOrder request, the service would publish OrderCreated event on Kafka(internal running on port 9092) topic: com.example.lty.order. The EventListener class is listening on topic: com.example.lty.order. The OrderCreated event is consumed internally and Order document is created in couchbase DB. The same event is also published on external Kafka broker(running on port 9093) for external consumers to consume.


Since, the event is consumed internally which then persists the order in repository(instead of directly persisting)  and the same event is consumed by external consumers also, this way using Listen To Yourself pattern both: persistence of order to DB and raising event for external consumers happen using kafka calls only ensuring atomicity i.e. either both succeed or both fail since only one external dependency Kafka is used to perform both operations.

In case the persistence to couchbase fails for any intermittent reason, kafka would ensure that the message is retried using the default retrial configuration thus adding more consistency to the application.
