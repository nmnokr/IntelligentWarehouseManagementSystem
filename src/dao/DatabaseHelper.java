package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;
import org.apache.log4j.Logger;
import bean.DatabaseProperties;

public class DatabaseHelper {
	final Logger logger = Logger.getLogger(DatabaseHelper.class);

	DatabaseProperties databaseProperties = null;

	protected void init(DatabaseProperties databaseProperties) {
		logger.debug("DatabaseHelper init metodu �al��maya ba�lad�.");
		this.databaseProperties = databaseProperties;
		logger.debug("DatabaseHelper init metodu �al��mas� bitti.");
	}

	public Connection getConnection() throws Exception {
		logger.debug("DatabaseHelper getConnection metodu �al��maya ba�lad�.");
		if (databaseProperties.isDataSource()) {
			logger.debug("DatabaseHelper getConnection metodu �al��mas� bitti.");
			return getDataSourceConnection();
		} else {
			logger.debug("DatabaseHelper getConnection metodu �al��mas� bitti.");
			return getTransactionalConnection();
		}
	}

	public Connection getRegularConnection() throws Exception {
		logger.debug("DatabaseHelper getRegularConnection metodu �al��maya ba�lad�.");
		Connection conn = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/warehousedb",
					"root","numan");
		} catch (Exception exception) {
			logger.fatal("DatabaseHelper getRegularConnection metodu exeption = " + exception);
			throw exception;
		}
		logger.debug("DatabaseHelper getRegularConnection metodu �al��mas� bitti.");
		return conn;
	}

	public Connection getDataSourceConnection() throws Exception {
		logger.debug("DatabaseHelper getDataSourceConnection metodu �al��maya ba�lad�.");
		Context initContext = new InitialContext();
		DataSource ds = (DataSource) initContext.lookup(databaseProperties.getJndiName());
		Connection conn = ds.getConnection();
		conn.setAutoCommit(false);
		logger.debug("DatabaseHelper getDataSourceConnection metodu �al��mas� bitti.");
		return conn;

	}

	public Connection getTransactionalConnection() throws Exception {
		logger.debug("DatabaseHelper getTransactionalConnection metodu �al��maya ba�lad�.");
		Connection conn = getRegularConnection();
		conn.setAutoCommit(false);
		logger.debug("DatabaseHelper getTransactionalConnection metodu �al��mas� bitti.");
		return conn;
	}

	public void closeConnection(Connection conn) throws Exception {
		logger.debug("DatabaseHelper closeConnection metodu �al��maya ba�lad�.");
		if (!conn.isClosed()) {
			try {
				conn.close();
			} catch (Exception exception) {
				logger.fatal("DatabaseHelper getTransactionalConnection metodu exeption = " + exception);
			}
		}
		logger.debug("DatabaseHelper closeConnection metodu �al��mas� bitti.");
	}
	
	public void closeResultSet(ResultSet rs) {
		logger.debug("DatabaseHelper closeResultSet metodu �al��maya ba�lad�.");
		try {
			if (rs != null)
				rs.close();
			logger.trace("closeResultSet metodu �al��t�.");
		} catch (Exception e) {
			logger.error("closeResultSet close error: " + e.getMessage());
			e.printStackTrace();
		}
		logger.debug("DatabaseHelper closeResultSet metodu �al��mas� bitti.");

	}

	public void closePreparedStatement(PreparedStatement pst) {
		logger.debug("DatabaseHelper closePreparedStatement metodu �al��maya ba�lad�.");
		try {
			if (pst != null)
				pst.close();
			logger.trace("closePreparedStatement metodu �al��maya ba�lad�.");
		} catch (Exception e) {
			logger.error("closePreparedStatement close error: " + e.getMessage());
			e.printStackTrace();
		}
		logger.debug("DatabaseHelper closePreparedStatement metodu �al��mas� bitti.");
	}

}
