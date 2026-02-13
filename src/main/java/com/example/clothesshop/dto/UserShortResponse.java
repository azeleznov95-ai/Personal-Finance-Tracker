package com.example.clothesshop.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserShortResponse {
    private Long id;
    private  String login;
    private String telegramUsername;
}
