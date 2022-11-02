package com.secme.repository;

import com.secme.model.Message;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MessagesMongoRepository extends MongoRepository<Message, String> {

    //find messages by the users who posted them
    List<Message> findByUsername(String id);
}
