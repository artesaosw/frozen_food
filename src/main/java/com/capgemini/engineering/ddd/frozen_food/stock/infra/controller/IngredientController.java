package com.capgemini.engineering.ddd.frozen_food.stock.infra.controller;

import com.capgemini.engineering.ddd.frozen_food._shared.IngredientID;
import com.capgemini.engineering.ddd.frozen_food.stock.domain.IngredientStatus;
import com.capgemini.engineering.ddd.frozen_food.stock.domain.entity.Ingredient;
import com.capgemini.engineering.ddd.frozen_food.stock.domain.exception.DuplicatedKeyException;
import com.capgemini.engineering.ddd.frozen_food.stock.domain.exception.NonExistentIngredientException;
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
import javax.validation.constraints.NotNull;
import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/ingredient")
@Validated
public class IngredientController {

    static final String GENERAL_ERROR_MSG = "INTERNAL_ERROR";
    static final String NO_DATA_ERROR_MSG = "NO_DATA_RECEIVED_ERROR";
    static final String DUPLICATED_INGREDIENT_ERROR_MSG = "DUPLICATED_INGREDIENT_ERROR";
    static final String ADD_SUCCESS_MSG = "ADD_SUCCESS";
    static final String UPDATE_SUCCESS_MSG = "UPDATE_SUCCESS";
    static final String DELETE_SUCCESS_MSG = "DELETE_SUCCESS";
    static final String INGREDIENT_NOT_FOUND_ERROR_MSG = "INGREDIENT_NOT_FOUND";
    static final String MISSING_PARAMETER_ERROR_MSG = "MISSING_PARAMETER_ERROR";
    static final String MISSING_PARAMETER_VALUE_ERROR_MSG = "MISSING_PARAMETER_VALUE_ERROR";

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
    public ResponseEntity<?> getIngredientById(@PathVariable @Valid IngredientID id) {
        try {
            var ingredient = ingredientService.getIngredientById(id);
            return ResponseEntity.ok(ingredient);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).contentType(MediaType.TEXT_PLAIN).body(GENERAL_ERROR_MSG);
        }
    }

    @GetMapping(path = "/{name}", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.TEXT_PLAIN_VALUE})
    public ResponseEntity<?> getIngredientByName(@PathVariable @Valid String name) {
        try {
            var ingredient = ingredientService.getIngredientByName(name);
            return ResponseEntity.ok(ingredient);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).contentType(MediaType.TEXT_PLAIN).body(GENERAL_ERROR_MSG);
        }
    }

    @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.TEXT_PLAIN_VALUE})
    public ResponseEntity<?> getAllIngredients() {
        try {
            return ResponseEntity.ok(ingredientService.getAllIngredients());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).contentType(MediaType.TEXT_PLAIN).body(GENERAL_ERROR_MSG);
        }
    }

    @GetMapping(path = "/in_use/", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.TEXT_PLAIN_VALUE})
    public ResponseEntity<?> getAllIngredientsInUse() {
        try {
            return ResponseEntity.ok(ingredientService.getAllIngredientsByIngredientStatus(IngredientStatus.IN_USE));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).contentType(MediaType.TEXT_PLAIN).body(GENERAL_ERROR_MSG);
        }
    }

    @GetMapping(path = "/not_in_use/", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.TEXT_PLAIN_VALUE})
    public ResponseEntity<?> getAllIngredientsNotInUse() {
        try {
            return ResponseEntity.ok(ingredientService.getAllIngredientsByIngredientStatus(IngredientStatus.NOT_IN_USE));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).contentType(MediaType.TEXT_PLAIN).body(GENERAL_ERROR_MSG);
        }
    }

    @GetMapping(path = "/in_test/", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.TEXT_PLAIN_VALUE})
    public ResponseEntity<?> getAllIngredientsInTest() {
        try {
            return ResponseEntity.ok(ingredientService.getAllIngredientsByIngredientStatus(IngredientStatus.IN_TEST));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).contentType(MediaType.TEXT_PLAIN).body(GENERAL_ERROR_MSG);
        }
    }

    @PostMapping(produces = {MediaType.TEXT_PLAIN_VALUE})
    public ResponseEntity<?> addIngredient(@RequestBody @Valid @NotNull Ingredient ingredient) {
        try {
            ingredientService.registerNewIngredient(ingredient);
            return ResponseEntity.created(URI.create("/ingredient/" + ingredient.getId())).contentType(MediaType.TEXT_PLAIN).body(ADD_SUCCESS_MSG);
        } catch (DuplicatedKeyException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).contentType(MediaType.TEXT_PLAIN).body(DUPLICATED_INGREDIENT_ERROR_MSG);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).contentType(MediaType.TEXT_PLAIN).body(GENERAL_ERROR_MSG);
        }
    }

    @PutMapping(consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.TEXT_PLAIN_VALUE})
    public ResponseEntity<?> updateIngredient(@RequestBody @Valid @NotNull Ingredient ingredient) {
        try {
            ingredientService.updateIngredient(ingredient);
            return ResponseEntity.ok(UPDATE_SUCCESS_MSG);
        } catch (NonExistentIngredientException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).contentType(MediaType.TEXT_PLAIN).body(INGREDIENT_NOT_FOUND_ERROR_MSG);
        }catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).contentType(MediaType.TEXT_PLAIN).body(GENERAL_ERROR_MSG);
        }
    }

    @PutMapping(path = "/status/{id}", consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.TEXT_PLAIN_VALUE})
    public ResponseEntity<?> updateIngredientStatus(@PathVariable @Valid @NotNull IngredientID id, @RequestParam @Valid @NotNull IngredientStatus ingredientStatus) {
        try {
            ingredientService.updateIngredientUseStatus(id, ingredientStatus);
            return ResponseEntity.ok(UPDATE_SUCCESS_MSG);
        } catch (NonExistentIngredientException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).contentType(MediaType.TEXT_PLAIN).body(INGREDIENT_NOT_FOUND_ERROR_MSG);
        }catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).contentType(MediaType.TEXT_PLAIN).body(GENERAL_ERROR_MSG);
        }
    }

    @PutMapping(path = "/stock/{id}", consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.TEXT_PLAIN_VALUE})
    public ResponseEntity<?> updateIngredientMinimumStockValue(@PathVariable @Valid @NotNull IngredientID id, @RequestParam @Valid @NotNull Integer minimumStockValue) {
        try {
            ingredientService.updateIngredientMinimumStockValue(id, minimumStockValue);
            return ResponseEntity.ok(UPDATE_SUCCESS_MSG);
        } catch (NonExistentIngredientException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).contentType(MediaType.TEXT_PLAIN).body(INGREDIENT_NOT_FOUND_ERROR_MSG);
        }catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).contentType(MediaType.TEXT_PLAIN).body(GENERAL_ERROR_MSG);
        }
    }

    @PutMapping(path = "/increase_stock/{id}", consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.TEXT_PLAIN_VALUE})
    public ResponseEntity<?> increaseIngredientStock(@PathVariable @Valid @NotNull IngredientID id, @RequestParam @Valid @NotNull Integer increaseIngredientStock) {
        try {
            ingredientService.increaseIngredientStock(id, increaseIngredientStock);
            return ResponseEntity.ok(UPDATE_SUCCESS_MSG);
        } catch (NonExistentIngredientException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).contentType(MediaType.TEXT_PLAIN).body(INGREDIENT_NOT_FOUND_ERROR_MSG);
        }catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).contentType(MediaType.TEXT_PLAIN).body(GENERAL_ERROR_MSG);
        }
    }

    @PutMapping(path = "/decrease_stock/{id}", consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.TEXT_PLAIN_VALUE})
    public ResponseEntity<?> decreaseIngredientStock(@PathVariable @Valid @NotNull IngredientID id, @RequestParam @Valid @NotNull Integer decreaseIngredientStock) {
        try {
            ingredientService.decreaseIngredientStock(id, decreaseIngredientStock);
            return ResponseEntity.ok(UPDATE_SUCCESS_MSG);
        } catch (NonExistentIngredientException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).contentType(MediaType.TEXT_PLAIN).body(INGREDIENT_NOT_FOUND_ERROR_MSG);
        }catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).contentType(MediaType.TEXT_PLAIN).body(GENERAL_ERROR_MSG);
        }
    }

    @DeleteMapping(path = "/{id}", produces = {MediaType.TEXT_PLAIN_VALUE})
    public ResponseEntity<?> deleteIngredient(@PathVariable @Valid IngredientID id) {
        try {
            ingredientService.deleteIngredient(id);
            return ResponseEntity.ok(DELETE_SUCCESS_MSG);
        } catch (NonExistentIngredientException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).contentType(MediaType.TEXT_PLAIN).body(INGREDIENT_NOT_FOUND_ERROR_MSG);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).contentType(MediaType.TEXT_PLAIN).body(GENERAL_ERROR_MSG);
        }
    }
}
