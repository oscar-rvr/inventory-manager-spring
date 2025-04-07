package com.grid.inventorymanager.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.context.annotation.Profile;

import java.time.LocalDate;
import java.util.List;
//@Profile("dev")
@Entity
@Data // Genera automáticamente los getters, setters, toString, equals, y hashCode
@Table(name = "purchases") // Especifica el nombre de la tabla en la base de datos
@NoArgsConstructor // Genera un constructor vacío
@AllArgsConstructor // Genera un constructor con todos los parámetros
public class Purchase {

    @Id // Define esta propiedad como la clave primaria
    @GeneratedValue(strategy = GenerationType.IDENTITY) // La base de datos autogenerará el valor del ID
    private Integer id;

    // Relación de muchos a uno con la clase 'Vendor'
    @ManyToOne // Indica que cada compra está asociada a un proveedor
    @JoinColumn(name = "vendor_id", nullable = false) // Establece la columna 'vendor_id' como clave foránea
    private Vendor vendor; // Propiedad que almacena la relación con 'Vendor'

    private LocalDate date; // Fecha de la compra
    private Double totalAmount; // Monto total de la compra

    // Relación de uno a muchos con la clase 'PurchaseDetail'
    @OneToMany(mappedBy = "purchase", cascade = CascadeType.ALL, orphanRemoval = true)
    // 'mappedBy = "purchase"' indica que la relación se mapea por la propiedad 'purchase' en 'PurchaseDetail'
    // 'cascade = CascadeType.ALL' asegura que las operaciones sobre la compra (insertar, actualizar, borrar)
    // se realicen automáticamente en los detalles de compra relacionados.
    // 'orphanRemoval = true' elimina los detalles de compra huérfanos cuando se eliminan de la lista.
    private List<PurchaseDetail> purchaseDetails;
}
