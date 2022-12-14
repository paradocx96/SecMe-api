package com.secme.controller;

import com.secme.api.MessagesApi;
import com.secme.dto.MessageDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/*
* IT19014128 - A.M.W.W.R.L. Wataketiya
*
* Controller for messages
* */

@RestController
@RequestMapping("/api/messages")
@CrossOrigin(origins = "https://localhost:3000", allowedHeaders = "*", exposedHeaders = "*")
public class MessagesController {

    private final MessagesApi messagesApi;

    @Autowired
    public MessagesController(MessagesApi messagesApi) {
        this.messagesApi = messagesApi;
    }

    //endpoint to get all messages
    @GetMapping
    @PreAuthorize("hasAuthority('read:messages')")
    public List<MessageDto> getAllMessages(){
        return messagesApi.getAllMessages();
    }

    //endpoint to get a message by id
    @GetMapping("{id}")
    @PreAuthorize("hasAuthority('read:message')")
    public ResponseEntity<?> getMessageById(@PathVariable String id){
        MessageDto messageDto = messagesApi.getMessageById(id);
        //if no message is found for the given id
        //return bad request
        if (messageDto == null){
            return new ResponseEntity<>("ID not found", HttpStatus.BAD_REQUEST);
        }

        //return the message for the id
        return new ResponseEntity<>(messagesApi.getMessageById(id), HttpStatus.OK);
    }

    //endpoint to get all messages by a username
    @GetMapping("getByUsername/{username}")
    @PreAuthorize("hasAuthority('read:messages-username')")
    public List<MessageDto> getMessagesByUsername(@PathVariable String username){
        return messagesApi.getMessagesByUsername(username);
    }

    //endpoint to create a message
    @PostMapping
    @PreAuthorize("hasAuthority('create:message')")
    public MessageDto createMessage(@RequestBody MessageDto messageDto){
        return messagesApi.createMessage(messageDto);
    }

    //endpoint to delete a message
    @DeleteMapping("{id}")
    @PreAuthorize("hasAuthority('delete:message')")
    public ResponseEntity<?> deleteMessage(@PathVariable String id){
        MessageDto messageDto = messagesApi.getMessageById(id);
        //if no message is found for the given id
        //return bad request
        if (messageDto == null){
            return new ResponseEntity<>("ID not found", HttpStatus.BAD_REQUEST);
        }
        //deletion completed. Return no content
        return  new ResponseEntity<>(messagesApi.deleteMessage(id), HttpStatus.NO_CONTENT);
    }


}
