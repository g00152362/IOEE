package com.dot.jms;

import javax.jms.JMSException;

import javax.jms.Message;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Component;


@Component("topicSender")
public class MessageSender {
	
	private static final Logger LOG = Logger.getLogger(MessageSender.class);
	@Autowired
	@Qualifier("jmsTopicTemplate")	
	private JmsTemplate jmsTemplate;

	public void setJmsTemplate(JmsTemplate jmsTemplate) {
	this.jmsTemplate = jmsTemplate;
	}
	
	public void sendMessage(String topic,final String message) {
		LOG.info("Send message: " + message);
		jmsTemplate.send(topic,new MessageCreator() {

		public Message createMessage(Session session) throws JMSException {
			TextMessage textMessage = session.createTextMessage(message);
			return textMessage;
		}
	
	});
	}

}