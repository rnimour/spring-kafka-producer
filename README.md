This is a tiny Spring Boot project that can send a message to Kafka, running at `localhost:9092`.

You can send messages by running this app and sending a POST request to localhost:8080/messages.
The app will then send the received request's body to as a message to topic `my-spring-topic`.