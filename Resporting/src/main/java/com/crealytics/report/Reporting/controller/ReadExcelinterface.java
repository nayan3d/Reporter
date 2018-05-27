package com.crealytics.report.Reporting.controller;

import java.util.List;

import com.crealytics.report.Reporting.entities.ExcelData;

public interface ReadExcelinterface {

	/**
	 * 
	 * @return List<ExcelData>
	 * 
	 */
	List<ExcelData> persistData();

}