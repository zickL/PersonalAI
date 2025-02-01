package com.example;

import io.github.stefanbratanov.jvm.openai.*;

import java.util.ArrayList;
import java.util.List;

public class OpenAI {


    public static String CallOpenAI(List<String> previousMessages, String userMessageString) {
        //System.out.println(System.getenv("OPENAI_API_KEY"));
        var key = System.getenv("OPENAI_API_KEY");
        io.github.stefanbratanov.jvm.openai.OpenAI openAI = io.github.stefanbratanov.jvm.openai.OpenAI.newBuilder(key).build();
        ChatClient chatClient = openAI.chatClient();
        var builder = CreateChatCompletionRequest.newBuilder();
        var model = builder.model("gpt-4");

        var userMessage = ChatMessage.userMessage(userMessageString);

        //var requestBuilder = model.message(userMessage);

        List<ChatMessage> messages = new ArrayList();
        for (var previousMessage : previousMessages) {
            messages.add(ChatMessage.userMessage(previousMessage));
        }
        messages.add(userMessage);


        var requestBuilder = model.messages(messages);

        CreateChatCompletionRequest createChatCompletionRequest = requestBuilder.build();

        ChatCompletion chatCompletion = chatClient.createChatCompletion(createChatCompletionRequest);
        var choices = chatCompletion.choices();

        return choices.getFirst().message().content();
    }
}

