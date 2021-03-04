package com.mycompany.app;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

@MessageDriven (name = "ConsumerFirst", // MDB otrzymuje wiadomosci z kolejki queue
activationConfig = { // usługa
		@ActivationConfigProperty(propertyName = "destination", propertyValue = "java:/jboss/exported/jms/queue/test")
		})
public class ConsumerFirst implements MessageListener {

	@Override
	public void onMessage(Message message) {
		TextMessage textMessage = (TextMessage) message;
		try {
			System.out.println("ConsumerFirst: -otrzymałem wiadomość z kolejki: " + textMessage.getText() + this.getClass().toString());
		} catch (JMSException e) {
			e.printStackTrace();
		}
	}


}
