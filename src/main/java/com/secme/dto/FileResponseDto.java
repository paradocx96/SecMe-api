/**
 * @Author: H.G. Malwatta - IT19240848
 * @Description: This class is used to store the file related responses remporarily
 * @Version: 1.0.0
 *
 */
package com.secme.dto;

public class FileResponseDto {
    String message;
    String status;

    public FileResponseDto() {
    }

    public FileResponseDto(String message, String status) {
        this.message = message;
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
