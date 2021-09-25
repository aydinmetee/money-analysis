package com.metea.moneyanalysis.endpoint;

import com.metea.moneyanalysis.dto.OperationDetailReadDTO;
import com.metea.moneyanalysis.dto.OperationDetailWriteDTO;
import com.metea.moneyanalysis.service.OperationDetailService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/operations/{masterId}")
@RequiredArgsConstructor
@Api(value = "/operations")
public class OperationDetailController {
    private final OperationDetailService operationDetailService;

    @GetMapping("/detail/{id}")
    @ApiOperation(value = "Get By Id Operation", response = OperationDetailReadDTO.class)
    public ResponseEntity<OperationDetailReadDTO> getById(@PathVariable(value = "masterId") Long masterId,
                                                          @PathVariable(value = "id") Long id) {
        return ResponseEntity.ok(operationDetailService.getById(id));
    }

    @PostMapping
    @ApiOperation(value = "Create Operation", response = OperationDetailReadDTO.class)
    public ResponseEntity<OperationDetailReadDTO> createUser(@PathVariable(value = "masterId") Long masterId,
                                                             @Valid @RequestBody OperationDetailWriteDTO operationDetailWriteDTO) {
        operationDetailWriteDTO.setMasterId(masterId);
        return ResponseEntity.ok(operationDetailService.save(operationDetailWriteDTO));
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "Delete Operation", response = Boolean.class)
    public ResponseEntity<Boolean> deleteUser(@PathVariable(value = "masterId") Long masterId,
                                              @PathVariable(value = "id") Long id) {
        return ResponseEntity.ok(operationDetailService.delete(id));
    }

    @GetMapping("/search")
    @ApiOperation(value = "Search", response = List.class)
    public ResponseEntity<List<OperationDetailReadDTO>> getAllByUserId(@PathVariable(value = "masterId") Long masterId) {
        return ResponseEntity.ok(operationDetailService.getAllByMasterId(masterId));
    }

    @GetMapping("/get-by-weekly")
    @ApiOperation(value = "Get By Weekly", response = List.class)
    public ResponseEntity<List<OperationDetailReadDTO>> getByWeekly(@PathVariable(value = "masterId") Long masterId) {
        return ResponseEntity.ok(operationDetailService.getAllWeekly(masterId));
    }

    @GetMapping("/get-by-monthly")
    @ApiOperation(value = "Get By Monthly", response = List.class)
    public ResponseEntity<List<OperationDetailReadDTO>> getByMonthly(@PathVariable(value = "masterId") Long masterId) {
        return ResponseEntity.ok(operationDetailService.getAllMonthly(masterId));
    }
}
