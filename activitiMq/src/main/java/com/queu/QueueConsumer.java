package com.queu;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

public class QueueConsumer {
    public static void main(String[] args) throws JMSException {
        String brokerURL = "tcp://192.168.0.107:61616";
        ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory(brokerURL);
        Connection connection = null;
        Session session = null;
        try {
            connection = connectionFactory.createConnection();
            //开启接收消息
            connection.start();
            session = connection.createSession(Boolean.TRUE, Session.AUTO_ACKNOWLEDGE);

            Destination destination = session.createQueue("first-queue");
            MessageConsumer consumer = session.createConsumer(destination);

            //阻塞，直到收到消息，只收一条
            TextMessage message = (TextMessage)consumer.receive();
            System.out.println(message.getText());
//            session.commit();
        } catch (JMSException e) {
            e.printStackTrace();
        }finally {
            if (session != null) {
                try {
                    session.close();
                } catch (JMSException e) {
                    e.printStackTrace();
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (JMSException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
