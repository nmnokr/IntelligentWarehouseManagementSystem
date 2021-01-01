package listener;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Properties;

import dto.Rfid;
import listener.SerialTest;
import properties.PropertiesService;
import service.ServiceFacade;

 

public class listenerAdd {
	public Long getRfid() throws Exception {
		 
			Properties appProperties = PropertiesService.getProperties();//propertyler dosyadan okundu
			try {
				ServiceFacade.getInstance().initialize(appProperties);
				//Bu Metod rs232 serii letiþim için gerekli dir
			  SerialTest main = new SerialTest();
			    main.initialize();
			    Thread t=new Thread() {
			        public void run() {
			            //the following line will keep this app alive for 1000    seconds,
			            //waiting for events to occur and responding to them    (printing incoming messages to console).
			            try {Thread.sleep(100000);} catch (InterruptedException    ie) {}
			        }
			    }; 
				System.out.println("baþladi");
			} catch (Exception e) {
				 
			}
		  return null  ;
	}
}
