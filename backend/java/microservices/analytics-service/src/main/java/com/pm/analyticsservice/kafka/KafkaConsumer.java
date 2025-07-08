package com.pm.analyticsservice.kafka;

import com.google.protobuf.InvalidProtocolBufferException;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.common.header.Headers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;
import patient.events.PatientEvent;

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

    @KafkaListener(topics = "patient", groupId = "analytics-service", containerFactory =
            "kafkaListenerByteArrayContainerFactory")
    public void consumeEvent(ConsumerRecord<String, byte[]> cr, @Payload byte[] event) {
        try {
            PatientEvent patientEvent = PatientEvent.parseFrom(event);
            // ... perform any business related to analytics here

            log.info("Received Patient Event: [PatientId={},PatientName={},PatientEmail={}]",
                    patientEvent.getPatientId(),
                    patientEvent.getName(),
                    patientEvent.getEmail(), cr.key(), typeIdHeader(cr.headers()), event);
        } catch (InvalidProtocolBufferException e) {
            log.error("Error deserializing event {}", e.getMessage());
        }
    }
}
