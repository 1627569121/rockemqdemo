package com.turing.simple;

import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.common.message.MessageExt;

import java.util.List;

/**
 * 消费者
 */
public class Consumer {

    public static void main(String[] args) throws MQClientException {
        // 1. 创建consumer对象
        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer("simple_consumer");
        // 2. 设置nameser
        consumer.setNamesrvAddr("192.168.2.110:9876");
        // 3. 订阅topic
        consumer.subscribe("topic_simple","*");
        // 4. 监听
        consumer.setMessageListener(new MessageListenerConcurrently() {
            @Override
            public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> list, ConsumeConcurrentlyContext context) {
                for (MessageExt messageExt : list) {
                    String topic = messageExt.getTopic();
                    String tags = messageExt.getTags();
                    byte[] body = messageExt.getBody();
                    String msg = new String(body, 0, body.length);
                    System.out.println(topic+":"+tags+":"+msg);
                }
                // 响应成功状态
                return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
            }
        });
        // 5. 启动
        consumer.start();
    }

}
