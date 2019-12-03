package com.example.demo1111.service;

import com.example.demo1111.model.Tag;

import java.util.Optional;

public interface ITagService {
    Iterable<Tag> findAll();
    Optional<Tag> findById(Long id);
    Iterable<Tag> findTagsByName(String tag_name);
    Tag save(Tag tag);
    void delete(Long id);


}
