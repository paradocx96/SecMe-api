/**
 * @Author: H.G. Malwatta - IT19240848
 * @Description: This is the API class for the all the file related operations
 * @Version: 1.0.0
 *
 */

package com.secme.api;

import com.secme.adapter.FilesAdapter;
import com.secme.dto.FileDto;
import com.secme.dto.FileResponseDto;
import com.secme.model.File;
import org.bson.BsonBinarySubType;
import org.bson.types.Binary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class FilesApi {

    private final FilesAdapter filesAdapter;

    @Autowired
    public FilesApi(FilesAdapter filesAdapter) {
        this.filesAdapter = filesAdapter;
    }


    //method to save a file
    public File saveFile(FileDto fileDto){
        File file = new File();
        file.setName(fileDto.getName());
        file.setUsername(fileDto.getUsername());
        file.setType(fileDto.getType());
        file.setContent(fileDto.getContent());
        file.setFileSize(fileDto.getFileSize());
        file.setDateTime(fileDto.getDateTime());
        return filesAdapter.save(file);
    }

    //method to save a file as binary
    public File saveFileAsBinary(MultipartFile multipartFile, String username, String dateTime, String data) throws IOException {
        File file = new File();
        file.setName(multipartFile.getOriginalFilename());
        file.setUsername(username);
        file.setDateTime(dateTime);
        file.setType(multipartFile.getContentType());
        file.setFileData(new Binary(BsonBinarySubType.BINARY, data.getBytes()));

        return filesAdapter.save(file);
    }

    //method to update a file
    public File updateFile(FileDto fileDto){
        File file = new File();
        file.setId(fileDto.getId());
        file.setName(fileDto.getName());
        file.setUsername(fileDto.getUsername());
        file.setType(fileDto.getType());
        file.setContent(fileDto.getContent());
        return filesAdapter.update(file);
    }

    //method to update the content of a file
    public FileResponseDto updateFileContent(String id, String newContent){
        return filesAdapter.updateContent(id, newContent);
    }

    //method to delete a file
    public FileResponseDto deleteFile(String id){
        return filesAdapter.delete(id);
    }

    //method to get a file by id
    public File getFileById(String id){
        return filesAdapter.getById(id);
    }

    //method to get all files
    public List<File> getAllFiles(){
        return filesAdapter.getAllFiles();
    }

    //method to get all files by username
    public List<File> getFilesByUsername(String username){
        return filesAdapter.getByUsername(username);
    }
}
