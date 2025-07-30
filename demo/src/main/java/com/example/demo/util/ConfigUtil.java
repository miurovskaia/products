package com.example.demo.util;

import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;

public class ConfigUtil {

    public static String getKafkaAdress() {
        Config conf = ConfigFactory.load();
        return conf.getString("kafka-address");
    }

    public static String getKafkaClientId() {
        Config conf = ConfigFactory.load();
        return conf.getString("kafka-client-id-config");
    }

    public static String getKafkaGroupId() {
        Config conf = ConfigFactory.load();
        return conf.getString("kafka-group-id-config");
    }

    public static String getKafkaCarsKafkaTopic() {
        Config conf = ConfigFactory.load();
        return conf.getString("kafka-topic-cars");
    }

}