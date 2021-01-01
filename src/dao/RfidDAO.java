package dao;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.Writer;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.apache.log4j.Logger;
import java.sql.PreparedStatement;
import bean.DatabaseProperties;

import dto.Category;
import dto.Company;

import dto.Product;
import dto.Rfid;
import dto.Stok;
import dto.SubCategory;

import dto.shelf;

public class RfidDAO extends DatabaseHelper {
	Logger logger = Logger.getLogger(RfidDAO.class);

	DatabaseProperties databaseProperties = null;

	public void init(Properties appProperties) {
		DatabaseProperties databaseProperties = new DatabaseProperties();
		databaseProperties.setUsername(appProperties.getProperty("dbuser"));
		databaseProperties.setPassword(appProperties.getProperty("dbpassword"));
		databaseProperties.setDatabaseConnectionURL(appProperties.getProperty("database"));
		databaseProperties.setDatabaseDriver(appProperties.getProperty("databaseDriver"));
		databaseProperties.setJndiName(appProperties.getProperty("jndiName"));
		databaseProperties.setDataSource(Boolean.parseBoolean(appProperties.getProperty("isDataSource")));
		super.init(databaseProperties);

	}

	public void addRfidTest(Rfid rfid) throws Exception {

		Connection conn = (Connection) getConnection();
		PreparedStatement stmt = null;
		StringBuilder query = new StringBuilder();

		try {
			query.append("INSERT INTO tdeneme ( id ,name )  ");
			query.append("VALUES (?,?)");
			String queryString = query.toString();

			stmt = (PreparedStatement) conn.prepareStatement(queryString);
			stmt.setString(1, rfid.getId());
			stmt.setString(2, rfid.getTur());

			stmt.executeUpdate();
			conn.commit();

		} catch (Exception e) {
			logger.error("RfidDao addRfidTest metodu exeption = " + e);
			conn.rollback();
			throw e;
		} finally {
			closePreparedStatement(stmt);
			closeConnection(conn);
		}
	}

	public int getSumWareHouse1() throws Exception {

		Connection conn = null;
		ResultSet rs = null;
		PreparedStatement preparedStatement = null;

		int temp = 0;
		try {
			String query = "SELECT  COUNT(*) From Product p,shelf s ,stok st WHERE s.Id=p.ShelfId AND st.Id=s.StokId AND st.Id=1 and  p.DepartureDate IS NULL OR p.DepartureDate >= NOW()";
			conn = getTransactionalConnection();
			preparedStatement = (PreparedStatement) conn.prepareStatement(query.toString());
			rs = preparedStatement.executeQuery();
			while (rs.next()) {
				temp = rs.getInt(1);
			}
			conn.commit();
		} catch (Exception e) {
			logger.error(" RfidDAO	 getSumWareHouse1 metodu exeption = " + e);
			conn.rollback();
			throw e;
		} finally {
			closeResultSet(rs);
			closePreparedStatement(preparedStatement);
			closeConnection(conn);
		}

		return temp;
	}

	public int getSumWareHouse2() throws Exception {

		Connection conn = null;
		ResultSet rs = null;
		PreparedStatement preparedStatement = null;

		int temp = 0;
		try {
			String query = "SELECT  COUNT(*) From Product p,shelf s ,stok st WHERE s.Id=p.ShelfId AND st.Id=s.StokId AND st.Id=2 and  p.DepartureDate IS NULL OR p.DepartureDate >= NOW()";
			conn = getTransactionalConnection();
			preparedStatement = (PreparedStatement) conn.prepareStatement(query.toString());
			rs = preparedStatement.executeQuery();
			while (rs.next()) {
				temp = rs.getInt(1);
			}
			conn.commit();
		} catch (Exception e) {
			logger.error(" 	RfidDAO  getSumWareHouse2 metodu exeption = " + e);
			conn.rollback();
			throw e;
		} finally {
			closeResultSet(rs);
			closePreparedStatement(preparedStatement);
			closeConnection(conn);
		}

		return temp;
	}

