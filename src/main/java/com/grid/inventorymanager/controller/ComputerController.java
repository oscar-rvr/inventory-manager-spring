package com.grid.inventorymanager.controller;

import com.grid.inventorymanager.model.Computer;
import com.grid.inventorymanager.service.ComputerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class ComputerController {
    private final ComputerService computerService;

    @GetMapping("/computers")
    public String findAll(Model model) {
        List<Computer> computers = computerService.findAll();
        model.addAttribute("computers", computers);
        return "computers";
    }
}
