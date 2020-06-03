package com.daxu.common.BatchExcel;

import java.util.List;


/**
 * @author xu.da1
 * 映射列
 */
public class ExcelteColumnMapping {
    // excel对应列名
	private String ExcelColumnName;
	//实体列名
	private String EntityColumnName;
	//转换方法名,最大支持三个参数
	private String ConvertFunctionName; 
	// 验证方法名集合,根据List顺序过滤
	private List<String> VerifyFunctionName;
	public String getExcelColumnName() {
		return ExcelColumnName;
	}
	public void setExcelColumnName(String excelColumnName) {
		ExcelColumnName = excelColumnName;
	}
	public String getEntityColumnName() {
		return EntityColumnName;
	}
	public void setEntityColumnName(String entityColumnName) {
		EntityColumnName = entityColumnName;
	}
	public String getConvertFunctionName() {
		return ConvertFunctionName;
	}
	public void setConvertFunctionName(String convertFunctionName) {
		ConvertFunctionName = convertFunctionName;
	}
	public List<String> getVerifyFunctionName() {
		return VerifyFunctionName;
	}
	public void setVerifyFunctionName(List<String> verifyFunctionName) {
		VerifyFunctionName = verifyFunctionName;
	}
	
	
	
}
