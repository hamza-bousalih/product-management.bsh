package org.bshg.productmanagement.zsecurity.permissions.resources;
import org.bshg.productmanagement.zsecurity.permissions.PermissionResource;

public enum SupplierPermission implements PermissionResource {
    CREATE(create),
    READ(read),
    EDIT(edit),
    DELETE(delete),
    ;

    private final String value;

    SupplierPermission(String action) {
        this.value = authority(action);
    }

    @Override
    public String resource() {
        return "supplier";
    }

    @Override
    public String authority() {
        return value;
    }
}
