package com.pm.analyticsservice.kafka;

import com.google.protobuf.InvalidProtocolBufferException;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.common.header.Headers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;
import restaurant.events.RestaurantEvent;
import user.events.UserEvent;

import java.util.stream.StreamSupport;

@Service
public class KafkaConsumer {

    private static final Logger log = LoggerFactory.getLogger(
            KafkaConsumer.class);

    private static String typeIdHeader(Headers headers) {
        return StreamSupport.stream(headers.spliterator(), false)
                .filter(header -> header.key().equals("__TypeId__"))
                .findFirst().map(header -> new String(header.value())).orElse("N/A");
    }

    @KafkaListener(topics = "restaurant", groupId = "analytics-service", containerFactory =
            "kafkaListenerByteArrayContainerFactory")
    public void consumeRestaurantEvent(ConsumerRecord<String, byte[]> cr, @Payload byte[] event) {
        try {
            RestaurantEvent restaurantEvent = RestaurantEvent.parseFrom(event);

            // ... perform any business related to analytics here

            log.info("Received Restaurant Event: [RestaurantId={},RestaurantName={},RestaurantEmail={}]",
                    restaurantEvent.getRestaurantId(),
                    restaurantEvent.getName(),
                    restaurantEvent.getEmail(), cr.key(), typeIdHeader(cr.headers()), event);
        } catch (InvalidProtocolBufferException e) {
            log.error("Error deserializing event {}", e.getMessage());
        }
    }

    @KafkaListener(topics = "user", groupId = "analytics-service", containerFactory =
            "kafkaListenerByteArrayContainerFactory")
    public void consumeUserEvent(ConsumerRecord<String, byte[]> cr, @Payload byte[] event) {
        try {
            UserEvent userEvent=UserEvent.parseFrom(event);
            log.info("Received User Event: [UserId={},Auth0Id={},UserEmail={}]",
                    userEvent.getUserId(),
                    userEvent.getAuth0Id(),
                    userEvent.getEmail(),
                    cr.key(), typeIdHeader(cr.headers()), event);
            // ... perform any business related to analytics here

        } catch (InvalidProtocolBufferException e) {
            log.error("Error deserializing event {}", e.getMessage());
        }
    }
}