	public int getSumWareHouse3() throws Exception {

		Connection conn = null;
		ResultSet rs = null;
		PreparedStatement preparedStatement = null;

		int temp = 0;
		try {
			String query = "SELECT  COUNT(*) From Product p,shelf s ,stok st WHERE s.Id=p.ShelfId AND st.Id=s.StokId AND st.Id=3 and  p.DepartureDate IS NULL OR p.DepartureDate >= NOW()";
			conn = getTransactionalConnection();
			preparedStatement = (PreparedStatement) conn.prepareStatement(query.toString());
			rs = preparedStatement.executeQuery();
			while (rs.next()) {
				temp = rs.getInt(1);
			}
			conn.commit();
		} catch (Exception e) {
			logger.error(" 	 RfidDAO  getSumWareHouse3 metodu exeption = " + e);
			conn.rollback();
			throw e;
		} finally {
			closeResultSet(rs);
			closePreparedStatement(preparedStatement);
			closeConnection(conn);
		}

		return temp;
	}

	public ArrayList<shelf> getShelf(int index) throws Exception {
		Connection conn = null;
		ResultSet rs = null;
		PreparedStatement preparedStatement = null;
		shelf shelf;
		ArrayList<shelf> shelfs = new ArrayList<>();
		try {
			String query = "SELECT * FROM  Shelf WHERE StokId=" + index + " ";
			conn = getTransactionalConnection();
			preparedStatement = (PreparedStatement) conn.prepareStatement(query.toString());
			rs = preparedStatement.executeQuery();
			while (rs.next()) {
				shelf = new shelf();

				shelf.setRfId(rs.getString(2));
				shelf.setId(rs.getInt(1));
				shelf.setStokId(rs.getInt(3));
				shelf.setStatus(rs.getBoolean(4));

				shelfs.add(shelf);
			}
			conn.commit();
		} catch (Exception e) {
			logger.error(" RfidDAO  getShelf  metodu exeption = " + e);
			conn.rollback();
			throw e;
		} finally {
			closeResultSet(rs);
			closePreparedStatement(preparedStatement);
			closeConnection(conn);
		}

		return shelfs;
	}

	public void UpdateStokProduct5(int rfidNo) throws Exception {
		Connection conn = getTransactionalConnection();
		PreparedStatement stmt = null;
		StringBuilder query = new StringBuilder();
		try {

			String queryString = " UPDATE product p SET p.StatusId=5 WHERE p.id=?";
			stmt = (PreparedStatement) conn.prepareStatement(queryString);

			stmt.setInt(1, rfidNo);

			stmt.executeUpdate();
			conn.commit();
		} catch (Exception e) {
			logger.error(" RfidDAO UpdateStokProduct5 metodu exeption = " + e);
			conn.rollback();
			throw e;
		} finally {
			closePreparedStatement(stmt);
			closeConnection(conn);
		}
		Date date = new Date();

		// display time and date
		String str = String.format("  : %tc", date);

		String konum = "C:\\\\Users\\\\Numan\\\\git\\\\depoDenetlemesistemi\\\\WarehouseManagementSystem\\\\ProductLog.txt";

		Writer output;
		output = new BufferedWriter(new FileWriter(konum, true));
		output.append("\n" + rfidNo + " Rfid nolu Ürün" + str + "'tarihinde Cıksı Islemı Baslamıstır");

		output.close();

	}

	//
	public ArrayList<Product> getStokProducts(int StokId) throws Exception {
		Connection conn = null;
		ResultSet rs = null;
		PreparedStatement preparedStatement = null;
		Product product;
		ArrayList<Product> products = new ArrayList<>();
		try {
			String query = "SELECT p.Id,p.name,p.CreateDate,p.Rfid FROM  product p,shelf s WHERE s.StokId='" + StokId
					+ "' AND s.Id=p.ShelfId AND s.Status=TRUE AND p.StatusId=4 AND p.DepartureDate IS NULL -- Depo1'deki ürünleri listeler.\n"
					+ " ";
			conn = getTransactionalConnection();
			preparedStatement = (PreparedStatement) conn.prepareStatement(query.toString());
			rs = preparedStatement.executeQuery();
			while (rs.next()) {
				product = new Product();
				product.setId(rs.getInt(1));

				product.setName(rs.getString(2));
				product.setCreateDate(rs.getDate(3));
				product.setRfId(rs.getString(4));

				products.add(product);
			}
			conn.commit();
		} catch (Exception e) {
			logger.error(" RfidDAO	 getStokProducts metodu exeption = " + e);
			conn.rollback();
			throw e;
		} finally {
			closeResultSet(rs);
			closePreparedStatement(preparedStatement);
			closeConnection(conn);
		}

		return products;
	}

