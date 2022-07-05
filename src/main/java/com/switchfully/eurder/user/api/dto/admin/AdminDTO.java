package com.switchfully.eurder.user.api.dto.admin;

import com.switchfully.eurder.util.name.api.dto.NameDTO;
import com.switchfully.eurder.util.name.domain.Name;

public record AdminDTO(int id, String userName, NameDTO nameDTO, String email) {}
