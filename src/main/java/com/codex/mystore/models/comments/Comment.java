package com.codex.mystore.models.comments;

import com.codex.mystore.models.team.TeamMember;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@NoArgsConstructor
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "description")
    private String description;

    @Column(name = "create-at")
    private String createAt;
    @Column(name = "update_at")
    private String updateAt;
    @OneToOne
    private TeamMember teamMember;
}
