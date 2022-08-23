package com.turing.order;

import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.*;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.common.message.MessageExt;

import java.util.List;

/**
 * 消费者
 */
public class OrderConsumer {

    public static void main(String[] args) throws MQClientException {
        // 1. 创建consumer对象
        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer("order_consumer");
        // 2. 设置nameser
        consumer.setNamesrvAddr("192.168.2.110:9876");
        // 3. 订阅topic
        consumer.subscribe("order_topic","*");
        // 4. 监听
        consumer.setMessageListener(new MessageListenerOrderly() {
            @Override
            public ConsumeOrderlyStatus consumeMessage(List<MessageExt> list, ConsumeOrderlyContext context) {
                for (MessageExt messageExt : list) {
                    String topic = messageExt.getTopic();
                    String tags = messageExt.getTags();
                    byte[] body = messageExt.getBody();
                    String msg = new String(body, 0, body.length);
                    System.out.println(topic+":"+tags+":"+msg);
                }
                return ConsumeOrderlyStatus.SUCCESS;
            }
        });
        // 5. 启动
        consumer.start();
    }

}
