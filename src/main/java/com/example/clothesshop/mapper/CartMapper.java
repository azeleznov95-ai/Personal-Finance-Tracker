package com.example.clothesshop.mapper;

import com.example.clothesshop.dto.CartResponseDto;
import com.example.clothesshop.model.Cart;
import org.springframework.stereotype.Component;

@Component
public class CartMapper {
    public CartResponseDto toResponse(Cart entity){
        CartResponseDto response =new CartResponseDto();
        var items = entity.getCartItems();
        response.setCartItems(items);
        response.setCartStatus(entity.getCartStatus());

        var totalPrice = 0;
        if (items!=null){
            for(var item: items ){
                totalPrice+= item.getPriceSnapshot()*item.getAmount();

            }}
        response.setTotalPrice(totalPrice);
        response.setUser(entity.getUser());
        return response;
    }
}
