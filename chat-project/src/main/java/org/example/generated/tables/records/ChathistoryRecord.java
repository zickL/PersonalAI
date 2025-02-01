/*
 * This file is generated by jOOQ.
 */
package org.example.generated.tables.records;


import java.time.LocalDateTime;

import org.example.generated.tables.Chathistory;
import org.jooq.impl.TableRecordImpl;


/**
 * The table <code>public.chathistory</code>.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes", "this-escape" })
public class ChathistoryRecord extends TableRecordImpl<ChathistoryRecord> {

    private static final long serialVersionUID = 1L;

    /**
     * Setter for <code>public.chathistory.id</code>.
     */
    public void setId(Integer value) {
        set(0, value);
    }

    /**
     * Getter for <code>public.chathistory.id</code>.
     */
    public Integer getId() {
        return (Integer) get(0);
    }

    /**
     * Setter for <code>public.chathistory.message</code>.
     */
    public void setMessage(String value) {
        set(1, value);
    }

    /**
     * Getter for <code>public.chathistory.message</code>.
     */
    public String getMessage() {
        return (String) get(1);
    }

    /**
     * Setter for <code>public.chathistory.is_user</code>.
     */
    public void setIsUser(Boolean value) {
        set(2, value);
    }

    /**
     * Getter for <code>public.chathistory.is_user</code>.
     */
    public Boolean getIsUser() {
        return (Boolean) get(2);
    }

    /**
     * Setter for <code>public.chathistory.timestamp</code>.
     */
    public void setTimestamp(LocalDateTime value) {
        set(3, value);
    }

    /**
     * Getter for <code>public.chathistory.timestamp</code>.
     */
    public LocalDateTime getTimestamp() {
        return (LocalDateTime) get(3);
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * Create a detached ChathistoryRecord
     */
    public ChathistoryRecord() {
        super(Chathistory.CHATHISTORY);
    }

    /**
     * Create a detached, initialised ChathistoryRecord
     */
    public ChathistoryRecord(Integer id, String message, Boolean isUser, LocalDateTime timestamp) {
        super(Chathistory.CHATHISTORY);

        setId(id);
        setMessage(message);
        setIsUser(isUser);
        setTimestamp(timestamp);
        resetChangedOnNotNull();
    }
}
