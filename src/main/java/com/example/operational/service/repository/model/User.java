package com.example.operational.service.repository.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;


@Getter
@Setter
@ToString
@Entity
@Table(name = "user_login")
public class User {
    @Id
    @SequenceGenerator(name="pk_sequence_1", sequenceName="entity_id_seq_1", allocationSize=1)
    @GeneratedValue(strategy = GenerationType.AUTO,generator="pk_sequence_1")
    private Long id;

    private String userName;

    private String password;

    private String role;
}
