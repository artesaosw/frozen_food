package com.capgemini.engineering.ddd.frozen_food.stock.infra.controller;

import com.capgemini.engineering.ddd.frozen_food._shared.SupplierID;
import com.capgemini.engineering.ddd.frozen_food.stock.domain.entity.Supplier;
import com.capgemini.engineering.ddd.frozen_food.stock.domain.exception.DuplicatedKeyException;
import com.capgemini.engineering.ddd.frozen_food.stock.domain.exception.NonExistentIngredientException;
import com.capgemini.engineering.ddd.frozen_food.stock.domain.service.SupplierService;
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
import javax.validation.constraints.NotNull;
import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/supplier")
@Validated
public class SupplierController {

    static final String GENERAL_ERROR_MSG = "INTERNAL_ERROR";
    static final String NO_DATA_ERROR_MSG = "NO_DATA_RECEIVED_ERROR";
    static final String DUPLICATED_SUPPLIER_ERROR_MSG = "DUPLICATED_SUPPLIER_ERROR";
    static final String ADD_SUCCESS_MSG = "ADD_SUCCESS";
    static final String UPDATE_SUCCESS_MSG = "UPDATE_SUCCESS";
    static final String DELETE_SUCCESS_MSG = "DELETE_SUCCESS";
    static final String SUPPLIER_NOT_FOUND_ERROR_MSG = "SUPPLIER_NOT_FOUND";
    static final String MISSING_PARAMETER_ERROR_MSG = "MISSING_PARAMETER_ERROR";
    static final String MISSING_PARAMETER_VALUE_ERROR_MSG = "MISSING_PARAMETER_VALUE_ERROR";

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
        return ResponseEntity.badRequest().contentType(MediaType.TEXT_PLAIN).body(NO_DATA_ERROR_MSG);
    }

    @ExceptionHandler(MissingServletRequestParameterException.class)
    private ResponseEntity<?> handleNoData(MissingServletRequestParameterException ex) {
        return ResponseEntity.badRequest().contentType(MediaType.TEXT_PLAIN).body(MISSING_PARAMETER_ERROR_MSG);
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    private ResponseEntity<?> handleNoData(MethodArgumentTypeMismatchException ex) {
        return ResponseEntity.badRequest().contentType(MediaType.TEXT_PLAIN).body(MISSING_PARAMETER_VALUE_ERROR_MSG);
    }

    @GetMapping(path = "/{id}", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.TEXT_PLAIN_VALUE})
    public ResponseEntity<?> getSupplierById(@PathVariable @Valid SupplierID id) {
        try {
            var supplier = supplierService.getSupplierById(id);
            return ResponseEntity.ok(supplier);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).contentType(MediaType.TEXT_PLAIN).body(GENERAL_ERROR_MSG);
        }
    }

    @GetMapping(path = "/{name}", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.TEXT_PLAIN_VALUE})
    public ResponseEntity<?> getSupplierByName(@PathVariable @Valid String name) {
        try {
            var supplier = supplierService.getSupplierByName(name);
            return ResponseEntity.ok(supplier);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).contentType(MediaType.TEXT_PLAIN).body(GENERAL_ERROR_MSG);
        }
    }

    @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.TEXT_PLAIN_VALUE})
    public ResponseEntity<?> getAllSuppliers() {
        try {
            return ResponseEntity.ok(supplierService.getAllSuppliers());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).contentType(MediaType.TEXT_PLAIN).body(GENERAL_ERROR_MSG);
        }
    }

    @PostMapping(produces = {MediaType.TEXT_PLAIN_VALUE})
    public ResponseEntity<?> addSupplier(@RequestBody @Valid @NotNull Supplier supplier) {
        try {
            supplierService.registerNewSupplier(supplier);
            return ResponseEntity.created(URI.create("/supplier/" + supplier.getId())).contentType(MediaType.TEXT_PLAIN).body(ADD_SUCCESS_MSG);
        } catch (DuplicatedKeyException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).contentType(MediaType.TEXT_PLAIN).body(DUPLICATED_SUPPLIER_ERROR_MSG);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).contentType(MediaType.TEXT_PLAIN).body(GENERAL_ERROR_MSG);
        }
    }

    @PutMapping(consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.TEXT_PLAIN_VALUE})
    public ResponseEntity<?> updateSupplier(@RequestBody @Valid @NotNull Supplier supplier) {
        try {
            supplierService.updateSupplier(supplier);
            return ResponseEntity.ok(UPDATE_SUCCESS_MSG);
        } catch (NonExistentIngredientException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).contentType(MediaType.TEXT_PLAIN).body(SUPPLIER_NOT_FOUND_ERROR_MSG);
        }catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).contentType(MediaType.TEXT_PLAIN).body(GENERAL_ERROR_MSG);
        }
    }

    @DeleteMapping(path = "/{id}", produces = {MediaType.TEXT_PLAIN_VALUE})
    public ResponseEntity<?> deleteSupplier(@PathVariable @Valid SupplierID id) {
        try {
            supplierService.deleteSupplier(id);
            return ResponseEntity.ok(DELETE_SUCCESS_MSG);
        } catch (NonExistentIngredientException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).contentType(MediaType.TEXT_PLAIN).body(SUPPLIER_NOT_FOUND_ERROR_MSG);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).contentType(MediaType.TEXT_PLAIN).body(GENERAL_ERROR_MSG);
        }
    }
}
