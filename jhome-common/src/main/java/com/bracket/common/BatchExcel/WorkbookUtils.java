package com.bracket.common.BatchExcel;

import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;

public class WorkbookUtils {
	public   SXSSFWorkbook workbook;
	 
	public WorkbookUtils() {
		super();
		// TODO Auto-generated constructor stub
	}

	public WorkbookUtils(String filePath) {
		super();
		// TODO Auto-generated constructor stub
		workbook=new SXSSFWorkbook(getXSSFWorkbook(filePath),100);
	}
	 
    /**
     * 创建 XSSFWorkbook
     * @param filePath
     * @return
     */
	public   XSSFWorkbook getXSSFWorkbook(String filePath) {
		XSSFWorkbook workbook =  null;
		
		BufferedInputStream inputStream = null;
		try {
			File fileXlsxPath = new File(filePath);
			inputStream = new BufferedInputStream(new FileInputStream(fileXlsxPath));
			workbook = new XSSFWorkbook(inputStream); 
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			if(inputStream!=null) {
				try {
					inputStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return workbook;
	}
	 
	/**
	 * XSSFWorkbook对象
	 * @param filePath
	 * @return
	 */
	public static XSSFWorkbook getXSSFWorkbookByOut(String filePath) {
		XSSFWorkbook workbook =  null;
		BufferedOutputStream outputStream = null;
		try {
			File fileXlsxPath = new File(filePath);
			outputStream = new BufferedOutputStream(new FileOutputStream(fileXlsxPath));
			workbook = new XSSFWorkbook();
			workbook.createSheet("测试Sheet");
			workbook.write(outputStream);
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			if(outputStream!=null) {
				try {
					outputStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return workbook;
	}
  

	public SXSSFWorkbook getWorkbook() {
		return workbook;
	}

	public void setWorkbook(SXSSFWorkbook workbook) {
		this.workbook = workbook;
	}

}
