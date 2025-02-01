package com.example;

import org.example.generated.tables.Chathistory;
import org.example.generated.tables.records.ChathistoryRecord;
import org.jooq.DSLContext;

import java.util.ArrayList;
import java.util.List;
import static org.example.generated.tables.Chathistory.CHATHISTORY;

public class ChatService {
    private List<String> previousMessages;
    private final DSLContext dsl;

    public ChatService(DSLContext dsl) {
        this.previousMessages = new ArrayList<>();
        this.dsl = dsl;
        loadChatHistory();
    }

    public String sendMessage(String userMessage) {
        saveMessageToDatabase(userMessage, true); // 保存用户消息
        String response = App.AskOpenAIConcisely(previousMessages, userMessage);
        saveMessageToDatabase(response, false);  // 保存系统响应
        return response;
    }
    private void loadChatHistory() {
        try {
            var records = dsl.selectFrom(CHATHISTORY)
                    .orderBy(Chathistory.CHATHISTORY.TIMESTAMP.asc()) // 按时间顺序加载
                    .fetch();

            for (ChathistoryRecord record : records) {
                String message = record.getMessage();
                boolean isUser = record.getIsUser();
                previousMessages.add(message);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private void saveMessageToDatabase(String message, boolean isUser) {
        try {
            // 插入新的聊天记录
            dsl.insertInto(Chathistory.CHATHISTORY)
                    .set(Chathistory.CHATHISTORY.MESSAGE, message)
                    .set(Chathistory.CHATHISTORY.IS_USER, isUser)
                    .set(Chathistory.CHATHISTORY.TIMESTAMP,java.time.LocalDateTime.now())
                    .execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public List<String> getPreviousMessages() {
        return previousMessages;
    }
}
