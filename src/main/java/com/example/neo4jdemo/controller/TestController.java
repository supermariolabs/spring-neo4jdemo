package com.example.neo4jdemo.controller;

import com.example.neo4jdemo.model.Document;
import com.example.neo4jdemo.model.Investigation;
import com.example.neo4jdemo.model.Involvement;
import com.example.neo4jdemo.model.Person;
import com.example.neo4jdemo.repo.DocumentRepository;
import com.example.neo4jdemo.repo.InvestigationRepository;
import com.example.neo4jdemo.repo.PersonRepository;
import com.google.gson.Gson;
import org.neo4j.driver.Record;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.neo4j.core.Neo4jClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/api")
public class TestController {

    @Autowired
    PersonRepository pr;
    @Autowired
    DocumentRepository dr;
    @Autowired
    InvestigationRepository ir;

    @Autowired
    Neo4jClient neo;

    @GetMapping("/test")
    public String test() {
        Investigation myCase = new Investigation();
        myCase.setId(UUID.randomUUID().toString());
        myCase.setDescription("My first case");
        ir.save(myCase);

        Person p1 = new Person();
        p1.setId("p001");
        p1.setFirstName("Mario");
        p1.setLastName("Cartia");
        p1.setEmail("mario.cartia@gmail.com");
        Involvement i1 = new Involvement();
        i1.setInvestigation(myCase);
        i1.setType("tipo1");
        p1.setInvolvement(i1);
        pr.save(p1);

        Person p2 = new Person();
        p2.setId("p002");
        p2.setFirstName("Pippo");
        p2.setLastName("Baudo");
        p2.setEmail("p.baudo@gmail.com");
        Involvement i2 = new Involvement();
        i2.setInvestigation(myCase);
        i2.setType("tipo1");
        p2.setInvolvement(i2);
        pr.save(p2);

        Document d = new Document();
        d.setId("d001");
        d.setDescription("very important document");
        d.setText("lorem ipsum dolor...");
        Involvement i3 = new Involvement();
        i3.setInvestigation(myCase);
        i3.setType("tipo2");
        d.setInvolvement(i3);
        dr.save(d);

        return "OK";
    }

    @PostMapping("/query")
    public String query(@RequestBody Map<String,String> body) {
        String query = body.get("cypher");
        Gson gson = new Gson();
        List<Record> res = neo.getQueryRunner().run(query).list();
        System.out.println("Cypher query: '"+query.toUpperCase()+"'");
        System.out.println("----------");
        for(Record row : res) {
            for (String key : row.keys()) {
                System.out.println("\t"+key+" -> "+gson.toJson(row.get(key)));
            }
        }
        System.out.println("----------");
        return gson.toJson(res);
    }

    @GetMapping("/peopleInvolved")
    public List<Person> involved(@RequestParam String investigationId) {
        return pr.involvedIn(investigationId);
    }

}
