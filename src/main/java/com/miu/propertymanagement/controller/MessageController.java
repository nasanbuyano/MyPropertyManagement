package com.miu.propertymanagement.controller;

import com.miu.propertymanagement.domain.dto.MessageRequestDTO;
import com.miu.propertymanagement.service.MessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RequestMapping("/api")
@RestController
public class MessageController {
    @Autowired
    private MessageService messageService;

    @PostMapping("/sendMessage")
    ResponseEntity<?> sendMessage(@RequestBody MessageRequestDTO messageRequestDTO) {
        messageService.sendMessage(messageRequestDTO);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @GetMapping("/message/{fromId}/getAllMessagesByBetweenUsers/{toId}")
    ResponseEntity<?> getAllMessagesByBetweenUsers(@PathVariable("fromId") Integer fromId,
                                                   @PathVariable("toId") Integer toId) {
        return ResponseEntity.ok(messageService.getAllMessagesByBetweenUsers(fromId, toId));
    }
}
