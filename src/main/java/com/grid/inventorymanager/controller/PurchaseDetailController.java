package com.grid.inventorymanager.controller;

import com.grid.inventorymanager.model.PurchaseDetail;
import com.grid.inventorymanager.service.PurchaseDetailService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class PurchaseDetailController {

    private final PurchaseDetailService purchaseDetailService;

    @GetMapping("/purchaseDetails")
    public String showPurchaseDetails(Model model) {
        List<PurchaseDetail> purchaseDetails = purchaseDetailService.findAll();
        model.addAttribute("purchaseDetails", purchaseDetails);
        return "purchaseDetail";
    }
}
