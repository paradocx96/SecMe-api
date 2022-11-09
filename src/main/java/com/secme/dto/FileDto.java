/**
 * @Author: H.G. Malwatta - IT19240848
 * @Description: This class is used to store the file details temporarily
 * @version: 1.0
 */
package com.secme.dto;


import org.bson.types.Binary;

public class FileDto {

    private String id;
    private String name;
    private String username;
    private String type;
    private String content;
    private Binary fileData;
    private String dateTime;
    private String fileSize;

    public FileDto() {
    }

    public FileDto(String name, String username, String type, String content, String fileSize) {
        this.name = name;
        this.username = username;
        this.type = type;
        this.content = content;
        this.fileSize = fileSize;
    }

    public FileDto(String name, String username, String type, Binary fileData) {
        this.name = name;
        this.username = username;
        this.type = type;
        this.fileData = fileData;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Binary getFileData() {
        return fileData;
    }
    public void setFileData(Binary fileData) {
        this.fileData = fileData;
    }
    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public String getFileSize() {
        return fileSize;
    }

    public void setFileSize(String fileSize) {
        this.fileSize = fileSize;
    }


    @Override
    public String toString() {
        return "FileDto{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", username='" + username + '\'' +
                ", type='" + type + '\'' +
                ", content='" + content + '\'' +
                '}';
    }
}
