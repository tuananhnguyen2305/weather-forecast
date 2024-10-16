package com.nta.cms.enums;

import java.util.List;

public enum Role {
    ADMIN, MANAGER, USER;

    public static List<String> getAllRoles() {
        return List.of("MANAGER", "ADMIN", "USER");
    }

}
