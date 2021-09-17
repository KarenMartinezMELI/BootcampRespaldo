package com.example.sendmessages.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class Message {
    protected int cost;
    protected int back;
    protected String message;
}
