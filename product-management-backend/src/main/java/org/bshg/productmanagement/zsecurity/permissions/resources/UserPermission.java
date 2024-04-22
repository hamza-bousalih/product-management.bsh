package org.bshg.productmanagement.zsecurity.permissions.resources;
import org.bshg.productmanagement.zsecurity.permissions.PermissionResource;

public enum UserPermission implements PermissionResource {
    CREATE(create),
    READ(read),
    EDIT(edit),
    DELETE(delete),
    ;

    private final String value;

    UserPermission(String action) {
        this.value = authority(action);
    }

    @Override
    public String resource() {
        return "client";
    }

    @Override
    public String authority() {
        return value;
    }
}
