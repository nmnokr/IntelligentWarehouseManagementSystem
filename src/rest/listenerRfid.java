package rest;

import java.util.Properties;

import javax.annotation.security.PermitAll;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import listener.SerialTest;
import properties.PropertiesService;
import service.ServiceFacade;

@Path("/listenerRfid")

@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class listenerRfid {
	@Path("/addRfid")
	@GET
	@PermitAll
	public void dinleRfid() throws Exception {

		Properties appProperties = PropertiesService.getProperties();// propertyler dosyadan okundu
		try {
			ServiceFacade.getInstance().initialize(appProperties);

			SerialTest main = new SerialTest();
			main.initialize();

			Thread t = new Thread() {
				public void run() {

					try {
						Thread.sleep(100000);
					} catch (InterruptedException ie) {
					}
				}
			};
			System.out.println("baþladi");
		} catch (Exception e) {

		}

	}

}
