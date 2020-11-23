package com.gini.record.itemrepository;

import com.gini.record.domain.RecordDAO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface RecordsRepository extends JpaRepository<RecordDAO, UUID> {

     public RecordDAO findByUserId(String userId); // This one should return a list

}
