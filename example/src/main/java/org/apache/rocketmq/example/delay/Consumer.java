package org.apache.rocketmq.example.delay;

import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.common.consumer.ConsumeFromWhere;
import org.apache.rocketmq.common.message.MessageExt;

import java.util.List;

public class Consumer {
    public static void main(String[] args) throws Exception {
        // 消费者组
        DefaultMQPushConsumer consumer =  new DefaultMQPushConsumer("delay_consumer_group");
        //注册nameserver
        consumer.setNamesrvAddr("localhost:9876");

        // 订阅主题
        consumer.subscribe("delayTopic","TagA");

        // 开启消费offset
        consumer.setConsumeFromWhere(ConsumeFromWhere.CONSUME_FROM_FIRST_OFFSET);


        //监听器
        consumer.registerMessageListener(new MessageListenerConcurrently() {
            @Override
            public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> list, ConsumeConcurrentlyContext consumeConcurrentlyContext) {
                for (int i = 0; i < list.size(); i++) {
                    MessageExt messageExt = list.get(i);
                    String msg = new String(messageExt.getBody());
                    //这里主要打印延迟时间≈当前时间-消息的生产时间
                    System.out.println(msg+" 时间="+(System.currentTimeMillis()-messageExt.getBornTimestamp()));
                }
                return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
            }
        });

        consumer.start();
    }
}