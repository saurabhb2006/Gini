
package com.gini.record.service;

import com.gini.record.domain.RecordDAO;
import com.gini.record.itemrepository.RecordsRepository;
import com.gini.record.service.RecordsDaoService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.UUID;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class ServiceMockitoTest {

    @Autowired
    private RecordsDaoService itemService;

    @MockBean
    private RecordsRepository recordsRepository;

    @Test
    public void getRecordsByUserIdTest(){
        Mockito.when(recordsRepository.findByUserId("saurabh")).thenReturn(
                 new RecordDAO("saurabh","pdf","Biology report"));
        String type = itemService.findByUserId("saurabh").getType();
        assertEquals(type.equals("pdf"), true);
    }

    @Test
    public void getRecordsByIdTest(){
        Mockito.when(recordsRepository.findById(UUID.fromString("31abe8fa-3de4-4884-b0f3-21c0fc006084"))).thenReturn(
                java.util.Optional.of(new RecordDAO("saurabh", "jpg", "Biology report")));
        String type = itemService.findById(UUID.fromString("31abe8fa-3de4-4884-b0f3-21c0fc006084")).get().getType();
        assertEquals(type.equals("jpg"), true);
    }

    @Test
    public void saveRecordTest(){
        RecordDAO record = new RecordDAO("saurabh", "jpg", "Biology report");
        String uuid = new String("31abe8fa-3de4-4884-b0f3-21c0fc006084");
        record.setId(UUID.fromString(uuid));
        Mockito.when(recordsRepository.save(record)).thenReturn(record);
        assertNotNull(record.getId());
    }

}

