package com.turing.simple;

import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.MQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.exception.RemotingException;

/**
 * 生产者
 */
public class Producer {

    public static void main(String[] args) throws MQClientException, MQBrokerException, RemotingException, InterruptedException {
        // 1. 创建producer对象
        DefaultMQProducer producer = new DefaultMQProducer("producer_simple");
        // 2. 设置nameserv信息
        producer.setNamesrvAddr("192.168.2.110:9876");
        // 3. 启动
        producer.start();
        // 4. 创建并发送消息
        Message message = new Message("topic_simple","tag_s","hello".getBytes());
        SendResult result = producer.send(message);
        System.out.println(result);
        // 5. 关闭
        producer.shutdown();
    }

}
