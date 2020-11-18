package com.gini.record.data;

import static org.junit.Assert.assertEquals;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.gini.record.domain.RecordDAO;
import com.gini.record.itemrepository.RecordsRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@DataJpaTest
@RunWith(SpringJUnit4ClassRunner.class)
public class RecordDAORepositoryTest {

    @Autowired
    private RecordsRepository repository;

    @Test
    public void testFindAll() {
        List<RecordDAO> items = repository.findAll();
        assertEquals(2,items.size());
    }

    @Test
    public void testFindById() {
        Optional<RecordDAO> recordDAO =  repository.findById(UUID.fromString("14a26b3d-b746-4d98-be4b-b0f53a8d0f0b"));
        assertEquals("File to test",recordDAO.get().getDescription());
    }

    @Test
    public void testFindByUserId() {
        RecordDAO recordDAO =  repository.findByUserId("saurabh");
        assertEquals("File to test",recordDAO.getDescription());
    }

}
