package com.switchfully.eurder.util.name.service;

import com.switchfully.eurder.util.name.api.dto.NameDTO;
import com.switchfully.eurder.util.name.domain.Name;
import org.springframework.stereotype.Component;

@Component
public class NameMapper {
    public Name toEntity (NameDTO nameDTO){
        return new Name(nameDTO.firstName(),nameDTO.lastName());
    }

    public NameDTO toDTO(Name name){
        return new NameDTO(name.getFirstName(),name.getLastName());
    }
}
