package com.bracket.common.BatchExcel;

import com.bracket.common.BatchExcel.Notify.demoNotify;
import com.bracket.common.Vo.professionEntity;
import com.bracket.common.BatchExcel.Notify.demoNotify;
import com.bracket.common.Vo.professionEntity;
import junit.framework.TestCase;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


public class Program extends TestCase {

	public void testExl() {
		demoNotify dNotify = new demoNotify();
		StringBuilder sBuilder = new StringBuilder();
		List<ExcelteColumnMapping> excelteColumnMappings = new ArrayList<ExcelteColumnMapping>();

		// WorkbookUtils workbookUtils = new WorkbookUtils("E:\\校方内部专业.xlsx");
		// SXSSFWorkbook sworkbook = workbookUtils.getWorkbook();
		// SXSSFSheet sheet=(SXSSFSheet) sworkbook.getSheetAt(0);
		// XSSFWorkbook workbook = sworkbook.getXSSFWorkbook();

		// 内存溢出
		XSSFWorkbook xWorkbook = null;
		// WorkbookUtils workbookUtils = new WorkbookUtils();
		// xWorkbook = workbookUtils.getXSSFWorkbook("E:\\校方内部专业.xlsx");
		SXSSFWorkbook sworkbook = null;
		try {
			WorkbookUtils workbookUtils = new WorkbookUtils("E:\\校方内部专业.xlsx");
			sworkbook = workbookUtils.getWorkbook();

			// xWorkbook=sworkbook.getXSSFWorkbook();

			Sheet sheet = sworkbook.getSheetAt(0);
			XSSFSheet sheet1 = xWorkbook.getSheetAt(0);
			sworkbook.dispose();
			System.out.println(String.format("++++++++++++++++%s+++++++++++",
					sheet.getSheetName()));

			System.out.println(String.format("++++++++++++++++%d+++++++++++",
					sheet.getLastRowNum()));

			try {
				for (int i = 0; i < sheet.getLastRowNum() + 1; i++) {
					Row row = sheet.getRow(i);
					if (row == null) {
						System.out.println(i + ":null");
					} else {
						for (int j = 0; j < row.getPhysicalNumberOfCells(); j++) {
							Cell cell = row.getCell(j);
							if (row.getCell(j) != null) {
								row.getCell(j).setCellType(
										Cell.CELL_TYPE_STRING);
							}
							// System.out.print(String.format("%s",cell.getStringCellValue()));
							System.out.println("=====" + i);
						}
						System.out.println();

					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
 

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			sworkbook.dispose();
		}

	}

	public void testExl2() throws InstantiationException, IllegalAccessException {
		demoNotify dNotify = new demoNotify();
		StringBuilder sBuilder = new StringBuilder();
		List<ExcelteColumnMapping> excelteColumnMappings = new ArrayList<ExcelteColumnMapping>();
		ExcelteColumnMapping excelteColumnMapping = new ExcelteColumnMapping();
		excelteColumnMapping.setEntityColumnName("majorName");
		excelteColumnMapping.setExcelColumnName("B");
		excelteColumnMappings.add(excelteColumnMapping);
		
		ExcelteColumnMapping excelteColumnMapping1 = new ExcelteColumnMapping();
		excelteColumnMapping1.setEntityColumnName("majorCode");
		excelteColumnMapping1.setExcelColumnName("A");		
		excelteColumnMappings.add(excelteColumnMapping1);
		
		WorkbookUtils workbookUtils = new WorkbookUtils("E:\\校方内部专业1.xlsx");
		SXSSFWorkbook sworkbook = workbookUtils.getWorkbook();
		XSSFWorkbook workbook= sworkbook.getXSSFWorkbook();
		ExcelConfig excelConfig = new ExcelConfig();
		excelConfig.setSheetRowStartIndex(1);
		excelConfig.setWorkSheet(workbook.getSheetAt(0));

		ExcelteEngine<professionEntity> engine = new ExcelteEngine<professionEntity>(dNotify);

		try {
			List<professionEntity> professionEntities = engine.MappingToEntity(excelConfig, excelteColumnMappings, sBuilder,professionEntity.class);
			Iterator<professionEntity> iterator = professionEntities.iterator();
			while (iterator.hasNext()) {
				professionEntity pEntity = iterator.next();
				System.out.println(String.format("%s,%s",pEntity.getMajorCode(), pEntity.getMajorName()));
			}

			System.out.println(sBuilder.toString());
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
}
