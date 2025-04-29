package com.grid.inventorymanager.service;

import com.grid.inventorymanager.model.Asset;
import com.grid.inventorymanager.model.Purchase;
import com.grid.inventorymanager.repository.PurchaseRepository;
import jakarta.persistence.criteria.CriteriaBuilder;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PurchaseService {
    private final PurchaseRepository purchaseRepository;

    public Purchase create(Purchase purchase){
        return purchaseRepository.save(purchase);
    }

    public Optional<Purchase> findById(Integer id){
        return purchaseRepository.findById(id);
    }

    public List<Purchase> findAll(){
        return purchaseRepository.findAll();
    }

    public Purchase update(Purchase purchase){
        return purchaseRepository.save(purchase);
    }

    public void deletedById(Integer id){
        purchaseRepository.deleteById(id);
    }

}
