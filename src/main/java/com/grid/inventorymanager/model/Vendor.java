package com.grid.inventorymanager.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data // Genera automáticamente los getters, setters, toString, equals, y hashCode
@Table(name = "vendors") // Especifica el nombre de la tabla en la base de datos
@NoArgsConstructor // Genera un constructor vacío
@AllArgsConstructor // Genera un constructor con todos los parámetros
public class Vendor {

    @Id // Define esta propiedad como la clave primaria
    @GeneratedValue(strategy = GenerationType.IDENTITY) // La base de datos autogenerará el valor del ID
    private Integer id;

    private String name;
    private String contact;

    // Relación de uno a muchos con la clase 'Purchase'
    @OneToMany(mappedBy = "vendor", cascade = CascadeType.ALL, orphanRemoval = true)
    // 'mappedBy = "vendor"' indica que la relación se mapea por la propiedad 'vendor' de la clase 'Purchase'
    // 'cascade = CascadeType.ALL' asegura que las operaciones sobre el proveedor (insertar, actualizar, borrar)
    // se realicen automáticamente en las compras relacionadas.
    // 'orphanRemoval = true' elimina compras huérfanas cuando se eliminan de la lista.
    private List<Purchase> purchases;
}
