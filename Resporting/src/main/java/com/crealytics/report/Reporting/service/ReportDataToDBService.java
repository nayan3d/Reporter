package com.crealytics.report.Reporting.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.crealytics.report.Reporting.entities.ExcelData;
import com.crealytics.report.Reporting.entities.ExcelDataRepository;

/**
 * 
 * @author NMUKHER
 *
 */
@Service
@Transactional
public class ReportDataToDBService implements ReportDataService {
	
	@Autowired
	ExcelDataRepository repository;
	
	@PersistenceContext
	private EntityManager entityManager;

	/* (non-Javadoc)
	 * @see com.crealytics.report.Reporting.service.ReportDataService#getEntityManager()
	 */
	@Override
	public EntityManager getEntityManager() {
		return entityManager;
	}

	/* (non-Javadoc)
	 * @see com.crealytics.report.Reporting.service.ReportDataService#insert(com.crealytics.report.Reporting.entities.ExcelData)
	 */
	@Override
	public int insert(ExcelData data) {
		entityManager.persist(data);
		return data.getId();	
	}

	/* (non-Javadoc)
	 * @see com.crealytics.report.Reporting.service.ReportDataService#setEntityManager(javax.persistence.EntityManager)
	 */
	@Override
	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}
	
	
	/* (non-Javadoc)
	 * @see com.crealytics.report.Reporting.service.ReportDataService#getAllReports()
	 */
	@Override
	public List<ExcelData> getAllReports() {
		return (List<ExcelData>) repository.findAll();
	}

	
	/* (non-Javadoc)
	 * @see com.crealytics.report.Reporting.service.ReportDataService#findByMonthAndSite(java.lang.String, java.lang.String)
	 */
	@Override
	public List<ExcelData> findByMonthAndSite(String month, String site) {
		return repository.findByMonthAndSite(month, site);
	}

	/* (non-Javadoc)
	 * @see com.crealytics.report.Reporting.service.ReportDataService#getAllReportForMonth(java.lang.String)
	 */
	@Override
	public List<ExcelData> getAllReportForMonth(String pathMonth) {
		// TODO Auto-generated method stub
		return repository.getAllReportForMonth(pathMonth);
	}

	/* (non-Javadoc)
	 * @see com.crealytics.report.Reporting.service.ReportDataService#getAllReportForSite(java.lang.String)
	 */
	@Override
	public List<ExcelData> getAllReportForSite(String site) {
		// TODO Auto-generated method stub
		return repository.getAllReportForSite(site);
	}

	
}
