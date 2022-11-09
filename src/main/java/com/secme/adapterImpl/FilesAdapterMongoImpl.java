/**
 * @Author: H.G. Malwatta - IT19240848
 * @Description: This class is used to store the file details in the database
 * @version: 1.0
 */

package com.secme.adapterImpl;

import com.secme.adapter.FilesAdapter;
import com.secme.dto.FileResponseDto;
import com.secme.model.File;
import com.secme.repository.FilesMongoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class FilesAdapterMongoImpl implements FilesAdapter {
    private final FilesMongoRepository filesMongoRepository;

   @Autowired
   public FilesAdapterMongoImpl(FilesMongoRepository filesMongoRepository) {
       this.filesMongoRepository = filesMongoRepository;
   }
    @Override
    public File save(File file) {
        return filesMongoRepository.save(file);
    }

    @Override
    public File update(File file) {
        return filesMongoRepository.save(file);
    }

    @Override
    public FileResponseDto updateContent(String id, String newContent) {
        return null;
    }

    @Override
    public FileResponseDto delete(String id) {
            filesMongoRepository.deleteById(id);
            return null;
    }

    @Override
    public File getById(String id) {
        if (filesMongoRepository.findById(id).isPresent()){
            return filesMongoRepository.findById(id).get();
        }
        else
            return null;
    }

    @Override
    public List<File> getAllFiles() {
        return filesMongoRepository.findAll();
    }

    @Override
    public List<File> getByUsername(String username) {
        return filesMongoRepository.findByUsername(username);
    }
}
