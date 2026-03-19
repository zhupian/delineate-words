package com.jiang.delineatewordsbacked.config;

import dev.langchain4j.memory.ChatMemory;
import dev.langchain4j.memory.chat.ChatMemoryProvider;
import dev.langchain4j.memory.chat.MessageWindowChatMemory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Configuration
public class CommonConfig {

    // 按 memoryId 维度缓存记忆，避免不同会话串话
    @Bean
    public ChatMemoryProvider chatMemoryProvider() {
        Map<Object, ChatMemory> memories = new ConcurrentHashMap<>();
        return memoryId -> memories.computeIfAbsent(memoryId, key -> MessageWindowChatMemory.builder()
                .maxMessages(20)
                .build());
    }

}
