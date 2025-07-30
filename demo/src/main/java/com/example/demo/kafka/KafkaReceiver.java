package com.example.demo.kafka;


import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Properties;



public class KafkaReceiver

{
  private final static String TOPIC = "test-topic-study2";
  private final static String GROUP_ID = "KafkaStudyConsumer";
  private final static String BOOTSTRAP_SERVERS = "broker:29092";

  private static final Logger logger = LoggerFactory.getLogger("KafkaReceiver");

  public static void listen() {
      Consumer<String, String> consumer = createConsumer(TOPIC);
      try {
          final ConsumerRecords<String, String> records = consumer.poll(1000);
          System.out.println("pollll made");
          if (!records.isEmpty()) {
              records.forEach(record -> {

                  logger.info("receive record(key={} value={} )",
                          record.key(), record.value());
                  System.out.println("received record"+record.key()+record.value());
              });

              consumer.commitAsync();
          }
          else{
              System.out.println("recordsisempty");
          }
      } catch (Exception e) {
          e.printStackTrace();
      } finally {
          consumer.commitSync();
          consumer.close();
      }
  }

  private static Consumer<String, String> createConsumer(String topic) {
      Properties props = new Properties();
      props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, BOOTSTRAP_SERVERS);
      props.put(ConsumerConfig.GROUP_ID_CONFIG, GROUP_ID);
      props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
      props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
      props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
      KafkaConsumer<String, String> consumer = new KafkaConsumer<>(props);

      consumer.subscribe(List.of(topic));

      return consumer;
  }


}

