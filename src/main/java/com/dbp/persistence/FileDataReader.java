package com.dbp.persistence;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.apache.log4j.Logger;

import com.dbp.persistence.ConnectionFactory;

/**
 * 
 * @author Lijarani
 * 
 * This class has been written for common file related functionality
 *
 */
public class FileDataReader {
	private static final Logger LOGGER=Logger.getLogger(FileDataReader.class);
	
	/**
	 * This method has been written for read data from schema file data
	 * @param path
	 * @return
	 */
	public static List<String> getFileData(String filePath) {
	//	LOGGER.info("Reading data from "+filePath);
		List<String> lines = null;
		List<String> dataList = new ArrayList<>();
		try (Stream<String> stream = Files.lines(Paths.get(filePath))) {
			lines = stream.collect(Collectors.toList());
			if (!lines.isEmpty()) {
				lines.forEach(line -> dataList.add(line));
			}
			//LOGGER.debug("After Reading data from file :: "+dataList);
			return dataList;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return dataList;
	}
	
	/**
	 * This method has been written for read db properties
	 * @param path
	 * @param dbName
	 * @return
	 */
	public static String[] readDataFromDbProfile(String path,String dbName){
		LOGGER.info("Reading data from :: "+path);
		String[] tokenArr=null;
		try (Stream<String> stream = Files.lines(Paths.get(path))) {
			List<String> lines= stream.filter(item->item.contains(dbName)).collect(Collectors.toList());
			String line=lines.get(0);
			LOGGER.debug("After Reading the data from db profile :: "+line);
			tokenArr=line.split("\\|");
		} catch (IOException e) {
			e.printStackTrace();
		}
		LOGGER.debug("After Spliting the data :: "+Arrays.asList(tokenArr));
		return tokenArr;
	}
}
