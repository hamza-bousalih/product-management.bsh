package org.bshg.productmanagement;
import org.bshg.productmanagement.zsecurity.entity.AppUser;
import org.bshg.productmanagement.zsecurity.entity.Role;
import org.bshg.productmanagement.zsecurity.service.facade.AppUserService;
import org.bshg.productmanagement.zsecurity.service.facade.RoleService;
import org.bshg.productmanagement.zsecurity.permissions.RoleEnum;
import org.bshg.productmanagement.services.facade.AdminService;
import org.bshg.productmanagement.entity.Admin;
import org.bshg.productmanagement.services.facade.ProductService;
import org.bshg.productmanagement.entity.Product;
import org.bshg.productmanagement.services.facade.CustomerService;
import org.bshg.productmanagement.entity.Customer;
import org.bshg.productmanagement.services.facade.SupplierService;
import org.bshg.productmanagement.entity.Supplier;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import java.util.ArrayList;
import java.util.List;
@Component
public class DataLoader implements ApplicationRunner {
@Value("${app.data-loader.load}")
private boolean load;
@Override
public void run(ApplicationArguments args) throws Exception {
if (load) {
System.out.println("Data Loading...");
generatePermission();
generateAdminAccount();
generateAdmin();
generateProduct();
generateCustomer();
generateSupplier();
System.out.println("Data Loaded!");
}
}
private void generatePermission() {
for (RoleEnum role : RoleEnum.values()) {
var roleEntity = role.getRole();
roleService.save(roleEntity);
}
}
private void generateAdminAccount() {
AppUser user = new AppUser("admin");
user.setPassword("123");
Role role = new Role();
role.setName(RoleEnum.ADMIN.name());
user.setRoles(new ArrayList<>());
user.getRoles().add(role);
appUserService.save(user);
System.out.println("One Account For ADMIN role is created: 'admin' :: '123'");
}
private void generateAdmin() {
for (int i = 0; i < 50; i++) {
Admin item = new Admin();
item.setFirstname("firstname " + i);
item.setLastname("lastname " + i);
item.setEmail("email " + i);
item.setPhone("phone " + i);
item.setUsername("Admin " + i);
item.setPassword(appUserService.cryptPassword("Admin " + i));
List<Role> savedRoles = roleService.save(item.getRoles());
item.setRoles(savedRoles);
adminService.create(item);
}
System.out.println("Data For Admin Generated!");
}
private void generateProduct() {
for (int i = 0; i < 50; i++) {
Product item = new Product();
item.setName("name " + i);
item.setMarque("marque " + i);
item.setSn("sn " + i);
item.setCab("cab " + i);
productService.create(item);
}
System.out.println("Data For Product Generated!");
}
private void generateCustomer() {
for (int i = 0; i < 50; i++) {
Customer item = new Customer();
item.setFirstname("firstname " + i);
item.setLastname("lastname " + i);
item.setEmail("email " + i);
item.setPhone("phone " + i);
customerService.create(item);
}
System.out.println("Data For Customer Generated!");
}
private void generateSupplier() {
for (int i = 0; i < 50; i++) {
Supplier item = new Supplier();
item.setName("name " + i);
supplierService.create(item);
}
System.out.println("Data For Supplier Generated!");
}
@Autowired private AdminService adminService;
@Autowired private ProductService productService;
@Autowired private CustomerService customerService;
@Autowired private SupplierService supplierService;
@Autowired private AppUserService appUserService;
@Autowired private RoleService roleService;
}