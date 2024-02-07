package com.miu.propertymanagement.service.impl;

import com.miu.propertymanagement.domain.Message;
import com.miu.propertymanagement.domain.dto.MessageRequestDTO;
import com.miu.propertymanagement.domain.dto.MessageResponseDTO;
import com.miu.propertymanagement.integration.Mapper.ListMapper;
import com.miu.propertymanagement.repository.MessageRepository;
import com.miu.propertymanagement.repository.UserRepository;
import com.miu.propertymanagement.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Transactional
@Service
public class MessageServiceImpl implements MessageService {
    @Autowired
    ListMapper listMapper;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private MessageRepository messageRepository;

    @Override
    public void sendMessage(MessageRequestDTO messageRequestDTO) {
        Message message = new Message();

        message.setTo(userRepository.findById(messageRequestDTO.getTo()).get());
        message.setFrom(userRepository.findById(messageRequestDTO.getFrom()).get());
        message.setContent(messageRequestDTO.getContent());
        message.setDateTime(LocalDateTime.now());

        messageRepository.save(message);
    }

    @Override
    public List<MessageResponseDTO> getAllMessagesByBetweenUsers(Integer fromId, Integer toId) {
        List<Message> messageList = messageRepository.findAllByFrom_IdAndTo_IdOrderByDateTimeAsc(fromId, toId);
        List<MessageResponseDTO> messageResponseDTOList = new ArrayList<>();
        messageList.forEach(m -> {
            MessageResponseDTO messageResponseDTO = new MessageResponseDTO();
            messageResponseDTO.setId(m.getId());
            messageResponseDTO.setFrom(m.getFrom().getId());
            messageResponseDTO.setTo(m.getTo().getId());
            messageResponseDTO.setContent(m.getContent());
            messageResponseDTO.setDateTime(m.getDateTime());
            messageResponseDTOList.add(messageResponseDTO);
        });
        return messageResponseDTOList;
    }
}
