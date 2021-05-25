package com.capgemini.engineering.ddd.frozen_food.stock.infra.controller;

import com.capgemini.engineering.ddd.frozen_food._shared.id.Identificator;
import com.capgemini.engineering.ddd.frozen_food._shared.utils.Error;
import com.capgemini.engineering.ddd.frozen_food._shared.utils.Message;
import com.capgemini.engineering.ddd.frozen_food.stock.domain.exception.InvalidElementException;
import com.capgemini.engineering.ddd.frozen_food.stock.domain.exception.NonExistentEntityException;
import com.capgemini.engineering.ddd.frozen_food.stock.domain.valueObject.NIF;
import com.capgemini.engineering.ddd.frozen_food.stock.domain.valueObject.SupplierID;
import com.capgemini.engineering.ddd.frozen_food.stock.domain.entity.Supplier;
import com.capgemini.engineering.ddd.frozen_food.stock.domain.exception.DuplicatedEntityException;
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
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/stock/supplier")
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
            return ResponseEntity.badRequest().body(new Error(new Exception(INVALID_NIF_MSG)));
        } else {
            return ResponseEntity.badRequest().body(new Error(new Exception(NO_DATA_ERROR_MSG)));
        }
    }

    @ExceptionHandler(MissingServletRequestParameterException.class)
    private ResponseEntity<?> handleNoData(MissingServletRequestParameterException ex) {
        return ResponseEntity.badRequest().body(new Error(new Exception(MISSING_PARAMETER_ERROR_MSG)));
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    private ResponseEntity<?> handleNoData(MethodArgumentTypeMismatchException ex) {
        return ResponseEntity.badRequest().body(new Error(new Exception(MISSING_PARAMETER_VALUE_ERROR_MSG)));
    }

    @GetMapping(path = "/{id}", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.TEXT_PLAIN_VALUE})
    public ResponseEntity<?> getSupplierById(@PathVariable @Valid @NotBlank String id) {
        try {
            SupplierID supplierID = Identificator.newInstance(SupplierID.class, id);
            var supplier = supplierService.getSupplierById(supplierID);
            return ResponseEntity.ok(supplier);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new Error(e));
        }
    }

    @GetMapping(path = "/name/{name}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getSupplierByName(@PathVariable @Valid @NotBlank String name) {
        try {
            var supplier = supplierService.getSupplierByName(name);
            return ResponseEntity.ok(supplier);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new Error(e));
        }
    }

    @GetMapping(path = "/nif/{nif}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getSupplierByNif(@PathVariable @Valid @NotNull NIF nif) {
        try {
            var supplier = supplierService.getSupplierByNif(nif);
            return ResponseEntity.ok(supplier);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new Error(e));
        }
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getAllSuppliers() {
        try {
            return ResponseEntity.ok(supplierService.getAllSuppliers());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new Error(e));
        }
    }

    @PostMapping()
    public ResponseEntity<?> addSupplierAsObject(@RequestBody @Valid @NotNull Supplier supplier) {
        try {
            supplierService.registerNewSupplier(supplier);
            return ResponseEntity.created(URI.create("/supplier/" + supplier.getId())).body(new Message(ADD_SUCCESS_MSG));
        } catch (DuplicatedEntityException | InvalidElementException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Error(e));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new Error(e));
        }
    }

//    @PostMapping()
//    public ResponseEntity<?> addSupplierAsParameters(@RequestParam @NotBlank String name, @NotNull NIF nif, @NotBlank String email, @NotBlank String cellPhone) {
//        try {
//            supplierService.registerNewSupplier(name, nif, email, cellPhone);
//            return ResponseEntity.created(URI.create("/supplier/" + name)).body(new Message(ADD_SUCCESS_MSG));
//        } catch (DuplicatedEntityException | InvalidElementException e) {
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Error(e));
//        } catch (Exception e) {
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new Error(e));
//        }
//    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> updateSupplier(@RequestBody @Valid @NotNull Supplier supplier) {
        try {
            supplierService.updateSupplier(supplier);
            return ResponseEntity.ok(new Message(UPDATE_SUCCESS_MSG));
        } catch (NonExistentEntityException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Error(e));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new Error(e));
        }
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<?> deleteSupplier(@PathVariable @Valid @NotBlank String id) {
        try {
            SupplierID supplierID = Identificator.newInstance(SupplierID.class, id);
            supplierService.deleteSupplier(supplierID);
            return ResponseEntity.ok(new Message(DELETE_SUCCESS_MSG));
        } catch (NonExistentEntityException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Error(e));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new Error(e));
        }
    }
}
