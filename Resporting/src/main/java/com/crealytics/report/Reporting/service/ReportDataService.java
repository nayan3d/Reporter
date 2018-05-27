package com.crealytics.report.Reporting.service;

import java.util.List;

import javax.persistence.EntityManager;

import com.crealytics.report.Reporting.entities.ExcelData;

public interface ReportDataService {

	EntityManager getEntityManager();

	int insert(ExcelData data);

	void setEntityManager(EntityManager entityManager);

	List<ExcelData> getAllReports();

	List<ExcelData> findByMonthAndSite(String month, String site);

	List<ExcelData> getAllReportForMonth(String pathMonth);

	List<ExcelData> getAllReportForSite(String site);

}