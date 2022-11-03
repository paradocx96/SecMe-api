package com.secme.api;

/*
 * IT19014128 - A.M.W.W.R.L. Wataketiya
 *
 * The API class for Messages
 * Performs processing of data from controllers
 * */

import com.secme.adapter.MessagesAdapter;
import com.secme.converters.MessageDtoConverter;
import com.secme.dto.MessageDto;
import com.secme.model.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MessagesApi {

    private final MessagesAdapter messagesAdapter;

    @Autowired
    public MessagesApi(MessagesAdapter messagesAdapter) {
        this.messagesAdapter = messagesAdapter;
    }

    //method to create the message
    public MessageDto createMessage(MessageDto messageDto){
        Message message = MessageDtoConverter.convertDtoToModelWithNoId(messageDto);
        Message createdMessage = messagesAdapter.save(message);
        return MessageDtoConverter.convertModelToDtoWithId(createdMessage);
    }

    //get all the messages in the system
    public List<MessageDto> getAllMessages(){
        List<Message> messages = messagesAdapter.getAll();
        List<MessageDto> messageDtos  = new ArrayList<>();
        for (Message message :  messages){
            MessageDto messageDto = MessageDtoConverter.convertModelToDtoWithId(message);
            messageDtos.add(messageDto);
        }
        return messageDtos;
    }

    //get a message by id
    public MessageDto getMessageById(String id){
        Message message = messagesAdapter.getById(id);
        //if the message is null return null
        if (message == null){
            return null;
        }
        return MessageDtoConverter.convertModelToDtoWithId(messagesAdapter.getById(id));
    }

    //get all messages by creator's username
    public List<MessageDto> getMessagesByUsername(String username){
        List<Message> messages = messagesAdapter.getByUsername(username);
        List<MessageDto> messageDtos  = new ArrayList<>();
        for (Message message :  messages){
            MessageDto messageDto = MessageDtoConverter.convertModelToDtoWithId(message);
            messageDtos.add(messageDto);
        }
        return messageDtos;
    }

    //delete a message by id
    public String deleteMessage(String id){
        return messagesAdapter.delete(id);
    }
}
