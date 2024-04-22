package org.bshg.productmanagement.zsecurity.permissions.resources;
import org.bshg.productmanagement.zsecurity.permissions.PermissionResource;

public enum AppUserPermission implements PermissionResource {
    CREATE(create),
    READ(read),
    EDIT(edit),
    DELETE(delete),
    ;

    private final String value;

    AppUserPermission(String action) {
        this.value = authority(action);
    }

    @Override
    public String resource() {
        return "appUser";
    }

    @Override
    public String authority() {
        return value;
    }
}
