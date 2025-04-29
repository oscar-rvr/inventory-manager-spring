package com.grid.inventorymanager.controller;

import com.grid.inventorymanager.model.AssetMovements;
import com.grid.inventorymanager.service.AssetMovementsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class AssetMovementsController {
    private final AssetMovementsService assetMovementsService;

    @GetMapping("/assetMovements")
    public String showAssetMovements(Model model){
        List<AssetMovements> assetMovements = assetMovementsService.findAll();
        model.addAttribute("assetMovements",assetMovements);

        return "assetMovements";
    }
}