	public int getCountStok() throws Exception {

		Connection conn = null;
		ResultSet rs = null;
		PreparedStatement preparedStatement = null;

		int countStok = 0;
		try {
			String query = "SELECT COUNT(*) FROM Stok";
			conn = getTransactionalConnection();
			preparedStatement = (PreparedStatement) conn.prepareStatement(query.toString());
			rs = preparedStatement.executeQuery();
			while (rs.next()) {
				countStok = rs.getInt(1);
			}
			conn.commit();
		} catch (Exception e) {
			logger.error("RfidDAO  getCountStok	 getCountStok metodu exeption = " + e);
			conn.rollback();
			throw e;
		} finally {
			closeResultSet(rs);
			closePreparedStatement(preparedStatement);
			closeConnection(conn);
		}

		return countStok;
	}

	public ArrayList<Product> getStatusId(int statusIndex) throws Exception {

		Connection conn = null;
		ResultSet rs = null;
		PreparedStatement preparedStatement = null;
		Product product;
		ArrayList<Product> products = new ArrayList<>();
		try {
			String query = "SELECT * FROM Product WHERE StatusId=" + statusIndex + " ";
			conn = getTransactionalConnection();
			preparedStatement = (PreparedStatement) conn.prepareStatement(query.toString());
			rs = preparedStatement.executeQuery();
			while (rs.next()) {
				product = new Product();

				product.setId(rs.getInt(1));
				product.setRfId(rs.getString(2));

				product.setName(rs.getString(3));

				product.setCategoryId(rs.getInt(4));
				product.setCompanyId(rs.getInt(5));
				product.setCreateDate(rs.getDate(6));
				product.setDepartureDate(rs.getDate(7));
				product.setStatusId(rs.getInt(8));
				product.setShelfId(rs.getInt(9));

				products.add(product);
			}
			conn.commit();
		} catch (Exception e) {
			logger.error(" 	RfidDAO getStatusId metodu exeption = " + e);
			conn.rollback();
			throw e;
		} finally {
			closeResultSet(rs);
			closePreparedStatement(preparedStatement);
			closeConnection(conn);
		}

		return products;
	}

	public Map<String, List> getAllStatus1() throws Exception {
		Map<String, List> mapList = new HashMap<String, List>();

		Connection conn = null;
		ResultSet rs = null;
		PreparedStatement preparedStatement = null;

		try {
			String query = "SELECT p.Name AS  AD,p.statusId ,c.Name AS KategoriAdı,co.Name AS Şirket_Adı,p.CreateDate AS Giriş_Tarihi,\n"
					+ "  s.RfId AS RfIdNo " + "  FROM product p , shelf s,category c,stok st,company co WHERE"
					+ "  c.Id=p.CategoryId AND st.Id=s.StokId AND co.Id=p.CompanyId and p.ShelfId=s.Id  ";
			conn = getTransactionalConnection();
			preparedStatement = (PreparedStatement) conn.prepareStatement(query.toString());
			rs = preparedStatement.executeQuery();
			while (rs.next()) {
				List<String> strings = new ArrayList<String>();
				strings.add(rs.getString(1));
				strings.add(rs.getString(2));
				strings.add(rs.getString(3));
				mapList.put(rs.getString(6), strings);

			}
			conn.commit();
		} catch (Exception e) {
			logger.error(" RfidDAO	 getAllStatus1 metodu exeption = " + e);
			conn.rollback();
			throw e;
		} finally {
			closeResultSet(rs);
			closePreparedStatement(preparedStatement);
			closeConnection(conn);
		}

		return mapList;

	}

