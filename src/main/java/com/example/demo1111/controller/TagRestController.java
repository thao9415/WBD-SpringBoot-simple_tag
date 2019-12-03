package com.example.demo1111.controller;

import com.example.demo1111.message.request.SearchTagsByName;
import com.example.demo1111.model.Tag;
import com.example.demo1111.service.ITagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/auth")
public class TagRestController {
    @Autowired
    ITagService tagService;

    @GetMapping("/tag")
    public ResponseEntity<?> getAllTag() {
        List<Tag> tagList = (List<Tag>) tagService.findAll();
        if (tagList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(tagList, HttpStatus.OK);
    }


    @GetMapping("/tag/{id}")
    public ResponseEntity<?> getTagById(@PathVariable Long id) {
        Optional<Tag> tag = tagService.findById(id);
        if (tag.isPresent()) {
            return new ResponseEntity<>(tag, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/tag")
    public ResponseEntity<?> createTag(@Valid @RequestBody Tag tag) {
        tagService.save(tag);
        return new ResponseEntity<>(tag, HttpStatus.CREATED);
    }

    @PutMapping("/tag/{id}")
    public ResponseEntity<?> updateTag(@Valid @RequestBody Tag tag, @PathVariable Long id) {
        Optional<Tag> tag1 = tagService.findById(id);
        if (tag1.isPresent()) {
            tag1.get().setName(tag.getName());
            tagService.save(tag1.get());
            return new ResponseEntity<>(tag1, HttpStatus.OK);

        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/tag/{id}")
    public ResponseEntity<?> deleteTag(@PathVariable Long id) {
        Optional<Tag> tag = tagService.findById(id);
        if (tag.isPresent()) {
            tagService.delete(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }


    @PostMapping("/tag/search-by-name")
    public ResponseEntity<?> searchTagsByName(@RequestBody SearchTagsByName tag_form) {
        if (tag_form.getName().equals("") || tag_form.getName() == null) {
            List<Tag> tags = (List<Tag>) tagService.findAll();
            if (tags.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(tags, HttpStatus.OK);
        }
        List<Tag> tags = (List<Tag>) tagService.findTagsByName(tag_form.getName());
        if (tags.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(tags, HttpStatus.OK);
    }

}
