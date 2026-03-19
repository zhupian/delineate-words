package com.jiang.delineatewordsbacked.service;

import dev.langchain4j.service.MemoryId;
import dev.langchain4j.service.SystemMessage;
import dev.langchain4j.service.UserMessage;
import dev.langchain4j.service.spring.AiService;
import dev.langchain4j.service.spring.AiServiceWiringMode;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;


@AiService(
        //表示我要手动装配
        wiringMode = AiServiceWiringMode.EXPLICIT,
        //指定模型
        chatModel = "openAiChatModel",
        streamingChatModel = "openAiStreamingChatModel",
        chatMemoryProvider = "chatMemoryProvider"//配置会话记忆对象
)
@Service
public interface ConsultantService {

    /**
     * 流式聊天方法，带会话记忆
     * @param memoryId 会话ID，用于区分不同用户的对话
     * @param message 用户消息
     * @return 流式返回的AI响应
     */
    @SystemMessage(fromResource = "system.txt")
    Flux<String> chatStream(
            @MemoryId String memoryId,
            @UserMessage String message
    );

    /**
     * 翻译方法，无会话记忆
     * @param message 需要翻译的消息
     * @return 流式返回的翻译结果
     */
    @SystemMessage(fromResource = "translation.txt")
    Flux<String> translation(@UserMessage String message);
}
