package com.grid.inventorymanager.bootstrap;

import com.grid.inventorymanager.model.*;
import com.grid.inventorymanager.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

@Component
@RequiredArgsConstructor
@Profile("!test")
public class BootStrapData implements CommandLineRunner {

    private final EmployeeService employeeService;
    private final AssetService assetService;
    private final AssetMovementsService assetMovementsService;
    private final ComputerService computerService;
    private final PurchaseService purchaseService;
    private final VendorService vendorService;
    private final UserService userService;

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
        AssetMovements assetMovements = AssetMovements.builder()
                .employee(empSaved)
                .asset(assetSaved)
                .movementType(MovementType.ASSIGN)
                .assetMovementDate(LocalDate.of(2024, 12, 12))
                .build();
        AssetMovements assetMovementsSaved = assetMovementsService.create(assetMovements);
        System.out.println("Asset Movement guardado " + assetMovementsSaved);

        // Crear computadora
        Computer computer = Computer.builder()
                .name("MAC 2018")
                .description("Interns usage")
                .seriesNumber("MAC2018ABC1234")
                .ram(12)
                .disk(128)
                .core("i9 12va")
                .screenState("bueno")
                .keyboardState("bueno")
                .shellState("scratched")
                .comments("need to go to maintainance")
                .build();
        Computer computerSaved = computerService.create(computer);
        System.out.println("Computer Saved" + computerSaved);

        // Crear y guardar Vendor primero
        Vendor vendorApple = Vendor.builder().name("Apple").contact("011231231231").build();
        Vendor vendorSaved = vendorService.create(vendorApple); // Guardamos el Vendor

        // Crear los detalles de compra
        PurchaseDetail detail = PurchaseDetail.builder()
                .asset(assetSaved)
                .amount(5)
                .pricePerItem(1999.99)
                .build();

        // Crear la compra y asignarle los detalles
        Purchase purchase = Purchase.builder()
                .vendor(vendorSaved) // Usar el vendor ya guardado
                .date(LocalDate.of(2024, 12, 12))
                .totalAmount(9999.95)
                .purchaseDetails(List.of(detail))
                .build();

        // Asignar la compra a cada detalle para mantener la relación bidireccional
        detail.setPurchase(purchase);

        // Finalmente, guardar la compra
        Purchase savedPurchase = purchaseService.create(purchase);
        System.out.println("Purchase guardada: " + savedPurchase);

        // Crear usuario
        User user = User.builder()
                .employee(empSaved)
                .role(Role.EMPLOYEE)  // Suponiendo que tienes una enum Role con ADMIN, TI_ADMIN, etc.
                .username("alejandro")
                .password("password123")
                .build();
        User savedUser = userService.create(user);
        System.out.println("Usuario guardado: " + savedUser);

    }

}
