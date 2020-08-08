package com.bracket.common.BatchExcel;

import org.apache.poi.xssf.usermodel.XSSFSheet;

/**
 * @author xu.da1
 *  配置器
 */
public class ExcelConfig {
	private int SheetRowEndIndex;
	private int SheetRowStartIndex;
	 
    private XSSFSheet  workSheet;
	
	public ExcelConfig() {
		super();
		SheetRowEndIndex=-1;
		SheetRowStartIndex=1; 
	}
	 
    public XSSFSheet getWorkSheet() {
		return workSheet;
	}

	public void setWorkSheet(XSSFSheet workSheet) {
		this.workSheet = workSheet;
	}

	public boolean CheckRule()
    { 
        if (SheetRowEndIndex > 0)
        {
            if (SheetRowEndIndex < SheetRowStartIndex)
            {
              return false; 
            }
        }
		return true;
    }

	public int getSheetRowEndIndex() {
		return SheetRowEndIndex;
	}

	public void setSheetRowEndIndex(int sheetRowEndIndex) {
		SheetRowEndIndex = sheetRowEndIndex;
	}

	public int getSheetRowStartIndex() {
		return SheetRowStartIndex;
	}

	public void setSheetRowStartIndex(int sheetRowStartIndex) {
		SheetRowStartIndex = sheetRowStartIndex;
	}

}
