package com.regular.chat

import org.springframework.ai.chat.client.advisor.MessageChatMemoryAdvisor
import org.springframework.ai.chat.client.advisor.SimpleLoggerAdvisor
import org.springframework.ai.chat.memory.ChatMemory
import org.springframework.ai.chat.memory.MessageWindowChatMemory
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class ChatConfig {

    @Bean
    fun simpleLoggerAdvisor(): SimpleLoggerAdvisor {
        return SimpleLoggerAdvisor.builder().build()
    }

    @Bean
    fun chatMemory(): ChatMemory {
        return MessageWindowChatMemory.builder().maxMessages(10).build();
    }

    @Bean
    fun messageChatMemoryAdvisor(chatMemory: ChatMemory): MessageChatMemoryAdvisor {
        return MessageChatMemoryAdvisor.builder(chatMemory).build();
    }
}