package org.apache.rocketmq.example.delay;

import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.common.RemotingHelper;


// https://my.oschina.net/apacherocketmq/blog/5276536
public class Producer {
    public static void main(String[] args) throws Exception{
        //生产者组
        DefaultMQProducer producer = new DefaultMQProducer("delay_producer_group");

        //设置nameserver
        producer.setNamesrvAddr("localhost:9876");
        //启动生产者
        producer.start();

        //构建消息
        Message message = new Message("delayTopic","TagA","delayMessage".getBytes(RemotingHelper.DEFAULT_CHARSET));
        // 重点：设置延迟级别
        message.setDelayTimeLevel(3);
        // 发送消息
        SendResult sendResult = producer.send(message);
        // 打印发送结果
        System.out.println("发送结果："+sendResult);
        // 关闭生产者
        producer.shutdown();
    }
}