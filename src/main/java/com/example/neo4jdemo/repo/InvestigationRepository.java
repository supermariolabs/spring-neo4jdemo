package com.example.neo4jdemo.repo;

import com.example.neo4jdemo.model.Investigation;
import org.springframework.data.neo4j.repository.Neo4jRepository;

public interface InvestigationRepository extends Neo4jRepository<Investigation, String> {
}
