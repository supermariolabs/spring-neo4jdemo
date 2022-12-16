package com.example.neo4jdemo.repo;

import com.example.neo4jdemo.model.Document;
import org.springframework.data.neo4j.repository.Neo4jRepository;

public interface DocumentRepository extends Neo4jRepository<Document, String> {
}
