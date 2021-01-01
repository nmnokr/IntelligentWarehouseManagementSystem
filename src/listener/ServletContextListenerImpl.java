package listener;

import java.util.Properties;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import org.apache.log4j.Logger;

import listener.ReadSerialPort;
import properties.PropertiesService;
import service.ServiceFacade;;

public class ServletContextListenerImpl implements ServletContextListener {
	final Logger logger = Logger.getLogger(ServletContextListenerImpl.class);

	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		logger.debug("ServletContextListenerImpl contextInitialized metodu çalýþmaya baþladý.");
		Properties appProperties = PropertiesService.getProperties();// propertyler dosyadan okundu
		try {
			ServiceFacade.getInstance().initialize(appProperties);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// Bu Metod rs232 serii letiim iin gerekli dir
		/*
		 * Thread t = new Thread() { public void run() { // the following line will keep
		 * this app alive for 1000 seconds, // waiting for events to occur and
		 * responding to them (printing incoming // messages to console). try {
		 * Thread.sleep(1000); } catch (InterruptedException ie) { } } };
		 */

		listenerRfidUpdate main = new listenerRfidUpdate();
		main.initialize();
		Thread t = new Thread() {
			public void run() {
				try {
					Thread.sleep(1000000);
				} catch (InterruptedException ie) {
				}
			}
		};
		t.start();
		System.out.println("Started");

	}

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		logger.debug("ServletContextListenerImpl contextDestroyed metodu çalýþmaya baþladý.");

	}

}