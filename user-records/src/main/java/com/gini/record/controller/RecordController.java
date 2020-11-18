package com.gini.record.controller;

import com.gini.record.domain.RecordDAO;
import com.gini.record.dto.RecordDTO;
import com.gini.record.exception.RecordException;
import com.gini.record.exception.RecordNotFoundException;
import com.gini.record.service.RecordsDaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

/**
 * Controller Class for REST Api
 */
@RestController
@RequestMapping("/api/v1")
public class RecordController {

    final Set<String> setOfTypes = new HashSet<>(Arrays.asList("pdf", "jpg", "png"));

    @Autowired(required = true)
    private RecordsDaoService recordsDaoService;

    @GetMapping(value = "/records", params="id")
    public Optional<RecordDAO> findById(@RequestParam UUID id) {
        return recordsDaoService.findById(id);
    }

    @GetMapping(value = "/records" , params="userId")
    public RecordDAO findByUserId(@RequestParam String userId) {
        return recordsDaoService.findByUserId(userId);
    }

    /**
     *
     * @param recordDto
     * @return
     */
    @PostMapping(value = "/records", produces = "application/json", consumes = "application/json")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ResponseEntity<UUID> insertRecord(@RequestBody RecordDTO recordDto) {
        RecordDAO checkUserId = recordsDaoService.findByUserId(recordDto.getUserId());
        if(checkUserId != null){
            throw new RecordException("Adding Record failed","The Record already exists in the list");
        } else if(!setOfTypes.contains(recordDto.getType())){
            throw new RecordException("Adding Record failed", "Please enter a valid type of file");
        }else{
            return new ResponseEntity<>(recordsDaoService.save(dtoTODaoObjectMapper(recordDto, new RecordDAO()) ), HttpStatus.ACCEPTED);
         }
    }

    /**
     * Method used to update an record description.
     *
     * @param id
     * @param description
     * @return Response status OK
     */

    @PatchMapping("/items/{id}")
    public ResponseEntity<String> updateDescription(@PathVariable UUID id, @RequestParam(required = true) String description){
    Optional<RecordDAO> recordDAO = recordsDaoService.findById(id);
    if(recordDAO.isPresent()){
        RecordDAO updateRecord = recordDAO.get();
        updateRecord.setDescription(description);
        recordsDaoService.updateRecord(updateRecord);
        return new ResponseEntity<>("Record successfully updated",HttpStatus.ACCEPTED);
    }else{
        throw new RecordNotFoundException("Record Not Found");
    }
}

    /**
     * Helper method to convert DTO to DAO object to be used to insert.
     *
     * @param recordDTO
     * @return item Object
     */
    public RecordDAO dtoTODaoObjectMapper(RecordDTO recordDTO, RecordDAO recordDAO){
         recordDAO.setUserId(recordDTO.getUserId());
         recordDAO.setType(recordDTO.getType());
         recordDAO.setDescription(recordDTO.getDescription());
         return recordDAO;

    }

}