	public void UpdateRfidDoor(String rfid, int status) throws Exception {
		Connection conn = null;
		PreparedStatement preparedStatement = null;
		try {
			String query = " UPDATE product p SET p.StatusId=" + status + " WHERE p.RfId=? ";
			conn = getTransactionalConnection();
			System.out.println(query);
			preparedStatement = (PreparedStatement) conn.prepareStatement(query);
			preparedStatement.setString(1, rfid);

			preparedStatement.executeUpdate();
			conn.commit();
		} catch (Exception e) {
			logger.error("RfidDAO UpdateRfidDoor   metodu exeption = " + e);
			conn.rollback();
			throw e;
		} finally {
			closePreparedStatement(preparedStatement);
			closeConnection(conn);
		}

		Date date = new Date();

		String str = String.format("  : %tc", date);

		String konum = "C:\\\\Users\\\\Numan\\\\git\\\\depoDenetlemesistemi\\\\WarehouseManagementSystem\\\\ProductLog.txt";

		Writer output;
		output = new BufferedWriter(new FileWriter(konum, true));
		output.append("\n" + rfid + " Rfid nolu Ürün" + str + "'tarihinde Ürün depoya giriş yapmiştir.");

		output.close();
	}

	public ArrayList<Company> getCompany() throws Exception {
		Connection conn = null;
		ResultSet rs = null;
		PreparedStatement preparedStatement = null;
		Company company;
		ArrayList<Company> companies = new ArrayList<>();
		try {
			String query = "SELECT * FROM company ";
			conn = getTransactionalConnection();
			preparedStatement = (PreparedStatement) conn.prepareStatement(query.toString());
			rs = preparedStatement.executeQuery();
			while (rs.next()) {
				company = new Company();

				company.setId(rs.getLong(1));
				company.setName(rs.getString(2));
				company.setAddress(rs.getString(3));
				company.setPhone(rs.getLong(4));

				companies.add(company);
			}
			conn.commit();
		} catch (Exception e) {
			logger.error(" RfidDAO 	 getCompany metodu exeption = " + e);
			conn.rollback();
			throw e;
		} finally {
			closeResultSet(rs);
			closePreparedStatement(preparedStatement);
			closeConnection(conn);
		}

		return companies;
	}

	public ArrayList<Stok> getStok() throws Exception {
		Connection conn = null;
		ResultSet rs = null;
		PreparedStatement preparedStatement = null;
		Stok stok;
		ArrayList<Stok> stoks = new ArrayList<>();
		try {
			String query = "SELECT * FROM stok ";
			conn = getTransactionalConnection();
			preparedStatement = (PreparedStatement) conn.prepareStatement(query.toString());
			rs = preparedStatement.executeQuery();
			while (rs.next()) {
				stok = new Stok();

				stok.setId(rs.getInt(1));
				stok.setName(rs.getString(2));
				stok.setAddress(rs.getString(3));
				stok.setPhone(rs.getString(4));
				stoks.add(stok);
			}
			conn.commit();
		} catch (Exception e) {
			logger.error("RfidDAO 	 getStok metodu exeption = " + e);
			conn.rollback();
			throw e;
		} finally {
			closeResultSet(rs);
			closePreparedStatement(preparedStatement);
			closeConnection(conn);
		}

		return stoks;
	}

	public ArrayList<Category> getCategory() throws Exception {
		Connection conn = null;
		ResultSet rs = null;
		PreparedStatement preparedStatement = null;
		Category category;
		ArrayList<Category> categories = new ArrayList<>();
		try {
			String query = "SELECT  * FROM category";
			conn = getTransactionalConnection();
			preparedStatement = (PreparedStatement) conn.prepareStatement(query.toString());
			rs = preparedStatement.executeQuery();
			while (rs.next()) {
				category = new Category();
				category.setId(rs.getLong(1));

				category.setName(rs.getString(2));

				categories.add(category);
			}
			conn.commit();
		} catch (Exception e) {
			logger.error(" 	 RfidDAO getCategory metodu exeption = " + e);
			conn.rollback();
			throw e;
		} finally {
			closeResultSet(rs);
			closePreparedStatement(preparedStatement);
			closeConnection(conn);
		}

		return categories;
	}

	public ArrayList<SubCategory> getSubCategory(int categoryId) throws Exception {
		Connection conn = null;
		ResultSet rs = null;
		PreparedStatement preparedStatement = null;
		SubCategory subCategory;
		ArrayList<SubCategory> subCategories = new ArrayList<>();
		try {
			String query = "SELECT  id,Name FROM subcategory WHERE CategoryId='" + categoryId + "'";
			conn = getTransactionalConnection();
			preparedStatement = (PreparedStatement) conn.prepareStatement(query.toString());
			rs = preparedStatement.executeQuery();
			while (rs.next()) {
				subCategory = new SubCategory();
				subCategory.setId(rs.getLong(1));

				subCategory.setName(rs.getString(2));

				subCategories.add(subCategory);
			}
			conn.commit();
		} catch (Exception e) {
			logger.error(" RfidDAO	 getSubCategory metodu exeption = " + e);
			conn.rollback();
			throw e;
		} finally {
			closeResultSet(rs);
			closePreparedStatement(preparedStatement);
			closeConnection(conn);
		}

		return subCategories;
	}

