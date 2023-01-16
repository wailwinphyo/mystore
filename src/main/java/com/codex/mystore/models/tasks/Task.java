package com.codex.mystore.models.tasks;

import com.codex.mystore.models.comments.Comment;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Collection;

@Data
@Entity
@NoArgsConstructor
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    @Column(name = "create_at")
    private String createAt;
    @Column(name = "update_at")
    private String updateAt;

    @Column(name = "creator_id")
    private String creatorId;

    @OneToMany()
    private Collection<Comment> commentList;
}
