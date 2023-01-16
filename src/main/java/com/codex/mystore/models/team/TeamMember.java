package com.codex.mystore.models.team;

import com.codex.mystore.models.user.User;
import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;

@Data
@Entity
public class TeamMember {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @OneToOne()
    private User user;

//    @Column(name = "permission")
//    Collection<Integer> permissionRoles;

}