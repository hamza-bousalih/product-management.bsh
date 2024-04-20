package org.bshg.productmanagement.zsecurity.permissions.resources;

import org.bshg.productmanagement.zsecurity.permissions.PermissionResource;

public enum CustomerPermission implements PermissionResource {
    CREATE(create),
    READ(read),
    EDIT(edit),
    DELETE(delete),
    ;

    private final String value;

    CustomerPermission(String action) {
        this.value = authority(action);
    }

    @Override
    public String resource() {
        return "customer";
    }

    @Override
    public String authority() {
        return value;
    }
}
