/**
 * @Author: H.G. Malwatta - IT19240848
 * @Description: This interface is used to define the methods for the FileMongoRepository
 * @version: 1.0
 */
package com.secme.repository;

import com.secme.model.File;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FilesMongoRepository extends MongoRepository<File, String> {

        //find files by the users who posted them
        List<File> findByUsername(String id);
}
