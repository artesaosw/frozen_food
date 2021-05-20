package com.capgemini.engineering.ddd.frozen_food.stock.infra.controller;

import com.capgemini.engineering.ddd.frozen_food._shared.stock.dto.ErrorDTO;
import com.capgemini.engineering.ddd.frozen_food._shared.stock.dto.MessageDTO;
import com.capgemini.engineering.ddd.frozen_food.stock.domain.exception.InvalidElementException;
import com.capgemini.engineering.ddd.frozen_food.stock.domain.exception.NonExistentEntityException;
import com.capgemini.engineering.ddd.frozen_food.stock.domain.valueObject.NIF;
import com.capgemini.engineering.ddd.frozen_food.stock.domain.valueObject.SupplierID;
import com.capgemini.engineering.ddd.frozen_food.stock.domain.entity.Supplier;
import com.capgemini.engineering.ddd.frozen_food.stock.domain.exception.DuplicatedEntityException;
import com.capgemini.engineering.ddd.frozen_food.stock.domain.service.SupplierService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/supplier")
@Validated
public class SupplierController {

    static final String NO_DATA_ERROR_MSG = "NO DATA RECEIVED ERROR";
    static final String ADD_SUCCESS_MSG = "Supplier added successfully!";
    static final String UPDATE_SUCCESS_MSG = "Supplier updated successfully!";
    static final String DELETE_SUCCESS_MSG = "Supplier deleted successfully!";
    static final String MISSING_PARAMETER_ERROR_MSG = "MISSING PARAMETER ERROR";
    static final String MISSING_PARAMETER_VALUE_ERROR_MSG = "MISSING PARAMETER VALUE ERROR";
    static final String INVALID_NIF_MSG = "NIF not valid.";

    @Autowired
    private SupplierService supplierService;

    public SupplierController() {
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    private ResponseEntity<?> handleInvalidData(MethodArgumentNotValidException ex) {
        List<String> messages = ex.getAllErrors().stream().map(DefaultMessageSourceResolvable::getDefaultMessage).collect(Collectors.toList());
        return ResponseEntity.badRequest().contentType(MediaType.APPLICATION_JSON).body(messages);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    private ResponseEntity<?> handleNoData(HttpMessageNotReadableException ex) {
        if (ex.getMessage().contains(INVALID_NIF_MSG)) {
            return ResponseEntity.badRequest().body(new ErrorDTO(new Exception(INVALID_NIF_MSG)));
        } else {
            return ResponseEntity.badRequest().body(new ErrorDTO(new Exception(NO_DATA_ERROR_MSG)));
        }
    }

    @ExceptionHandler(MissingServletRequestParameterException.class)
    private ResponseEntity<?> handleNoData(MissingServletRequestParameterException ex) {
        return ResponseEntity.badRequest().body(new ErrorDTO(new Exception(MISSING_PARAMETER_ERROR_MSG)));
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    private ResponseEntity<?> handleNoData(MethodArgumentTypeMismatchException ex) {
        return ResponseEntity.badRequest().body(new ErrorDTO(new Exception(MISSING_PARAMETER_VALUE_ERROR_MSG)));
    }

    @GetMapping(path = "/{id}", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.TEXT_PLAIN_VALUE})
    public ResponseEntity<?> getSupplierById(@PathVariable @Valid @NotBlank String id) {
        try {
//            SupplierID supplierID = new SupplierID(id);
            String jsonString = String.format("{\"id\":\"%s\"}", id);
            ObjectMapper mapper = new ObjectMapper();
            var supplierID = mapper.readValue(jsonString, SupplierID.class);
            var supplier = supplierService.getSupplierBySupplierID(supplierID);
            return ResponseEntity.ok(supplier);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).contentType(MediaType.TEXT_PLAIN).body(e);
        }
    }

    @GetMapping(path = "/name/{name}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getSupplierByName(@PathVariable @Valid @NotBlank String name) {
        try {
            var supplier = supplierService.getSupplierByName(name);
            return ResponseEntity.ok(supplier);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ErrorDTO(e));
        }
    }

    @GetMapping(path = "/nif/{nif}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getSupplierByNif(@PathVariable @Valid @NotBlank String nif) {
        try {
            String jsonString = String.format("{\"nif\":\"%s\"}", nif);
            ObjectMapper mapper = new ObjectMapper();
            var nifObj = mapper.readValue(jsonString, NIF.class); // TODO não funciona
            var supplier = supplierService.getSupplierByNif(nifObj);
            return ResponseEntity.ok(supplier);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ErrorDTO(e));
        }
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getAllSuppliers() {
        try {
            return ResponseEntity.ok(supplierService.getAllSuppliers());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ErrorDTO(e));
        }
    }

    @PostMapping()
    public ResponseEntity<?> addSupplier(@RequestBody @Valid @NotNull Supplier supplier) {
        try {
            supplierService.registerNewSupplier(supplier);
            return ResponseEntity.created(URI.create("/supplier/" + supplier.getId())).body(new MessageDTO(ADD_SUCCESS_MSG));
        } catch (DuplicatedEntityException | InvalidElementException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorDTO(e));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ErrorDTO(e));
        }
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> updateSupplier(@RequestBody @Valid @NotNull Supplier supplier) {
        try {
            supplierService.updateSupplier(supplier);
            return ResponseEntity.ok(new MessageDTO(UPDATE_SUCCESS_MSG));
        } catch (NonExistentEntityException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorDTO(e));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ErrorDTO(e));
        }
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<?> deleteSupplier(@PathVariable @Valid @NotBlank String id) {
        try {
            String jsonString = String.format("{\"id\":\"%s\"}", id);
            ObjectMapper mapper = new ObjectMapper();
            var supplierID = mapper.readValue(jsonString, SupplierID.class);
            supplierService.deleteSupplier(supplierID);
            return ResponseEntity.ok(new MessageDTO(DELETE_SUCCESS_MSG));
        } catch (NonExistentEntityException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorDTO(e));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ErrorDTO(e));
        }
    }
}
