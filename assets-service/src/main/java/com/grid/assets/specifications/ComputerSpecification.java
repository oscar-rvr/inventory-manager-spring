package com.grid.assets.specifications;

import com.grid.inventorymanager.model.Computer;
import org.springframework.data.jpa.domain.Specification;

public class ComputerSpecification {

    public static Specification<Computer> hasName(String name) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.equal(root.get("name"), name);
    }

    public static Specification<Computer> hasSeriesNumber(String seriesNumber) {
        return (root, query, cb) -> cb.like(root.get("seriesNumber"), "%" + seriesNumber + "%");
    }

    public static Specification<Computer> hasDescription(String description) {
        return (root, query, cb) -> cb.like(root.get("description"), "%" + description + "%");
    }

    public static Specification<Computer> hasRam(int ram) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.equal(root.get("ram"), ram);
    }

    public static Specification<Computer> hasCore(String core) {
        return (root, query, cb) -> cb.like(root.get("core"), "%" + core + "%");
    }

    public static Specification<Computer> hasDisk(int disk) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.equal(root.get("disk"), disk);
    }

}
