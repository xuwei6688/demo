package com.topic;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

public class TopicPerstinceConsumer {
    public static void main(String[] args) {
        String brokerURL = "tcp://192.168.0.107:61616";

        ActiveMQConnectionFactory activeMQConnectionFactory = new ActiveMQConnectionFactory(brokerURL);
        Connection connection = null;
        Session session = null;

        try {
            connection = activeMQConnectionFactory.createConnection();
            //设置持久订阅id
            connection.setClientID("id-1");
            connection.start();
            session = connection.createSession(true, Session.AUTO_ACKNOWLEDGE);

            Topic topic = session.createTopic("first-topic");
            MessageConsumer consumer = session.createDurableSubscriber(topic, "id-1");

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
