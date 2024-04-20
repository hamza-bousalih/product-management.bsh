package org.bshg.productmanagement.zsecurity.permissions.resources;

import org.bshg.productmanagement.zsecurity.permissions.PermissionResource;

public enum AdminPermission implements PermissionResource {
    CREATE(create),
    READ(read),
    EDIT(edit),
    DELETE(delete),
    ;

    private final String value;

    AdminPermission(String action) {
        this.value = authority(action);
    }

    @Override
    public String resource() {
        return "admin";
    }

    @Override
    public String authority() {
        return value;
    }
}
