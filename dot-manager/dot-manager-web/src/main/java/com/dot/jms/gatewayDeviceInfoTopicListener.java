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
import com.dot.service.GatewayInfoService;
import com.dot.utils.JsonUtils;



@Controller
public class gatewayDeviceInfoTopicListener implements  MessageListener {
	

	
	private static final Logger LOG = Logger.getLogger(ReceiveMessageListener.class); 
	@Autowired
	private GatewayInfoService gatewayInfoService;	
	

	@Override
	public void onMessage(Message message) {
	
	
		if(message instanceof BytesMessage ){
			LOG.info("BytesMessage incoming" );
			BytesMessage bm= (BytesMessage) message;
			
            byte[] b = new byte[1024];
            
            int len = -1;    
            String sm;
              
            try {
            	len=bm.readBytes(b);
            	if(len != -1){
            		sm = new String(b, 0, len);
            		LOG.info(sm);  
		//            TbGatewayInfo item;
	//	            item = (TbGatewayInfo) JsonUtils.jsonString2Object(sm, TbGatewayInfo.class);
	//	            item.setUpdatedTime(new Date());	
		  //          gatewayInfoService.updateRunGatewayInfo(item);		            
            	}
			} catch (JMSException e) {
				// TODO Auto-generated catch block
				System.out.println("reason "+e.getErrorCode());
				System.out.println("msg "+e.getMessage());
				System.out.println("loc "+e.getLocalizedMessage());
				System.out.println("cause "+e.getCause());
				System.out.println("excep "+e);				
				e.printStackTrace();
			}   			
			
		}
		
		if (message instanceof TextMessage) {  
            TextMessage text = (TextMessage) message;  
            try {          
	                LOG.info("Received message:" + text.getText());
		        //    TbGatewayInfo item;
		      //      item = (TbGatewayInfo) JsonUtils.jsonString2Object(text.getText(), TbGatewayInfo.class);
		    //        item.setUpdatedTime(new Date());
		          //  gatewayInfoService.updateRunGatewayInfo(item);

            } catch (JMSException e) {  
                e.printStackTrace();  
            }  
        }  
		
	}
	

	  
}