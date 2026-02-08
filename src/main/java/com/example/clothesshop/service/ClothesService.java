package com.example.clothesshop.service;

import com.example.clothesshop.dto.ClothesAddRequestDto;
import com.example.clothesshop.dto.ClothesResponseDto;
import com.example.clothesshop.exeptions.BadClothesRequest;
import com.example.clothesshop.mapper.ClothesMapper;
import com.example.clothesshop.repository.ClothesRepository;
import org.springframework.stereotype.Service;

@Service
public class ClothesService {
private final ClothesRepository clothesRepository;
private final ClothesMapper mapper;
    ClothesService(ClothesRepository clothesRepository, ClothesMapper mapper){
        this.clothesRepository=clothesRepository;
        this.mapper = mapper;
    }
    public ClothesResponseDto create(ClothesAddRequestDto requestDto){
        if (requestDto.getName()==null|| requestDto.getName().isBlank()){
            throw new BadClothesRequest("Name is null or blank");
        }
        if (requestDto.getPrice() == null){
            throw new BadClothesRequest("Price is null");
        }
        if (requestDto.getPrice()<=0){
            throw new BadClothesRequest("Price must be more than zero");
        }
        if (requestDto.getRemainingAmount()==null){
            throw new BadClothesRequest("remaining amount is null");
        }
        if (requestDto.getRemainingAmount()<0){
            throw new BadClothesRequest("remaining amount must be more than zero or equal");
        }
        var entity = mapper.toEntity(requestDto);
        var saved = clothesRepository.save(entity);
        return mapper.toResponse(saved);
    }
}
