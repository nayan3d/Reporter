package com.crealytics.report.Reporting.controller;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.List;

import org.aspectj.lang.annotation.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.crealytics.report.Reporting.entities.ExcelData;
import com.crealytics.report.Reporting.service.ReportDataService;

@RunWith(SpringRunner.class)
@WebMvcTest(value = ReportController.class, secure = false)
public class ReportControllerTest {

	

    @InjectMocks
    ReportController controller;

    @Autowired
    WebApplicationContext context;

    private MockMvc mvc;

    @Before(value = "")
    public void initTests() {
        MockitoAnnotations.initMocks(this);
        mvc = MockMvcBuilders.webAppContextSetup(context).build();
    }

    //@Test
    public void shouldHaveEmptyDB() throws Exception {
        mvc.perform(get("/reports/2/android")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
               // .andExpect(jsonPath("$", hasSize(0)));
    }

}
