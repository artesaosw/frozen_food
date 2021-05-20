package com.capgemini.engineering.ddd.frozen_food.stock.infra.controller;

import com.capgemini.engineering.ddd.frozen_food._shared.stock.dto.ErrorDTO;
import com.capgemini.engineering.ddd.frozen_food._shared.stock.dto.MessageDTO;
import com.capgemini.engineering.ddd.frozen_food.stock.domain.valueObject.SupplierOrderID;
import com.capgemini.engineering.ddd.frozen_food.stock.domain.valueObject.OrderStatus;
import com.capgemini.engineering.ddd.frozen_food.stock.domain.entity.SupplierOrder;
import com.capgemini.engineering.ddd.frozen_food.stock.domain.exception.DuplicatedEntityException;
import com.capgemini.engineering.ddd.frozen_food.stock.domain.exception.NonExistentEntityException;
import com.capgemini.engineering.ddd.frozen_food.stock.domain.service.SupplierOrderService;
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
import javax.validation.constraints.NotNull;
import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/order")
@Validated
public class SupplierOrderController {

    static final String NO_DATA_ERROR_MSG = "NO DATA RECEIVED ERROR";
    static final String ADD_SUCCESS_MSG = "Supplier Order added successfully!";
    static final String UPDATE_SUCCESS_MSG = "Supplier Order updated successfully!";
    static final String DELETE_SUCCESS_MSG = "Supplier Order deleted successfully!";
    static final String MISSING_PARAMETER_ERROR_MSG = "MISSING PARAMETER ERROR";
    static final String MISSING_PARAMETER_VALUE_ERROR_MSG = "MISSING PARAMETER VALUE ERROR";

    @Autowired
    private SupplierOrderService supplierOrderService;

    public SupplierOrderController() {
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    private ResponseEntity<?> handleInvalidData(MethodArgumentNotValidException ex) {
        List<String> messages = ex.getAllErrors().stream().map(DefaultMessageSourceResolvable::getDefaultMessage).collect(Collectors.toList());
        return ResponseEntity.badRequest().contentType(MediaType.APPLICATION_JSON).body(messages);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    private ResponseEntity<?> handleNoData(HttpMessageNotReadableException ex) {
        return ResponseEntity.badRequest().body(new ErrorDTO(new Exception(NO_DATA_ERROR_MSG)));
    }

    @ExceptionHandler(MissingServletRequestParameterException.class)
    private ResponseEntity<?> handleNoData(MissingServletRequestParameterException ex) {
        return ResponseEntity.badRequest().body(new ErrorDTO(new Exception(MISSING_PARAMETER_ERROR_MSG)));
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    private ResponseEntity<?> handleNoData(MethodArgumentTypeMismatchException ex) {
        return ResponseEntity.badRequest().body(new ErrorDTO(new Exception(MISSING_PARAMETER_VALUE_ERROR_MSG)));
    }

    @GetMapping(path = "/supplier/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getChefOrderById(@PathVariable @Valid String id) {
        try {
            String jsonString = String.format("{\"id\":\"%s\"}", id);
            ObjectMapper mapper = new ObjectMapper();
            var supplierOrderID = mapper.readValue(jsonString, SupplierOrderID.class);
            var supplierOrder = supplierOrderService.getSupplierOrderBySupplierOrderID(supplierOrderID);
            return ResponseEntity.ok(supplierOrder);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ErrorDTO(e));
        }
    }

    @GetMapping(path = "/supplier", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getAllSuppliersOrders() {
        try {
            return ResponseEntity.ok(supplierOrderService.getAllSuppliersOrders());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ErrorDTO(e));
        }
    }

    @GetMapping(path = "/supplier/delivered", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getAllDeliveredSuppliersOrders() {
        try {
            return ResponseEntity.ok(supplierOrderService.getAllSuppliersOrdersByOrderStatus(OrderStatus.DELIVERED));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ErrorDTO(e));
        }
    }

    @GetMapping(path = "/supplier/undelivered", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getAllUndeliveredSuppliersOrders() {
        try {
            return ResponseEntity.ok(supplierOrderService.getAllSuppliersOrdersByOrderStatus(OrderStatus.UNDELIVERED));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ErrorDTO(e));
        }
    }

    @GetMapping(path = "/supplier/canceled", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getAllCanceledSuppliersOrders() {
        try {
            return ResponseEntity.ok(supplierOrderService.getAllSuppliersOrdersByOrderStatus(OrderStatus.CANCELED));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ErrorDTO(e));
        }
    }

    @PostMapping(path = "/supplier")
    public ResponseEntity<?> addSupplierOrder(@RequestBody @Valid @NotNull SupplierOrder supplierOrder) {
        try {
            supplierOrderService.registerNewSupplierOrder(supplierOrder);
            return ResponseEntity.created(URI.create("/supplier/" + supplierOrder.getId())).body(new MessageDTO(ADD_SUCCESS_MSG));
        } catch (DuplicatedEntityException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorDTO(e));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ErrorDTO(e));
        }
    }

    @PutMapping(path = "/supplier", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> updateSupplierOrder(@RequestBody @Valid @NotNull SupplierOrder supplierOrder) {
        try {
            supplierOrderService.updateSupplierOrder(supplierOrder);
            return ResponseEntity.ok(new MessageDTO(UPDATE_SUCCESS_MSG));
        } catch (NonExistentEntityException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorDTO(e));
        }catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ErrorDTO(e));
        }
    }

    @PutMapping(path = "/supplier/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> updateSupplierOrderStatus(@PathVariable @Valid @NotNull String id, @RequestParam @Valid @NotNull OrderStatus orderStatus) {
        try {
            String jsonString = String.format("{\"id\":\"%s\"}", id);
            ObjectMapper mapper = new ObjectMapper();
            var supplierOrderID = mapper.readValue(jsonString, SupplierOrderID.class);
            supplierOrderService.updateSupplierOrderStatus(supplierOrderID, orderStatus);
            return ResponseEntity.ok(new MessageDTO(UPDATE_SUCCESS_MSG));
        } catch (NonExistentEntityException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorDTO(e));
        }catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ErrorDTO(e));
        }
    }

    @DeleteMapping(path = "/supplier/{id}")
    public ResponseEntity<?> deleteSupplierOrder(@PathVariable @Valid String id) {
        try {
            String jsonString = String.format("{\"id\":\"%s\"}", id);
            ObjectMapper mapper = new ObjectMapper();
            var supplierOrderID = mapper.readValue(jsonString, SupplierOrderID.class);
            supplierOrderService.deleteSupplierOrder(supplierOrderID);
            return ResponseEntity.ok(new MessageDTO(DELETE_SUCCESS_MSG));
        } catch (NonExistentEntityException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorDTO(e));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ErrorDTO(e));
        }
    }
}
