package com.dot.jms;

import java.util.Date;

import javax.jms.BytesMessage;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.dot.pojo.TbGatewayInfo;
import com.dot.pojo.msgReportPacket;
import com.dot.service.GatewayInfoService;
import com.dot.utils.JsonUtils;



@Controller
public class gatewayReportListener implements  MessageListener {
	

	
	private static final Logger LOG = Logger.getLogger(ReceiveMessageListener.class); 
	@Autowired
	private GatewayInfoService gatewayInfoService;	
	
	@Autowired  
    private MessageSender messageSender;    
	

	@Override
	public void onMessage(Message message) {
		
		if(message instanceof BytesMessage ){
			//LOG.info("BytesMessage incoming" );
			BytesMessage bm= (BytesMessage) message;
			
            byte[] b = new byte[1024];
            
            int len = -1;    
            String sm;
              
            try {
            	len=bm.readBytes(b);
            	if(len != -1){
            		sm = new String(b, 0, len);
            		LOG.info((new Date()) +sm); 
            		msgReportPacket pkt;
            		TbGatewayInfo item = new TbGatewayInfo();
            		pkt = (msgReportPacket) JsonUtils.jsonString2Object(sm, msgReportPacket.class);
            		item.setSerialNumber(pkt.getEsn());
            		item.setMac(pkt.getMac());
            		item.setIp(pkt.getIp());
            		item.setUpdatedTime(new Date());
            		item.setReportTime(new Date());
            		gatewayInfoService.updateRunGatewayInfo(item);
            		/////// test
            		
       
            	//	messageSender.sendMessage("good!");

            		
            	}
            } catch (JMSException e) {  
                e.printStackTrace();  
            }
		}
	}
	  
}