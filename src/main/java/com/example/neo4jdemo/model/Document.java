package com.example.neo4jdemo.model;

import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Relationship;

@Node
public class Document {
    @Id String id;
    String description;
    String text;
    @Relationship(type = "INVOLVED")
    Involvement involvement;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Involvement getInvolvement() {
        return involvement;
    }

    public void setInvolvement(Involvement involvement) {
        this.involvement = involvement;
    }
}
