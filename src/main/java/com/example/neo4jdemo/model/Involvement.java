package com.example.neo4jdemo.model;

import org.springframework.data.neo4j.core.schema.*;

@RelationshipProperties
public class Involvement {
    @Id @GeneratedValue private Long relationshipId;
    @Property String type;
    @TargetNode Investigation investigation;

    public Long getRelationshipId() {
        return relationshipId;
    }

    public void setRelationshipId(Long relationshipId) {
        this.relationshipId = relationshipId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Investigation getInvestigation() {
        return investigation;
    }

    public void setInvestigation(Investigation investigation) {
        this.investigation = investigation;
    }
}
