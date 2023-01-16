package com.codex.mystore.models.team;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Collection;

@Data
@Entity
@NoArgsConstructor
public class Team {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "team_name")
    private String teamName;

    @Column(name = "create_at")
    private String createAt;
    @Column(name = "update_at")
    private String updateAt;

    @OneToMany()
    @JoinTable(name = "team_member_list")
    private Collection<TeamMember> teamMembers;
}
