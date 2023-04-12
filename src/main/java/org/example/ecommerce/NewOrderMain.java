package org.example.ecommerce;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.Properties;
import java.util.concurrent.ExecutionException;

public class NewOrderMain {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        var producer = new KafkaProducer<String, String>(properties());
        var value = "111111,222222,33333";
        var record = new ProducerRecord<String, String>("ECOMMERCE_NEW_ORDER", value, value);
        producer.send(record, (data, err)-> {
            if(err != null) {
                err.printStackTrace();
                return;
            }
            System.out.println("Sucesso! Enviando " + data.topic() + ":::partition " + data.partition() + " /offset " + data.offset() + " /timestamp " + data.timestamp());
        }).get();
    }

    private static Properties properties() {
        var properties = new Properties();
        properties.setProperty(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "127.0.0.1:9092");
        properties.setProperty(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        properties.setProperty(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        return properties;
    }
}
