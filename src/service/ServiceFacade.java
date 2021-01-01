package service;

import java.util.ArrayList;
 
import java.util.Properties;

import org.apache.log4j.Logger;

import dao.RfidDAO;

 
import dto.Rfid;
import dto.Stok;
import dto.SubCategory;
import dto.Category;
import dto.Company;
import dto.Product;
import dto.shelf;

public class ServiceFacade {
	private static ServiceFacade serviceFacade;
	final static Logger logger = Logger.getLogger(ServiceFacade.class);

	private RfidDAO personelDAO = null;

	private ServiceFacade() {
		logger.info("ServiceFacade nesnesi oluï¿½turuldu.");
	}

	public static ServiceFacade getInstance() {
		logger.info("ServiceFacade getInstance  metodu Calısmaya basladi");
		if (serviceFacade == null) {
			serviceFacade = new ServiceFacade();
		}
		return serviceFacade;
	}

	public void initialize(Properties appProperties) throws Exception {
		logger.info("ServiceFacade initialize  metodu Calismaya basladi");

		personelDAO = new RfidDAO();

		personelDAO.init(appProperties);

		logger.debug("ServiceFacade initialize metodu Calismasi bitti.");
	}

	public void shutdown() {
		logger.info("ServiceFacade shutdown  metodu Calisması basladi");
	}

	/////////////////////////////////////////////////////////////////////////////////////////
	public void addRfidTest(Rfid rfid) throws Exception {
		System.out.println("geldi");
		personelDAO.addRfidTest(rfid);
	}

	public ArrayList<shelf> getShelf(int index) throws Exception {
		return personelDAO.getShelf(index);
	}
	public ArrayList<Company> getCompany() throws Exception {
		return personelDAO.getCompany();
	}
	public ArrayList<Category> getCategory() throws Exception {
		return personelDAO.getCategory();
	}
	public ArrayList<Stok> getStok() throws Exception {
		return personelDAO.getStok();
	}
	public ArrayList<Product> 	getStokProducts(int StokId) throws Exception {
		return personelDAO.getStokProducts(StokId);
	}
	public int 	getSumWareHouse1() throws Exception {
		return personelDAO.getSumWareHouse1();
	}
	public int 	getSumWareHouse2() throws Exception {
		return personelDAO.getSumWareHouse2();
	}
	public int 	getSumWareHouse3() throws Exception {
		return personelDAO.getSumWareHouse3();
	}
	public void	UpdateStokProduct(int rfidNo) throws Exception {
		  personelDAO.UpdateStokProduct5(rfidNo);
	}

	public ArrayList<SubCategory> getSubCategory(int categoryId ) throws Exception {
		return personelDAO.getSubCategory(categoryId);
	}
	
	public void addProduct(String katagori,String firma,String name,String rfid) throws Exception {
 		personelDAO.addProduct(katagori,firma,name,rfid);
	}

	
	
}
