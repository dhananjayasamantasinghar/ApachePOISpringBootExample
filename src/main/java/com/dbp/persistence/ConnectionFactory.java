package com.dbp.persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.apache.log4j.Logger;

/**
 * 
 * @author Dhananjay Samanta
 *
 */
public class ConnectionFactory {

	private static final Logger LOGGER=Logger.getLogger(ConnectionFactory.class);
	/**
	 * This method has been written for get the connection object
	 * 
	 * @return
	 * @throws SQLException
	 */
	public static Connection getConnection(String path, String dbName) throws SQLException {
		
		Connection connection;
		String[] dbProperties = FileDataReader.readDataFromDbProfile(path, dbName);

		if (dbProperties != null && dbProperties.length == 6) {
			String url = dbProperties[0];
			String host = dbProperties[1];
			String port = dbProperties[2];
			String sid = dbProperties[3];
			String userName = dbProperties[4];
			String password = dbProperties[5];
			try {
				StringBuffer actualUrl = new StringBuffer().append(url).append(DBConstants.ORACLE_SEPARATOR).append(host)
						.append(DBConstants.URL_SEPARATOR).append(port).append(DBConstants.URL_SEPARATOR).append(sid);

				LOGGER.debug("DB URL :: "+actualUrl);
				connection = DriverManager.getConnection(actualUrl.toString(), userName, password);
				LOGGER.info("Connection Established Successfully");
			} catch (Exception e) {
				LOGGER.error(e.getMessage());
				throw new RuntimeException(e.getMessage());
			}
		} else {
			LOGGER.error("DBProfile Data missing");
			throw new RuntimeException("DBProfile Data missing");
		}
		return connection;
	}

}
