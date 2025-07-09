package com.pm.restaurantservice.kafka;
import com.pm.restaurantservice.model.Restaurant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import restaurant.events.RestaurantEvent;

@Service
public class KafkaProducer {

  private static final Logger log = LoggerFactory.getLogger(
      KafkaProducer.class);
  private final KafkaTemplate<String, byte[]> kafkaTemplate;

  public KafkaProducer(KafkaTemplate<String, byte[]> kafkaTemplate) {
    this.kafkaTemplate = kafkaTemplate;
  }

  public void sendEvent(Restaurant restaurant) {
    RestaurantEvent event = RestaurantEvent.newBuilder()
        .setRestaurantId(restaurant.getId().toString())
        .setName(restaurant.getName())
        .setEmail(restaurant.getEmail())
        .setEventType("RESTAURANT_CREATED")
        .build();

    try {
      kafkaTemplate.send("restaurant", event.toByteArray());
    } catch (Exception e) {
      log.error("Error sending RestaurantCreated event: {}", event);
    }
  }
}
