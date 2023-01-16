package com.codex.mystore.models.documents;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor
@Data
@Entity

public class Documents {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "file_path", nullable = false)
    private String filePath;

    @Column(name = "create_ai")
    private String createAt;
}
