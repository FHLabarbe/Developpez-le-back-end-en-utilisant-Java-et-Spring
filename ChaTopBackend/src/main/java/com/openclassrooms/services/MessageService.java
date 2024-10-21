package com.openclassrooms.services;

import com.openclassrooms.model.DBUser;
import com.openclassrooms.model.Message;
import com.openclassrooms.model.MessageDTO;
import com.openclassrooms.model.Rentals;
import com.openclassrooms.repository.DBUserRepository;
import com.openclassrooms.repository.MessageRepository;
import com.openclassrooms.repository.RentalsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class MessageService {
  @Autowired
  private MessageRepository messageRepository;

  @Autowired
  private DBUserRepository userRepository;

  @Autowired
  private RentalsRepository rentalsRepository;

  public Message createMessage(MessageDTO messageDTO){
    DBUser userId = userRepository.findById(messageDTO.getUser_id()).orElseThrow(() -> new RuntimeException("User not found"));
    Rentals rentalId = rentalsRepository.findById(messageDTO.getRental_id()).orElseThrow(()-> new RuntimeException("Rental not found"));

    Message message = new Message();
    message.setUser_id(userId);
    message.setRental_id(rentalId);
    message.setMessage(messageDTO.getMessage());
    message.setCreated_at(LocalDateTime.now());
    message.setUpdated_at(LocalDateTime.now());

    return messageRepository.save(message);
  }
}
