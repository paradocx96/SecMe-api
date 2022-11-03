package com.secme.adapter;

import com.secme.model.Message;

import java.util.List;

/*
 * IT19014128 - A.M.W.W.R.L. Wataketiya
 *
 * The interface for MessagesAdapter
 * */

public interface MessagesAdapter {
    Message save(Message message);
    List<Message> getAll();
    Message update(Message message);
    String delete (String id);
    Message getById (String id);
    List<Message> getByUsername (String username);
    String updateContent(String id, String newContent);
}
