package com.jiang.delineatewordsbacked.controller;

import com.jiang.delineatewordsbacked.service.ConsultantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

import java.util.Objects;



@RestController
public class ChatController {
    @Autowired
    private ConsultantService consultantService;

    @GetMapping(value = "/chat", produces = "text/plain;charset=UTF-8")
    public Flux<String> chat(@RequestParam String memoryId, @RequestParam String message) {
        if (!StringUtils.hasText(memoryId)) {
            return Flux.error(new IllegalArgumentException("memoryId 不能为空"));
        }
        if (!StringUtils.hasText(message)) {
            return Flux.error(new IllegalArgumentException("message 不能为空"));
        }
        //这行代码由两个方法链式调用组成，目的是获取一个去除了首尾空格的、非空的字符串。
        String safeMemoryId = Objects.requireNonNull(memoryId).trim();
        String safeMessage = Objects.requireNonNull(message).trim();
        return consultantService.chatStream(safeMemoryId, safeMessage);
    }
    @GetMapping(value = "/reset", produces = "application/json;charset=UTF-8")
    public Flux<String> translation(@RequestParam String message){
        if(!StringUtils.hasText(message)) {
            return Flux.error(new IllegalArgumentException("message 不能为空"));
        }
        String safeMessage = Objects.requireNonNull(message).trim();
        return consultantService.translation(safeMessage);
    }

}
