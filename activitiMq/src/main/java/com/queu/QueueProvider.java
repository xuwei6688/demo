package com.queu;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.command.ActiveMQTextMessage;

import javax.jms.*;

public class QueueProvider {
    public static void main(String[] args) {
        String brokerURL = "tcp://192.168.0.107:61616";
        ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory(brokerURL);
        Connection connection = null;
        Session session = null;
        try {
            connection = connectionFactory.createConnection();
            connection.start();

            //第一个参数设置true，以事物模式提交。第一个参数设置true后第二个参数忽略
            //AUTO_ACKNOWLEDGE 消费者receive后自动确认
            //CLIENT_ACKNOWLEDGE message.acknowledge()确认
            session = connection.createSession(Boolean.FALSE, Session.AUTO_ACKNOWLEDGE);

            Destination destination = session.createQueue("first-queue");
            MessageProducer producer = session.createProducer(destination);

            TextMessage textMessage = new ActiveMQTextMessage();
            textMessage.setText("hello");
            producer.send(textMessage);

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
