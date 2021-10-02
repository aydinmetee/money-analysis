package com.metea.moneyanalysis.endpoint;

import com.metea.moneyanalysis.dto.*;
import com.metea.moneyanalysis.serviceview.OperationDetailServiceView;
import com.metea.moneyanalysis.serviceview.OperationMasterServiceView;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/operations")
@RequiredArgsConstructor
@Api(value = "/operations")
public class OperationController {
    private final OperationMasterServiceView operationMasterService;
    private final OperationDetailServiceView operationDetailService;

    @PostMapping
    @ApiOperation(value = "Create Operation Master", response = OperationMasterReadDTO.class)
    public ResponseEntity<OperationMasterReadDTO> createUser(
            @Valid @RequestBody OperationMasterWriteDTO operationMasterWriteDTO) {
        return ResponseEntity.ok(operationMasterService.save(operationMasterWriteDTO));
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Get Operation Master By Id", response = OperationMasterReadDTO.class)
    public ResponseEntity<OperationMasterReadDTO> getById(@PathVariable(value = "id") Long id) {
        return ResponseEntity.ok(operationMasterService.getById(id));
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "Delete Operation Master", response = Boolean.class)
    public ResponseEntity<Boolean> deleteUser(@PathVariable(value = "id") Long id) {
        return ResponseEntity.ok(operationMasterService.delete(id));
    }

    @GetMapping("/master-for-user")
    @ApiOperation(value = "Get Operation Master By User ", response = OperationMasterReadDTO.class)
    public ResponseEntity<OperationMasterReadDTO> getMasterByUser() {
        return ResponseEntity.ok(operationMasterService.getMasterByUser());
    }

    @PostMapping("/search")
    @ApiOperation(value = "Search Operation Master", response = Page.class)
    public ResponseEntity<Page<OperationMasterReadDTO>> search(@RequestBody() OperationMasterSearchCriteriaDTO filter,
                                                               @RequestParam(defaultValue = "0") int page,
                                                               @RequestParam(defaultValue = "3") int size) {
        return ResponseEntity.ok(operationMasterService.search(filter, PageRequest.of(page, size)));
    }

    @GetMapping("/find-all-details-by-user")
    @ApiOperation(value = "Find All Details For User", response = Page.class)
    public ResponseEntity<Page<OperationDetailReadDTO>> searchDetailForUser() {
        return ResponseEntity.ok(operationDetailService.getAllRecordByUser());
    }

    @GetMapping("/{masterId}/detail/{id}")
    @ApiOperation(value = "Get By Id Operation Detail", response = OperationDetailReadDTO.class)
    public ResponseEntity<OperationDetailReadDTO> getById(@PathVariable(value = "masterId") Long masterId,
                                                          @PathVariable(value = "id") Long id) {
        return ResponseEntity.ok(operationDetailService.getById(id));
    }

    @PostMapping("/{masterId}/detail")
    @ApiOperation(value = "Create Operation Detail", response = OperationDetailReadDTO.class)
    public ResponseEntity<OperationDetailReadDTO> createDetail(@PathVariable(value = "masterId") Long masterId,
                                                               @Valid @RequestBody OperationDetailWriteDTO operationDetailWriteDTO) {
        operationDetailWriteDTO.setMasterId(masterId);
        return ResponseEntity.ok(operationDetailService.save(operationDetailWriteDTO));
    }

    @DeleteMapping("/{masterId}/detail/{id}")
    @ApiOperation(value = "Delete Operation Detail", response = Boolean.class)
    public ResponseEntity<Boolean> deleteDetail(@PathVariable(value = "masterId") Long masterId,
                                                @PathVariable(value = "id") Long id) {
        return ResponseEntity.ok(operationDetailService.delete(id));
    }

    @GetMapping("/{masterId}/detail/search-all")
    @ApiOperation(value = "Search By Master Id", response = List.class)
    public ResponseEntity<List<OperationDetailReadDTO>> getAllByMasterId(@PathVariable(value = "masterId") Long masterId) {
        return ResponseEntity.ok(operationDetailService.getAllByMasterId(masterId));
    }

    @GetMapping("/{masterId}/get-by-weekly")
    @ApiOperation(value = "Get By Weekly", response = List.class)
    public ResponseEntity<List<OperationDetailReadDTO>> getByWeekly(@PathVariable(value = "masterId") Long masterId) {
        return ResponseEntity.ok(operationDetailService.getAllWeekly(masterId));
    }

    @GetMapping("/{masterId}/get-by-monthly")
    @ApiOperation(value = "Get By Monthly", response = List.class)
    public ResponseEntity<List<OperationDetailReadDTO>> getByMonthly(@PathVariable(value = "masterId") Long masterId) {
        return ResponseEntity.ok(operationDetailService.getAllMonthly(masterId));
    }

    @PostMapping("/{masterId}/search")
    @ApiOperation(value = "Search Operation Details", response = Page.class)
    public ResponseEntity<Page<OperationDetailReadDTO>> searchDetails(@RequestBody() OperationDetailSearchCriteriaDTO filter,
                                                                      @RequestParam() Long masterId,
                                                                      @RequestParam(defaultValue = "0") int page,
                                                                      @RequestParam(defaultValue = "3") int size) {
        filter.setMasterId(masterId);
        return ResponseEntity.ok(operationDetailService.search(filter, PageRequest.of(page, size)));
    }
}
