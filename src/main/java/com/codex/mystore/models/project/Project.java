package com.codex.mystore.models.project;

import com.codex.mystore.models.documents.Documents;
import com.codex.mystore.models.tasks.Task;
import com.codex.mystore.models.team.Team;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Collection;

@Data
@Entity
@NoArgsConstructor
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "project_name", nullable = false)
    private String projectName;

    @Column(name = "start_date", nullable = false)
    private String startDate;
    @Column(name = "end_date", nullable = false)
    private String endDate;

    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "create-at")
    private String createAt;
    @Column(name = "update_at")
    private String updateAt;

    @OneToOne(optional = true)
    private Team team;

    @OneToMany
    private Collection<Documents> documentList;

    @OneToMany
    @JoinTable(name = "project_task_list")
    private Collection<Task> taskList;
}
