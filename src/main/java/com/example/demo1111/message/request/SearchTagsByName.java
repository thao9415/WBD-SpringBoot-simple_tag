package com.example.demo1111.message.request;

public class SearchTagsByName {
    private String name;

    public SearchTagsByName() {
    }

    public SearchTagsByName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
