package com.turing.service;

import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.stereotype.Service;

@Service
@RocketMQMessageListener(consumerGroup = "consumerGroup",topic = "topic_rocket", selectorExpression = "*")
public class ProductService implements RocketMQListener {

    @Override
    public void onMessage(Object o) {
        System.out.println(o);
    }


}
