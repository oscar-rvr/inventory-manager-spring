package com.grid.inventorymanager.controller;

import com.grid.inventorymanager.model.Asset;
import com.grid.inventorymanager.service.AssetService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class AssetController {
    private final AssetService assetService;

    @GetMapping("/assets")
    public String showAssets(Model model) {
        List<Asset> assets = assetService.findAll();
        model.addAttribute("assets", assets);
        return "assets";
    }
}
