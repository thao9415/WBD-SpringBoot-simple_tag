package com.example.demo1111.repository;

import com.example.demo1111.model.Tag;
import org.springframework.data.repository.CrudRepository;

public interface ITagRepository extends CrudRepository<Tag, Long> {
    Iterable<Tag> findTagsByName(String tag_name);
}
