package com.otus.edu.auth.service;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.stream.Collectors;

import org.springframework.core.io.ClassPathResource;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.query.Query;

import ru.gromov.auth.domain.Account;

public abstract class AbstractMongoTest {

    protected void init(MongoOperations operations, String collectionName) {
        String initialDataFile = "data.json";
        ClassPathResource initialResource = new ClassPathResource(
                initialDataFile);
        if (Account.class.isAnnotationPresent(Document.class)) {
            Document document = Account.class.getAnnotation(Document.class);
            if (document.collection() != null) {
                collectionName = document.collection();
            }
        }

        if (operations.count(new Query(), collectionName) == 0
                && initialResource.exists()) {
            try {
                File file = initialResource.getFile();
                String json = Files.lines(file.toPath(), StandardCharsets.UTF_8)
                        .collect(Collectors.joining("\n"));
                operations.executeCommand("{insert:\"" + collectionName
                        + "\", documents: " + json + "}");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
