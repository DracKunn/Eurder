package com.switchfully.eurder.user.api.dto.admin;

import com.switchfully.eurder.util.name.api.dto.NameDTO;

public record CreateAdminDTO (String userName, NameDTO nameDTO, String email){
}
