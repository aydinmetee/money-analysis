package com.metea.moneyanalysis.endpoint;

import com.metea.moneyanalysis.domain.OperationMaster;
import com.metea.moneyanalysis.dto.OperationMasterReadDTO;
import com.metea.moneyanalysis.dto.OperationMasterWriteDTO;
import com.metea.moneyanalysis.service.OperationMasterService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/operations")
@RequiredArgsConstructor
@Api(value = "/operations")
public class OperationMasterController {

    private final OperationMasterService operationMasterService;

    @GetMapping("/{id}")
    @ApiOperation(value = "Get By Id Operation", response = OperationMasterReadDTO.class)
    public ResponseEntity<OperationMasterReadDTO> getById(@PathVariable(value = "id") Long id) {
        return ResponseEntity.ok(operationMasterService.getById(id));
    }

    @PostMapping
    @ApiOperation(value = "Create Operation", response = OperationMasterReadDTO.class)
    public ResponseEntity<OperationMasterReadDTO> createUser(
            @Valid @RequestBody OperationMasterWriteDTO operationMasterWriteDTO) {
        return ResponseEntity.ok(operationMasterService.save(operationMasterWriteDTO));
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "Delete Operation", response = Boolean.class)
    public ResponseEntity<Boolean> deleteUser(@PathVariable(value = "id") Long id) {
        return ResponseEntity.ok(operationMasterService.delete(id));
    }

    @GetMapping("/for-user/{id}")
    @ApiOperation(value = "Get By Id Operation", response = List.class)
    public ResponseEntity<List<OperationMasterReadDTO>> getAllByUserId(@PathVariable(value = "id") Long id) {
        return ResponseEntity.ok(operationMasterService.getAllByUserId(id));
    }

}
