package com.codex.mystore.models.guest;

import com.codex.mystore.models.project.Project;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Collection;

@Data
@Entity
@NoArgsConstructor
public class Guest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String guestName;
    private String guestEmail;
    private String guestPhoneNumber;
    private String createAt;
    private String updateAt;

    @OneToMany
    @JoinTable(
            name = "guest_project",
            joinColumns = @JoinColumn(
                    name = "guest_id", referencedColumnName = "id"
            ),
            inverseJoinColumns = @JoinColumn(
                    name = "project_id", referencedColumnName = "id"
            )
    )
    private Collection<Project> projects;
}
