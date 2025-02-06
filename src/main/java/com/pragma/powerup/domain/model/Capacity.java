package com.pragma.powerup.domain.model;

import java.util.List;

public class Capacity {

    private Long id;
    private String name;
    private String description;
    private List<Long> technologies;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Long> getTechnologies() {
        return technologies;
    }

    public void setTechnologies(List<Long> technologies) {
        this.technologies = technologies;
    }
}
