package com.order.main.data.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

/**
 * {@code OrderProductEntity} represents a row in the <code>OrderProducts</code> database table.
 * <p/>
 *
 * @author Luis Marin
 * @since 3/8/17
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "order_product", schema = "public")
public class OrderProductEntity implements Serializable {

    @Id
    @Column(name = "id")
    @SequenceGenerator(name="order_product_id_seq",
            sequenceName="public.order_product_id_seq",
            allocationSize=1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator="order_product_id_seq")
    private Integer id;

    @Column(name = "order_id", nullable = false)
    private Integer orderId;

    /*@Column(name = "product_id", nullable = false)
    private Integer productId;*/

    @ManyToOne
    @JoinColumn
    private ProductEntity product;

}