	public Map<String, List> getAllWarehouseProducts(int status) throws Exception {
		Map<String, List> mapList = new HashMap<String, List>();
		int i = 0;
		Connection conn = null;
		ResultSet rs = null;
		PreparedStatement preparedStatement = null;
		Product products;
		shelf shelf;
		try {
			String query = "SELECT p.RfId,p.Name,c.Name,p.ShelfId FROM product p,shelf s,stok st,category c WHERE c.Id=p.CategoryId "
					+ "and p.ShelfId=s.Id AND s.StokId=st.Id   AND p.StatusId='" + status + "' "
					+ " AND  p.DepartureDate IS NULL OR p.DepartureDate >= NOW() ";
			conn = getTransactionalConnection();
			preparedStatement = (PreparedStatement) conn.prepareStatement(query.toString());
			rs = preparedStatement.executeQuery();
			while (rs.next()) {
				List<String> strings = new ArrayList<String>();
				strings.add(rs.getString(1));
				strings.add(rs.getString(2));
				strings.add(rs.getString(3));
				strings.add("" + rs.getInt(4));

				System.out.println(rs.getString(1));
				mapList.put("" + i, strings);
				i++;
			}
			conn.commit();
		} catch (Exception e) {
			logger.error(" RfidDAO	 getAllWarehouseProducts metodu exeption = " + e);
			conn.rollback();
			throw e;
		} finally {
			closeResultSet(rs);
			closePreparedStatement(preparedStatement);
			closeConnection(conn);
		}

		return mapList;

	}

	public String getProductRfid(String ProductId) throws Exception {

		Connection conn = null;
		ResultSet rs = null;
		PreparedStatement preparedStatement = null;

		String temp = "";
		try {
			String query = "SELECT s.RfId FROM product p,shelf s WHERE p.ShelfId=s.Id AND p.RfId='" + ProductId + "'";
			conn = getTransactionalConnection();
			preparedStatement = (PreparedStatement) conn.prepareStatement(query.toString());
			rs = preparedStatement.executeQuery();
			while (rs.next()) {
				temp = rs.getString(1);
				System.out.println("gelen" + temp);
			}
			conn.commit();
		} catch (Exception e) {
			logger.error("RfidDAO 	 getProductRfid metodu exeption = " + e);
			conn.rollback();
			throw e;
		} finally {
			closeResultSet(rs);
			closePreparedStatement(preparedStatement);
			closeConnection(conn);
		}

		return temp.toString();
	}

	public void UpdateProduct4(String productRfid) throws Exception {
		Connection conn = getTransactionalConnection();
		PreparedStatement stmt = null;
		StringBuilder query = new StringBuilder();
		try {

			String queryString = "UPDATE product p SET  p.StatusId=4 WHERE p.RfId='" + productRfid + "'";
			stmt = (PreparedStatement) conn.prepareStatement(queryString);
			System.out.println("güncelenen ürün " + productRfid);
			// stmt.setString(1, productRfid);

			stmt.executeUpdate();
			conn.commit();
		} catch (Exception e) {
			logger.error(" RfidDAO UpdateProduct4 metodu exeption = " + e);
			conn.rollback();
			throw e;
		} finally {
			closePreparedStatement(stmt);
			closeConnection(conn);
		}
		Date date = new Date();

		// display time and date
		String str = String.format("  : %tc", date);

		String konum = "C:\\\\Users\\\\Numan\\\\git\\\\depoDenetlemesistemi\\\\WarehouseManagementSystem\\\\ProductLog.txt";

		Writer output;
		output = new BufferedWriter(new FileWriter(konum, true));
		output.append("\n" + productRfid + " Rfid nolu Ürün" + str + "'tarihinde Ürün raf'a yerleşmiştir");

		output.close();

	}

