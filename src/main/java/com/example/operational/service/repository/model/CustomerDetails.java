package com.example.operational.service.repository.model;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Getter
@Setter
@ToString
@Entity
@Table(name = "customer_details")
public class CustomerDetails {
    @Id
    @SequenceGenerator(name="pk_sequence_1", sequenceName="entity_id_seq_1", allocationSize=1)
    @GeneratedValue(strategy = GenerationType.AUTO,generator="pk_sequence_1")
    @Column(name = "customer_id")
    private Long id;

    private String firstName;

    private String lastName;

    private String identityNumber;

    private String district;

    private String city;

    private String street1;

    private String street2;

    private String houseNo;

    @OneToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User userId;


}
