package com.secme.converters;

import com.secme.dto.MessageDto;
import com.secme.model.Message;

/*
* IT19014128 - A.M.W.W.R.L. Wataketiya
*
* The DTO converter for Messages
* Converts between DTOs and models of messages
* */

public class MessageDtoConverter {

    //convert a message model with an ID to a message DTO
    public static MessageDto convertModelToDtoWithId(Message message){
        //instantiate and populate message DTO using message model
        MessageDto messageDto = new MessageDto();
        messageDto.setId(message.getId());
        messageDto.setContent(message.getContent());
        messageDto.setUsername(message.getUsername());

        return messageDto;
    }

    //convert a message model without an ID to a message DTO
    public static MessageDto convertModelToDtoWithNoId(Message message){
        //instantiate and populate message DTO using message model
        MessageDto messageDto = new MessageDto();
        messageDto.setContent(message.getContent());
        messageDto.setUsername(message.getUsername());

        return messageDto;
    }

    //convert a message DTO with an ID to a message model
    public static Message convertDtoToModelWithId(MessageDto messageDto){

        //instantiate and populate message model using message DTO
        Message message = new Message();
        message.setId(messageDto.getId());
        message.setContent(messageDto.getContent());
        message.setUsername(messageDto.getUsername());

        return message;
    }

    //convert a message DTO without an ID to a message model
    public static Message convertDtoToModelWithNoId(MessageDto messageDto){

        //instantiate and populate message model using message DTO
        Message message = new Message();
        message.setContent(messageDto.getContent());
        message.setUsername(messageDto.getUsername());

        return message;
    }

}
