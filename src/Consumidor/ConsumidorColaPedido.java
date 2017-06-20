/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Consumidor;

import javax.jms.Connection;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageConsumer;
import javax.jms.Session;
import javax.jms.TextMessage;
import org.apache.activemq.ActiveMQConnectionFactory;

/**
 *
 * @author amlazo
 */
public class ConsumidorColaPedido {
    public String consumirMensaje(String nombreCola) throws JMSException{
        ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory();
        Connection connection=connectionFactory.createConnection();
        Session session= connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        Destination cola= session.createQueue(nombreCola);
        MessageConsumer consumer=session.createConsumer(cola);
        connection.start();
        TextMessage message=(TextMessage)consumer.receive();
        String mensaje=message.getText();
        consumer.close();
        session.close();
        connection.close();
        return mensaje;
    }
}
