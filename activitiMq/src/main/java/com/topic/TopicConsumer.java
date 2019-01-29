package com.topic;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.command.ActiveMQTextMessage;

import javax.jms.*;

/**
 * topic模式下默认非持久订阅，也就是说消费者只能接到启动订阅后生产者发出的消息
 */
public class TopicConsumer {
    public static void main(String[] args) {
        String brokerURL = "tcp://192.168.0.107:61616";

        ActiveMQConnectionFactory activeMQConnectionFactory = new ActiveMQConnectionFactory(brokerURL);
        Connection connection = null;
        Session session = null;

        try {
            connection = activeMQConnectionFactory.createConnection();
            connection.start();
            session = connection.createSession(true, Session.AUTO_ACKNOWLEDGE);

            Destination destination = session.createTopic("first-topic");
            MessageConsumer consumer = session.createConsumer(destination);

            TextMessage message = (TextMessage) consumer.receive();
            System.out.println(message.getText());
            session.commit();
        } catch (JMSException e) {
            e.printStackTrace();
        }finally {
            if(session != null) {
                try {
                    session.close();
                } catch (JMSException e) {
                    e.printStackTrace();
                }
            }
            if (connection != null){
                try {
                    connection.close();
                } catch (JMSException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
