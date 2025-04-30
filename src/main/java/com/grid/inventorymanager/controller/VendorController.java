package com.grid.inventorymanager.controller;

import com.grid.inventorymanager.model.Vendor;
import com.grid.inventorymanager.service.VendorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class VendorController {
    private final VendorService vendorService;

    @GetMapping("/vendors")
    public String showVendors(Model model) {
        List<Vendor> vendors = vendorService.findAll();
        model.addAttribute("vendors", vendors);

        return "vendors";
    }
}
