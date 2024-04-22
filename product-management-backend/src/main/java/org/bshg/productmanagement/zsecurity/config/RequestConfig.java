package org.bshg.productmanagement.zsecurity.config;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.bshg.productmanagement.zsecurity.permissions.resources.*;
import org.bshg.productmanagement.zsecurity.permissions.RoleEnum;
import static org.springframework.http.HttpMethod.*;
public class RequestConfig {
private RequestConfig() {}
private static HttpSecurity http;
public static void set(HttpSecurity http) throws Exception {
if (RequestConfig.http == null) RequestConfig.http = http;
adminAuthorizeHttpRequests();
productAuthorizeHttpRequests();
customerAuthorizeHttpRequests();
supplierAuthorizeHttpRequests();
appUserAuthorizeHttpRequests();
roleAuthorizeHttpRequests();
defaultAuthorizeHttpRequests();
}
private static void appUserAuthorizeHttpRequests() throws Exception {
http.authorizeHttpRequests(auth -> auth
.requestMatchers(GET, "/api/v1/user/**").hasAnyAuthority(UserPermission.READ.authority())
.requestMatchers(POST, "/api/v1/user/**").hasAnyAuthority(UserPermission.CREATE.authority())
.requestMatchers(PUT, "/api/v1/user/**").hasAnyAuthority(UserPermission.EDIT.authority())
.requestMatchers(DELETE, "/api/v1/user/**").hasAnyAuthority(UserPermission.DELETE.authority())
.requestMatchers("/api/v1/user/**").hasAnyRole(RoleEnum.ADMIN.name())
);
}
private static void roleAuthorizeHttpRequests() throws Exception {
http.authorizeHttpRequests(auth -> auth
.requestMatchers(GET, "/api/v1/role/**").hasAnyAuthority(RolePermission.READ.authority())
.requestMatchers(POST, "/api/v1/role/**").hasAnyAuthority(RolePermission.CREATE.authority())
.requestMatchers(PUT, "/api/v1/role/**").hasAnyAuthority(RolePermission.EDIT.authority())
.requestMatchers(DELETE, "/api/v1/role/**").hasAnyAuthority(RolePermission.DELETE.authority())
.requestMatchers("/api/v1/role/**").hasAnyRole(RoleEnum.ADMIN.name())
);
}
private static void adminAuthorizeHttpRequests() throws Exception {
http.authorizeHttpRequests(auth -> auth
.requestMatchers(GET, "/api/admin/**").hasAnyAuthority(AdminPermission.READ.authority())
.requestMatchers(POST, "/api/admin/**").hasAnyAuthority(AdminPermission.CREATE.authority())
.requestMatchers(PUT, "/api/admin/**").hasAnyAuthority(AdminPermission.EDIT.authority())
.requestMatchers(DELETE, "/api/admin/**").hasAnyAuthority(AdminPermission.DELETE.authority())
.requestMatchers("/api/admin/**").hasAnyRole(RoleEnum.ADMIN.name())
);
}
private static void productAuthorizeHttpRequests() throws Exception {
http.authorizeHttpRequests(auth -> auth
.requestMatchers(GET, "/api/product/**").hasAnyAuthority(ProductPermission.READ.authority())
.requestMatchers(POST, "/api/product/**").hasAnyAuthority(ProductPermission.CREATE.authority())
.requestMatchers(PUT, "/api/product/**").hasAnyAuthority(ProductPermission.EDIT.authority())
.requestMatchers(DELETE, "/api/product/**").hasAnyAuthority(ProductPermission.DELETE.authority())
.requestMatchers("/api/product/**").hasAnyRole(RoleEnum.ADMIN.name())
);
}
private static void customerAuthorizeHttpRequests() throws Exception {
http.authorizeHttpRequests(auth -> auth
.requestMatchers(GET, "/api/customer/**").hasAnyAuthority(CustomerPermission.READ.authority())
.requestMatchers(POST, "/api/customer/**").hasAnyAuthority(CustomerPermission.CREATE.authority())
.requestMatchers(PUT, "/api/customer/**").hasAnyAuthority(CustomerPermission.EDIT.authority())
.requestMatchers(DELETE, "/api/customer/**").hasAnyAuthority(CustomerPermission.DELETE.authority())
.requestMatchers("/api/customer/**").hasAnyRole(RoleEnum.ADMIN.name())
);
}
private static void supplierAuthorizeHttpRequests() throws Exception {
http.authorizeHttpRequests(auth -> auth
.requestMatchers(GET, "/api/supplier/**").hasAnyAuthority(SupplierPermission.READ.authority())
.requestMatchers(POST, "/api/supplier/**").hasAnyAuthority(SupplierPermission.CREATE.authority())
.requestMatchers(PUT, "/api/supplier/**").hasAnyAuthority(SupplierPermission.EDIT.authority())
.requestMatchers(DELETE, "/api/supplier/**").hasAnyAuthority(SupplierPermission.DELETE.authority())
.requestMatchers("/api/supplier/**").hasAnyRole(RoleEnum.ADMIN.name())
);
}
private static void defaultAuthorizeHttpRequests() throws Exception {
http.authorizeHttpRequests(auth -> auth
.requestMatchers("/login").permitAll()
.requestMatchers("/api/login").permitAll()
.requestMatchers("/api/**").authenticated()
.anyRequest().authenticated());
}
}