package dto;

import java.util.Date;

public class Product {
	private int Id;
	private String RfId;
	private String Name;
	private int CategoryId;
	private int CompanyId;
	private Date CreateDate;
	private Date DepartureDate;
	private int StatusId;
	private int ShelfId;

	public int getId() {
		return Id;
	}

	public void setId(int id) {
		Id = id;
	}

	public String getRfId() {
		return RfId;
	}

	public void setRfId(String rfId) {
		RfId = rfId;
	}

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

	public int getCategoryId() {
		return CategoryId;
	}

	public void setCategoryId(int categoryId) {
		CategoryId = categoryId;
	}

	public int getCompanyId() {
		return CompanyId;
	}

	public void setCompanyId(int companyId) {
		CompanyId = companyId;
	}

	public Date getCreateDate() {
		return CreateDate;
	}

	public void setCreateDate(Date createDate) {
		CreateDate = createDate;
	}

	public Date getDepartureDate() {
		return DepartureDate;
	}

	public void setDepartureDate(Date departureDate) {
		DepartureDate = departureDate;
	}

	public int getStatusId() {
		return StatusId;
	}

	public void setStatusId(int statusId) {
		StatusId = statusId;
	}

	public int getShelfId() {
		return ShelfId;
	}

	public void setShelfId(int shelfId) {
		ShelfId = shelfId;
	}

}
