package creditdirect.clientmicrocervice.kafka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class KafkaProducer {

    private final KafkaTemplate<String, String> kafkaTemplate; // Change String to match your key/value types

    // Inject KafkaTemplate using constructor
    @Autowired
    public KafkaProducer(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    // Method to send dossier information to Kafka
    public void sendDossierToKafka(String topic, String dossierInfo) {
        kafkaTemplate.send(topic, dossierInfo);
    }
}
