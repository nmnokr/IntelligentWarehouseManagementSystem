package rest;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
 
import javax.ws.rs.core.MediaType;

import listener.ReadSerialPort;
 
import dto.Product;
import dto.Rfid;
import dto.Stok;
import dto.SubCategory;
import dto.Category;
import dto.Company;
import service.ServiceFacade;

@Path("/rfid")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class RfidRest {

	@Path("/addRfid")
	@POST
	public void addRfidTest(Rfid rfid) throws Exception {
		ServiceFacade.getInstance().addRfidTest(rfid);
	}

	@Path("/deneme")
	@POST
	public void addProduct(String s) throws Exception {
		System.out.println(s);

		String[] tokens = s.split(">");
		System.out.println();
		String katagori = tokens[0];
		System.out.println(katagori);
		String firma = tokens[1];
		System.out.println(firma);
		String name = tokens[2];
		System.out.println(name);
		String rfid = tokens[3];
		System.out.println(rfid);
		List<String> a = new ArrayList<>();
		for (String t : tokens)
			a.add(t);
		ServiceFacade.getInstance().addProduct(katagori, firma, name, rfid);
	}

	@Path("/getAllStatus0")
	@GET

	public List<dto.shelf> getShelf(int index) throws Exception {
		return ServiceFacade.getInstance().getShelf(index);
	}

	@Path("/getSumWareHouse1")
	@GET

	public int getSumWareHouse1() throws Exception {
		return ServiceFacade.getInstance().getSumWareHouse1();
	}

	@Path("/getSumWareHouse2")
	@GET

	public int getSumWareHouse2() throws Exception {
		return ServiceFacade.getInstance().getSumWareHouse2();
	}

	@Path("/getSumWareHouse3")
	@GET
	public int getSumWareHouse3() throws Exception {
		return ServiceFacade.getInstance().getSumWareHouse3();
	}

	@Path("/getCompany")
	@GET
	public List<Company> getCompany() throws Exception {
		return ServiceFacade.getInstance().getCompany();

	}

	// depolari listeleme
	@Path("/getStok")
	@GET
	public List<Stok> getStok() throws Exception {
		return ServiceFacade.getInstance().getStok();

	}

	@Path("/getReadRfid")
	@GET
	public String getReadRfid() {
		String asd = "";
		try {
			System.out.println("geldi");
			ReadSerialPort Pport = new ReadSerialPort();
			System.out.println("geldi2");
			Pport.init();
			System.out.println("geldi3");
			Pport.ReadRFIDPort();
			System.out.println("geldi4");
			Thread.sleep(5000);
			asd = Pport.close();

		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			System.out.println("sýkýntý var");
		}

		return asd;
	}

	// secilen depodaki ürünleri listeleme
	@Path("/getStokProducts")
	@POST
	public List<Product> getStokProducts(int StokId) throws Exception {
		return ServiceFacade.getInstance().getStokProducts(StokId);

	}

	@Path("/updateStokProduct")
	@POST
	public void UpdateStokProduct(int rfidNo) throws Exception {
		ServiceFacade.getInstance().UpdateStokProduct(rfidNo);

	}

	@Path("/getCategory")
	@GET
	public List<Category> getCategory() throws Exception {
		return ServiceFacade.getInstance().getCategory();

	}

	@Path("/getSubCategory")
	@POST

	public List<SubCategory> getSubCategory(int categoryId) throws Exception {
		return ServiceFacade.getInstance().getSubCategory(categoryId);

	}
}