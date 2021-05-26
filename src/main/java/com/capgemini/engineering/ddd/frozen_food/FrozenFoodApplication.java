package com.capgemini.engineering.ddd.frozen_food;

import com.capgemini.engineering.ddd.frozen_food._shared.Unit;
import com.capgemini.engineering.ddd.frozen_food._shared.id.IngredientID;
import com.capgemini.engineering.ddd.frozen_food.menu.infra.dao.IngredientDAO;
import com.capgemini.engineering.ddd.frozen_food.stock.domain.entity.*;
import com.capgemini.engineering.ddd.frozen_food.stock.domain.service.IngredientService;
import com.capgemini.engineering.ddd.frozen_food.stock.domain.valueObject.NIF;
import com.capgemini.engineering.ddd.frozen_food.stock.infra.dao.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.HashMap;
import java.util.Map;

@SpringBootApplication
public class FrozenFoodApplication implements CommandLineRunner {

	@Autowired
	private ChefOrderDAO chefOrderDAO;
	@Autowired
	private ProductionOrderDAO productionOrderDAO;
	@Autowired
	private SupplierOrderDAO supplierOrderDAO;
	@Autowired
	private StockIngredientDAO stockIngredientDAO;
	@Autowired
	private SupplierDAO supplierDAO;

	@Autowired
	private IngredientService ingredientService;

	public static void main(String[] args) {
		SpringApplication.run(FrozenFoodApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		chefOrderDAO.deleteAll();
		productionOrderDAO.deleteAll();
		supplierOrderDAO.deleteAll();
		stockIngredientDAO.deleteAll();
		supplierDAO.deleteAll();

		Ingredient ingredient1 = new Ingredient("apple", Unit.KG);
		Ingredient ingredient2 = new Ingredient("onion", Unit.KG);
		Ingredient ingredient3 = new Ingredient("pineapple", Unit.KG);

		stockIngredientDAO.save(ingredient1);
		stockIngredientDAO.save(ingredient2);
		stockIngredientDAO.save(ingredient3);

		Map<Ingredient, Integer> order = new HashMap();
		Map<String, Integer> orderID = new HashMap();

		order.put(ingredient1, 100);
		order.put(ingredient2, 50);
		order.put(ingredient3, 20);

		orderID.put(ingredient1.getId().toString(), 100);
		orderID.put(ingredient2.getId().toString(), 50);
		orderID.put(ingredient3.getId().toString(), 20);

		ChefOrder chefOrder = new ChefOrder("BUY1", orderID);
		ProductionOrder productionOrder = new ProductionOrder("POrder", orderID);

		Supplier supplier = new Supplier("Produtos Fresquinhos", new NIF("517408082"), "pf@pf.com", "914659875");

		SupplierOrder supplierOrder = new SupplierOrder("Sup001", orderID, supplier.getId(), 1234.25);
		supplierDAO.save(supplier);

		chefOrderDAO.save(chefOrder);

		productionOrderDAO.save(productionOrder);

		supplierOrderDAO.save(supplierOrder);
	}
}
