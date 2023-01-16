package com.codex.mystore.constants;


public enum RoleType {

    // enum constants calling the enum constructors
    ADMIN("ROLE_ADMIN", 1),
    USER("ROLE_USER",2),
    LEADER("ROLE_LEADER",3);

    private final String roleName;
    private final int roleType;

    RoleType(String roleName, int roleType) {
        this.roleName = roleName;
        this.roleType = roleType;
    }

    public String getRoleName() {
        return roleName;
    }

    public int getRoleType() {
        return roleType;
    }
}