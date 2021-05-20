package com.capgemini.engineering.ddd.frozen_food.stock.infra.controller;

import com.capgemini.engineering.ddd.frozen_food._shared.id.IngredientID;
import com.capgemini.engineering.ddd.frozen_food._shared.stock.dto.ErrorDTO;
import com.capgemini.engineering.ddd.frozen_food._shared.stock.dto.MessageDTO;
import com.capgemini.engineering.ddd.frozen_food.stock.domain.valueObject.IngredientStatus;
import com.capgemini.engineering.ddd.frozen_food.stock.domain.entity.Ingredient;
import com.capgemini.engineering.ddd.frozen_food.stock.domain.exception.DuplicatedEntityException;
import com.capgemini.engineering.ddd.frozen_food.stock.domain.exception.NonExistentEntityException;
import com.capgemini.engineering.ddd.frozen_food.stock.domain.service.IngredientService;
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
@RequestMapping("/ingredient")
@Validated
public class IngredientController {

    static final String NO_DATA_ERROR_MSG = "NO DATA RECEIVED ERROR";
    static final String ADD_SUCCESS_MSG = "Ingredient added successfully!";
    static final String UPDATE_SUCCESS_MSG = "Ingredient updated successfully!";
    static final String DELETE_SUCCESS_MSG = "Ingredient deleted successfully!";
    static final String MISSING_PARAMETER_ERROR_MSG = "MISSING PARAMETER ERROR";
    static final String MISSING_PARAMETER_VALUE_ERROR_MSG = "MISSING PARAMETER VALUE ERROR";

    @Autowired
    private IngredientService ingredientService;

    public IngredientController() {
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

    @GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getIngredientById(@PathVariable @Valid String id) {
        try {
            String jsonString = String.format("{\"id\":\"%s\"}", id);
            ObjectMapper mapper = new ObjectMapper();
            var ingredientID = mapper.readValue(jsonString, IngredientID.class);
            var ingredient = ingredientService.getIngredientByIngredientID(ingredientID);
            return ResponseEntity.ok(ingredient);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ErrorDTO(e));
        }
    }

    @GetMapping(path = "/name/{name}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getIngredientByName(@PathVariable @Valid String name) {
        try {
            var ingredient = ingredientService.getIngredientByName(name);
            return ResponseEntity.ok(ingredient);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ErrorDTO(e));
        }
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getAllIngredients() {
        try {
            return ResponseEntity.ok(ingredientService.getAllIngredients());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ErrorDTO(e));
        }
    }

    @GetMapping(path = "/in_use", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getAllIngredientsInUse() {
        try {
            return ResponseEntity.ok(ingredientService.getAllIngredientsByIngredientStatus(IngredientStatus.IN_USE));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ErrorDTO(e));
        }
    }

    @GetMapping(path = "/not_in_use", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getAllIngredientsNotInUse() {
        try {
            return ResponseEntity.ok(ingredientService.getAllIngredientsByIngredientStatus(IngredientStatus.NOT_IN_USE));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ErrorDTO(e));
        }
    }

    @GetMapping(path = "/in_test", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getAllIngredientsInTest() {
        try {
            return ResponseEntity.ok(ingredientService.getAllIngredientsByIngredientStatus(IngredientStatus.IN_TEST));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ErrorDTO(e));
        }
    }

    @PostMapping()
    public ResponseEntity<?> addIngredient(@RequestBody @Valid @NotNull Ingredient ingredient) {
        try {
            ingredientService.registerNewIngredient(ingredient);
            return ResponseEntity.created(URI.create("/ingredient/" + ingredient.getId())).body(new MessageDTO(ADD_SUCCESS_MSG));
        } catch (DuplicatedEntityException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorDTO(e));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ErrorDTO(e));
        }
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> updateIngredient(@RequestBody @Valid @NotNull Ingredient ingredient) {
        try {
            ingredientService.updateIngredient(ingredient);
            return ResponseEntity.ok(new MessageDTO(UPDATE_SUCCESS_MSG));
        } catch (NonExistentEntityException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorDTO(e));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ErrorDTO(e));
        }
    }

    @PutMapping(path = "/status/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> updateIngredientStatus(@PathVariable @Valid @NotNull String id, @RequestParam @Valid @NotNull IngredientStatus ingredientStatus) {
        try {
            String jsonString = String.format("{ \"id\" : \"%s\" }", id);
            ObjectMapper mapper = new ObjectMapper();
            var ingredientID = mapper.readValue(jsonString, IngredientID.class);
            ingredientService.updateIngredientUseStatus(ingredientID, ingredientStatus);
            return ResponseEntity.ok(new MessageDTO(UPDATE_SUCCESS_MSG));
        } catch (NonExistentEntityException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorDTO(e));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ErrorDTO(e));
        }
    }

    @PutMapping(path = "/stock/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> updateIngredientMinimumStockValue(@PathVariable @Valid @NotNull String id, @RequestParam @Valid @NotNull Integer minimumStockValue) {
        try {
            String jsonString = String.format("{ \"id\" : \"%s\" }", id);
            ObjectMapper mapper = new ObjectMapper();
            var ingredientID = mapper.readValue(jsonString, IngredientID.class);
            ingredientService.updateIngredientMinimumStockValue(ingredientID, minimumStockValue);
            return ResponseEntity.ok(new MessageDTO(UPDATE_SUCCESS_MSG));
        } catch (NonExistentEntityException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorDTO(e));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ErrorDTO(e));
        }
    }

    @PutMapping(path = "/increase_stock/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> increaseIngredientStock(@PathVariable @Valid @NotNull String id, @RequestParam @Valid @NotNull Integer quantity) {
        try {
            String jsonString = String.format("{ \"id\" : \"%s\" }", id);
            ObjectMapper mapper = new ObjectMapper();
            var ingredientID = mapper.readValue(jsonString, IngredientID.class);
            ingredientService.increaseIngredientStock(ingredientID, quantity);
            return ResponseEntity.ok(new MessageDTO(UPDATE_SUCCESS_MSG));
        } catch (NonExistentEntityException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorDTO(e));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ErrorDTO(e));
        }
    }

    @PutMapping(path = "/decrease_stock/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> decreaseIngredientStock(@PathVariable @Valid @NotNull String id, @RequestParam @Valid @NotNull Integer quantity) {
        try {
            String jsonString = String.format("{ \"id\" : \"%s\" }", id);
            ObjectMapper mapper = new ObjectMapper();
            var ingredientID = mapper.readValue(jsonString, IngredientID.class);
            ingredientService.decreaseIngredientStock(ingredientID, quantity);
            return ResponseEntity.ok(new MessageDTO(UPDATE_SUCCESS_MSG));
        } catch (NonExistentEntityException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorDTO(e));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ErrorDTO(e));
        }
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<?> deleteIngredient(@PathVariable @Valid String id) {
        try {
            String jsonString = String.format("{ \"id\" : \"%s\" }", id);
            ObjectMapper mapper = new ObjectMapper();
            var ingredientID = mapper.readValue(jsonString, IngredientID.class);
            ingredientService.deleteIngredient(ingredientID);
            return ResponseEntity.ok(new MessageDTO(DELETE_SUCCESS_MSG));
        } catch (NonExistentEntityException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorDTO(e));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ErrorDTO(e));
        }
    }
}
