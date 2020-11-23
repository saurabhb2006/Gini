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
@RequestMapping("/api/v1") //Why not having "records" in the general path? Also, you are mixing Records with Items in your controller.
public class RecordController {

    final Set<String> setOfTypes = new HashSet<>(Arrays.asList("pdf", "jpg", "png")); //You could use an Enum instead of a list. It allows to have a strong check in the RecordDAO

    @Autowired(required = true)
    private RecordsDaoService recordsDaoService;

    @GetMapping(value = "/records", params="id") //Path param is wrong. It should be /records/{id}
    public Optional<RecordDAO> findById(@RequestParam UUID id) { //Method name is not great. Confusing with Repository
        return recordsDaoService.findById(id);
    }
    //You are using findById in the Controller, the service and the Repository. This is very confusing. FindById, save, insert, etc... Are better suited for the repository. You can user "CreateRecord" for instance for the controller.

    @GetMapping(value = "/records" , params="userId")
    public RecordDAO findByUserId(@RequestParam String userId) {
        return recordsDaoService.findByUserId(userId);
    }

    /**
     * partial documentation
     * @param recordDto
     * @return
     */
    @PostMapping(value = "/records", produces = "application/json", consumes = "application/json")
    @ResponseStatus(HttpStatus.ACCEPTED) //ResponseStatus should be 201. CREATED
    public ResponseEntity<UUID> insertRecord(@RequestBody RecordDTO recordDto) {
        RecordDAO checkUserId = recordsDaoService.findByUserId(recordDto.getUserId());
        if(checkUserId != null){ //A user can only have one record
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

    @PatchMapping("/items/{id}") //Very good that you are using Patch here.
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
