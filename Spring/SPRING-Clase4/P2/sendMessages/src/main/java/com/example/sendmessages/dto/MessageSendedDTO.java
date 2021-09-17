package com.example.sendmessages.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class MessageSendedDTO {
    private int cost;
    private int back;
    private String msg;
    private String messengerId;
    private String typeMessenger;
}
