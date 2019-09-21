package com.dbp.persistence;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import org.apache.log4j.Logger;
/**
 * 
 * @author Dhananjay Samanta
 *
 */
public class Test {
	private static final Logger LOGGER=Logger.getLogger(Test.class);
	public static void main(String[] args) throws SQLException, IOException {
		
		// input parameter values
		String dbProfilePath = "C:/Users/Dhananjay Samanta/Documents/workspace-sts-3.9.1.RELEASE/DBProfilePOC2/src/com/dbp/common/dbProfile.txt";
		String dbName = "XE";

		Connection con =ConnectionFactory.getConnection(dbProfilePath, dbName);
		
		System.out.println(con);
		
	}
}
