package com.codex.mystore.models.team;


import com.codex.mystore.models.role.Privilege;
import lombok.Data;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Data
public class TeamRole {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "name")
    private String name;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "role_permission",
            joinColumns = @JoinColumn(
                    name = "role_id", referencedColumnName = "id"
            ),
            inverseJoinColumns = @JoinColumn(
                    name = "permission_id", referencedColumnName = "id"
            )
    )
    private Collection<TeamPermission> permissions = new java.util.ArrayList<>();
}
