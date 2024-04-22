package org.bshg.productmanagement.zsecurity.permissions;
import org.bshg.productmanagement.zsecurity.entity.Permission;
import org.bshg.productmanagement.zsecurity.entity.Role;
import org.bshg.productmanagement.zsecurity.permissions.resources.*;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
public enum RoleEnum {
ADMIN(
UserPermission.values(),
RolePermission.values(),
AdminPermission.values(),
ProductPermission.values(),
CustomerPermission.values(),
SupplierPermission.values()
),
;
private Set<PermissionResource> permissions = null;
RoleEnum() {}
RoleEnum(Object... permissionsList) {
this.permissions = Arrays.stream(permissionsList)
.flatMap(o -> {
if (o instanceof PermissionResource[]) {
return Arrays.stream((PermissionResource[]) o);
} else if (o instanceof PermissionResource) {
return Arrays.stream(new PermissionResource[]{(PermissionResource) o});
} else {
throw new IllegalArgumentException("Unsupported type in permissionsList");
}
}).collect(Collectors.toUnmodifiableSet());
}
public Set<PermissionResource> getPermissions() {
return permissions;
}
public Role getRole() {
var result = new Role();
result.setName(this.name());
result.setPermissions(
this.getPermissions().stream()
.map(p -> new Permission(p.authority()))
.collect(Collectors.toSet())
);
return result;
}
}