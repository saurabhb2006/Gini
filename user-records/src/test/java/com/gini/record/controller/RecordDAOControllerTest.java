package com.gini.record.controller;

import com.gini.record.domain.RecordDAO;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Test Class to test controller methods GET,POST,DELETE,PUT
 */
public class RecordDAOControllerTest extends AbstractTest {  //This is integration test. The method name is wrong.
    @Override
    @Before
    public void setUp() {
        super.setUp();
    }
    @Test
    public void getRecordsById() throws Exception {
        String uri = "http://localhost:8080/api/v1/records/?id=31abe8fa-3de4-4884-b0f3-21c0fc006084";
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
                .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
        String content = mvcResult.getResponse().getContentAsString();
        RecordDAO record = super.mapFromJson(content, RecordDAO.class);
        assertTrue(record.getUserId().equals("victor"));
    }

    @Test
    public void getRecordsByUserId() throws Exception {
        String uri = "http://localhost:8080/api/v1/records/?userId=victor";
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
                .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
        String content = mvcResult.getResponse().getContentAsString();
        RecordDAO record = super.mapFromJson(content, RecordDAO.class);
        assertTrue(record.getUserId().equals("victor"));
    }

    @Test
    public void insertRecord() throws Exception {
        String uri = "http://localhost:8080/api/v1/records";
        RecordDAO recordDAO = new RecordDAO();
        recordDAO.setUserId("saurabhbhasin");
        recordDAO.setType("pdf");
        recordDAO.setDescription("File to view");
        String inputJson = super.mapToJson(recordDAO);
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(inputJson)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(202, status);
        String content = mvcResult.getResponse().getContentAsString(); //You don't check the content saved.
        assertNotNull(content);
    }

    @Test
    public void updateDescription() throws Exception {
        String uri = "http://localhost:8080/api/v1/items/14a26b3d-b746-4d98-be4b-b0f53a8d0f0b?description=test to see";

         MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.patch(uri)
                 .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();
        int status = mvcResult.getResponse().getStatus();
        assertEquals(202, status);
        String content = mvcResult.getResponse().getContentAsString();
        assertEquals(content, "Record successfully updated");
    }

}
