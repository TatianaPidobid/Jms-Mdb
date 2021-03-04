package com.mycompany.app;

import javax.annotation.Resource;
import javax.ejb.LocalBean;
import javax.ejb.Schedule;
import javax.ejb.Stateless;
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;

@Stateless
@LocalBean
public class Producer { // otsylaet soobsenia
   	@Resource(lookup = "java:/ConnectionFactory")
	private ConnectionFactory connectionFactory;
	
	@Resource(name = "java:/jboss/exported/jms/queue/test") // ochered w kot my hotim czto-to polozit
	private Destination destination;  // punkt naznaczenia
	
	@Schedule(hour ="*", minute = "*", second = "*/5", persistent = false) //raz w sec
	public void produceMessage() {
		try {
			Connection connection = connectionFactory.createConnection();
			Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
			MessageProducer messageProducer = session.createProducer(destination);
			TextMessage textMessage = session.createTextMessage("Hello from Producer");
			messageProducer.send(textMessage); // otsylajet soobsenia w oczered queue java:/jboss/exported/jms/queue/test
			System.out.println("--------------------------------------");
			connection.close();
			session.close();
		} catch (JMSException e) {
			e.printStackTrace();
		}
		}
}
