package com.tony.mqttdemo.mqtt;

import lombok.extern.slf4j.Slf4j;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.springframework.stereotype.Component;

/**
 * @author tony
 * @date 2023/1/6 14:28
 */
@Slf4j
@Component
public class MqttSubClient {

    public MqttSubClient(MqttPushClient mqttPushClient){
        subScribeDataPublishTopic();
    }


    private void subScribeDataPublishTopic(){
        //订阅tony_queue主题
       subscribe("tony_queue");
    }

    /**
     * 订阅某个主题，qos默认为0
     *
     * @param topic
     */
    public void subscribe(String topic) {
        subscribe(topic, 0);
    }

    /**
     * 订阅某个主题
     *
     * @param topic 主题名
     * @param qos
     */
    public void subscribe(String topic, int qos) {
        try {
            MqttClient client = MqttPushClient.getClient();
            if (client == null) return;
            client.subscribe(topic, qos);
            log.info("订阅主题:{}",topic);
        } catch (MqttException e) {
            e.printStackTrace();
        }
    }

}
