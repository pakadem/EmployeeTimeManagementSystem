package com.adp3.controller.reports;

import com.adp3.entity.reports.LeaveReport;
import com.adp3.factory.reports.LeaveReportFactory;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

/**
 * Author: Megan Jacobs
 * Class: Part Time
 * Student number: 211137162
 * Controller: LeaveReportControllerTest - test all methods using PostMapping, GetMapping, PutMapping & DeleteMapping
 *
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@RunWith(SpringRunner.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class LeaveReportControllerTest {

    @Autowired
    private TestRestTemplate restTemplate;
    final String baseURL="http://localhost:8080/leaveReport/";
    private ResponseEntity leaveReportResponseEntity = null;
    private ObjectMapper mapper = new ObjectMapper();
    //private JsonNode root = mapper.readTree(leaveReportResponse.getBody());


    final LeaveReport leaveReport = LeaveReportFactory.buildLeaveReport("compassionate");

    @Test
    public void a_create(){ // Test PostMapping
         leaveReportResponseEntity =
                restTemplate.postForEntity(baseURL + "create", MediaType.APPLICATION_JSON,LeaveReport.class);
        assertNotNull(leaveReportResponseEntity.getBody());
        System.out.println("POST LeaveReport : " + leaveReportResponseEntity.getBody());
        assertEquals(HttpStatus.OK,leaveReportResponseEntity.getStatusCode());
    }

    @Test
    public void b_read() { // Test GetMapping
         leaveReportResponseEntity =
                restTemplate.getForEntity(baseURL + "read/"+ leaveReport.getLeaveReportID(), LeaveReport.class);
        System.out.println("GET LeaveReport : "+ leaveReport);
        assertEquals(HttpStatus.OK,leaveReportResponseEntity.getStatusCode());
    }

    @Test
    public void c_update() { // Test PutMapping
        LeaveReport original =
        restTemplate.getForObject(baseURL + "read/" + leaveReport.getLeaveReportID(), LeaveReport.class);
        restTemplate.put(baseURL + "update/" + "updated", original );
        LeaveReport updatedLeaveReport =
                restTemplate.getForObject(baseURL + "leaveReport/" + leaveReport.getLeaveReportID(), LeaveReport.class);
        assertNotNull(updatedLeaveReport);
        System.out.println("PUT LeaveReport : " + original);
    }

    @Test
    public void e_delete() { // Test DeleteMapping
        restTemplate.delete(baseURL + "delete/" + leaveReport.getLeaveReportID());
        System.out.println("DELETED LeaveReport :  " + baseURL + "/deleted:" + leaveReport.getLeaveReportID());
    }

    @Test
    public void d_getAll() {
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<String> entity = new HttpEntity<>(null, headers);
        ResponseEntity<String> result = restTemplate/*.withBasicAuth("Megan", "Password")*/
                .exchange(baseURL + "getAll/", HttpMethod.GET,entity, String.class);
            System.out.println("GETall LeaveReport: " + result.getBody());
    }
}