package com.grid.assets.specifications;

import com.grid.inventorymanager.model.Asset;
import org.springframework.data.jpa.domain.Specification;

public class AssetSpecification {

    public static Specification<Asset> hasName(String name) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.equal(root.get("name"), name);
    }

    public static Specification<Asset> hasSeriesNumber(String seriesNumber) {
        return (root, query, cb) -> cb.like(root.get("seriesNumber"), "%" + seriesNumber + "%");
    }

    public static Specification<Asset> hasDescription(String description) {
        return (root, query, cb) -> cb.like(root.get("description"), "%" + description + "%");
    }
}
