package com.miu.propertymanagement.service;

import com.miu.propertymanagement.domain.dto.MessageRequestDTO;
import com.miu.propertymanagement.domain.dto.MessageResponseDTO;

import java.util.List;

public interface MessageService {
    void sendMessage(MessageRequestDTO messageRequestDTO);

    List<MessageResponseDTO> getAllMessagesByBetweenUsers(Integer fromId, Integer toId);
}
