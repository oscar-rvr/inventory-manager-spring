package com.grid.inventorymanager.controller;

import com.grid.inventorymanager.model.Purchase;
import com.grid.inventorymanager.service.PurchaseService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@AllArgsConstructor
@Controller
public class PurchaseController {
    private final PurchaseService purchaseService;

    @GetMapping("/purchases")
    public String findAll(Model model) {
        List<Purchase> purchases = purchaseService.findAll();
        model.addAttribute("purchases", purchases);
        return "purchases";
    }
}
