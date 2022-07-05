package com.switchfully.eurder.users.admin.api;

import com.switchfully.eurder.util.name.Name;

public record AdminDTO (String userName, Name name, String email) {
}
