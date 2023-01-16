package com.codex.mystore.models.comments;

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
    @Column(name = "comment")
    private String comment;

    @Column(name = "create_ai")
    private String createAt;
    @Column(name = "update_at")
    private String updateAt;
}