	public void UpdateProduct2(String productRfid) throws Exception {
		Connection conn = getTransactionalConnection();
		PreparedStatement stmt = null;
		StringBuilder query = new StringBuilder();
		try {

			String queryString = "UPDATE product p SET  p.StatusId=2 WHERE p.RfId='" + productRfid + "'";
			stmt = (PreparedStatement) conn.prepareStatement(queryString);
			System.out.println("güncelenen ürün " + productRfid);
			// stmt.setString(1, productRfid);

			stmt.executeUpdate();
			conn.commit();
		} catch (Exception e) {
			logger.error("UpdateProduct2 metodu exeption = " + e);
			conn.rollback();
			throw e;
		} finally {
			closePreparedStatement(stmt);
			closeConnection(conn);
		}
		Date date = new Date();

		// display time and date
		String str = String.format("  : %tc", date);

		String konum = "C:\\\\Users\\\\Numan\\\\git\\\\depoDenetlemesistemi\\\\WarehouseManagementSystem\\\\ProductLog.txt";

		Writer output;
		output = new BufferedWriter(new FileWriter(konum, true));
		output.append("\n" + productRfid + " Rfid nolu Ürün" + str + "'tarihinde Ürün Personel Tarafindan alınmiştir");

		output.close();

	}

	public void UpdateProduc6(String productRfid) throws Exception {
		Connection conn = getTransactionalConnection();
		PreparedStatement stmt = null;
		StringBuilder query = new StringBuilder();
		try {

			String queryString = "UPDATE product p SET  p.StatusId=6 WHERE p.RfId='" + productRfid + "'";
			stmt = (PreparedStatement) conn.prepareStatement(queryString);
			System.out.println("güncelenen ürün " + productRfid);
			// stmt.setString(1, productRfid);

			stmt.executeUpdate();
			conn.commit();
			Date date = new Date();

			// display time and date
			String str = String.format("  : %tc", date);

			String konum = "C:\\\\Users\\\\Numan\\\\git\\\\depoDenetlemesistemi\\\\WarehouseManagementSystem\\\\ProductLog.txt";

			Writer output;
			output = new BufferedWriter(new FileWriter(konum, true));
			output.append("\n" + productRfid + " Rfid nolu Ürün" + str + "'tarihinde Ürün raf'tan alınmiştir");

			output.close();
		} catch (Exception e) {
			logger.error("UpdateProduc6 metodu exeption = " + e);
			conn.rollback();
			throw e;
		} finally {
			closePreparedStatement(stmt);
			closeConnection(conn);
		}
	
	
	}

	public ArrayList<shelf> getEmptyShelf(int StokId) throws Exception {
		Connection conn = null;
		ResultSet rs = null;
		PreparedStatement preparedStatement = null;
		shelf shelf;
		ArrayList<shelf> shelfs = new ArrayList<>();
		try {
			String query = "SELECT * FROM Shelf   WHERE STATUS=0 AND StokId=" + StokId + "";
			conn = getTransactionalConnection();
			preparedStatement = (PreparedStatement) conn.prepareStatement(query.toString());
			rs = preparedStatement.executeQuery();
			while (rs.next()) {
				shelf = new shelf();
				shelf.setId(rs.getInt(1));

				shelfs.add(shelf);
			}
			conn.commit();
		} catch (Exception e) {
			logger.error(" 	 getEmptyShelf metodu exeption = " + e);
			conn.rollback();
			throw e;
		} finally {
			closeResultSet(rs);
			closePreparedStatement(preparedStatement);
			closeConnection(conn);
		}

		return shelfs;
	}

