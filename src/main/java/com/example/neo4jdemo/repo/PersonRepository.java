package com.example.neo4jdemo.repo;

import com.example.neo4jdemo.model.Person;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PersonRepository extends Neo4jRepository<Person, String> {

    @Query("MATCH (p: Person)-[rel:INVOLVED {type: 'tipo1'}]->(i:Investigation {id: $investigationId}) RETURN p")
    public List<Person> involvedIn(@Param("investigationId") String investigationId);

}
