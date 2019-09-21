package com.realspeed.extractor;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Component;

import com.realspeed.model.AgeModel;
import com.realspeed.model.AmountModel;
import com.realspeed.model.DocumentModel;
import com.realspeed.model.MainDataModel;
@Component
public class ExcelDataExtractor implements IDataExtractor {
	private static final String FILE_NAME = "src/main/resources/data/data.xlsx";
	private String document;
	/**
	 * {@inheritDoc}
	 */
	@Override
	public String extractData(int age, int amount) {
		try {
			int ageId = 0;
			Map<String, List> m = readFromSheet(2, AgeModel.class);
			List<AgeModel> l = m.get(AgeModel.class.getSimpleName());
			for (AgeModel ageModel : l) {
				String range = ageModel.getAge_Range();
				String[] tokens = range.split("-");
				int startAge = Integer.parseInt(tokens[0].trim());
				int endAge = Integer.parseInt(tokens[1].trim());
				for (int i = startAge; i <= endAge; i++) {

					if (i == age) {
						ageId = ageModel.getAge_id();
					}
				}
			}
			int amountId = 0;
			Map<String, List> m1 = readFromSheet(3, AmountModel.class);
			List<AmountModel> l1 = m1.get(AmountModel.class.getSimpleName());
			for (AmountModel amountModel : l1) {
				String range = amountModel.getAmountRange();
				String[] tokens = range.split("-");
				int amountStart = Integer.parseInt(tokens[0].trim());
				int amountEnd = Integer.parseInt(tokens[1].trim());
				for (int i = amountStart; i <= amountEnd; i++) {

					if (i == amount) {
						amountId = amountModel.getAmount_id();
					}
				}
			}
			int docId = 0;
			Map<String, List> m2 = readFromSheet(1, MainDataModel.class);
			List<MainDataModel> l2 = m2.get(MainDataModel.class.getSimpleName());
			for (MainDataModel mainDataModel : l2) {
				if (ageId == mainDataModel.getAge() && amountId == mainDataModel.getAmount()) {
					System.out.println("AgeID :: " + ageId + "  AmountId :: " + amountId + "  :: Document Id  :: "
							+ mainDataModel.getDocument());
					docId = mainDataModel.getDocument();
				}
			}
			
			if(docId!=0){
				Map<String, List> m3 = readFromSheet(4, DocumentModel.class);
				List<DocumentModel> l3 = m3.get(DocumentModel.class.getSimpleName());
				for (DocumentModel documentModel : l3) {
					if(documentModel.getId()==docId)
						document=documentModel.getDocument();
				}
			}else{
				throw new RuntimeException("Combination of age and amount is not available in Main Sheet!!");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return document;
	}

	/**
	 * This method has been written for extract the data from excel file base on
	 * input as sheetNo and populate into a data object and return collection of
	 * object
	 * 
	 * @param sheetNo
	 * @param clazz
	 * @return
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 */
	private Map<String, List> readFromSheet(int sheetNo, Class clazz)
			throws InstantiationException, IllegalAccessException {
		Map<String, List> map = new HashMap<>();
		List<Object> list = new ArrayList<>();
		try {
			FileInputStream excelFile = new FileInputStream(new File(FILE_NAME));
			Workbook workbook = new XSSFWorkbook(excelFile);
			org.apache.poi.ss.usermodel.Sheet datatypeSheet = workbook.getSheetAt(sheetNo - 1);
			Iterator<Row> iterator = datatypeSheet.iterator();
			AgeModel ageModel = null;
			AmountModel amountModel = null;
			DocumentModel documentModel = null;
			MainDataModel mainDataModel = null;
			while (iterator.hasNext()) {
				if (clazz.isAssignableFrom(AgeModel.class)) {
					ageModel = AgeModel.class.newInstance();
				} else if (clazz.isAssignableFrom(AmountModel.class)) {
					amountModel = AmountModel.class.newInstance();
				} else if (clazz.isAssignableFrom(DocumentModel.class)) {
					documentModel = DocumentModel.class.newInstance();
				} else if (clazz.isAssignableFrom(MainDataModel.class)) {
					mainDataModel = MainDataModel.class.newInstance();
				}
				Row currentRow = iterator.next();
				Iterator<Cell> cellIterator = currentRow.iterator();
				while (cellIterator.hasNext()) {
					Cell currentCell = cellIterator.next();
					if (clazz.isAssignableFrom(AgeModel.class)) {
						if (currentCell.getCellTypeEnum() == CellType.STRING) {
							ageModel.setAge_Range(currentCell.getStringCellValue());
						} else if (currentCell.getCellTypeEnum() == CellType.NUMERIC) {
							ageModel.setAge_id(new Double(currentCell.getNumericCellValue()).intValue());
						}
					}
					if (clazz.isAssignableFrom(AmountModel.class)) {
						if (currentCell.getCellTypeEnum() == CellType.STRING) {
							amountModel.setAmountRange(currentCell.getStringCellValue());
						} else if (currentCell.getCellTypeEnum() == CellType.NUMERIC) {
							amountModel.setAmount_id((new Double(currentCell.getNumericCellValue())).intValue());
						}
					}
					if (clazz.isAssignableFrom(DocumentModel.class)) {
						if (currentCell.getCellTypeEnum() == CellType.STRING) {
							documentModel.setDocument(currentCell.getStringCellValue());
						} else if (currentCell.getCellTypeEnum() == CellType.NUMERIC) {
							documentModel.setId((new Double(currentCell.getNumericCellValue())).intValue());
						}
					}
					if (clazz.isAssignableFrom(MainDataModel.class)) {
						String cellValue = currentCell.getStringCellValue();
						if (cellValue.startsWith("age")) {
							mainDataModel.setAge(Integer.parseInt(cellValue.split("=")[1]));
						}
						if (cellValue.startsWith("amount")) {
							mainDataModel.setAmount(Integer.parseInt(cellValue.split("=")[1]));
						}
						if (cellValue.startsWith("doc")) {
							mainDataModel.setDocument(Integer.parseInt(cellValue.split("=")[1]));
						}
					}
				}
				if (mainDataModel != null) {
					list.add(mainDataModel);
					map.put(MainDataModel.class.getSimpleName(), list);
				}
				if (ageModel != null) {
					list.add(ageModel);
					map.put(AgeModel.class.getSimpleName(), list);
				}
				if (amountModel != null) {
					list.add(amountModel);
					map.put(AmountModel.class.getSimpleName(), list);
				}
				if (documentModel != null) {
					list.add(documentModel);
					map.put(DocumentModel.class.getSimpleName(), list);
				}

			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return map;
	}
	public static void main(String[] args) {
		ExcelDataExtractor extractor = new ExcelDataExtractor();
		System.out.println("Document is :: "+extractor.extractData(41, 22000));
		
	}
}
