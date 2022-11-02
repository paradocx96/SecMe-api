package com.secme.repository;

import com.secme.model.Message;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
/*
 * IT19014128 - A.M.W.W.R.L. Wataketiya
 *
 * The Messages repository interface extending mongo repository
 * */

@Repository
public interface MessagesMongoRepository extends MongoRepository<Message, String> {

    //find messages by the users who posted them
    List<Message> findByUsername(String id);
}
