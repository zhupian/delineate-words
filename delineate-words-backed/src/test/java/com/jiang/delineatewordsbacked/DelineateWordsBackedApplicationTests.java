package com.jiang.delineatewordsbacked;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class DelineateWordsBackedApplicationTests {

    @Autowired
    private ApplicationContext applicationContext;

    @Test
    void contextLoads() {
    }

    @Test
    void shouldRegisterOpenAiStreamingChatModelBean() {
        assertTrue(
                applicationContext.containsBean("openAiStreamingChatModel"),
                "缺少 openAiStreamingChatModel Bean，Flux<String> 的 AiService 无法正常工作"
        );
    }

}
