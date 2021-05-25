package com.capgemini.engineering.ddd.frozen_food.stock.infra.controller;

import com.capgemini.engineering.ddd.frozen_food._shared.id.Identificator;
import com.capgemini.engineering.ddd.frozen_food._shared.id.IngredientID;
import com.capgemini.engineering.ddd.frozen_food._shared.utils.Error;
import com.capgemini.engineering.ddd.frozen_food._shared.utils.Message;
import com.capgemini.engineering.ddd.frozen_food.stock.domain.valueObject.IngredientStatus;
import com.capgemini.engineering.ddd.frozen_food.stock.domain.entity.Ingredient;
import com.capgemini.engineering.ddd.frozen_food.stock.domain.exception.DuplicatedEntityException;
import com.capgemini.engineering.ddd.frozen_food.stock.domain.exception.NonExistentEntityException;
import com.capgemini.engineering.ddd.frozen_food.stock.domain.service.IngredientService;
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
@RequestMapping("/stock/ingredient")
@Validated
public class StockIngredientController {

    static final String NO_DATA_ERROR_MSG = "NO DATA RECEIVED ERROR";
    static final String ADD_SUCCESS_MSG = "Ingredient added successfully!";
    static final String UPDATE_SUCCESS_MSG = "Ingredient updated successfully!";
    static final String DELETE_SUCCESS_MSG = "Ingredient deleted successfully!";
    static final String MISSING_PARAMETER_ERROR_MSG = "MISSING PARAMETER ERROR";
    static final String MISSING_PARAMETER_VALUE_ERROR_MSG = "MISSING PARAMETER VALUE ERROR";

    @Autowired
    private IngredientService ingredientService;

    public StockIngredientController() {
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    private ResponseEntity<?> handleInvalidData(MethodArgumentNotValidException ex) {
        List<String> messages = ex.getAllErrors().stream().map(DefaultMessageSourceResolvable::getDefaultMessage).collect(Collectors.toList());
        return ResponseEntity.badRequest().contentType(MediaType.APPLICATION_JSON).body(messages);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    private ResponseEntity<?> handleNoData(HttpMessageNotReadableException ex) {
        return ResponseEntity.badRequest().body(new Error(new Exception(NO_DATA_ERROR_MSG)));
    }

    @ExceptionHandler(MissingServletRequestParameterException.class)
    private ResponseEntity<?> handleNoData(MissingServletRequestParameterException ex) {
        return ResponseEntity.badRequest().body(new Error(new Exception(MISSING_PARAMETER_ERROR_MSG)));
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    private ResponseEntity<?> handleNoData(MethodArgumentTypeMismatchException ex) {
        return ResponseEntity.badRequest().body(new Error(new Exception(MISSING_PARAMETER_VALUE_ERROR_MSG)));
    }

    @GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getIngredientById(@PathVariable @Valid String id) {
        try {
            IngredientID ingredientID = Identificator.newInstance(IngredientID.class, id);
            var ingredient = ingredientService.getIngredientById(ingredientID);
            return ResponseEntity.ok(ingredient);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new Error(e));
        }
    }

    @GetMapping(path = "/name/{name}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getIngredientByName(@PathVariable @Valid String name) {
        try {
            var ingredient = ingredientService.getIngredientByName(name);
            return ResponseEntity.ok(ingredient);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new Error(e));
        }
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getAllIngredients() {
        try {
            return ResponseEntity.ok(ingredientService.getAllIngredients());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new Error(e));
        }
    }

    @GetMapping(path = "/in_use", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getAllIngredientsInUse() {
        try {
            return ResponseEntity.ok(ingredientService.getAllIngredientsByIngredientStatus(IngredientStatus.IN_USE));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new Error(e));
        }
    }

    @GetMapping(path = "/not_in_use", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getAllIngredientsNotInUse() {
        try {
            return ResponseEntity.ok(ingredientService.getAllIngredientsByIngredientStatus(IngredientStatus.NOT_IN_USE));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new Error(e));
        }
    }

    @GetMapping(path = "/in_test", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getAllIngredientsInTest() {
        try {
            return ResponseEntity.ok(ingredientService.getAllIngredientsByIngredientStatus(IngredientStatus.IN_TEST));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new Error(e));
        }
    }

    @PostMapping()
    public ResponseEntity<?> addIngredient(@RequestBody @Valid @NotNull Ingredient ingredient) {
        try {
            ingredientService.registerNewIngredient(ingredient);
            return ResponseEntity.created(URI.create("/ingredient/" + ingredient.id())).body(new Message(ADD_SUCCESS_MSG));
        } catch (DuplicatedEntityException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Error(e));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new Error(e));
        }
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> updateIngredient(@RequestBody @Valid @NotNull Ingredient ingredient) {
        try {
            ingredientService.updateIngredient(ingredient);
            return ResponseEntity.ok(new Message(UPDATE_SUCCESS_MSG));
        } catch (NonExistentEntityException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Error(e));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new Error(e));
        }
    }

    @PutMapping(path = "/status/{id}")
    public ResponseEntity<?> updateIngredientStatus(@PathVariable @Valid @NotBlank String id, @RequestParam @NotBlank String ingredientStatus) {
        try {
            IngredientID ingredientID = Identificator.newInstance(IngredientID.class, id);
            ingredientService.updateIngredientUseStatus(ingredientID, IngredientStatus.valueOf(ingredientStatus));
            return ResponseEntity.ok(new Message(UPDATE_SUCCESS_MSG));
        } catch (NonExistentEntityException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Error(e));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new Error(e));
        }
    }

    @PutMapping(path = "/stock/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> updateIngredientMinimumStockValue(@PathVariable @Valid @NotNull String id, @RequestParam @Valid @NotNull Integer minimumStockValue) {
        try {
            IngredientID ingredientID = Identificator.newInstance(IngredientID.class, id);
            ingredientService.updateIngredientMinimumStockValue(ingredientID, minimumStockValue);
            return ResponseEntity.ok(new Message(UPDATE_SUCCESS_MSG));
        } catch (NonExistentEntityException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Error(e));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new Error(e));
        }
    }

    @PutMapping(path = "/increase_stock/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> increaseIngredientStock(@PathVariable @Valid @NotNull String id, @RequestParam @Valid @NotNull Integer quantity) {
        try {
            IngredientID ingredientID = Identificator.newInstance(IngredientID.class, id);
            ingredientService.increaseIngredientStock(ingredientID, quantity);
            return ResponseEntity.ok(new Message(UPDATE_SUCCESS_MSG));
        } catch (NonExistentEntityException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Error(e));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new Error(e));
        }
    }

    @PutMapping(path = "/decrease_stock/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> decreaseIngredientStock(@PathVariable @Valid @NotNull String id, @RequestParam @Valid @NotNull Integer quantity) {
        try {
            IngredientID ingredientID = Identificator.newInstance(IngredientID.class, id);
            ingredientService.decreaseIngredientStock(ingredientID, quantity);
            return ResponseEntity.ok(new Message(UPDATE_SUCCESS_MSG));
        } catch (NonExistentEntityException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Error(e));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new Error(e));
        }
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<?> deleteIngredient(@PathVariable @Valid String id) {
        try {
            IngredientID ingredientID = Identificator.newInstance(IngredientID.class, id);
            ingredientService.deleteIngredient(ingredientID);
            return ResponseEntity.ok(new Message(DELETE_SUCCESS_MSG));
        } catch (NonExistentEntityException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Error(e));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new Error(e));
        }
    }
}
