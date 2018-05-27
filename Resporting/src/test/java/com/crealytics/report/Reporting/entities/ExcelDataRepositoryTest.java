package com.crealytics.report.Reporting.entities;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.*;

import java.util.List;

import org.aspectj.lang.annotation.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.crealytics.report.Reporting.controller.ReportController;
import com.crealytics.report.Reporting.service.ReportDataService;

@RunWith(SpringRunner.class)
@DataJpaTest
public class ExcelDataRepositoryTest {
	
	@Autowired
	    private TestEntityManager entityManager;
		 
	 @Autowired
	    private ExcelDataRepository dataRepository;

	@Test
	public void whenFindByMonth_returnMonth() {
		
		ExcelData data  = new ExcelData("iOS", 2550165L, 2419733L, 6331L, 1564L, 4692.28, "January");
		entityManager.persist(data);
	    entityManager.flush();
	    // when
	    List<ExcelData> listdata = dataRepository.findByMonthAndSite(data.getMonth(), data.getSite());	    
	    String found = null;	 
	    for(int i=0;i<listdata.size();i++)
	    {
	    	found=listdata.get(i).getMonth();	    	
	    }
	    // then
	    assertThat(found)
	      .isEqualTo(data.getMonth());
	}
	 
}
