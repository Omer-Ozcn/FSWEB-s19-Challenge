package com.workintech.twitter_api.controller;

import com.workintech.twitter_api.dto.comment.CommentCreateRequest;
import com.workintech.twitter_api.dto.comment.CommentResponse;
import com.workintech.twitter_api.dto.comment.CommentUpdateRequest;
import com.workintech.twitter_api.entity.Comment;
import com.workintech.twitter_api.mapper.CommentMapper;
import com.workintech.twitter_api.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/comment")
public class CommentController {

    private final CommentService commentService;
    private final CommentMapper commentMapper;

    @PostMapping
    public ResponseEntity<CommentResponse> create(@Valid @RequestBody CommentCreateRequest req) {
        var c = commentService.create(req.tweetId(), req.userId(), req.content());
        return ResponseEntity.ok(commentMapper.toResponse(c));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CommentResponse> update(@PathVariable Long id, @Valid @RequestBody CommentUpdateRequest req) {
        var updated = commentService.update(id, req.requesterId(), req.content());
        return ResponseEntity.ok(commentMapper.toResponse(updated));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id, @RequestParam Long requesterId) {
        commentService.delete(id, requesterId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/byTweet")
    public ResponseEntity<List<CommentResponse>> listByTweet(@RequestParam Long tweetId) {
        var list = commentService.listByTweet(tweetId);
        return ResponseEntity.ok(commentMapper.toList(list));
    }
}
