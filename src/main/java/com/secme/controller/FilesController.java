package com.secme.controller;

import com.secme.api.FilesApi;
import com.secme.dto.FileDto;
import com.secme.dto.FileResponseDto;
import com.secme.model.File;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/api/files/")
public class FilesController {

    private FilesApi filesApi;

    @Autowired
    public FilesController(FilesApi filesApi) {
        this.filesApi = filesApi;
    }

    //endpoint to create a file
    @PostMapping("add")
    @PreAuthorize("hasAuthority('create:file')")
    public File addFile(@RequestBody FileDto fileDto){
        return filesApi.saveFile(fileDto);
    }

    @PostMapping("addFile")
    @PreAuthorize("hasAuthority('create:file-binary')")
    public File addFileAsBinary(@RequestParam("file") MultipartFile multipartFile, @RequestParam("username") String username, @RequestParam("dateTime") String dateTime, @RequestParam("data") String data) throws IOException {
        return filesApi.saveFileAsBinary(multipartFile, username, dateTime, data);
    }

    //endpoint to update a file
    @PostMapping("update")
    @PreAuthorize("hasAuthority('update:file')")
    public File updateFile(@RequestBody FileDto fileDto){
        return filesApi.updateFile(fileDto);
    }

    //endpoint to update the content of a file
    @PostMapping("updateContent")
    @PreAuthorize("hasAuthority('update:file-content')")
    public FileResponseDto updateFileContent(String id, String newContent){
        return filesApi.updateFileContent(id, newContent);
    }

    //endpoint to delete a file
    @DeleteMapping("{id}")
    @PreAuthorize("hasAuthority('delete:file')")
    public FileResponseDto deleteFile(@PathVariable String id){
        return filesApi.deleteFile(id);
    }

    //endpoint to get a file by id
    @GetMapping("getById/{id}")
    @PreAuthorize("hasAuthority('read:file')")
    public File getFileById(@PathVariable String id){

//        File file = filesApi.getFileById(id);
//        file.getFileData().getData();

        return filesApi.getFileById(id);
    }

    //endpoint to get all files
    @GetMapping("getAll")
    @PreAuthorize("hasAuthority('read:files')")
    public List<File> getAllFiles(){
        return filesApi.getAllFiles();
    }

    //endpoint to get all files by username
    @GetMapping("getByUsername/{username}")
    @PreAuthorize("hasAuthority('read:files-username')")
    public List<File> getAllFilesByUsername(@PathVariable String username){
        return filesApi.getFilesByUsername(username);
    }
}
