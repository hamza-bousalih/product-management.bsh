package org.bshg.productmanagement.ws.converter;
import org.bshg.productmanagement.entity.Admin;
import org.bshg.productmanagement.ws.dto.AdminDto;
import org.bshg.productmanagement.zsecurity.ws.converter.AppUserConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.ArrayList;
import java.util.List;
@Component
public class AdminConverter {
@Autowired private AppUserConverter appUserConverter;
protected void configure(boolean value) {
}
public final AdminDto toDto(Admin item) {
this.configure(false);
var dto = item != null ? convertToDto(item) : null;
this.configure(true);
return dto;
}
public final Admin toItem(AdminDto d) {
return d != null ? convertToItem(d) : null;
}
public final List<Admin> toItem(List<AdminDto> dtos) {
if (dtos == null) return null;
List<Admin> list = new ArrayList<>();
dtos.forEach(it -> list.add(toItem(it)));
return list;
}
public final List<AdminDto> toDto(List<Admin> items) {
if (items == null) return null;
List<AdminDto> list = new ArrayList<>();
items.forEach(it -> list.add(toDto(it)));
return list;
}
protected Admin convertToItem(AdminDto dto) {
var item = new Admin();
appUserConverter.convertToChildItem(dto, item);
item.setFirstname(dto.getFirstname());
item.setLastname(dto.getLastname());
item.setEmail(dto.getEmail());
item.setPhone(dto.getPhone());
return item;
}
protected AdminDto convertToDto(Admin item) {
var dto = new AdminDto();
appUserConverter.convertToChildDto(item, dto);
dto.setFirstname(item.getFirstname());
dto.setLastname(item.getLastname());
dto.setEmail(item.getEmail());
dto.setPhone(item.getPhone());
return dto;
}
}