	public void addProduct(String katagori, String firma, String isim, String rfid) throws Exception {
		int k = 0;

		ArrayList<shelf> shelfs = new ArrayList<>();
		String s = katagori.toString();

		if (s.equals("Bilgisayar")) {
			k = 1;
		} else if (s.equals("Telefon")) {
			k = 2;
		} else if (s.equals("Beyaz Eşya")) {
			k = 3;
		}
		shelfs = getEmptyShelf(k);
 
		int random = (int) (Math.random() * shelfs.size() + 0);
		System.out.println(random);
		int shelfid = shelfs.get(random).getId();
		System.out.println(shelfid);
		Connection conn = (Connection) getConnection();
		PreparedStatement stmt = null;
		StringBuilder query = new StringBuilder();
		
		PreparedStatement stmt2 = null;
 
		try {
			query.append(
					"INSERT INTO product (   RfId  ,Name  ,CategoryId  ,CompanyId  ,CreateDate  ,StatusId  ,ShelfId )  ");
			query.append("VALUES ('" + rfid + "','" + isim + "'," + k + ",1,CURTIME(),1," + shelfid + ")");

			String queryString = query.toString();
			System.out.println("sorgu" + queryString);
			stmt = (PreparedStatement) conn.prepareStatement(queryString);

			stmt.executeUpdate();

			stmt2 = (PreparedStatement) conn.prepareStatement(" UPDATE shelf s SET s.Status=true WHERE s.Id=" + shelfid + "  ");
			stmt2.executeUpdate();

			conn.commit();
			Date date = new Date();

			// display time and date
			String str = String.format("  : %tc", date);

			String konum = "C:\\\\Users\\\\Numan\\\\git\\\\depoDenetlemesistemi\\\\WarehouseManagementSystem\\\\ProductLog.txt";

			Writer output;
			output = new BufferedWriter(new FileWriter(konum, true));
			output.append("\n" + " Rfid nolu:" + rfid + "İsim:" + isim + " Ürün " + str
					+ "'Tarihinde Ürün Eklenmiştir. Ve Depo alanında Beklemektedir.");

			output.close();
		} catch (Exception e) {
			logger.error("  addProduct metodu exeption = " + e);
			System.out.println("hata");
			conn.rollback();
			Date date = new Date();

			// display time and date
			String str = String.format("  : %tc", date);

			String konum = "C:\\Users\\Numan\\git\\depoDenetlemesistemi\\WarehouseManagementSystem\\uyariLog.txt";

			Writer output;
			output = new BufferedWriter(new FileWriter(konum, true));
			output.append("\n" + " Rfid nolu:" + rfid + "İsim:" + isim + " Ürün " + str
					+ "'Tarihinde Ürün Ekleme Aşamasinda Hata Meydana Gelmiştir");

			output.close();

			throw e;
		} finally {
			closePreparedStatement(stmt);
			closeConnection(conn);
		}
	}

	public ArrayList<Product> getRfidStatus() throws Exception {
		Connection conn = null;
		ResultSet rs = null;
		PreparedStatement preparedStatement = null;
		Product product;
		ArrayList<Product> products = new ArrayList<>();
		try {
			String query = "Select RfId,StatusId From Product Where StatusId=2 OR StatusId=6";
			conn = getTransactionalConnection();
			preparedStatement = (PreparedStatement) conn.prepareStatement(query.toString());
			rs = preparedStatement.executeQuery();
			while (rs.next()) {
				product = new Product();

				product.setRfId(rs.getString(1));
				product.setStatusId(rs.getInt(2));
				products.add(product);
			}
			conn.commit();
		} catch (Exception e) {
			logger.error(" 	 getRfidStatus metodu exeption = " + e);
			conn.rollback();
			throw e;
		} finally {
			closeResultSet(rs);
			closePreparedStatement(preparedStatement);
			closeConnection(conn);
		}

		return products;
	}

	public int productRfidStatus(String rfid) throws Exception {
		Connection conn = null;
		ResultSet rs = null;
		PreparedStatement preparedStatement = null;
		int temp = 0;
		try {
			String query = "Select StatusId From Product Where RfId='" + rfid + "'";
			conn = getTransactionalConnection();
			preparedStatement = (PreparedStatement) conn.prepareStatement(query.toString());
			rs = preparedStatement.executeQuery();
			while (rs.next()) {
				temp = rs.getInt(1);
			}
			conn.commit();
		} catch (Exception e) {
			logger.error(" 	 productRfidStatus metodu exeption = " + e);
			conn.rollback();
			throw e;
		} finally {
			closeResultSet(rs);
			closePreparedStatement(preparedStatement);
			closeConnection(conn);
		}

		return temp;
	}

	public static void main(String[] args) {
		RfidDAO dao = new RfidDAO();
		try {
			ArrayList<shelf> shelfs = new ArrayList<>();
			shelfs = dao.getEmptyShelf(1);
			System.out.println(shelfs.size());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}