package com.example.operational.service.repository.model;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;

@Getter
@Setter
@ToString
@Entity
@Table(name = "order_details")
public class OrderDetails {
    @Id
    @SequenceGenerator(name="pk_sequence", sequenceName="entity_id_seq", allocationSize=1)
    @GeneratedValue(strategy = GenerationType.AUTO,generator="pk_sequence")
    @Column(name = "order_id")
    private Long id;

    private String customerId;

    private String item_name;

    private String quantity;

    private String totalCost;

    private String status;

    @CreationTimestamp
    private Timestamp createdDatetime;

}
