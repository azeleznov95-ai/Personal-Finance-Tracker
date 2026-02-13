package com.example.clothesshop.mapper;

import com.example.clothesshop.dto.UserShortResponse;
import com.example.clothesshop.model.Users;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {
public UserShortResponse toResponse(Users entity){
    UserShortResponse response = new UserShortResponse();
    response.setId(entity.getId());
    response.setLogin(entity.getLogin());
    response.setTelegramUsername(entity.getTelegramUsername());
    return response;
}
}
