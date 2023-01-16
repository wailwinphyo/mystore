package com.codex.mystore.models.team;

import com.codex.mystore.models.role.Role;
import com.codex.mystore.models.user.User;
import lombok.Data;

import javax.persistence.*;
import java.util.Collection;

@Data
@Entity
public class TeamMember {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    private User user;

    @ManyToOne
    @JoinColumn(name = "team_id")
    private Team team;

    /*@OneToMany
    private Collection<TeamPermission> teamPermissionList;*/

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "team_member_roles",
            joinColumns = @JoinColumn(
                    name = "user_id", referencedColumnName = "id"
            ),
            inverseJoinColumns = @JoinColumn(
                    name = "role_id", referencedColumnName = "id"
            )
    )
    private Collection<TeamRole> teamRoles;
}
