package com.gini.record.service;

import com.gini.record.domain.RecordDAO;
import com.gini.record.itemrepository.RecordsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

/**
 * Service class to handle request from Controller to the repository (Service Layer).
 */
@Service
public class RecordsDaoService {

    @Autowired
    private RecordsRepository recordsRepository;

    public UUID save(RecordDAO recordDao){ return recordsRepository.save(recordDao).getId(); }

    public Optional<RecordDAO> findById(UUID id){ return recordsRepository.findById(id);} //You could throw a NotFoundException instead of returning Optional

    public RecordDAO findByUserId(String userId){ return recordsRepository.findByUserId(userId);}

    public void updateRecord(RecordDAO recordDAO) { recordsRepository.save(recordDAO);} //You could check if the record exists here instead of in the controller.

}
