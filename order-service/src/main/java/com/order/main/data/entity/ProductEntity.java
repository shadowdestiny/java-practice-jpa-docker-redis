package com.order.main.data.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

/**
 * {@code ProductEntity} represents a row in the <code>Products</code> database table.
 * <p/>
 *
 * @author Luis Marin
 * @since 3/8/17
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "products", schema = "public")
public class ProductEntity implements Serializable {

    @Id
    @Column(name = "id")
    @SequenceGenerator(name="products_id_seq",
            sequenceName="public.products_id_seq",
            allocationSize=1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator="products_id_seq")
    private Integer id;

    @Column(name = "sku", nullable = false)
    private String sku;

    @Column(name = "nombre", nullable = false)
    private String name;

    @Column(name = "precio", nullable = false)
    private Float price;

    @Column(name = "moneda", nullable = false)
    private String currency;

}
