package com.codex.mystore.controller.comment;

import com.codex.mystore.dao.repo.CommentRepo;
import com.codex.mystore.models.comments.Comment;
import com.codex.mystore.models.tasks.Task;
import com.codex.mystore.network.request.CommentRequest;
import com.codex.mystore.network.request.TaskRequest;
import com.codex.mystore.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/comment")
public class CommentController {
    @Autowired
    CommentRepo commentRepo;
    @Autowired
    DateUtils dateUtils;

    @PostMapping(value = "/create")
    public ResponseEntity<?> createRole(@RequestBody CommentRequest commentRequest) {
        String currentDateTime = dateUtils.currentDateAndTime();
        Comment comment = new Comment();
        comment.setDescription(commentRequest.getCommentDescription());
        comment.setUpdateAt(currentDateTime);
        comment.setCreateAt(currentDateTime);

        commentRepo.save(comment);
        return ResponseEntity.ok("Create Comment Success");
    }
}
