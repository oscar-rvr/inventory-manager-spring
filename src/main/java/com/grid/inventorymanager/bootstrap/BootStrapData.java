package com.grid.inventorymanager.bootstrap;

import com.grid.inventorymanager.model.*;
import com.grid.inventorymanager.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Set;

@Component
@RequiredArgsConstructor
@Profile("local")
public class BootStrapData implements CommandLineRunner {

    private final EmployeeService employeeService;
    private final AssetService assetService;
    private final AssetMovementsService assetMovementsService;
    private final ComputerService computerService;
    private final PurchaseService purchaseService;
    private final VendorService vendorService;
    private final UserService userService;
/*
THIS IS ONLY FOR TESTING PURPOSES
*/
    @Override
    public void run(String... args) {
        // Crear empleado
        Employee emp = Employee.builder().name("Alejandro").mail("Alejandro@dev.com").build();

        Employee empSaved = employeeService.create(emp);
        System.out.println("Empleado guardado " + empSaved);

        // Crear asset
        Asset asset = Asset.builder().name("mouse").description("inalambrico").seriesNumber("123456").build();
        Asset assetSaved = assetService.create(asset);
        System.out.println("Asset Guardado " + assetSaved);

        // Crear asset movement
        assetSaved.addEmployee(empSaved);
        assetService.update(assetSaved);
        System.out.println("Asset Movement guardado " + assetSaved.getEmployees());

        // Crear computadora
        Computer computer = Computer.builder().name("MAC 2018").description("Interns usage").seriesNumber("MACC2018").ram(12).disk(128).core("i9 12va").screenState("good").keyboardState("good").shellState("scratched").comments("need to go to maintainance").build();
        Computer computerSaved = computerService.create(computer);
        System.out.println("Computer Saved" + computerSaved);

        // Crear y guardar Vendor primero
        Vendor vendorApple = Vendor.builder().name("Apple").contact("011231231231").build();
        Vendor vendorSaved = vendorService.create(vendorApple);

        // Crear los detalles de compra
        PurchaseDetail detail = PurchaseDetail.builder().asset(assetSaved).amount(5).pricePerItem(1999.99).build();

        // Crear la compra y asignarle los detalles
        Purchase purchase = Purchase.builder().vendor(vendorSaved).date(LocalDate.of(2024, 12, 12)).totalAmount(9999.95).details(Set.of(detail)).build();

        // Asignar la compra a cada detalle para mantener la relación bidireccional
        detail.setPurchase(purchase);

        // Finalmente, guardar la compra
        Purchase savedPurchase = purchaseService.create(purchase);
        System.out.println("Purchase guardada con id: " + savedPurchase.getId() + ", vendor id: " + savedPurchase.getVendor().getId());

        vendorApple.addPurchase(savedPurchase);
        //vendorService.update(vendorApple);

        //Crear usuario
        User user = User.builder().role(Role.EMPLOYEE).username("alejandro").password("password123").build();

        empSaved.addUser(user);
        employeeService.update(empSaved);

        for (int i = 0; i < 30; i++) {
            String name = "user" + i;
            String email = name + "@example.com";
            String username = name;
            String password = "Pass" + i + "!"; // puedes usar Faker si prefieres aleatorios
            Role role = (i % 2 == 0) ? Role.ADMIN : Role.EMPLOYEE;

            Employee emp1 = Employee.builder().name(name).mail(email).build();

            emp1 = employeeService.create(emp1); // guarda empleado

            User user1 = User.builder().username(username).password(password).role(role).build();

            emp1.addUser(user1);
            employeeService.update(emp1); // guarda relación bidireccional
        }

        computerService.create(Computer.builder().name("Finance Laptop").description("Laptop para análisis financiero").seriesNumber("FINA2021").ram(16).disk(512).core("i5 11th gen").screenState("excellent").keyboardState("excellent").shellState("clean").comments("Asignado a finanzas").build());

        computerService.create(Computer.builder().name("QA Tester").description("Máquina para testing automático").seriesNumber("QATE1001").ram(8).disk(256).core("i5 9th gen").screenState("good").keyboardState("good").shellState("used").comments("Uso por equipo QA").build());

        computerService.create(Computer.builder().name("Video Editing Rig").description("Edición de video 4K").seriesNumber("VIDE2023").ram(64).disk(2048).core("i9 13th gen").screenState("excellent").keyboardState("excellent").shellState("mint").comments("Equipo de producción audiovisual").build());

        computerService.create(Computer.builder().name("Linux Server").description("Servidor interno para pruebas").seriesNumber("LINS9001").ram(32).disk(1024).core("Xeon E5").screenState("none").keyboardState("none").shellState("rack-mounted").comments("Solo acceso remoto").build());

        computerService.create(Computer.builder().name("Training Room PC").description("Máquina en sala de capacitación").seriesNumber("TRAI1234").ram(8).disk(500).core("i3 8th gen").screenState("fair").keyboardState("fair").shellState("scratched").comments("Uso compartido").build());

    }

}
