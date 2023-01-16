package com.codex.mystore.models.role;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "privilege")
@Data
public class Privilege {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
}