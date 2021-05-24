package com.capgemini.engineering.ddd.frozen_food;

import com.capgemini.engineering.ddd.frozen_food._shared.OrderDeliveryState;
import com.capgemini.engineering.ddd.frozen_food._shared.id.CustomerID;
import com.capgemini.engineering.ddd.frozen_food._shared.id.Identificator;
import com.capgemini.engineering.ddd.frozen_food._shared.id.OrderID;
import com.capgemini.engineering.ddd.frozen_food._shared.id.ProductID;
import com.capgemini.engineering.ddd.frozen_food.sales.domain.entity.Customer;
import com.capgemini.engineering.ddd.frozen_food.sales.domain.entity.Order;
import com.capgemini.engineering.ddd.frozen_food.sales.domain.entity.Product;
import com.capgemini.engineering.ddd.frozen_food.sales.domain.repository.CustomerRepository;
import com.capgemini.engineering.ddd.frozen_food.sales.domain.repository.OrderRepository;
import com.capgemini.engineering.ddd.frozen_food.sales.domain.repository.ProductRepository;
import com.capgemini.engineering.ddd.frozen_food.sales.domain.valueObject.Address;
import com.capgemini.engineering.ddd.frozen_food.sales.domain.valueObject.Dimensions;
import com.capgemini.engineering.ddd.frozen_food.sales.domain.valueObject.NIF;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDate;

//ATTENTION: DO NOT UPLOAD THIS CLASS FILE !!!!!!!!!!
@SpringBootApplication
public class FrozenFoodApplication implements CommandLineRunner {

	@Autowired
	private CustomerRepository customerRepository;

	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private OrderRepository orderRepository;

	public static void main(String[] args) {
		SpringApplication.run(FrozenFoodApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		//wipe the database clean when starting
		this.productRepository.deleteAll();
		this.customerRepository.deleteAll();
		this.orderRepository.deleteAll();

		Product product1 = new Product();
		product1.setProductID(Identificator.newInstance(ProductID.class));
		product1.setUnitPrice(563.45);
		product1.setDimensions(new Dimensions(1, 2, 4.5, 11.2));
		product1.setName("ProdutoTeste 1");
		product1.setShelfLife(30);
		product1.setAvailable(true);

		Product product2 = new Product();
		product2.setProductID(Identificator.newInstance(ProductID.class));
		product2.setUnitPrice(3550.99);
		product2.setDimensions(new Dimensions(2, 150, 0.5, 5000.33));
		product2.setName("ProdutoTeste 2");
		product2.setShelfLife(100);
		product2.setAvailable(true);

		product1 = this.productRepository.save(product1);
		product2 = this.productRepository.save(product2);

		Customer customer1 = new Customer();
		customer1.setCustomerID(Identificator.newInstance(CustomerID.class));
		customer1.setName("CustomerTeste 1");
		customer1.setNif(new NIF("136532489"));
		customer1.setEmail("customer1_Teste@gmail.com");
		customer1.setCellphoneNumber("91 91 91 999");
		customer1.setAddress(new Address("Street 1", "5º D", "5000-333", "note1"));

		Customer customer2 = new Customer();
		customer2.setCustomerID(Identificator.newInstance(CustomerID.class));
		customer2.setName("CustomerTeste 2");
		customer2.setNif(new NIF("181320886"));
		customer2.setEmail("customer2_Teste2@gmail.com");
		customer2.setCellphoneNumber("93 91 91 777");
		customer2.setAddress(new Address("Street 2", "1º D", "4000-111", "note_2"));

		customer1 = this.customerRepository.save(customer1);
		customer2 = this.customerRepository.save(customer2);

		Order order1 = new Order();
		order1.setOrderID(Identificator.newInstance(OrderID.class));
		order1.setOrderedBy(customer1);
		order1.setOrderDeliveryState(OrderDeliveryState.PROCESSING);
		order1.setCreationDate(LocalDate.now());
		order1.getProductsOrdered().put(product1, 200);

		Order order2 = new Order();
		order2.setOrderID(Identificator.newInstance(OrderID.class));
		order2.setOrderedBy(customer2);
		order2.setOrderDeliveryState(OrderDeliveryState.CONFIRMED);
		order2.setCreationDate(LocalDate.now());
		order2.getProductsOrdered().put(product1, 50);
		order2.getProductsOrdered().put(product2, 110);

		this.orderRepository.save(order1);
		this.orderRepository.save(order2);
	}
}
