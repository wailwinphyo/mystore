package com.codex.mystore.Models;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Data
@NoArgsConstructor
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    /*@ManyToMany(mappedBy = "roles")
    private Collection<User> users;*/

    /*@ManyToMany
    @JoinTable(
            name = "roles_privileges",
            joinColumns = @JoinColumn(
                    name = "role_id", referencedColumnName = "id"
            ),
            inverseJoinColumns = @JoinColumn(
                    name = "privilege_id", referencedColumnName = "id"
            )
    )
    private Collection<Privilege> privileges;*/

    public Role(String name) {
        this.name = name;
    }
}
