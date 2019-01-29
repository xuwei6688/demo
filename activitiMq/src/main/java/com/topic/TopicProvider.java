package com.topic;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.command.ActiveMQTextMessage;

import javax.jms.*;


public class TopicProvider {
    public static void main(String[] args) {
        String brokerURL = "tcp://192.168.0.107:61616";

        ActiveMQConnectionFactory activeMQConnectionFactory = new ActiveMQConnectionFactory(brokerURL);
        Connection connection = null;
        Session session = null;

        try {
            connection = activeMQConnectionFactory.createConnection();
            session = connection.createSession(true, Session.AUTO_ACKNOWLEDGE);

            Destination destination = session.createTopic("first-topic");
            MessageProducer producer = session.createProducer(destination);

            TextMessage message = new ActiveMQTextMessage();
            message.setText("哈哈");

            producer.send(message);
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