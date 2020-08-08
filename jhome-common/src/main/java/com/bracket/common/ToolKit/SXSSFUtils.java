package com.bracket.common.ToolKit;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFCell;

import java.util.Iterator;

public class SXSSFUtils {
	/**
	 * @param fromSheet
	 * @param toSheet
	 */
	public static void mergeSheetAllRegion(Sheet fromSheet, Sheet toSheet) {
		int num = fromSheet.getNumMergedRegions();
		CellRangeAddress cellR = null;
		for (int i = 0; i < num; i++) {
			cellR = fromSheet.getMergedRegion(i);
			toSheet.addMergedRegion(cellR);
		}
	}
 
	/**
	 * @param wb
	 * @param fromCell
	 * @param toCell
	 */
	public static void copyCell(SXSSFWorkbook wb, Cell fromCell, Cell toCell) {
		toCell.setCellStyle(fromCell.getCellStyle());
		if (fromCell.getCellComment() != null) {
			toCell.setCellComment(fromCell.getCellComment());
		}

		int fromCellType = fromCell.getCellType();
		toCell.setCellType(fromCellType);
		if (fromCellType == XSSFCell.CELL_TYPE_NUMERIC) {
			if (XSSFDateUtil.isCellDateFormatted(fromCell)) {
				toCell.setCellValue(fromCell.getDateCellValue());
			} else {
				toCell.setCellValue(fromCell.getNumericCellValue());
			}
		} else if (fromCellType == XSSFCell.CELL_TYPE_STRING) {
			toCell.setCellValue(fromCell.getRichStringCellValue());
		} else if (fromCellType == XSSFCell.CELL_TYPE_BLANK) {
			// nothing21
		} else if (fromCellType == XSSFCell.CELL_TYPE_BOOLEAN) {
			toCell.setCellValue(fromCell.getBooleanCellValue());
		} else if (fromCellType == XSSFCell.CELL_TYPE_ERROR) {
			toCell.setCellErrorValue(fromCell.getErrorCellValue());
		} else if (fromCellType == XSSFCell.CELL_TYPE_FORMULA) {
			toCell.setCellFormula(fromCell.getCellFormula());
		} else { // nothing29
		}
 
	}
 
	/**
	 * @param wb
	 * @param oldRow
	 * @param toRow
	 */
	public static void copyRow(SXSSFWorkbook wb, Row oldRow, Row toRow) {
		toRow.setHeight(oldRow.getHeight());
		for (Iterator cellIt = oldRow.cellIterator(); cellIt.hasNext();) {
			Cell tmpCell = (Cell) cellIt.next();
			Cell newCell = toRow.createCell(tmpCell.getColumnIndex());
			copyCell(wb, tmpCell, newCell);
		}
	}
 
	/**
	 * @param wb
	 * @param fromSheet
	 * @param toSheet
	 */
	public static void copySheet(SXSSFWorkbook wb, Sheet fromSheet, Sheet toSheet) {
		mergeSheetAllRegion(fromSheet, toSheet);

		for (Iterator rowIt = fromSheet.rowIterator(); rowIt.hasNext();) {
			Row oldRow = (Row) rowIt.next();
			Row newRow = toSheet.createRow(oldRow.getRowNum());
			copyRow(wb, oldRow, newRow);
		}
	}
 
	public class XSSFDateUtil extends DateUtil {
 
	}
} 