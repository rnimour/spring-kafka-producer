package com.rnimour.messaging.kafka

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.kafka.support.SendResult
import org.springframework.stereotype.Component
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@Component
class MyKafkaProducer {

    @Autowired
    private lateinit var kafkaTemplate: KafkaTemplate<String, String>

    fun sendMessage(topic: String, message: String) {
        val now = LocalDateTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss.SSS"))
        println("$now | Received <$message>. sending to Kafka")

        val future = kafkaTemplate.send(topic, message)
        future.whenComplete(handleCompletion(message))
    }

    private fun handleCompletion(message: String) = { result: SendResult<String, String>?, ex: Throwable? ->
        if (ex == null) {
            println("@offset ${result?.recordMetadata?.offset()}, sent message <$message>")
        } else {
            println("could not send message <$message> because of ${ex.message}")
        }
    }
}