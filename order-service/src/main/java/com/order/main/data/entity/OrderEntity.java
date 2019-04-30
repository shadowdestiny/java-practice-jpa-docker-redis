package com.order.main.data.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

/**
 * {@code OrderEntity} represents a row in the <code>Orders</code> database table.
 * <p/>
 *
 * @author Luis Marin
 * @since 3/8/17
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "orders", schema = "public")
public class OrderEntity implements Serializable {

    @Id
    @Column(name = "id")
    @SequenceGenerator(name="orders_id_seq",
            sequenceName="public.orders_id_seq",
            allocationSize=1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator="orders_id_seq")
    private Integer id;

    @Column(name = "currency", nullable = true)
    private String currency;

    @Column(name = "total", nullable = true, insertable = false)
    private Float total = 0.0f;

}
