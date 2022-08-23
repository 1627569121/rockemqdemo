package com.turing.order;

import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.MessageQueueSelector;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.common.message.MessageQueue;
import org.apache.rocketmq.remoting.exception.RemotingException;

import java.util.List;

/**
 * 生产者
 */
public class OrderProducer {

    public static void main(String[] args) throws MQClientException, MQBrokerException, RemotingException, InterruptedException {
        // 1. 创建producer对象
        DefaultMQProducer producer = new DefaultMQProducer("order_producer");
        // 2. 设置nameserv信息
        producer.setNamesrvAddr("192.168.2.110:9876");
        // 3. 启动
        producer.start();
        // 4. 创建并发送消息
        for (int i = 0; i < 5; i++) {
            Message message = new Message("order_topic","tag_o",("hello"+i).getBytes());

            // 延时消息(10s之后)
            message.setDelayTimeLevel(3);

            SendResult result = producer.send(message, new MessageQueueSelector() {
                @Override
                public MessageQueue select(List<MessageQueue> list, Message message, Object arg) {
                    // 选择发送此消息到哪个mq
                    Integer index = (Integer) arg;
                    return list.get(index);
                }
            },1);
            System.out.println(result);
        }
        // 5. 关闭
        producer.shutdown();
    }

}
