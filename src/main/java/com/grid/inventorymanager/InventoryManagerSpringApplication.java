package com.grid.inventorymanager;

import com.grid.inventorymanager.model.Employee;
import com.grid.inventorymanager.service.EmployeeService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class InventoryManagerSpringApplication {

	public static void main(String[] args) {
		SpringApplication.run(InventoryManagerSpringApplication.class, args);
	}

	@Bean
	public CommandLineRunner run(EmployeeService employeeService){
		return args -> {
			//crear un empleado
			Employee emp = new Employee("oscar", "oscar@ti.com");
			Employee saved = employeeService.create(emp);
			System.out.println("EMpleado guardado " + saved);
		};
	}
}
